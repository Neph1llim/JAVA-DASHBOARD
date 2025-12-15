package backend.dao.impl;

import backend.dao.interfaces.CourseDao;
import backend.model.Course;
import backend.exceptions.DatabaseException;
import backend.db.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourseDaoImpl implements CourseDao {
    private final DatabaseConnection dbConnection;
    
    public CourseDaoImpl() {
        this.dbConnection = DatabaseConnection.getInstance();
    }
    
    @Override  // FIXED: Changed from create() to createCourse()
    public Course createCourse(Course course) throws DatabaseException {
        String sql = "INSERT INTO courses (course_name, user_id, course_year, course_grades, " +
                    "created_at) VALUES (?, ?, ?, ?, ?)";
        
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            pstmt.setString(1, course.getCourseName());
            pstmt.setInt(2, course.getUserId());
            pstmt.setInt(3, course.getCourseYear());
            pstmt.setDouble(4, course.getCourseGrades());
            pstmt.setDate(5, Date.valueOf(course.getCreatedDate()));
            
            int affectedRows = pstmt.executeUpdate();
            
            if (affectedRows > 0) {
                try (ResultSet rs = pstmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        course.setCourseId(rs.getInt(1));
                    }
                }
            }
            
            return course;
            
        } catch (SQLException e) {
            throw new DatabaseException("Failed to create course: " + e.getMessage(), e);
        }
    }
    
    @Override  // FIXED: Changed from findById() to getCourseById()
    public Course getCourseById(int courseId) throws DatabaseException {
        String sql = "SELECT * FROM courses WHERE course_id = ?";
        
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, courseId);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return mapResultSetToCourse(rs);
            }
            
            return null;
            
        } catch (SQLException e) {
            throw new DatabaseException("Failed to find course: " + e.getMessage(), e);
        }
    }
    
    @Override  // FIXED: Changed from findByUserId() to getCoursesByUser()
    public List<Course> getCoursesByUser(int userId) throws DatabaseException {
        List<Course> courses = new ArrayList<>();
        String sql = "SELECT * FROM courses WHERE user_id = ? ORDER BY created_at DESC";
        
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                courses.add(mapResultSetToCourse(rs));
            }
            
            return courses;
            
        } catch (SQLException e) {
            throw new DatabaseException("Failed to find user courses: " + e.getMessage(), e);
        }
    }
    
    @Override
    public List<Course> findAll() throws DatabaseException {
        List<Course> courses = new ArrayList<>();
        String sql = "SELECT * FROM courses ORDER BY course_name";
        
        try (Connection conn = dbConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                courses.add(mapResultSetToCourse(rs));
            }
            
            return courses;
            
        } catch (SQLException e) {
            throw new DatabaseException("Failed to retrieve courses: " + e.getMessage(), e);
        }
    }
    
    @Override  // FIXED: Changed from update() to updateCourse()
    public boolean updateCourse(Course course) throws DatabaseException {
        String sql = "UPDATE courses SET course_name = ?, user_id = ?, course_year = ?, " +
                    "course_grades = ?, created_at = ? WHERE course_id = ?";
        
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, course.getCourseName());
            pstmt.setInt(2, course.getUserId());
            pstmt.setInt(3, course.getCourseYear());
            pstmt.setDouble(4, course.getCourseGrades());
            pstmt.setDate(5, Date.valueOf(course.getCreatedDate()));
            pstmt.setInt(6, course.getCourseId());
            
            return pstmt.executeUpdate() > 0;
            
        } catch (SQLException e) {
            throw new DatabaseException("Failed to update course: " + e.getMessage(), e);
        }
    }
    
    @Override  // FIXED: Changed from delete() to deleteCourse()
    public boolean deleteCourse(int courseId) throws DatabaseException {
        String sql = "DELETE FROM courses WHERE course_id = ?";
        
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, courseId);
            return pstmt.executeUpdate() > 0;
            
        } catch (SQLException e) {
            throw new DatabaseException("Failed to delete course: " + e.getMessage(), e);
        }
    }
    
    @Override
    public List<Course> searchCourses(String keyword) throws DatabaseException {
        List<Course> courses = new ArrayList<>();
        String sql = "SELECT * FROM courses WHERE course_name LIKE ? ORDER BY course_name";
        
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            String searchPattern = "%" + keyword + "%";
            pstmt.setString(1, searchPattern);
            
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                courses.add(mapResultSetToCourse(rs));
            }
            
            return courses;
            
        } catch (SQLException e) {
            throw new DatabaseException("Failed to search courses: " + e.getMessage(), e);
        }
    }
    
    @Override
    public List<Course> findUpcomingCourses(int userId) throws DatabaseException {
        // Note: Your courses table doesn't have start_date, so this might need adjustment
        List<Course> courses = new ArrayList<>();
        String sql = "SELECT * FROM courses WHERE user_id = ? ORDER BY created_at DESC LIMIT 10";
        
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                courses.add(mapResultSetToCourse(rs));
            }
            
            return courses;
            
        } catch (SQLException e) {
            throw new DatabaseException("Failed to find upcoming courses: " + e.getMessage(), e);
        }
    }
    
    @Override  // FIXED: Added missing method
    public List<Course> findCompletedCourses(int userId) throws DatabaseException {
        // Assuming completed courses have course_grades > 0 or some completion flag
        List<Course> courses = new ArrayList<>();
        String sql = "SELECT * FROM courses WHERE user_id = ? AND course_grades > 0 " +
                    "ORDER BY course_grades DESC";
        
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                courses.add(mapResultSetToCourse(rs));
            }
            
            return courses;
            
        } catch (SQLException e) {
            throw new DatabaseException("Failed to find completed courses: " + e.getMessage(), e);
        }
    }
    
    @Override  // FIXED: Changed from enrollUser() to enrollUserToCourse()
    public boolean enrollUserToCourse(int courseId, int userId) throws DatabaseException {
        String sql = "INSERT INTO course_enrollments (course_id, user_id, enrolled_at) " +
                    "VALUES (?, ?, NOW()) ON DUPLICATE KEY UPDATE enrolled_at = NOW()";
        
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, courseId);
            pstmt.setInt(2, userId);
            return pstmt.executeUpdate() > 0;
            
        } catch (SQLException e) {
            throw new DatabaseException("Failed to enroll user: " + e.getMessage(), e);
        }
    }
    
    @Override  // FIXED: Added missing method (note parameter order: courseId, userId)
    public boolean unenrollUser(int courseId, int userId) throws DatabaseException {
        String sql = "DELETE FROM course_enrollments WHERE course_id = ? AND user_id = ?";
        
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, courseId);
            pstmt.setInt(2, userId);
            return pstmt.executeUpdate() > 0;
            
        } catch (SQLException e) {
            throw new DatabaseException("Failed to unenroll user: " + e.getMessage(), e);
        }
    }
    
    @Override
    public List<Integer> getEnrolledUsers(int courseId) throws DatabaseException {
        List<Integer> userIds = new ArrayList<>();
        String sql = "SELECT user_id FROM course_enrollments WHERE course_id = ?";
        
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, courseId);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                userIds.add(rs.getInt("user_id"));
            }
            
            return userIds;
            
        } catch (SQLException e) {
            throw new DatabaseException("Failed to get enrolled users: " + e.getMessage(), e);
        }
    }
    
    private Course mapResultSetToCourse(ResultSet rs) throws SQLException {
        Course course = new Course();
        course.setCourseId(rs.getInt("course_id"));
        course.setCourseName(rs.getString("course_name"));
        course.setCourseYear(rs.getInt("course_year"));
        course.setCourseGrades(rs.getDouble("course_grades"));
        
        Date createdAt = rs.getDate("created_at");
        if (createdAt != null) {
            course.setCreatedDate(createdAt.toLocalDate());
        }
        
        course.setUserId(rs.getInt("user_id"));
        return course;
    }
}