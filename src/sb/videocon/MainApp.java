package sb.videocon;

import java.io.IOException;
import java.sql.SQLException;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sb.videocon.controller.EmployeeEditDialogController;
import sb.videocon.controller.EmployeeLayoutController;
import sb.videocon.controller.MainProductLayoutController;
import sb.videocon.controller.ProductEditDialogController;
import sb.videocon.controller.ProductLayoutController;
import sb.videocon.controller.RootLayoutController;
import sb.videocon.db.ConnectionTest;
import sb.videocon.db.DbOperations;
import sb.videocon.model.Employee;
import sb.videocon.model.Product;

public class MainApp extends Application {
	private Stage primaryStage;
	private BorderPane rootLayout;

	public static void main(String[] args) {
		launch(args);
	}

	public Stage getPrimaryStage() {
		return primaryStage;
	}

	/* Observable Lists */
	private ObservableList<Employee> employeeData = FXCollections.observableArrayList();
	private ObservableList<Product> productData = FXCollections.observableArrayList();

	private ObservableList<Product> productFreshArrivalData = FXCollections.observableArrayList();
	private ObservableList<Product> productDefectedData = FXCollections.observableArrayList();
	private ObservableList<Product> productSoldOutData = FXCollections.observableArrayList();
	private ObservableList<Product> productDispatchedData = FXCollections.observableArrayList();

	public MainApp() throws ClassNotFoundException, SQLException {
		ConnectionTest conObj = new ConnectionTest();
		employeeData = FXCollections.observableArrayList(new DbOperations().getEmployeeFromDB());

	}

	public ObservableList<Employee> getEmployeeData() {
		return employeeData;
	}

	public ObservableList<Product> getProductData() {
		return productData;
	}

	public ObservableList<Product> getProductFreshArrivalData() throws ClassNotFoundException, SQLException {
		productFreshArrivalData = FXCollections.observableArrayList(new DbOperations().getFreshArrivalProductFromDB());
		return productFreshArrivalData;
	}

	public ObservableList<Product> getProductDefectedData() throws ClassNotFoundException, SQLException {
		productFreshArrivalData = FXCollections.observableArrayList(new DbOperations().getDefectedProductFromDB());	
		return productDefectedData;
	}

	public ObservableList<Product> getProductSoldOutData() throws ClassNotFoundException, SQLException {
		productFreshArrivalData = FXCollections.observableArrayList(new DbOperations().getSoldOutProductFromDB());	
		return productSoldOutData;
	}

	public ObservableList<Product> getProductDispatchedData() throws ClassNotFoundException, SQLException {
		productFreshArrivalData = FXCollections.observableArrayList(new DbOperations().getDispatchedProductFromDB());	
		return productDispatchedData;
	}

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Videocon");
		try {
			FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("view/RootLayout.fxml"));
			rootLayout = (BorderPane) loader.load();
			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			primaryStage.resizableProperty().setValue(Boolean.FALSE);
			RootLayoutController controller = loader.getController();
			controller.setMainApp(this);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public boolean showEmployee() {

		try {
			FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("view/EmployeeLayout.fxml"));
			AnchorPane page = (AnchorPane) loader.load();
			rootLayout.setCenter(page);
			EmployeeLayoutController controller = loader.getController();
			controller.setMainApp(this);
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}

	public boolean showProduct() {

		try {
			FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("view/ProductLayout.fxml"));
			AnchorPane page = (AnchorPane) loader.load();
			rootLayout.setCenter(page);
			ProductLayoutController controller = loader.getController();
			controller.setMainApp(this);
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}

	public boolean showMainProductAssignedView() throws ClassNotFoundException, SQLException {

		try {
			FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("view/MainProductLayout.fxml"));
			AnchorPane page = (AnchorPane) loader.load();
			rootLayout.setCenter(page);
			MainProductLayoutController controller = loader.getController();
			controller.setMainApp(this);
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}

	public boolean showEmployeeEditDialog(Employee employee) {
		try {
			// Load the fxml file and create a new stage for the popup
			FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("view/EmployeeEditDialog.fxml"));
			AnchorPane page = (AnchorPane) loader.load();
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Edit Person");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			// Set the person into the controller
			EmployeeEditDialogController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setEmployee(employee);

			// Show the dialog and wait until the user closes it
			dialogStage.showAndWait();

			return controller.isOkClicked();

		} catch (IOException e) {
			// Exception gets thrown if the fxml file could not be loaded
			e.printStackTrace();
			return false;
		}
	}

	public boolean showProductEditDialog(Product product) {
		try {
			// Load the fxml file and create a new stage for the popup
			FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("view/ProductEditDialog.fxml"));
			AnchorPane page = (AnchorPane) loader.load();
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Edit Product");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			// Set the person into the controller
			ProductEditDialogController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setProduct(product);

			// Show the dialog and wait until the user closes it
			dialogStage.showAndWait();

			return controller.isOkClicked();

		} catch (IOException e) {
			// Exception gets thrown if the fxml file could not be loaded
			e.printStackTrace();
			return false;
		}
	}

}