package backend.dao.interfaces;

import backend.model.Note;
import backend.exceptions.DatabaseException;
import java.util.List;

public interface NoteDao {
    // CRUD Operations
    Note create(Note note) throws DatabaseException;
    Note findById(int noteId) throws DatabaseException;
    List<Note> findByUserId(int userId) throws DatabaseException;
    boolean update(Note note) throws DatabaseException;
    boolean delete(int noteId) throws DatabaseException; // Soft delete
    
    // Special queries for your Note model
    List<Note> findPinnedNotes(int userId) throws DatabaseException;
    List<Note> searchNotes(String keyword, int userId) throws DatabaseException;
    
    // Pin functionality
    boolean togglePin(int noteId) throws DatabaseException;
    
    // Trash/restore functionality
    List<Note> getDeletedNotes(int userId) throws DatabaseException;
    boolean restoreNote(int noteId) throws DatabaseException;
    boolean permanentDelete(int noteId) throws DatabaseException;
    
    // Statistics
    int getNoteCount(int userId) throws DatabaseException;
    List<Note> getRecentNotes(int userId, int limit) throws DatabaseException;
}