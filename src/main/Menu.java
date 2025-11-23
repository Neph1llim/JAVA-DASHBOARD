package main;

import java.awt.CardLayout;
import java.awt.Dimension;


public class Menu extends javax.swing.JPanel {
  
    private boolean isMinimized = false;    
    
    public Menu() {
        initComponents();
    }
    
    private void showPanel(String name){
        CardLayout card = (CardLayout) MainFrame.Interface.getLayout();
        card.show(MainFrame.Interface, name);
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

        menu.setBackground(new java.awt.Color(102, 102, 102));
        menu.setMinimumSize(new java.awt.Dimension(50, 100));
        menu.setPreferredSize(new java.awt.Dimension(200, 568));

        minimize.setBackground(new java.awt.Color(51, 51, 51));
        minimize.setForeground(new java.awt.Color(0, 0, 0));
        minimize.setText("<<");
        minimize.addActionListener(this::minimizeActionPerformed);

        home.setBackground(new java.awt.Color(51, 51, 51));
        home.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        home.setForeground(new java.awt.Color(0, 0, 0));
        home.setText("Home");
        home.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        home.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        home.setMaximumSize(new java.awt.Dimension(113, 37));
        home.setMinimumSize(new java.awt.Dimension(50, 37));
        home.addActionListener(this::homeActionPerformed);

        notes.setBackground(new java.awt.Color(51, 51, 51));
        notes.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        notes.setForeground(new java.awt.Color(0, 0, 0));
        notes.setText("Notes");
        notes.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        notes.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        notes.setMaximumSize(new java.awt.Dimension(111, 37));
        notes.setMinimumSize(new java.awt.Dimension(50, 37));
        notes.addActionListener(this::notesActionPerformed);

        files.setBackground(new java.awt.Color(51, 51, 51));
        files.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        files.setForeground(new java.awt.Color(0, 0, 0));
        files.setText("Files");
        files.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        files.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        files.setMaximumSize(new java.awt.Dimension(98, 37));
        files.setMinimumSize(new java.awt.Dimension(50, 37));
        files.addActionListener(this::filesActionPerformed);

        widgets.setBackground(new java.awt.Color(51, 51, 51));
        widgets.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        widgets.setForeground(new java.awt.Color(0, 0, 0));
        widgets.setText("Widgets");
        widgets.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        widgets.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        widgets.setMaximumSize(new java.awt.Dimension(130, 37));
        widgets.setMinimumSize(new java.awt.Dimension(50, 37));
        widgets.addActionListener(this::widgetsActionPerformed);

        settings.setBackground(new java.awt.Color(51, 51, 51));
        settings.setForeground(new java.awt.Color(0, 0, 0));
        settings.setText("Settings");
        settings.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        settings.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        settings.addActionListener(this::settingsActionPerformed);

        javax.swing.GroupLayout menuLayout = new javax.swing.GroupLayout(menu);
        menu.setLayout(menuLayout);
        menuLayout.setHorizontalGroup(
            menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(notes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(files, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(home, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(widgets, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(menuLayout.createSequentialGroup()
                        .addComponent(minimize, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 139, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(menuLayout.createSequentialGroup()
                .addGap(51, 51, 51)
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 273, Short.MAX_VALUE)
                .addComponent(settings)
                .addGap(43, 43, 43))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(menu, javax.swing.GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(menu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
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
        if(!isMinimized){
            MainFrame.menu.setPreferredSize(new Dimension(100, menu.getHeight()));
            home.setText("1");
            notes.setText("2");
            files.setText("3");
            widgets.setText("4");
            settings.setText("5");
            minimize.setText(">>");
            isMinimized = true;
        } else {
            MainFrame.menu.setPreferredSize(new Dimension(200, menu.getHeight()));
            home.setText("Home");
            notes.setText("Notes");
            files.setText("Files");
            widgets.setText("Widgets");
            settings.setText("Settings");
            minimize.setText("<<");
            isMinimized = false;
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
