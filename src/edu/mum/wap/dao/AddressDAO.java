package edu.mum.wap.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import edu.mum.wap.model.Address;
import edu.mum.wap.util.DBConnector;

public class AddressDAO {

	public List<Address> findAll() {
		List<Address> list = new ArrayList<>();
		try {
			Connection connection = new DBConnector().getConnection();
			PreparedStatement pstmt = connection.prepareStatement("select * from Addresses");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {

				// GET VALUE FROM RESULTSET
				Long id = rs.getLong("id");
				String country = rs.getString("country");
				String state = rs.getString("state");
				String city = rs.getString("city");
				String street = rs.getString("street");
				String apartment = rs.getString("apartment");
				String zipCode = rs.getString("zip_code");

				// CREATE AN OBJECT & ASSIGN VALUE FROM RESULTSET
				Address object = new Address();
				object.setId(id);
				object.setCountry(country);
				object.setState(state);
				object.setCity(city);
				object.setStreet(street);
				object.setApartment(apartment);
				object.setZipCode(zipCode);

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

	public Address findById(Long value) {
		Address object = null;
		try {
			object = new Address();
			Connection connection = new DBConnector().getConnection();
			PreparedStatement pstmt = connection.prepareStatement("select * from Addresses where id = ?");
			pstmt.setLong(1, value);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				// GET VALUE FROM RESULTSET
				Long id = rs.getLong("id");
				String country = rs.getString("country");
				String state = rs.getString("state");
				String city = rs.getString("city");
				String street = rs.getString("street");
				String apartment = rs.getString("apartment");
				String zipCode = rs.getString("zip_code");

				// CREATE AN OBJECT & ASSIGN VALUE FROM RESULTSET
				object.setId(id);
				object.setCountry(country);
				object.setState(state);
				object.setCity(city);
				object.setStreet(street);
				object.setApartment(apartment);
				object.setZipCode(zipCode);
			}
			pstmt.close();
			rs.close();
			connection.close();
		} catch (SQLException e) {
			System.err.println(e);
		}
		return object;
	}

	public Address create(Address address) {
		Address newObject = null;
		try {
			Connection connection = new DBConnector().getConnection();
			PreparedStatement pstmt = connection.prepareStatement(
					"insert into Addresses (country, state, city, street, apartment, zip_code) values (?, ?, ?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, address.getCountry());
			pstmt.setString(2, address.getState());
			pstmt.setString(3, address.getCity());
			pstmt.setString(4, address.getStreet());
			pstmt.setString(5, address.getApartment());
			pstmt.setString(6, address.getZipCode());
			pstmt.executeUpdate();

			ResultSet generatedKeys = pstmt.getGeneratedKeys();
			if (generatedKeys.next()) {
				newObject = address;
				newObject.setId(generatedKeys.getLong(1));
			}
			pstmt.close();
			connection.close();
		} catch (SQLException e) {
			System.err.println(e);
		}
		return newObject;
	}

	public boolean update(Address address) {
		try {
			Address object = findById(address.getId());
			if (object != null) {
				Connection connection = new DBConnector().getConnection();
				PreparedStatement pstmt = connection.prepareStatement(
						"update addresses set country = ?, state = ?, city = ?, street = ?, apartment = ?, zip_code = ? where id=?");
				pstmt.setString(1, object.getCountry());
				pstmt.setString(2, object.getState());
				pstmt.setString(3, object.getCity());
				pstmt.setString(4, object.getStreet());
				pstmt.setString(5, object.getApartment());
				pstmt.setString(6, object.getZipCode());
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
