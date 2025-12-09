package backend.utils;

public class SecurityUtils {
    
    // Simple password hashing (use BCrypt in production)
    public static String hashPassword(String password) {
        if (password == null) return null;
        // Simple hash for now - replace with BCrypt.hashpw(password, BCrypt.gensalt())
        return Integer.toString(password.hashCode());
    }
    
    public static boolean verifyPassword(String password, String hashedPassword) {
        if (password == null || hashedPassword == null) return false;
        return hashPassword(password).equals(hashedPassword);
    }
    
    // Validate email format
    public static boolean isValidEmail(String email) {
        if (email == null) return false;
        return email.contains("@") && email.contains(".");
    }
    
    // Validate username (alphanumeric, 3-20 chars)
    public static boolean isValidUsername(String username) {
        if (username == null) return false;
        return username.matches("^[a-zA-Z0-9_]{3,20}$");
    }
}
