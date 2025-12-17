package main.interfaces;

import javax.swing.*;
import java.awt.*;
import backend.services.UserService;
import backend.model.User;
import backend.exceptions.AuthenticationException;
import backend.exceptions.DatabaseException;
import backend.exceptions.ValidationException;

public class Settings extends javax.swing.JPanel {
    private UserService userService;
    private User currentUser;

    public Settings() {
        initComponents();
        userService = new UserService();
        loadCurrentUser();
    }
    
    /**
     * Load current logged in user into form fields
     */
    private void loadCurrentUser() {
        try {
            User currentUser = userService.getCurrentUser();
            
            if (currentUser != null) {
                // Get fresh user details
                User userDetails = userService.getCurrentUserDetails();
                
                if (userDetails != null) {
                    settingsUsername.setText(userDetails.getUsername());
                    settingsEmail.setText(userDetails.getEmail());
                    settingsPassword.setText(""); // Clear password for security
                    
                    // Update button text
                    changeAccountBtn.setText("Switch Account");
                }
            } else {
                // No user logged in
                settingsUsername.setText("");
                settingsEmail.setText("");
                settingsPassword.setText("");
                changeAccountBtn.setText("Login");
            }
            
        } catch (Exception e) {
            System.err.println("Error loading current user: " + e.getMessage());
        }
    }
     
     /**
     * Clear all login fields
     */     
    private void clearFields() {
        settingsUsername.setText("");
        settingsEmail.setText("");
        settingsPassword.setText("");
    }

    /* Built-in codes and functions */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Settings = new javax.swing.JPanel();
        Options = new main.component.Panel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        settingsEmail = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        settingsPassword = new javax.swing.JPasswordField();
        changeAccountBtn = new main.component.Button();
        jLabel7 = new javax.swing.JLabel();
        settingsUsername = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        applyChangesBtn = new main.component.Button();
        saveChangesBtn = new main.component.Button();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jComboBox3 = new javax.swing.JComboBox<>();
        jComboBox4 = new javax.swing.JComboBox<>();
        jComboBox5 = new javax.swing.JComboBox<>();
        jComboBox6 = new javax.swing.JComboBox<>();
        jSeparator3 = new javax.swing.JSeparator();

        Settings.setBackground(new java.awt.Color(21, 21, 23));
        Settings.setMinimumSize(new java.awt.Dimension(100, 100));
        Settings.setPreferredSize(new java.awt.Dimension(1440, 810));
        Settings.setLayout(new java.awt.GridBagLayout());

        Options.setBackground(new java.awt.Color(255, 255, 255));
        Options.setMinimumSize(new java.awt.Dimension(900, 800));
        Options.setPanelBackground(new java.awt.Color(51, 51, 51));

        jPanel1.setBackground(new java.awt.Color(32, 32, 34));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.setAlignmentY(0.1F);

