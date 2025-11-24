/*
TODO:

1. Separate Buttons to a new class
2. change the buttons to arrays
3. optimize code
*/

package main;

import java.awt.CardLayout;
import java.awt.Dimension;
import javax.swing.SwingConstants;

public class Menu extends javax.swing.JPanel {
  
    private boolean isMinimized = false;    

    public Menu() {
        initComponents();
        
        // Customizations
        
        // to round the menu panel
        menu.putClientProperty("JComponent.roundRect", true);
        menu.putClientProperty("JComponent.roundRect.arc", 25);
        
        // rounds the min button
        minimize.putClientProperty("JButton.buttonType", "roundRect");
        minimize.putClientProperty("JComponent.roundRect.arc", 100);
    }
    
    private void showPanel(String name){
        CardLayout card = (CardLayout) MainFrame.Interface.getLayout();
        card.show(MainFrame.Interface, name);
    }
    
    private void btnTextField(boolean isMinimized){
        if(isMinimized){
            // text fields
            home.setText("Home");
            notes.setText("Notes");
            files.setText("Files");
            widgets.setText("Widgets");
            settings.setText("Settings");
            minimize.setText("");
        } else{
            // text fields
            home.setText("");
            notes.setText("");
            files.setText("");
            widgets.setText("");
            settings.setText("");
            minimize.setText("");
        }
    }
    
    private void btnCorners(boolean isMinimized){
        if(isMinimized){
            home.putClientProperty("JButton.buttonType", true);
            home.putClientProperty("JComponent.roundRect.arc", 20);
            notes.putClientProperty("JButton.buttonType", true);
            notes.putClientProperty("JComponent.roundRect.arc", 20);
            files.putClientProperty("JButton.buttonType", true);
            files.putClientProperty("JComponent.roundRect.arc", 20);
            widgets.putClientProperty("JButton.buttonType", true);
            widgets.putClientProperty("JComponent.roundRect.arc", 20);
            settings.putClientProperty("JButton.buttonType", true);
            settings.putClientProperty("JComponent.roundRect.arc", 20);
        } else {
            home.putClientProperty("JButton.buttonType", "roundRect");
            home.putClientProperty("JComponent.roundRect.arc", 100);
            notes.putClientProperty("JButton.buttonType", "roundRect");
            notes.putClientProperty("JComponent.roundRect.arc", 100);
            files.putClientProperty("JButton.buttonType", "roundRect");
            files.putClientProperty("JComponent.roundRect.arc", 100);
            widgets.putClientProperty("JButton.buttonType", "roundRect");
            widgets.putClientProperty("JComponent.roundRect.arc", 100);
            settings.putClientProperty("JButton.buttonType", "roundRect");
            settings.putClientProperty("JComponent.roundRect.arc", 100);
            
        }
    }
     
    private void btnAlignment(boolean isMinimized){
        if (isMinimized){
            minimize.setHorizontalAlignment(SwingConstants.LEFT);
            home.setHorizontalAlignment(SwingConstants.LEFT);
            notes.setHorizontalAlignment(SwingConstants.LEFT);
            files.setHorizontalAlignment(SwingConstants.LEFT);
            widgets.setHorizontalAlignment(SwingConstants.LEFT);
            settings.setHorizontalAlignment(SwingConstants.LEFT);
        } else {
            minimize.setHorizontalAlignment(SwingConstants.CENTER);
            home.setHorizontalAlignment(SwingConstants.CENTER);
            notes.setHorizontalAlignment(SwingConstants.CENTER);
            files.setHorizontalAlignment(SwingConstants.CENTER);
            widgets.setHorizontalAlignment(SwingConstants.CENTER);
            settings.setHorizontalAlignment(SwingConstants.CENTER);
        
        }
    }
    
    private void btnSize(boolean isMinimized){
        if (isMinimized){
            home.setPreferredSize(new Dimension(115, home.getHeight()));
            notes.setPreferredSize(new Dimension(115, notes.getHeight()));
            files.setPreferredSize(new Dimension(115, files.getHeight()));
            widgets.setPreferredSize(new Dimension(115, widgets.getHeight()));
        } else {
            home.setPreferredSize(new Dimension(40, home.getHeight()));
            notes.setPreferredSize(new Dimension(40, notes.getHeight()));
            files.setPreferredSize(new Dimension(40, files.getHeight()));
            widgets.setPreferredSize(new Dimension(40, widgets.getHeight()));
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        menu = new javax.swing.JPanel();
        minimize = new javax.swing.JButton();
        home = new javax.swing.JButton();
        notes = new javax.swing.JButton();
        files = new javax.swing.JButton();
        widgets = new javax.swing.JButton();
        settings = new javax.swing.JButton();

        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.LINE_AXIS));

        menu.setBackground(new java.awt.Color(102, 102, 102));
        menu.setMinimumSize(new java.awt.Dimension(50, 100));
        menu.setPreferredSize(new java.awt.Dimension(200, 568));

        minimize.setBackground(new java.awt.Color(51, 51, 51));
        minimize.setForeground(new java.awt.Color(0, 0, 0));
        minimize.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/icons/arrow-left.png"))); // NOI18N
        minimize.setBorderPainted(false);
        minimize.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        minimize.setFocusPainted(false);
        minimize.addActionListener(this::minimizeActionPerformed);

