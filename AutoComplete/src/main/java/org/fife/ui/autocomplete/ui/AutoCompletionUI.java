/*
 * 06/05/2024
 *
 * AutoCompletionUI.java - Strategy interface for auto-completion UI hooks.
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
 * Strategy interface that abstracts popup/description rendering for
 * auto-completion. Implementations can provide Swing popups, CLI logging, or
 * other UI toolkits.
 */
public interface AutoCompletionUI {

	/**
	 * Creates the popup window for displaying completions.
	 *
	 * @param parentWindow The parent window.
	 * @param ac The owning auto-completion.
	 * @return The popup window, or <code>null</code> if popup rendering is not
	 *         desired.
	 */
	AutoCompletePopupWindow createPopupWindow(Window parentWindow, AutoCompletion ac);

	/**
	 * Creates the parameterized completion context for displaying parameter
	 * assistance.
	 *
	 * @param parentWindow The parent window.
	 * @param ac The owning auto-completion.
	 * @param pc The completion being inserted.
	 * @return The context, or <code>null</code> if parameter assistance should
	 *         be disabled for this UI.
	 */
	ParameterizedCompletionContext createParameterizedCompletionContext(
		Window parentWindow, AutoCompletion ac, ParameterizedCompletion pc);

	/**
	 * Lifecycle hook called when {@link AutoCompletion} is installed.
	 *
	 * @param ac The owning auto-completion.
	 * @param session The session it was installed to.
	 */
	void onInstall(AutoCompletion ac, TextSession session);

	/**
	 * Lifecycle hook called when {@link AutoCompletion} is uninstalled.
	 *
	 * @param ac The owning auto-completion.
	 */
	void onUninstall(AutoCompletion ac);

}
