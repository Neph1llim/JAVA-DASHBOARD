package main.component;

import javax.swing.JOptionPane;

public class GradeTab extends javax.swing.JPanel {
    
    public GradeTab() {
        initComponents();
        findPanels();
        setupLayout();
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
    
    // ... rest of your code ...


    public void calculateFinalGrade() {
        if (centerPanel == null) return;

        double totalGrade = 0;
        int validEntries = 0;

        // Loop through all AddGrade panels in centerPanel
        for (java.awt.Component comp : centerPanel.getComponents()) {
            if (comp instanceof AddGrade) {
                AddGrade gradeEntry = (AddGrade) comp;
                if (gradeEntry.isValidEntry()) {
                    totalGrade += gradeEntry.getGradeValue();
                    validEntries++;
                }
            }
        }

        if (validEntries > 0) {
            JOptionPane.showMessageDialog(this, 
                "Final Grade: " + String.format("%.2f", totalGrade) + "/100",
                "Grade Calculation",
                JOptionPane.INFORMATION_MESSAGE);
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
        panel2.setMaximumSize(null);
        panel2.setPanelBackground(new java.awt.Color(102, 102, 102));
        panel2.setPreferredSize(new java.awt.Dimension(1440, 810));
        panel2.setLayout(new java.awt.GridBagLayout());

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setMaximumSize(null);
        jScrollPane1.setMinimumSize(new java.awt.Dimension(480, 255));

        contentPanel.setArc(0);
        contentPanel.setMaximumSize(null);
        contentPanel.setMinimumSize(new java.awt.Dimension(1440, 735));
        contentPanel.setPreferredSize(null);
        contentPanel.setVerifyInputWhenFocusTarget(false);
        contentPanel.setLayout(new java.awt.BorderLayout());

        centerPanel.setLayout(new javax.swing.BoxLayout(centerPanel, javax.swing.BoxLayout.Y_AXIS));
        contentPanel.add(centerPanel, java.awt.BorderLayout.CENTER);

        buttonPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        button1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/resource/add.png"))); // NOI18N
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

        panel3.setArc(0);
        panel3.setMaximumSize(new java.awt.Dimension(360, 75));
        panel3.setMinimumSize(new java.awt.Dimension(360, 75));
        panel3.setPanelBackground(new java.awt.Color(153, 153, 153));
        panel3.setPreferredSize(new java.awt.Dimension(360, 75));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("Assement");

        javax.swing.GroupLayout panel3Layout = new javax.swing.GroupLayout(panel3);
        panel3.setLayout(panel3Layout);
        panel3Layout.setHorizontalGroup(
            panel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel3Layout.createSequentialGroup()
                .addGap(161, 161, 161)
                .addComponent(jLabel1)
                .addContainerGap(116, Short.MAX_VALUE))
        );
        panel3Layout.setVerticalGroup(
            panel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

        panel5.setArc(0);
        panel5.setMaximumSize(new java.awt.Dimension(360, 75));
        panel5.setMinimumSize(new java.awt.Dimension(360, 75));
        panel5.setPanelBackground(new java.awt.Color(102, 102, 102));
        panel5.setPreferredSize(new java.awt.Dimension(360, 75));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setText("Score");

        javax.swing.GroupLayout panel5Layout = new javax.swing.GroupLayout(panel5);
        panel5.setLayout(panel5Layout);
        panel5Layout.setHorizontalGroup(
            panel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel5Layout.createSequentialGroup()
                .addContainerGap(131, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(182, 182, 182))
        );
        panel5Layout.setVerticalGroup(
            panel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel5Layout.createSequentialGroup()
                .addContainerGap(25, Short.MAX_VALUE)
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

        panel6.setArc(0);
        panel6.setMaximumSize(new java.awt.Dimension(360, 75));
        panel6.setMinimumSize(new java.awt.Dimension(360, 75));
        panel6.setPanelBackground(new java.awt.Color(153, 153, 153));
        panel6.setPreferredSize(new java.awt.Dimension(360, 75));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setText("% of grade");

        javax.swing.GroupLayout panel6Layout = new javax.swing.GroupLayout(panel6);
        panel6.setLayout(panel6Layout);
        panel6Layout.setHorizontalGroup(
            panel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel6Layout.createSequentialGroup()
                .addContainerGap(107, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(160, 160, 160))
        );
        panel6Layout.setVerticalGroup(
            panel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel6Layout.createSequentialGroup()
                .addContainerGap(25, Short.MAX_VALUE)
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

        panel4.setArc(0);
        panel4.setMaximumSize(new java.awt.Dimension(360, 75));
        panel4.setMinimumSize(new java.awt.Dimension(360, 75));
        panel4.setPanelBackground(new java.awt.Color(102, 102, 102));
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
                .addContainerGap(130, Short.MAX_VALUE))
        );
        panel4Layout.setVerticalGroup(
            panel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel4Layout.createSequentialGroup()
                .addContainerGap(25, Short.MAX_VALUE)
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
    
    // Make sure centerPanel is available
    if (centerPanel == null) {
        setupLayout();
    }
    
    // Add to centerPanel
    centerPanel.add(newGrade);
    
    // Make AddGrade take full width
    newGrade.setMaximumSize(new java.awt.Dimension(Integer.MAX_VALUE, newGrade.getPreferredSize().height));
    
    // Update layout
    centerPanel.revalidate();
    centerPanel.repaint();
    
    // Scroll to show the new component
    newGrade.scrollRectToVisible(newGrade.getBounds());
    }//GEN-LAST:event_button1ActionPerformed


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
    // End of variables declaration//GEN-END:variables
}
