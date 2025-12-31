/*
 * 06/05/2024
 *
 * SwingTextSessionAdapter.java - Swing adapter for TextSession.
 *
 * This library is distributed under a modified BSD license.  See the included
 * LICENSE.md file for details.
 */
package org.fife.ui.autocomplete;

import java.awt.event.FocusListener;
import java.beans.PropertyChangeListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.JTextComponent;
import javax.swing.event.CaretListener;
import javax.swing.event.DocumentListener;


/**
 * Adapter that exposes a {@link JTextComponent} as a {@link TextSession}.
 */
public class SwingTextSessionAdapter implements TextSession {

	private final JTextComponent delegate;

	/**
	 * Constructor.
	 *
	 * @param delegate The text component to wrap.
	 */
	public SwingTextSessionAdapter(JTextComponent delegate) {
		this.delegate = delegate;
	}

	@Override
	public int getCaretPosition() {
		return delegate.getCaretPosition();
	}

	@Override
	public void setCaretPosition(int position) {
		delegate.setCaretPosition(position);
	}

	@Override
	public int getLength() {
		return delegate.getDocument().getLength();
	}

	@Override
	public int getSelectionStart() {
		return delegate.getSelectionStart();
	}

	@Override
	public int getSelectionEnd() {
		return delegate.getSelectionEnd();
	}

	@Override
	public void select(int start, int end) {
		delegate.select(start, end);
	}

	@Override
	public boolean isEditable() {
		return delegate.isEditable();
	}

	@Override
	public String getText() {
		return delegate.getText();
	}

	@Override
	public String getText(int offs, int len) throws BadLocationException {
		return delegate.getDocument().getText(offs, len);
	}

	@Override
	public void insertText(int offs, String text) {
		try {
			delegate.getDocument().insertString(offs, text, null);
		} catch (BadLocationException e) {
			throw new IllegalArgumentException(e);
		}
	}

	@Override
	public void replaceRange(String text, int start, int end) {
		delegate.setSelectionStart(start);
		delegate.setSelectionEnd(end);
		delegate.replaceSelection(text);
	}

	@Override
	public Document getDocument() {
		return delegate.getDocument();
	}

	@Override
	public void addDocumentListener(DocumentListener listener) {
		delegate.getDocument().addDocumentListener(listener);
	}

	@Override
	public void removeDocumentListener(DocumentListener listener) {
		delegate.getDocument().removeDocumentListener(listener);
	}

	@Override
	public void addCaretListener(CaretListener listener) {
		delegate.addCaretListener(listener);
	}

	@Override
	public void removeCaretListener(CaretListener listener) {
		delegate.removeCaretListener(listener);
	}

	@Override
	public void addFocusListener(FocusListener listener) {
		delegate.addFocusListener(listener);
	}

	@Override
	public void removeFocusListener(FocusListener listener) {
		delegate.removeFocusListener(listener);
	}

	@Override
	public void addPropertyChangeListener(String property, PropertyChangeListener listener) {
		delegate.addPropertyChangeListener(property, listener);
	}

	@Override
	public void removePropertyChangeListener(String property, PropertyChangeListener listener) {
		delegate.removePropertyChangeListener(property, listener);
	}

	@Override
	public void requestFocus() {
		delegate.requestFocusInWindow();
	}

	@Override
	public JTextComponent getAsJTextComponent() {
		return delegate;
	}

}
