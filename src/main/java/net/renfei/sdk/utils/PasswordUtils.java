package net.renfei.sdk.utils;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

/**
 * 密码工具类
 *
 * @author RenFei
 */
public class PasswordUtils {
    /**
     * 加密方式
     */
    private static final String PBKDF2_ALGORITHM = "PBKDF2WithHmacSHA1";
    private static final String PBKDF2_SHA_512_ALGORITHM = "PBKDF2WithHmacSHA512";
    /* 以下参数可以根据实际情况修改 */
    /**
     * 盐值长度
     */
    private static final int SALT_BYTE_SIZE = 24;
    /**
     * 导出秘钥长度
     */
    private static final int HASH_BYTE_SIZE = 18;
    /**
     * 迭代数
     */
    private static final int PBKDF2_ITERATIONS = 64000;
    /* 以下参数不要修改 */
    private static final int HASH_SECTIONS = 5;
    private static final int HASH_ALGORITHM_INDEX = 0;
    private static final int ITERATION_INDEX = 1;
    private static final int HASH_SIZE_INDEX = 2;
    private static final int SALT_INDEX = 3;
    private static final int PBKDF2_INDEX = 4;

    /**
     * 创建一个密文密码
     *
     * @param password 明文密码
     * @return
     * @throws CannotPerformOperationException
     */
    public static String createHash(String password)
            throws CannotPerformOperationException {
        return createHash(password.toCharArray());
    }

    /**
     * 创建一个密文密码
     *
     * @param password 明文密码字符数组
     * @return
     * @throws CannotPerformOperationException
     */
    public static String createHash(char[] password)
            throws CannotPerformOperationException {
        // Generate a random salt
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[SALT_BYTE_SIZE];
        random.nextBytes(salt);

        // Hash the password
        byte[] hash = pbkdf2(password, salt, PBKDF2_ITERATIONS, PBKDF2_SHA_512_ALGORITHM, HASH_BYTE_SIZE);
        int hashSize = hash.length;

        // format: algorithm:iterations:hashSize:salt:hash
        String parts = "sha256:" +
                PBKDF2_ITERATIONS +
                ":" + hashSize +
                ":" +
                toBase64(salt) +
                ":" +
                toBase64(hash);
        return parts;
    }

    /**
     * 验证密码
     *
     * @param password    明文密码
     * @param correctHash 密文密码
     * @return 是否符合
     */
    public static boolean verifyPassword(String password, String correctHash) {
        try {
            return verifyPassword(password.toCharArray(), correctHash);
        } catch (Exception e) {
            return false;
        }

    }

    /**
     * 验证密码
     *
     * @param password    明文密码字符数组
     * @param correctHash 密文密码
     * @return 是否符合
     */
    public static boolean verifyPassword(char[] password, String correctHash)
            throws CannotPerformOperationException, InvalidHashException {
        // Decode the hash into its parameters
        String[] params = correctHash.split(":");
        if (params.length != HASH_SECTIONS) {
            throw new InvalidHashException(
                    "Fields are missing from the password hash."
            );
        }

        int iterations = 0;
        try {
            iterations = Integer.parseInt(params[ITERATION_INDEX]);
        } catch (NumberFormatException ex) {
            throw new InvalidHashException(
                    "Could not parse the iteration count as an integer.",
                    ex
            );
        }

        if (iterations < 1) {
            throw new InvalidHashException(
                    "Invalid number of iterations. Must be >= 1."
            );
        }


        byte[] salt = null;
        try {
            salt = fromBase64(params[SALT_INDEX]);
        } catch (IllegalArgumentException ex) {
            throw new InvalidHashException(
                    "Base64 decoding of salt failed.",
                    ex
            );
        }

        byte[] hash = null;
        try {
            hash = fromBase64(params[PBKDF2_INDEX]);
        } catch (IllegalArgumentException ex) {
            throw new InvalidHashException(
                    "Base64 decoding of pbkdf2 output failed.",
                    ex
            );
        }


        int storedHashSize = 0;
        try {
            storedHashSize = Integer.parseInt(params[HASH_SIZE_INDEX]);
        } catch (NumberFormatException ex) {
            throw new InvalidHashException(
                    "Could not parse the hash size as an integer.",
                    ex
            );
        }

        if (storedHashSize != hash.length) {
            throw new InvalidHashException(
                    "Hash length doesn't match stored hash length."
            );
        }

        byte[] testHash = null;
        if ("sha1".equals(params[HASH_ALGORITHM_INDEX])) {
            testHash = pbkdf2(password, salt, iterations, PBKDF2_ALGORITHM, hash.length);
        } else if ("sha256".equals(params[HASH_ALGORITHM_INDEX])) {
            testHash = pbkdf2(password, salt, iterations, PBKDF2_SHA_512_ALGORITHM, hash.length);
        } else {
            throw new CannotPerformOperationException(
                    "Unsupported hash type."
            );
        }
        // Compare the hashes in constant time. The password is correct if
        // both hashes match.
        return slowEquals(hash, testHash);
    }

    private static boolean slowEquals(byte[] a, byte[] b) {
        int diff = a.length ^ b.length;
        for (int i = 0; i < a.length && i < b.length; i++) {
            diff |= a[i] ^ b[i];
        }
        return diff == 0;
    }

    private static byte[] pbkdf2(char[] password, byte[] salt, int iterations, String algorithm, int bytes)
            throws CannotPerformOperationException {
        try {
            PBEKeySpec spec = new PBEKeySpec(password, salt, iterations, bytes * 8);
            SecretKeyFactory skf = SecretKeyFactory.getInstance(algorithm);
            return skf.generateSecret(spec).getEncoded();
        } catch (NoSuchAlgorithmException ex) {
            throw new CannotPerformOperationException(
                    "Hash algorithm not supported.",
                    ex
            );
        } catch (InvalidKeySpecException ex) {
            throw new CannotPerformOperationException(
                    "Invalid key spec.",
                    ex
            );
        }
    }

    private static byte[] fromBase64(String hex)
            throws IllegalArgumentException {
        return Base64.getDecoder().decode(hex);
    }

    private static String toBase64(byte[] array) {
        return Base64.getEncoder().encodeToString(array);
    }

    @SuppressWarnings("serial")
    static public class InvalidHashException extends Exception {
        public InvalidHashException(String message) {
            super(message);
        }

        public InvalidHashException(String message, Throwable source) {
            super(message, source);
        }
    }

    @SuppressWarnings("serial")
    static public class CannotPerformOperationException extends Exception {
        public CannotPerformOperationException(String message) {
            super(message);
        }

        public CannotPerformOperationException(String message, Throwable source) {
            super(message, source);
        }
    }
}
