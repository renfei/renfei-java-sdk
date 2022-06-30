/*
 *   Copyright 2022 RenFei(i@renfei.net)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.renfei.sdk.test.security.gm.sm;

import net.renfei.sdk.security.gm.sm.SM2Util;
import net.renfei.sdk.security.gm.sm.SM2X509CertFactory;
import net.renfei.sdk.security.gm.sm.random.CertSNAllocator;
import net.renfei.sdk.security.gm.sm.random.RandomSNAllocator;
import net.renfei.sdk.test.Tests;
import net.renfei.sdk.utils.StringUtil;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x500.X500NameBuilder;
import org.bouncycastle.asn1.x500.style.BCStyle;
import org.bouncycastle.crypto.engines.SM2Engine;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.pkcs.PKCS10CertificationRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.security.auth.x500.X500Principal;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.*;
import java.security.cert.X509Certificate;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author renfei
 */
public class SM2UtilTest extends Tests {
    static String pubFileName = "pub.pem";
    static String privFileName = "priv.pem";
    static String encryptedprivFileName = "encryptedpriv.pem";
    static String reqFileName = "req.pem";
    static String certFileName = "cert.pem";
    static String exceptionHappened = "Exception happened";
    static String keyEqualHint = "key should be equal";
    static String passwd = StringUtil.getRandomNumber(18);
    static int randomData = 128;
    static byte[] message = StringUtil.getRandomString(randomData).getBytes();
    PublicKey pubKey;
    PrivateKey privKey;
    X509Certificate x509Certificate;
    KeyPair keyPair;

