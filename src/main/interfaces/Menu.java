package main.interfaces;

/* import statements */
import java.awt.CardLayout;
import java.awt.Dimension;
import javax.swing.JButton;
import main.MainFrame;

public class Menu extends javax.swing.JPanel {
  
    /* Constructors */
    public Menu() {
        initComponents();
        // to round the menu panel
        putClientProperty("JComponent.roundRect", true);
        putClientProperty("JComponent.roundRect.arc", 25);
    }
         
    /* Methods */   
    private void showPanel(String name){
        CardLayout card = (CardLayout) MainFrame.Interface.getLayout();
        card.show(MainFrame.Interface, name);
    }
    
    private JButton[] menuButtons() {
        return new JButton[] { home, notes, files, widgets, settings };
    }
    
    public void Minimize(boolean isMinimized){
        int targetWidth = isMinimized ? 60 : 250; // ternary operator
        int btnWidth = isMinimized ? 42 : 115; // ternary operator

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

        //refresh the panel
        revalidate();
        repaint();
    }

    public void animateToWidth(int currentWidth, boolean minimize) {
        setPreferredSize(new Dimension(currentWidth, getPreferredSize().height));

        int btnWidth = 42 + (115 - 42) * (currentWidth - 60) / (250 - 60);
        for (JButton btn : menuButtons()) {
            btn.setPreferredSize(new Dimension(btnWidth, btn.getHeight()));
        }

        if (minimize && currentWidth < 155) {
            for (JButton btn : menuButtons()) {
                btn.setText("");
            }
        } 
        
        if (!minimize && currentWidth > 155) {
            home.setText("Home");
            notes.setText("Notes");
            files.setText("Files");
            widgets.setText("Widgets");
            settings.setText("Settings");
        }
    }

    /* Built-in codes and functions */
        @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel1 = new main.component.Panel();
        home = new main.component.Button();
        notes = new main.component.Button();
        files = new main.component.Button();
        widgets = new main.component.Button();
        settings = new main.component.Button();

        setMinimumSize(new java.awt.Dimension(50, 250));
        setPreferredSize(new java.awt.Dimension(250, 810));
        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.LINE_AXIS));

        panel1.setArc(0);
        panel1.setPanelBackground(new java.awt.Color(27, 27, 28));

        home.setBackground(new java.awt.Color(51, 51, 51));
        home.setForeground(new java.awt.Color(255, 255, 255));
        home.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/resource/home.png"))); // NOI18N
        home.setText("Home");
        home.setToolTipText("");
        home.setBorderColor(new java.awt.Color(0, 0, 0));
        home.setDisabledIcon(null);
        home.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        home.setHideActionText(true);
        home.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        home.setIconTextGap(12);
        home.setMargin(new java.awt.Insets(2, 6, 3, 14));
        home.addActionListener(this::homeActionPerformed);

        notes.setBackground(new java.awt.Color(51, 51, 51));
        notes.setForeground(new java.awt.Color(255, 255, 255));
        notes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/resource/notes.png"))); // NOI18N
        notes.setText("Notes");
        notes.setBorderColor(new java.awt.Color(0, 0, 0));
        notes.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        notes.setHideActionText(true);
        notes.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        notes.setIconTextGap(12);
        notes.setMargin(new java.awt.Insets(2, 6, 3, 14));
        notes.addActionListener(this::notesActionPerformed);

        files.setBackground(new java.awt.Color(51, 51, 51));
        files.setForeground(new java.awt.Color(255, 255, 255));
        files.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/resource/folder.png"))); // NOI18N
        files.setText("Files");
        files.setBorderColor(new java.awt.Color(0, 0, 0));
        files.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        files.setHideActionText(true);
        files.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        files.setIconTextGap(12);
        files.setMargin(new java.awt.Insets(2, 6, 3, 14));
        files.addActionListener(this::filesActionPerformed);
        files.addActionListener(this::filesActionPerformed);

        widgets.setBackground(new java.awt.Color(51, 51, 51));
        widgets.setForeground(new java.awt.Color(255, 255, 255));
        widgets.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/resource/plugin.png"))); // NOI18N
        widgets.setText("Widgets");
        widgets.setBorderColor(new java.awt.Color(0, 0, 0));
        widgets.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        widgets.setHideActionText(true);
        widgets.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        widgets.setIconTextGap(12);
        widgets.setMargin(new java.awt.Insets(2, 6, 3, 14));
        widgets.addActionListener(this::widgetsActionPerformed);

        settings.setBackground(new java.awt.Color(51, 51, 51));
        settings.setForeground(new java.awt.Color(255, 255, 255));
        settings.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/resource/settings.png"))); // NOI18N
        settings.setText("Settings");
        settings.setBorderColor(new java.awt.Color(0, 0, 0));
        settings.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        settings.setHideActionText(true);
        settings.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        settings.setIconTextGap(12);
        settings.setMargin(new java.awt.Insets(2, 6, 3, 14));
        settings.addActionListener(this::settingsActionPerformed);

        javax.swing.GroupLayout panel1Layout = new javax.swing.GroupLayout(panel1);
        panel1.setLayout(panel1Layout);
        panel1Layout.setHorizontalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(settings, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(files, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(widgets, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 238, Short.MAX_VALUE)
                    .addComponent(notes, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(home, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panel1Layout.setVerticalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(home, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(notes, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(files, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(widgets, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 487, Short.MAX_VALUE)
                .addComponent(settings, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        widgets.getAccessibleContext().setAccessibleDescription("");

        add(panel1);
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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private main.component.Button files;
    private main.component.Button home;
    private main.component.Button notes;
    private main.component.Panel panel1;
    private main.component.Button settings;
    private main.component.Button widgets;
    // End of variables declaration//GEN-END:variables
}