package backend.dao.impl;

import backend.dao.interfaces.UserDao;
import backend.model.User;
import backend.exceptions.DatabaseException;
import backend.exceptions.DuplicateEntryException;
import backend.db.DatabaseConnection;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {
    private final DatabaseConnection dbConnection;
    
    public UserDaoImpl() {
        this.dbConnection = DatabaseConnection.getInstance();
    }
    
    @Override
    public User create(User user) throws DatabaseException, DuplicateEntryException {
        // SQL matches your exact table structure
        String sql = "INSERT INTO Users (username, email, password_hash, course_id, created_at, updated_at) " +
                    "VALUES (?, ?, ?, ?, ?, ?)";
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            // Check for duplicates first
            if (usernameExists(user.getUsername())) {
                throw new DuplicateEntryException("Username '" + user.getUsername() + "' already exists");
            }
            
            if (emailExists(user.getEmail())) {
                throw new DuplicateEntryException("Email '" + user.getEmail() + "' already exists");
            }
            
            conn = dbConnection.getConnection();
            pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getEmail());
            pstmt.setString(3, user.getPasswordHash());
            
            // Handle course_id (can be null)
            if (user.getCourseId() != null) {
                pstmt.setInt(4, user.getCourseId());
            } else {
                pstmt.setNull(4, Types.INTEGER);
            }
            
            LocalDateTime now = LocalDateTime.now();
            pstmt.setTimestamp(5, Timestamp.valueOf(now));
            pstmt.setTimestamp(6, Timestamp.valueOf(now));
            
            int affectedRows = pstmt.executeUpdate();
            
            if (affectedRows > 0) {
                rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    user.setUserId(rs.getInt(1));
                }
            }
            
            return user;
            
        } catch (SQLException e) {
            if (e.getErrorCode() == 1062) { // MySQL duplicate entry
                throw new DuplicateEntryException("Duplicate entry: " + e.getMessage());
            }
            throw new DatabaseException("Failed to create user: " + e.getMessage(), e);
        } finally {
            closeResources(rs, pstmt, conn);
        }
    }
    
    @Override
    public User findById(int userId) throws DatabaseException {
        String sql = "SELECT user_id, username, email, password_hash, course_id, " +
                    "created_at, updated_at FROM Users WHERE user_id = ?";
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            conn = dbConnection.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, userId);
            rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return mapResultSetToUser(rs);
            }
            
            return null;
            
        } catch (SQLException e) {
            throw new DatabaseException("Failed to find user: " + e.getMessage(), e);
        } finally {
            closeResources(rs, pstmt, conn);
        }
    }
    
    @Override
    public User findByUsername(String username) throws DatabaseException {
        String sql = "SELECT user_id, username, email, password_hash, course_id, " +
                    "created_at, updated_at FROM Users WHERE username = ?";
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            conn = dbConnection.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return mapResultSetToUser(rs);
            }
            
            return null;
            
        } catch (SQLException e) {
            throw new DatabaseException("Failed to find user by username: " + e.getMessage(), e);
        } finally {
            closeResources(rs, pstmt, conn);
        }
    }
    
    @Override
    public List<User> findAll() throws DatabaseException {
        List<User> users = new ArrayList<>();
        String sql = "SELECT user_id, username, email, password_hash, course_id, " +
                    "created_at, updated_at FROM Users ORDER BY username";
        
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = dbConnection.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            
            while (rs.next()) {
                users.add(mapResultSetToUser(rs));
            }
            
            return users;
            
        } catch (SQLException e) {
            throw new DatabaseException("Failed to retrieve users: " + e.getMessage(), e);
        } finally {
            closeResources(rs, stmt, conn);
        }
    }
    
    @Override
    public boolean update(User user) throws DatabaseException {
        String sql = "UPDATE Users SET username = ?, email = ?, password_hash = ?, " +
                    "course_id = ?, updated_at = ? WHERE user_id = ?";
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            conn = dbConnection.getConnection();
            pstmt = conn.prepareStatement(sql);
            
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getEmail());
            pstmt.setString(3, user.getPasswordHash());
            
            // Handle course_id
            if (user.getCourseId() != null) {
                pstmt.setInt(4, user.getCourseId());
            } else {
                pstmt.setNull(4, Types.INTEGER);
            }
            
            pstmt.setTimestamp(5, Timestamp.valueOf(LocalDateTime.now()));
            pstmt.setInt(6, user.getUserId());
            
            return pstmt.executeUpdate() > 0;
            
        } catch (SQLException e) {
            throw new DatabaseException("Failed to update user: " + e.getMessage(), e);
        } finally {
            closeResources(null, pstmt, conn);
        }
    }
    
    @Override
    public boolean delete(int userId) throws DatabaseException {
        String sql = "DELETE FROM Users WHERE user_id = ?";
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            conn = dbConnection.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, userId);
            
            return pstmt.executeUpdate() > 0;
            
        } catch (SQLException e) {
            throw new DatabaseException("Failed to delete user: " + e.getMessage(), e);
        } finally {
            closeResources(null, pstmt, conn);
        }
    }
    
    @Override
    public boolean usernameExists(String username) throws DatabaseException {
        String sql = "SELECT COUNT(*) as count FROM Users WHERE username = ?";
        
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return rs.getInt("count") > 0;
            }
            
            return false;
            
        } catch (SQLException e) {
            throw new DatabaseException("Failed to check username: " + e.getMessage(), e);
        }
    }
    
    @Override
    public boolean emailExists(String email) throws DatabaseException {
        String sql = "SELECT COUNT(*) as count FROM Users WHERE email = ?";
        
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, email);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return rs.getInt("count") > 0;
            }
            
            return false;
            
        } catch (SQLException e) {
            throw new DatabaseException("Failed to check email: " + e.getMessage(), e);
        }
    }
    
    @Override
    public List<User> searchUsers(String keyword) throws DatabaseException {
        List<User> users = new ArrayList<>();
        String sql = "SELECT user_id, username, email, password_hash, course_id, " +
                    "created_at, updated_at FROM Users WHERE " +
                    "username LIKE ? OR email LIKE ? ORDER BY username";
        
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            String searchPattern = "%" + keyword + "%";
            pstmt.setString(1, searchPattern);
            pstmt.setString(2, searchPattern);
            
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                users.add(mapResultSetToUser(rs));
            }
            
            return users;
            
        } catch (SQLException e) {
            throw new DatabaseException("Failed to search users: " + e.getMessage(), e);
        }
    }
    
    public List<User> findByCourseId(int courseId) throws DatabaseException {
        List<User> users = new ArrayList<>();
        String sql = "SELECT user_id, username, email, password_hash, course_id, " +
                    "created_at, updated_at FROM Users WHERE course_id = ? ORDER BY username";
        
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, courseId);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                users.add(mapResultSetToUser(rs));
            }
            
            return users;
            
        } catch (SQLException e) {
            throw new DatabaseException("Failed to find users by course: " + e.getMessage(), e);
        }
    }
    
    public boolean updateCourseId(int userId, Integer courseId) throws DatabaseException {
        String sql = "UPDATE Users SET course_id = ?, updated_at = ? WHERE user_id = ?";
        
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            if (courseId != null) {
                pstmt.setInt(1, courseId);
            } else {
                pstmt.setNull(1, Types.INTEGER);
            }
            
            pstmt.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
            pstmt.setInt(3, userId);
            
            return pstmt.executeUpdate() > 0;
            
        } catch (SQLException e) {
            throw new DatabaseException("Failed to update course ID: " + e.getMessage(), e);
        }
    }
    
    // Helper method to map ResultSet to User object
    private User mapResultSetToUser(ResultSet rs) throws SQLException {
        User user = new User();
        user.setUserId(rs.getInt("user_id"));
        user.setUsername(rs.getString("username"));
        user.setEmail(rs.getString("email"));
        user.setPasswordHash(rs.getString("password_hash"));
        
        int courseId = rs.getInt("course_id");
        if (!rs.wasNull()) {
            user.setCourseId(courseId);
        }
        
        user.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
        user.setUpdatedAt(rs.getTimestamp("updated_at").toLocalDateTime());
        return user;
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

    @Override
    public User findByEmail(String email) throws DatabaseException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}