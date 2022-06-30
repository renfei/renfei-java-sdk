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
package net.renfei.sdk.security.gm.sm;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringWriter;
import java.math.BigInteger;
import java.security.*;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.spec.ECGenParameterSpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import javax.security.auth.x500.X500Principal;

import net.renfei.sdk.security.gm.sm.random.SecureRandomFactory;
import org.bouncycastle.asn1.gm.GMNamedCurves;
import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import org.bouncycastle.asn1.x9.X9ECParameters;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.engines.SM2Engine;
import org.bouncycastle.crypto.params.ECDomainParameters;
import org.bouncycastle.crypto.params.ECPrivateKeyParameters;
import org.bouncycastle.crypto.params.ECPublicKeyParameters;
import org.bouncycastle.crypto.params.ParametersWithRandom;
import org.bouncycastle.jcajce.provider.asymmetric.ec.BCECPrivateKey;
import org.bouncycastle.jcajce.provider.asymmetric.ec.BCECPublicKey;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.jce.spec.ECParameterSpec;
import org.bouncycastle.jce.spec.ECPublicKeySpec;
import org.bouncycastle.math.ec.ECPoint;
import org.bouncycastle.math.ec.FixedPointCombMultiplier;
import org.bouncycastle.openssl.PEMParser;
import org.bouncycastle.openssl.PKCS8Generator;
import org.bouncycastle.openssl.jcajce.*;
import org.bouncycastle.operator.ContentSigner;
import org.bouncycastle.operator.InputDecryptorProvider;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.operator.OutputEncryptor;
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder;
import org.bouncycastle.pkcs.PKCS10CertificationRequest;
import org.bouncycastle.pkcs.PKCS10CertificationRequestBuilder;
import org.bouncycastle.pkcs.PKCS8EncryptedPrivateKeyInfo;
import org.bouncycastle.pkcs.PKCSException;
import org.bouncycastle.pkcs.jcajce.JcaPKCS10CertificationRequestBuilder;
import org.bouncycastle.util.io.pem.PemObject;
import org.bouncycastle.util.io.pem.PemReader;
import org.bouncycastle.util.io.pem.PemWriter;

/**
 * 非对称加密
 *
 * @author renfei
 */
public class SM2Util extends AbstractSM {
    private Signature signature;
    private static final X9ECParameters X_9_EC_PARAMETERS = GMNamedCurves.getByName(CURVE_NAME);
    private static final ECDomainParameters EC_DOMAIN_PARAMETERS = new ECDomainParameters(X_9_EC_PARAMETERS.getCurve(), X_9_EC_PARAMETERS.getG(), X_9_EC_PARAMETERS.getN());
    private static final ECParameterSpec PARAMETER_SPEC = new ECParameterSpec(X_9_EC_PARAMETERS.getCurve(), X_9_EC_PARAMETERS.getG(), X_9_EC_PARAMETERS.getN());
    private static KeyPairGenerator generator;
    private static final JcaPEMKeyConverter CONVERTER = new JcaPEMKeyConverter().setProvider(BouncyCastleProvider.PROVIDER_NAME);

    public SM2Util() throws NoSuchProviderException, NoSuchAlgorithmException, InvalidAlgorithmParameterException {
        if (Security.getProvider(BouncyCastleProvider.PROVIDER_NAME) == null) {
            Security.addProvider(new BouncyCastleProvider());
        }
        signature = Signature.getInstance(SM3SM2_VALUE, BouncyCastleProvider.PROVIDER_NAME);
        generator = KeyPairGenerator.getInstance(EC_VALUE, BouncyCastleProvider.PROVIDER_NAME);
        generator.initialize(new ECGenParameterSpec(CURVE_NAME));
    }

    public Signature getSignature() {
        return signature;
    }

    public void setSignature(Signature signature) {
        this.signature = signature;
    }

    /**
     * 生成 PKCS#10 证书请求
     *
     * @return RSA P10 证书请求 Base64 字符串
     * @throws InvalidAlgorithmParameterException 当采用的 ECC 算法不适用于该密钥对生成器时
     */
    public KeyPair generateKeyPair() {
        return generator.generateKeyPair();
    }

