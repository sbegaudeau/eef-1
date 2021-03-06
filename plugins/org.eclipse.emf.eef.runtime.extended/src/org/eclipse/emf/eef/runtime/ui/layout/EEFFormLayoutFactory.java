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
package org.eclipse.emf.eef.runtime.ui.layout;

import org.eclipse.swt.layout.GridLayout;

/**
 * @author <a href="mailto:goulwen.lefur@obeo.fr">Goulwen Le Fur</a>
 */
public class EEFFormLayoutFactory {

	// Used in place of 0. If 0 is used, widget borders will appear clipped
	// on some platforms (e.g. Windows XP Classic Theme).
	// Form tool kit requires parent composites containing the widget to have
	// at least 1 pixel border margins in order to paint the flat borders.
	// The form toolkit paints flat borders on a given widget when native
	// borders are not painted by SWT. See FormToolkit#paintBordersFor()
	public static final int DEFAULT_CLEAR_MARGIN = 2;

	// CLEAR
	public static final int CLEAR_MARGIN_TOP = DEFAULT_CLEAR_MARGIN;

	public static final int CLEAR_MARGIN_BOTTOM = DEFAULT_CLEAR_MARGIN;

	public static final int CLEAR_MARGIN_LEFT = DEFAULT_CLEAR_MARGIN;

	public static final int CLEAR_MARGIN_RIGHT = DEFAULT_CLEAR_MARGIN;

	public static final int CLEAR_HORIZONTAL_SPACING = 0;

	public static final int CLEAR_VERTICAL_SPACING = 0;

	public static final int CLEAR_MARGIN_HEIGHT = 12;

	public static final int CLEAR_MARGIN_WIDTH = 7;

	public static final int FORM_BODY_HORIZONTAL_SPACING = 20;

	public static final int FORM_BODY_VERTICAL_SPACING = 17;

	// MASTER DETAILS
	public static final int MASTER_DETAILS_MARGIN_RIGHT = 1;

	public static final int MASTER_DETAILS_MARGIN_TOP = 0;

	public static final int MASTER_DETAILS_MARGIN_BOTTOM = 0;

	// Used only by masters part. Details part margin dynamically calculated
	public static final int MASTER_DETAILS_MARGIN_LEFT = 0;

	// Used only by details part. Masters part margin dynamically calculated
	public static final int MASTER_DETAILS_HORIZONTAL_SPACING = FORM_BODY_HORIZONTAL_SPACING;

	public static final int MASTER_DETAILS_VERTICAL_SPACING = FORM_BODY_VERTICAL_SPACING;

	public static final int MASTER_DETAILS_MARGIN_HEIGHT = 12;

	public static final int MASTER_DETAILS_MARGIN_WIDTH = 7;

	/**
	 * For master sections belonging to a master details block.
	 * 
	 * @param makeColumnsEqualWidth
	 * @param numColumns
	 * @return
	 */
	public static GridLayout createMasterGridLayout(boolean makeColumnsEqualWidth, int numColumns) {
		GridLayout layout = new GridLayout();

		layout.marginHeight = MASTER_DETAILS_MARGIN_HEIGHT;
		layout.marginWidth = MASTER_DETAILS_MARGIN_WIDTH;

		layout.marginTop = MASTER_DETAILS_MARGIN_TOP;
		layout.marginBottom = MASTER_DETAILS_MARGIN_BOTTOM;
		layout.marginLeft = MASTER_DETAILS_MARGIN_LEFT;
		// Cannot set layout on a sash form.
		// In order to replicate the horizontal spacing between sections,
		// divide the amount by 2 and set the master section right margin to
		// half the amount and set the left details section margin to half
		// the amount. The default sash width is currently set at 3.
		// Minus 1 pixel from each half. Use the 1 left over pixel to separate
		// the details section from the vertical scollbar.
		int marginRight = MASTER_DETAILS_HORIZONTAL_SPACING;
		if (marginRight > 0) {
			marginRight = marginRight / 2;
			if (marginRight > 0) {
				marginRight--;
			}
		}
		layout.marginRight = marginRight;

		layout.horizontalSpacing = MASTER_DETAILS_HORIZONTAL_SPACING;
		layout.verticalSpacing = MASTER_DETAILS_VERTICAL_SPACING;

		layout.makeColumnsEqualWidth = makeColumnsEqualWidth;
		layout.numColumns = numColumns;

		return layout;
	}

	/**
	 * For miscellaneous grouping composites. For sections (as a whole - header plus client).
	 * 
	 * @param makeColumnsEqualWidth
	 * @param numColumns
	 * @return
	 */
	public static GridLayout createClearGridLayout(boolean makeColumnsEqualWidth, int numColumns) {
		GridLayout layout = new GridLayout();

		layout.marginHeight = CLEAR_MARGIN_HEIGHT;
		layout.marginWidth = CLEAR_MARGIN_WIDTH;

		layout.marginTop = CLEAR_MARGIN_TOP;
		layout.marginBottom = CLEAR_MARGIN_BOTTOM;
		layout.marginLeft = CLEAR_MARGIN_LEFT;
		layout.marginRight = CLEAR_MARGIN_RIGHT;

		layout.horizontalSpacing = CLEAR_HORIZONTAL_SPACING;
		layout.verticalSpacing = CLEAR_VERTICAL_SPACING;

		layout.makeColumnsEqualWidth = makeColumnsEqualWidth;
		layout.numColumns = numColumns;

		return layout;
	}

	/**
	 * For details sections belonging to a master details block.
	 * 
	 * @param makeColumnsEqualWidth
	 * @param numColumns
	 * @return
	 */
	public static GridLayout createDetailsGridLayout(boolean makeColumnsEqualWidth, int numColumns) {
		GridLayout layout = new GridLayout();

		layout.marginHeight = MASTER_DETAILS_MARGIN_HEIGHT;
		layout.marginWidth = MASTER_DETAILS_MARGIN_WIDTH;

		layout.marginTop = MASTER_DETAILS_MARGIN_TOP;
		layout.marginBottom = MASTER_DETAILS_MARGIN_BOTTOM;
		// Cannot set layout on a sash form.
		// In order to replicate the horizontal spacing between sections,
		// divide the amount by 2 and set the master section right margin to
		// half the amount and set the left details section margin to half
		// the amount. The default sash width is currently set at 3.
		// Minus 1 pixel from each half. Use the 1 left over pixel to separate
		// the details section from the vertical scollbar.
		int marginLeft = MASTER_DETAILS_HORIZONTAL_SPACING;
		if (marginLeft > 0) {
			marginLeft = marginLeft / 2;
			if (marginLeft > 0) {
				marginLeft--;
			}
		}
		layout.marginLeft = marginLeft;
		layout.marginRight = MASTER_DETAILS_MARGIN_RIGHT;

		layout.horizontalSpacing = MASTER_DETAILS_HORIZONTAL_SPACING;
		layout.verticalSpacing = MASTER_DETAILS_VERTICAL_SPACING;

		layout.makeColumnsEqualWidth = makeColumnsEqualWidth;
		layout.numColumns = numColumns;

		return layout;
	}

}
