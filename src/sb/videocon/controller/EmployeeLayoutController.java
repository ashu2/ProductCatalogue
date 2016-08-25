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
import sb.videocon.model.Employee;

public class EmployeeLayoutController {
	private MainApp mainApp;
	private Stage dialogStage;

	@FXML
	private TableView<Employee> employeeTable;
	@FXML
	private TableColumn<Employee, String> firstNameColumn;
	@FXML
	private TableColumn<Employee, String> lastNameColumn;
	@FXML
	private Label firstNameLabel;
	@FXML
	private Label lastNameLabel;
	@FXML
	private Label mobileNoLabel;
	@FXML
	private Label addressLabel;
	@FXML
	private Label dojLabel;
	@FXML
	private Label dobLabel;
	//
	@FXML
	private void initialize() {
		firstNameColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("firstName"));
		lastNameColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("lastName"));
		employeeTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		employeeTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Employee>() {
			@Override
			public void changed(ObservableValue<? extends Employee> observable, Employee oldValue, Employee newValue) {
				showEmployeeDetails(newValue);
			}
		});
	}

	private void showEmployeeDetails(Employee employee) {
		firstNameLabel.setText(employee.getFirstName());
		lastNameLabel.setText(employee.getLastName());
		mobileNoLabel.setText(employee.getMobileNo());
		addressLabel.setText(employee.getAddress());
		dojLabel.setText(employee.getDoj());
		dobLabel.setText(employee.getDob());
		
	}

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
		employeeTable.setItems(mainApp.getEmployeeData());
	}

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	/* handler */

	@FXML
	private void handleNewButton() throws ClassNotFoundException, SQLException {
		Employee tempEmployee = new Employee();
		boolean okClicked = mainApp.showEmployeeEditDialog(tempEmployee);
		if (okClicked) {
			new DbOperations().addEmployee(tempEmployee);
			mainApp.getEmployeeData().add(tempEmployee);
			employeeTable.refresh();
		}
	}

	@FXML
	private void handleEditButton() {

	}

	@FXML
	private void handleDeleteButton() throws ClassNotFoundException, SQLException {
		int selectedIndex = employeeTable.getSelectionModel().getSelectedIndex();
		if (selectedIndex >= 0) {
			(new DbOperations()).deleteEmployee(employeeTable.getItems().get(0).getDob());
			employeeTable.getItems().remove(selectedIndex);
			employeeTable.refresh();
//			System.out.println(employeeTable.getItems().get(0));
			
		} else {
			// Nothing selected
			Dialogs.showWarningDialog(mainApp.getPrimaryStage(), "Please select a person in the table.",
					"No Person Selected", "No Selection");
		}
	}

}
