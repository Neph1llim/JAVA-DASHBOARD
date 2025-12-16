package backend.dao.interfaces;

import backend.model.Assessment;
import backend.exceptions.DatabaseException;
import java.util.List;

public interface AssessmentDao {
    Assessment createAssessment(Assessment assessment) throws DatabaseException;
    Assessment getAssessmentById(int assessmentId) throws DatabaseException;
    List<Assessment> getAssessmentsByCourse(int courseId) throws DatabaseException;
    boolean updateAssessment(Assessment assessment) throws DatabaseException;
    boolean deleteAssessment(int assessmentId) throws DatabaseException;
    boolean deleteAssessmentsByCourse(int courseId) throws DatabaseException;
    double calculateCourseFinalGrade(int courseId) throws DatabaseException;
}