package backend.dao.interfaces;

import backend.model.Course;
import backend.exceptions.DatabaseException;
import java.util.List;

public interface CourseDao {
    // CRUD Operations
    Course createCourse(Course course) throws DatabaseException;
    Course getCourseById(int courseId) throws DatabaseException;
    List<Course> getCoursesByUser(int userId) throws DatabaseException;
    List<Course> findAll() throws DatabaseException;
    boolean updateCourse(Course course) throws DatabaseException;
    boolean deleteCourse(int courseId) throws DatabaseException;
    
    // Business operations - REMOVED enrollment methods
    List<Course> searchCourses(String keyword) throws DatabaseException;
}