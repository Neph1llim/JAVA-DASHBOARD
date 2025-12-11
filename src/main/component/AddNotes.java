package main.component;

/* Import statements */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.*;
import main.MainFrame;
import main.interfaces.Notes;

public class AddNotes extends javax.swing.JPanel {
    /* Properties */
    String titlePlaceholder = "Untitled note";
    String textPlaceholder = "Start typing your notes here...";
    Color initialText = new Color(33, 33, 34);
    Color normalColor = Button.normalColor;
    Color highlightColor = new Color(255, 255, 255);;
    
    // Track formatting states
    private boolean boldActive = false;
    private boolean italicActive = false;
    private boolean underlineActive = false;
    private Color currentTextColor = Color.BLACK;
    
    /* Constructors */
    public AddNotes() {
        initComponents();
        setupPlaceholder();
        resetFormattingButtons();
    }

    /* Methods */
    private void showPanel(String name){
        CardLayout card = (CardLayout) MainFrame.Interface.getLayout();
        card.show(MainFrame.Interface, name);
    }
    
    private void setupPlaceholder() {
        textArea.setText(textPlaceholder);
        textArea.setForeground(initialText);
        
        title.setText(titlePlaceholder);
        title.setForeground(initialText);
        
        textArea.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (textArea.getForeground().equals(initialText)) {
                    textArea.setText("");
                    textArea.setForeground(currentTextColor);
                }
            }
            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                if (textArea.getText().trim().isEmpty()) {
                    textArea.setText(textPlaceholder);
                    textArea.setForeground(initialText);
                }
            }
        });
        
        title.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (title.getForeground().equals(initialText)) {
                    title.setText("");
                    title.setForeground(currentTextColor);
                }
            }
            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                if (title.getText().trim().isEmpty()) {
                    title.setText(titlePlaceholder);
                    title.setForeground(initialText);
                }
            }
        });
    }
    
    private Button[] Buttons() {
        return new Button[] { back, bold, italize, underline, font, fontColor, cancel, save };
    }
    
    private void resetFormattingButtons() {
        // Set all formatting buttons to normal state
        for(Button btn: Buttons()){
            btn.setSelected(false);
            btn.setBackground(normalColor);
        }
    }
    
    private void updateButtonAppearance(Button button, boolean isActive) {
        if (isActive) {
            button.setBackground(highlightColor);
        } else {
            button.setBackground(normalColor);
        }
        button.repaint();
    }
    
    private void applyBold() {
        boldActive = !boldActive;
        bold.setSelected(boldActive);
        updateButtonAppearance(bold, boldActive);
        
        // Apply to selected text or prepare for next input
        applyStyleToText(StyleConstants.Bold, boldActive);
    }
    
    private void applyItalic() {
        italicActive = !italicActive;
        italize.setSelected(italicActive);
        updateButtonAppearance(italize, italicActive);
        
        // Apply to selected text or prepare for next input
        applyStyleToText(StyleConstants.Italic, italicActive);
    }
    
    private void applyUnderline() {
        underlineActive = !underlineActive;
        underline.setSelected(underlineActive);
        updateButtonAppearance(underline, underlineActive);
        
        // Apply to selected text or prepare for next input
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
            // (affects typing from current cursor position)
            ((JTextPane)textArea).setCharacterAttributes(attrs, false);
        } else {
            // Apply to selected text
            doc.setCharacterAttributes(start, end - start, attrs, false);
        }

        // Return focus to text area
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
        
        // Show in JOptionPane
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

        currentTextColor = color;

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

        // Return focus to text area
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
        panel1.setPanelBackground(new java.awt.Color(0, 0, 0));
        panel1.setLayout(new java.awt.GridBagLayout());

        back.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/resource/fluent--arrow-reply-20-filled.png"))); // NOI18N
        back.setMinimumSize(new java.awt.Dimension(40, 40));
        back.setPreferredSize(new java.awt.Dimension(40, 40));
        back.addActionListener(this::backActionPerformed);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(18, 25, 0, 0);
        panel1.add(back, gridBagConstraints);

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));
        jPanel1.setLayout(new java.awt.GridBagLayout());

        panel2.setPanelBackground(new java.awt.Color(102, 102, 102));

        title.setBackground(new java.awt.Color(102, 102, 102));
        title.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        title.setForeground(new java.awt.Color(0, 0, 0));
        title.setText("Title");
        title.setBorder(null);
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

        panel3.setPanelBackground(new java.awt.Color(102, 102, 102));
        panel3.setLayout(new java.awt.GridBagLayout());

        jScrollPane3.setBorder(null);
        jScrollPane3.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        jScrollPane3.setViewportView(textArea);

        textArea.setBackground(new java.awt.Color(102, 102, 102));
        textArea.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        textArea.setForeground(new java.awt.Color(0, 0, 0));
        textArea.setText("TEXT HERE");
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

        panel4.setPanelBackground(new java.awt.Color(102, 102, 102));

        cancel.setLabel("Cancel");
        cancel.addActionListener(this::cancelActionPerformed);

        save.setLabel("Save");
        save.addActionListener(this::saveActionPerformed);

        bold.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/resource/carbon--text-bold.png"))); // NOI18N
        bold.setMargin(new java.awt.Insets(3, 0, 0, 0));
        bold.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/main/resource/carbon--text-bold.png"))); // NOI18N
        bold.addActionListener(this::boldActionPerformed);

        italize.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/resource/ic--round-format-italic.png"))); // NOI18N
        italize.setMargin(new java.awt.Insets(3, 0, 0, 0));
        italize.addActionListener(this::italizeActionPerformed);

        underline.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/resource/ic--round-format-underlined.png"))); // NOI18N
        underline.setMargin(new java.awt.Insets(3, 0, 0, 0));
        underline.addActionListener(this::underlineActionPerformed);

        font.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/resource/ci--font.png"))); // NOI18N
        font.setMargin(new java.awt.Insets(3, 0, 0, 0));
        font.addActionListener(this::fontActionPerformed);

        fontColor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/resource/ri--font-color.png"))); // NOI18N
        fontColor.setMargin(new java.awt.Insets(3, 0, 0, 0));
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
                .addComponent(panel1, javax.swing.GroupLayout.DEFAULT_SIZE, 993, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel1, javax.swing.GroupLayout.DEFAULT_SIZE, 652, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelActionPerformed
        // Keep previous file 
        showPanel("notes");
        setupPlaceholder();
        System.out.println("notes canceled");
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
                    textArea.selectAll(); // Optional: highlight text
                    System.out.println("title added");
                }
            }
        });
    }//GEN-LAST:event_titleActionPerformed

    private void backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backActionPerformed
         showPanel("notes");
         System.out.println("backed");
    }//GEN-LAST:event_backActionPerformed

    private void saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveActionPerformed
         // add logic here
        
        // change this to instance
        JPanel round1 = new Panel(35, new Color(50,50,50));
        round1.setPreferredSize(new Dimension(250, 250));

        Notes.notePanel.add(round1);
        
        // Configuration for panels
        Notes.notePanel.setLayout(new FlowLayout());
        Notes.notePanel.revalidate();
        Notes.notePanel.repaint();
        
        //return to notes page
        showPanel("notes");
        setupPlaceholder();
        System.out.println("note added");
    }//GEN-LAST:event_saveActionPerformed

    private void boldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boldActionPerformed
        applyBold();
    }//GEN-LAST:event_boldActionPerformed

    private void fontActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fontActionPerformed
        Font selectedFont = fontChooser();
        
        if (selectedFont != null) {
            // Apply the selected font to text area
            textArea.setFont(selectedFont);
            
            // Also apply to title with larger size
            Font titleFont = selectedFont.deriveFont(24f);
            title.setFont(titleFont);
            
            System.out.println("Font changed to: " + selectedFont.getFamily());
        } else {
            System.out.println("Font selection cancelled");
        }
        
        // Reset button appearance
        font.setSelected(false);
        updateButtonAppearance(font, false);
    }//GEN-LAST:event_fontActionPerformed

    private void fontColorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fontColorActionPerformed
        Color selectedColor = JColorChooser.showDialog(
            this, 
            "Choose Text Color", 
            currentTextColor
        );
        
        if (selectedColor != null) {
            applyColorToSelection(selectedColor);
        }
        
        // Reset button appearance
        fontColor.setSelected(false);
        updateButtonAppearance(fontColor, false);
    }//GEN-LAST:event_fontColorActionPerformed

    private void italizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_italizeActionPerformed
        applyItalic();
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
