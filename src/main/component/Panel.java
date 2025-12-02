/* ano bago?
arc = roundness
color = background color
enablehover = true mo pag need hover sa panel
hovercolor = color ng hover
bordercolor = obvoius na yan
borderthickness
textcolor
*/
package main.component;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Panel extends JPanel {

    // Default properties
    private int arc = 25;
    private Color backgroundColor = new Color(240, 240, 240);
    private Color borderColor = new Color(200, 200, 200);
    private int borderThickness = 0;

    // Hover / pressed
    private boolean enableHover = false;
    private Color hoverColor = backgroundColor;
    private Color currentColor = backgroundColor;

    public Panel() {
        initPanel();
    }

    public Panel(int arc) {
        this.arc = arc;
        initPanel();
    }

    public Panel(int arc, Color bg) {
        this.arc = arc;
        this.backgroundColor = bg;
        this.currentColor = bg;
        initPanel();
    }

    private void initPanel() {
        setOpaque(false);
        initComponents();

        // Add hover listener if enabled
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if (enableHover) {
                    currentColor = hoverColor;
                    repaint();
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                currentColor = backgroundColor;
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

    // ---------- Bean Properties ----------

    public int getArc() { return arc; }
    public void setArc(int arc) { this.arc = arc; repaint(); }

    public Color getPanelBackground() { return backgroundColor; }
    public void setPanelBackground(Color bg) { this.backgroundColor = bg; this.currentColor = bg; repaint(); }

    public Color getBorderColor() { return borderColor; }
    public void setBorderColor(Color bc) { this.borderColor = bc; repaint(); }

    public int getBorderThickness() { return borderThickness; }
    public void setBorderThickness(int thickness) { this.borderThickness = thickness; repaint(); }

    public boolean isEnableHover() { return enableHover; }
    public void setEnableHover(boolean v) { this.enableHover = v; }

    public Color getHoverColor() { return hoverColor; }
    public void setHoverColor(Color c) { this.hoverColor = c; }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
