/*
 * 06/05/2024
 *
 * TextSession.java - Abstraction over editable text used by auto-completion.
 *
 * This library is distributed under a modified BSD license.  See the included
 * LICENSE.md file for details.
 */
package org.fife.ui.autocomplete;

import java.awt.event.FocusListener;
import java.beans.PropertyChangeListener;
import javax.swing.event.CaretListener;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.JTextComponent;


/**
 * Abstraction over editable text used by the auto-completion engine.  This
 * interface mirrors the minimal surface area of {@link JTextComponent} needed
 * by the library so it can be adapted to non-Swing environments (e.g. console
 * input loops) while still providing Swing backwards compatibility.
 *
 * @author Robert Futrell
 * @since 3.4
 */
public interface TextSession {

	/**
	 * Returns the caret position.
	 *
	 * @return The caret position.
	 */
	int getCaretPosition();

	/**
	 * Moves the caret to a new position.
	 *
	 * @param position The new position.
	 */
	void setCaretPosition(int position);

	/**
	 * Returns the length of the underlying text.
	 *
	 * @return The length.
	 */
	int getLength();

	/**
	 * Returns the current selection start.
	 *
	 * @return The selection start.
	 */
	int getSelectionStart();

	/**
	 * Returns the selection end.
	 *
	 * @return The selection end.
	 */
	int getSelectionEnd();

	/**
	 * Selects a range of text.
	 *
	 * @param start The selection start.
	 * @param end The selection end.
	 */
	void select(int start, int end);

	/**
	 * Returns whether the underlying text is editable.
	 *
	 * @return Whether the text is editable.
	 */
	boolean isEditable();

	/**
	 * Returns the underlying text.
	 *
	 * @return The text.
	 */
	String getText();

	/**
	 * Returns text in a specific range.
	 *
	 * @param offs The offset.
	 * @param len The length.
	 * @return The text.
	 * @throws BadLocationException If the range is invalid.
	 */
	String getText(int offs, int len) throws BadLocationException;

	/**
	 * Inserts text at a specific offset.
	 *
	 * @param offs The offset.
	 * @param text The text.
	 */
	void insertText(int offs, String text);

	/**
	 * Replaces text in a specific range.
	 *
	 * @param text The text to insert.
	 * @param start The start offset.
	 * @param end The end offset.
	 */
	void replaceRange(String text, int start, int end);

	/**
	 * Returns the underlying document.
	 *
	 * @return The document.
	 */
	Document getDocument();

	/**
	 * Adds a {@link DocumentListener}.
	 *
	 * @param listener The listener.
	 */
	void addDocumentListener(DocumentListener listener);

	/**
	 * Removes a {@link DocumentListener}.
	 *
	 * @param listener The listener.
	 */
	void removeDocumentListener(DocumentListener listener);

	/**
	 * Adds a {@link CaretListener}.
	 *
	 * @param listener The listener.
	 */
	void addCaretListener(CaretListener listener);

	/**
	 * Removes a {@link CaretListener}.
	 *
	 * @param listener The listener.
	 */
	void removeCaretListener(CaretListener listener);

	/**
	 * Adds a {@link FocusListener}.
	 *
	 * @param listener The listener.
	 */
	void addFocusListener(FocusListener listener);

	/**
	 * Removes a {@link FocusListener}.
	 *
	 * @param listener The listener.
	 */
	void removeFocusListener(FocusListener listener);

	/**
	 * Adds a property change listener for a specific property.
	 *
	 * @param property The property.
	 * @param listener The listener.
	 */
	void addPropertyChangeListener(String property, PropertyChangeListener listener);

	/**
	 * Removes a property change listener for a specific property.
	 *
	 * @param property The property.
	 * @param listener The listener.
	 */
	void removePropertyChangeListener(String property, PropertyChangeListener listener);

	/**
	 * Requests focus for the underlying implementation.
	 */
	void requestFocus();

	/**
	 * If this session is backed by a Swing {@link JTextComponent}, returns it.
	 *
	 * @return The component or <code>null</code>.
	 */
	default JTextComponent getAsJTextComponent() {
		return null;
	}

}
