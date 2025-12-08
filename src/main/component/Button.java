/* ano bago?
arc = roundness
color = background color
hovercolor = color pag naka hover sa button
pressedcolor = pag naka pressed 
bordercolor = obvoius na yan
borderthickness
textcolor
*/

package main.component; // change to package and location

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Button extends JButton {

    // default properties
    private int arc = 25;
    private Color normalColor = new Color(67, 69, 74);
    private Color hoverColor = new Color(57, 59, 64);
    private Color pressedColor = new Color(53, 54, 56);
    private Color borderColor = new Color(150, 150, 150);
    private int borderThickness = 0;

    private Color currentColor = normalColor;

    // Default text color (NEW)
    private Color textColor = Color.BLACK;

    public Button() {
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
        setContentAreaFilled(false);
        setBorderPainted(false);
        setFocusPainted(false);

        // Apply default text color
        super.setForeground(textColor);

        // Hover + press behavior
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                currentColor = hoverColor;
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                currentColor = normalColor;
                repaint();
            }

            @Override
            public void mousePressed(MouseEvent e) {
                currentColor = pressedColor;
                repaint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                currentColor = hoverColor;
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

        // Border
        if (borderThickness > 0) {
            g2.setColor(borderColor);
            g2.setStroke(new BasicStroke(borderThickness));
            g2.drawRoundRect(
                borderThickness / 2,
                borderThickness / 2,
                getWidth() - borderThickness,
                getHeight() - borderThickness,
                arc, arc
            );
        }

        g2.dispose();
        super.paintComponent(g);
    }

    // ------------ Bean Properties ------------

    public int getArc() { return arc; }
    public void setArc(int arc) { this.arc = arc; repaint(); }

    public Color getNormalColor() { return normalColor; }
    public void setNormalColor(Color c) { this.normalColor = c; repaint(); }

    public Color getHoverColor() { return hoverColor; }
    public void setHoverColor(Color c) { this.hoverColor = c; repaint(); }

    public Color getPressedColor() { return pressedColor; }
    public void setPressedColor(Color c) { this.pressedColor = c; repaint(); }

    public Color getBorderColor() { return borderColor; }
    public void setBorderColor(Color c) { this.borderColor = c; repaint(); }

    public int getBorderThickness() { return borderThickness; }
    public void setBorderThickness(int t) { this.borderThickness = t; repaint(); }

    // NEW — text color property (NetBeans editable)
    @Override
    public void setForeground(Color fg) {
        this.textColor = fg;     // store the value
        super.setForeground(fg); // apply to JButton
        repaint();
    }

    public Color getForeground() {
        return this.textColor;
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
