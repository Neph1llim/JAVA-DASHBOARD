
package main.interfaces;

import javax.swing.*;
import java.awt.*;
import backend.services.UserService;
import backend.model.User;
import backend.exceptions.ValidationException;
import backend.exceptions.DatabaseException;

public class Settings extends javax.swing.JPanel {
    private javax.swing.JButton changeAccountBtn;
    private javax.swing.JLabel currentUserLabel;
    
    private UserService userService;

    public Settings() {
        initComponents();
        userService = new UserService();
    }
    
     /**
     * Load current user data into form fields
     */
    private void loadUserData() {
        try {
            User currentUser = userService.getCurrentUser();
            
            if (currentUser != null) {
                // Get fresh data from database
                User userDetails = userService.getCurrentUserDetails();
                
                // Set current user label
                currentUserLabel.setText("Account: " + userDetails.getUsername());
                
                // Set form fields
                settingsUsername.setText(userDetails.getUsername());
                settingsEmail.setText(userDetails.getEmail());
                
                // Clear password field for security
                settingsPassword.setText("");
                
                System.out.println("Loaded user data:");
                System.out.println("Username: " + userDetails.getUsername());
                System.out.println("Email: " + userDetails.getEmail());
                System.out.println("User ID: " + userDetails.getUserId());
            } else {
                JOptionPane.showMessageDialog(this,
                    "No user is logged in. Please login first.",
                    "Not Logged In",
                    JOptionPane.WARNING_MESSAGE);
                
                // Clear fields
                settingsUsername.setText("");
                settingsEmail.setText("");
                settingsPassword.setText("");
                currentUserLabel.setText("Account: Not Logged In");
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                "Unexpected error: " + e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
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
        changeAccoutBtn = new main.component.Button();
        jLabel7 = new javax.swing.JLabel();
        settingsUsername = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        button1 = new main.component.Button();
        button2 = new main.component.Button();
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

        changeAccoutBtn.setForeground(new java.awt.Color(255, 255, 255));
        changeAccoutBtn.setText("Login");
        changeAccoutBtn.setArc(15);
        changeAccoutBtn.setFont(new java.awt.Font("Segoe UI Variable", 1, 18)); // NOI18N
        changeAccoutBtn.addActionListener(this::changeAccoutBtnActionPerformed);

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
                            .addComponent(changeAccoutBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                .addComponent(changeAccoutBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jLabel1.setFont(new java.awt.Font("Segoe UI Semibold", 1, 26)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Settings");
        jLabel1.setAutoscrolls(true);

        button1.setForeground(new java.awt.Color(255, 255, 255));
        button1.setText("Apply");
        button1.setArc(15);
        button1.setFont(new java.awt.Font("Segoe UI Variable", 1, 18)); // NOI18N
        button1.addActionListener(this::button1ActionPerformed);

        button2.setForeground(new java.awt.Color(255, 255, 255));
        button2.setText("Save");
        button2.setArc(15);
        button2.setFont(new java.awt.Font("Segoe UI Variable", 1, 18)); // NOI18N

        javax.swing.GroupLayout OptionsLayout = new javax.swing.GroupLayout(Options);
        Options.setLayout(OptionsLayout);
        OptionsLayout.setHorizontalGroup(
            OptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, OptionsLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(button2, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
            .addGroup(OptionsLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(OptionsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(OptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addGroup(OptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(button2, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
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

    private void button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_button1ActionPerformed

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

    private void changeAccoutBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changeAccoutBtnActionPerformed
        changeAccountBtn.setEnabled(false);
        String originalText = changeAccountBtn.getText();
        changeAccountBtn.setText("Updating...");

        // Get values from form
        String username = settingsUsername.getText().trim();
        String email = settingsEmail.getText().trim();
        String password = new String(settingsPassword.getPassword()).trim();

        // Validation
        if (username.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Username is required",
                "Validation Error",
                JOptionPane.ERROR_MESSAGE);
            changeAccountBtn.setEnabled(true);
            changeAccountBtn.setText(originalText);
            return;
        }

        if (email.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Email is required",
                "Validation Error",
                JOptionPane.ERROR_MESSAGE);
            changeAccountBtn.setEnabled(true);
            changeAccountBtn.setText(originalText);
            return;
        }

        // Show confirmation dialog
        String passwordMessage = password.isEmpty() ? 
            "Password will remain unchanged." : 
            "Password will be updated.";

        int confirmation = JOptionPane.showConfirmDialog(this,
            "<html><b>Confirm Account Update</b><br><br>" +
            "Username: " + username + "<br>" +
            "Email: " + email + "<br>" +
            passwordMessage + "<br><br>" +
            "Are you sure you want to update your account?</html>",
            "Confirm Update",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE);

        if (confirmation != JOptionPane.YES_OPTION) {
            changeAccountBtn.setEnabled(true);
            changeAccountBtn.setText(originalText);
            return;
        }

        // Perform update in a separate thread to prevent GUI freezing
        new Thread(() -> {
            try {
                // Call UserService to update account
                boolean success = userService.updateUserAccount(username, email, password);

                // Update UI on EDT
                SwingUtilities.invokeLater(() -> {
                    if (success) {
                        JOptionPane.showMessageDialog(Settings.this,
                            "<html><b>Account Updated Successfully!</b><br><br>" +
                            "Your account information has been updated.</html>",
                            "Success",
                            JOptionPane.INFORMATION_MESSAGE);

                        // Update current user label
                        currentUserLabel.setText("Account: " + username);

                        // Clear password field after successful update
                        settingsPassword.setText("");

                        // Reset borders
                        settingsUsername.setBorder(UIManager.getBorder("TextField.border"));
                        settingsEmail.setBorder(UIManager.getBorder("TextField.border"));
                        settingsPassword.setBorder(UIManager.getBorder("PasswordField.border"));

                    } else {
                        JOptionPane.showMessageDialog(Settings.this,
                            "Failed to update account. Please try again.",
                            "Update Failed",
                            JOptionPane.ERROR_MESSAGE);
                    }

                    // Re-enable button
                    changeAccountBtn.setEnabled(true);
                    changeAccountBtn.setText(originalText);
                });

            } catch (ValidationException e) {
                SwingUtilities.invokeLater(() -> {
                    JOptionPane.showMessageDialog(Settings.this,
                        "Validation Error: " + e.getMessage(),
                        "Validation Failed",
                        JOptionPane.ERROR_MESSAGE);
                    changeAccountBtn.setEnabled(true);
                    changeAccountBtn.setText(originalText);
                });

            } catch (DatabaseException e) {
                SwingUtilities.invokeLater(() -> {
                    JOptionPane.showMessageDialog(Settings.this,
                        "Database Error: " + e.getMessage(),
                        "Database Error",
                        JOptionPane.ERROR_MESSAGE);
                    changeAccountBtn.setEnabled(true);
                    changeAccountBtn.setText(originalText);
                });

            } catch (Exception e) {
                SwingUtilities.invokeLater(() -> {
                    JOptionPane.showMessageDialog(Settings.this,
                        "Unexpected Error: " + e.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                    changeAccountBtn.setEnabled(true);
                    changeAccountBtn.setText(originalText);
                    e.printStackTrace();
                });
            }
        }).start();
    }//GEN-LAST:event_changeAccoutBtnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private main.component.Panel Options;
    private javax.swing.JPanel Settings;
    private main.component.Button button1;
    private main.component.Button button2;
    private main.component.Button changeAccoutBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JTextField settingsEmail;
    private javax.swing.JPasswordField settingsPassword;
    private javax.swing.JTextField settingsUsername;
    // End of variables declaration//GEN-END:variables
}
