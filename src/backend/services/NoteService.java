package backend.services;

import backend.dao.interfaces.NoteDao;
import backend.dao.impl.NoteDaoImpl;
import backend.model.Note;
import backend.utils.ValidationUtil;
import backend.utils.SessionManager;
import backend.exceptions.AuthenticationException;
import backend.exceptions.ValidationException;
import backend.exceptions.DatabaseException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;

public class NoteService {
    private final NoteDao noteDao;
    
    public NoteService() {
        this.noteDao = new NoteDaoImpl();
    }
    
    /**
     * Create a new note WITHOUT category
     */
    public Note createNote(String title, String content, boolean isPinned) 
            throws AuthenticationException, ValidationException, DatabaseException {
        
        // Check authentication
        if (!SessionManager.isLoggedIn()) {
            throw new AuthenticationException("You must be logged in to create a note");
        }
        
        // Validate inputs
        if (!ValidationUtil.isValidNoteTitle(title)) {
            throw new ValidationException("Title is required and must be 255 characters or less");
        }
        
        if (content == null || content.trim().isEmpty()) {
            throw new ValidationException("Content cannot be empty");
        }
        
        try {
            // Create note WITHOUT category parameter
            Note note = new Note(
                SessionManager.getCurrentUserId(), 
                title.trim(), 
                content.trim()
            );
            note.setPinned(isPinned);
            
            return noteDao.create(note);
            
        } catch (Exception e) {
            throw new DatabaseException("Failed to create note: " + e.getMessage(), e);
        }
    }
    
    /**
     * Get note by ID (with permission check)
     */
    public Note getNoteById(int noteId) 
        throws AuthenticationException, ValidationException, DatabaseException {
        
        // Check authentication
        if (!SessionManager.isLoggedIn()) {
            throw new AuthenticationException("You must be logged in to view notes");
        }
        
        try {
            Note note = noteDao.findById(noteId);
            
            if (note == null) {
                throw new ValidationException("Note not found");
            }
            
            // Check ownership
            if (note.getUserId() != SessionManager.getCurrentUserId()) {
                throw new AuthenticationException("You don't have permission to view this note");
            }
            
            return note;
            
        } catch (ValidationException | AuthenticationException e) {
            throw e;
        } catch (Exception e) {
            throw new DatabaseException("Failed to get note: " + e.getMessage(), e);
        }
    }
    
    /**
     * Get all notes for current user
     */
    public List<Note> getUserNotes() 
            throws AuthenticationException, DatabaseException {
        
        if (!SessionManager.isLoggedIn()) {
            throw new AuthenticationException("You must be logged in to view notes");
        }
        
        try {
            return noteDao.findByUserId(SessionManager.getCurrentUserId());
        } catch (Exception e) {
            throw new DatabaseException("Failed to get user notes: " + e.getMessage(), e);
        }
    }
    
    /**
     * Get pinned notes for current user
     */
    public List<Note> getPinnedNotes() 
            throws AuthenticationException, DatabaseException {
        
        if (!SessionManager.isLoggedIn()) {
            throw new AuthenticationException("You must be logged in to view notes");
        }
        
        try {
            return noteDao.findPinnedNotes(SessionManager.getCurrentUserId());
        } catch (Exception e) {
            throw new DatabaseException("Failed to get pinned notes: " + e.getMessage(), e);
        }
    }
    
    /**
     * Update a note WITHOUT category
     */
    public boolean updateNote(int noteId, String title, String content, boolean isPinned) 
            throws AuthenticationException, ValidationException, DatabaseException {
        
        if (!SessionManager.isLoggedIn()) {
            throw new AuthenticationException("You must be logged in to update notes");
        }
        
        try {
            // Check ownership
            Note existingNote = noteDao.findById(noteId);
            if (existingNote == null) {
                throw new ValidationException("Note not found");
            }
            
            if (existingNote.getUserId() != SessionManager.getCurrentUserId()) {
                throw new AuthenticationException("You don't have permission to update this note");
            }
            
            // Validate inputs
            if (!ValidationUtil.isValidNoteTitle(title)) {
                throw new ValidationException("Title is required and must be 255 characters or less");
            }
            
            if (content == null || content.trim().isEmpty()) {
                throw new ValidationException("Content cannot be empty");
            }
            
            // Update note
            existingNote.setTitle(title.trim());
            existingNote.setContent(content.trim());
            existingNote.setPinned(isPinned);
            existingNote.setUpdatedAt(LocalDateTime.now());
            
            return noteDao.update(existingNote);
            
        } catch (ValidationException | AuthenticationException e) {
            throw e;
        } catch (Exception e) {
            throw new DatabaseException("Failed to update note: " + e.getMessage(), e);
        }
    }
    
