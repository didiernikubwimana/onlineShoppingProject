package edu.mum.wap.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.mum.wap.model.PaymentSimulator;
import edu.mum.wap.util.DBConnector;

public class PaymentSimulatorDAO {

	public List<PaymentSimulator> findAll() {
		List<PaymentSimulator> list = new ArrayList<>();
		try {
			Connection connection = new DBConnector().getConnection();
			PreparedStatement pstmt = connection.prepareStatement("select * from payment_simulator");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {

				// GET VALUE FROM RESULTSET
				Long id = rs.getLong("id");
				Long userId = rs.getLong("user_id");
				String cardNumber = rs.getString("card_number");
				String cardHolder = rs.getString("card_holder");
				int securityCode = rs.getInt("security_code");
				String expiryDate = rs.getString("expiry_date");
				String cardType = rs.getString("card_type");
				String cardProvider = rs.getString("card_provider");
				double balance = rs.getDouble("balance");

				// CREATE AN OBJECT & ASSIGN VALUE FROM RESULTSET
				PaymentSimulator object = new PaymentSimulator();
				object.setId(id);
				object.setUserId(userId);
				object.setCardNumber(cardNumber);
				object.setCardHolder(cardHolder);
				object.setSecurityCode(securityCode);
				object.setExpiryDate(expiryDate);
				object.setCardType(cardType);
				object.setCardProvider(cardProvider);
				object.setBalance(balance);

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

	public PaymentSimulator findById(Long value) {
		PaymentSimulator object = null;
		try {
			object = new PaymentSimulator();
			Connection connection = new DBConnector().getConnection();
			PreparedStatement pstmt = connection.prepareStatement("select * from payment_simulator where id = ?");
			pstmt.setLong(1, value);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				// GET VALUE FROM RESULTSET
				Long id = rs.getLong("id");
				Long userId = rs.getLong("user_id");
				String cardNumber = rs.getString("card_number");
				String cardHolder = rs.getString("card_holder");
				int securityCode = rs.getInt("security_code");
				String expiryDate = rs.getString("expiry_date");
				String cardType = rs.getString("card_type");
				String cardProvider = rs.getString("card_provider");
				double balance = rs.getDouble("balance");

				// CREATE AN OBJECT & ASSIGN VALUE FROM RESULTSET
				object.setId(id);
				object.setUserId(userId);
				object.setCardNumber(cardNumber);
				object.setCardHolder(cardHolder);
				object.setSecurityCode(securityCode);
				object.setExpiryDate(expiryDate);
				object.setCardType(cardType);
				object.setCardProvider(cardProvider);
				object.setBalance(balance);

			}
			pstmt.close();
			rs.close();
			connection.close();
		} catch (SQLException e) {
			System.err.println(e);
		}
		return object;
	}

	public PaymentSimulator findByUserId(Long value) {
		PaymentSimulator object = null;
		try {
			object = new PaymentSimulator();
			Connection connection = new DBConnector().getConnection();
			PreparedStatement pstmt = connection.prepareStatement("select * from payment_simulator where user_id = ?");
			pstmt.setLong(1, value);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				// GET VALUE FROM RESULTSET
				Long id = rs.getLong("id");
				Long userId = rs.getLong("user_id");
				String cardNumber = rs.getString("card_number");
				String cardHolder = rs.getString("card_holder");
				int securityCode = rs.getInt("security_code");
				String expiryDate = rs.getString("expiry_date");
				String cardType = rs.getString("card_type");
				String cardProvider = rs.getString("card_provider");
				double balance = rs.getDouble("balance");

				// CREATE AN OBJECT & ASSIGN VALUE FROM RESULTSET
				object.setId(id);
				object.setUserId(userId);
				object.setCardNumber(cardNumber);
				object.setCardHolder(cardHolder);
				object.setSecurityCode(securityCode);
				object.setExpiryDate(expiryDate);
				object.setCardType(cardType);
				object.setCardProvider(cardProvider);
				object.setBalance(balance);

			}
			pstmt.close();
			rs.close();
			connection.close();
		} catch (SQLException e) {
			System.err.println(e);
		}
		return object;
	}

	public boolean create(PaymentSimulator paymentSimulator) {
		try {
			Connection connection = new DBConnector().getConnection();
			PreparedStatement pstmt = connection.prepareStatement(
					"insert into payment_simulator (user_id, card_number, card_holder, security_code, expiry_date, card_type, card_provider, balance) values (?, ?, ?, ?, ?, ?, ?, ?)");
			pstmt.setLong(1, paymentSimulator.getUserId());
			pstmt.setString(2, paymentSimulator.getCardNumber());
			pstmt.setString(3, paymentSimulator.getCardHolder());
			pstmt.setDouble(4, paymentSimulator.getSecurityCode());
			pstmt.setString(5, paymentSimulator.getExpiryDate());
			pstmt.setString(6, paymentSimulator.getCardType());
			pstmt.setString(7, paymentSimulator.getCardProvider());
			pstmt.setDouble(8, paymentSimulator.getBalance());
			pstmt.executeUpdate();
			pstmt.close(); 
			connection.close();
			return true;
		} catch (SQLException e) {
			System.err.println(e);
		}
		return false;
	}

	public boolean update(PaymentSimulator paymentSimulator) {
		try {
			PaymentSimulator object = findById(paymentSimulator.getId());
			if (object != null) {
				Connection connection = new DBConnector().getConnection();
				PreparedStatement pstmt = connection.prepareStatement(
						"update payment_simulator set user_id = ?, card_number = ?, card_holder = ?, security_code = ?, expiry_date = ?, card_type = ?, card_provider = ?, balance = ? where id=?");
				pstmt.setLong(1, paymentSimulator.getUserId());
				pstmt.setString(2, paymentSimulator.getCardNumber());
				pstmt.setString(3, paymentSimulator.getCardHolder());
				pstmt.setDouble(4, paymentSimulator.getSecurityCode());
				pstmt.setString(5, paymentSimulator.getExpiryDate());
				pstmt.setString(6, paymentSimulator.getCardType());
				pstmt.setString(7, paymentSimulator.getCardProvider());
				pstmt.setDouble(8, paymentSimulator.getBalance());
				pstmt.setLong(9, paymentSimulator.getId());
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
