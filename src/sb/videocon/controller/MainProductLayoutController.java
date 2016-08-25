package sb.videocon.controller;

import java.sql.SQLException;

import javafx.fxml.FXML;
import javafx.scene.control.Dialogs;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sb.videocon.MainApp;
import sb.videocon.db.DbOperations;
import sb.videocon.model.Product;

public class MainProductLayoutController {

	private MainApp mainApp;
	/* FRESH ARRIVAL */
	@FXML
	private TableView<Product> productFreshArrivalTable;
	@FXML
	private TableColumn<Product, String> serialNoFAColumn;
	@FXML
	private TableColumn<Product, String> pNameFAColumn;
	@FXML
	private TableColumn<Product, String> costFAColumn;
	@FXML
	private TableColumn<Product, String> brandFAColumn;
	@FXML
	private TableColumn<Product, String> categoryFAColumn;

	/* DEFECTED */
	@FXML
	private TableView<Product> productDefectedTable;
	@FXML
	private TableColumn<Product, String> serialNoDFColumn;
	@FXML
	private TableColumn<Product, String> pNameDFColumn;
	@FXML
	private TableColumn<Product, String> costDFColumn;
	@FXML
	private TableColumn<Product, String> brandDFColumn;
	@FXML
	private TableColumn<Product, String> categoryDFColumn;

	/* SOLD OUT */
	@FXML
	private TableView<Product> productSoldOutTable;
	@FXML
	private TableColumn<Product, String> serialNoSOColumn;
	@FXML
	private TableColumn<Product, String> pNameSOColumn;
	@FXML
	private TableColumn<Product, String> costSOColumn;
	@FXML
	private TableColumn<Product, String> brandSOColumn;
	@FXML
	private TableColumn<Product, String> categorySOColumn;

	/* DISPATCHED */
	@FXML
	private TableView<Product> productDispatchedTable;
	@FXML
	private TableColumn<Product, String> serialNoDPColumn;
	@FXML
	private TableColumn<Product, String> pNameDPColumn;
	@FXML
	private TableColumn<Product, String> costDPColumn;
	@FXML
	private TableColumn<Product, String> brandDPColumn;
	@FXML
	private TableColumn<Product, String> categoryDPColumn;

	@FXML
	private void initialize() throws ClassNotFoundException, SQLException {
		serialNoFAColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("serialNo"));
		pNameFAColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("productName"));
		costFAColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("cost"));
		brandFAColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("brand"));
		categoryFAColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("category"));
		productFreshArrivalTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		
		serialNoDPColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("serialNo"));
		pNameDPColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("productName"));
		costDPColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("cost"));
		brandDPColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("brand"));
		categoryDPColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("category"));
		productDispatchedTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		
		serialNoDFColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("serialNo"));
		pNameDFColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("productName"));
		costDFColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("cost"));
		brandDFColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("brand"));
		categoryDFColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("category"));
		productDefectedTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		
		serialNoSOColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("serialNo"));
		pNameSOColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("productName"));
		costSOColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("cost"));
		brandSOColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("brand"));
		categorySOColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("category"));
		productSoldOutTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		
		
	}

	public void setMainApp(MainApp mainApp) throws ClassNotFoundException, SQLException {
		try {
			this.mainApp = mainApp;
			productFreshArrivalTable.setItems(mainApp.getProductFreshArrivalData());
			productDefectedTable.setItems(mainApp.getProductFreshArrivalData());
			productDispatchedTable.setItems(mainApp.getProductFreshArrivalData());
			productSoldOutTable.setItems(mainApp.getProductFreshArrivalData());

//			productDefectedTable.setItems(mainApp.getProductDefectedData());
//			productDispatchedTable.setItems(mainApp.getProductDispatchedData());
//			productSoldOutTable.setItems(mainApp.getProductSoldOutData());
			System.out.println(productSoldOutTable.getItems());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@FXML
	public void handleAssignProduct() throws ClassNotFoundException, SQLException {
		int selectedIndex = productFreshArrivalTable.getSelectionModel().getSelectedIndex();
		if (selectedIndex >= 0) {
			(new DbOperations()).assignProduct(productFreshArrivalTable.getItems().get(0), "Raman");
			productFreshArrivalTable.getItems().remove(selectedIndex);
			productFreshArrivalTable.refresh();

		} else {
			// Nothing selected
			Dialogs.showWarningDialog(mainApp.getPrimaryStage(), "Please select a product in the table.",
					"No Product Selected", "No Selection");
		}
	}

	@FXML
	public void handleSellProduct() throws ClassNotFoundException, SQLException {
		int selectedIndex = productFreshArrivalTable.getSelectionModel().getSelectedIndex();
		if (selectedIndex >= 0) {
			(new DbOperations()).sellProduct(productFreshArrivalTable.getItems().get(0));
			productFreshArrivalTable.getItems().remove(selectedIndex);
			productFreshArrivalTable.refresh();

		} else {
			// Nothing selected
			Dialogs.showWarningDialog(mainApp.getPrimaryStage(), "Please select a product in the table.",
					"No Product Selected", "No Selection");
		}
	}

}
