package main;

/* Import files location */
import com.formdev.flatlaf.FlatDarkLaf;
import java.awt.*;
import javax.swing.*;


public class MainFrame extends javax.swing.JFrame {
    /* Properties */
    boolean isMinimized = false;
    private final CardLayout card;
    
    /* Constructors for OOP */
    public MainFrame() {
        initComponents();
        
        setExtendedState(JFrame.MAXIMIZED_BOTH); // full screen
        
        // default Interface set to home 
        card = (CardLayout) Interface.getLayout();
        card.show(Interface,"home");
    }

    /* Built-in codes and functions */    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        homePage = new javax.swing.JPanel();
        container = new javax.swing.JPanel();
        menu = new main.interfaces.Menu();
        btnContainer = new javax.swing.JPanel();
        minimize = new main.component.Button();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(20, 0), new java.awt.Dimension(100, 32), new java.awt.Dimension(20, 32767));
        Interface = new javax.swing.JPanel();
        home = new main.interfaces.Home();
        widgets = new main.interfaces.Widgets();
        notes = new main.interfaces.Notes();
        settings = new main.interfaces.Settings();
        files = new main.interfaces.Files();
        login = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Dashboard");
        setAlwaysOnTop(true);
        setBackground(new java.awt.Color(0, 0, 0));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setMinimumSize(new java.awt.Dimension(480, 270));
        setName("MainFrame"); // NOI18N
        getContentPane().setLayout(new java.awt.CardLayout());

        homePage.setLayout(new java.awt.BorderLayout());

        container.setMinimumSize(new java.awt.Dimension(0, 0));
        container.setPreferredSize(new java.awt.Dimension(250, 810));
        container.setLayout(new java.awt.BorderLayout());
        container.add(menu, java.awt.BorderLayout.CENTER);

        btnContainer.setBackground(new java.awt.Color(27, 27, 28));
        btnContainer.setMinimumSize(new java.awt.Dimension(100, 100));
        btnContainer.setLayout(new java.awt.GridBagLayout());

        minimize.setBackground(new java.awt.Color(27, 27, 28));
        minimize.setForeground(new java.awt.Color(27, 27, 28));
        minimize.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/resource/arrow-left.png"))); // NOI18N
        minimize.setArc(100);
        minimize.setNormalColor(new java.awt.Color(27, 27, 28));
        minimize.setOpaque(true);
        minimize.addActionListener(this::minimizeActionPerformed);
        btnContainer.add(minimize, new java.awt.GridBagConstraints());

        filler1.setBackground(new java.awt.Color(27, 27, 28));
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

        homePage.add(container, java.awt.BorderLayout.LINE_START);

        Interface.setBackground(new java.awt.Color(102, 102, 102));
        Interface.setMinimumSize(new java.awt.Dimension(500, 270));
        Interface.setPreferredSize(new java.awt.Dimension(1200, 810));
        Interface.setLayout(new java.awt.CardLayout());
        Interface.add(home, "home");

        widgets.setBackground(new java.awt.Color(21, 21, 23));
        widgets.setForeground(new java.awt.Color(27, 27, 28));
        Interface.add(widgets, "widgets");

        notes.setBackground(new java.awt.Color(255, 255, 255));
        Interface.add(notes, "notes");
        Interface.add(settings, "settings");
        Interface.add(files, "files");

        homePage.add(Interface, java.awt.BorderLayout.CENTER);
        Interface.getAccessibleContext().setAccessibleName("");

        getContentPane().add(homePage, "card2");

        javax.swing.GroupLayout loginLayout = new javax.swing.GroupLayout(login);
        login.setLayout(loginLayout);
        loginLayout.setHorizontalGroup(
            loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1456, Short.MAX_VALUE)
        );
        loginLayout.setVerticalGroup(
            loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 810, Short.MAX_VALUE)
        );

        getContentPane().add(login, "card3");

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void minimizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_minimizeActionPerformed
        final int maxWidth = 250;
        final int minWidth = 60;
        final int targetWidth = isMinimized ? maxWidth : minWidth; // ternary operator
        final int currentWidth = container.getWidth();

        javax.swing.Timer timer = new javax.swing.Timer(8, null);
        final int[] step = {0};
        final int totalSteps = 30; // adjust for collapse speed

        timer.addActionListener((java.awt.event.ActionEvent e) -> {
            step[0]++;

            // Calculate width
            int width = currentWidth + (targetWidth - currentWidth) * step[0] / totalSteps;

            // Update BOTH container and menu
            container.setPreferredSize(new Dimension(width, container.getHeight()));
            menu.animateToWidth(width, !isMinimized);

            // Single revalidate
            container.revalidate();
            container.repaint();

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

                isMinimized = !isMinimized; // inverts the state
            }
        });

        timer.start();
    }//GEN-LAST:event_minimizeActionPerformed

    /* Main Class Code */ 
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
    private javax.swing.JPanel homePage;
    private javax.swing.JPanel login;
    public static main.interfaces.Menu menu;
    private main.component.Button minimize;
    private main.interfaces.Notes notes;
    private main.interfaces.Settings settings;
    private main.interfaces.Widgets widgets;
    // End of variables declaration//GEN-END:variables
}
