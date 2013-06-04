/**
 * 
 */
package org.eclipse.emf.eef.runtime.ui.fx.widgets;

import java.io.IOException;
import java.net.URL;
import java.util.Collection;
import java.util.List;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.eef.runtime.ui.fx.FXUIConstants;
import org.eclipse.emf.eef.runtime.ui.fx.widgets.utils.AdapterFactoryFeatureObservableList;

import at.bestsolution.efxclipse.runtime.emf.edit.ui.AdapterFactoryTableCellFactory;
import at.bestsolution.efxclipse.runtime.emf.edit.ui.ProxyCellValueFactory;

import com.google.common.collect.Lists;

/**
 * @author <a href="mailto:goulwen.lefur@obeo.fr">Goulwen Le Fur</a>
 *
 */
public class MultiLinePropertyViewer extends BorderPane  {
	
	private TableView<Object> table;
	private Button addButton;
	private Button removeButton;
	private Button upButton;
	private Button downButton;

	private AdapterFactory adapterFactory;
	private Collection<MultiLinePropertyViewerListener> listeners;

	private boolean locked;
	private int lowerBound = 0;
	private int upperBound = -1;

	public MultiLinePropertyViewer() {
		table = new TableView<Object>();
		TableColumn<Object, Object> column = new TableColumn<Object, Object>("Elements");
		column.setPrefWidth(FXUIConstants.DEFAULT_COLUMN_WIDTH);
		table.getColumns().add(column);
		table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		table.setOnMouseReleased(new EventHandler<MouseEvent>() {

			/**
			 * {@inheritDoc}
			 * @see javafx.event.EventHandler#handle(javafx.event.Event)
			 */
			public void handle(MouseEvent event) {
				updateButtons();				
			}
			
		});
		table.setOnMouseClicked(new EventHandler<MouseEvent>() {

			/**
			 * {@inheritDoc}
			 * @see javafx.event.EventHandler#handle(javafx.event.Event)
			 */
			public void handle(MouseEvent arg0) {
				if (arg0.getClickCount() == 2) {
					for (MultiLinePropertyViewerListener listener : listeners) {
						listener.edit(table.getSelectionModel().getSelectedItem());
					}					
				}
			}
			
		});
		setCenter(table);
		VBox vbox = createControlPanel();
		vbox.setPadding(new Insets(0, 10, 0, 10));
		vbox.setSpacing(5);
		setRight(vbox);
		updateButtons();
		listeners = Lists.newArrayList();
	}

	private VBox createControlPanel() {
		VBox vbox = new VBox();
		addButton = createButton("icons/Add.gif");
		addButton.setOnMouseClicked(new EventHandler<MouseEvent>() {

			/**
			 * {@inheritDoc}
			 * @see javafx.event.EventHandler#handle(javafx.event.Event)
			 */
			public void handle(MouseEvent event) {
				for (MultiLinePropertyViewerListener listener : listeners) {
					listener.add();
				}
			}
			
		});
		removeButton = createButton("icons/Delete.gif");
		removeButton.setOnMouseClicked(new EventHandler<MouseEvent>() {

			/**
			 * {@inheritDoc}
			 * @see javafx.event.EventHandler#handle(javafx.event.Event)
			 */
			public void handle(MouseEvent event) {
				for (MultiLinePropertyViewerListener listener : listeners) {
					if (table.getSelectionModel().getSelectedItems().size() > 1) {
						listener.removeAll(table.getSelectionModel().getSelectedItems());
					} else if (table.getSelectionModel().getSelectedItems().size() == 1) {
						listener.remove(table.getSelectionModel().getSelectedItem());
					}
				}
			}
			
		});
		upButton = createButton("icons/ArrowUp.gif");
		upButton.setOnMouseClicked(new EventHandler<MouseEvent>() {

			/**
			 * {@inheritDoc}
			 * @see javafx.event.EventHandler#handle(javafx.event.Event)
			 */
			public void handle(MouseEvent event) {
				for (MultiLinePropertyViewerListener listener : listeners) {
					listener.moveUp(table.getSelectionModel().getSelectedItem());
				}
			}
			
		});
		downButton = createButton("icons/ArrowDown.gif");
		downButton.setOnMouseClicked(new EventHandler<MouseEvent>() {

			/**
			 * {@inheritDoc}
			 * @see javafx.event.EventHandler#handle(javafx.event.Event)
			 */
			public void handle(MouseEvent event) {
				for (MultiLinePropertyViewerListener listener : listeners) {
					listener.moveDown(table.getSelectionModel().getSelectedItem());
				}
			}
			
		});
		vbox.getChildren().addAll(addButton, removeButton, upButton, downButton);
		buildAdditionnalActionControls(vbox);
		return vbox;
	}

	private Button createButton(String iconPath) {
		Button result;
		URL bundleEntry = org.osgi.framework.FrameworkUtil.getBundle(this.getClass()).getEntry(iconPath);
		try {
			Image addImage = new Image(bundleEntry.openStream());
			result = new Button("", new ImageView(addImage));
		} catch (IOException e) {
			result = new Button("+");
		}
		return result;
	}
	
