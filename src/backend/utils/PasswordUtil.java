package backend.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class PasswordUtil {
    private static final int SALT_LENGTH = 16;
    private static final int HASH_ITERATIONS = 10000;
    
    /**
     * Hash a password with salt using SHA-256
     */
    public static String hashPassword(String password) {
        try {
            // Generate random salt
            SecureRandom random = new SecureRandom();
            byte[] salt = new byte[SALT_LENGTH];
            random.nextBytes(salt);
            
            // Hash password with salt
            byte[] hash = hashWithSalt(password, salt, HASH_ITERATIONS);
            
            // Combine salt and hash
            byte[] combined = new byte[salt.length + hash.length];
            System.arraycopy(salt, 0, combined, 0, salt.length);
            System.arraycopy(hash, 0, combined, salt.length, hash.length);
            
            return Base64.getEncoder().encodeToString(combined);
            
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Password hashing algorithm not available", e);
        }
    }
    
    /**
     * Verify a password against a stored hash
     */
    public static boolean checkPassword(String password, String storedHash) {
        try {
            if (storedHash == null || storedHash.isEmpty()) {
                return false;
            }
            
            // Decode the stored hash
            byte[] combined = Base64.getDecoder().decode(storedHash);
            
            // Extract salt (first SALT_LENGTH bytes)
            byte[] salt = new byte[SALT_LENGTH];
            System.arraycopy(combined, 0, salt, 0, SALT_LENGTH);
            
            // Extract stored hash (remaining bytes)
            byte[] storedHashBytes = new byte[combined.length - SALT_LENGTH];
            System.arraycopy(combined, SALT_LENGTH, storedHashBytes, 0, storedHashBytes.length);
            
            // Hash the provided password with the same salt
            byte[] testHash = hashWithSalt(password, salt, HASH_ITERATIONS);
            
            // Compare the hashes
            return MessageDigest.isEqual(storedHashBytes, testHash);
            
        } catch (Exception e) {
            return false;
        }
    }
    
    /**
     * Simple password strength checker
     */
    public static boolean isStrongPassword(String password) {
        if (password == null || password.length() < 8) {
            return false;
        }
        
        boolean hasUpper = false;
        boolean hasLower = false;
        boolean hasDigit = false;
        
        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) hasUpper = true;
            if (Character.isLowerCase(c)) hasLower = true;
            if (Character.isDigit(c)) hasDigit = true;
        }
        
        // Require at least one uppercase, one lowercase, and one digit
        return hasUpper && hasLower && hasDigit;
    }
    
    /**
     * Generate a random password (for password reset features)
     */
    public static String generateRandomPassword(int length) {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        SecureRandom random = new SecureRandom();
        StringBuilder password = new StringBuilder(length);
        
        for (int i = 0; i < length; i++) {
            password.append(chars.charAt(random.nextInt(chars.length())));
        }
        
        return password.toString();
    }
    
    /**
     * Helper method to hash password with salt and iterations
     */
    private static byte[] hashWithSalt(String password, byte[] salt, int iterations) 
            throws NoSuchAlgorithmException {
        
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        digest.update(salt);
        digest.update(password.getBytes());
        
        byte[] hash = digest.digest();
        
        // Apply multiple iterations for added security
        for (int i = 1; i < iterations; i++) {
            digest.reset();
            hash = digest.digest(hash);
        }
        
        return hash;
    }
}