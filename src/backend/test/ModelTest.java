package backend.test;

import backend.model.User;
import backend.model.Note;

public class ModelTest {
    public static void main(String[] args) {
        System.out.println("=== MODEL CREATION TEST ===\n");
        
        // Test User model
        System.out.println("1. Testing User model...");
        User user = new User("test@example.com", "testuser", "hashedpassword123");
        user.setUserId(1);
        System.out.println("   User created: " + user);
        System.out.println("   Email: " + user.getEmail());
        System.out.println("   Username: " + user.getUsername());
        System.out.println("   ✓ User model works\n");
        
        // Test Note model
        System.out.println("2. Testing Note model...");
        Note note = new Note(1, "First Note", "This is note content");
        note.setNoteId(1);
        System.out.println("   Note created: " + note.getTitle());
        System.out.println("   Content: " + note.getContent());
        System.out.println("   User ID: " + note.getUserId());
        System.out.println("   ✓ Note model works\n");
        
        System.out.println("✅ MODELS READY FOR DAO IMPLEMENTATION");
        System.out.println("\nNext: Create DAO interfaces and implementations");
    }
}