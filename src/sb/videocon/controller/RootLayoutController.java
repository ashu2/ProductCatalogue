package sb.videocon.controller;

import java.sql.SQLException;

import javafx.fxml.FXML;
import sb.videocon.MainApp;
//sb.videocon.controller.RootLayoutController
public class RootLayoutController {
	private MainApp mainApp;
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}
	
	@FXML
	private void handleAddEmployee(){
		mainApp.showEmployee();
		
	}
	
	@FXML
	private void handleAddProduct(){
		mainApp.showProduct();
	}
	
	@FXML
	private void handleClose(){
		System.exit(0);
	}
	
	@FXML
	private void handleAssignProduct() throws ClassNotFoundException, SQLException{
		mainApp.showMainProductAssignedView();
	}
	@FXML
	private void handleHelp(){
		
	}
}
