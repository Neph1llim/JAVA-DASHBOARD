package main;

import com.formdev.flatlaf.FlatDarkLaf;
import java.awt.*;
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
        minimize = new main.component.Button();
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
        container.add(menu, java.awt.BorderLayout.LINE_END);

        btnContainer.setBackground(new java.awt.Color(102, 102, 102));
        btnContainer.setMinimumSize(new java.awt.Dimension(100, 100));
        btnContainer.setLayout(new java.awt.GridBagLayout());

        minimize.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/resource/arrow-left.png"))); // NOI18N
        minimize.setNormalColor(new java.awt.Color(102, 102, 102));
        minimize.setOpaque(true);
        minimize.addActionListener(this::minimizeActionPerformed);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        btnContainer.add(minimize, gridBagConstraints);

        filler1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        filler1.setOpaque(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
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
        final int bigWidth = 250;
        final int smallWidth = 60;
        final int targetWidth = isMinimized ? bigWidth : smallWidth;
        final int startWidth = container.getWidth();

        javax.swing.Timer timer = new javax.swing.Timer(8, null);
        final int[] step = {0};
        final int totalSteps = 30;

        timer.addActionListener((java.awt.event.ActionEvent e) -> {
            step[0]++;

            // Calculate width
            int width = startWidth + (targetWidth - startWidth) * step[0] / totalSteps;

            // Update BOTH container and menu
            container.setPreferredSize(new Dimension(width, container.getHeight()));
            menu.animateToWidth(width, !isMinimized);

            // Single revalidate
            getContentPane().revalidate();

            // When animation finishes
            if (step[0] >= totalSteps) {
                timer.stop();

                // Final state
                container.setPreferredSize(new Dimension(targetWidth, container.getHeight()));
                menu.Minimize(!isMinimized); // This now just sets final state

                // Update icon
                minimize.setIcon(new ImageIcon(getClass().getResource(
                    isMinimized ? "/main/resource/arrow-left.png" 
                               : "/main/resource/arrow-right.png"
                )));

                isMinimized = !isMinimized;
            }
        });

        timer.start();
    }//GEN-LAST:event_minimizeActionPerformed

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code">
        try{UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (UnsupportedLookAndFeelException ex) {
        }
        //</editor-fold>
        
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
    private main.component.Button minimize;
    private main.interfaces.Notes notes;
    private main.interfaces.Settings settings;
    private main.interfaces.Widgets widgets;
    // End of variables declaration//GEN-END:variables
}