    /**
     * Delete a note (soft delete)
     */
    public boolean deleteNote(int noteId) 
            throws AuthenticationException, DatabaseException {
        
        if (!SessionManager.isLoggedIn()) {
            throw new AuthenticationException("You must be logged in to delete notes");
        }
        
        try {
            // Check ownership
            Note note = noteDao.findById(noteId);
            if (note != null && note.getUserId() != SessionManager.getCurrentUserId()) {
                throw new AuthenticationException("You don't have permission to delete this note");
            }
            
            return noteDao.delete(noteId);
            
        } catch (AuthenticationException e) {
            throw e;
        } catch (Exception e) {
            throw new DatabaseException("Failed to delete note: " + e.getMessage(), e);
        }
    }
    
    /**
     * Toggle pin status of a note
     */
    public boolean togglePin(int noteId) 
            throws AuthenticationException, DatabaseException {
        
        if (!SessionManager.isLoggedIn()) {
            throw new AuthenticationException("You must be logged in to modify notes");
        }
        
        try {
            // Check ownership
            Note note = noteDao.findById(noteId);
            if (note != null && note.getUserId() != SessionManager.getCurrentUserId()) {
                throw new AuthenticationException("You don't have permission to modify this note");
            }
            
            return noteDao.togglePin(noteId);
            
        } catch (AuthenticationException e) {
            throw e;
        } catch (Exception e) {
            throw new DatabaseException("Failed to toggle pin: " + e.getMessage(), e);
        }
    }
    
    /**
     * Search notes for current user
     */
    public List<Note> searchNotes(String keyword) 
            throws AuthenticationException, DatabaseException {
        
        if (!SessionManager.isLoggedIn()) {
            throw new AuthenticationException("You must be logged in to search notes");
        }
        
        try {
            return noteDao.searchNotes(keyword, SessionManager.getCurrentUserId());
        } catch (Exception e) {
            throw new DatabaseException("Failed to search notes: " + e.getMessage(), e);
        }
    }
    
    /**
     * Get deleted notes (trash) for current user
     */
    public List<Note> getDeletedNotes() 
            throws AuthenticationException, DatabaseException {
        
        if (!SessionManager.isLoggedIn()) {
            throw new AuthenticationException("You must be logged in to view trash");
        }
        
        try {
            return noteDao.getDeletedNotes(SessionManager.getCurrentUserId());
        } catch (Exception e) {
            throw new DatabaseException("Failed to get deleted notes: " + e.getMessage(), e);
        }
    }
    
    /**
     * Restore a deleted note
     */
    public boolean restoreNote(int noteId) 
            throws AuthenticationException, DatabaseException {
        
        if (!SessionManager.isLoggedIn()) {
            throw new AuthenticationException("You must be logged in to restore notes");
        }
        
        try {
            // Check ownership in deleted notes
            List<Note> deletedNotes = noteDao.getDeletedNotes(SessionManager.getCurrentUserId());
            boolean ownsNote = false;
            for (Note note : deletedNotes) {
                if (note.getNoteId() == noteId) {
                    ownsNote = true;
                    break;
                }
            }
            
            if (!ownsNote) {
                throw new AuthenticationException("You don't have permission to restore this note");
            }
            
            return noteDao.restoreNote(noteId);
            
        } catch (AuthenticationException e) {
            throw e;
        } catch (Exception e) {
            throw new DatabaseException("Failed to restore note: " + e.getMessage(), e);
        }
    }
    
