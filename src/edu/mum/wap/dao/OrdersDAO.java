package edu.mum.wap.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import edu.mum.wap.model.EPaymentStatus;
import edu.mum.wap.model.Order;
import edu.mum.wap.util.DBConnector;

public class OrdersDAO {

	public List<Order> findAll() {
		List<Order> list = new ArrayList<>();
		try {
			Connection connection = new DBConnector().getConnection();
			PreparedStatement pstmt = connection.prepareStatement("select * from orders");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {

				// GET VALUE FROM RESULTSET
				Long id = rs.getLong("id");
				Long userId = rs.getLong("user_id");
				Long paymentId = rs.getLong("payment_id");
				double netAmount = rs.getDouble("net_amount");
				double taxAmount = rs.getDouble("tax_amount");
				double totalAmount = rs.getDouble("total_amount");
				EPaymentStatus status = EPaymentStatus.valueOf(rs.getString("status"));

				// CREATE AN OBJECT & ASSIGN VALUE FROM RESULTSET
				Order object = new Order();
				object.setId(id);
				object.setUserId(userId);
				object.setPaymentId(paymentId);
				object.setNetAmount(netAmount);
				object.setTaxAmount(taxAmount);
				object.setTotalAmount(totalAmount);
				object.setStatus(status);
				

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

	public Order findById(Long value) {
		Order object = null;
		try {
			object = new Order();
			Connection connection = new DBConnector().getConnection();
			PreparedStatement pstmt = connection.prepareStatement("select * from orders where id = ?");
			pstmt.setLong(1, value);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				// GET VALUE FROM RESULTSET
				Long id = rs.getLong("id");
				Long userId = rs.getLong("user_id");
				Long paymentId = rs.getLong("payment_id");
				double netAmount = rs.getDouble("net_amount");
				double taxAmount = rs.getDouble("tax_amount");
				double totalAmount = rs.getDouble("total_amount");
				EPaymentStatus status = EPaymentStatus.valueOf(rs.getString("status"));

				// CREATE AN OBJECT & ASSIGN VALUE FROM RESULTSET
				object.setId(id);
				object.setUserId(userId);
				object.setPaymentId(paymentId);
				object.setNetAmount(netAmount);
				object.setTaxAmount(taxAmount);
				object.setTotalAmount(totalAmount);
				object.setStatus(status);
			}
			pstmt.close();
			rs.close();
			connection.close();
		} catch (SQLException e) {
			System.err.println(e);
		}
		return object;
	}

	public Order create(Order order) {
		Order newObject = null;
		try {
			newObject = new Order();
			Connection connection = new DBConnector().getConnection();
			PreparedStatement pstmt = connection.prepareStatement(
					"insert into orders (user_id, payment_id, net_amount, tax_amount, total_amount, status) values (?, ?, ?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);
			pstmt.setLong(1, order.getUserId());
			pstmt.setDouble(2, order.getPaymentId());
			pstmt.setDouble(3, order.getNetAmount());
			pstmt.setDouble(4, order.getTaxAmount());
			pstmt.setDouble(5, order.getTotalAmount());
			pstmt.setString(6, order.getStatus().toString());
			pstmt.executeUpdate();

			ResultSet generatedKeys = pstmt.getGeneratedKeys();
			if (generatedKeys.next()) {
				newObject = order;
				newObject.setId(generatedKeys.getLong(1));
			}
			pstmt.close(); 
			connection.close();
		} catch (SQLException e) {
			System.err.println(e);
		}
		return newObject;
	}

	public boolean update(Order order) {
		try {
			Order object = findById(order.getId());
			if (object != null) {
				Connection connection = new DBConnector().getConnection();
				PreparedStatement pstmt = connection.prepareStatement(
						"update orders set user_id = ?, payment_id = ?, net_amount = ?, tax_amount = ?, total_amount = ?, status = ? where id=?");
				pstmt.setLong(1, order.getUserId());
				pstmt.setDouble(2, order.getPaymentId());
				pstmt.setDouble(3, order.getNetAmount());
				pstmt.setDouble(4, order.getTaxAmount());
				pstmt.setDouble(5, order.getTotalAmount());
				pstmt.setString(6, order.getStatus().toString());
				pstmt.setLong(7, order.getId());
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
