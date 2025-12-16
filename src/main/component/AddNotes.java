package main.component;

/* Import statements */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.*;
import main.MainFrame;
import main.interfaces.Notes;

public class AddNotes extends javax.swing.JPanel {
   /* Properties */
    String titlePlaceholder = "Untitled note";
    String textPlaceholder = "Start typing your notes here...";
    Color initialText = new Color(33, 33, 34);
    Color normalColor = Button.normalColor;
    Color highlightColor = Color.WHITE;
    
    // Track formatting states
    private boolean boldActive = false;
    private boolean italicActive = false;
    private boolean underlineActive = false;
    private boolean textColorActive = false;
    private final Color normalTextColor = Color.BLACK;
    private Color newTextColor = normalTextColor;
    
    // Reference to Notes panel
    private Notes notesPanel;
    private boolean isEditing = false;
    private Note noteBeingEdited = null;
    
    /* Constructors */
    public AddNotes(Notes notesPanel) {
        this.notesPanel = notesPanel;
        this.isEditing = false;
        initComponents();
        setupTextArea();
        setupPlaceholder();
        resetButtons();
    }

    /* Methods */
    private void showPanel(String name){
        CardLayout card = (CardLayout) MainFrame.Interface.getLayout();
        card.show(MainFrame.Interface, name);
    }
    
    public AddNotes(Notes notesPanel, Note noteToEdit) {
        this.notesPanel = notesPanel;
        this.noteBeingEdited = noteToEdit;
        this.isEditing = true;
        initComponents();
        setupTextArea();
        loadNoteForEditing();
        resetButtons();
        
        // Update button text for editing mode
        save.setLabel("Update");
        cancel.setLabel("Cancel Edit");
    }
    
    private void loadNoteForEditing() {
        if (noteBeingEdited != null) {
            // Load title
            String noteTitle = noteBeingEdited.getNoteTitle();
            if (noteTitle != null && !noteTitle.isEmpty()) {
                title.setText(noteTitle);
                title.setForeground(normalTextColor);
            }
            
            // Load content
            String noteContent = noteBeingEdited.getNoteContent();
            if (noteContent != null && !noteContent.isEmpty()) {
                textArea.setText(noteContent);
                textArea.setForeground(normalTextColor);
                
                // Try to load formatting
                try {
                    StyledDocument sourceDoc = noteBeingEdited.getStyledDocument();
                    StyledDocument targetDoc = (StyledDocument) textArea.getDocument();
                    
                    // Clear and insert text
                    targetDoc.remove(0, targetDoc.getLength());
                    targetDoc.insertString(0, noteContent, null);
                    
                    // Copy formatting
                    for (int i = 0; i < sourceDoc.getLength(); i++) {
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
                    // If formatting fails, just set plain text
                    textArea.setText(noteContent);
                }
            }
        }
    }
    
    private void setupTextArea() {
        // Use a simpler approach - set maximum size to force wrapping
        textArea.setMaximumSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));

        // Configure scroll pane
        jScrollPane3.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane3.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        // Remove border
        jScrollPane3.setBorder(null);

        // Set margins
        textArea.setMargin(new Insets(5, 5, 5, 5));

        // Make text area non-opaque
        textArea.setOpaque(false);

        // Force word wrap by setting the text area's preferred size
        textArea.setPreferredSize(new Dimension(100, 100)); // Small initial size

