package backend.dao.interfaces;

import backend.model.ToDoItem;
import backend.exceptions.DatabaseException;
import java.time.LocalDateTime;
import java.util.List;

public interface ToDoItemDao {
    // CRUD Operations
    ToDoItem createTodo(ToDoItem item) throws DatabaseException;
    ToDoItem findById(int itemId) throws DatabaseException;
    List<ToDoItem> getTodosByUser(int userId) throws DatabaseException;
    List<ToDoItem> findByCourseId(int courseId) throws DatabaseException;
    boolean update(ToDoItem item) throws DatabaseException;
    boolean delete(int itemId) throws DatabaseException;
    
    // Status-based queries
    List<ToDoItem> getTodosByStatus(int userId, String status) throws DatabaseException;
    List<ToDoItem> findByPriority(int userId, String priority) throws DatabaseException;
    List<ToDoItem> findOverdueItems(int userId) throws DatabaseException;
    List<ToDoItem> findDueToday(int userId) throws DatabaseException;
    List<ToDoItem> findDueThisWeek(int userId) throws DatabaseException;
    
    // Completion operations
    boolean markAsComplete(int itemId) throws DatabaseException;
    boolean markAsInProgress(int itemId) throws DatabaseException;
    
    // Search
    List<ToDoItem> searchItems(String keyword, int userId) throws DatabaseException;
    
    // Statistics
    int getCompletedCount(int userId) throws DatabaseException;
    int getPendingCount(int userId) throws DatabaseException;
    int getOverdueCount(int userId) throws DatabaseException;
}