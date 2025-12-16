package backend.model;

import java.time.LocalDate;

public class Assessment {
    private int assessmentId;
    private int courseId;
    private String assessmentName;
    private double score;
    private double maxScore;
    private double percentage;
    private double calculatedGrade;
    private LocalDate createdAt;
    
    // Constructors
    public Assessment() {}
    
    public Assessment(int courseId, String assessmentName, double score, 
                     double maxScore, double percentage, double calculatedGrade) {
        this.courseId = courseId;
        this.assessmentName = assessmentName;
        this.score = score;
        this.maxScore = maxScore;
        this.percentage = percentage;
        this.calculatedGrade = calculatedGrade;
        this.createdAt = LocalDate.now();
    }
    
    // Getters and setters
    public int getAssessmentId() { return assessmentId; }
    public void setAssessmentId(int assessmentId) { this.assessmentId = assessmentId; }
    
    public int getCourseId() { return courseId; }
    public void setCourseId(int courseId) { this.courseId = courseId; }
    
    public String getAssessmentName() { return assessmentName; }
    public void setAssessmentName(String assessmentName) { this.assessmentName = assessmentName; }
    
    public double getScore() { return score; }
    public void setScore(double score) { this.score = score; }
    
    public double getMaxScore() { return maxScore; }
    public void setMaxScore(double maxScore) { this.maxScore = maxScore; }
    
    public double getPercentage() { return percentage; }
    public void setPercentage(double percentage) { this.percentage = percentage; }
    
    public double getCalculatedGrade() { return calculatedGrade; }
    public void setCalculatedGrade(double calculatedGrade) { this.calculatedGrade = calculatedGrade; }
    
    public LocalDate getCreatedDate() { return createdAt; }
    public void setCreatedDate(LocalDate createdAt) { this.createdAt = createdAt; }
}