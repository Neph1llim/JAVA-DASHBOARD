package backend.test;

import backend.utils.PasswordUtil;
import backend.utils.ValidationUtil;
import backend.utils.SessionManager;
import backend.model.User;

public class UtilsTest {
    
    public static void main(String[] args) {
        System.out.println("=== UTILS TEST ===\n");
        
        // Test PasswordUtil
        System.out.println("1. Testing PasswordUtil...");
        String password = "StrongPass123";
        String hash = PasswordUtil.hashPassword(password);
        System.out.println("   ✓ Password hashed: " + hash.substring(0, 20) + "...");
        
        boolean passwordValid = PasswordUtil.checkPassword(password, hash);
        System.out.println("   ✓ Password verification: " + passwordValid);
        
        boolean passwordInvalid = PasswordUtil.checkPassword("wrongpass", hash);
        System.out.println("   ✓ Wrong password rejected: " + !passwordInvalid);
        
        boolean isStrong = PasswordUtil.isStrongPassword(password);
        System.out.println("   ✓ Password strength check: " + isStrong);
        
        String randomPass = PasswordUtil.generateRandomPassword(12);
        System.out.println("   ✓ Random password generated: " + randomPass);
        
        // Test ValidationUtil
        System.out.println("\n2. Testing ValidationUtil...");
        System.out.println("   ✓ Valid email: " + ValidationUtil.isValidEmail("test@example.com"));
        System.out.println("   ✓ Invalid email: " + !ValidationUtil.isValidEmail("invalid"));
        System.out.println("   ✓ Valid username: " + ValidationUtil.isValidUsername("john_doe123"));
        System.out.println("   ✓ Invalid username: " + !ValidationUtil.isValidUsername("ab"));
        System.out.println("   ✓ Valid note title: " + ValidationUtil.isValidNoteTitle("My Note"));
        System.out.println("   ✓ Invalid note title: " + !ValidationUtil.isValidNoteTitle(""));
        
        // Test SessionManager
        System.out.println("\n3. Testing SessionManager...");
        User testUser = new User("sessionuser", "session@example.com", "hash123");
        testUser.setUserId(999);
        
        SessionManager.setCurrentUser(testUser);
        System.out.println("   ✓ Session set");
        System.out.println("   ✓ User logged in: " + SessionManager.isLoggedIn());
        System.out.println("   ✓ Current user ID: " + SessionManager.getCurrentUserId());
        System.out.println("   ✓ Current username: " + SessionManager.getCurrentUser());
        
        SessionManager.clearSession();
        System.out.println("   ✓ Session cleared");
        System.out.println("   ✓ User logged out: " + !SessionManager.isLoggedIn());
        
        System.out.println("\n=== ALL UTILS TESTS PASSED! ===");
    }
}