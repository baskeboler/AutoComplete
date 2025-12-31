/*
 * 06/05/2024
 *
 * SwingAutoCompletionUI.java - Default Swing UI strategy for auto-completion.
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
 * Default Swing implementation of the auto-completion UI strategy.
 */
public class SwingAutoCompletionUI implements AutoCompletionUI {

	@Override
	public AutoCompletePopupWindow createPopupWindow(Window parentWindow,
			AutoCompletion ac) {
		return new AutoCompletePopupWindow(parentWindow, ac);
	}

	@Override
	public ParameterizedCompletionContext createParameterizedCompletionContext(
			Window parentWindow, AutoCompletion ac, ParameterizedCompletion pc) {
		return new ParameterizedCompletionContext(parentWindow, ac, pc);
	}

	@Override
	public void onInstall(AutoCompletion ac, TextSession session) {
		// Nothing to do for Swing
	}

	@Override
	public void onUninstall(AutoCompletion ac) {
		// Nothing to do for Swing
	}

}
