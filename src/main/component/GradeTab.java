package main.component;

import javax.swing.JOptionPane;
import backend.services.CourseService;
import backend.services.AssessmentService;
import backend.model.Course;
import backend.model.Assessment;
import java.util.List;

public class GradeTab extends javax.swing.JPanel {
    private CourseService courseService;
    private AssessmentService assessmentService;
    private int courseId; // Store course ID for this tab
    private String courseName; // Store course name
    
    public GradeTab() {
        initComponents();
        findPanels();
        setupLayout();
        
        this.courseId = -1; // No database ID
        initializeServices();
    }
    
    private void findPanels() {
        // Find centerPanel and buttonPanel from contentPanel
        for (java.awt.Component comp : contentPanel.getComponents()) {
            if (comp.getName() != null && comp.getName().equals("centerPanel")) {
                centerPanel = (javax.swing.JPanel) comp;
            }
            if (comp.getName() != null && comp.getName().equals("buttonPanel")) {
                buttonPanel = (javax.swing.JPanel) comp;
            }
        }
        
        // If not found by name, try by position
        if (centerPanel == null && contentPanel.getComponentCount() > 0) {
            java.awt.BorderLayout layout = (java.awt.BorderLayout) contentPanel.getLayout();
            centerPanel = (javax.swing.JPanel) layout.getLayoutComponent(java.awt.BorderLayout.CENTER);
        }
        if (buttonPanel == null && contentPanel.getComponentCount() > 0) {
            java.awt.BorderLayout layout = (java.awt.BorderLayout) contentPanel.getLayout();
            buttonPanel = (javax.swing.JPanel) layout.getLayoutComponent(java.awt.BorderLayout.SOUTH);
        }
    }
    
    private void setupLayout() {
        if (centerPanel == null) {
            centerPanel = new javax.swing.JPanel();
            centerPanel.setLayout(new javax.swing.BoxLayout(centerPanel, javax.swing.BoxLayout.Y_AXIS));
            centerPanel.setOpaque(false);
            contentPanel.add(centerPanel, java.awt.BorderLayout.CENTER);
        }
        
        if (buttonPanel == null) {
            buttonPanel = new javax.swing.JPanel();
            buttonPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));
            buttonPanel.setOpaque(false);
            