	public void setInput(AdapterFactory adapterFactory, EObject input, EStructuralFeature feature) {
		this.adapterFactory = adapterFactory;
		for (int i = 0; i< table.getColumns().size(); i++) {
			@SuppressWarnings("unchecked")
			TableColumn<Object, Object> column = (TableColumn<Object, Object>) table.getColumns().get(i);
			column.setCellFactory(new AdapterFactoryTableCellFactory<Object, Object>(this.adapterFactory, i));
			column.setCellValueFactory(new ProxyCellValueFactory<Object, Object>());
		}
		table.setItems(new AdapterFactoryFeatureObservableList<Object>(adapterFactory, input, feature));
	}
	
	protected void buildAdditionnalActionControls(Pane parent) {
		//Do nothing
	}

	public void addColumn(String name, int defaultColumnWidth) {
		TableColumn<Object, Object> column = new TableColumn<Object, Object>(name);
		column.setPrefWidth(FXUIConstants.DEFAULT_COLUMN_WIDTH);
		table.getColumns().add(column);
	}	
	
	/**
	 * @param lowerBound the lowerBound to set
	 */
	public void setLowerBound(int lowerBound) {
		this.lowerBound = lowerBound;
	}

	/**
	 * @param upperBound the upperBound to set
	 */
	public void setUpperBound(int upperBound) {
		this.upperBound = upperBound;
	}

	/**
	 * @param locked the locked to set
	 */
	public void setLocked(boolean locked) {
		this.locked = locked;
		updateButtons();
	}

	public void refresh() {
		updateButtons();
	}

	/**
	 * Add a {@link MultiLinePropertyViewerListener} to this instance.
	 * @param listener {@link MultiLinePropertyViewerListener} to add.
	 */
	public void addReferenceEditorListener(MultiLinePropertyViewerListener listener) {
		listeners.add(listener);
	}
	
	/**
	 * Remove a {@link MultiLinePropertyViewerListener} to this instance.
	 * @param listener {@link MultiLinePropertyViewerListener} to remove.
	 */
	public void removeReferenceEditorListener(MultiLinePropertyViewerListener listener) {
		listeners.remove(listener);
	}
	
	/**
	 * Update the list buttons.
	 */
	protected void updateButtons() {
		TableViewSelectionModel<Object> selection = table.getSelectionModel();
		addButton.setDisable(!shouldEnableAdd(selection));
		if (selection.getSelectedItems().size() == 0) {
			removeButton.setDisable(true);
			upButton.setDisable(true);
			downButton.setDisable(true);
		} else if (selection.getSelectedItems().size() == 1) {
			removeButton.setDisable(!shouldEnableRemove(selection));
			upButton.setDisable(!shouldEnableUp(selection));
			downButton.setDisable(!shouldEnableDown(selection));
		} else {
			removeButton.setDisable(!shouldEnableRemove(selection));
			upButton.setDisable(true);
			downButton.setDisable(true);
		}
	}

	private boolean shouldEnableAdd(TableViewSelectionModel<Object> selection) {
		List<Object> elements = table.getItems();
		return !locked && ((upperBound == -1) || (elements.size() < upperBound));
	}
	
	private boolean shouldEnableRemove(TableViewSelectionModel<Object> selection) {
		List<Object> elements = table.getItems();
		return !locked && ((lowerBound == 0) || (elements.size() > lowerBound));
	}
	
	private boolean shouldEnableUp(TableViewSelectionModel<Object> selection) {
		Object selectedElement = selection.getSelectedItem();
		List<Object> elements = table.getItems();
		if (elements != null) {
			return !locked && (elements.size() > 1  && elements.indexOf(selectedElement) > 0);
		}
		return false;
	}

	private boolean shouldEnableDown(TableViewSelectionModel<Object> selection) {
		Object selectedElement = selection.getSelectedItem();
		List<Object> elements = table.getItems();
		if (elements != null) {
			return !locked && (elements.size() > 1)  && (elements.indexOf(selectedElement) < elements.size() - 1);
		}
		return false;
	}

	/**
	 * @author <a href="mailto:goulwen.lefur@obeo.fr">Goulwen Le Fur</a>
	 *
	 */
	public interface MultiLinePropertyViewerListener {

		/**
		 * Handle a "add" request.
		 */
		void add();
		
		/**
		 * Handle a "edit" request.
		 * @param editedElement Edited Element.
		 */
		void edit(Object editedElement);
		
		/**
		 * Handle a "remove" request.
		 * @param removedElement Removed Element.
		 */
		void remove(Object removedElement);
		
		/**
		 * Handle a "remove" request for several elements.
		 * @param removedElements Removed Elements.
		 */
		void removeAll(Collection<?> removedElements);
		
		/**
		 * Handle a "move up" request.
		 * @param movedElement Moved Element.
		 */
		void moveUp(Object movedElement);
		
		/**
		 * Handle a "move down" request.
		 * @param movedElement Moved Element.
		 */
		void moveDown(Object movedElement);
		
	}
}