    /**
     * 加密
     *
     * @param sm2Engine SM2引擎
     * @param publicKey 公钥
     * @param message   内容
     * @return
     * @throws InvalidCipherTextException
     */
    public byte[] encrypt(SM2Engine sm2Engine, PublicKey publicKey, byte[] message) throws InvalidCipherTextException {
        BCECPublicKey localECPublicKey = (BCECPublicKey) publicKey;
        ECPublicKeyParameters ecPublicKeyParameters = new ECPublicKeyParameters(localECPublicKey.getQ(), EC_DOMAIN_PARAMETERS);
        sm2Engine.init(true, new ParametersWithRandom(ecPublicKeyParameters, SecureRandomFactory.getSecureRandom()));
        return sm2Engine.processBlock(message, 0, message.length);
    }

    /**
     * 解密
     *
     * @param sm2Engine  SM2引擎
     * @param privateKey 私钥
     * @param message    内容
     * @return
     * @throws InvalidCipherTextException
     */
    public byte[] decrypt(SM2Engine sm2Engine, PrivateKey privateKey, byte[] message) throws InvalidCipherTextException {
        BCECPrivateKey localECPrivateKey = (BCECPrivateKey) privateKey;
        ECPrivateKeyParameters ecPrivateKeyParameters = new ECPrivateKeyParameters(localECPrivateKey.getD(), EC_DOMAIN_PARAMETERS);
        sm2Engine.init(false, ecPrivateKeyParameters);
        return sm2Engine.processBlock(message, 0, message.length);
    }

    /**
     * 对消息进行签名
     *
     * @param privateKey 私钥证书
     * @param message    消息
     * @return
     * @throws SignatureException
     * @throws InvalidKeyException
     */
    public byte[] sign(PrivateKey privateKey, byte[] message) throws SignatureException, InvalidKeyException {
        synchronized (this) {
            signature.initSign(privateKey, SecureRandomFactory.getSecureRandom());
            signature.update(message);
            return signature.sign();
        }
    }

    /**
     * 验证签名
     *
     * @param publicKey 公钥证书
     * @param message   消息
     * @param sigBytes  签名
     * @return
     * @throws InvalidKeyException
     * @throws SignatureException
     */
    public boolean verify(PublicKey publicKey, byte[] message, byte[] sigBytes) throws InvalidKeyException, SignatureException {
        synchronized (this) {
            signature.initVerify(publicKey);
            signature.update(message);
            return signature.verify(sigBytes);
        }
    }

    /**
     * 生成 CSR
     *
     * @param keyPair
     * @param subject
     * @return
     * @throws OperatorCreationException
     */
    public static PKCS10CertificationRequest generateCSR(KeyPair keyPair, X500Principal subject) throws OperatorCreationException {
        ContentSigner signer = new JcaContentSignerBuilder(SM3SM2_VALUE).build(keyPair.getPrivate());
        PKCS10CertificationRequestBuilder builder = new JcaPKCS10CertificationRequestBuilder(subject, keyPair.getPublic());
        return builder.build(signer);
    }

    /**
     * 私钥转字符串
     *
     * @param privateKey 私钥对象
     * @param password   密码
     * @return
     * @throws OperatorCreationException
     * @throws IOException
     */
    public static String pemFrom(PrivateKey privateKey, String password) throws OperatorCreationException, IOException {
        OutputEncryptor encryptor = null;
        if (password != null && password.length() > 0) {
            encryptor = new JceOpenSSLPKCS8EncryptorBuilder(PKCS8Generator.AES_256_CBC)
                    .setProvider(BouncyCastleProvider.PROVIDER_NAME)
                    .setRandom(SecureRandomFactory.getSecureRandom())
                    .setPasssword(password.toCharArray())
                    .build();
        }
        PKCS8Generator generator = new JcaPKCS8Generator(privateKey, encryptor);
        StringWriter stringWriter = new StringWriter();
        JcaPEMWriter pemWriter = new JcaPEMWriter(stringWriter);
        pemWriter.writeObject(generator);
        pemWriter.close();
        stringWriter.close();
        return stringWriter.toString();
    }

    /**
     * 公钥转字符串
     *
     * @param publicKey
     * @return
     * @throws IOException
     */
    public static String pemFrom(PublicKey publicKey) throws IOException {
        PemObject pem = new PemObject("PUBLIC KEY", publicKey.getEncoded());
        StringWriter str = new StringWriter();
        PemWriter pemWriter = new PemWriter(str);
        pemWriter.writeObject(pem);
        pemWriter.close();
        str.close();
        return str.toString();
    }

