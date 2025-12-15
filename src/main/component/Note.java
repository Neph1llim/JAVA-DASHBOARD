package main.component;
import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;

public class Note extends javax.swing.JPanel {
    private String noteTitle;
    private String noteContent;

    public Note(String title, String content) {
        this.noteTitle = title;
        this.noteContent = content;
        initComponents();
        setupCard();
        
        setPreferredSize(new Dimension(250, 250)); // Keep preferred, but not minimum/maximum
        setMaximumSize(new Dimension(300, 300)); // Set a maximum but not too restrictive
    }

    private void setupCard() {
        titleLabel.setText(noteTitle);
        
        // For JTextPane, we can use HTML for styling
        // Or set plain text and apply styles later
        if (noteContent.length() > 100) {
            contentPane.setText(noteContent.substring(0, 20) + "...");
        } else {
            contentPane.setText(noteContent);
        }
        
        // Make it non-editable but selectable
        contentPane.setEditable(false);
        contentPane.setCaretPosition(0); // Scroll to top
    }
    
    /**
     * Apply styles to the content text
     */
    public void applyStylesToContent(boolean bold, boolean italic, boolean underline, Color color) {
        StyledDocument doc = contentPane.getStyledDocument();
        SimpleAttributeSet attrs = new SimpleAttributeSet();
        
        if (bold) {
            StyleConstants.setBold(attrs, true);
        }
        if (italic) {
            StyleConstants.setItalic(attrs, true);
        }
        if (underline) {
            StyleConstants.setUnderline(attrs, true);
        }
        if (color != null) {
            StyleConstants.setForeground(attrs, color);
        }
        
        // Apply to all text
        doc.setCharacterAttributes(0, doc.getLength(), attrs, false);
    }
    
    public String getNoteTitle() {
        return noteTitle;
    }

    public String getNoteContent() {
        return noteContent;
    }
    
    public StyledDocument getStyledDocument() {
        return contentPane.getStyledDocument();
    }
    
    public JTextPane getContentPane() {
        return contentPane;
    }
    
    public void copyFormattingFrom(StyledDocument sourceDoc) {
    try {
        StyledDocument targetDoc = contentPane.getStyledDocument();
        
        // Clear existing formatting
        SimpleAttributeSet clearAttrs = new SimpleAttributeSet();
        targetDoc.setCharacterAttributes(0, targetDoc.getLength(), clearAttrs, true);
        
        // Copy character attributes
        for (int i = 0; i < Math.min(sourceDoc.getLength(), targetDoc.getLength()); i++) {
            Element charElement = sourceDoc.getCharacterElement(i);
            if (charElement != null) {
                AttributeSet attrs = charElement.getAttributes();
                if (attrs != null && attrs.getAttributeCount() > 0) {
                    targetDoc.setCharacterAttributes(i, 1, attrs, true);
                }
            }
        }
        
    } catch (Exception e) {
        e.printStackTrace();
    }
}   
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel1 = new main.component.Panel();
        panel2 = new main.component.Panel();
        titleLabel = new javax.swing.JLabel();
        panel3 = new main.component.Panel();
        scrollPane = new javax.swing.JScrollPane();
        contentPane = new javax.swing.JTextPane();

        setMinimumSize(new java.awt.Dimension(250, 250));
        setPreferredSize(new java.awt.Dimension(250, 250));

        panel1.setMinimumSize(new java.awt.Dimension(250, 250));
        panel1.setPreferredSize(new java.awt.Dimension(250, 250));

        panel2.setBackground(new java.awt.Color(102, 102, 102));
        panel2.setBorderColor(new java.awt.Color(0, 0, 0));
        panel2.setPanelBackground(new java.awt.Color(204, 204, 204));

        titleLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        titleLabel.setForeground(new java.awt.Color(0, 0, 0));
        titleLabel.setText("Title");

        javax.swing.GroupLayout panel2Layout = new javax.swing.GroupLayout(panel2);
        panel2.setLayout(panel2Layout);
        panel2Layout.setHorizontalGroup(
            panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(titleLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 286, Short.MAX_VALUE)
                .addContainerGap())
        );
        panel2Layout.setVerticalGroup(
            panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(titleLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                .addContainerGap())
        );

        panel3.setForeground(new java.awt.Color(0, 0, 0));
        panel3.setPanelBackground(new java.awt.Color(204, 204, 204));

        contentPane.setBackground(new java.awt.Color(204, 204, 204));
        scrollPane.setViewportView(contentPane);

        javax.swing.GroupLayout panel3Layout = new javax.swing.GroupLayout(panel3);
        panel3.setLayout(panel3Layout);
        panel3Layout.setHorizontalGroup(
            panel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE)
                .addContainerGap())
        );
        panel3Layout.setVerticalGroup(
            panel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout panel1Layout = new javax.swing.GroupLayout(panel1);
        panel1.setLayout(panel1Layout);
        panel1Layout.setHorizontalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panel1Layout.setVerticalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextPane contentPane;
    private main.component.Panel panel1;
    private main.component.Panel panel2;
    private main.component.Panel panel3;
    private javax.swing.JScrollPane scrollPane;
    private javax.swing.JLabel titleLabel;
    // End of variables declaration//GEN-END:variables
}
