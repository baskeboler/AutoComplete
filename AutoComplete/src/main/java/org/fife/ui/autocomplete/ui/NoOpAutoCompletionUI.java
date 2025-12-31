/*
 * 06/05/2024
 *
 * NoOpAutoCompletionUI.java - No-op UI strategy for non-graphical use cases.
 *
 * This library is distributed under a modified BSD license.  See the included
 * LICENSE.md file for details.
 */
package org.fife.ui.autocomplete.ui;

import java.awt.Window;

import org.fife.ui.autocomplete.AutoCompletePopupWindow;
import org.fife.ui.autocomplete.AutoCompletion;
import org.fife.ui.autocomplete.ParameterizedCompletion;
import org.fife.ui.autocomplete.ParameterizedCompletionContext;
import org.fife.ui.autocomplete.TextSession;


/**
 * UI strategy that disables popup rendering. Useful for CLI scenarios where
 * completions are consumed programmatically.
 */
public class NoOpAutoCompletionUI implements AutoCompletionUI {

	@Override
	public AutoCompletePopupWindow createPopupWindow(Window parentWindow,
			AutoCompletion ac) {
		return null;
	}

	@Override
	public ParameterizedCompletionContext createParameterizedCompletionContext(
			Window parentWindow, AutoCompletion ac, ParameterizedCompletion pc) {
		return null;
	}

	@Override
	public void onInstall(AutoCompletion ac, TextSession session) {
		// Nothing to do.
	}

	@Override
	public void onUninstall(AutoCompletion ac) {
		// Nothing to do.
	}

}
