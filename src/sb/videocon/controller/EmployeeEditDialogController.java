package sb.videocon.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Dialogs;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sb.videocon.model.Employee;
import sb.videocon.util.CalendarUtil;

public class EmployeeEditDialogController {

	@FXML
	private TextField firstNameField;
	@FXML
	private TextField lastNameField;
	@FXML
	private TextField addressField;
	@FXML
	private TextField dobField;
	@FXML
	private TextField dojField;
	@FXML
	private TextField mobileNoField;

	private Stage dialogStage;
	private Employee employee;
	private boolean okClicked = false;

	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {

	}

	/**
	 * Sets the stage of this dialog.
	 * 
	 * @param dialogStage
	 */
	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	/**
	 * Sets the person to be edited in the dialog.
	 * 
	 * @param person
	 */
	public void setEmployee(Employee employee) {
		this.employee = employee;

		firstNameField.setText(employee.getFirstName());
		lastNameField.setText(employee.getLastName());
		mobileNoField.setText(employee.getMobileNo());
		addressField.setText(employee.getAddress());
		dobField.setText(employee.getDob());
		dojField.setText(employee.getDoj());
	}

	/**
	 * Returns true if the user clicked OK, false otherwise.
	 * 
	 * @return
	 */
	public boolean isOkClicked() {
		return okClicked;
	}

	/**
	 * Called when the user clicks ok.
	 */
	@FXML
	private void handleOk() {
		if (isInputValid()) {
			employee.setFirstName(firstNameField.getText());
			employee.setLastName(lastNameField.getText());
			employee.setAddress(addressField.getText());
			employee.setMobileNo(mobileNoField.getText());
			employee.setDob(dobField.getText());
			employee.setDoj(dojField.getText());
			okClicked = true;
			dialogStage.close();
		}
	}

	/**
	 * Called when the user clicks cancel.
	 */
	@FXML
	private void handleCancel() {
		dialogStage.close();
	}

	/**
	 * Validates the user input in the text fields.
	 * 
	 * @return true if the input is valid
	 */
	private boolean isInputValid() {
		String errorMessage = "";

		if (firstNameField.getText() == null || firstNameField.getText().length() == 0) {
			errorMessage += "No valid first name!\n";
		}
		if (lastNameField.getText() == null || lastNameField.getText().length() == 0) {
			errorMessage += "No valid last name!\n";
		}
		if (addressField.getText() == null || addressField.getText().length() == 0) {
			errorMessage += "No valid Address!\n";
		}

		if (dobField.getText() == null || dobField.getText().length() == 0) {
			errorMessage += "No valid birthday!\n";
		} else {}

		if (errorMessage.length() == 0) {
			return true;
		} else {
			// Show the error message
			Dialogs.showErrorDialog(dialogStage, errorMessage, "Please correct invalid fields", "Invalid Fields");
			return false;
		}
	}
}