package com.danielmichalski.elicense.ui.login.filter;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

/**
 * Author: Daniel
 */

public class JTextFieldDocumentFilter extends DocumentFilter {
    private int fieldColumnLength;

    public JTextFieldDocumentFilter(int fieldColumnLength) {
        this.fieldColumnLength = fieldColumnLength;
    }

    @Override
    public void insertString(DocumentFilter.FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
        if (fb.getDocument().getLength() + string.length() > fieldColumnLength) {
            return;
        }

        fb.insertString(offset, string, attr);
    }

    @Override
    public void remove(DocumentFilter.FilterBypass fb, int offset, int length) throws BadLocationException {
        fb.remove(offset, length);
    }

    @Override
    public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
        if (fb.getDocument().getLength() + text.length() > fieldColumnLength) {
            return;
        }

        fb.insertString(offset, text, attrs);
    }
}
