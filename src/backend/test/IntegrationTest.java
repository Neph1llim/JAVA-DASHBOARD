package backend.test;

import backend.services.UserService;
import backend.services.NoteService;
import backend.model.User;

public class IntegrationTest {
    public static void main(String[] args) {
        System.out.println("🔌 QUICK CONNECTIVITY TEST\n");
        
        try {
            // Initialize services
            UserService userService = new UserService();
            System.out.println("✅ UserService initialized");
            
            NoteService noteService = new NoteService();
            System.out.println("✅ NoteService initialized");
            
            // Create a valid username that passes validation
            // Format: 3-20 chars, letters, numbers, underscore only
            long timestamp = System.currentTimeMillis();
            String username = "Cy " + timestamp % 10000; 
            System.out.println("📝 Using username: " + username);
            String emailFirst = "sampleemail";
            
            User user = userService.register(
            // Test registration
                emailFirst + "@test.com",
                "TestPass123",
                null
            );
            System.out.println("✅ User registration successful: " + user.getUsername() + 
                             " (ID: " + user.getUserId() + ")");
            
            // Test login
            User loggedIn = userService.login(username, "TestPass123");
            System.out.println("✅ User login successful");
            
            // Test note creation
            backend.model.Note note = noteService.createNote(
                "Connectivity Test Note",
                "Testing service connectivity - " + timestamp,
                false
            );
            System.out.println("✅ Note creation successful: ID=" + note.getNoteId());
            
            // Test note retrieval
            int noteCount = noteService.getNoteCount();
            System.out.println("✅ Note retrieval successful: " + noteCount + " notes");
            
            // Test note search
            java.util.List<backend.model.Note> searchResults = noteService.searchNotes("connectivity");
            System.out.println("✅ Note search successful: " + searchResults.size() + " results");
            
            // Test note deletion
            noteService.deleteNote(note.getNoteId());
            System.out.println("✅ Note deletion successful");
            
            // Cleanup
            noteService.emptyTrash();
            userService.logout();
            
            System.out.println("\n🎉 ALL CONNECTIVITY TESTS PASSED!");
            System.out.println("Services are properly connected to database.");
            
        } catch (Exception e) {
            System.err.println("\n❌ CONNECTIVITY TEST FAILED: " + e.getMessage());
            e.printStackTrace();
        }
    }
}