package main.interfaces;

/* Import packages*/
import javax.swing.*;
import java.awt.*;
import backend.services.UserService;
import backend.model.User;
import backend.exceptions.AuthenticationException;
import backend.exceptions.DatabaseException;
import backend.exceptions.ValidationException;
import main.MainFrame;

public class Settings extends javax.swing.JPanel {
    private final UserService userService;
    private MainFrame mainFrame;

    /**
     * Class constructors
     */
    public Settings() {
        initComponents();
        userService = new UserService();
        loadCurrentUser();
    }
    
    public void setMainFrame(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
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
        applyChangesBtn = new main.component.Button();
        saveChangesBtn = new main.component.Button();
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
// </editor-fold>                        
    
     /**
     * Load current logged in user into form fields
     */
    private void loadCurrentUser() {
        try {
            User currentUser = userService.getCurrentUser();
            
            if (currentUser != null) {
                // Display current user info
                settingsUsername.setText(currentUser.getUsername());
                settingsEmail.setText(currentUser.getEmail());
                settingsPassword.setText("");
                
                // Make fields editable for account switching
                settingsUsername.setEditable(true);
                settingsEmail.setEditable(true);
                settingsPassword.setEditable(true);
                
                changeAccountBtn.setText("Switch Account");
            } else {
                // No user logged in
                settingsUsername.setText("");
                settingsEmail.setText("");
                settingsPassword.setText("");
                changeAccountBtn.setText("Login");
                
                // Make fields editable for login/registration
                settingsUsername.setEditable(true);
                settingsEmail.setEditable(true);
                settingsPassword.setEditable(true);
            }
            
        } catch (Exception e) {
            System.err.println("Error loading current user: " + e.getMessage());
        }
    }
    
    /**
     * Reloads settings for the currently logged-in user
     * Called when user logs in or switches accounts
     */
    public void reloadSettings() {
        loadCurrentUser();
    }
    
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
    
    /**
     * Switch to an existing account
     */
    private void applyChangesBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_applyChangesBtnActionPerformed
        switchAccount();
    }//GEN-LAST:event_applyChangesBtnActionPerformed
    
    /**
     * Create a new account
     */
    private void saveChangesBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveChangesBtnActionPerformed
        createNewAccount();
    }//GEN-LAST:event_saveChangesBtnActionPerformed
    
    /**
     * Switch to an existing account using email and password
     */
    private void switchAccount() {
        String email = settingsEmail.getText().trim();
        String password = new String(settingsPassword.getPassword()).trim();
        
        if (email.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Please enter email and password to switch accounts",
                "Input Required", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        try {
            // Generate username from email
            String username = email.contains("@") ? email.substring(0, email.indexOf("@")) : email;
            
            // Try to login with provided credentials
            UserService userService = new UserService();
            userService.login(username, password);
            
            JOptionPane.showMessageDialog(this,
                "Successfully switched account!",
                "Account Switched",
                JOptionPane.INFORMATION_MESSAGE);
            
            // Reload settings for current user
            loadCurrentUser();
            
            // Reload ALL user data (notes, settings, etc)
            Home homePanel = getMainFrame().getHomePanel();
            if (homePanel != null) {
                homePanel.reloadAllUserData();
            }
            
            // Clear password field
            settingsPassword.setText("");
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                "Failed to switch account: " + e.getMessage(),
                "Switch Failed",
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Gets reference to MainFrame for accessing other panels
     */
    private main.MainFrame getMainFrame() {
        Container container = this;
        while (container != null) {
            if (container instanceof main.MainFrame) {
                return (main.MainFrame) container;
            }
            container = container.getParent();
        }
        return null;
    }
    
    /**
     * Create a new account
     */
    private void createNewAccount() {
        String username = settingsUsername.getText().trim();
        String email = settingsEmail.getText().trim();
        String password = new String(settingsPassword.getPassword()).trim();
        
        if (username.isEmpty() || email.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Please enter username, email, and password",
                "Input Required", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        try {
            // Register new user
            UserService userService = new UserService();
            User newUser = userService.register(email, password, null);
            
            JOptionPane.showMessageDialog(this,
                "Account created successfully!",
                "Success",
                JOptionPane.INFORMATION_MESSAGE);
            
            // Reload current user info
            loadCurrentUser();
            
            // Clear fields
            settingsUsername.setText("");
            settingsEmail.setText("");
            settingsPassword.setText("");
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                "Failed to create account: " + e.getMessage(),
                "Creation Failed",
                JOptionPane.ERROR_MESSAGE);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private main.component.Panel Options;
    private javax.swing.JPanel Settings;
    private main.component.Button applyChangesBtn;
    private main.component.Button changeAccountBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator3;
    private main.component.Button saveChangesBtn;
    private javax.swing.JTextField settingsEmail;
    private javax.swing.JPasswordField settingsPassword;
    private javax.swing.JTextField settingsUsername;
    // End of variables declaration//GEN-END:variables

}