    /**
     * Permanently delete a note
     */
    public boolean permanentDelete(int noteId) 
            throws AuthenticationException, DatabaseException {
        
        if (!SessionManager.isLoggedIn()) {
            throw new AuthenticationException("You must be logged in to permanently delete notes");
        }
        
        try {
            // Check ownership in deleted notes
            List<Note> deletedNotes = noteDao.getDeletedNotes(SessionManager.getCurrentUserId());
            boolean ownsNote = false;
            for (Note note : deletedNotes) {
                if (note.getNoteId() == noteId) {
                    ownsNote = true;
                    break;
                }
            }
            
            if (!ownsNote) {
                throw new AuthenticationException("You don't have permission to delete this note");
            }
            
            return noteDao.permanentDelete(noteId);
            
        } catch (AuthenticationException e) {
            throw e;
        } catch (Exception e) {
            throw new DatabaseException("Failed to permanently delete note: " + e.getMessage(), e);
        }
    }
    
    /**
     * Empty trash (delete all deleted notes permanently)
     */
    public int emptyTrash() 
            throws AuthenticationException, DatabaseException {
        
        if (!SessionManager.isLoggedIn()) {
            throw new AuthenticationException("You must be logged in to empty trash");
        }
        
        try {
            List<Note> deletedNotes = noteDao.getDeletedNotes(SessionManager.getCurrentUserId());
            int count = 0;
            
            for (Note note : deletedNotes) {
                if (noteDao.permanentDelete(note.getNoteId())) {
                    count++;
                }
            }
            
            return count;
            
        } catch (Exception e) {
            throw new DatabaseException("Failed to empty trash: " + e.getMessage(), e);
        }
    }
    
    /**
     * Get note count for current user
     */
    public int getNoteCount() 
            throws AuthenticationException, DatabaseException {
        
        if (!SessionManager.isLoggedIn()) {
            throw new AuthenticationException("You must be logged in to view statistics");
        }
        
        try {
            return noteDao.getNoteCount(SessionManager.getCurrentUserId());
        } catch (Exception e) {
            throw new DatabaseException("Failed to get note count: " + e.getMessage(), e);
        }
    }
    
    /**
     * Get recent notes for current user
     */
    public List<Note> getRecentNotes(int limit) 
            throws AuthenticationException, DatabaseException {
        
        if (!SessionManager.isLoggedIn()) {
            throw new AuthenticationException("You must be logged in to view notes");
        }
        
        try {
            return noteDao.getRecentNotes(SessionManager.getCurrentUserId(), limit);
        } catch (Exception e) {
            throw new DatabaseException("Failed to get recent notes: " + e.getMessage(), e);
        }
    }
    
    /**
     * Get note statistics
     */
    public NoteStatistics getNoteStatistics() 
            throws AuthenticationException, DatabaseException {
        
        if (!SessionManager.isLoggedIn()) {
            throw new AuthenticationException("You must be logged in to view statistics");
        }
        
        try {
            int userId = SessionManager.getCurrentUserId();
            List<Note> allNotes = noteDao.findByUserId(userId);
            List<Note> pinnedNotes = noteDao.findPinnedNotes(userId);
            List<Note> deletedNotes = noteDao.getDeletedNotes(userId);
            
            NoteStatistics stats = new NoteStatistics();
            stats.setTotalNotes(allNotes.size());
            stats.setPinnedNotes(pinnedNotes.size());
            stats.setDeletedNotes(deletedNotes.size());
            stats.setActiveNotes(allNotes.size());
            
            return stats;
            
        } catch (Exception e) {
            throw new DatabaseException("Failed to get note statistics: " + e.getMessage(), e);
        }
    }
    
    /**
     * Inner class for note statistics
     */
    public static class NoteStatistics {
        private int totalNotes;
        private int pinnedNotes;
        private int deletedNotes;
        private int activeNotes;
        
        // Getters and Setters
        public int getTotalNotes() { return totalNotes; }
        public void setTotalNotes(int totalNotes) { this.totalNotes = totalNotes; }
        
        public int getPinnedNotes() { return pinnedNotes; }
        public void setPinnedNotes(int pinnedNotes) { this.pinnedNotes = pinnedNotes; }
        
        public int getDeletedNotes() { return deletedNotes; }
        public void setDeletedNotes(int deletedNotes) { this.deletedNotes = deletedNotes; }
        
        public int getActiveNotes() { return activeNotes; }
        public void setActiveNotes(int activeNotes) { this.activeNotes = activeNotes; }
        
        @Override
        public String toString() {
            return String.format("Notes: %d active, %d pinned, %d in trash", 
                activeNotes, pinnedNotes, deletedNotes);
        }
    }
}