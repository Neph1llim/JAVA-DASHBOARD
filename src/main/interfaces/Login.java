package main.interfaces;

import backend.exceptions.AuthenticationException;
import backend.exceptions.DatabaseException;
import backend.model.User;
import backend.services.UserService;
import java.awt.*;
import java.awt.event.*;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.border.Border;
import main.MainFrame;

public class Login extends javax.swing.JPanel {
    /* Properties */
    String emailPlaceholder = "24-XXXXX@g.batstate-u.edu.ph";
    String passwordPlaceholder = "               ";
    Color initialText = new Color(204,204, 204);
    private final Color currentTextColor = Color.WHITE;
    private MainFrame mainFrame;
    
    public Login() {
        initComponents();
        setupPlaceholder();
        setupListeners();
        setBorder("both", true);
    }
    
    /* Methods */
    public void setMainFrame(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }
    
    private void setBorder(String type, boolean isValid){
        Border normal = BorderFactory.createLineBorder(Color.GRAY,2);
        Border red = BorderFactory.createLineBorder(Color.RED, 2);
        Border current = !isValid ? red: normal;
        
        switch(type){
            case "email"-> {emailTextField.setBorder(current);break;}
            case "pass" -> {passwordTextField.setBorder(current);break;}
            case "both" -> {emailTextField.setBorder(current);passwordTextField.setBorder(current);break;}
        }
    }

    private void handleSignupClick() {
        if (mainFrame != null) {
            CardLayout card = (CardLayout) MainFrame.Login.getLayout();
            card.show(MainFrame.Login, "signup");
            setupPlaceholder();
            showPassword.setSelected(false);
            passwordTextField.setEchoChar('•');
        } else {
            JOptionPane.showMessageDialog(this, "Error: MainFrame not initialized!");
        }
    }
    
