
package main.component;


public class AddGrade extends javax.swing.JPanel {

    private double percentageValue;
    private double scoreValue;
    private double maxScoreValue;
    
    public AddGrade() {
        initComponents();
        setupDefaults();
        setupListeners();
    }
    
    public Assessment toAssessment(int courseId) {
    Assessment assessment = new Assessment();
    assessment.setCourseId(courseId);
    assessment.setAssessmentName(this.getAssessmentName());
    assessment.setScore(this.getGradeValue()); // Using the calculated grade
    assessment.setMaxScore(100.0); // Assuming max is 100 for grade contribution
    assessment.setPercentage(this.getPercentageValue());
    assessment.setCalculatedGrade(this.getGradeValue());
    return assessment;
}

    public Object[] getAssessmentData() {
        return new Object[] {
            getAssessmentName(),
            getScore(),
            getMaxScore(),
            getPercentage(),
            getCalculatedGrade()
        };
    }
    
    private void setupDefaults() {
        // Placeholder text
        jTextField1.setText("Enter Assessment Name");
        jTextField2.setText("0"); // Score
        jTextField6.setText("100"); // Max Score
        jTextField4.setText("0"); // Percentage
        jTextField5.setText("0.0"); // Calculated Grade
        
        // Initialize values
        scoreValue = 0;
        maxScoreValue = 100;
        percentageValue = 0;
    }
    
    private void setupListeners() {
        jTextField2.addActionListener(e -> calculateGrade());
        jTextField6.addActionListener(e -> calculateGrade());
        jTextField4.addActionListener(e -> calculateGrade());
    }
    
    public void calculateGrade() {
        try {
            // Get values from text fields
            String scoreText = jTextField2.getText().trim();
            String maxText = jTextField6.getText().trim();
            String percentText = jTextField4.getText().trim();
            
            scoreValue = scoreText.isEmpty() ? 0 : Double.parseDouble(scoreText);
            maxScoreValue = maxText.isEmpty() ? 100 : Double.parseDouble(maxText);
            percentageValue = percentText.isEmpty() ? 0 : Double.parseDouble(percentText);
            
            // Validate inputs
            if (maxScoreValue <= 0) {
                jTextField5.setText("Invalid input.");
                return;
            }
            
            if (scoreValue > maxScoreValue) {
                jTextField5.setText("Invalid input.");
                return;
            }
            
            if (percentageValue <= 0 || percentageValue > 100) {
                jTextField5.setText("Invalid input.");
                return;
            }
            
            // Calculate grade contribution for this assessment
            // (score/max) * (percentage/100) * 100
            double gradeContribution = (scoreValue / maxScoreValue) * (percentageValue / 100.0) * 100.0;
            
            // Format and display
            jTextField5.setText(String.format("%.2f", gradeContribution));
            
        } catch (NumberFormatException e) {
            jTextField5.setText("Invalid Input");
        }
    }
    public String getAssessmentName() {
        return jTextField1.getText();
    }

    public void setAssessmentName(String name) {
        jTextField1.setText(name);
    }

    public String getScore() {
        return jTextField2.getText();
    }

    public void setScore(String score) {
        jTextField2.setText(score);
        calculateGrade(); // Recalculate when score changes
    }

    public String getMaxScore() {
        return jTextField6.getText();
    }

    public void setMaxScore(String maxScore) {
        jTextField6.setText(maxScore);
        calculateGrade(); // Recalculate when max changes
    }

    public String getPercentage() {
        return jTextField4.getText();
    }

    public void setPercentage(String percentage) {
        jTextField4.setText(percentage);
        calculateGrade(); // Recalculate when percentage changes
    }

    public String getCalculatedGrade() {
        return jTextField5.getText();
    }
    
    public double getGradeValue() {
        try {
            return Double.parseDouble(jTextField5.getText());
        } catch (NumberFormatException e) {
            return 0;
        }
    }
    
    public double getPercentageValue() {
        return percentageValue;
    }
    