        jLabel2.setFont(new java.awt.Font("Segoe UI Variable", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Account Settings");

        settingsEmail.setFont(new java.awt.Font("Segoe UI Variable", 1, 18)); // NOI18N
        settingsEmail.setText("XX-XXXXX@g.batstate-u.edu.ph");
        settingsEmail.addActionListener(this::settingsEmailActionPerformed);

        jLabel3.setFont(new java.awt.Font("Segoe UI Variable", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Email");

        jLabel5.setFont(new java.awt.Font("Segoe UI Variable", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Password");

        settingsPassword.setText("ZALZALAKOUH");
        settingsPassword.addActionListener(this::settingsPasswordActionPerformed);

        changeAccountBtn.setForeground(new java.awt.Color(255, 255, 255));
        changeAccountBtn.setText("Login");
        changeAccountBtn.setArc(15);
        changeAccountBtn.setFont(new java.awt.Font("Segoe UI Variable", 1, 18)); // NOI18N
        changeAccountBtn.addActionListener(this::changeAccountBtnActionPerformed);

        jLabel7.setFont(new java.awt.Font("Segoe UI Variable", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Username");

        settingsUsername.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        settingsUsername.setText("Add username...");
        settingsUsername.addActionListener(this::settingsUsernameActionPerformed);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(139, 139, 139)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(changeAccountBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel7))
                                .addGap(101, 101, 101)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(settingsPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 599, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(settingsEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 599, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(settingsUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 599, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(139, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(settingsUsername, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                    .addComponent(jLabel7))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(settingsEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(settingsPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addComponent(changeAccountBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jLabel1.setFont(new java.awt.Font("Segoe UI Semibold", 1, 26)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Settings");
        jLabel1.setAutoscrolls(true);

        applyChangesBtn.setForeground(new java.awt.Color(255, 255, 255));
        applyChangesBtn.setText("Apply");
        applyChangesBtn.setArc(15);
        applyChangesBtn.setFont(new java.awt.Font("Segoe UI Variable", 1, 18)); // NOI18N
        applyChangesBtn.addActionListener(this::applyChangesBtnActionPerformed);

        saveChangesBtn.setForeground(new java.awt.Color(255, 255, 255));
        saveChangesBtn.setText("Save");
        saveChangesBtn.setArc(15);
        saveChangesBtn.setFont(new java.awt.Font("Segoe UI Variable", 1, 18)); // NOI18N
        saveChangesBtn.addActionListener(this::saveChangesBtnActionPerformed);

        jPanel2.setBackground(new java.awt.Color(32, 32, 34));

        jLabel4.setFont(new java.awt.Font("Segoe UI Variable", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Customize");

        jLabel6.setFont(new java.awt.Font("Segoe UI Variable", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Theme");

        jLabel8.setFont(new java.awt.Font("Segoe UI Variable", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Background Color");

        jLabel9.setFont(new java.awt.Font("Segoe UI Variable", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Button Color");

        jLabel10.setFont(new java.awt.Font("Segoe UI Variable", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Font Color");

        jLabel11.setFont(new java.awt.Font("Segoe UI Variable", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Font");

        jComboBox1.setFont(new java.awt.Font("Segoe UI Variable", 1, 14)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox1.addActionListener(this::jComboBox1ActionPerformed);

        jComboBox3.setFont(new java.awt.Font("Segoe UI Variable", 1, 14)); // NOI18N
        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox3.addActionListener(this::jComboBox3ActionPerformed);

        jComboBox4.setFont(new java.awt.Font("Segoe UI Variable", 1, 14)); // NOI18N
        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox4.addActionListener(this::jComboBox4ActionPerformed);

        jComboBox5.setFont(new java.awt.Font("Segoe UI Variable", 1, 14)); // NOI18N
        jComboBox5.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox5.addActionListener(this::jComboBox5ActionPerformed);

        jComboBox6.setFont(new java.awt.Font("Segoe UI Variable", 1, 14)); // NOI18N
        jComboBox6.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox6.addActionListener(this::jComboBox6ActionPerformed);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel4))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(133, 133, 133)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10)
                            .addComponent(jLabel6))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jComboBox1, 0, 604, Short.MAX_VALUE)
                    .addComponent(jComboBox5, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jComboBox4, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jComboBox3, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jComboBox6, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(133, 133, 133))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox6, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addContainerGap(39, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout OptionsLayout = new javax.swing.GroupLayout(Options);
        Options.setLayout(OptionsLayout);
        OptionsLayout.setHorizontalGroup(
            OptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, OptionsLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(applyChangesBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(saveChangesBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
            .addGroup(OptionsLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(OptionsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(OptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jSeparator1)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(OptionsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jSeparator3)
        );
        OptionsLayout.setVerticalGroup(
            OptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(OptionsLayout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addGroup(OptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(saveChangesBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(applyChangesBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18))
        );

        Settings.add(Options, new java.awt.GridBagConstraints());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Settings, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1182, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Settings, javax.swing.GroupLayout.DEFAULT_SIZE, 886, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void applyChangesBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_applyChangesBtnActionPerformed
        applyAccountChanges();
    }//GEN-LAST:event_applyChangesBtnActionPerformed
    
    private void applyAccountChanges() {
        String username = settingsUsername.getText().trim();
        String email = settingsEmail.getText().trim();
        String password = new String(settingsPassword.getPassword()).trim();
        
        // Check if user is logged in
        User currentUser = userService.getCurrentUser();
        if (currentUser == null) {
            JOptionPane.showMessageDialog(this,
                "Please login first to apply changes",
                "Not Logged In",
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        // Validation
        StringBuilder validationErrors = new StringBuilder();
        
        if (username.isEmpty()) {
            validationErrors.append("• Username is required\n");
        } else if (!username.matches("^[a-zA-Z0-9_]{3,20}$")) {
            validationErrors.append("• Username must be 3-20 characters (letters, numbers, underscores only)\n");
        }
        
        if (email.isEmpty()) {
            validationErrors.append("• Email is required\n");
        } else if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            validationErrors.append("• Invalid email format\n");
        }
        
        if (!password.isEmpty() && password.length() < 6) {
            validationErrors.append("• Password must be at least 6 characters\n");
        }
        
        // Show validation errors if any
        if (validationErrors.length() > 0) {
            JOptionPane.showMessageDialog(this,
                "Please fix the following errors:\n\n" + validationErrors.toString(),
                "Validation Error",
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        // Show what will be changed
        StringBuilder changes = new StringBuilder();
        changes.append("The following changes will be applied:\n\n");
        
        if (!username.equals(currentUser.getUsername())) {
            changes.append("• Username: " + currentUser.getUsername() + " → " + username + "\n");
        }
        
        if (!email.equals(currentUser.getEmail())) {
            changes.append("• Email: " + currentUser.getEmail() + " → " + email + "\n");
        }
        
        if (!password.isEmpty()) {
            changes.append("• Password: Will be updated\n");
        }
        
        if (changes.toString().equals("The following changes will be applied:\n\n")) {
            changes.append("• No changes detected\n");
        }
        
        changes.append("\nClick 'Save' to confirm these changes.");
        
        // Show confirmation dialog
        JOptionPane.showMessageDialog(this,
            changes.toString(),
            "Apply Changes - Preview",
            JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jComboBox3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox3ActionPerformed

    private void jComboBox6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox6ActionPerformed

    private void jComboBox4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox4ActionPerformed

    private void jComboBox5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox5ActionPerformed

    private void settingsUsernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_settingsUsernameActionPerformed
        String username = settingsUsername.getText().trim();
    
        if (username.isEmpty()) {
            settingsUsername.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
            JOptionPane.showMessageDialog(this, 
                "Username cannot be empty",
                "Validation Error",
                JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Check username format (basic validation)
        if (!username.matches("^[a-zA-Z0-9_]{3,20}$")) {
            settingsUsername.setBorder(BorderFactory.createLineBorder(Color.ORANGE, 1));
        } else {
            settingsUsername.setBorder(BorderFactory.createLineBorder(Color.GREEN, 1));
        }
    }//GEN-LAST:event_settingsUsernameActionPerformed

    private void settingsEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_settingsEmailActionPerformed
         String email = settingsEmail.getText().trim();
    
        if (email.isEmpty()) {
            settingsEmail.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
            JOptionPane.showMessageDialog(this, 
                "Email cannot be empty",
                "Validation Error",
                JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Basic email validation
        if (email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            settingsEmail.setBorder(BorderFactory.createLineBorder(Color.GREEN, 1));
        } else {
            settingsEmail.setBorder(BorderFactory.createLineBorder(Color.ORANGE, 1));
            JOptionPane.showMessageDialog(this, 
                "Email format appears invalid",
                "Warning",
                JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_settingsEmailActionPerformed

    private void settingsPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_settingsPasswordActionPerformed
        String password = new String(settingsPassword.getPassword()).trim();
    
        if (!password.isEmpty()) {
            // Check password strength
            boolean hasUpper = password.matches(".*[A-Z].*");
            boolean hasLower = password.matches(".*[a-z].*");
            boolean hasDigit = password.matches(".*\\d.*");
            boolean hasLength = password.length() >= 8;

            if (hasUpper && hasLower && hasDigit && hasLength) {
                settingsPassword.setBorder(BorderFactory.createLineBorder(Color.GREEN, 1));
            } else {
                settingsPassword.setBorder(BorderFactory.createLineBorder(Color.ORANGE, 1));

                String message = "Password should have:\n";
                if (!hasUpper) message += "• At least one uppercase letter\n";
                if (!hasLower) message += "• At least one lowercase letter\n";
                if (!hasDigit) message += "• At least one number\n";
                if (!hasLength) message += "• At least 8 characters\n";

                JOptionPane.showMessageDialog(this, 
                    message,
                    "Password Requirements",
                    JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            // Empty password field - user might not want to change password
            settingsPassword.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        }
    }//GEN-LAST:event_settingsPasswordActionPerformed

    private void changeAccountBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changeAccountBtnActionPerformed
        // Get values from form
        String identifier = settingsUsername.getText().trim();
        String password = new String(settingsPassword.getPassword()).trim();
        
        // Validation
        if (identifier.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Username or email is required",
                "Validation Error",
                JOptionPane.WARNING_MESSAGE);
            settingsUsername.requestFocus();
            return;
        }
        
        if (password.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Password is required",
                "Validation Error",
                JOptionPane.WARNING_MESSAGE);
            settingsPassword.requestFocus();
            return;
        }
        
        // Disable button during authentication
        changeAccountBtn.setEnabled(false);
        String originalButtonText = changeAccountBtn.getText();
        changeAccountBtn.setText("Processing...");
        
        try {
            User loggedInUser;
            
            // Check if identifier is email (contains @)
            if (identifier.contains("@")) {
                // Login with email
                loggedInUser = userService.loginWithEmail(identifier, password);
            } else {
                // Login with username
                loggedInUser = userService.login(identifier, password);
            }
            
            // Login successful!
            System.out.println("Login successful! User: " + loggedInUser.getUsername());
            
            // Update button
            changeAccountBtn.setEnabled(true);
            changeAccountBtn.setText("Switch");
            
            // Update email field with user's email
            settingsEmail.setText(loggedInUser.getEmail());
            
            // Clear password field for security
            settingsPassword.setText("");
            
            // Show success message
            String message = "Login successful!\nWelcome, " + loggedInUser.getUsername() + "!";
            if (!originalButtonText.equals("Login")) {
                message = "Account switched successfully!\nNow using: " + loggedInUser.getUsername();
            }
            
            JOptionPane.showMessageDialog(this, 
                message,
                "Success", 
                JOptionPane.INFORMATION_MESSAGE);
            
        } catch (AuthenticationException e) {
            // Authentication failed
            changeAccountBtn.setEnabled(true);
            changeAccountBtn.setText(originalButtonText);
            
            System.err.println("Authentication failed: " + e.getMessage());
            JOptionPane.showMessageDialog(this, 
                "Invalid username/email or password. Please try again.",
                "Login Failed", 
                JOptionPane.ERROR_MESSAGE);
            
        } catch (DatabaseException e) {
            // Database error
            changeAccountBtn.setEnabled(true);
            changeAccountBtn.setText(originalButtonText);
            
            System.err.println("Database error during login: " + e.getMessage());
            e.printStackTrace();
            
            JOptionPane.showMessageDialog(this, 
                "Unable to connect to database. Please try again later.",
                "Connection Error", 
                JOptionPane.ERROR_MESSAGE);
            
        } catch (Exception e) {
            // Any other error  
            changeAccountBtn.setEnabled(true);
            changeAccountBtn.setText(originalButtonText);
            
            System.err.println("Unexpected error during login: " + e.getMessage());
            e.printStackTrace();
            
            JOptionPane.showMessageDialog(this, 
                "An unexpected error occurred: " + e.getMessage(),
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_changeAccountBtnActionPerformed
    
    //SAVE ACCOUNT FUNCTION
    private void saveChangesBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveChangesBtnActionPerformed
        saveAccountChanges();
    }//GEN-LAST:event_saveChangesBtnActionPerformed
    
     private void saveAccountChanges() {
        String username = settingsUsername.getText().trim();
        String email = settingsEmail.getText().trim();
        String password = new String(settingsPassword.getPassword()).trim();
        
        // Check if user is logged in
        User currentUser = userService.getCurrentUser();
        if (currentUser == null) {
            JOptionPane.showMessageDialog(this,
                "Please login first to save changes",
                "Not Logged In",
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        // Validation (same as apply)
        if (username.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Username is required",
                "Validation Error",
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        if (email.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Email is required",
                "Validation Error",
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        // Disable button during save
        saveChangesBtn.setEnabled(false);
        String originalButtonText = saveChangesBtn.getText();
        saveChangesBtn.setText("Saving...");
        
        try {
            // Update user account
            boolean success = userService.updateUserAccount(username, email, password);
            
            if (success) {
                // Success
                saveChangesBtn.setEnabled(true);
                saveChangesBtn.setText(originalButtonText);
                settingsPassword.setText(""); // Clear password after save
                
                // Show what was changed
                StringBuilder savedChanges = new StringBuilder();
                savedChanges.append("Account updated successfully!\n\n");
                savedChanges.append("Changes saved:\n");
                
                if (!username.equals(currentUser.getUsername())) {
                    savedChanges.append("• New username: " + username + "\n");
                }
                
                if (!email.equals(currentUser.getEmail())) {
                    savedChanges.append("• New email: " + email + "\n");
                }
                
                if (!password.isEmpty()) {
                    savedChanges.append("• Password has been updated\n");
                }
                
                JOptionPane.showMessageDialog(this,
                    savedChanges.toString(),
                    "Save Successful",
                    JOptionPane.INFORMATION_MESSAGE);
                    
                // Reload user data to reflect changes
                loadCurrentUser();
            } else {
                // Update failed
                saveChangesBtn.setEnabled(true);
                saveChangesBtn.setText(originalButtonText);
                JOptionPane.showMessageDialog(this,
                    "Failed to update account. Please try again.",
                    "Save Failed",
                    JOptionPane.ERROR_MESSAGE);
            }
            
        } catch (ValidationException e) {
            saveChangesBtn.setEnabled(true);
            saveChangesBtn.setText(originalButtonText);
            JOptionPane.showMessageDialog(this,
                "Validation error: " + e.getMessage(),
                "Save Failed",
                JOptionPane.ERROR_MESSAGE);
            
        } catch (DatabaseException e) {
            saveChangesBtn.setEnabled(true);
            saveChangesBtn.setText(originalButtonText);
            JOptionPane.showMessageDialog(this,
                "Database error: " + e.getMessage(),
                "Save Failed",
                JOptionPane.ERROR_MESSAGE);
            
        } catch (Exception e) {
            saveChangesBtn.setEnabled(true);
            saveChangesBtn.setText(originalButtonText);
            JOptionPane.showMessageDialog(this,
                "Error: " + e.getMessage(),
                "Save Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Update account information (Save button functionality)
     */
    private void updateAccountInfo() {
        String username = settingsUsername.getText().trim();
        String email = settingsEmail.getText().trim();
        String password = new String(settingsPassword.getPassword()).trim();
        
        // Check if we have a logged in user
        if (currentUser == null) {
            JOptionPane.showMessageDialog(this,
                "Please login first to update account",
                "Not Logged In",
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        // Show loading state
        changeAccountBtn.setEnabled(false);
        String originalText = changeAccountBtn.getText();
        changeAccountBtn.setText("Updating...");
        
        new Thread(() -> {
            try {
                boolean success;
                
                if (password.isEmpty()) {
                    // Update without changing password
                    success = userService.updateProfile(
                        currentUser.getUserId(),
                        username,
                        email
                    );
                } else {
                    // Update with password change
                    success = userService.updateUserAccount(username, email, password);
                }
                
                SwingUtilities.invokeLater(() -> {
                    if (success) {
                        // Reload user data
                        loadCurrentUser();
                        
                        JOptionPane.showMessageDialog(Settings.this,
                            "Account updated successfully",
                            "Success",
                            JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(Settings.this,
                            "Failed to update account",
                            "Update Failed",
                            JOptionPane.ERROR_MESSAGE);
                    }
                    
                    // Reset button
                    changeAccountBtn.setEnabled(true);
                    changeAccountBtn.setText(originalText);
                });
                
            } catch (ValidationException e) {
                SwingUtilities.invokeLater(() -> {
                    JOptionPane.showMessageDialog(Settings.this,
                        "Validation error: " + e.getMessage(),
                        "Update Failed",
                        JOptionPane.ERROR_MESSAGE);
                    
                    // Reset button
                    changeAccountBtn.setEnabled(true);
                    changeAccountBtn.setText(originalText);
                });
                
            } catch (DatabaseException e) {
                SwingUtilities.invokeLater(() -> {
                    JOptionPane.showMessageDialog(Settings.this,
                        "Database error: " + e.getMessage(),
                        "Update Failed",
                        JOptionPane.ERROR_MESSAGE);
                    
                    // Reset button
                    changeAccountBtn.setEnabled(true);
                    changeAccountBtn.setText(originalText);
                });
                
            } catch (Exception e) {
                SwingUtilities.invokeLater(() -> {
                    JOptionPane.showMessageDialog(Settings.this,
                        "Error: " + e.getMessage(),
                        "Update Failed",
                        JOptionPane.ERROR_MESSAGE);
                    
                    // Reset button
                    changeAccountBtn.setEnabled(true);
                    changeAccountBtn.setText(originalText);
                });
            }
        }).start();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private main.component.Panel Options;
    private javax.swing.JPanel Settings;
    private main.component.Button applyChangesBtn;
    private main.component.Button changeAccountBtn;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JComboBox<String> jComboBox4;
    private javax.swing.JComboBox<String> jComboBox5;
    private javax.swing.JComboBox<String> jComboBox6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private main.component.Button saveChangesBtn;
    private javax.swing.JTextField settingsEmail;
    private javax.swing.JPasswordField settingsPassword;
    private javax.swing.JTextField settingsUsername;
    // End of variables declaration//GEN-END:variables
}
