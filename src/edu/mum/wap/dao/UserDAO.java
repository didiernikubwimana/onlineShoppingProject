package edu.mum.wap.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import edu.mum.wap.model.ERoleType;
import edu.mum.wap.model.User;
import edu.mum.wap.util.DBConnector;

public class UserDAO {

	public List<User> findAll() {
		List<User> list = new ArrayList<>();
		try {
			Connection connection = new DBConnector().getConnection();
			PreparedStatement pstmt = connection.prepareStatement("select * from users");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {

				// GET VALUE FROM RESULTSET
				Long id = rs.getLong("id");
				String userName = rs.getString("user_name");
				String password = rs.getString("password");
				boolean rememberMe = rs.getBoolean("remember_me");
				ERoleType role = ERoleType.valueOf(rs.getString("role"));
				Long person = rs.getLong("person_id");

				// CREATE AN OBJECT & ASSIGN VALUE FROM RESULTSET
				User object = new User();
				object.setId(id);
				object.setUserName(userName);
				object.setPassword(password);
				object.setRememberMe(rememberMe);
				object.setRole(role);
				object.setPersonId(person);

				// ADD OBJECT TO THE RETURN LIST
				list.add(object);

			}
			pstmt.close();
			rs.close();
			connection.close();
		} catch (SQLException e) {
			System.err.println(e);
		} finally {

		}
		return list;
	}

	public User findById(Long value) {
		User user = null;
		try {
			user = new User();
			Connection connection = new DBConnector().getConnection();
			PreparedStatement pstmt = connection.prepareStatement("select * from users where id = ?");
			pstmt.setLong(1, value);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				// GET VALUE FROM RESULTSET
				Long id = rs.getLong("id");
				String userName = rs.getString("user_name");
				String password = rs.getString("password");
				boolean rememberMe = rs.getBoolean("remember_me");
				ERoleType role = ERoleType.valueOf(rs.getString("role"));
				Long person = rs.getLong("person_id");

				// CREATE AN OBJECT & ASSIGN VALUE FROM RESULTSET
				user.setId(id);
				user.setUserName(userName);
				user.setPassword(password);
				user.setRememberMe(rememberMe);
				user.setRole(role);
				user.setPersonId(person);
			}
			pstmt.close();
			rs.close();
			connection.close();
		} catch (SQLException e) {
			System.err.println(e);
		}
		return user;
	}

	public User findByUserName(String value) {
		User user = null;
		try {
			Connection connection = new DBConnector().getConnection();
			PreparedStatement pstmt = connection.prepareStatement("select * from users where user_name = ?");
			pstmt.setString(1, value);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				user = new User();
				// GET VALUE FROM RESULTSET
				Long id = rs.getLong("id");
				String userName = rs.getString("user_name");
				String password = rs.getString("password");
				boolean rememberMe = rs.getBoolean("remember_me");
				ERoleType role = ERoleType.valueOf(rs.getString("role"));
				Long person = rs.getLong("person_id");

				// CREATE AN OBJECT & ASSIGN VALUE FROM RESULTSET
				user.setId(id);
				user.setUserName(userName);
				user.setPassword(password);
				user.setRememberMe(rememberMe);
				user.setRole(role);
				user.setPersonId(person);
			}
			pstmt.close();
			rs.close();
			connection.close();
		} catch (SQLException e) {
			System.err.println(e);
		}
		return user;
	}

	public User create(User user) {
		User newObject = null;
		try {
			Connection connection = new DBConnector().getConnection();
			PreparedStatement pstmt = connection.prepareStatement(
					"insert into users (user_name, password, remember_me, role, person_id) values (?, ?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, user.getUserName());
			pstmt.setString(2, user.getPassword());
			pstmt.setBoolean(3, user.isRememberMe());
			pstmt.setString(4, user.getRole().toString());
			pstmt.setLong(5, user.getPersonId());
			pstmt.executeUpdate();
			ResultSet generatedKeys = pstmt.getGeneratedKeys();
			if (generatedKeys.next()) {
				newObject = user;
				newObject.setId(generatedKeys.getLong(1));
			}
			pstmt.close();
			connection.close();
		} catch (SQLException e) {
			System.err.println(e);
		}
		return newObject;
	}

	public boolean update(User user) {
		try {
			User object = findById(user.getId());
			if (user != null) {
				Connection connection = new DBConnector().getConnection();
				PreparedStatement pstmt = connection.prepareStatement(
						"update users set user_name = ?, password = ?, remember_me = ?, role = ?, person_id = ? where id=?");
				pstmt.setString(1, user.getUserName());
				pstmt.setString(2, user.getPassword());
				pstmt.setBoolean(3, user.isRememberMe());
				pstmt.setString(4, user.getRole().toString());
				pstmt.setLong(5, user.getPersonId());
				pstmt.setLong(6, object.getId());
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
