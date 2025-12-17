package backend.test;

import backend.db.DatabaseConnection;
import backend.services.CourseService;
import backend.model.Course;

public class TestDatabaseFix {
    public static void main(String[] args) {
        System.out.println("Testing database fix...");
        
        try {
            // First fix the database
            backend.test.FixDatabase.fixCoursesTable();
            
            // Then test creating a course
            CourseService service = new CourseService();
            Course course = service.createCourse(1, "Mathematics");
            
            if (course != null) {
                System.out.println("SUCCESS: Course created with ID: " + course.getCourseId());
                System.out.println("Course name: " + course.getCourseName());
                System.out.println("Final grade: " + course.getFinalGrade());
            } else {
                System.err.println("FAILED: Course creation returned null");
            }
            
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getMessage());
            e.printStackTrace();
        }
    }
}