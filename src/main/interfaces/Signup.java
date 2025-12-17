package main.interfaces;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import main.MainFrame;
import backend.services.UserService;
import backend.model.User;
import javax.swing.border.Border;

public class Signup extends javax.swing.JPanel {

    /* Properties */
    String emailPlaceholder = "24-XXXXX@g.batstate-u.edu.ph";
    String passwordPlaceholder = "               ";
    Color initialText = new Color(204,204, 204);
    private final Color currentTextColor = Color.WHITE;
    
    public Signup() {  
        initComponents();
        setupPlaceholder();
        setupListeners();
        setBorder("both", true);
    }
    
    /* Methods */   
    private void showPanel(String name){
        CardLayout card = (CardLayout) MainFrame.Login.getLayout();
        card.show(MainFrame.Login, name);
    }
    
    private void setBorder(String type, boolean isValid){
        Border normal = BorderFactory.createLineBorder(Color.GRAY,2);
        Border red = BorderFactory.createLineBorder(Color.RED, 2);
        Border current = !isValid ? red: normal;
        
        switch(type){
            case "email"-> {emailField.setBorder(current);break;}
            case "pass" -> {passwordTextField.setBorder(current);break;}
            case "both" -> {emailField.setBorder(current);passwordTextField.setBorder(current);break;}
        }
    }
    
