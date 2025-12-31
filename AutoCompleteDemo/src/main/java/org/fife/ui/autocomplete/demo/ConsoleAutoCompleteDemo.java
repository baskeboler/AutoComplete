/*
 * 06/05/2024
 *
 * ConsoleAutoCompleteDemo.java - Minimal CLI example showing TextSession usage.
 *
 * This library is distributed under a modified BSD license.  See the included
 * LICENSE.md file for details.
 */
package org.fife.ui.autocomplete.demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;

import org.fife.ui.autocomplete.BasicCompletion;
import org.fife.ui.autocomplete.Completion;
import org.fife.ui.autocomplete.AutoCompletion;
import org.fife.ui.autocomplete.DefaultCompletionProvider;
import org.fife.ui.autocomplete.TextSession;
import org.fife.ui.autocomplete.ui.NoOpAutoCompletionUI;

/**
 * Minimal console demo that wires the auto-completion engine to a text session
 * backed by a plain document. This avoids Swing UI while still exercising
 * completion logic.
 */
public class ConsoleAutoCompleteDemo {

	public static void main(String[] args) throws IOException {
		DefaultCompletionProvider provider = new DefaultCompletionProvider();
		provider.addCompletion(new BasicCompletion(provider, "printf"));
		provider.addCompletion(new BasicCompletion(provider, "println"));
		provider.addCompletion(new BasicCompletion(provider, "printStackTrace"));

		DocumentBackedSession session = new DocumentBackedSession(new PlainDocument());

		AutoCompletion ac = new AutoCompletion(provider);
		ac.setUi(new NoOpAutoCompletionUI());
		ac.install(session);

		System.out.println("Type text and press ENTER to see completions (CTRL+C to exit).");
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
			String line;
			while ((line = reader.readLine()) != null) {
				session.setText(line);
				session.setCaretPosition(line.length());
				List<Completion> completions = provider.getCompletions(session);
				System.out.println("Suggestions: ");
				for (Completion c : completions) {
					System.out.println(" - " + c.getInputText());
				}
				System.out.println();
			}
		}
	}

	/**
	 * Simple {@link TextSession} that wraps a {@link Document} for CLI usage.
	 */
	private static class DocumentBackedSession implements TextSession {

		private final Document document;
		private int caretPosition;
		private int selectionStart;
		private int selectionEnd;

		DocumentBackedSession(Document document) {
			this.document = document;
		}

		void setText(String text) {
			try {
				document.remove(0, document.getLength());
				document.insertString(0, text, null);
				caretPosition = text.length();
				selectionStart = selectionEnd = caretPosition;
			} catch (BadLocationException e) {
				throw new IllegalStateException(e);
			}
		}

		@Override
		public int getCaretPosition() {
			return caretPosition;
		}

		@Override
		public void setCaretPosition(int position) {
			caretPosition = Math.max(0, Math.min(position, getLength()));
			selectionStart = selectionEnd = caretPosition;
		}

		@Override
		public int getLength() {
			return document.getLength();
		}

		@Override
		public int getSelectionStart() {
			return selectionStart;
		}

		@Override
		public int getSelectionEnd() {
			return selectionEnd;
		}

		@Override
		public void select(int start, int end) {
			selectionStart = Math.max(0, start);
			selectionEnd = Math.max(selectionStart, end);
			caretPosition = selectionEnd;
		}

		@Override
		public boolean isEditable() {
			return true;
		}

		@Override
		public String getText() {
			try {
				return document.getText(0, document.getLength());
			} catch (BadLocationException e) {
				return "";
			}
		}

		@Override
		public String getText(int offs, int len) throws BadLocationException {
			return document.getText(offs, len);
		}

		@Override
		public void insertText(int offs, String text) {
			try {
				document.insertString(offs, text, null);
			} catch (BadLocationException e) {
				throw new IllegalStateException(e);
			}
		}

		@Override
		public void replaceRange(String text, int start, int end) {
			try {
				document.remove(start, end - start);
				document.insertString(start, text, null);
				setCaretPosition(start + text.length());
			} catch (BadLocationException e) {
				throw new IllegalStateException(e);
			}
		}

		@Override
		public Document getDocument() {
			return document;
		}

		@Override
		public void addDocumentListener(javax.swing.event.DocumentListener listener) {
			document.addDocumentListener(listener);
		}

		@Override
		public void removeDocumentListener(javax.swing.event.DocumentListener listener) {
			document.removeDocumentListener(listener);
		}

		@Override
		public void addCaretListener(javax.swing.event.CaretListener listener) {
			// No caret events in CLI
		}

		@Override
		public void removeCaretListener(javax.swing.event.CaretListener listener) {
		}

		@Override
		public void addFocusListener(java.awt.event.FocusListener listener) {
			// No focus in CLI
		}

		@Override
		public void removeFocusListener(java.awt.event.FocusListener listener) {
		}

		@Override
		public void addPropertyChangeListener(String property, java.beans.PropertyChangeListener listener) {
			// No properties in CLI
		}

		@Override
		public void removePropertyChangeListener(String property, java.beans.PropertyChangeListener listener) {
		}

		@Override
		public void requestFocus() {
			// No-op
		}
	}
}
