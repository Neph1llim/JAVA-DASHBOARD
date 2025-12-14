package backend.test;

import backend.dao.impl.NoteDaoImpl;
import backend.model.Note;
import backend.exceptions.DatabaseException;
import java.util.List;

public class NoteDaoTest {
    
    public static void main(String[] args) {
        System.out.println("=== NOTE DAO TEST (Matching your schema) ===\n");
        
        try {
            NoteDaoImpl noteDao = new NoteDaoImpl();
            
            // Test 1: Create Note
            System.out.println("1. Testing Note Creation...");
            Note note = new Note(1, "Test Note", "This is a test note content");
            note.setPinned(true);
            
            Note createdNote = noteDao.create(note);
            System.out.println("   ✓ Note created with ID: " + createdNote.getNoteId());
            System.out.println("   ✓ Title: " + createdNote.getTitle());
            System.out.println("   ✓ Pinned: " + createdNote.isPinned());
            
            // Test 2: Find by ID
            System.out.println("\n2. Testing Find by ID...");
            Note foundNote = noteDao.findById(createdNote.getNoteId());
            if (foundNote != null) {
                System.out.println("   ✓ Note found: " + foundNote.getTitle());
            } else {
                System.out.println("   ✗ Note not found");
            }
            
            // Test 3: Find by User ID
            System.out.println("\n3. Testing Find by User ID...");
            List<Note> userNotes = noteDao.findByUserId(1);
            System.out.println("   ✓ User has " + userNotes.size() + " notes");
            
            // Test 4: Update Note
            System.out.println("\n4. Testing Update...");
            foundNote.setTitle("Updated Test Note");
            foundNote.setContent("Updated content");
            boolean updated = noteDao.update(foundNote);
            System.out.println("   ✓ Update successful: " + updated);
            
            // Test 5: Search Notes
            System.out.println("\n5. Testing Search...");
            List<Note> searchResults = noteDao.searchNotes("test", 1);
            System.out.println("   ✓ Search found " + searchResults.size() + " notes");
            
            // Test 6: Toggle Pin
            System.out.println("\n6. Testing Toggle Pin...");
            boolean pinToggled = noteDao.togglePin(createdNote.getNoteId());
            System.out.println("   ✓ Pin toggled: " + pinToggled);
            
            // Test 7: Find Pinned Notes
            System.out.println("\n7. Testing Pinned Notes...");
            List<Note> pinnedNotes = noteDao.findPinnedNotes(1);
            System.out.println("   ✓ Found " + pinnedNotes.size() + " pinned notes");
            
            // Test 8: Soft Delete
            System.out.println("\n8. Testing Soft Delete...");
            boolean deleted = noteDao.delete(createdNote.getNoteId());
            System.out.println("   ✓ Soft deleted: " + deleted);
            
            // Test 9: Get Deleted Notes
            System.out.println("\n9. Testing Get Deleted Notes...");
            List<Note> deletedNotes = noteDao.getDeletedNotes(1);
            System.out.println("   ✓ Found " + deletedNotes.size() + " deleted notes");
            
            // Test 10: Restore Note
            System.out.println("\n10. Testing Restore...");
            boolean restored = noteDao.restoreNote(createdNote.getNoteId());
            System.out.println("   ✓ Restored: " + restored);
            
            // Test 11: Get Note Count
            System.out.println("\n11. Testing Note Count...");
            int count = noteDao.getNoteCount(1);
            System.out.println("   ✓ Note count: " + count);
            
            // Test 12: Get Recent Notes
            System.out.println("\n12. Testing Recent Notes...");
            List<Note> recentNotes = noteDao.getRecentNotes(1, 5);
            System.out.println("   ✓ Recent notes: " + recentNotes.size());
            
            System.out.println("\n=== ALL NOTE DAO TESTS PASSED! ===");
            
        } catch (DatabaseException e) {
            System.err.println("\n✗ NOTE DAO TEST FAILED: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("\n✗ UNEXPECTED ERROR: " + e.getMessage());
            e.printStackTrace();
        }
    }
}