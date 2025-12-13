package backend.services;

/**
 *
 * @author Cyrus Wilson
 */
import backend.dao.interfaces.CourseDao;
import backend.dao.impl.CourseDaoImpl;
import backend.model.Course;
import java.util.List;

public class CourseService {
    private CourseDao courseDao;
    
    public CourseService() {
        this.courseDao = new CourseDaoImpl();
    }
    
    /**
     * Create a new course
     */
    public Course createCourse(int userId, String courseName, String courseYear) {
        try {
            
            Course course = new Course();
            course.setUserId(userId);
            course.setCourseName(courseName);
            if (courseYear != null && !courseYear.trim().isEmpty()) {
            course.setCourseYear(Integer.parseInt(courseYear.trim()));
            }
            
            System.out.println("[COURSE SERVICE] Creating course: " + courseName);
            return courseDao.createCourse(course);
            
        } catch (Exception e) {
            System.err.println("[COURSE SERVICE] Error creating course: " + e.getMessage());
            return null;
        }
    }
    
    /**
     * Get all courses for a user
     */
    public List<Course> getUserCourses(int userId) {
        try {
            System.out.println("[COURSE SERVICE] Getting courses for user ID: " + userId);
            return courseDao.getCoursesByUser(userId);
        } catch (Exception e) {
            System.err.println("[COURSE SERVICE] Error getting courses: " + e.getMessage());
            return null;
        }
    }
    
    /**
     * Get course by ID
     */
    public Course getCourseById(int courseId) {
        try {
            System.out.println("[COURSE SERVICE] Getting course ID: " + courseId);
            return courseDao.getCourseById(courseId);
        } catch (Exception e) {
            System.err.println("[COURSE SERVICE] Error getting course: " + e.getMessage());
            return null;
        }
    }
    
    /**
     * Update course
     */
    public boolean updateCourse(Course course) {
        try {
            if (course == null) {
                System.err.println("[COURSE SERVICE] Course cannot be null");
                return false;
            }
            
            System.out.println("[COURSE SERVICE] Updating course ID: " + course.getCourseId());
            return courseDao.updateCourse(course);
            
        } catch (Exception e) {
            System.err.println("[COURSE SERVICE] Error updating course: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Delete course
     */
    public boolean deleteCourse(int courseId) {
        try {
            System.out.println("[COURSE SERVICE] Deleting course ID: " + courseId);
            return courseDao.deleteCourse(courseId);
        } catch (Exception e) {
            System.err.println("[COURSE SERVICE] Error deleting course: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Assign/enroll user to course
     */
    public boolean assignUserToCourse(int userId, int courseId) {
        try {
            System.out.println("[COURSE SERVICE] Enrolling user ID: " + userId + " to course ID: " + courseId);
            return courseDao.enrollUserToCourse(userId, courseId);
        } catch (Exception e) {
            System.err.println("[COURSE SERVICE] Error assigning user to course: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Test method
     */
    public static void main(String[] args) {
        CourseService service = new CourseService();
        System.out.println("=== COURSE SERVICE TEST ===");
        System.out.println("Service methods ready. Requires CourseDaoImpl implementation.");
    }
}