    private void setupListeners() {
        RedirectLogin.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                RedirectLogin.setForeground(new Color(135, 206, 250));
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                RedirectLogin.setForeground(new Color(102, 204, 255));
            }
        });
        
        emailField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent evt) {
                if (emailField.getForeground().equals(initialText)) {
                    setBorder("email", true);
                    emailField.setText("");
                    emailField.setForeground(currentTextColor);
                }
            }

            @Override
            public void focusLost(FocusEvent evt) {
                if (emailField.getText().trim().isEmpty()) {
                    emailField.setText(emailPlaceholder);
                    emailField.setForeground(initialText);
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
        
        emailField.addKeyListener(new KeyAdapter() {
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
                    signup.requestFocus();
                    signup.doClick();
                }
            }
        });
    }
    
    private void setupPlaceholder() {
        emailField.setText(emailPlaceholder);
        emailField.setForeground(initialText);
        passwordTextField.setText(passwordPlaceholder);
        passwordTextField.setForeground(initialText);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        signupCard = new javax.swing.JPanel(){
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
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        emailField = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        passwordTextField = new javax.swing.JPasswordField();
        signup = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel5 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel6 = new javax.swing.JLabel();
        RedirectLogin = new javax.swing.JLabel();
        showPassword = new javax.swing.JCheckBox();

        setBackground(new java.awt.Color(21, 21, 23));
        setPreferredSize(new java.awt.Dimension(1440, 810));
        setLayout(new java.awt.GridBagLayout());

        signupCard.setBackground(new java.awt.Color(51, 51, 51));
        signupCard.setForeground(new java.awt.Color(51, 51, 51));
        signupCard.setOpaque(false);
        signupCard.setPreferredSize(new java.awt.Dimension(500, 525));

        jLabel1.setFont(new java.awt.Font("Segoe UI Variable", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Sign Up");

        jLabel3.setFont(new java.awt.Font("Segoe UI Variable", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Email");

        emailField.setFont(new java.awt.Font("Segoe UI Variable", 1, 14)); // NOI18N
        emailField.setPreferredSize(new java.awt.Dimension(222, 26));
        emailField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                emailFieldFocusGained(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI Variable", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Password");

        passwordTextField.setFont(new java.awt.Font("Segoe UI Variable", 1, 14)); // NOI18N
        passwordTextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                passwordTextFieldFocusGained(evt);
            }
        });

        signup.setBackground(new java.awt.Color(122, 134, 254));
        signup.setFont(new java.awt.Font("Segoe UI Variable", 1, 18)); // NOI18N
        signup.setText("Sign Up");
        signup.addActionListener(this::signupActionPerformed);

        jLabel5.setFont(new java.awt.Font("Segoe UI Variable", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("OR");

        jLabel6.setFont(new java.awt.Font("Segoe UI Variable", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Already have an account?");

        RedirectLogin.setFont(new java.awt.Font("Segoe UI Variable", 1, 14)); // NOI18N
        RedirectLogin.setForeground(new java.awt.Color(102, 204, 255));
        RedirectLogin.setText("Login");
        RedirectLogin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                RedirectLoginMouseClicked(evt);
            }
        });

        showPassword.setFont(new java.awt.Font("Segoe UI Variable", 1, 12)); // NOI18N
        showPassword.setForeground(new java.awt.Color(153, 153, 153));
        showPassword.setText("Show password");
        showPassword.addActionListener(this::showPasswordActionPerformed);

        javax.swing.GroupLayout signupCardLayout = new javax.swing.GroupLayout(signupCard);
        signupCard.setLayout(signupCardLayout);
        signupCardLayout.setHorizontalGroup(
            signupCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, signupCardLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(signup, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(157, 157, 157))
            .addGroup(signupCardLayout.createSequentialGroup()
                .addGroup(signupCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(signupCardLayout.createSequentialGroup()
                        .addGap(185, 185, 185)
                        .addComponent(jLabel1))
                    .addGroup(signupCardLayout.createSequentialGroup()
                        .addGap(62, 62, 62)
                        .addGroup(signupCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(signupCardLayout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(RedirectLogin))
                            .addGroup(signupCardLayout.createSequentialGroup()
                                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(signupCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(emailField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel2)
                                .addComponent(passwordTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 376, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel3))
                            .addComponent(showPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(62, Short.MAX_VALUE))
        );
        signupCardLayout.setVerticalGroup(
            signupCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, signupCardLayout.createSequentialGroup()
                .addContainerGap(37, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(emailField, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(passwordTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(showPassword)
                .addGap(23, 23, 23)
                .addComponent(signup, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addGroup(signupCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jSeparator3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(signupCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(RedirectLogin))
                .addGap(39, 39, 39))
        );

        add(signupCard, new java.awt.GridBagConstraints());
    }// </editor-fold>//GEN-END:initComponents

    private void RedirectLoginMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_RedirectLoginMouseClicked
        showPanel("login");
        setupPlaceholder();
        showPassword.setSelected(false);
    }//GEN-LAST:event_RedirectLoginMouseClicked

    private void signupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_signupActionPerformed
        // Get values from fields
        String email = emailField.getText();
        String password = new String(passwordTextField.getPassword());

        // Trim whitespace
        email = email.trim();
        password = password.trim();

        // Basic validation
        if (email.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Email is required!");
            emailField.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Password is required!");
            passwordTextField.requestFocus();
            return;
        }

        // Create UserService and try to register
        try {
            UserService userService = new UserService();
            User newUser = userService.register(email, password);

            // Success
            JOptionPane.showMessageDialog(this, 
                "Registration successful! Username: " + newUser.getUsername());

            // Clear fields
            emailField.setText("");
            passwordTextField.setText("");

            // Navigate to login
            showPanel("login");

        } catch (Exception e) {
            // Show error
            JOptionPane.showMessageDialog(this, 
                "Error: " + e.getMessage());
        }
    }//GEN-LAST:event_signupActionPerformed

    private void showPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showPasswordActionPerformed
        if (showPassword.isSelected()) {
            passwordTextField.setEchoChar((char)0);
        } else {
            passwordTextField.setEchoChar((char)'•');
        }
    }//GEN-LAST:event_showPasswordActionPerformed

    private void emailFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_emailFieldFocusGained
        setBorder("email", true);
    }//GEN-LAST:event_emailFieldFocusGained

    private void passwordTextFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_passwordTextFieldFocusGained
        setBorder("pass", true);
    }//GEN-LAST:event_passwordTextFieldFocusGained


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel RedirectLogin;
    private javax.swing.JTextField emailField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JPasswordField passwordTextField;
    private javax.swing.JCheckBox showPassword;
    private javax.swing.JButton signup;
    private javax.swing.JPanel signupCard;
    // End of variables declaration//GEN-END:variables
}