            // Find and move button1 to buttonPanel
            for (java.awt.Component comp : contentPanel.getComponents()) {
                if (comp instanceof main.component.Button && comp.getName() != null && comp.getName().equals("button1")) {
                    contentPanel.remove(comp);
                    buttonPanel.add(comp);
                    break;
                }
            }
            contentPanel.add(buttonPanel, java.awt.BorderLayout.SOUTH);
        }
        
        contentPanel.revalidate();
        contentPanel.repaint();
    }
    
    private void saveAllAssessments() {
    if (courseId <= 0) {
        JOptionPane.showMessageDialog(this,
            "This course is not saved to the database yet. Please save the course first.",
            "Course Not Saved",
            JOptionPane.WARNING_MESSAGE);
        return;
    }

    if (assessmentService == null) {
        JOptionPane.showMessageDialog(this,
            "Unable to connect to database service. Please check your connection.",
            "Service Error",
            JOptionPane.ERROR_MESSAGE);
        return;
    }
    
    int savedCount = 0;
    int errorCount = 0;
    StringBuilder errorMessages = new StringBuilder();
    
    // Check if there are any assessments to save
    int assessmentCount = 0;
    for (java.awt.Component comp : centerPanel.getComponents()) {
        if (comp instanceof AddGrade) {
            assessmentCount++;
        }
    }
    
    if (assessmentCount == 0) {
        JOptionPane.showMessageDialog(this,
            "No assessments found to save. Please add assessments first.",
            "No Assessments",
            JOptionPane.INFORMATION_MESSAGE);
        return;
    }
    
    // Collect all AddGrade components from centerPanel
    for (java.awt.Component comp : centerPanel.getComponents()) {
        if (comp instanceof AddGrade) {
            AddGrade grade = (AddGrade) comp;
            
            // Call calculateGrade to ensure latest values are calculated
            grade.calculateGrade();
            
            // Validate the grade entry
            if (!grade.isValidEntry()) {
                errorCount++;
                errorMessages.append("• Invalid grade calculation for: ")
                            .append(grade.getAssessmentName()).append("\n");
                continue;
            }
            
            // Check for empty assessment name
            String assessmentName = grade.getAssessmentName().trim();
            if (assessmentName.isEmpty() || assessmentName.equals("Enter Assessment Name")) {
                errorCount++;
                errorMessages.append("• Missing assessment name\n");
                continue;
            }
            
            try {
                // Get values including the CALCULATED GRADE
                double score = Double.parseDouble(grade.getScore());
                double maxScore = Double.parseDouble(grade.getMaxScore());
                double percentage = grade.getPercentageValue();
                double calculatedGrade = grade.getGradeValue();  // GET THE CALCULATED GRADE
                
                // Validate percentage range
                if (percentage < 0 || percentage > 100) {
                    errorCount++;
                    errorMessages.append("• Invalid percentage for: ").append(assessmentName)
                                .append(" (must be 0-100)\n");
                    continue;
                }
                
                // Validate score <= maxScore
                if (score > maxScore) {
                    errorCount++;
                    errorMessages.append("• Score cannot exceed max score for: ")
                                .append(assessmentName).append("\n");
                    continue;
                }
                
                // Validate maxScore > 0
                if (maxScore <= 0) {
                    errorCount++;
                    errorMessages.append("• Max score must be > 0 for: ")
                                .append(assessmentName).append("\n");
                    continue;
                }
                
                // Save to database WITH CALCULATED GRADE
                Assessment saved = assessmentService.saveAssessment(
                    courseId, assessmentName, score, maxScore, percentage, calculatedGrade);
                
                if (saved != null) {
                    savedCount++;
                    System.out.println("Saved assessment: " + assessmentName + 
                                     " - Percentage: " + percentage + 
                                     " - Calculated Grade: " + calculatedGrade);
                } else {
                    errorCount++;
                    errorMessages.append("• Failed to save: ").append(assessmentName).append("\n");
                }
                
            } catch (NumberFormatException e) {
                errorCount++;
                errorMessages.append("• Invalid number format for: ").append(assessmentName).append("\n");
            } catch (Exception e) {
                errorCount++;
                errorMessages.append("• Error saving ").append(assessmentName)
                            .append(": ").append(e.getMessage()).append("\n");
                e.printStackTrace();
            }
        }
    }
    
    // SHOW SUMMARY MESSAGE TO USER
    if (errorCount == 0) {
        if (savedCount > 0) {
            JOptionPane.showMessageDialog(this,
                "Successfully saved " + savedCount + " assessment(s).",
                "Save Successful",
                JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this,
                "No assessments were saved. Please check your inputs.",
                "No Data Saved",
                JOptionPane.INFORMATION_MESSAGE);
        }
    } else {
        // Show error summary
        String errorSummary = "Encountered " + errorCount + " error(s) while saving:\n\n" 
                           + errorMessages.toString();
        
        // Truncate if too long
        if (errorSummary.length() > 500) {
            errorSummary = errorSummary.substring(0, 500) + "\n... (more errors truncated)";
        }
        
        JOptionPane.showMessageDialog(this,
            errorSummary,
            "Save Errors",
            JOptionPane.ERROR_MESSAGE);
    }
}
    
    private void initializeServices() {
        if (courseId > 0) {
            try {
                this.assessmentService = new AssessmentService();
                
                // Enable save button if it exists
                if (saveAllButton != null) {
                    saveAllButton.setEnabled(true);
                    saveAllButton.setBackground(new java.awt.Color(86, 134, 254));
                }
                
                loadSavedAssessments();
                
            } catch (Exception e) {
                System.err.println("Failed to initialize assessment service: " + e.getMessage());
                
                // Disable save button
                if (saveAllButton != null) {
                    saveAllButton.setEnabled(false);
                    saveAllButton.setBackground(new java.awt.Color(153, 153, 153));
                }
            }
        }
    }
    
    private void loadSavedAssessments() {
        if (assessmentService == null || courseId <= 0) return;
        
        try {
            List<Assessment> assessments = assessmentService.getCourseAssessments(courseId);
            if (assessments != null) {
                for (Assessment assessment : assessments) {
                    AddGrade gradeComponent = new AddGrade();
                    gradeComponent.setAssessmentName(assessment.getAssessmentName());
                    gradeComponent.setScore(String.valueOf(assessment.getScore()));
                    gradeComponent.setMaxScore(String.valueOf(assessment.getMaxScore()));
                    gradeComponent.setPercentage(String.valueOf(assessment.getPercentage()));
                    gradeComponent.calculateGrade();
                    
                    if (centerPanel == null) {
                        setupLayout();
                    }
                    
                    centerPanel.add(gradeComponent);
                    gradeComponent.setMaximumSize(new java.awt.Dimension(
                        Integer.MAX_VALUE, 
                        gradeComponent.getPreferredSize().height
                    ));
                }
                
                if (centerPanel != null) {
                    centerPanel.revalidate();
                    centerPanel.repaint();
                }
            }
        } catch (Exception e) {
            System.err.println("Error loading assessments: " + e.getMessage());
        }
    }
    
    // GETTERS AND SETTERS (keep only one set)
    public int getCourseId() { 
        return courseId; 
    }
    
    public void setCourseId(int courseId) { 
        this.courseId = courseId;
        if (courseId > 0) {
            initializeServices();
            
            // Enable save button if it exists
            if (saveAllButton != null) {
                saveAllButton.setEnabled(true);
                saveAllButton.setBackground(new java.awt.Color(86, 134, 254));
            }
        } else {
            // Disable save button for local-only courses
            if (saveAllButton != null) {
                saveAllButton.setEnabled(false);
            }
        }
    }
    
    public String getCourseName() { 
        return courseName; 
    }
    
    public void setCourseName(String courseName) { 
        this.courseName = courseName;
        if (this.getName() == null) {
            this.setName(courseName);
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        panel2 = new main.component.Panel();
        jScrollPane1 = new javax.swing.JScrollPane();
        contentPanel = new main.component.Panel();
        centerPanel = new javax.swing.JPanel();
        buttonPanel = new javax.swing.JPanel();
        saveAllButton = new main.component.Button();
        button1 = new main.component.Button();
        jPanel1 = new javax.swing.JPanel();
        panel3 = new main.component.Panel();
        jLabel1 = new javax.swing.JLabel();
        panel5 = new main.component.Panel();
        jLabel2 = new javax.swing.JLabel();
        panel6 = new main.component.Panel();
        jLabel3 = new javax.swing.JLabel();
        panel4 = new main.component.Panel();
        jLabel4 = new javax.swing.JLabel();

        setMaximumSize(null);
        setMinimumSize(new java.awt.Dimension(480, 255));
        setPreferredSize(new java.awt.Dimension(1440, 810));

        panel2.setArc(0);
        panel2.setPanelBackground(new java.awt.Color(21, 21, 23));
        panel2.setPreferredSize(new java.awt.Dimension(1440, 810));
        panel2.setLayout(new java.awt.GridBagLayout());

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setMaximumSize(null);
        jScrollPane1.setMinimumSize(new java.awt.Dimension(480, 255));

        contentPanel.setArc(0);
        contentPanel.setMinimumSize(new java.awt.Dimension(1440, 735));
        contentPanel.setVerifyInputWhenFocusTarget(false);
        contentPanel.setLayout(new java.awt.BorderLayout());

        centerPanel.setLayout(new javax.swing.BoxLayout(centerPanel, javax.swing.BoxLayout.Y_AXIS));
        contentPanel.add(centerPanel, java.awt.BorderLayout.CENTER);

        buttonPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        saveAllButton.setBackground(new java.awt.Color(51, 51, 51));
        saveAllButton.setText("Save");
        saveAllButton.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        saveAllButton.setPreferredSize(new java.awt.Dimension(80, 40));
        saveAllButton.addActionListener(this::saveAllButtonActionPerformed);
        buttonPanel.add(saveAllButton);

        button1.setBackground(new java.awt.Color(51, 51, 51));
        button1.setForeground(new java.awt.Color(0, 0, 0));
        button1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/resource/icons/add.png"))); // NOI18N
        button1.setFont(new java.awt.Font("Segoe UI Variable", 1, 12)); // NOI18N
        button1.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        button1.addActionListener(this::button1ActionPerformed);
        buttonPanel.add(button1);

        contentPanel.add(buttonPanel, java.awt.BorderLayout.SOUTH);

        jScrollPane1.setViewportView(contentPanel);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        panel2.add(jScrollPane1, gridBagConstraints);

        jPanel1.setMinimumSize(new java.awt.Dimension(1440, 75));
        jPanel1.setLayout(new java.awt.GridBagLayout());

        panel3.setBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.darkGray, null));
        panel3.setArc(0);
        panel3.setMaximumSize(new java.awt.Dimension(360, 75));
        panel3.setMinimumSize(new java.awt.Dimension(360, 75));
        panel3.setPanelBackground(new java.awt.Color(51, 51, 51));
        panel3.setPreferredSize(new java.awt.Dimension(360, 75));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("Assessment");

        javax.swing.GroupLayout panel3Layout = new javax.swing.GroupLayout(panel3);
        panel3.setLayout(panel3Layout);
        panel3Layout.setHorizontalGroup(
            panel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel3Layout.createSequentialGroup()
                .addContainerGap(98, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(159, 159, 159))
        );
        panel3Layout.setVerticalGroup(
            panel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel3Layout.createSequentialGroup()
                .addContainerGap(21, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(25, 25, 25))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        jPanel1.add(panel3, gridBagConstraints);

        panel5.setBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.darkGray, null));
        panel5.setArc(0);
        panel5.setMaximumSize(new java.awt.Dimension(360, 75));
        panel5.setMinimumSize(new java.awt.Dimension(360, 75));
        panel5.setPanelBackground(new java.awt.Color(51, 51, 51));
        panel5.setPreferredSize(new java.awt.Dimension(360, 75));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setText("Score");

        javax.swing.GroupLayout panel5Layout = new javax.swing.GroupLayout(panel5);
        panel5.setLayout(panel5Layout);
        panel5Layout.setHorizontalGroup(
            panel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel5Layout.createSequentialGroup()
                .addContainerGap(127, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(182, 182, 182))
        );
        panel5Layout.setVerticalGroup(
            panel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel5Layout.createSequentialGroup()
                .addContainerGap(21, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(25, 25, 25))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        jPanel1.add(panel5, gridBagConstraints);

        panel6.setBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.darkGray, null));
        panel6.setArc(0);
        panel6.setMaximumSize(new java.awt.Dimension(360, 75));
        panel6.setMinimumSize(new java.awt.Dimension(360, 75));
        panel6.setPanelBackground(new java.awt.Color(51, 51, 51));
        panel6.setPreferredSize(new java.awt.Dimension(360, 75));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setText("% of grade");

        javax.swing.GroupLayout panel6Layout = new javax.swing.GroupLayout(panel6);
        panel6.setLayout(panel6Layout);
        panel6Layout.setHorizontalGroup(
            panel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel6Layout.createSequentialGroup()
                .addContainerGap(103, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(160, 160, 160))
        );
        panel6Layout.setVerticalGroup(
            panel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel6Layout.createSequentialGroup()
                .addContainerGap(21, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(25, 25, 25))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        jPanel1.add(panel6, gridBagConstraints);

        panel4.setBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.darkGray, null));
        panel4.setArc(0);
        panel4.setMaximumSize(new java.awt.Dimension(360, 75));
        panel4.setMinimumSize(new java.awt.Dimension(360, 75));
        panel4.setPanelBackground(new java.awt.Color(51, 51, 51));
        panel4.setPreferredSize(new java.awt.Dimension(360, 75));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setText("Grade");

        javax.swing.GroupLayout panel4Layout = new javax.swing.GroupLayout(panel4);
        panel4.setLayout(panel4Layout);
        panel4Layout.setHorizontalGroup(
            panel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel4Layout.createSequentialGroup()
                .addGap(179, 179, 179)
                .addComponent(jLabel4)
                .addContainerGap(126, Short.MAX_VALUE))
        );
        panel4Layout.setVerticalGroup(
            panel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel4Layout.createSequentialGroup()
                .addContainerGap(21, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addGap(25, 25, 25))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        jPanel1.add(panel4, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.weightx = 1.0;
        panel2.add(jPanel1, gridBagConstraints);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1440, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(panel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 810, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(panel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button1ActionPerformed
    AddGrade newGrade = new AddGrade();
        
        if (centerPanel == null) {
            setupLayout();
        }
        
        centerPanel.add(newGrade);
        newGrade.setMaximumSize(new java.awt.Dimension(
            Integer.MAX_VALUE, 
            newGrade.getPreferredSize().height
        ));
        
        centerPanel.revalidate();
        centerPanel.repaint();
        newGrade.scrollRectToVisible(newGrade.getBounds());
    }//GEN-LAST:event_button1ActionPerformed

    private void saveAllButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveAllButtonActionPerformed
    saveAllAssessments();
    }//GEN-LAST:event_saveAllButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private main.component.Button button1;
    private javax.swing.JPanel buttonPanel;
    private javax.swing.JPanel centerPanel;
    private main.component.Panel contentPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private main.component.Panel panel2;
    private main.component.Panel panel3;
    private main.component.Panel panel4;
    private main.component.Panel panel5;
    private main.component.Panel panel6;
    private main.component.Button saveAllButton;
    // End of variables declaration//GEN-END:variables
}