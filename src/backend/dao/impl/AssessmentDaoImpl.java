package backend.dao.impl;

import backend.dao.interfaces.AssessmentDao;
import backend.model.Assessment;
import backend.exceptions.DatabaseException;
import backend.db.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AssessmentDaoImpl implements AssessmentDao {
    private final DatabaseConnection dbConnection;
    
    public AssessmentDaoImpl() {
        this.dbConnection = DatabaseConnection.getInstance();
    }
    
    @Override
    public Assessment createAssessment(Assessment assessment) throws DatabaseException {
        String sql = "INSERT INTO Assessments (course_id, assessment_name, score, " +
                    "max_score, percentage, calculated_grade, created_at) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            pstmt.setInt(1, assessment.getCourseId());
            pstmt.setString(2, assessment.getAssessmentName());
            pstmt.setDouble(3, assessment.getScore());
            pstmt.setDouble(4, assessment.getMaxScore());
            pstmt.setDouble(5, assessment.getPercentage());
            pstmt.setDouble(6, assessment.getCalculatedGrade());
            pstmt.setDate(7, Date.valueOf(assessment.getCreatedDate()));
            
            int affectedRows = pstmt.executeUpdate();
            
            if (affectedRows > 0) {
                try (ResultSet rs = pstmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        assessment.setAssessmentId(rs.getInt(1));
                    }
                }
            }
            
            // Update course final grade
            updateCourseFinalGrade(assessment.getCourseId());
            
            return assessment;
            
        } catch (SQLException e) {
            throw new DatabaseException("Failed to create assessment: " + e.getMessage(), e);
        }
    }
    
    @Override
    public List<Assessment> getAssessmentsByCourse(int courseId) throws DatabaseException {
        List<Assessment> assessments = new ArrayList<>();
        String sql = "SELECT * FROM Assessments WHERE course_id = ? ORDER BY created_at";
        
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, courseId);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                assessments.add(mapResultSetToAssessment(rs));
            }
            
            return assessments;
            
        } catch (SQLException e) {
            throw new DatabaseException("Failed to get assessments: " + e.getMessage(), e);
        }
    }
    
    @Override
    public boolean deleteAssessment(int assessmentId) throws DatabaseException {
        String sql = "DELETE FROM Assessments WHERE assessment_id = ?";
        
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            // Get course_id before deleting to update final grade
            int courseId = getCourseIdByAssessment(assessmentId);
            
            pstmt.setInt(1, assessmentId);
            boolean deleted = pstmt.executeUpdate() > 0;
            
            if (deleted && courseId > 0) {
                updateCourseFinalGrade(courseId);
            }
            
            return deleted;
            
        } catch (SQLException e) {
            throw new DatabaseException("Failed to delete assessment: " + e.getMessage(), e);
        }
    }
    
    @Override
    public double calculateCourseFinalGrade(int courseId) throws DatabaseException {
        String sql = "SELECT SUM(calculated_grade) as total_grade FROM Assessments WHERE course_id = ?";
        
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, courseId);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return rs.getDouble("total_grade");
            }
            
            return 0.0;
            
        } catch (SQLException e) {
            throw new DatabaseException("Failed to calculate final grade: " + e.getMessage(), e);
        }
    }
    
    private void updateCourseFinalGrade(int courseId) throws DatabaseException {
        double finalGrade = calculateCourseFinalGrade(courseId);
        
        String sql = "UPDATE Courses SET final_grade = ? WHERE course_id = ?";
        
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setDouble(1, finalGrade);
            pstmt.setInt(2, courseId);
            pstmt.executeUpdate();
            
        } catch (SQLException e) {
            throw new DatabaseException("Failed to update course final grade: " + e.getMessage(), e);
        }
    }
    
    private int getCourseIdByAssessment(int assessmentId) throws DatabaseException {
        String sql = "SELECT course_id FROM Assessments WHERE assessment_id = ?";
        
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, assessmentId);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return rs.getInt("course_id");
            }
            
            return -1;
            
        } catch (SQLException e) {
            throw new DatabaseException("Failed to get course ID: " + e.getMessage(), e);
        }
    }
    
    private Assessment mapResultSetToAssessment(ResultSet rs) throws SQLException {
        Assessment assessment = new Assessment();
        assessment.setAssessmentId(rs.getInt("assessment_id"));
        assessment.setCourseId(rs.getInt("course_id"));
        assessment.setAssessmentName(rs.getString("assessment_name"));
        assessment.setScore(rs.getDouble("score"));
        assessment.setMaxScore(rs.getDouble("max_score"));
        assessment.setPercentage(rs.getDouble("percentage"));
        assessment.setCalculatedGrade(rs.getDouble("calculated_grade"));
        
        Date createdAt = rs.getDate("created_at");
        if (createdAt != null) {
            assessment.setCreatedDate(createdAt.toLocalDate());
        }
        
        return assessment;
    }

    @Override
    public Assessment getAssessmentById(int assessmentId) throws DatabaseException {
    String sql = "SELECT * FROM Assessments WHERE assessment_id = ?";
    
    try (Connection conn = dbConnection.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        
        pstmt.setInt(1, assessmentId);
        ResultSet rs = pstmt.executeQuery();
        
        if (rs.next()) {
            return mapResultSetToAssessment(rs);
        }
        
        return null;
        
    } catch (SQLException e) {
        throw new DatabaseException("Failed to get assessment by id: " + e.getMessage(), e);
    }
}

    @Override
    public boolean deleteAssessmentsByCourse(int courseId) throws DatabaseException {
    String sql = "DELETE FROM Assessments WHERE course_id = ?";
    
    try (Connection conn = dbConnection.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        
        pstmt.setInt(1, courseId);
        boolean deleted = pstmt.executeUpdate() > 0;
        
        if (deleted) {
            updateCourseFinalGrade(courseId);
        }
        
        return deleted;
        
    } catch (SQLException e) {
        throw new DatabaseException("Failed to delete assessments by course: " + e.getMessage(), e);
    }
    
    }
        @Override
    public boolean updateAssessment(Assessment assessment) throws DatabaseException {
        String sql = "UPDATE Assessments SET assessment_name = ?, score = ?, " +
                    "max_score = ?, percentage = ?, calculated_grade = ? " +
                    "WHERE assessment_id = ?";
        
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, assessment.getAssessmentName());
            pstmt.setDouble(2, assessment.getScore());
            pstmt.setDouble(3, assessment.getMaxScore());
            pstmt.setDouble(4, assessment.getPercentage());
            pstmt.setDouble(5, assessment.getCalculatedGrade());
            pstmt.setInt(6, assessment.getAssessmentId());
            
            boolean updated = pstmt.executeUpdate() > 0;
            
            if (updated) {
                updateCourseFinalGrade(assessment.getCourseId());
            }
            
            return updated;
            
        } catch (SQLException e) {
            throw new DatabaseException("Failed to update assessment: " + e.getMessage(), e);
        }
    }
}