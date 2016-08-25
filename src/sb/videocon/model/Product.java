package sb.videocon.model;

public class Product {
	private String serialNo;
	private String productName;
	private String cost;
	private String category;
	private String brand;
	private String description;
	private String status;

	public Product() {
	}

	public Product(String serialNo, String productName, String cost, String category, String description,
			String brand) {
		this.serialNo = serialNo;
		this.productName = productName;
		this.cost = cost;
		this.category = category;
		this.brand = brand;
		this.description = description;
	}

	public String getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getCost() {
		return cost;
	}

	public void setCost(String cost) {
		this.cost = cost;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	
}
