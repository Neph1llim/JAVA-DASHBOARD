package main.interfaces;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.Timer;
import main.MainFrame;

public class Menu extends javax.swing.JPanel {
  
    public Menu() {
        initComponents();
        // to round the menu panel
        putClientProperty("JComponent.roundRect", true);
        putClientProperty("JComponent.roundRect.arc", 25);
    }
         
        @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        menu = new javax.swing.JPanel();
        settings = new javax.swing.JButton();
        home = new javax.swing.JButton();
        widgets = new javax.swing.JButton();
        files = new javax.swing.JButton();
        notes = new javax.swing.JButton();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(212, 52), new java.awt.Dimension(32767, 500));

        setMinimumSize(new java.awt.Dimension(50, 250));
        setPreferredSize(new java.awt.Dimension(250, 810));
        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.LINE_AXIS));

        menu.setBackground(new java.awt.Color(102, 102, 102));
        menu.setMinimumSize(new java.awt.Dimension(50, 300));
        menu.setPreferredSize(new java.awt.Dimension(250, 810));
        menu.setLayout(new java.awt.GridBagLayout());

        settings.setBackground(new java.awt.Color(51, 51, 51));
        settings.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        settings.setForeground(new java.awt.Color(0, 0, 0));
        settings.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/resource/settings.png"))); // NOI18N
        settings.setText("Settings");
        settings.setBorderPainted(false);
        settings.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        settings.setFocusPainted(false);
        settings.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        settings.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        settings.setMargin(new java.awt.Insets(2, 5, 3, 5));
        settings.setMinimumSize(new java.awt.Dimension(111, 43));
        settings.setPreferredSize(new java.awt.Dimension(212, 52));
        settings.addActionListener(this::settingsActionPerformed);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 142;
        gridBagConstraints.ipady = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(12, 5, 5, 6);
        menu.add(settings, gridBagConstraints);

        home.setBackground(new java.awt.Color(51, 51, 51));
        home.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        home.setForeground(new java.awt.Color(0, 0, 0));
        home.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/resource/home.png"))); // NOI18N
        home.setText("Home");
        home.setBorderPainted(false);
        home.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        home.setFocusPainted(false);
        home.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        home.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        home.setIconTextGap(0);
        home.setMargin(new java.awt.Insets(2, 5, 3, 5));
        home.setMaximumSize(new java.awt.Dimension(250, 72));
        home.setMinimumSize(new java.awt.Dimension(111, 43));
        home.setPreferredSize(new java.awt.Dimension(212, 52));
        home.addActionListener(this::homeActionPerformed);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 142;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 6);
        menu.add(home, gridBagConstraints);

        widgets.setBackground(new java.awt.Color(51, 51, 51));
        widgets.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        widgets.setForeground(new java.awt.Color(0, 0, 0));
        widgets.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/resource/plugin.png"))); // NOI18N
        widgets.setText("Widgets");
        widgets.setBorderPainted(false);
        widgets.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        widgets.setFocusPainted(false);
        widgets.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        widgets.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        widgets.setIconTextGap(0);
        widgets.setMargin(new java.awt.Insets(2, 5, 3, 5));
        widgets.setMaximumSize(new java.awt.Dimension(250, 72));
        widgets.setMinimumSize(new java.awt.Dimension(111, 43));
        widgets.setPreferredSize(new java.awt.Dimension(212, 52));
        widgets.addActionListener(this::widgetsActionPerformed);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 142;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(12, 5, 0, 5);
        menu.add(widgets, gridBagConstraints);

        files.setBackground(new java.awt.Color(51, 51, 51));
        files.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        files.setForeground(new java.awt.Color(0, 0, 0));
        files.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/resource/folder.png"))); // NOI18N
        files.setText("Files");
        files.setBorderPainted(false);
        files.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        files.setFocusPainted(false);
        files.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        files.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        files.setIconTextGap(0);
        files.setMargin(new java.awt.Insets(2, 5, 3, 5));
        files.setMaximumSize(new java.awt.Dimension(250, 72));
        files.setMinimumSize(new java.awt.Dimension(111, 43));
        files.setPreferredSize(new java.awt.Dimension(212, 52));
        files.addActionListener(this::filesActionPerformed);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 142;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(12, 5, 0, 6);
        menu.add(files, gridBagConstraints);

        notes.setBackground(new java.awt.Color(51, 51, 51));
        notes.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        notes.setForeground(new java.awt.Color(0, 0, 0));
        notes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/resource/notes.png"))); // NOI18N
        notes.setText("Notes");
        notes.setBorderPainted(false);
        notes.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        notes.setFocusPainted(false);
        notes.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        notes.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        notes.setIconTextGap(0);
        notes.setMargin(new java.awt.Insets(2, 5, 3, 5));
        notes.setMaximumSize(new java.awt.Dimension(250, 72));
        notes.setMinimumSize(new java.awt.Dimension(111, 43));
        notes.setPreferredSize(new java.awt.Dimension(212, 52));
        notes.addActionListener(this::notesActionPerformed);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 142;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(12, 5, 0, 6);
        menu.add(notes, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 214;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(12, 16, 0, 20);
        menu.add(filler1, gridBagConstraints);

        add(menu);
    }// </editor-fold>//GEN-END:initComponents

    private void settingsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_settingsActionPerformed
        showPanel("settings");
    }//GEN-LAST:event_settingsActionPerformed

    private void widgetsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_widgetsActionPerformed
        showPanel("widgets");
    }//GEN-LAST:event_widgetsActionPerformed

    private void filesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filesActionPerformed
        showPanel("files");
    }//GEN-LAST:event_filesActionPerformed

    private void notesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_notesActionPerformed
        showPanel("notes");
    }//GEN-LAST:event_notesActionPerformed

    private void homeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_homeActionPerformed
        showPanel("home");
    }//GEN-LAST:event_homeActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton files;
    private javax.swing.Box.Filler filler1;
    private javax.swing.JButton home;
    private javax.swing.JPanel menu;
    private javax.swing.JButton notes;
    private javax.swing.JButton settings;
    private javax.swing.JButton widgets;
    // End of variables declaration//GEN-END:variables