        // Add listener to adjust on resize
        jScrollPane3.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                // When scroll pane resizes, adjust text area width
                int width = jScrollPane3.getViewport().getWidth() - 20;
                if (width > 0) {
                    textArea.setSize(width, textArea.getHeight());
                }
            }
        });

    }
     
    private void setupPlaceholder() {
        // Only setup placeholder if not editing
        if (!isEditing) {
            textArea.setText(textPlaceholder);
            textArea.setForeground(initialText);
            
            title.setText(titlePlaceholder);
            title.setForeground(initialText);
        }
        
        // Add focus listeners
        textArea.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (!isEditing && textArea.getForeground().equals(initialText)) {
                    textArea.setText("");
                    textArea.setForeground(normalTextColor);
                }
            }
            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                if (!isEditing && textArea.getText().trim().isEmpty()) {
                    textArea.setText(textPlaceholder);
                    textArea.setForeground(initialText);
                }
            }
        });
        
        title.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (!isEditing && title.getForeground().equals(initialText)) {
                    title.setText("");
                    title.setForeground(normalTextColor);
                }
            }
            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                if (!isEditing && title.getText().trim().isEmpty()) {
                    title.setText(titlePlaceholder);
                    title.setForeground(initialText);
                }
            }
        });
    }
    
     private void saveNote() {
        // Get title and content
        String noteTitle = title.getText().trim();
        StyledDocument styledDoc = (StyledDocument) textArea.getDocument();

        // Use placeholder if empty
        if (noteTitle.isEmpty() || (!isEditing && noteTitle.equals(titlePlaceholder))) {
            noteTitle = "Untitled Note";
        }

        // Get text content
        String noteContent = "";
        try {
            noteContent = styledDoc.getText(0, styledDoc.getLength());
            if (noteContent.trim().isEmpty() || (!isEditing && noteContent.equals(textPlaceholder))) {
                noteContent = "Empty Note";
            }
        } catch (BadLocationException e) {
            e.printStackTrace();
            noteContent = "Error loading content";
        }

        if (isEditing) {
            // Update existing note
            notesPanel.updateNoteCard(noteTitle, noteContent, styledDoc);
        } else {
            // Create new note
            Note noteCard = new Note(noteTitle, noteContent);

            // Apply formatting to the new note
            try {
                StyledDocument noteDoc = noteCard.getStyledDocument();
                noteDoc.remove(0, noteDoc.getLength());
                noteDoc.insertString(0, noteContent, null);

                // Copy formatting
                for (int i = 0; i < styledDoc.getLength(); i++) {
                    Element charElement = styledDoc.getCharacterElement(i);
                    if (charElement != null) {
                        AttributeSet attrs = charElement.getAttributes();
                        if (attrs != null && attrs.getAttributeCount() > 0) {
                            noteDoc.setCharacterAttributes(i, 1, attrs, true);
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            // Add to notes panel
            notesPanel.addNoteCard(noteCard);
        }

        // Return to notes view
        showPanel("notes");
        resetButtons();
        
        // Reset for next use
        isEditing = false;
        noteBeingEdited = null;
    }
     
     private void cancelEdit() {
        if (isEditing) {
            // Cancel editing without saving changes
            notesPanel.cancelEdit();
        }
        
        // Return to notes view
        showPanel("notes");
        resetButtons();
        
        // Reset for next use
        isEditing = false;
        noteBeingEdited = null;
    }
    
    private Button[] Buttons() {
        return new Button[] { back, bold, italize, underline, font, fontColor, cancel, save };
    }
    
    private void resetButtons() {
        // Set all formatting buttons to normal state
        for(Button btn: Buttons()){
            btn.setSelected(false);
            btn.setBackground(normalColor);
        }
        boldActive = false;
        italicActive = false;
        underlineActive = false;
        textColorActive = false;
        fontColor.setForeground(normalTextColor);
        applyColorToSelection(normalTextColor);
    }
    
    private void updateButtonAppearance(Button button, boolean isActive) {
        if (isActive) {
            button.setHighlightColor(highlightColor);
            button.setHighlighted(isActive);
        } else {
            button.setHighlightColor(normalColor);
            button.setHighlighted(isActive);
        }
    }
    
    private void applyBold() {
        boldActive = !boldActive;
        bold.setSelected(boldActive);
        updateButtonAppearance(bold, boldActive);
        
        applyStyleToText(StyleConstants.Bold, boldActive);
    }
    
    private void applyItalic() {
        italicActive = !italicActive;
        italize.setSelected(italicActive);
        updateButtonAppearance(italize, italicActive);
        
        applyStyleToText(StyleConstants.Italic, italicActive);
    }
    
    private void applyUnderline() {
        underlineActive = !underlineActive;
        underline.setSelected(underlineActive);
        updateButtonAppearance(underline, underlineActive);
        
        applyStyleToText(StyleConstants.Underline, underlineActive);
    }
    
    private void applyStyleToText(Object styleConstant, boolean applyStyle) {
        StyledDocument doc = (StyledDocument) textArea.getDocument();
        SimpleAttributeSet attrs = new SimpleAttributeSet();

        // Set the style attribute
        if (styleConstant.equals(StyleConstants.Bold)) {
            StyleConstants.setBold(attrs, applyStyle);
        } else if (styleConstant.equals(StyleConstants.Italic)) {
            StyleConstants.setItalic(attrs, applyStyle);
        } else if (styleConstant.equals(StyleConstants.Underline)) {
            StyleConstants.setUnderline(attrs, applyStyle);
        }

        // Get selected text range
        int start = textArea.getSelectionStart();
        int end = textArea.getSelectionEnd();

        if (start == end) {
            // Apply style to the next character to be typed
            ((JTextPane)textArea).setCharacterAttributes(attrs, false);
        } else {
            // Apply to selected text
            doc.setCharacterAttributes(start, end - start, attrs, false);
        }

        textArea.requestFocus();
    }
    
    private Font fontChooser() {
        String[] fonts = GraphicsEnvironment.getLocalGraphicsEnvironment()
            .getAvailableFontFamilyNames();
        
        JComboBox<String> fontCombo = new JComboBox<>(fonts);
        
        // Preselect current font if available
        Font currentFont = textArea.getFont();
        String currentFontName = currentFont.getFamily();
        fontCombo.setSelectedItem(currentFontName);
        
        int result = JOptionPane.showConfirmDialog(
            this,
            fontCombo,
            "Select Font Family",
            JOptionPane.OK_CANCEL_OPTION,
            JOptionPane.PLAIN_MESSAGE
        );
        
        if (result == JOptionPane.OK_OPTION) {
            String selectedFontName = (String) fontCombo.getSelectedItem();
            if (selectedFontName != null) {
                // Create new font with selected family, but keep current size and style
                return new Font(selectedFontName, currentFont.getStyle(), currentFont.getSize());
            }
        }
        
        return null;
    }
    
    private void applyColorToSelection(Color color) {
        if (color == null) return;

        newTextColor = color;

        StyledDocument doc = (StyledDocument) textArea.getDocument();
        SimpleAttributeSet attrs = new SimpleAttributeSet();
        StyleConstants.setForeground(attrs, color);

        // Get selected text range
        int start = textArea.getSelectionStart();
        int end = textArea.getSelectionEnd();

        if (start == end) {
            // No text selected - set color for next typed text
            textArea.setCharacterAttributes(attrs, false);
        } else {
            // Apply color to selected text
            doc.setCharacterAttributes(start, end - start, attrs, false);
        }
        textArea.requestFocus();
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        panel1 = new main.component.Panel();
        back = new main.component.Button();
        jPanel1 = new javax.swing.JPanel();
        panel2 = new main.component.Panel();
        title = new javax.swing.JTextField();
        panel3 = new main.component.Panel();
        jScrollPane3 = new javax.swing.JScrollPane();
        textArea = new javax.swing.JTextPane();
        panel4 = new main.component.Panel();
        cancel = new main.component.Button();
        save = new main.component.Button();
        bold = new main.component.Button();
        italize = new main.component.Button();
        underline = new main.component.Button();
        font = new main.component.Button();
        fontColor = new main.component.Button();

        panel1.setArc(0);
        panel1.setPanelBackground(new java.awt.Color(21, 21, 23));
        panel1.setLayout(new java.awt.GridBagLayout());

        back.setBackground(new java.awt.Color(21, 21, 23));
        back.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/resource/icons/back.png"))); // NOI18N
        back.setMinimumSize(new java.awt.Dimension(40, 40));
        back.setPreferredSize(new java.awt.Dimension(40, 40));
        back.addActionListener(this::backActionPerformed);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(18, 25, 0, 0);
        panel1.add(back, gridBagConstraints);

        jPanel1.setBackground(new java.awt.Color(21, 21, 23));
        jPanel1.setLayout(new java.awt.GridBagLayout());

        panel2.setPanelBackground(new java.awt.Color(51, 51, 51));

        title.setBackground(new java.awt.Color(51, 51, 51));
        title.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        title.setText("Title");
        title.setBorder(null);
        title.setDisabledTextColor(new java.awt.Color(51, 51, 51));
        title.setHighlighter(null);
        title.addActionListener(this::titleActionPerformed);

        javax.swing.GroupLayout panel2Layout = new javax.swing.GroupLayout(panel2);
        panel2.setLayout(panel2Layout);
        panel2Layout.setHorizontalGroup(
            panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(title, javax.swing.GroupLayout.PREFERRED_SIZE, 847, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panel2Layout.setVerticalGroup(
            panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(title)
                .addContainerGap())
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 16;
        gridBagConstraints.ipady = 12;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(6, 6, 0, 6);
        jPanel1.add(panel2, gridBagConstraints);

        panel3.setPanelBackground(new java.awt.Color(51, 51, 51));
        panel3.setLayout(new java.awt.GridBagLayout());

        jScrollPane3.setBorder(null);
        jScrollPane3.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane3.setViewportView(textArea);

        textArea.setBackground(new java.awt.Color(51, 51, 51));
        textArea.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        textArea.setText("TEXT HERE");
        textArea.setDisabledTextColor(new java.awt.Color(51, 51, 51));
        jScrollPane3.setViewportView(textArea);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        panel3.add(jScrollPane3, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 10;
        gridBagConstraints.ipady = 8;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 6, 0, 6);
        jPanel1.add(panel3, gridBagConstraints);

        panel4.setPanelBackground(new java.awt.Color(51, 51, 51));

        cancel.setFont(new java.awt.Font("Segoe UI Variable", 1, 14)); // NOI18N
        cancel.setLabel("Cancel");
        cancel.addActionListener(this::cancelActionPerformed);

        save.setFont(new java.awt.Font("Segoe UI Variable", 1, 14)); // NOI18N
        save.setLabel("Save");
        save.addActionListener(this::saveActionPerformed);

        bold.setBackground(new java.awt.Color(67, 69, 74));
        bold.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/resource/icons/fontBold.png"))); // NOI18N
        bold.setMargin(new java.awt.Insets(3, 0, 0, 0));
        bold.addActionListener(this::boldActionPerformed);

        italize.setBackground(new java.awt.Color(67, 69, 74));
        italize.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/resource/icons/fontItalic.png"))); // NOI18N
        italize.setMargin(new java.awt.Insets(3, 0, 0, 0));
        italize.addActionListener(this::italizeActionPerformed);

        underline.setBackground(new java.awt.Color(67, 69, 74));
        underline.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/resource/icons/fontUnderline.png"))); // NOI18N
        underline.setMargin(new java.awt.Insets(3, 0, 0, 0));
        underline.addActionListener(this::underlineActionPerformed);

        font.setBackground(new java.awt.Color(67, 69, 74));
        font.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/resource/icons/fontStyle.png"))); // NOI18N
        font.setMargin(new java.awt.Insets(3, 0, 0, 0));
        font.addActionListener(this::fontActionPerformed);

        fontColor.setBackground(new java.awt.Color(67, 69, 74));
        fontColor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/resource/icons/fontColor.png"))); // NOI18N
        fontColor.setText("_");
        fontColor.setToolTipText("");
        fontColor.setFont(new java.awt.Font("Tahoma", 1, 28)); // NOI18N
        fontColor.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        fontColor.setIconTextGap(0);
        fontColor.setMargin(new java.awt.Insets(0, 0, 0, 0));
        fontColor.addActionListener(this::fontColorActionPerformed);

        javax.swing.GroupLayout panel4Layout = new javax.swing.GroupLayout(panel4);
        panel4.setLayout(panel4Layout);
        panel4Layout.setHorizontalGroup(
            panel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel4Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(bold, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(italize, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(underline, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(font, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fontColor, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cancel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(save, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12))
        );
        panel4Layout.setVerticalGroup(
            panel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(underline, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(italize, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cancel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(save, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bold, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(font, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(fontColor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 384;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 6, 6, 6);
        jPanel1.add(panel4, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 20, 25, 25);
        panel1.add(jPanel1, gridBagConstraints);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panel1, javax.swing.GroupLayout.PREFERRED_SIZE, 993, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel1, javax.swing.GroupLayout.DEFAULT_SIZE, 652, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelActionPerformed
        cancelEdit();
    }//GEN-LAST:event_cancelActionPerformed

    private void underlineActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_underlineActionPerformed
        applyUnderline();
    }//GEN-LAST:event_underlineActionPerformed

    private void titleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_titleActionPerformed
        title.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    textArea.requestFocus();
                    textArea.selectAll();
                }
            }
        });
    }//GEN-LAST:event_titleActionPerformed

    private void backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backActionPerformed
         showPanel("notes");
    }//GEN-LAST:event_backActionPerformed

    private void saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveActionPerformed
         saveNote();                          
    }//GEN-LAST:event_saveActionPerformed

    private void boldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boldActionPerformed
        applyBold();
    }//GEN-LAST:event_boldActionPerformed

    private void fontActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fontActionPerformed
        Font selectedFont = fontChooser();
        
        if (selectedFont != null) {
            // Applies the selected font to text area
            textArea.setFont(selectedFont);
            // Also apply to title with larger size
            Font titleFont = selectedFont.deriveFont(24f);
            title.setFont(titleFont);
        }
        
        // Reset button appearance
        font.setSelected(false);
        updateButtonAppearance(font, false);
    }//GEN-LAST:event_fontActionPerformed

    private void fontColorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fontColorActionPerformed
        Color selectedColor = JColorChooser.showDialog(
            this, 
            "Choose Text Color", 
            newTextColor
        );
        
        if (selectedColor != null) {
            applyColorToSelection(selectedColor);
            updateButtonAppearance(fontColor, textColorActive);
            fontColor.setForeground(selectedColor);
        }
        
        fontColor.setSelected(false);
    }//GEN-LAST:event_fontColorActionPerformed

    private void italizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_italizeActionPerformed
        applyItalic();
        updateButtonAppearance(italize, italicActive);
    }//GEN-LAST:event_italizeActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private main.component.Button back;
    private main.component.Button bold;
    private main.component.Button cancel;
    private main.component.Button font;
    private main.component.Button fontColor;
    private main.component.Button italize;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane3;
    private main.component.Panel panel1;
    private main.component.Panel panel2;
    private main.component.Panel panel3;
    private main.component.Panel panel4;
    private main.component.Button save;
    private javax.swing.JTextPane textArea;
    private javax.swing.JTextField title;
    private main.component.Button underline;
    // End of variables declaration//GEN-END:variables
}
