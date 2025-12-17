package backend.dao.interfaces;

import backend.model.User;
import backend.exceptions.DatabaseException;
import backend.exceptions.DuplicateEntryException;
import java.util.List;

public interface UserDao {
    // CRUD Operations
    User create(User user) throws DatabaseException, DuplicateEntryException;
    User findById(int userId) throws DatabaseException;
    User findByUsername(String username) throws DatabaseException;
    User findByEmail(String email) throws DatabaseException;
    List<User> findAll() throws DatabaseException;
    boolean update(User user) throws DatabaseException;
    boolean delete(int userId) throws DatabaseException;
    
    // Validation
    boolean usernameExists(String username) throws DatabaseException;
    boolean emailExists(String email) throws DatabaseException;
        
    // Search functionality
    List<User> searchUsers(String keyword) throws DatabaseException;
    
    // Course-related methods
    List<User> findByCourseId(int courseId) throws DatabaseException;
    boolean updateCourseId(int userId, Integer courseId) throws DatabaseException;
}