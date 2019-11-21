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
import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;

import edu.mum.wap.dao.CategoryDAO;
import edu.mum.wap.dao.OrdersDAO;
import edu.mum.wap.dao.PaymentSimulatorDAO;
import edu.mum.wap.dao.PersonDAO;
import edu.mum.wap.dao.ProductDAO;
import edu.mum.wap.dao.UserDAO;
import edu.mum.wap.model.Category;
import edu.mum.wap.model.ERoleType;
import edu.mum.wap.model.Order;
import edu.mum.wap.model.PaymentSimulator;
import edu.mum.wap.model.Person;
import edu.mum.wap.model.Product;
import edu.mum.wap.model.User;
import edu.mum.wap.util.ProjectConstant;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			HttpSession session = request.getSession();
			UserDAO dao = new UserDAO();
			String username = request.getParameter("username");
			String password = request.getParameter("password");

			// String hashPass = BCrypt.hashpw(password, BCrypt.gensalt(12));
			// boolean isAuthenticated = BCrypt.checkpw(password, hashPass);

			String remember = request.getParameter("rememberMe");
			User user = dao.findByUserName(username);
			String usernameMessage = "";
			String passwordMessage = "";
			boolean login = false;
			if (user == null) {
				usernameMessage = "Invalid Username";
			} else {
				boolean rememberMe = user.isRememberMe();
				if (remember != null && remember.equals("on")) {
					rememberMe = true;
				}
				if (BCrypt.checkpw(password, user.getPassword())) {
					session.setAttribute("user", user);
					PaymentSimulatorDAO pdao = new PaymentSimulatorDAO();
					PaymentSimulator payment = pdao.findByUserId(user.getId());
					session.setAttribute("payment", payment);
					login = true;
					user.setRememberMe(rememberMe);
					dao.update(user);
				} else {
					passwordMessage = "Invalid Password";
				}
			}
			if (login) {
				ProductDAO productDao = new ProductDAO();
				List<Product> listProduct = productDao.findAll();
				request.getSession().setAttribute("listProduct", listProduct);
				if (user.getRole().equals(ERoleType.CLIENT)) {
					response.sendRedirect(ProjectConstant.INDEX_VIEW);
				}
				if (user.getRole().equals(ERoleType.ADMIN)) {
					OrdersDAO orderDao = new OrdersDAO();
					List<Order> listOrder = orderDao.findAll();
					request.getSession().setAttribute("listOrder", listOrder);

					UserDAO userDao = new UserDAO();
					List<User> listUser = userDao.findAll();
					request.getSession().setAttribute("listUser", listUser);

					PersonDAO personDao = new PersonDAO();
					List<Person> listPerson = personDao.findAll();
					request.getSession().setAttribute("listPerson", listPerson);

					CategoryDAO product = new CategoryDAO();
					List<Category> list = product.findAll();
					request.getSession().setAttribute("categories", list);

					RequestDispatcher view = request.getRequestDispatcher("/DashboardView");
					view.forward(request, response);
//				response.sendRedirect("admin/dashboard.jsp");
				}
			} else {
				request.setAttribute("usernameMessage", usernameMessage);
				request.setAttribute("passwordMessage", passwordMessage);
				request.setAttribute("username", username);
				RequestDispatcher view = request.getRequestDispatcher("login.jsp");
				view.forward(request, response);
			}

			List<Product> cartItems = (List<Product>) request.getSession().getAttribute("cartItems");
			if (cartItems == null) {
				cartItems = new ArrayList<>();
			}
			request.getSession().setAttribute("cartItems", cartItems);

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