    static {
        try {
            Security.addProvider(new BouncyCastleProvider());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void saveCSRInPem(PKCS10CertificationRequest csr, String csrFile) throws IOException, OperatorCreationException {
        String csrPem = SM2Util.pemFrom(csr);
        Files.write(Paths.get(csrFile), csrPem.getBytes());
    }

    public static void saveCertificateInPem(X509Certificate x509Certificate, String certFileName) throws Exception {
        String certStr = SM2Util.pemFrom(x509Certificate);
        Files.write(Paths.get(certFileName), certStr.getBytes());
    }

    public static X509Certificate genCertificate(KeyPair keyPair, PKCS10CertificationRequest csr, X500Name x500Name) throws Exception {
        long certExpire = 20L * 365 * 24 * 60 * 60 * 1000;
        CertSNAllocator snAllocator = new RandomSNAllocator();
        SM2X509CertFactory rootCertFactory = new SM2X509CertFactory(keyPair, x500Name);
        Date now = new Date();
        return rootCertFactory.rootCACert(csr.getEncoded(),
                "i@renfei.net",
                snAllocator.nextSerialNumber(),
                now,
                new Date(now.getDate() + certExpire));
    }

    public static void savePemFormatKeyFile(PrivateKey privateKey, String filename) throws IOException, OperatorCreationException {
        String privateKeyPem = SM2Util.pemFrom(privateKey, "");
        Files.write(Paths.get(filename), privateKeyPem.getBytes());
    }

    public static void savePemFormatPubKeyFile(PublicKey publicKey, String filename) throws IOException {
        String pubKeyPem = SM2Util.pemFrom(publicKey);
        Files.write(Paths.get(filename), pubKeyPem.getBytes());
    }

    public static void saveKeyPairInPem(KeyPair keyPair, String pubFileName, String privFileName) throws IOException, OperatorCreationException {
        savePemFormatKeyFile(keyPair.getPrivate(), privFileName);
        savePemFormatPubKeyFile(keyPair.getPublic(), pubFileName);
    }

    @BeforeEach
    @Test
    public void generateFile() {
        File pubFile = new File(pubFileName);
        File privFile = new File(privFileName);
        File reqFile = new File(reqFileName);
        File certFile = new File(certFileName);
        try {
            if (!pubFile.exists()) {
                SM2Util instance = new SM2Util();
                this.keyPair = instance.generatekeyPair();
                saveKeyPairInPem(this.keyPair, pubFileName, privFileName);

                X500NameBuilder builder = new X500NameBuilder(BCStyle.INSTANCE);
                X500Name x500Name = builder.addRDN(BCStyle.CN, "Root CA").build();
                // gen csr && save
                PKCS10CertificationRequest csr = SM2Util.generateCSR(keyPair, new X500Principal(String.valueOf(x500Name)));
                saveCSRInPem(csr, reqFileName);
                // gen cert && save
                X509Certificate x509Certificate = genCertificate(keyPair, csr, x500Name);
                saveCertificateInPem(x509Certificate, certFileName);
            } else {
                System.out.println("Skip file generation deal to interact testing.");
            }
            this.pubKey = SM2Util.loadPublicFromFile(pubFileName);
            assertNotNull(this.pubKey);
            this.privKey = SM2Util.loadPrivateFromFile(privFileName, "");
            assertNotNull(this.privKey);
            this.x509Certificate = SM2Util.loadX509CertificateFromFile(certFileName);
            assertNotNull(this.x509Certificate);
            assertEquals("SM3WITHSM2", this.x509Certificate.getSigAlgName());
        } catch (Exception e) {
            e.printStackTrace();
            fail(exceptionHappened);
        }
        assertTrue(pubFile.exists());
        assertTrue(privFile.exists());
        assertTrue(reqFile.exists());
        assertTrue(certFile.exists());
    }

    //encrypt and decrypt
    @Test
    public void encryptAndDecryptC1C3C2() {
        SM2Engine sm2Engine = new SM2Engine(SM2Engine.Mode.C1C3C2);
        try {
            SM2Util instance = new SM2Util();
            byte[] encrypted = instance.encrypt(sm2Engine, this.pubKey, message);
            byte[] rs = instance.decrypt(sm2Engine, this.privKey, encrypted);
            assertEquals(new String(message), new String(rs));
            byte[] encrypted2 = instance.encrypt(sm2Engine, this.pubKey, "msg".getBytes());
            rs = instance.decrypt(sm2Engine, this.privKey, encrypted2);
            assertNotEquals(new String(message), new String(rs));
        } catch (Exception e) {
            e.printStackTrace();
            fail(exceptionHappened);
        }
    }

    //encrypt and decrypt
    @Test
    public void encryptAndDecryptC1C2C3() {
        SM2Engine sm2Engine = new SM2Engine(SM2Engine.Mode.C1C2C3);
        try {
            SM2Util instance = new SM2Util();
            byte[] encrypted = instance.encrypt(sm2Engine, this.pubKey, message);
            byte[] rs = instance.decrypt(sm2Engine, this.privKey, encrypted);
            assertEquals(new String(message), new String(rs));
            byte[] encrypted2 = instance.encrypt(sm2Engine, this.pubKey, "msg".getBytes());
            rs = instance.decrypt(sm2Engine, this.privKey, encrypted2);
            assertNotEquals(new String(message), new String(rs));
        } catch (Exception e) {
            e.printStackTrace();
            fail(exceptionHappened);
        }
    }

    //sign and verify
    @Test
    public void signAndverify() {
        try {
            SM2Util instance = new SM2Util();
            byte[] signbyte = instance.sign(this.privKey, message);
            boolean rs = instance.verify(this.pubKey, message, signbyte);
            assertTrue(rs);
            rs = instance.verify(this.pubKey, message, message);
            assertFalse(rs);
        } catch (Exception e) {
            e.printStackTrace();
            fail(exceptionHappened);
        }
    }

    @Test
    public void signAndverifyHash256Sample() {
        try {
            if (Security.getProvider("BC") == null) {
                Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
            }
            Signature signature = Signature.getInstance("SHA256WITHSM2", "BC");
            SM2Util instance = new SM2Util();
            instance.setSignature(signature);
            byte[] signbyte = instance.sign(this.privKey, message);
            boolean rs = instance.verify(this.pubKey, message, signbyte);
            assertTrue(rs);
            rs = instance.verify(this.pubKey, message, message);
            assertFalse(rs);
        } catch (Exception e) {
            e.printStackTrace();
            fail(exceptionHappened);
        }
    }

    //private key Derive from private key
    @Test
    public void derivePublicFromPrivate() {
        SM2Util.derivePublicFromPrivate(this.privKey);
    }

    //key with password
    @Test
    public void keyPairWithPasswd() {
        try {
            SM2Util instance = new SM2Util();
            KeyPair keyPair = instance.generatekeyPair();
            String privateKeyPem = SM2Util.pemFrom(keyPair.getPrivate(), passwd);
            Files.write(Paths.get(encryptedprivFileName), privateKeyPem.getBytes());
            PrivateKey key = SM2Util.loadPrivateFromFile(encryptedprivFileName, passwd);
            assertNotNull(key);
        } catch (Exception e) {
            e.printStackTrace();
            fail(exceptionHappened);
        }
    }

    @Test
    public void issueCertificate() throws Exception {
        SM2Util sm2Util = new SM2Util();
        // 证书颁发时长
        long certExpire = 20L * 365 * 24 * 60 * 60 * 1000;
        CertSNAllocator snAllocator = new RandomSNAllocator();

        // one 模拟根 CA 自签名生成根证书 rootCACert
        KeyPair rootKeyPair = sm2Util.generatekeyPair();
        X500Name rootX500Name = new X500NameBuilder(BCStyle.INSTANCE).addRDN(BCStyle.CN, "Root CA").build();
        SM2X509CertFactory rootCertMaker = new SM2X509CertFactory(rootKeyPair, rootX500Name);
        PublicKey rootKeyPairPublic = rootKeyPair.getPublic();
        byte[] rootcsr = SM2Util.generateCSR(rootKeyPair, new X500Principal(String.valueOf(rootX500Name))).getEncoded();
        Date now = new Date();
        X509Certificate rootCACert = rootCertMaker.rootCACert(rootcsr,
                "i@renfei.net",
                snAllocator.nextSerialNumber(),
                now,
                new Date(now.getDate() + certExpire));

        // two 模拟根 CA 生成中间证书
        KeyPair midKeyPair = sm2Util.generatekeyPair();
        PublicKey midKeyPairPublic = midKeyPair.getPublic();
        X500Name midX500Name = new X500NameBuilder(BCStyle.INSTANCE).addRDN(BCStyle.CN, "Intermediate CA").build();
        byte[] midcsr = SM2Util.generateCSR(midKeyPair, new X500Principal(String.valueOf(midX500Name))).getEncoded();
        X509Certificate midCACert = rootCertMaker.subCACert(midcsr,
                "test1@renfei.net",
                snAllocator.nextSerialNumber(),
                now,
                new Date(now.getDate() + certExpire)
        );

        // three 模拟中间 CA 生成用户证书
        SM2X509CertFactory midCertMaker = new SM2X509CertFactory(midKeyPair, midX500Name);
        KeyPair userKeyPair = sm2Util.generatekeyPair();
        X500Name userX500Name = new X500NameBuilder(BCStyle.INSTANCE).addRDN(BCStyle.CN, "User CA").build();
        byte[] usercsr = SM2Util.generateCSR(userKeyPair, new X500Principal(String.valueOf(userX500Name))).getEncoded();
        X509Certificate userCACert = midCertMaker.subCACert(usercsr,
                "test2@renfei.net",
                snAllocator.nextSerialNumber(),
                now,
                new Date(now.getDate() + certExpire));

        // 根证书自签名，用自己的公钥验证
        rootCACert.verify(rootKeyPairPublic, BouncyCastleProvider.PROVIDER_NAME);
        // 中间证书可用根证书的公钥验证
        midCACert.verify(rootKeyPairPublic, BouncyCastleProvider.PROVIDER_NAME);
        // 用户证书可用中间证书的公钥验证
        userCACert.verify(midKeyPairPublic, BouncyCastleProvider.PROVIDER_NAME);
    }
}
