package backend.test;

import backend.exceptions.*;
import backend.services.UserService;
import backend.model.User;

public class ExceptionTest {
    public static void main(String[] args) {
        System.out.println("=== EXCEPTION HANDLING TEST ===\n");
        
        UserService userService = new UserService();
        
        // Test 1: Invalid email registration
        System.out.println("1. Testing invalid email registration...");
        try {
            User invalidEmailUser = userService.register(
                "testuser", 
                "invalid-email", 
                "password123", 
                "password123",  // Added confirmPassword
                null            // Added courseId (null)
            );
            System.out.println("   ❌ Should have thrown ValidationException!");
        } catch (ValidationException e) {
            System.out.println("   ✅ Correctly threw ValidationException: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("   ❌ Wrong exception type: " + e.getClass().getSimpleName() + ": " + e.getMessage());
        }
        
        // Test 2: Invalid username (too short)
        System.out.println("\n2. Testing invalid username registration...");
        try {
            User invalidUser = userService.register(
                "ab",  // Too short (3-20 chars required)
                "valid@email.com", 
                "password123", 
                "password123",  // Added confirmPassword
                null            // Added courseId (null)
            );
            System.out.println("   ❌ Should have thrown ValidationException!");
        } catch (ValidationException e) {
            System.out.println("   ✅ Correctly threw ValidationException: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("   ❌ Wrong exception type: " + e.getClass().getSimpleName() + ": " + e.getMessage());
        }
        
        // Test 3: Password mismatch
        System.out.println("\n3. Testing password mismatch...");
        try {
            User passwordMismatchUser = userService.register(
                "testuser123", 
                "test@email.com", 
                "password123", 
                "differentpassword",  // Different from password
                null
            );
            System.out.println("   ❌ Should have thrown ValidationException!");
        } catch (ValidationException e) {
            System.out.println("   ✅ Correctly threw ValidationException: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("   ❌ Wrong exception type: " + e.getClass().getSimpleName() + ": " + e.getMessage());
        }
        
        // Test 4: Database exception (if no connection)
        System.out.println("\n4. Testing database exception handling...");
        try {
            // This will throw if database is not connected
            User dbTest = userService.login("nonexistent_user_999999", "wrongpass");
            System.out.println("   ⚠ Got user (unexpected): " + dbTest.getUsername());
        } catch (AuthenticationException e) {
            System.out.println("   ✅ Correctly threw AuthenticationException: " + e.getMessage());
        } catch (DatabaseException e) {
            System.out.println("   ✅ DatabaseException handled: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("   ❌ Unexpected exception: " + e.getClass().getSimpleName() + ": " + e.getMessage());
        }
        
        // Test 5: Test with valid data (should work)
        System.out.println("\n5. Testing valid registration (cleanup after)...");
        try {
            String timestamp = String.valueOf(System.currentTimeMillis());
            String username = "testuser_" + timestamp.substring(timestamp.length() - 6);
            
            User validUser = userService.register(
                username,
                username + "@test.com",
                "ValidPass123",
                "ValidPass123",
                null
            );
            System.out.println("   ✅ Valid registration succeeded: " + validUser.getUsername());
            
            // Clean up: Try to delete or just note it exists
            System.out.println("   ℹ Test user created: " + username + " (ID: " + validUser.getUserId() + ")");
            System.out.println("   ℹ Note: Test user not deleted for inspection");
            
        } catch (Exception e) {
            System.err.println("   ❌ Valid registration failed: " + e.getMessage());
        }
        
        System.out.println("\n" + "=".repeat(50));
        System.out.println("✅ EXCEPTION TESTING COMPLETE!");
        System.out.println("=".repeat(50));
    }
}