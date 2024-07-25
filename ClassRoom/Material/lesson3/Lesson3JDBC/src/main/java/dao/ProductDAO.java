package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class ProductDAO {

	ProductDAO() {
		try {
			Class.forName("org.hsqldb.jdbcDriver");
		} catch (ClassNotFoundException e) {
			System.out
					.println("ClassNotFoundException in ProductDAO constructor :"
							+ e.getStackTrace());
		}
	}

	private Connection getConnection() throws SQLException {
		return DriverManager.getConnection(
				"jdbc:hsqldb:hsql://localhost/trainingdb", "sa", "");
	}

	public void closeConnection(Connection conn) {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				System.err.println("SQLCloseException in closeConnection(): "
						+ e);
			}
		}
	}

	public void save(Product product) {
		Connection conn = null;
		PreparedStatement prepareCreateProduct = null;
		try {
			conn = getConnection();
			prepareCreateProduct = conn.prepareStatement("INSERT INTO product VALUES(?,?,?)");
			prepareCreateProduct.setInt(1, product.getProductnumber());
			prepareCreateProduct.setString(2, product.getProductName());
			prepareCreateProduct.setDouble(3, product.getPrice());

			int updateResult = prepareCreateProduct.executeUpdate();
		} catch (SQLException ex) {
			System.out.println("SQLException in save(): " + ex);
		} finally {
			try {
				prepareCreateProduct.close();
				closeConnection(conn);
			} catch (SQLException ex) {

			}
		}
	}

	public void update(Product product) {
		Connection conn = null;
		PreparedStatement prepareUpdateProduct = null;
		try {
			conn = getConnection();
			prepareUpdateProduct = conn.prepareStatement("UPDATE product SET name = ?, price = ? WHERE number = ?");
			prepareUpdateProduct.setInt(3, product.getProductnumber());
			prepareUpdateProduct.setString(1, product.getProductName());
			prepareUpdateProduct.setDouble(2, product.getPrice());

			int updateResult = prepareUpdateProduct.executeUpdate();
		} catch (SQLException ex) {
			System.out.println("SQLException in update(): " + ex);
		} finally {
			try {
				prepareUpdateProduct.close();
				closeConnection(conn);
			} catch (SQLException ex) {

			}
		}
	}

	public Product load(int productNumber) {
		Connection conn = null;
		PreparedStatement prepareLoadProduct = null;
		ResultSet rs = null;
		Product product = null;
		try {
			conn = getConnection();
			prepareLoadProduct = conn.prepareStatement("SELECT * FROM product WHERE number = ?");
			prepareLoadProduct.setInt(1, productNumber);

			rs = prepareLoadProduct.executeQuery();
			while(rs.next()) {
				String name = rs.getString("name");
				double price = rs.getDouble("price");
				product = new Product(productNumber, name, price);
			}
		} catch (SQLException ex) {
			System.out.println("SQLException in delete(): " + ex);
		} finally {
			try {
				prepareLoadProduct.close();
				closeConnection(conn);
			} catch (SQLException ex) {

			}
		}
		return product;
	}

	public void delete(Product product) {
		Connection conn = null;
		PreparedStatement prepareDeleteProduct = null;
		try {
			conn = getConnection();
			prepareDeleteProduct = conn.prepareStatement("DELETE FROM product WHERE number = ?");
			prepareDeleteProduct.setInt(1, product.getProductnumber());

			int updateResult = prepareDeleteProduct.executeUpdate();
		} catch (SQLException ex) {
			System.out.println("SQLException in delete(): " + ex);
		} finally {
			try {
				prepareDeleteProduct.close();
				closeConnection(conn);
			} catch (SQLException ex) {

			}
		}
	}

	public Collection getAllProducts() {
		Collection productList = new ArrayList();
		Connection conn = null;
		PreparedStatement prepareLoadProduct = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			prepareLoadProduct = conn.prepareStatement("SELECT * FROM product");
			rs = prepareLoadProduct.executeQuery();
			while(rs.next()) {
				int productNumber = rs.getInt("number");
				String name = rs.getString("name");
				double price = rs.getDouble("price");
				Product product = new Product(productNumber, name, price);
				productList.add(product);
			}
		} catch (SQLException ex) {
			System.out.println("SQLException in delete(): " + ex);
		} finally {
			try {
				prepareLoadProduct.close();
				closeConnection(conn);
			} catch (SQLException ex) {

			}
		}
		return productList;
	}

}