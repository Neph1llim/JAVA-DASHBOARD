/*
TODO:
connect this to themes

arc = roundness
color = background color
hovercolor = color pag naka hover sa button
pressedcolor = pag naka pressed 
textcolor
*/

package main.component; // change to package and location

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Button extends JButton {

    // default properties
    private int arc = 25;

    // theme colors 
    private Color normalColor = new Color(67, 69, 74);
    private Color hoverColor  = new Color(57, 59, 64);
    private Color pressedColor= new Color(53, 54, 56);
    private Color highlightColor = new Color(188, 188, 186);
    private Color textColor = Color.WHITE;
    
    // state
    private Color currentColor = normalColor;
    private boolean highlighted = false;

    public Button() {
        super();
        setup();
    }

    public Button(String text) {
        super(text);
        setup();
    }

    public Button(String text, int arc) {
        super(text);
        this.arc = arc;
        setup();
    }
    
    private void setup() {
        initComponents();
        setOpaque(false);
        setContentAreaFilled(false);
        setBorderPainted(false);
        setFocusPainted(false);

        // Apply default text color
        super.setForeground(textColor);

        // Hover + press behavior
        addMouseListener(new MouseAdapter() {
           @Override
            public void mouseEntered(MouseEvent e) {
                if (!highlighted) currentColor = hoverColor;
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (!highlighted) currentColor = normalColor;
                repaint();
            }

            @Override
            public void mousePressed(MouseEvent e) {
                if (!highlighted) currentColor = pressedColor;
                repaint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (!highlighted) currentColor = hoverColor;
                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Background
        g2.setColor(currentColor);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), arc, arc);


        g2.dispose();
        super.paintComponent(g);
    }

    // ------------ Bean Properties ------------

    public int getArc() { return arc; }
    public void setArc(int arc) { this.arc = arc; repaint(); }

    // NEW — text color property (NetBeans editable)
    @Override
    public void setForeground(Color fg) {
        this.textColor = fg;     // store the value
        super.setForeground(fg); // apply to JButton
        repaint();
    }

    // ---------- Highlight API ----------
    public void setHighlighted(boolean value) {
        highlighted = value;
        currentColor = value ? highlightColor : normalColor; // change bg color
        super.setForeground(value ? Color.BLACK : Color.WHITE); // change text color
        repaint();
    }

    public boolean isHighlighted() {
        return highlighted;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBackground(new java.awt.Color(102, 102, 102));
        setForeground(new java.awt.Color(153, 153, 153));
        setOpaque(true);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
