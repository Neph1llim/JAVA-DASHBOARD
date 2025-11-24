package main;

import com.formdev.flatlaf.FlatDarkLaf;
import java.awt.CardLayout;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;


public class MainFrame extends javax.swing.JFrame {
    
    private final CardLayout card;
    
    public MainFrame() {
        initComponents();
        
        // default Interface set to home 
        card = (CardLayout) Interface.getLayout();
        card.show(Interface,"home");
        
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Interface = new javax.swing.JPanel();
        files = new main.Files();
        home = new main.Home();
        settings = new main.Settings();
        notes = new main.Notes();
        widgets = new main.Widgets();
        menu = new main.Menu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Dashboard");
        setAlwaysOnTop(true);
        setBackground(new java.awt.Color(0, 0, 0));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setName("MainFrame"); // NOI18N

        Interface.setBackground(new java.awt.Color(102, 102, 102));
        Interface.setLayout(new java.awt.CardLayout());
        Interface.add(files, "files");
        Interface.add(home, "home");
        Interface.add(settings, "settings");
        Interface.add(notes, "notes");
        Interface.add(widgets, "widgets");

        getContentPane().add(Interface, java.awt.BorderLayout.CENTER);
        Interface.getAccessibleContext().setAccessibleName("");

        menu.setPreferredSize(new java.awt.Dimension(200, 568));
        getContentPane().add(menu, java.awt.BorderLayout.WEST);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

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
    private main.Files files;
    private main.Home home;
    public static main.Menu menu;
    private main.Notes notes;
    private main.Settings settings;
    private main.Widgets widgets;
    // End of variables declaration//GEN-END:variables
}
