package backend.services;

import backend.dao.interfaces.AssessmentDao;
import backend.dao.impl.AssessmentDaoImpl;
import backend.model.Assessment;
import java.util.List;

public class AssessmentService {
    private AssessmentDao assessmentDao;
    
    public AssessmentService() {
        this.assessmentDao = new AssessmentDaoImpl();
    }
    
    public boolean deleteAssessmentsByCourse(int courseId) {
        try {
            return assessmentDao.deleteAssessmentsByCourse(courseId);
        } catch (Exception e) {
            System.err.println("[ASSESSMENT SERVICE] Error deleting assessments by course: " + e.getMessage());
            return false;
        }
    }
    
    public Assessment saveAssessment(int courseId, String assessmentName, 
                                    double score, double maxScore, double percentage, 
                                    double calculatedGrade) {
        try {
           
            Assessment assessment = new Assessment(courseId, assessmentName, 
                score, maxScore, percentage, calculatedGrade);
            
            return assessmentDao.createAssessment(assessment);
            
        } catch (Exception e) {
            System.err.println("[ASSESSMENT SERVICE] Error saving assessment: " + e.getMessage());
            return null;
        }
    }
    
    public List<Assessment> getCourseAssessments(int courseId) {
        try {
            return assessmentDao.getAssessmentsByCourse(courseId);
        } catch (Exception e) {
            System.err.println("[ASSESSMENT SERVICE] Error getting assessments: " + e.getMessage());
            return null;
        }
    }
    
    public double getCourseFinalGrade(int courseId) {
        try {
            return assessmentDao.calculateCourseFinalGrade(courseId);
        } catch (Exception e) {
            System.err.println("[ASSESSMENT SERVICE] Error calculating final grade: " + e.getMessage());
            return 0.0;
        }
    }
    
    public boolean deleteAssessment(int assessmentId) {
        try {
            return assessmentDao.deleteAssessment(assessmentId);
        } catch (Exception e) {
            System.err.println("[ASSESSMENT SERVICE] Error deleting assessment: " + e.getMessage());
            return false;
        }
    }
}