package backend.utils;

import java.util.regex.Pattern;

public class ValidationUtil {
    private static final Pattern EMAIL_PATTERN = 
        Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");
    private static final Pattern USERNAME_PATTERN = 
        Pattern.compile("^[a-zA-Z0-9_ ]{3,20}$");
    
    /**
     * Validate email address
     */
    public static boolean isValidEmail(String email) {
        if (email == null || email.trim().isEmpty()) return false;
        return EMAIL_PATTERN.matcher(email.trim()).matches();
    }
    
    /**
     * Validate username (3-20 chars, alphanumeric and underscore only)
     */
    public static boolean isValidUsername(String username) {
        if (username == null || username.trim().isEmpty()) return false;
        return USERNAME_PATTERN.matcher(username.trim()).matches();
    }
    
    /**
     * Validate note title (not empty, max 255 chars)
     */
    public static boolean isValidNoteTitle(String title) {
        return title != null && !title.trim().isEmpty() && title.trim().length() <= 255;
    }
    
    /**
     * Validate string is not null or empty
     */
    public static boolean isNotEmpty(String value) {
        return value != null && !value.trim().isEmpty();
    }
    
    /**
     * Validate string length is within range
     */
    public static boolean isValidLength(String value, int min, int max) {
        if (value == null) return false;
        int length = value.trim().length();
        return length >= min && length <= max;
    }
    
    /**
     * Validate integer is positive
     */
    public static boolean isPositiveInteger(int value) {
        return value > 0;
    }
    
    /**
     * Validate integer is within range
     */
    public static boolean isInRange(int value, int min, int max) {
        return value >= min && value <= max;
    }
    
    /**
     * Trim and return null if empty
     */
    public static String trimToNull(String value) {
        if (value == null) return null;
        String trimmed = value.trim();
        return trimmed.isEmpty() ? null : trimmed;
    }
    
    /**
     * Capitalize first letter of each word
     */
    public static String capitalizeWords(String value) {
        if (value == null || value.trim().isEmpty()) return value;
        
        String[] words = value.trim().split("\\s+");
        StringBuilder result = new StringBuilder();
        
        for (String word : words) {
            if (!word.isEmpty()) {
                result.append(Character.toUpperCase(word.charAt(0)))
                      .append(word.substring(1).toLowerCase())
                      .append(" ");
            }
        }
        
        return result.toString().trim();
    }
}