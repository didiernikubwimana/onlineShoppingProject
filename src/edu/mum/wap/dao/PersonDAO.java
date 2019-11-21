package edu.mum.wap.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import edu.mum.wap.model.EGander;
import edu.mum.wap.model.Person;
import edu.mum.wap.util.DBConnector;

public class PersonDAO {

	public List<Person> findAll() {
		List<Person> list = new ArrayList<>();
		try {
			Connection connection = new DBConnector().getConnection();
			PreparedStatement pstmt = connection.prepareStatement("select * from persons");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {

				// GET VALUE FROM RESULTSET
				Long id = rs.getLong("id");
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");
				String gender = rs.getString("gender");
				LocalDate dob = rs.getDate("dob").toLocalDate();
				String email = rs.getString("email");
				Long addressId = rs.getLong("address_id");

				// CREATE AN OBJECT & ASSIGN VALUE FROM RESULTSET
				Person object = new Person();
				object.setId(id);
				object.setFirstName(firstName);
				object.setLastName(lastName);
				object.setGender(EGander.valueOf(gender));
				object.setDob(dob);
				object.setEmail(email);
				object.setAddressId(addressId);

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

	public Person findById(Long value) {
		Person object = null;
		try {
			object = new Person();
			Connection connection = new DBConnector().getConnection();
			PreparedStatement pstmt = connection.prepareStatement("select * from persons where id = ?");
			pstmt.setLong(1, value);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				// GET VALUE FROM RESULTSET
				Long id = rs.getLong("id");
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");
				String gender = rs.getString("gender");
				LocalDate dob = rs.getDate("dob").toLocalDate();
				String email = rs.getString("email");
				Long addressId = rs.getLong("address_id");

				// CREATE AN OBJECT & ASSIGN VALUE FROM RESULTSET
				object.setId(id);
				object.setFirstName(firstName);
				object.setLastName(lastName);
				object.setGender(EGander.valueOf(gender));
				object.setDob(dob);
				object.setEmail(email);
				object.setAddressId(addressId);
			}
			pstmt.close();
			rs.close();
			connection.close();
		} catch (SQLException e) {
			System.err.println(e);
		}
		return object;
	}

	public Person create(Person person) {
		Person newObject = null;
		try {
			Connection connection = new DBConnector().getConnection();
			PreparedStatement pstmt = connection.prepareStatement(
					"insert into persons (first_name, last_name, gender, dob, email, address_id) values (?, ?, ?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, person.getFirstName());
			pstmt.setString(2, person.getLastName());
			pstmt.setString(3, person.getGender().toString());
			pstmt.setString(4, person.getDob().toString());
			pstmt.setString(5, person.getEmail());
			pstmt.setLong(6, person.getAddressId());
			pstmt.executeUpdate();

			ResultSet generatedKeys = pstmt.getGeneratedKeys();
			if (generatedKeys.next()) {
				newObject = person;
				newObject.setId(generatedKeys.getLong(1));
			}
			pstmt.close(); 
			connection.close();
		} catch (SQLException e) {
			System.err.println(e);
		}
		return newObject;
	}

	public boolean update(Person person) {
		try {
			Person object = findById(person.getId());
			if (object != null) {
				Connection connection = new DBConnector().getConnection();
				PreparedStatement pstmt = connection.prepareStatement(
						"update persons set first_name = ?, last_name = ?, gender = ?, dob = ?, email = ?, address_id = ? where id=?");
				pstmt.setString(1, object.getFirstName());
				pstmt.setString(2, object.getLastName());
				pstmt.setString(3, object.getGender().toString());
				pstmt.setString(4, object.getDob().toString());
				pstmt.setString(5, object.getEmail());
				pstmt.setLong(6, object.getAddressId());
				pstmt.setLong(7, object.getId());
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
