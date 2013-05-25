package com.terrynow.exitclose;

import org.eclipse.ui.IStartup;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchListener;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

/**
 * @author Terry E-mail: yaoxinghuo at 126 dot com
 * @date May 25, 2013 12:30:46 PM
 */
public class Startup implements IStartup {

	public void earlyStartup() {
		registerAutoClose();
	}

	private void registerAutoClose() {
		// 根据eclipse的网站提示
		// http://wiki.eclipse.org/FAQ_Close_All_Editors_On_Shutdown
		IWorkbench workbench = PlatformUI.getWorkbench();

		workbench.addWorkbenchListener(new IWorkbenchListener() {
			public boolean preShutdown(IWorkbench workbench, boolean forced) {
				IWorkbenchWindow iww = workbench.getActiveWorkbenchWindow();
				if (iww == null)
					return true;
				final IWorkbenchPage activePage = iww.getActivePage();
				if (activePage == null)
					return true;
				activePage.closeEditors(activePage.getEditorReferences(), true);
				return true;
			}

			public void postShutdown(IWorkbench workbench) {

			}
		});
	}
}
