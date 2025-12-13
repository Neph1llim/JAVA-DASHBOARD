package backend.test;

import backend.dao.impl.UserDaoImpl;
import backend.dao.impl.NoteDaoImpl;
import backend.model.User;
import backend.model.Note;

public class QuickFixTest {
    
    public static void main(String[] args) {
        System.out.println("=== QUICK FIX TEST ===\n");
        
        try {
            // Test 1: Create User
            System.out.println("1. Testing User Creation...");
            UserDaoImpl userDao = new UserDaoImpl();
            User user = new User("testuser", "test@example.com", "hashed123");
            user = userDao.create(user);
            System.out.println("   ✓ User created: ID=" + user.getUserId(  ));
            
            // Test 2: Find User
            User foundUser = userDao.findById(user.getUserId());
            System.out.println("   ✓ User found: " + foundUser.getUsername());
            
            // Test 3: Create Note
            System.out.println("\n2. Testing Note Creation...");
            NoteDaoImpl noteDao = new NoteDaoImpl();
            Note note = new Note(user.getUserId(), "Test Note", "Content here");
            note = noteDao.create(note);
            System.out.println("   ✓ Note created: ID=" + note.getNoteId());
            
            // Test 4: Find Note
            Note foundNote = noteDao.findById(note.getNoteId());
            System.out.println("   ✓ Note found: " + foundNote.getTitle());
            
            // Test 5: Search Users
            System.out.println("\n3. Testing User Search...");
            var searchResults = userDao.searchUsers("test");
            System.out.println("   ✓ Found " + searchResults.size() + " users");
            
            System.out.println("\n=== ALL TESTS PASSED! ===");
            
        } catch (Exception e) {
            System.err.println("\n✗ TEST FAILED: " + e.getMessage());
            e.printStackTrace();
        }
    }
}