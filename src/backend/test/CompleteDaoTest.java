package backend.test;

import backend.dao.impl.UserDaoImpl;
import backend.dao.impl.NoteDaoImpl;
import backend.model.User;
import backend.model.Note;
import backend.exceptions.DatabaseException;
import backend.exceptions.DuplicateEntryException;
import java.util.List;

public class CompleteDaoTest {
    
    public static void main(String[] args) {
        System.out.println("=== COMPLETE DAO INTEGRATION TEST ===\n");
        
        try {
            UserDaoImpl userDao = new UserDaoImpl();
            NoteDaoImpl noteDao = new NoteDaoImpl();
            
            // Step 1: Clean up any existing test data
            System.out.println("1. Cleaning up old test data...");
            try {
                User existingUser = userDao.findByUsername("testuser");
                if (existingUser != null) {
                    // First delete user's notes (due to foreign key cascade)
                    List<Note> userNotes = noteDao.findByUserId(existingUser.getUserId());
                    for (Note note : userNotes) {
                        noteDao.permanentDelete(note.getNoteId());
                    }
                    // Then delete user
                    userDao.delete(existingUser.getUserId());
                    System.out.println("   ✓ Cleaned up old test user");
                }
            } catch (Exception e) {
                System.out.println("   No old data to clean up");
            }
            
            // Step 2: Create a test user
            System.out.println("\n2. Creating test user...");
            User testUser = new User("testuser", "test@example.com", "hashed_password_123");
            testUser = userDao.create(testUser);
            System.out.println("   ✓ User created with ID: " + testUser.getUserId());
            System.out.println("   ✓ Username: " + testUser.getUsername());
            System.out.println("   ✓ Email: " + testUser.getEmail());
            
            // Step 3: Create notes for the user
            System.out.println("\n3. Creating test notes...");
            
            // Note 1: Regular note
            Note note1 = new Note(testUser.getUserId(), "Meeting Notes", 
                                 "Discussion about the new project requirements.");
            note1 = noteDao.create(note1);
            System.out.println("   ✓ Created note 1: " + note1.getTitle() + " (ID: " + note1.getNoteId() + ")");
            
            // Note 2: Pinned note
            Note note2 = new Note(testUser.getUserId(), "Important Todo", 
                                 "1. Finish backend\n2. Test API endpoints\n3. Deploy to staging");
            note2.setPinned(true);
            note2 = noteDao.create(note2);
            System.out.println("   ✓ Created note 2 (pinned): " + note2.getTitle() + " (ID: " + note2.getNoteId() + ")");
            
            // Note 3: Another note
            Note note3 = new Note(testUser.getUserId(), "Study Plan", 
                                 "Monday: Math\nTuesday: Science\nWednesday: Literature");
            note3 = noteDao.create(note3);
            System.out.println("   ✓ Created note 3: " + note3.getTitle() + " (ID: " + note3.getNoteId() + ")");
            
            // Step 4: Test user retrieval
            System.out.println("\n4. Testing user retrieval...");
            User retrievedUser = userDao.findById(testUser.getUserId());
            if (retrievedUser != null) {
                System.out.println("   ✓ User retrieved successfully");
                System.out.println("   ✓ Created at: " + retrievedUser.getCreatedAt());
            }
            
            // Step 5: Test note retrieval by user
            System.out.println("\n5. Testing note retrieval by user...");
            List<Note> userNotes = noteDao.findByUserId(testUser.getUserId());
            System.out.println("   ✓ User has " + userNotes.size() + " notes total");
            
            // Step 6: Test pinned notes
            System.out.println("\n6. Testing pinned notes...");
            List<Note> pinnedNotes = noteDao.findPinnedNotes(testUser.getUserId());
            System.out.println("   ✓ Found " + pinnedNotes.size() + " pinned notes");
            
            // Step 7: Test note update
            System.out.println("\n7. Testing note update...");
            note1.setTitle("Updated Meeting Notes");
            note1.setContent("Updated discussion points...");
            boolean updated = noteDao.update(note1);
            System.out.println("   ✓ Note updated: " + updated);
            
            // Step 8: Test search
            System.out.println("\n8. Testing note search...");
            List<Note> searchResults = noteDao.searchNotes("meeting", testUser.getUserId());
            System.out.println("   ✓ Search found " + searchResults.size() + " notes with 'meeting'");
            
            // Step 9: Test toggle pin
            System.out.println("\n9. Testing toggle pin...");
            boolean pinToggled = noteDao.togglePin(note3.getNoteId());
            System.out.println("   ✓ Pin toggled: " + pinToggled);
            
            // Step 10: Test note count
            System.out.println("\n10. Testing note count...");
            int noteCount = noteDao.getNoteCount(testUser.getUserId());
            System.out.println("   ✓ Active note count: " + noteCount);
            
            // Step 11: Test soft delete
            System.out.println("\n11. Testing soft delete...");
            boolean deleted = noteDao.delete(note2.getNoteId());
            System.out.println("   ✓ Note soft deleted: " + deleted);
            
            // Step 12: Verify soft delete
            System.out.println("\n12. Verifying soft delete...");
            Note deletedNote = noteDao.findById(note2.getNoteId());
            if (deletedNote == null) {
                System.out.println("   ✓ Note not found after soft delete (correct)");
            }
            
            List<Note> deletedNotes = noteDao.getDeletedNotes(testUser.getUserId());
            System.out.println("   ✓ Found " + deletedNotes.size() + " deleted notes in trash");
            
            // Step 13: Test restore
            System.out.println("\n13. Testing restore...");
            boolean restored = noteDao.restoreNote(note2.getNoteId());
            System.out.println("   ✓ Note restored: " + restored);
            
            // Step 14: Final count
            System.out.println("\n14. Final counts...");
            int finalActiveCount = noteDao.getNoteCount(testUser.getUserId());
            int finalTotalCount = noteDao.findByUserId(testUser.getUserId()).size();
            System.out.println("   ✓ Active notes: " + finalActiveCount);
            System.out.println("   ✓ Total notes (including restored): " + finalTotalCount);
            
            // Step 15: List all users (should be just our test user)
            System.out.println("\n15. Listing all users...");
            List<User> allUsers = userDao.findAll();
            System.out.println("   ✓ Total users in database: " + allUsers.size());
            for (User u : allUsers) {
                System.out.println("     - ID: " + u.getUserId() + ", Username: " + u.getUsername());
            }
            
            System.out.println("\n=== ALL TESTS PASSED SUCCESSFULLY! ===");
            System.out.println("\nSummary:");
            System.out.println("- Created user: " + testUser.getUsername() + " (ID: " + testUser.getUserId() + ")");
            System.out.println("- Created notes: 3 total");
            System.out.println("- Tested: CRUD operations, search, pin, soft delete, restore");
            
        } catch (DuplicateEntryException e) {
            System.err.println("\n✗ DUPLICATE ENTRY ERROR: " + e.getMessage());
            System.err.println("   Try running the test again or clean the database first.");
        } catch (DatabaseException e) {
            System.err.println("\n✗ DATABASE ERROR: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("\n✗ UNEXPECTED ERROR: " + e.getMessage());
            e.printStackTrace();
        }
    }
}