package sb.videocon.model;

import java.util.Calendar;

import javafx.fxml.FXML;

public class Employee {
	private String firstName;
	private String lastName;
	private String mobileNo;
	private String address;
	private String dob;
	private String doj;
	
	public Employee(){
	}
	
	
	public Employee(String firstName, String lastName){
		this.firstName = firstName;
		this.lastName = lastName;
		
		//dummy
		this.mobileNo = "4567890";
		this.address = "dummyAddress";
	}
	
	public Employee(String firstName, String lastName, String mobileno, String address, String doj, String dob){
		this.firstName = firstName;
		this.lastName = lastName;
		this.mobileNo = mobileno;
		this.address = address;
		this.dob = dob;
		this.doj = doj;
	}
	
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}


	public String getDob() {
		return dob;
	}


	public void setDob(String dob) {
		this.dob = dob;
	}


	public String getDoj() {
		return doj;
	}


	public void setDoj(String doj) {
		this.doj = doj;
	}
	
	
	
}

