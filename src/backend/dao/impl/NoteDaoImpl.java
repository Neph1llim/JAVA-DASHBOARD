package backend.dao.impl;

import backend.dao.interfaces.NoteDao;
import backend.model.Note;
import backend.exceptions.DatabaseException;
import backend.db.DatabaseConnection;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class NoteDaoImpl implements NoteDao {
    private final DatabaseConnection dbConnection;
    
    public NoteDaoImpl() {
        this.dbConnection = DatabaseConnection.getInstance();
    }
    
    @Override
    public Note create(Note note) throws DatabaseException {
        // SQL matches your table structure exactly
        String sql = "INSERT INTO notes (user_id, title, content, is_pinned, created_at, updated_at) " +
                    "VALUES (?, ?, ?, ?, ?, ?)";
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            conn = dbConnection.getConnection();
            pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            
            // Set parameters matching your table columns
            pstmt.setInt(1, note.getUserId());
            pstmt.setString(2, note.getTitle());
            pstmt.setString(3, note.getContent());
            pstmt.setBoolean(4, note.isPinned());
            pstmt.setTimestamp(5, Timestamp.valueOf(LocalDateTime.now()));
            pstmt.setTimestamp(6, Timestamp.valueOf(LocalDateTime.now()));
            
            int affectedRows = pstmt.executeUpdate();
            
            if (affectedRows > 0) {
                rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    note.setNoteId(rs.getInt(1));
                }
            }
            
            return note;
            
        } catch (SQLException e) {
            throw new DatabaseException("Failed to create note: " + e.getMessage(), e);
        } finally {
            closeResources(rs, pstmt, conn);
        }
    }
    
    @Override
    public Note findById(int noteId) throws DatabaseException {
        // Get note by ID, excluding soft-deleted notes (deleted_at IS NULL)
        String sql = "SELECT note_id, user_id, title, content, is_pinned, " +
                    "created_at, updated_at, deleted_at " +
                    "FROM notes WHERE note_id = ? AND deleted_at IS NULL";
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            conn = dbConnection.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, noteId);
            rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return mapResultSetToNote(rs);
            }
            
            return null;
            
        } catch (SQLException e) {
            throw new DatabaseException("Failed to find note: " + e.getMessage(), e);
        } finally {
            closeResources(rs, pstmt, conn);
        }
    }
    
    @Override
    public List<Note> findByUserId(int userId) throws DatabaseException {
        List<Note> notes = new ArrayList<>();
        // Get all active notes for a user, ordered by pinned first, then updated date
        String sql = "SELECT note_id, user_id, title, content, is_pinned, " +
                    "created_at, updated_at, deleted_at " +
                    "FROM notes WHERE user_id = ? AND deleted_at IS NULL " +
                    "ORDER BY is_pinned DESC, updated_at DESC";
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            conn = dbConnection.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, userId);
            rs = pstmt.executeQuery();
            
            while (rs.next()) {
                notes.add(mapResultSetToNote(rs));
            }
            
            return notes;
            
        } catch (SQLException e) {
            throw new DatabaseException("Failed to find user notes: " + e.getMessage(), e);
        } finally {
            closeResources(rs, pstmt, conn);
        }
    }
    
    @Override
    public List<Note> findPinnedNotes(int userId) throws DatabaseException {
        List<Note> notes = new ArrayList<>();
        String sql = "SELECT note_id, user_id, title, content, is_pinned, " +
                    "created_at, updated_at, deleted_at " +
                    "FROM notes WHERE user_id = ? AND is_pinned = 1 AND deleted_at IS NULL " +
                    "ORDER BY updated_at DESC";
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            conn = dbConnection.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, userId);
            rs = pstmt.executeQuery();
            
            while (rs.next()) {
                notes.add(mapResultSetToNote(rs));
            }
            
            return notes;
            
        } catch (SQLException e) {
            throw new DatabaseException("Failed to find pinned notes: " + e.getMessage(), e);
        } finally {
            closeResources(rs, pstmt, conn);
        }
    }
    
    @Override
    public List<Note> searchNotes(String keyword, int userId) throws DatabaseException {
        List<Note> notes = new ArrayList<>();
        // Search in title and content, case-insensitive
        String sql = "SELECT note_id, user_id, title, content, is_pinned, " +
                    "created_at, updated_at, deleted_at " +
                    "FROM notes WHERE user_id = ? AND deleted_at IS NULL " +
                    "AND (title LIKE ? OR content LIKE ?) " +
                    "ORDER BY updated_at DESC";
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            conn = dbConnection.getConnection();
            pstmt = conn.prepareStatement(sql);
            
            String searchPattern = "%" + keyword + "%";
            pstmt.setInt(1, userId);
            pstmt.setString(2, searchPattern);
            pstmt.setString(3, searchPattern);
            
            rs = pstmt.executeQuery();
            
            while (rs.next()) {
                notes.add(mapResultSetToNote(rs));
            }
            
            return notes;
            
        } catch (SQLException e) {
            throw new DatabaseException("Failed to search notes: " + e.getMessage(), e);
        } finally {
            closeResources(rs, pstmt, conn);
        }
    }
    
    @Override
    public boolean update(Note note) throws DatabaseException {
        // Update note, excluding soft-deleted ones
        String sql = "UPDATE notes SET title = ?, content = ?, is_pinned = ?, " +
                    "updated_at = ? WHERE note_id = ? AND user_id = ? AND deleted_at IS NULL";
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            conn = dbConnection.getConnection();
            pstmt = conn.prepareStatement(sql);
            
            pstmt.setString(1, note.getTitle());
            pstmt.setString(2, note.getContent());
            pstmt.setBoolean(3, note.isPinned());
            pstmt.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now()));
            pstmt.setInt(5, note.getNoteId());
            pstmt.setInt(6, note.getUserId());
            
            return pstmt.executeUpdate() > 0;
            
        } catch (SQLException e) {
            throw new DatabaseException("Failed to update note: " + e.getMessage(), e);
        } finally {
            closeResources(null, pstmt, conn);
        }
    }
    
    @Override
    public boolean delete(int noteId) throws DatabaseException {
        // Soft delete - set deleted_at timestamp
        String sql = "UPDATE notes SET deleted_at = ? WHERE note_id = ? AND deleted_at IS NULL";
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            conn = dbConnection.getConnection();
            pstmt = conn.prepareStatement(sql);
            
            pstmt.setTimestamp(1, Timestamp.valueOf(LocalDateTime.now()));
            pstmt.setInt(2, noteId);
            
            return pstmt.executeUpdate() > 0;
            
        } catch (SQLException e) {
            throw new DatabaseException("Failed to delete note: " + e.getMessage(), e);
        } finally {
            closeResources(null, pstmt, conn);
        }
    }
    
    @Override
    public boolean togglePin(int noteId) throws DatabaseException {
        // Toggle the is_pinned flag
        String sql = "UPDATE notes SET is_pinned = NOT is_pinned, updated_at = ? " +
                    "WHERE note_id = ? AND deleted_at IS NULL";
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            conn = dbConnection.getConnection();
            pstmt = conn.prepareStatement(sql);
            
            pstmt.setTimestamp(1, Timestamp.valueOf(LocalDateTime.now()));
            pstmt.setInt(2, noteId);
            
            return pstmt.executeUpdate() > 0;
            
        } catch (SQLException e) {
            throw new DatabaseException("Failed to toggle pin: " + e.getMessage(), e);
        } finally {
            closeResources(null, pstmt, conn);
        }
    }
    
    @Override
    public List<Note> getDeletedNotes(int userId) throws DatabaseException {
        List<Note> notes = new ArrayList<>();
        String sql = "SELECT note_id, user_id, title, content, is_pinned, " +
                    "created_at, updated_at, deleted_at " +
                    "FROM notes WHERE user_id = ? AND deleted_at IS NOT NULL " +
                    "ORDER BY deleted_at DESC";
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            conn = dbConnection.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, userId);
            rs = pstmt.executeQuery();
            
            while (rs.next()) {
                notes.add(mapResultSetToNote(rs));
            }
            
            return notes;
            
        } catch (SQLException e) {
            throw new DatabaseException("Failed to get deleted notes: " + e.getMessage(), e);
        } finally {
            closeResources(rs, pstmt, conn);
        }
    }
    
    @Override
    public boolean restoreNote(int noteId) throws DatabaseException {
        // Restore a soft-deleted note
        String sql = "UPDATE notes SET deleted_at = NULL, updated_at = ? " +
                    "WHERE note_id = ? AND deleted_at IS NOT NULL";
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            conn = dbConnection.getConnection();
            pstmt = conn.prepareStatement(sql);
            
            pstmt.setTimestamp(1, Timestamp.valueOf(LocalDateTime.now()));
            pstmt.setInt(2, noteId);
            
            return pstmt.executeUpdate() > 0;
            
        } catch (SQLException e) {
            throw new DatabaseException("Failed to restore note: " + e.getMessage(), e);
        } finally {
            closeResources(null, pstmt, conn);
        }
    }
    
    @Override
    public boolean permanentDelete(int noteId) throws DatabaseException {
        // Permanent delete from database
        String sql = "DELETE FROM notes WHERE note_id = ?";
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            conn = dbConnection.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, noteId);
            
            return pstmt.executeUpdate() > 0;
            
        } catch (SQLException e) {
            throw new DatabaseException("Failed to permanently delete note: " + e.getMessage(), e);
        } finally {
            closeResources(null, pstmt, conn);
        }
    }
    
    @Override
    public int getNoteCount(int userId) throws DatabaseException {
        String sql = "SELECT COUNT(*) as count FROM notes WHERE user_id = ? AND deleted_at IS NULL";
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            conn = dbConnection.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, userId);
            rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return rs.getInt("count");
            }
            
            return 0;
            
        } catch (SQLException e) {
            throw new DatabaseException("Failed to get note count: " + e.getMessage(), e);
        } finally {
            closeResources(rs, pstmt, conn);
        }
    }
    
    @Override
    public List<Note> getRecentNotes(int userId, int limit) throws DatabaseException {
        List<Note> notes = new ArrayList<>();
        String sql = "SELECT note_id, user_id, title, content, is_pinned, " +
                    "created_at, updated_at, deleted_at " +
                    "FROM notes WHERE user_id = ? AND deleted_at IS NULL " +
                    "ORDER BY updated_at DESC LIMIT ?";
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            conn = dbConnection.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, userId);
            pstmt.setInt(2, limit);
            rs = pstmt.executeQuery();
            
            while (rs.next()) {
                notes.add(mapResultSetToNote(rs));
            }
            
            return notes;
            
        } catch (SQLException e) {
            throw new DatabaseException("Failed to get recent notes: " + e.getMessage(), e);
        } finally {
            closeResources(rs, pstmt, conn);
        }
    }
    
    // Helper method to map ResultSet to Note object
    private Note mapResultSetToNote(ResultSet rs) throws SQLException {
        Note note = new Note();
        note.setNoteId(rs.getInt("note_id"));
        note.setUserId(rs.getInt("user_id"));
        note.setTitle(rs.getString("title"));
        note.setContent(rs.getString("content"));
        note.setPinned(rs.getBoolean("is_pinned"));
        note.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
        note.setUpdatedAt(rs.getTimestamp("updated_at").toLocalDateTime());
        
        Timestamp deletedAt = rs.getTimestamp("deleted_at");
        if (deletedAt != null) {
            note.setDeletedAt(deletedAt.toLocalDateTime());
        }
        
        return note;
    }
    
    // Resource cleanup helper
    private void closeResources(ResultSet rs, Statement stmt, Connection conn) {
        try {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (conn != null && !conn.isClosed()) {
                dbConnection.closeConnection(conn);
            }
        } catch (SQLException e) {
            System.err.println("Error closing database resources: " + e.getMessage());
        }
    }
}