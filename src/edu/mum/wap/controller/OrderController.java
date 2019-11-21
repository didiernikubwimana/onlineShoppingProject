package edu.mum.wap.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.mum.wap.dao.OrdersDAO;
import edu.mum.wap.dao.ProductDAO;
import edu.mum.wap.model.EPaymentStatus;
import edu.mum.wap.model.Order;
import edu.mum.wap.model.PaymentSimulator;
import edu.mum.wap.model.Product;
import edu.mum.wap.model.User;
import edu.mum.wap.util.CartDTO;

/**
 * Servlet implementation class OrderController
 */
@WebServlet("/OrderController")
public class OrderController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher view = request.getRequestDispatcher("checkout.jsp");
		List<CartDTO> cartItems = (List<CartDTO>) request.getSession().getAttribute("cartItems");
		double netAmount = (Double) request.getSession().getAttribute("cartTotal");
		User user = (User) request.getSession().getAttribute("user");
		PaymentSimulator payment = (PaymentSimulator) request.getSession().getAttribute("payment");
		Order order = new Order();
		order.setUserId(user.getId());
		order.setPaymentId(payment.getId());
		order.setNetAmount(netAmount);
		order.setTaxAmount(0);
		order.setTotalAmount(netAmount);
		order.setStatus(EPaymentStatus.PAID);
		OrdersDAO orderDAO = new OrdersDAO();
		Order newOrder = orderDAO.create(order);
		if (newOrder != null) {
			for (CartDTO c : cartItems) {
				ProductDAO productDAO = new ProductDAO();
				Product p = productDAO.findById(c.getProduct().getId());
				p.setOrderId(order.getId());
				productDAO.update(p);
			}
		} else {
			view = request.getRequestDispatcher("checkout.jsp");
		}
		request.getSession().setAttribute("cartItems", new ArrayList<>());
		request.getSession().setAttribute("cartTotal", 0);
		request.getSession().setAttribute("successMessage", "Order Placed Successfully");
		view.forward(request, response);
	}

}