// Codes area    
    private void showPanel(String name){
        CardLayout card = (CardLayout) MainFrame.Interface.getLayout();
        card.show(MainFrame.Interface, name);
    }
    
    private JButton[] menuButtons() {
        return new JButton[] { home, notes, files, widgets, settings };
    }
    
    public void Minimize(boolean isMinimized){
        int targetWidth = isMinimized ? 60 : 250;
        int btnWidth = isMinimized ? 42 : 115;

        setPreferredSize(new Dimension(targetWidth, getPreferredSize().height));

        for (JButton btn : menuButtons()) {
            btn.setPreferredSize(new Dimension(btnWidth, btn.getHeight()));
        }

        if (isMinimized) {
            for (JButton btn : menuButtons()) {
                btn.setText("");
            }
        } else {
            home.setText("Home");
            notes.setText("Notes");
            files.setText("Files");
            widgets.setText("Widgets");
            settings.setText("Settings");
        }

        revalidate();
        repaint();
    }

    public void animateToWidth(int currentWidth, boolean willBeMinimized) {
        setPreferredSize(new Dimension(currentWidth, getPreferredSize().height));

        int btnWidth = 42 + (115 - 42) * (currentWidth - 60) / (250 - 60);
        for (JButton btn : menuButtons()) {
            btn.setPreferredSize(new Dimension(btnWidth, btn.getHeight()));
        }

        if (willBeMinimized && currentWidth < 155) {
            for (JButton btn : menuButtons()) {
                btn.setText("");
            }
        } else if (!willBeMinimized && currentWidth > 155) {
            home.setText("Home");
            notes.setText("Notes");
            files.setText("Files");
            widgets.setText("Widgets");
            settings.setText("Settings");
        }
    }
}