    private void setupListeners() {
        signupButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                signupButton.setForeground(new Color(135, 206, 250));
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                signupButton.setForeground(new Color(102, 204, 255));
            }
        });
        
        emailTextField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent evt) {
                if (emailTextField.getForeground().equals(initialText)) {
                    setBorder("email", true);
                    emailTextField.setText("");
                    emailTextField.setForeground(currentTextColor);
                }
            }

            @Override
            public void focusLost(FocusEvent evt) {
                if (emailTextField.getText().trim().isEmpty()) {
                    emailTextField.setText(emailPlaceholder);
                    emailTextField.setForeground(initialText);
                }
            }
        });

        passwordTextField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent evt) {
                if (passwordTextField.getForeground().equals(initialText)) {
                    setBorder("pass", true);
                    passwordTextField.setText("");
                    passwordTextField.setForeground(currentTextColor);

                }
            }

            @Override
            public void focusLost(FocusEvent evt) {
                if (passwordTextField.getPassword().length == 0) {
                    passwordTextField.setEchoChar((char)0);
                    passwordTextField.setText(passwordPlaceholder);
                    passwordTextField.setForeground(initialText);
                }
            }
        });
        
        emailTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    passwordTextField.requestFocus();
                    passwordTextField.selectAll();
                }
            }
        });
        
        passwordTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    login.requestFocus();
                    login.doClick();
                }
            }
        });
    }
    
    private void setupPlaceholder() {
        emailTextField.setText(emailPlaceholder);
        emailTextField.setForeground(initialText);
        passwordTextField.setText(passwordPlaceholder);
        passwordTextField.setForeground(initialText);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        loginCard = new javax.swing.JPanel(){
            private int corner = 25;
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), corner, corner);
                g2.dispose();
                super.paintComponent(g);
            }
        };
        loginLabelPanel = new javax.swing.JPanel();
        loginLabel = new javax.swing.JLabel();
        loginFields = new javax.swing.JPanel();
        emailLabel = new javax.swing.JLabel();
        emailTextField = new javax.swing.JTextField();
        passwordLabel = new javax.swing.JLabel();
        passwordTextField = new javax.swing.JPasswordField();
        loginPanel = new javax.swing.JPanel();
        login = new javax.swing.JButton();
        orPanel = new javax.swing.JPanel();
        orLabel = new javax.swing.JLabel();
        separatorRight = new javax.swing.JSeparator();
        jSeparator1 = new javax.swing.JSeparator();
        signupPanel = new javax.swing.JPanel();
        signupLabel = new javax.swing.JLabel();
        signupButton = new javax.swing.JLabel();
        showPassword = new javax.swing.JCheckBox();

        setBackground(new java.awt.Color(21, 21, 23));
        setMinimumSize(new java.awt.Dimension(480, 255));
        setPreferredSize(new java.awt.Dimension(1440, 810));
        setLayout(new java.awt.GridBagLayout());

        loginCard.setBackground(new java.awt.Color(51, 51, 51));
        loginCard.setForeground(new java.awt.Color(51, 51, 51));
        loginCard.setOpaque(false);
        loginCard.setPreferredSize(new java.awt.Dimension(500, 525));

        loginLabelPanel.setBackground(new java.awt.Color(51, 51, 51));

        loginLabel.setFont(new java.awt.Font("Segoe UI Variable", 1, 36)); // NOI18N
        loginLabel.setForeground(new java.awt.Color(255, 255, 255));
        loginLabel.setText("Login");

        loginFields.setBackground(new java.awt.Color(51, 51, 51));

        emailLabel.setFont(new java.awt.Font("Segoe UI Variable", 1, 14)); // NOI18N
        emailLabel.setForeground(new java.awt.Color(255, 255, 255));
        emailLabel.setText("Email/Username");

        emailTextField.setFont(new java.awt.Font("Segoe UI Variable", 1, 14)); // NOI18N
        emailTextField.setText("\n");
        emailTextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                emailTextFieldFocusGained(evt);
            }
        });

        passwordLabel.setFont(new java.awt.Font("Segoe UI Variable", 1, 14)); // NOI18N
        passwordLabel.setForeground(new java.awt.Color(255, 255, 255));
        passwordLabel.setText("Password");

        passwordTextField.setFont(new java.awt.Font("Segoe UI Variable", 1, 14)); // NOI18N
        passwordTextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                passwordTextFieldFocusGained(evt);
            }
        });

        javax.swing.GroupLayout loginFieldsLayout = new javax.swing.GroupLayout(loginFields);
        loginFields.setLayout(loginFieldsLayout);
        loginFieldsLayout.setHorizontalGroup(
            loginFieldsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(loginFieldsLayout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addGroup(loginFieldsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(emailLabel)
                    .addComponent(passwordLabel)
                    .addComponent(passwordTextField)
                    .addComponent(emailTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 376, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(62, Short.MAX_VALUE))
        );
        loginFieldsLayout.setVerticalGroup(
            loginFieldsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(loginFieldsLayout.createSequentialGroup()
                .addComponent(emailLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(emailTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addComponent(passwordLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(passwordTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout loginLabelPanelLayout = new javax.swing.GroupLayout(loginLabelPanel);
        loginLabelPanel.setLayout(loginLabelPanelLayout);
        loginLabelPanelLayout.setHorizontalGroup(
            loginLabelPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(loginLabelPanelLayout.createSequentialGroup()
                .addGap(203, 203, 203)
                .addComponent(loginLabel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(loginLabelPanelLayout.createSequentialGroup()
                .addComponent(loginFields, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 12, Short.MAX_VALUE))
        );
        loginLabelPanelLayout.setVerticalGroup(
            loginLabelPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(loginLabelPanelLayout.createSequentialGroup()
                .addComponent(loginLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addComponent(loginFields, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        loginPanel.setBackground(new java.awt.Color(51, 51, 51));

        login.setBackground(new java.awt.Color(122, 134, 254));
        login.setFont(new java.awt.Font("Segoe UI Variable", 1, 18)); // NOI18N
        login.setText("Login");
        login.addActionListener(this::loginActionPerformed);

        javax.swing.GroupLayout loginPanelLayout = new javax.swing.GroupLayout(loginPanel);
        loginPanel.setLayout(loginPanelLayout);
        loginPanelLayout.setHorizontalGroup(
            loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(loginPanelLayout.createSequentialGroup()
                .addGap(157, 157, 157)
                .addComponent(login, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        loginPanelLayout.setVerticalGroup(
            loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, loginPanelLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(login, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        orPanel.setBackground(new java.awt.Color(51, 51, 51));

        orLabel.setFont(new java.awt.Font("Segoe UI Variable", 1, 14)); // NOI18N
        orLabel.setForeground(new java.awt.Color(255, 255, 255));
        orLabel.setText("OR");

        javax.swing.GroupLayout orPanelLayout = new javax.swing.GroupLayout(orPanel);
        orPanel.setLayout(orPanelLayout);
        orPanelLayout.setHorizontalGroup(
            orPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(orPanelLayout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(orLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(separatorRight, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        orPanelLayout.setVerticalGroup(
            orPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(orPanelLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(orPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(orLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(separatorRight, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        signupPanel.setBackground(new java.awt.Color(51, 51, 51));

        signupLabel.setFont(new java.awt.Font("Segoe UI Variable", 1, 14)); // NOI18N
        signupLabel.setForeground(new java.awt.Color(255, 255, 255));
        signupLabel.setText("Don't have an account?");

        signupButton.setFont(new java.awt.Font("Segoe UI Variable", 1, 14)); // NOI18N
        signupButton.setForeground(new java.awt.Color(102, 204, 255));
        signupButton.setText("Sign up");
        signupButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                signupButtonMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout signupPanelLayout = new javax.swing.GroupLayout(signupPanel);
        signupPanel.setLayout(signupPanelLayout);
        signupPanelLayout.setHorizontalGroup(
            signupPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(signupPanelLayout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addComponent(signupLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(signupButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        signupPanelLayout.setVerticalGroup(
            signupPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, signupPanelLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(signupPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(signupLabel)
                    .addComponent(signupButton)))
        );

        showPassword.setFont(new java.awt.Font("Segoe UI Variable", 1, 12)); // NOI18N
        showPassword.setForeground(new java.awt.Color(153, 153, 153));
        showPassword.setText("Show password");
        showPassword.addActionListener(this::showPasswordActionPerformed);

        javax.swing.GroupLayout loginCardLayout = new javax.swing.GroupLayout(loginCard);
        loginCard.setLayout(loginCardLayout);
        loginCardLayout.setHorizontalGroup(
            loginCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(loginCardLayout.createSequentialGroup()
                .addGroup(loginCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(loginCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(loginPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(orPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(signupPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(loginLabelPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(loginCardLayout.createSequentialGroup()
                        .addGap(62, 62, 62)
                        .addComponent(showPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(135, 135, 135))
        );
        loginCardLayout.setVerticalGroup(
            loginCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(loginCardLayout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(loginLabelPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(showPassword)
                .addGap(23, 23, 23)
                .addComponent(loginPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(orPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19)
                .addComponent(signupPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        add(loginCard, new java.awt.GridBagConstraints());
    }// </editor-fold>//GEN-END:initComponents

    private void loginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginActionPerformed
         // Get values
           String email = emailTextField.getText().trim();
        String password = new String(passwordTextField.getPassword()).trim();

        // Check if it's placeholder text
        boolean isEmailPlaceholder = email.equals(emailPlaceholder);
        boolean isPasswordPlaceholder = password.equals(passwordPlaceholder.trim());

        // Validation
        if (email.isEmpty() || isEmailPlaceholder) {
            setBorder("email", false);
            JOptionPane.showMessageDialog(this, "Email is Required!");
            return;
        }

        if (password.isEmpty() || isPasswordPlaceholder) {
            setBorder("pass", false);
            JOptionPane.showMessageDialog(this, "Password is Required!");
            return;
        }

        // Disable login button during authentication
        login.setEnabled(false);
        String originalButtonText = login.getText();
        login.setText("Logging in...");

        try {
            // Create UserService instance
            UserService userService = new UserService();

            // Generate username from email (same way as in UserService)
            String username = generateUsernameFromEmail(email);

            // Debug: Print what we're trying to login
            System.out.println("Attempting login with:");
            System.out.println("Email: " + email);
            System.out.println("Generated username: " + username);
            System.out.println("Password length: " + password.length());

            // Authenticate user using UserService's login method
            User loggedInUser = userService.login(username, password);

            // Login successful!
            System.out.println("Login successful! User ID: " + loggedInUser.getUserId());
            System.out.println("Username: " + loggedInUser.getUsername());
            System.out.println("Email: " + loggedInUser.getEmail());

            // Reset button
            login.setEnabled(true);
            login.setText(originalButtonText);
            setBorder("both", true);

            // Show success message
            JOptionPane.showMessageDialog(this, 
                "Login successful!\nWelcome, " + loggedInUser.getUsername() + "!",
                "Success", 
                JOptionPane.INFORMATION_MESSAGE);

            // Clear fields
            emailTextField.setText("");
            passwordTextField.setText("");
            setupPlaceholder();

            // Navigate to HomePage
            if (mainFrame != null) {
                mainFrame.showCard("HomePage");
            }

        } catch (AuthenticationException e) {
            // Authentication failed
            login.setEnabled(true);
            login.setText(originalButtonText);
            setBorder("both", false);

            System.err.println("Authentication failed: " + e.getMessage());
            JOptionPane.showMessageDialog(this, 
                "Invalid email or password. Please try again.",
                "Login Failed", 
                JOptionPane.ERROR_MESSAGE);

        } catch (DatabaseException e) {
            // Database error
            login.setEnabled(true);
            login.setText(originalButtonText);

            System.err.println("Database error during login: " + e.getMessage());
            e.printStackTrace();

            JOptionPane.showMessageDialog(this, 
                "Unable to connect to database. Please try again later.",
                "Connection Error", 
                JOptionPane.ERROR_MESSAGE);

        } catch (Exception e) {
            // Any other error
            login.setEnabled(true);
            login.setText(originalButtonText);

            System.err.println("Unexpected error during login: " + e.getMessage());
            e.printStackTrace();

            JOptionPane.showMessageDialog(this, 
                "An unexpected error occurred: " + e.getMessage(),
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        }    
    }//GEN-LAST:event_loginActionPerformed
    
    private String generateUsernameFromEmail(String email) {
        if (email == null || !email.contains("@")) {
            // Fallback if email is invalid
            return "user_" + (System.currentTimeMillis() % 10000);
        }

        String username = email.split("@")[0];

        // Clean up - remove special characters, keep only letters, numbers, underscores
        username = username.replaceAll("[^a-zA-Z0-9_]", "_");

        // Ensure proper length
        if (username.length() < 3) {
            username = username + "_" + (int)(Math.random() * 1000);
        } else if (username.length() > 20) {
            username = username.substring(0, 20);
        }

        return username;
    }
    private void signupButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_signupButtonMouseClicked
        handleSignupClick(); 
        setBorder("both", true);
    }//GEN-LAST:event_signupButtonMouseClicked

    private void showPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showPasswordActionPerformed
        if (showPassword.isSelected()) {
            passwordTextField.setEchoChar((char)0);
        } else {
            passwordTextField.setEchoChar((char)'•');
        }
    }//GEN-LAST:event_showPasswordActionPerformed

    private void emailTextFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_emailTextFieldFocusGained
        setBorder("email", true);
    }//GEN-LAST:event_emailTextFieldFocusGained

    private void passwordTextFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_passwordTextFieldFocusGained
        setBorder("pass", true);
    }//GEN-LAST:event_passwordTextFieldFocusGained


    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel emailLabel;
    private javax.swing.JTextField emailTextField;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JButton login;
    private javax.swing.JPanel loginCard;
    private javax.swing.JPanel loginFields;
    private javax.swing.JLabel loginLabel;
    private javax.swing.JPanel loginLabelPanel;
    private javax.swing.JPanel loginPanel;
    private javax.swing.JLabel orLabel;
    private javax.swing.JPanel orPanel;
    private javax.swing.JLabel passwordLabel;
    private javax.swing.JPasswordField passwordTextField;
    private javax.swing.JSeparator separatorRight;
    private javax.swing.JCheckBox showPassword;
    private javax.swing.JLabel signupButton;
    private javax.swing.JLabel signupLabel;
    private javax.swing.JPanel signupPanel;
    // End of variables declaration//GEN-END:variables
}
