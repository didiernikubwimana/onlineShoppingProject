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

import edu.mum.wap.dao.ProductDAO;
import edu.mum.wap.model.Product;
import edu.mum.wap.util.CartDTO;

/**
 * Servlet implementation class CartController
 */
@WebServlet("/CartController")
public class CartController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	List<Product> shoppingCart = new ArrayList<>();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String URL = request.getParameter("cartURL");
		String action = request.getParameter("action");
		try {
			List<CartDTO> cartItems = (List<CartDTO>) request.getSession().getAttribute("cartItems");
			List<CartDTO> items = (List<CartDTO>) request.getSession().getAttribute("cartItems");
			if (cartItems == null) {
				cartItems = new ArrayList<>();
				items = new ArrayList<>();
			}
			ProductDAO productDAO = new ProductDAO();
			Long productId = Long.valueOf(request.getParameter("productId"));
			Product newProduct = productDAO.findById(productId);
			if (newProduct != null) {
				if (productExist(cartItems, newProduct)) {
					for (CartDTO c : cartItems) {
						Product p = c.getProduct();
						if (c.getProduct().getId() == newProduct.getId()) {
							int newQty = c.getQuantity();
							if (action.contentEquals("minus")) {
								newQty = c.getQuantity() - 1;
							}
							if (action.contentEquals("add")) {
								newQty = c.getQuantity() + 1;
							}
							if (newQty <= 0) {
								items.remove(c);
							}
							c.setQuantity(newQty);
							double totalAmount = newProduct.getPrice() * newQty;
							c.setTotalAmount(totalAmount);
						}
					}
				} else {
					cartItems.add(new CartDTO(newProduct, 1, newProduct.getPrice()));
				}
			} else {
				// ADD MESSAGE HERE TODO
			}
			cartItems = items;
			request.getSession().setAttribute("cartItems", cartItems);
			request.getSession().setAttribute("cartTotal", calculateTotal(cartItems));
		} catch (Exception e) {
			System.out.print(e);
		}

		RequestDispatcher view = request.getRequestDispatcher(URL);
		view.forward(request, response);
	}

	private boolean productExist(List<CartDTO> list, Product product) {
		for (CartDTO c : list) {
			if (c.getProduct().getId().equals(product.getId())) {
				return true;
			}
		}
		return false;
	}

	private double calculateTotal(List<CartDTO> list) {
		double total = 0;
		for (CartDTO c : list) {
			total += c.getTotalAmount();
		}
		return total;
	}
}
