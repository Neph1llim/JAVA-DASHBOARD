package backend.services;

import backend.dao.interfaces.ToDoItemDao;
import backend.dao.impl.ToDoItemDaoImpl;
import backend.model.ToDoItem;
import java.time.LocalDateTime;
import java.util.List;

public class TodoService {
    private ToDoItemDao todoDao;
    
    public TodoService() {
        this.todoDao = new ToDoItemDaoImpl();
    }
    
    /**
     * Create a new to do item
     */
    public ToDoItem createTodo(int userId, String taskTitle, String description, 
                          String priority, LocalDateTime dueDate) {
        try {
            if (priority == null || 
                (!priority.equals("LOW") && !priority.equals("MEDIUM") && !priority.equals("HIGH"))) {
                priority = "MEDIUM"; // Default
            }
            
            ToDoItem todo = new ToDoItem();
            todo.setUserId(userId);
            todo.setTitle(taskTitle);
            todo.setDescription(description != null ? description : "");
            todo.setPriority(priority);
            todo.setDueDate(dueDate);
            todo.setCompleted(false);
            
            System.out.println("[TODO SERVICE] Creating todo: " + taskTitle + " [" + priority + "]");
            return todoDao.createTodo(todo);
            
        } catch (Exception e) {
            System.err.println("[TODO SERVICE] Error creating todo: " + e.getMessage());
            return null;
        }
    }
    
    /**
     * Get all to dos for a user
     */
    public List<ToDoItem> getUserTodos(int userId) {
        try {
            System.out.println("[TODO SERVICE] Getting todos for user ID: " + userId);
            return todoDao.getTodosByUser(userId);
        } catch (Exception e) {
            System.err.println("[TODO SERVICE] Error getting todos: " + e.getMessage());
            return null;
        }
    }
    
    /**
     * Get to dos by status (completed/incomplete)
     */
    public List<ToDoItem> getTodosByStatus(int userId, boolean completed) {
        try {
            String status = completed ? "COMPLETED" : "PENDING";
            System.out.println("[TODO SERVICE] Getting " + status + 
                             " todos for user ID: " + userId);
            return todoDao.getTodosByStatus(userId, status);
        } catch (Exception e) {
            System.err.println("[TODO SERVICE] Error getting todos by status: " + e.getMessage());
            return null;
        }
    }
    
    /**
     * Get upcoming to dos (not completed, with due date)
     */
    public List<ToDoItem> getUpcomingTodos(int userId) {
        try {
            System.out.println("[TODO SERVICE] Getting upcoming todos for user ID: " + userId);
            // We'll combine due today + due this week for "upcoming"
            List<ToDoItem> dueToday = todoDao.findDueToday(userId);
            List<ToDoItem> dueThisWeek = todoDao.findDueThisWeek(userId);
            
            // Combine and remove duplicates (if any)
            dueToday.addAll(dueThisWeek);
            return dueToday;
        } catch (Exception e) {
            System.err.println("[TODO SERVICE] Error getting upcoming todos: " + e.getMessage());
            return null;
        }
    }
    
    /**
     * Toggle to do completion status
     */
    public boolean toggleTodoStatus(int todoId, boolean completed) {
        try {
            System.out.println("[TODO SERVICE] Setting todo ID: " + todoId + " to " + 
                             (completed ? "completed" : "incomplete"));
            
            if (completed) {
                return todoDao.markAsComplete(todoId);
            } else {
                return todoDao.markAsInProgress(todoId);
            }
        } catch (Exception e) {
            System.err.println("[TODO SERVICE] Error toggling todo status: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Update to do
     */
    public boolean updateTodo(ToDoItem todo) {
        try {
            if (todo == null) {
                System.err.println("[TODO SERVICE] Todo cannot be null");
                return false;
            }
            
            System.out.println("[TODO SERVICE] Updating todo ID: " + todo.getTodoId());
            return todoDao.update(todo);
            
        } catch (Exception e) {
            System.err.println("[TODO SERVICE] Error updating todo: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Delete to do
     */
    public boolean deleteTodo(int todoId) {
        try {
            System.out.println("[TODO SERVICE] Deleting todo ID: " + todoId);
            return todoDao.delete(todoId);
        } catch (Exception e) {
            System.err.println("[TODO SERVICE] Error deleting todo: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Get to do by ID - FIXED: Uses findById() not getTodoById()
     */
    public ToDoItem getTodoById(int todoId) {
        try {
            System.out.println("[TODO SERVICE] Getting todo ID: " + todoId);
            return todoDao.findById(todoId);  // FIXED: Changed from getTodoById() to findById()
        } catch (Exception e) {
            System.err.println("[TODO SERVICE] Error getting todo: " + e.getMessage());
            return null;
        }
    }
    
    /**
     * Get to dos by priority
     */
    public List<ToDoItem> getTodosByPriority(int userId, String priority) {
        try {
            System.out.println("[TODO SERVICE] Getting " + priority + " priority todos for user: " + userId);
            return todoDao.findByPriority(userId, priority);
        } catch (Exception e) {
            System.err.println("[TODO SERVICE] Error getting todos by priority: " + e.getMessage());
            return null;
        }
    }
    
    /**
     * Search to dos
     */
    public List<ToDoItem> searchTodos(String keyword, int userId) {
        try {
            System.out.println("[TODO SERVICE] Searching todos with keyword: " + keyword);
            return todoDao.searchItems(keyword, userId);
        } catch (Exception e) {
            System.err.println("[TODO SERVICE] Error searching todos: " + e.getMessage());
            return null;
        }
    }
    
    /**
     * Get overdue to dos
     */
    public List<ToDoItem> getOverdueTodos(int userId) {
        try {
            System.out.println("[TODO SERVICE] Getting overdue todos for user: " + userId);
            return todoDao.findOverdueItems(userId);
        } catch (Exception e) {
            System.err.println("[TODO SERVICE] Error getting overdue todos: " + e.getMessage());
            return null;
        }
    }
    
    /**
     * Get to dos due today
     */
    public List<ToDoItem> getTodosDueToday(int userId) {
        try {
            System.out.println("[TODO SERVICE] Getting todos due today for user: " + userId);
            return todoDao.findDueToday(userId);
        } catch (Exception e) {
            System.err.println("[TODO SERVICE] Error getting todos due today: " + e.getMessage());
            return null;
        }
    }
    
    /**
     * Get to dos by course
     */
    public List<ToDoItem> getTodosByCourse(int courseId) {
        try {
            System.out.println("[TODO SERVICE] Getting todos for course ID: " + courseId);
            return todoDao.findByCourseId(courseId);
        } catch (Exception e) {
            System.err.println("[TODO SERVICE] Error getting todos by course: " + e.getMessage());
            return null;
        }
    }
    
    /**
     * Get statistics
     */
    public String getTodoStatistics(int userId) {
        try {
            int completed = todoDao.getCompletedCount(userId);
            int pending = todoDao.getPendingCount(userId);
            int overdue = todoDao.getOverdueCount(userId);
            
            return String.format("Completed: %d, Pending: %d, Overdue: %d", 
                               completed, pending, overdue);
        } catch (Exception e) {
            System.err.println("[TODO SERVICE] Error getting statistics: " + e.getMessage());
            return "Error loading statistics";
        }
    }
    
    /**
     * Test method
     */
    public static void main(String[] args) {
        TodoService service = new TodoService();
        System.out.println("=== TODO SERVICE TEST ===");
        System.out.println("Service methods ready. Connect to GUI to test.");
    }
}