        home.setBackground(new java.awt.Color(51, 51, 51));
        home.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        home.setForeground(new java.awt.Color(0, 0, 0));
        home.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/icons/home.png"))); // NOI18N
        home.setText("Home");
        home.setBorderPainted(false);
        home.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        home.setFocusPainted(false);
        home.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        home.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        home.setIconTextGap(0);
        home.setMargin(new java.awt.Insets(2, 5, 3, 5));
        home.setMaximumSize(new java.awt.Dimension(113, 37));
        home.setMinimumSize(new java.awt.Dimension(0, 0));
        home.addActionListener(this::homeActionPerformed);

        notes.setBackground(new java.awt.Color(51, 51, 51));
        notes.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        notes.setForeground(new java.awt.Color(0, 0, 0));
        notes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/icons/notes.png"))); // NOI18N
        notes.setText("Notes");
        notes.setBorderPainted(false);
        notes.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        notes.setFocusPainted(false);
        notes.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        notes.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        notes.setIconTextGap(0);
        notes.setMargin(new java.awt.Insets(2, 5, 3, 5));
        notes.setMaximumSize(new java.awt.Dimension(111, 37));
        notes.setMinimumSize(new java.awt.Dimension(0, 0));
        notes.addActionListener(this::notesActionPerformed);

        files.setBackground(new java.awt.Color(51, 51, 51));
        files.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        files.setForeground(new java.awt.Color(0, 0, 0));
        files.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/icons/folder.png"))); // NOI18N
        files.setText("Files");
        files.setBorderPainted(false);
        files.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        files.setFocusPainted(false);
        files.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        files.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        files.setIconTextGap(0);
        files.setMargin(new java.awt.Insets(2, 5, 3, 5));
        files.setMaximumSize(new java.awt.Dimension(98, 37));
        files.setMinimumSize(new java.awt.Dimension(0, 0));
        files.addActionListener(this::filesActionPerformed);

        widgets.setBackground(new java.awt.Color(51, 51, 51));
        widgets.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        widgets.setForeground(new java.awt.Color(0, 0, 0));
        widgets.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/icons/plugin.png"))); // NOI18N
        widgets.setText("Widgets");
        widgets.setBorderPainted(false);
        widgets.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        widgets.setFocusPainted(false);
        widgets.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        widgets.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        widgets.setIconTextGap(0);
        widgets.setMargin(new java.awt.Insets(2, 5, 3, 5));
        widgets.setMaximumSize(new java.awt.Dimension(130, 37));
        widgets.setMinimumSize(new java.awt.Dimension(0, 0));
        widgets.addActionListener(this::widgetsActionPerformed);

        settings.setBackground(new java.awt.Color(51, 51, 51));
        settings.setForeground(new java.awt.Color(0, 0, 0));
        settings.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/icons/settings.png"))); // NOI18N
        settings.setText("Settings");
        settings.setBorderPainted(false);
        settings.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        settings.setFocusPainted(false);
        settings.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        settings.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        settings.addActionListener(this::settingsActionPerformed);

        javax.swing.GroupLayout menuLayout = new javax.swing.GroupLayout(menu);
        menu.setLayout(menuLayout);
        menuLayout.setHorizontalGroup(
            menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, menuLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(notes, javax.swing.GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)
                    .addComponent(files, javax.swing.GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)
                    .addComponent(home, javax.swing.GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)
                    .addComponent(widgets, javax.swing.GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)
                    .addComponent(minimize))
                .addContainerGap())
            .addGroup(menuLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(settings)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        menuLayout.setVerticalGroup(
            menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(minimize)
                .addGap(18, 18, 18)
                .addComponent(home, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(notes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(files, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(widgets, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 213, Short.MAX_VALUE)
                .addComponent(settings)
                .addGap(43, 43, 43))
        );

        add(menu);
    }// </editor-fold>//GEN-END:initComponents

    private void homeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_homeActionPerformed
        showPanel("home");
    }//GEN-LAST:event_homeActionPerformed

    private void notesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_notesActionPerformed
        showPanel("notes");
    }//GEN-LAST:event_notesActionPerformed

    private void filesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filesActionPerformed
        showPanel("files");
    }//GEN-LAST:event_filesActionPerformed

    private void widgetsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_widgetsActionPerformed
        showPanel("widgets");
    }//GEN-LAST:event_widgetsActionPerformed

    private void settingsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_settingsActionPerformed
        showPanel("settings");
    }//GEN-LAST:event_settingsActionPerformed

    private void minimizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_minimizeActionPerformed
        if(isMinimized){
            MainFrame.menu.setPreferredSize(new Dimension(200, menu.getHeight()));
            
            btnTextField(isMinimized);
            btnCorners(isMinimized);
            btnAlignment(isMinimized);
            btnSize(isMinimized);
            
            // others
            minimize.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/icons/arrow-left.png")));
            isMinimized = false;
        } else {
            MainFrame.menu.setPreferredSize(new Dimension(100, menu.getHeight()));
            
            btnTextField(isMinimized);
            btnCorners(isMinimized);
            btnAlignment(isMinimized);
            btnSize(isMinimized);
            
            // others
            minimize.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/icons/arrow-right.png")));
            isMinimized = true;
        }
        MainFrame.menu.revalidate();
        MainFrame.menu.repaint();
    }//GEN-LAST:event_minimizeActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton files;
    private javax.swing.JButton home;
    private javax.swing.JPanel menu;
    private javax.swing.JButton minimize;
    private javax.swing.JButton notes;
    private javax.swing.JButton settings;
    private javax.swing.JButton widgets;
    // End of variables declaration//GEN-END:variables
}
