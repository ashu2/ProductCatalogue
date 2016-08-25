package sb.videocon.controller;

import java.sql.SQLException;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Dialogs;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sb.videocon.MainApp;
import sb.videocon.db.DbOperations;
import sb.videocon.model.Product;

public class ProductLayoutController {
	private MainApp mainApp;
	private Stage dialogStage;

	@FXML
	private TableView<Product> productTable;
	
	/*<TableColumn prefWidth="150.0" text="Serial No" fx:id="serialNoColumn"/>
    <TableColumn prefWidth="150.0" text="Product Name" fx:id="productNameColumn"/>
    <TableColumn prefWidth="150.0" text="Cost" fx:id="costColumn"/>
    <TableColumn prefWidth="150.0" text="Brand" fx:id="brandColumn"/>
    <TableColumn prefWidth="150.0" text="Category" fx:id="categoryColumn"/>
    <TableColumn prefWidth="150.0" text="Description" fx:id="descriptionColumn"/>*/
    
	@FXML
	private TableColumn<Product, String> serialNoColumn;
	@FXML
	private TableColumn<Product, String> productNameColumn;
	@FXML
	private TableColumn<Product, String> costColumn;
	@FXML
	private TableColumn<Product, String> brandColumn;
	@FXML
	private TableColumn<Product, String> categoryColumn;
	@FXML
	private TableColumn<Product, String> descriptionColumn;

	@FXML
	private Label serialNoLabel;
	@FXML
	private Label productNameLabel;
	@FXML
	private Label costLabel;
	@FXML
	private Label brandLabel;
	@FXML
	private Label categoryLabel;
	@FXML
	private Label descriptionLabel;
	//
	@FXML
	private void initialize() {
		serialNoColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("serialNo"));
		productNameColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("productName"));
		brandColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("brand"));
		productTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		productTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Product>() {
			@Override
			public void changed(ObservableValue<? extends Product> observable, Product oldValue, Product newValue) {
				showProductDetails(newValue);
			}
		});
	}

	private void showProductDetails(Product Product) {
		productNameLabel.setText(Product.getProductName());
		costLabel.setText(Product.getCost());
		categoryLabel.setText(Product.getCategory());
		brandLabel.setText(Product.getBrand());
		serialNoLabel.setText(Product.getSerialNo());
		descriptionLabel.setText(Product.getDescription());
		
	}

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
		productTable.setItems(mainApp.getProductData());
	}

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	/* handler */

	@FXML
	private void handleNewButton() throws ClassNotFoundException, SQLException {
		Product tempProduct = new Product();
		boolean okClicked = mainApp.showProductEditDialog(tempProduct);
		if (okClicked) {
			new DbOperations().addProduct(tempProduct);
			mainApp.getProductData().add(tempProduct);
			productTable.refresh();
		}
	}

	@FXML
	private void handleEditButton() {

	}

	@FXML
	private void handleDeleteButton() throws ClassNotFoundException, SQLException {
		int selectedIndex = productTable.getSelectionModel().getSelectedIndex();
		if (selectedIndex >= 0) {
			(new DbOperations()).deleteProduct(productTable.getItems().get(0).getSerialNo());
			productTable.getItems().remove(selectedIndex);
			productTable.refresh();
//			System.out.println(ProductTable.getItems().get(0));
			
		} else {
			// Nothing selected
			Dialogs.showWarningDialog(mainApp.getPrimaryStage(), "Please select a product in the table.",
					"No Product Selected", "No Selection");
		}
	}

}
