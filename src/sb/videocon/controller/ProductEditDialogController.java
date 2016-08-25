package sb.videocon.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Dialogs;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sb.videocon.model.Employee;
import sb.videocon.model.Product;

public class ProductEditDialogController {

	@FXML
	private TextField searialNoField;
	@FXML
	private TextField productNameField;
	@FXML
	private TextField costField;
	@FXML
	private TextField categoryField;
	@FXML
	private TextField brandField;
	@FXML
	private TextField descriptionField;

	private Stage dialogStage;
	private Product product;
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
	public void setProduct(Product product) {
		this.product = product;
/*
		firstNameField.setText(employee.getFirstName());
		lastNameField.setText(employee.getLastName());
		mobileNoField.setText(employee.getMobileNo());
		addressField.setText(employee.getAddress());
		dobField.setText(employee.getDob());
		dojField.setText(employee.getDoj());
*/	}

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
			product.setSerialNo(searialNoField.getText());
			product.setProductName(productNameField.getText());
			product.setBrand(brandField.getText());
			product.setCategory(categoryField.getText());
			product.setCost(costField.getText());
			product.setDescription(descriptionField.getText());
			okClicked = true;
			dialogStage.close();
		}
	}
	@FXML
	private void handleCancel() {
		dialogStage.close();
	}
	private boolean isInputValid() {
		String errorMessage = "";
/*
		if (searialNoField.getText() == null || searialNoField.getText().length() == 0) {
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
*/
		if (errorMessage.length() == 0) {
			return true;
		} else {
			// Show the error message
			Dialogs.showErrorDialog(dialogStage, errorMessage, "Please correct invalid fields", "Invalid Fields");
			return false;
		}
	}
}