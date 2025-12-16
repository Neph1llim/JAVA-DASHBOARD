package main.component;
import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Note extends javax.swing.JPanel {
    private String noteTitle;
    private String noteContent;
    private static final int TITLE_MAX_LENGTH = 15;
    private static final int CONTENT_MAX_LENGTH = 150;

    public Note(String title, String content) {
        this.noteTitle = title;
        this.noteContent = content;
        initComponents();
        setupCard();
        
        setPreferredSize(new Dimension(250, 250));
        setMaximumSize(new Dimension(300, 300));
    }

    private void setupCard() {
        // Limit title to 15 characters
        if (noteTitle.length() > TITLE_MAX_LENGTH) {
            titleLabel.setText(noteTitle.substring(0, TITLE_MAX_LENGTH) + "...");
        } else {
            titleLabel.setText(noteTitle);
        }
        
        // Limit content to 150 characters for display
        String displayContent;
        if (noteContent.length() > CONTENT_MAX_LENGTH) {
            displayContent = noteContent.substring(0, CONTENT_MAX_LENGTH) + "...";
        } else {
            displayContent = noteContent;
        }
        
        // Set the text with proper line wrapping
        contentPane.setText(displayContent);
        
        // Enable word wrap and set proper wrapping style
        contentPane.setEditorKit(new WrapEditorKit());
        
        // Configure JTextPane for proper text display
        contentPane.setEditable(false);
        contentPane.setFocusable(false);
        contentPane.setRequestFocusEnabled(false);
        contentPane.setCaretPosition(0);
        
        // Remove border and padding for cleaner look
        contentPane.setBorder(null);
        contentPane.setMargin(new Insets(2, 2, 2, 2));
        
        // Set wrap style word to break at word boundaries
        contentPane.putClientProperty(JTextPane.W3C_LENGTH_UNITS, Boolean.TRUE);
        
        // Also disable focus for the scroll pane
        scrollPane.setFocusable(false);
        scrollPane.setBorder(null);
        
        // Ensure text doesn't exceed bounds by setting viewport properties
        scrollPane.getViewport().setOpaque(false);
        
        // Force the content to fit within the area
        adjustContentDisplay();
    }
    
    /**
     * Custom EditorKit for word wrapping in JTextPane
     */
    private static class WrapEditorKit extends StyledEditorKit {
        @Override
        public ViewFactory getViewFactory() {
            return new WrapViewFactory();
        }
        
        private static class WrapViewFactory implements ViewFactory {
            @Override
            public View create(Element elem) {
                String kind = elem.getName();
                if (kind != null) {
                    if (kind.equals(AbstractDocument.ContentElementName)) {
                        return new WrapLabelView(elem);
                    } else if (kind.equals(AbstractDocument.ParagraphElementName)) {
                        return new javax.swing.text.ParagraphView(elem);
                    } else if (kind.equals(AbstractDocument.SectionElementName)) {
                        return new javax.swing.text.BoxView(elem, View.Y_AXIS);
                    } else if (kind.equals(StyleConstants.ComponentElementName)) {
                        return new javax.swing.text.ComponentView(elem);
                    } else if (kind.equals(StyleConstants.IconElementName)) {
                        return new javax.swing.text.IconView(elem);
                    }
                }
                return new javax.swing.text.LabelView(elem);
            }
        }
        
        private static class WrapLabelView extends javax.swing.text.LabelView {
            public WrapLabelView(Element elem) {
                super(elem);
            }
            
            @Override
            public float getMinimumSpan(int axis) {
                switch (axis) {
                    case View.X_AXIS:
                        return 0;
                    case View.Y_AXIS:
                        return super.getMinimumSpan(axis);
                    default:
                        throw new IllegalArgumentException("Invalid axis: " + axis);
                }
            }
        }
    }
    
    /**
     * Adjust content display to ensure it doesn't exceed bounds
     */
    private void adjustContentDisplay() {
        // Force the JTextPane to wrap text properly
        contentPane.setSize(panel3.getWidth() - 20, panel3.getHeight() - 20);
        
        // Ensure text fits within the visible area
        SwingUtilities.invokeLater(() -> {
            // Check if text is exceeding the visible area
            Document doc = contentPane.getDocument();
            try {
                if (doc.getLength() > 0) {
                    // Ensure the viewport shows the beginning of text
                    contentPane.setCaretPosition(0);
                    contentPane.moveCaretPosition(0);
                    
                    // If text is too long vertically, add ellipsis at the end
                    Rectangle viewRect = contentPane.getVisibleRect();
                    Rectangle textRect = contentPane.modelToView(doc.getLength() - 1);
                    
                    if (textRect != null && textRect.y + textRect.height > viewRect.height) {
                        // Text exceeds vertical bounds, add vertical ellipsis
                        String currentText = contentPane.getText();
                        if (!currentText.endsWith("...")) {
                            // Find a good place to cut the text
                            int cutPoint = Math.max(0, CONTENT_MAX_LENGTH - 10);
                            String truncated = currentText.substring(0, Math.min(cutPoint, currentText.length())) + "...";
                            contentPane.setText(truncated);
                        }
                    }
                }
            } catch (Exception e) {
                // Ignore layout exceptions
            }
        });
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
    
    public interface NoteClickListener {
        void onNoteClicked(Note note);
    }
    
    private NoteClickListener clickListener;
    
        
    // Add a getter method
    public NoteClickListener getNoteClickListener() {
        return clickListener;
    }
    
    // Add a method to remove click listener (optional but good practice)
    public void removeNoteClickListener() {
        this.clickListener = null;
        // You might also want to remove the mouse listeners here
        for (MouseListener ml : this.getMouseListeners()) {
            this.removeMouseListener(ml);
        }
    }
    
    // Add this method to set click listener
    public void setNoteClickListener(NoteClickListener listener) {
        this.clickListener = listener;
        
        // Make the entire note panel clickable
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (clickListener != null) {
                    clickListener.onNoteClicked(Note.this);
                }
            }
        });
        
        // Also make child components pass clicks to parent
        titleLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (clickListener != null) {
                    clickListener.onNoteClicked(Note.this);
                }
            }
        });
        
        contentPane.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (clickListener != null) {
                    clickListener.onNoteClicked(Note.this);
                }
            }
        });
    }
    
    /**
     * Updates the note content while preserving the click listener
     */
    public void updateContent(String newTitle, String newContent, StyledDocument styledDoc) {
        try {
            // Update title
            if (newTitle.length() > TITLE_MAX_LENGTH) {
                titleLabel.setText(newTitle.substring(0, TITLE_MAX_LENGTH) + "...");
            } else {
                titleLabel.setText(newTitle);
            }
            
            // Update stored titles
            this.noteTitle = newTitle;
            this.noteContent = newContent;
            
            // Update displayed content
            String displayContent;
            if (newContent.length() > CONTENT_MAX_LENGTH) {
                displayContent = newContent.substring(0, CONTENT_MAX_LENGTH) + "...";
            } else {
                displayContent = newContent;
            }
            
            // Clear and update text pane
            StyledDocument doc = contentPane.getStyledDocument();
            doc.remove(0, doc.getLength());
            doc.insertString(0, displayContent, null);
            
            // Apply formatting from editor
            for (int i = 0; i < Math.min(styledDoc.getLength(), doc.getLength()); i++) {
                Element charElement = styledDoc.getCharacterElement(i);
                if (charElement != null) {
                    AttributeSet attrs = charElement.getAttributes();
                    if (attrs != null && attrs.getAttributeCount() > 0) {
                        doc.setCharacterAttributes(i, 1, attrs, true);
                    }
                }
            }
            
            // Refresh the note
            revalidate();
            repaint();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void setNoteTitle(String title) {
        this.noteTitle = title;
    }
    
    public void setNoteContent(String content) {
        this.noteContent = content;
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

        scrollPane.setBorder(null);
        scrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        scrollPane.setViewportView(contentPane);

        contentPane.setBackground(new java.awt.Color(204, 204, 204));
        contentPane.setBorder(null);
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
