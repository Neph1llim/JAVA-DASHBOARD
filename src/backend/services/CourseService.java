package backend.services;

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
     * Create a new course for a user
     */
    public Course createCourse(int userId, String courseName) {
        try {
            Course course = new Course(courseName, 0.0, 0.0, 
                                      java.time.LocalDate.now(), userId);
            return courseDao.createCourse(course);
        } catch (Exception e) {
            System.err.println("[COURSE SERVICE] Error creating course: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
    
    /**
     * Get all courses for a specific user
     */
    public List<Course> getUserCourses(int userId) {
        try {
            return courseDao.getCoursesByUser(userId);
        } catch (Exception e) {
            System.err.println("[COURSE SERVICE] Error getting courses: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
    
    /**
     * Get a course by its ID
     */
    public Course getCourseById(int courseId) {
        try {
            return courseDao.getCourseById(courseId);
        } catch (Exception e) {
            System.err.println("[COURSE SERVICE] Error getting course by ID: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
    
    /**
     * Update an existing course
     */
    public boolean updateCourse(Course course) {
        try {
            return courseDao.updateCourse(course);
        } catch (Exception e) {
            System.err.println("[COURSE SERVICE] Error updating course: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * Delete a course and all its associated assessments
     */
    public boolean deleteCourse(int courseId) {
        try {
            return courseDao.deleteCourse(courseId);
        } catch (Exception e) {
            System.err.println("[COURSE SERVICE] Error deleting course: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * Search courses by keyword
     */
    public List<Course> searchCourses(String keyword) {
        try {
            return courseDao.searchCourses(keyword);
        } catch (Exception e) {
            System.err.println("[COURSE SERVICE] Error searching courses: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
    
    /**
     * Get all courses (admin function or for debugging)
     */
    public List<Course> getAllCourses() {
        try {
            return courseDao.findAll();
        } catch (Exception e) {
            System.err.println("[COURSE SERVICE] Error getting all courses: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
    
    /**
     * Update course final grade based on assessments
     */
    public boolean updateCourseFinalGrade(int courseId, double finalGrade) {
        try {
            Course course = courseDao.getCourseById(courseId);
            if (course == null) {
                System.err.println("[COURSE SERVICE] Course not found: " + courseId);
                return false;
            }
            
            course.setFinalGrade(finalGrade);
            return courseDao.updateCourse(course);
            
        } catch (Exception e) {
            System.err.println("[COURSE SERVICE] Error updating course final grade: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * Check if a course name already exists for a user
     */
    public boolean isCourseNameExists(int userId, String courseName) {
        try {
            List<Course> userCourses = getUserCourses(userId);
            if (userCourses == null) return false;
            
            return userCourses.stream()
                .anyMatch(course -> course.getCourseName().equalsIgnoreCase(courseName));
        } catch (Exception e) {
            System.err.println("[COURSE SERVICE] Error checking course name: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * Get total number of courses for a user
     */
    public int getCourseCount(int userId) {
        try {
            List<Course> courses = getUserCourses(userId);
            return courses != null ? courses.size() : 0;
        } catch (Exception e) {
            System.err.println("[COURSE SERVICE] Error getting course count: " + e.getMessage());
            e.printStackTrace();
            return 0;
        }
    }
    
    /**
     * Get average final grade for all courses of a user
     */
    public double getAverageFinalGrade(int userId) {
        try {
            List<Course> courses = getUserCourses(userId);
            if (courses == null || courses.isEmpty()) return 0.0;
            
            double total = courses.stream()
                .mapToDouble(Course::getFinalGrade)
                .sum();
            
            return total / courses.size();
        } catch (Exception e) {
            System.err.println("[COURSE SERVICE] Error calculating average grade: " + e.getMessage());
            e.printStackTrace();
            return 0.0;
        }
    }
}