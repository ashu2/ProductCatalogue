package sb.videocon.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import sb.videocon.model.Employee;
import sb.videocon.model.Product;

public class DbOperations {
	public void addEmployee(Employee emp) throws ClassNotFoundException, SQLException {
		// ConnectionTest.getConnection()
		String str = "insert into ms_employee(firstName, lastname, mobileno, address, doj, dob) values("
				+ "?,?,?,?,?,?)";
		/*
		 * + +" , "+ emp.getLastName() +" , "+ emp.getMobileNo() +" , " +
		 * emp.getAddress() +" , "+ emp.getDoj() +" , "+ emp.getDob() + " )");
		 */
		PreparedStatement st = ConnectionTest.getConnection().prepareStatement(str);
		st.setString(1, emp.getFirstName());
		st.setString(2, emp.getLastName());
		st.setString(3, emp.getMobileNo());
		st.setString(4, emp.getAddress());
		st.setString(5, emp.getDoj());
		st.setString(6, emp.getDob());

		st.executeUpdate();
	}

	public void deleteEmployee(String dob) throws ClassNotFoundException, SQLException {
		// ConnectionTest.getConnection()
		String str = "delete from ms_employee where dob = ?";
		PreparedStatement pst = ConnectionTest.getConnection().prepareStatement(str);
		pst.setString(1, dob);
		pst.execute();
	}

	public List<Employee> getEmployeeFromDB() throws ClassNotFoundException, SQLException {
		List<Employee> listEmp = new ArrayList<>();
		Statement st = ConnectionTest.getConnection().createStatement();
		ResultSet rs = st.executeQuery("SELECT * FROM ms_employee");
		while (rs.next()) {
			// System.out.print("Column 1 returned ");
			// System.out.println(rs.getString(1));
			listEmp.add(new Employee(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
					rs.getString(5), rs.getString(6)));

		}
		rs.close();
		st.close();
		return listEmp;

	}

	/* Product */

	public void addProduct(Product prod) throws ClassNotFoundException, SQLException {
		// ConnectionTest.getConnection()
		/*
		 * serialNo character varying(100), pName character varying(100), pCost
		 * character varying(100), Category character varying(1000), Brand
		 * character varying(1000), Description character varying(2000)
		 */
		String str = "insert into ms_product(serialNo, pName, pCost, Category, Brand, Description, status) values("
				+ "?,?,?,?,?,?,?)";
		PreparedStatement st = ConnectionTest.getConnection().prepareStatement(str);
		st.setString(1, prod.getSerialNo());
		st.setString(2, prod.getProductName());
		st.setString(3, prod.getCost());
		st.setString(4, prod.getCategory());
		st.setString(5, prod.getCategory());
		st.setString(6, prod.getDescription());
		st.setString(7, "freshArrival");

		st.executeUpdate();
	}

	public void deleteProduct(String serailNo) throws ClassNotFoundException, SQLException {
		// ConnectionTest.getConnection()
		String str = "delete from ms_product where serialNo = ?";
		PreparedStatement pst = ConnectionTest.getConnection().prepareStatement(str);
		pst.setString(1, serailNo);
		pst.execute();
	}

	public List<Product> getProductFromDB() throws ClassNotFoundException, SQLException {
		List<Product> listProd = new ArrayList<>();
		Statement st = ConnectionTest.getConnection().createStatement();
		ResultSet rs = st.executeQuery("SELECT * FROM ms_product");
		while (rs.next()) {
			listProd.add(new Product(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
					rs.getString(5), rs.getString(6)));

		}
		rs.close();
		st.close();
		return listProd;

	}

	public List<Product> getFreshArrivalProductFromDB() throws ClassNotFoundException, SQLException {
		List<Product> listProd = new ArrayList<>();
		Statement st = ConnectionTest.getConnection().createStatement();
		ResultSet rs = st.executeQuery("SELECT * FROM ms_product where status = 'freshArrival'");
		while (rs.next()) {
			listProd.add(new Product(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
					rs.getString(5), rs.getString(6)));

		}
		rs.close();
		st.close();
		return listProd;

	}

	public List<Product> getDefectedProductFromDB() throws ClassNotFoundException, SQLException {
		List<Product> listProd = new ArrayList<>();
		Statement st = ConnectionTest.getConnection().createStatement();
		ResultSet rs = st.executeQuery("SELECT * FROM ms_product where status = 'defected'");
		while (rs.next()) {
			listProd.add(new Product(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
					rs.getString(5), rs.getString(6)));

		}
		rs.close();
		st.close();
		return listProd;

	}

	public List<Product> getSoldOutProductFromDB() throws ClassNotFoundException, SQLException {
		List<Product> listProd = new ArrayList<>();
		Statement st = ConnectionTest.getConnection().createStatement();
		ResultSet rs = st.executeQuery("SELECT * FROM ms_product where status = 'soldOut'");
		while (rs.next()) {
			listProd.add(new Product(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
					rs.getString(5), rs.getString(6)));

		}
		System.out.println(listProd.size());
		rs.close();
		st.close();
		return listProd;

	}

	public List<Product> getDispatchedProductFromDB() throws ClassNotFoundException, SQLException {
		List<Product> listProd = new ArrayList<>();
		Statement st = ConnectionTest.getConnection().createStatement();
		ResultSet rs = st.executeQuery("SELECT * FROM ms_product where status = 'dispatched'");
		while (rs.next()) {
			listProd.add(new Product(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
					rs.getString(5), rs.getString(6)));

		}
		rs.close();
		st.close();
		return listProd;

	}

	public void sellProduct(Product prod) throws ClassNotFoundException, SQLException {
		// ConnectionTest.getConnection()
		String str = "UPDATE  ms_product set status='soldOut' " + " where serialNo=?";

		PreparedStatement st = ConnectionTest.getConnection().prepareStatement(str);
		st.setString(1, prod.getSerialNo());
		st.executeUpdate();
	}
	
	public void assignProduct(Product prod, String empName) throws ClassNotFoundException, SQLException {
		// ConnectionTest.getConnection()
		String str = "UPDATE  ms_product set status='dispatched' " + " where serialNo=?";

		PreparedStatement st = ConnectionTest.getConnection().prepareStatement(str);
		st.setString(1, prod.getSerialNo());
		st.executeUpdate();
	}

	
}
