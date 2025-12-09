package main.interfaces;

import java.awt.CardLayout;
import main.MainFrame;

public class Login extends javax.swing.JPanel {
    private MainFrame mainFrame;
    
    public Login() {
        initComponents();
    }
    
    /* Methods */
    public void setMainFrame(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
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
        separatorLeft = new javax.swing.JSeparator();
        separatorRight = new javax.swing.JSeparator();
        signupPanel = new javax.swing.JPanel();
        signupLabel = new javax.swing.JLabel();
        signupButton = new javax.swing.JLabel();

        setBackground(new java.awt.Color(21, 21, 23));
        setMinimumSize(new java.awt.Dimension(480, 255));
        setPreferredSize(new java.awt.Dimension(1440, 810));
        setLayout(new java.awt.GridBagLayout());

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));
        jPanel1.setPreferredSize(new java.awt.Dimension(500, 525));
        jPanel1.setLayout(new java.awt.GridBagLayout());

        loginLabelPanel.setBackground(new java.awt.Color(51, 51, 51));

        loginLabel.setFont(new java.awt.Font("Segoe UI Variable", 1, 36)); // NOI18N
        loginLabel.setForeground(new java.awt.Color(255, 255, 255));
        loginLabel.setText("Login");

        javax.swing.GroupLayout loginLabelPanelLayout = new javax.swing.GroupLayout(loginLabelPanel);
        loginLabelPanel.setLayout(loginLabelPanelLayout);
        loginLabelPanelLayout.setHorizontalGroup(
            loginLabelPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, loginLabelPanelLayout.createSequentialGroup()
                .addContainerGap(205, Short.MAX_VALUE)
                .addComponent(loginLabel)
                .addGap(201, 201, 201))
        );
        loginLabelPanelLayout.setVerticalGroup(
            loginLabelPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(loginLabelPanelLayout.createSequentialGroup()
                .addComponent(loginLabel)
                .addGap(0, 12, Short.MAX_VALUE))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        jPanel1.add(loginLabelPanel, gridBagConstraints);

        loginFields.setBackground(new java.awt.Color(51, 51, 51));

        emailLabel.setFont(new java.awt.Font("Segoe UI Variable", 1, 14)); // NOI18N
        emailLabel.setForeground(new java.awt.Color(255, 255, 255));
        emailLabel.setText("Email/Username");

        emailTextField.setFont(new java.awt.Font("Segoe UI Variable", 1, 14)); // NOI18N
        emailTextField.setText("24-XXXXX@g.batstate-u.edu.ph");
        emailTextField.addActionListener(this::emailTextFieldActionPerformed);

        passwordLabel.setFont(new java.awt.Font("Segoe UI Variable", 1, 14)); // NOI18N
        passwordLabel.setForeground(new java.awt.Color(255, 255, 255));
        passwordLabel.setText("Password");

        passwordTextField.setFont(new java.awt.Font("Segoe UI Variable", 1, 14)); // NOI18N
        passwordTextField.setText("jPasswordField1");
        passwordTextField.addActionListener(this::passwordTextFieldActionPerformed);

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
                .addGap(0, 12, Short.MAX_VALUE))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 10, 0);
        jPanel1.add(loginFields, gridBagConstraints);

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
                .addGap(156, 156, 156)
                .addComponent(login, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(158, Short.MAX_VALUE))
        );
        loginPanelLayout.setVerticalGroup(
            loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, loginPanelLayout.createSequentialGroup()
                .addGap(0, 12, Short.MAX_VALUE)
                .addComponent(login, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        jPanel1.add(loginPanel, gridBagConstraints);

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
                .addComponent(separatorLeft, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(orLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(separatorRight, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(63, Short.MAX_VALUE))
        );
        orPanelLayout.setVerticalGroup(
            orPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, orPanelLayout.createSequentialGroup()
                .addGap(0, 12, Short.MAX_VALUE)
                .addGroup(orPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(separatorRight, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(orLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(separatorLeft, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 10, 0);
        jPanel1.add(orPanel, gridBagConstraints);

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
                .addContainerGap(231, Short.MAX_VALUE))
        );
        signupPanelLayout.setVerticalGroup(
            signupPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, signupPanelLayout.createSequentialGroup()
                .addGap(0, 12, Short.MAX_VALUE)
                .addGroup(signupPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(signupLabel)
                    .addComponent(signupButton)))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        jPanel1.add(signupPanel, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        add(jPanel1, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void passwordTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_passwordTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_passwordTextFieldActionPerformed

    private void emailTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emailTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_emailTextFieldActionPerformed

    private void loginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginActionPerformed
        // add logic here
        
        mainFrame.showCard("HomePage");
    }//GEN-LAST:event_loginActionPerformed

    private void signupButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_signupButtonMouseClicked
        // change to signup
        CardLayout card = (CardLayout) MainFrame.Login.getLayout();
        card.show(MainFrame.Login, "signup");
    }//GEN-LAST:event_signupButtonMouseClicked


    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel emailLabel;
    private javax.swing.JTextField emailTextField;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton login;
    private javax.swing.JPanel loginFields;
    private javax.swing.JLabel loginLabel;
    private javax.swing.JPanel loginLabelPanel;
    private javax.swing.JPanel loginPanel;
    private javax.swing.JLabel orLabel;
    private javax.swing.JPanel orPanel;
    private javax.swing.JLabel passwordLabel;
    private javax.swing.JPasswordField passwordTextField;
    private javax.swing.JSeparator separatorLeft;
    private javax.swing.JSeparator separatorRight;
    private javax.swing.JLabel signupButton;
    private javax.swing.JLabel signupLabel;
    private javax.swing.JPanel signupPanel;
    // End of variables declaration//GEN-END:variables
}