    public boolean isValidEntry() {
        try {
            String gradeText = jTextField5.getText();
                if (gradeText.startsWith("Error")) {
                    return false;
                }
            Double.parseDouble(gradeText);
            return true;
            } catch (NumberFormatException e) {
                return false;
            }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jTextField3 = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        panel1 = new main.component.Panel();
        jTextField1 = new javax.swing.JTextField();
        panel3 = new main.component.Panel();
        jTextField4 = new javax.swing.JTextField();
        panel2 = new main.component.Panel();
        jTextField2 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        panel4 = new main.component.Panel();
        jTextField5 = new javax.swing.JTextField();

        jTextField3.setText("jTextField3");

        setMaximumSize(new java.awt.Dimension(1440, 75));
        setPreferredSize(new java.awt.Dimension(1440, 75));
        setLayout(new java.awt.BorderLayout());

        jPanel1.setMaximumSize(new java.awt.Dimension(1440, 75));
        jPanel1.setPreferredSize(new java.awt.Dimension(1440, 75));
        jPanel1.setLayout(new java.awt.GridBagLayout());

        panel1.setBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.darkGray, null));
        panel1.setArc(0);
        panel1.setMaximumSize(new java.awt.Dimension(0, 0));
        panel1.setMinimumSize(new java.awt.Dimension(360, 75));
        panel1.setPanelBackground(new java.awt.Color(21, 21, 23));
        panel1.setPreferredSize(new java.awt.Dimension(360, 75));

        jTextField1.setText("...");
        jTextField1.addActionListener(this::jTextField1ActionPerformed);

        javax.swing.GroupLayout panel1Layout = new javax.swing.GroupLayout(panel1);
        panel1.setLayout(panel1Layout);
        panel1Layout.setHorizontalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(5, Short.MAX_VALUE))
        );
        panel1Layout.setVerticalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        jPanel1.add(panel1, gridBagConstraints);

        panel3.setBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.darkGray, null));
        panel3.setArc(0);
        panel3.setMaximumSize(null);
        panel3.setMinimumSize(new java.awt.Dimension(360, 75));
        panel3.setPanelBackground(new java.awt.Color(21, 21, 23));
        panel3.setPreferredSize(new java.awt.Dimension(360, 75));

        jTextField4.setText("...");

        javax.swing.GroupLayout panel3Layout = new javax.swing.GroupLayout(panel3);
        panel3.setLayout(panel3Layout);
        panel3Layout.setHorizontalGroup(
            panel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(170, Short.MAX_VALUE))
        );
        panel3Layout.setVerticalGroup(
            panel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel3Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        jPanel1.add(panel3, gridBagConstraints);

        panel2.setBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.darkGray, null));
        panel2.setArc(0);
        panel2.setBorderColor(new java.awt.Color(204, 204, 204));
        panel2.setMinimumSize(new java.awt.Dimension(360, 75));
        panel2.setPanelBackground(new java.awt.Color(21, 21, 23));
        panel2.setPreferredSize(new java.awt.Dimension(360, 75));
        panel2.setLayout(new java.awt.GridBagLayout());

        jTextField2.setText("...");
        jTextField2.addActionListener(this::jTextField2ActionPerformed);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 91;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.insets = new java.awt.Insets(22, 6, 21, 0);
        panel2.add(jTextField2, gridBagConstraints);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("/");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(23, 12, 0, 0);
        panel2.add(jLabel1, gridBagConstraints);

        jTextField6.setText("...");
        jTextField6.addActionListener(this::jTextField6ActionPerformed);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 91;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(22, 6, 21, 12);
        panel2.add(jTextField6, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        jPanel1.add(panel2, gridBagConstraints);

        panel4.setBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.darkGray, null));
        panel4.setArc(0);
        panel4.setMaximumSize(null);
        panel4.setMinimumSize(new java.awt.Dimension(360, 75));
        panel4.setPanelBackground(new java.awt.Color(21, 21, 23));
        panel4.setPreferredSize(new java.awt.Dimension(360, 75));

        jTextField5.setText("--%");

        javax.swing.GroupLayout panel4Layout = new javax.swing.GroupLayout(panel4);
        panel4.setLayout(panel4Layout);
        panel4Layout.setHorizontalGroup(
            panel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(170, Short.MAX_VALUE))
        );
        panel4Layout.setVerticalGroup(
            panel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel4Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        jPanel1.add(panel4, gridBagConstraints);

        add(jPanel1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        calculateGrade();
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jTextField6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField6ActionPerformed
        calculateGrade();
    }//GEN-LAST:event_jTextField6ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private main.component.Panel panel1;
    private main.component.Panel panel2;
    private main.component.Panel panel3;
    private main.component.Panel panel4;
    // End of variables declaration//GEN-END:variables
}