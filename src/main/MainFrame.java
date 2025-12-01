package main;

import com.formdev.flatlaf.FlatDarkLaf;
import java.awt.CardLayout;
import java.awt.Dimension;
import javax.swing.*;


public class MainFrame extends javax.swing.JFrame {
    
    boolean isMinimized = false;
    private final CardLayout card;
    
    public MainFrame() {
        initComponents();
        
        setExtendedState(JFrame.MAXIMIZED_BOTH); // full screen
        setLocationRelativeTo(null); // Centers on screen
        
        
        // default Interface set to home 
        card = (CardLayout) Interface.getLayout();
        card.show(Interface,"home");
        
        minimize.putClientProperty("JButton.buttonType", "roundRect");
        minimize.putClientProperty("JComponent.roundRect.arc", 100);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        Interface = new javax.swing.JPanel();
        widgets = new main.interfaces.Widgets();
        notes = new main.interfaces.Notes();
        settings = new main.interfaces.Settings();
        files = new main.interfaces.Files();
        home = new main.interfaces.Home();
        container = new javax.swing.JPanel();
        menu = new main.interfaces.Menu();
        btnContainer = new javax.swing.JPanel();
        minimize = new javax.swing.JButton();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(20, 0), new java.awt.Dimension(100, 32), new java.awt.Dimension(20, 32767));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Dashboard");
        setAlwaysOnTop(true);
        setBackground(new java.awt.Color(0, 0, 0));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setMinimumSize(new java.awt.Dimension(480, 270));
        setName("MainFrame"); // NOI18N

        Interface.setBackground(new java.awt.Color(102, 102, 102));
        Interface.setMinimumSize(new java.awt.Dimension(500, 270));
        Interface.setPreferredSize(new java.awt.Dimension(1200, 810));
        Interface.setLayout(new java.awt.CardLayout());
        Interface.add(widgets, "widgets");
        Interface.add(notes, "notes");
        Interface.add(settings, "settings");
        Interface.add(files, "files");
        Interface.add(home, "home");

        container.setMinimumSize(new java.awt.Dimension(0, 0));
        container.setPreferredSize(new java.awt.Dimension(250, 810));
        container.setLayout(new java.awt.BorderLayout());
        container.add(menu, java.awt.BorderLayout.CENTER);

        btnContainer.setBackground(new java.awt.Color(102, 102, 102));
        btnContainer.setMinimumSize(new java.awt.Dimension(100, 100));
        btnContainer.setLayout(new java.awt.GridBagLayout());

        minimize.setBackground(new java.awt.Color(51, 51, 51));
        minimize.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/resource/arrow-left.png"))); // NOI18N
        minimize.setToolTipText("");
        minimize.setBorderPainted(false);
        minimize.setFocusPainted(false);
        minimize.setPreferredSize(new java.awt.Dimension(35, 35));
        minimize.addActionListener(this::minimizeActionPerformed);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 15, 5);
        btnContainer.add(minimize, gridBagConstraints);

        filler1.setOpaque(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.weightx = 1.0;
        btnContainer.add(filler1, gridBagConstraints);

        container.add(btnContainer, java.awt.BorderLayout.PAGE_START);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(container, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(Interface, javax.swing.GroupLayout.DEFAULT_SIZE, 1190, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Interface, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(container, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        Interface.getAccessibleContext().setAccessibleName("");

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void minimizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_minimizeActionPerformed
        int expandedWidth = 250;
        int collapsedWidth = 60;
        int minWidth = 0;
        
        int width = isMinimized ? expandedWidth : collapsedWidth;
        int min = isMinimized ? minWidth: width;
        // Only change width, keep height the same
        container.setPreferredSize(new Dimension(width, getPreferredSize().height));
        container.setMinimumSize(new Dimension(min, 0));
        
        // Change minimize icon
        String iconPath = isMinimized
                ? "/main/resource/arrow-left.png"
                : "/main/resource/arrow-right.png";
        minimize.setIcon(new javax.swing.ImageIcon(getClass().getResource(iconPath)));

        // Flip flag
        menu.Minimize(isMinimized); // update menu panel
        isMinimized = !isMinimized;
        
        // Refresh layout
        container.revalidate();
        container.repaint();
    }//GEN-LAST:event_minimizeActionPerformed

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code">
        try{UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (UnsupportedLookAndFeelException ex) {
        }
        //</editor-fold>
        UIManager.put("Button.arc", 20); // rounded buttons
        UIManager.put("Component.arc", 20); // rounded components

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new MainFrame().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JPanel Interface;
    private javax.swing.JPanel btnContainer;
    private javax.swing.JPanel container;
    private main.interfaces.Files files;
    private javax.swing.Box.Filler filler1;
    private main.interfaces.Home home;
    public static main.interfaces.Menu menu;
    private javax.swing.JButton minimize;
    private main.interfaces.Notes notes;
    private main.interfaces.Settings settings;
    private main.interfaces.Widgets widgets;
    // End of variables declaration//GEN-END:variables
}
