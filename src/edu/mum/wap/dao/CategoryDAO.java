package edu.mum.wap.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.mum.wap.model.Category;
import edu.mum.wap.util.DBConnector;

public class CategoryDAO {

	public List<Category> findAll() {
		List<Category> list = new ArrayList<>();
		try {
			Connection connection = new DBConnector().getConnection();
			PreparedStatement pstmt = connection.prepareStatement("select * from Categories");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {

				// GET VALUE FROM RESULTSET
				Long id = rs.getLong("id");
				String name = rs.getString("name");
				String description = rs.getString("description");

				// CREATE AN OBJECT & ASSIGN VALUE FROM RESULTSET
				Category object = new Category();
				object.setId(id);
				object.setName(name);
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

	public Category findById(Long value) {
		Category object = null;
		try {
			object = new Category();
			Connection connection = new DBConnector().getConnection();
			PreparedStatement pstmt = connection.prepareStatement("select * from Categories where id = ?");
			pstmt.setLong(1, value);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				// GET VALUE FROM RESULTSET
				Long id = rs.getLong("id");
				String name = rs.getString("name");
				String description = rs.getString("description");

				// CREATE AN OBJECT & ASSIGN VALUE FROM RESULTSET
				object.setId(id);
				object.setName(name);
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

	public boolean create(Category category) {
		try {
			Connection connection = new DBConnector().getConnection();
			PreparedStatement pstmt = connection
					.prepareStatement("insert into Categories (name, description) values (?, ?)");
			pstmt.setString(1, category.getName());
			pstmt.setString(2, category.getDescription());
			pstmt.executeUpdate();
			pstmt.close(); 
			connection.close();
			return true;
		} catch (SQLException e) {
			System.err.println(e);
		}
		return false;
	}

	public boolean update(Category category) {
		try {
			Category object = findById(category.getId());
			if (object != null) {
				Connection connection = new DBConnector().getConnection();
				PreparedStatement pstmt = connection
						.prepareStatement("update Categories set name = ?, description = ? where id=?");
				pstmt.setString(1, object.getName());
				pstmt.setString(2, object.getDescription());
				pstmt.setLong(3, object.getId());
				pstmt.executeUpdate();
				pstmt.close(); 
				connection.close();
				return true;
			}
		} catch (SQLException e) {
			System.err.println(e);
		}
		return false;
	}

}
