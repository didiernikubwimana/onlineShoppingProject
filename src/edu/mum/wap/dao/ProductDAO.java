package edu.mum.wap.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import edu.mum.wap.model.Product;
import edu.mum.wap.util.DBConnector;

public class ProductDAO {

	public List<Product> findAll() {
		List<Product> list = new ArrayList<>();
		try {
			Connection connection = new DBConnector().getConnection();
			PreparedStatement pstmt = connection.prepareStatement("select * from products");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {

				// GET VALUE FROM RESULTSET
				Long id = rs.getLong("id");
				String name = rs.getString("name");
				double price = rs.getDouble("price");
				Long categoryId = rs.getLong("category_id");
				long availableQty = rs.getLong("available_qty");
				Long orderId = rs.getLong("order_id");
				String image = rs.getString("image");
				String description = rs.getString("description");

				// CREATE AN OBJECT & ASSIGN VALUE FROM RESULTSET
				Product object = new Product();
				object.setId(id);
				object.setName(name);
				object.setPrice(price);
				object.setCategoryId(categoryId);
				object.setAvailableQty(availableQty);
				object.setOrderId(orderId);
				object.setImage(image);
				object.setDescription(description);

				// ADD OBJECT TO THE RETURN LIST
				list.add(object);
			}
			pstmt.close();
			rs.close();
			connection.close();
		} catch (SQLException e) {
			System.err.println(e);
		}
		return list;
	}

	public Product findById(Long value) {
		Product object = null;
		try {
			object = new Product();
			Connection connection = new DBConnector().getConnection();
			PreparedStatement pstmt = connection.prepareStatement("select * from products where id = ?");
			pstmt.setLong(1, value);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				// GET VALUE FROM RESULTSET
				Long id = rs.getLong("id");
				String name = rs.getString("name");
				double price = rs.getDouble("price");
				Long categoryId = rs.getLong("category_id");
				long availableQty = rs.getLong("available_qty");
				Long orderId = rs.getLong("order_id");
				String image = rs.getString("image");
				String description = rs.getString("description");

				// CREATE AN OBJECT & ASSIGN VALUE FROM RESULTSET
				object.setId(id);
				object.setName(name);
				object.setPrice(price);
				object.setCategoryId(categoryId);
				object.setAvailableQty(availableQty);
				object.setOrderId(orderId);
				object.setImage(image);
				object.setDescription(description);

			}
			pstmt.close();
			rs.close();
			connection.close();
		} catch (SQLException e) {
			System.err.println(e);
		}
		return object;
	}

	public Product create(Product product) {
		Product newObject = null;
		try {
			newObject = new Product();
			Connection connection = new DBConnector().getConnection();
			PreparedStatement pstmt = connection.prepareStatement(
					"insert into products (name, price, category_id, available_qty, image, description) values (?, ?, ?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, product.getName());
			pstmt.setDouble(2, product.getPrice());
			pstmt.setLong(3, product.getCategoryId());
			pstmt.setLong(4, product.getAvailableQty());
			pstmt.setString(5, product.getImage());
			pstmt.setString(6, product.getDescription());

			ResultSet generatedKeys = pstmt.getGeneratedKeys();
			if (generatedKeys.next()) {
				newObject = product;
				newObject.setId(generatedKeys.getLong(1));
			}
			pstmt.executeUpdate();
			connection.close();
		} catch (SQLException e) {
			System.err.println(e);
		}
		return product;
	}

	public boolean update(Product product) {
		try {
			Product object = findById(product.getId());
			if (object != null) {
				Connection connection = new DBConnector().getConnection();
				PreparedStatement pstmt = connection.prepareStatement(
						"update products set name = ?, price = ?, category_id = ?, available_qty = ?, order_id = ?, image = ?, description = ? where id=?");
				pstmt.setString(1, product.getName());
				pstmt.setDouble(2, product.getPrice());
				pstmt.setLong(3, product.getCategoryId());
				pstmt.setLong(4, product.getAvailableQty());
				pstmt.setLong(5, product.getOrderId());
				pstmt.setString(6, product.getImage());
				pstmt.setString(7, product.getDescription());
				pstmt.setLong(8, object.getId());
				pstmt.executeUpdate();
				connection.close();
				return true;
			}
		} catch (SQLException e) {
			System.err.println(e);
		}
		return false;
	}

}