    /**
     * 打印 OpenSSL PEM 格式文件字符串的 SSL 证书请求 CSR 文件内容
     *
     * @param csr 证书请求对象
     */
    public static String pemFrom(PKCS10CertificationRequest csr) throws IOException {
        PemObject pem = new PemObject("CERTIFICATE REQUEST", csr.getEncoded());
        StringWriter str = new StringWriter();
        PemWriter pemWriter = new PemWriter(str);
        pemWriter.writeObject(pem);
        pemWriter.close();
        str.close();
        return str.toString();
    }

    public static String pemFrom(X509Certificate x509Certificate) throws IOException, CertificateEncodingException {
        PemObject pem = new PemObject("CERTIFICATE", x509Certificate.getEncoded());
        StringWriter str = new StringWriter();
        PemWriter pemWriter = new PemWriter(str);
        pemWriter.writeObject(pem);
        pemWriter.close();
        str.close();
        return str.toString();
    }

    /**
     * 加载私钥证书文件
     *
     * @param filename 文件路径
     * @param password 证书密码
     * @return
     * @throws IOException
     * @throws OperatorCreationException
     * @throws PKCSException
     */
    public static PrivateKey loadPrivateFromFile(String filename, String password) throws IOException, OperatorCreationException, PKCSException {
        FileReader fr = new FileReader(filename);
        PEMParser pemReader = new PEMParser(fr);
        Object obj = pemReader.readObject();
        PrivateKey priv = null;
        fr.close();
        pemReader.close();
        if (password != null && password.length() > 0) {
            if (obj instanceof PKCS8EncryptedPrivateKeyInfo) {
                PKCS8EncryptedPrivateKeyInfo epkInfo = (PKCS8EncryptedPrivateKeyInfo) obj;
                InputDecryptorProvider decryptor = new JceOpenSSLPKCS8DecryptorProviderBuilder()
                        .setProvider(BouncyCastleProvider.PROVIDER_NAME)
                        .build(password.toCharArray());
                PrivateKeyInfo pkInfo = epkInfo.decryptPrivateKeyInfo(decryptor);
                priv = CONVERTER.getPrivateKey(pkInfo);
            }
        } else {
            priv = CONVERTER.getPrivateKey((PrivateKeyInfo) obj);
        }
        return priv;
    }

    /**
     * 加载公钥证书文件
     *
     * @param filename 文件路径
     * @return
     * @throws IOException
     * @throws NoSuchProviderException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     */
    public static PublicKey loadPublicFromFile(String filename) throws IOException, NoSuchProviderException, NoSuchAlgorithmException, InvalidKeySpecException {
        FileReader fr = new FileReader(filename);
        PemObject spki = new PemReader(fr).readPemObject();
        fr.close();
        Provider p = Security.getProvider(BouncyCastleProvider.PROVIDER_NAME);
        return KeyFactory.getInstance(EC_VALUE, BouncyCastleProvider.PROVIDER_NAME).generatePublic(new X509EncodedKeySpec(spki.getContent()));
    }

    /**
     * 加载 X509 证书文件
     *
     * @param filename 文件路径
     * @return
     * @throws IOException
     * @throws CertificateException
     * @throws NoSuchProviderException
     */
    public static X509Certificate loadX509CertificateFromFile(String filename) throws IOException, CertificateException,
            NoSuchProviderException {
        FileInputStream in = null;
        in = new FileInputStream(filename);
        CertificateFactory cf = CertificateFactory.getInstance("X.509", BouncyCastleProvider.PROVIDER_NAME);
        return (X509Certificate) cf.generateCertificate(in);
    }

    /**
     * 根据私钥生成公钥
     *
     * @param privateKey 私钥
     * @return
     */
    public static PublicKey derivePublicFromPrivate(PrivateKey privateKey) {
        BCECPrivateKey localECPrivateKey = (BCECPrivateKey) privateKey;
        BigInteger d = localECPrivateKey.getD();
        ECPoint ecpoint = new FixedPointCombMultiplier().multiply(GMNamedCurves.getByName(CURVE_NAME).getG(), d);
        ECPublicKeySpec pubKeySpec = new ECPublicKeySpec(ecpoint, PARAMETER_SPEC);
        return new BCECPublicKey(privateKey.getAlgorithm(), pubKeySpec,
                BouncyCastleProvider.CONFIGURATION);
    }
}
