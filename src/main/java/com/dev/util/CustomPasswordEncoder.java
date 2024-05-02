package com.dev.util;

import org.springframework.security.crypto.password.PasswordEncoder;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

public class CustomPasswordEncoder implements PasswordEncoder {
    private static final int ITERATIONS = 10000;
    private static final int KEY_LENGTH = 256;
    private static final int SALT_LENGTH = 32;

    private static byte[] generateSalt() {
        byte[] salt = new byte[SALT_LENGTH];
        SecureRandom random = new SecureRandom();
        random.nextBytes(salt);
        return salt;
    }

    @Override
    public String encode(CharSequence rawPassword) {
        byte[] salt = generateSalt();
       return hashPassword(rawPassword.toString(),salt)+ "$" +  Base64.getEncoder().encodeToString(salt);
    }

    private static String hashPassword(String password, byte[] salt) {
        char[] passwordChars = password.toCharArray();
        PBEKeySpec spec = new PBEKeySpec(passwordChars, salt, ITERATIONS, KEY_LENGTH);
        try {
            SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            byte[] hash = skf.generateSecret(spec).getEncoded();
            return Base64.getEncoder().encodeToString(hash);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        String[] parts = encodedPassword.split("\\$");
        String storedHash = parts[0];
        String saltString = parts[1];
        byte[] salt = Base64.getDecoder().decode(saltString);
        String newHash = hashPassword(rawPassword.toString(), salt);
        return newHash.equals(storedHash);
    }
}
