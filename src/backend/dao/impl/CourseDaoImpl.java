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
    
    @Override
    public Course createCourse(Course course) throws DatabaseException {
    // Check if final_grade column exists
    boolean hasFinalGradeColumn = checkColumnExists("courses", "final_grade");
    
    String sql;
    if (hasFinalGradeColumn) {
        sql = "INSERT INTO courses (course_name, user_id, course_grades, final_grade, created_at) " +
              "VALUES (?, ?, ?, ?, ?)";
    } else {
        // Fallback for old schema
        sql = "INSERT INTO courses (course_name, user_id, course_grades, created_at) " +
              "VALUES (?, ?, ?, ?)";
    }
    
    try (Connection conn = dbConnection.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
        
        int paramIndex = 1;
        pstmt.setString(paramIndex++, course.getCourseName());
        pstmt.setInt(paramIndex++, course.getUserId());
        pstmt.setDouble(paramIndex++, course.getCourseGrades());
        
        if (hasFinalGradeColumn) {
            pstmt.setDouble(paramIndex++, course.getFinalGrade());
        }
        
        pstmt.setDate(paramIndex, Date.valueOf(course.getCreatedDate()));
        
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

    
    private boolean checkColumnExists(String tableName, String columnName) throws DatabaseException {
        String sql = "SELECT COUNT(*) FROM information_schema.columns " +
                     "WHERE table_schema = DATABASE() AND table_name = ? AND column_name = ?";

        try (Connection conn = dbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, tableName);
            pstmt.setString(2, columnName);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
            return false;

        } catch (SQLException e) {
            throw new DatabaseException("Failed to check column existence: " + e.getMessage(), e);
        }
    }
    
    @Override
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
    
    @Override
    public List<Course> getCoursesByUser(int userId) throws DatabaseException {
        List<Course> courses = new ArrayList<>();
        String sql = "SELECT * FROM courses WHERE user_id = ? ORDER BY course_name";
        
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
    
    @Override
    public boolean updateCourse(Course course) throws DatabaseException {
        String sql = "UPDATE courses SET course_name = ?, user_id = ?, course_grades = ?, " +
                    "final_grade = ? WHERE course_id = ?";
        
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, course.getCourseName());
            pstmt.setInt(2, course.getUserId());
            pstmt.setDouble(3, course.getCourseGrades());
            pstmt.setDouble(4, course.getFinalGrade());
            pstmt.setInt(5, course.getCourseId());
            
            return pstmt.executeUpdate() > 0;
            
        } catch (SQLException e) {
            throw new DatabaseException("Failed to update course: " + e.getMessage(), e);
        }
    }
    
    @Override
    public boolean deleteCourse(int courseId) throws DatabaseException {
        // First delete assessments for this course
        String deleteAssessmentsSql = "DELETE FROM Assessments WHERE course_id = ?";
        String deleteCourseSql = "DELETE FROM Courses WHERE course_id = ?";

        try (Connection conn = dbConnection.getConnection()) {
            // Start transaction
            conn.setAutoCommit(false);

            try {
                // Delete assessments first (foreign key constraint)
                try (PreparedStatement pstmt1 = conn.prepareStatement(deleteAssessmentsSql)) {
                    pstmt1.setInt(1, courseId);
                    pstmt1.executeUpdate();
                }

                // Delete the course
                try (PreparedStatement pstmt2 = conn.prepareStatement(deleteCourseSql)) {
                    pstmt2.setInt(1, courseId);
                    boolean courseDeleted = pstmt2.executeUpdate() > 0;

                    if (courseDeleted) {
                        conn.commit();
                        return true;
                    } else {
                        conn.rollback();
                        return false;
                    }
                }

            } catch (SQLException e) {
                conn.rollback();
                throw new DatabaseException("Failed to delete course and assessments: " + e.getMessage(), e);
            } finally {
                conn.setAutoCommit(true);
            }

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
    
    private Course mapResultSetToCourse(ResultSet rs) throws SQLException {
    Course course = new Course();
    course.setCourseId(rs.getInt("course_id"));
    course.setCourseName(rs.getString("course_name"));
    course.setCourseGrades(rs.getDouble("course_grades"));
    
    // Try to get final_grade, but handle if it doesn't exist
    try {
        course.setFinalGrade(rs.getDouble("final_grade"));
    } catch (SQLException e) {
        // Column doesn't exist, set default
        course.setFinalGrade(0.0);
    }
    
    Date createdAt = rs.getDate("created_at");
    if (createdAt != null) {
        course.setCreatedDate(createdAt.toLocalDate());
    }
    
    course.setUserId(rs.getInt("user_id"));
    return course;
}
}