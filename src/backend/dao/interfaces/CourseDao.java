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
    
    // Business operations
    List<Course> searchCourses(String keyword) throws DatabaseException;
    List<Course> findUpcomingCourses(int userId) throws DatabaseException;
    List<Course> findCompletedCourses(int userId) throws DatabaseException;
    boolean enrollUserToCourse(int courseId, int userId) throws DatabaseException;
    boolean unenrollUser(int courseId, int userId) throws DatabaseException;
    List<Integer> getEnrolledUsers(int courseId) throws DatabaseException;
}