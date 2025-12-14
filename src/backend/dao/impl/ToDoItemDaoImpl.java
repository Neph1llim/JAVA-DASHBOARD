package backend.dao.impl;

import backend.dao.interfaces.ToDoItemDao;
import backend.model.ToDoItem;
import backend.exceptions.DatabaseException;
import backend.db.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ToDoItemDaoImpl implements ToDoItemDao {
    private final DatabaseConnection dbConnection;
    
    public ToDoItemDaoImpl() {
        this.dbConnection = DatabaseConnection.getInstance();
    }
    
    public ToDoItem createTodo(ToDoItem item) throws DatabaseException {
        String sql = "INSERT INTO to_do_list (title, description, priority, status, " +
                    "due_date, user_id, course_id, completed, created_at) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, NOW())";
        
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            pstmt.setString(1, item.getTitle());
            pstmt.setString(2, item.getDescription());
            pstmt.setString(3, item.getPriority());
            pstmt.setString(4, item.getStatus());
            
            if (item.getDueDate() != null) {
                pstmt.setTimestamp(5, Timestamp.valueOf(item.getDueDate()));
            } else {
                pstmt.setNull(5, Types.TIMESTAMP);
            }
            
            pstmt.setInt(6, item.getUserId());
            
            if (item.getCourseId() != null) {
                pstmt.setInt(7, item.getCourseId());
            } else {
                pstmt.setNull(7, Types.INTEGER);
            }
            
            pstmt.setBoolean(8, item.isCompleted());
            
            int affectedRows = pstmt.executeUpdate();
            
            if (affectedRows > 0) {
                try (ResultSet rs = pstmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        item.setItemId(rs.getInt(1));
                    }
                }
            }
            
            return item;
            
        } catch (SQLException e) {
            throw new DatabaseException("Failed to create todo item: " + e.getMessage(), e);
        }
    }
    
    public List<ToDoItem> findByUserId(int userId) throws DatabaseException {
        List<ToDoItem> items = new ArrayList<>();
        String sql = "SELECT * FROM to_do_list WHERE user_id = ? ORDER BY " +
                    "CASE WHEN due_date IS NULL THEN 1 ELSE 0 END, " +
                    "due_date, priority DESC";
        
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                items.add(mapResultSetToItem(rs));
            }
            
            return items;
            
        } catch (SQLException e) {
            throw new DatabaseException("Failed to find user todo items: " + e.getMessage(), e);
        }
    }
    
    public List<ToDoItem> findByStatus(int userId, String status) throws DatabaseException {
        List<ToDoItem> items = new ArrayList<>();
        String sql = "SELECT * FROM to_do_list WHERE user_id = ? AND status = ? " +
                    "ORDER BY due_date, priority DESC";
        
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, userId);
            pstmt.setString(2, status);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                items.add(mapResultSetToItem(rs));
            }
            
            return items;
            
        } catch (SQLException e) {
            throw new DatabaseException("Failed to find items by status: " + e.getMessage(), e);
        }
    }
    
    @Override
    public List<ToDoItem> findOverdueItems(int userId) throws DatabaseException {
        List<ToDoItem> items = new ArrayList<>();
        String sql = "SELECT * FROM to_do_list WHERE user_id = ? AND completed = 0 " +
                    "AND due_date IS NOT NULL AND due_date < NOW() " +
                    "ORDER BY due_date";
        
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                items.add(mapResultSetToItem(rs));
            }
            
            return items;
            
        } catch (SQLException e) {
            throw new DatabaseException("Failed to find overdue items: " + e.getMessage(), e);
        }
    }
    
    @Override
    public boolean markAsComplete(int itemId) throws DatabaseException {
        String sql = "UPDATE to_do_list SET status = 'COMPLETED', completed = 1, " +
                    "completed_at = NOW() WHERE item_id = ?";
        
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, itemId);
            return pstmt.executeUpdate() > 0;
            
        } catch (SQLException e) {
            throw new DatabaseException("Failed to mark item as complete: " + e.getMessage(), e);
        }
    }
    
    @Override
    public int getCompletedCount(int userId) throws DatabaseException {
        String sql = "SELECT COUNT(*) as count FROM to_do_list " +
                    "WHERE user_id = ? AND completed = 1";
        
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return rs.getInt("count");
            }
            
            return 0;
            
        } catch (SQLException e) {
            throw new DatabaseException("Failed to get completed count: " + e.getMessage(), e);
        }
    }
    
    // Add these missing methods to ToDoItemDaoImpl

    @Override
    public ToDoItem findById(int itemId) throws DatabaseException {
        String sql = "SELECT * FROM to_do_list WHERE item_id = ?";

        try (Connection conn = dbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, itemId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return mapResultSetToItem(rs);
            }

            return null;

        } catch (SQLException e) {
            throw new DatabaseException("Failed to find todo item: " + e.getMessage(), e);
        }
    }

    @Override
    public List<ToDoItem> findByCourseId(int courseId) throws DatabaseException {
        List<ToDoItem> items = new ArrayList<>();
        String sql = "SELECT * FROM to_do_list WHERE course_id = ? ORDER BY due_date";

        try (Connection conn = dbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, courseId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                items.add(mapResultSetToItem(rs));
            }

            return items;

        } catch (SQLException e) {
            throw new DatabaseException("Failed to find course todos: " + e.getMessage(), e);
        }
    }

    @Override
    public boolean update(ToDoItem item) throws DatabaseException {
        String sql = "UPDATE to_do_list SET title = ?, description = ?, priority = ?, " +
                    "status = ?, due_date = ?, course_id = ?, completed = ? " +
                    "WHERE item_id = ? AND user_id = ?";

        try (Connection conn = dbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, item.getTitle());
            pstmt.setString(2, item.getDescription());
            pstmt.setString(3, item.getPriority());
            pstmt.setString(4, item.getStatus());

            if (item.getDueDate() != null) {
                pstmt.setTimestamp(5, Timestamp.valueOf(item.getDueDate()));
            } else {
                pstmt.setNull(5, Types.TIMESTAMP);
            }

            if (item.getCourseId() != null) {
                pstmt.setInt(6, item.getCourseId());
            } else {
                pstmt.setNull(6, Types.INTEGER);
            }

            pstmt.setBoolean(7, item.isCompleted());
            pstmt.setInt(8, item.getItemId());
            pstmt.setInt(9, item.getUserId());

            return pstmt.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new DatabaseException("Failed to update todo item: " + e.getMessage(), e);
        }
    }

    @Override
    public boolean delete(int itemId) throws DatabaseException {
        String sql = "DELETE FROM to_do_list WHERE item_id = ?";

        try (Connection conn = dbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, itemId);
            return pstmt.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new DatabaseException("Failed to delete todo item: " + e.getMessage(), e);
        }
    }
    
    private ToDoItem mapResultSetToItem(ResultSet rs) throws SQLException {
        ToDoItem item = new ToDoItem();
        item.setItemId(rs.getInt("item_id"));
        item.setTitle(rs.getString("title"));
        item.setDescription(rs.getString("description"));
        item.setPriority(rs.getString("priority"));
        item.setStatus(rs.getString("status"));
        
        Timestamp dueDate = rs.getTimestamp("due_date");
        if (dueDate != null) {
            item.setDueDate(dueDate.toLocalDateTime());
        }
        
        item.setUserId(rs.getInt("user_id"));
        item.setCourseId(rs.getInt("course_id"));
        item.setCompleted(rs.getBoolean("completed"));
        item.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
        
        Timestamp completedAt = rs.getTimestamp("completed_at");
        if (completedAt != null) {
            item.setCompletedAt(completedAt.toLocalDateTime());
        }
        
        return item;
    }
    
    @Override
    public List<ToDoItem> getTodosByUser(int userId) throws DatabaseException {
        return findByUserId(userId); // Call existing method
    }

    @Override
    public List<ToDoItem> findByPriority(int userId, String priority) throws DatabaseException {
        List<ToDoItem> items = new ArrayList<>();
        String sql = "SELECT * FROM to_do_list WHERE user_id = ? AND priority = ? " +
                    "ORDER BY due_date";

        try (Connection conn = dbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, userId);
            pstmt.setString(2, priority);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                items.add(mapResultSetToItem(rs));
            }

            return items;

        } catch (SQLException e) {
            throw new DatabaseException("Failed to find items by priority: " + e.getMessage(), e);
        }
    }

    @Override
    public List<ToDoItem> findDueToday(int userId) throws DatabaseException {
        List<ToDoItem> items = new ArrayList<>();
        String sql = "SELECT * FROM to_do_list WHERE user_id = ? AND completed = 0 " +
                    "AND DATE(due_date) = CURDATE() ORDER BY due_date";

        try (Connection conn = dbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                items.add(mapResultSetToItem(rs));
            }

            return items;

        } catch (SQLException e) {
            throw new DatabaseException("Failed to find items due today: " + e.getMessage(), e);
        }
    }

    @Override
    public List<ToDoItem> findDueThisWeek(int userId) throws DatabaseException {
        List<ToDoItem> items = new ArrayList<>();
        String sql = "SELECT * FROM to_do_list WHERE user_id = ? AND completed = 0 " +
                    "AND YEARWEEK(due_date, 1) = YEARWEEK(CURDATE(), 1) " +
                    "ORDER BY due_date";

        try (Connection conn = dbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                items.add(mapResultSetToItem(rs));
            }

            return items;

        } catch (SQLException e) {
            throw new DatabaseException("Failed to find items due this week: " + e.getMessage(), e);
        }
    }

    @Override
    public boolean markAsInProgress(int itemId) throws DatabaseException {
        String sql = "UPDATE to_do_list SET status = 'IN_PROGRESS', completed = 0 " +
                    "WHERE item_id = ?";

        try (Connection conn = dbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, itemId);
            return pstmt.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new DatabaseException("Failed to mark item as in progress: " + e.getMessage(), e);
        }
    }

    @Override
    public List<ToDoItem> searchItems(String keyword, int userId) throws DatabaseException {
        List<ToDoItem> items = new ArrayList<>();
        String sql = "SELECT * FROM to_do_list WHERE user_id = ? AND " +
                    "(title LIKE ? OR description LIKE ?) ORDER BY due_date";

        try (Connection conn = dbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, userId);
            String searchPattern = "%" + keyword + "%";
            pstmt.setString(2, searchPattern);
            pstmt.setString(3, searchPattern);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                items.add(mapResultSetToItem(rs));
            }

            return items;

        } catch (SQLException e) {
            throw new DatabaseException("Failed to search items: " + e.getMessage(), e);
        }
    }

    @Override
    public int getPendingCount(int userId) throws DatabaseException {
        String sql = "SELECT COUNT(*) as count FROM to_do_list " +
                    "WHERE user_id = ? AND completed = 0";

        try (Connection conn = dbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return rs.getInt("count");
            }

            return 0;

        } catch (SQLException e) {
            throw new DatabaseException("Failed to get pending count: " + e.getMessage(), e);
        }
    }

    // FIX: This is the method causing the error!
    @Override
    public int getOverdueCount(int userId) throws DatabaseException {
        String sql = "SELECT COUNT(*) as count FROM to_do_list " +
                    "WHERE user_id = ? AND completed = 0 " +
                    "AND due_date IS NOT NULL AND due_date < NOW()";

        try (Connection conn = dbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return rs.getInt("count");
            }

            return 0;

        } catch (SQLException e) {
            throw new DatabaseException("Failed to get overdue count: " + e.getMessage(), e);
        }
    }

    @Override
    public List<ToDoItem> getTodosByStatus(int userId, String status) throws DatabaseException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
