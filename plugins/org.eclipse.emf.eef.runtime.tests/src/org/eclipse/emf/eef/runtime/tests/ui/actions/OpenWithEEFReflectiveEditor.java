/*******************************************************************************
 * Copyright (c) 2008, 2011 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.emf.eef.runtime.tests.ui.actions;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.emf.eef.runtime.EEFRuntimePlugin;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.ElementTreeSelectionDialog;
import org.eclipse.ui.model.WorkbenchContentProvider;
import org.eclipse.ui.model.WorkbenchLabelProvider;
import org.eclipse.ui.part.FileEditorInput;

/**
 * Our sample action implements workbench action delegate. The action proxy will be created by the workbench
 * and shown in the UI. When the user tries to use the action, this delegate will be created and execution
 * will be delegated to it.
 * 
 * @see IWorkbenchWindowActionDelegate
 */
public class OpenWithEEFReflectiveEditor implements IWorkbenchWindowActionDelegate {
	private IWorkbenchWindow window;

	/**
	 * The constructor.
	 */
	public OpenWithEEFReflectiveEditor() {
	}

	/**
	 * The action has been activated. The argument of the method represents the 'real' action sitting in the
	 * workbench UI.
	 * 
	 * @see IWorkbenchWindowActionDelegate#run
	 */
	public void run(IAction action) {
		ElementTreeSelectionDialog workspaceJarSelectionDialog = new ElementTreeSelectionDialog(window
				.getShell(), new WorkbenchLabelProvider(), new WorkbenchContentProvider());
		workspaceJarSelectionDialog.setInput(ResourcesPlugin.getWorkspace().getRoot());
		workspaceJarSelectionDialog.setTitle("Select the file to open with the EEF Reflective Editor");
		workspaceJarSelectionDialog.open();

		Object firstResult = workspaceJarSelectionDialog.getFirstResult();

		if (firstResult instanceof IFile)
			try {
				PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().openEditor(
						new FileEditorInput((IFile)firstResult), "org.eclipse.emf.eef.reflect.editor");
			} catch (PartInitException e) {
				EEFRuntimePlugin.getDefault().logError("Unable to open file with EEF Reflective Editor", e);
			}
	}

	/**
	 * Selection in the workbench has been changed. We can change the state of the 'real' action here if we
	 * want, but this can only happen after the delegate has been created.
	 * 
	 * @see IWorkbenchWindowActionDelegate#selectionChanged
	 */
	public void selectionChanged(IAction action, ISelection selection) {
	}

	/**
	 * We can use this method to dispose of any system resources we previously allocated.
	 * 
	 * @see IWorkbenchWindowActionDelegate#dispose
	 */
	public void dispose() {
	}

	/**
	 * We will cache window object in order to be able to provide parent shell for the message dialog.
	 * 
	 * @see IWorkbenchWindowActionDelegate#init
	 */
	public void init(IWorkbenchWindow window) {
		this.window = window;
	}
}
