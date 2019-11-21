package edu.mum.wap.view;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.mum.wap.dao.ProductDAO;
import edu.mum.wap.model.Product;
import edu.mum.wap.util.ProjectConstant;

/**
 * Servlet implementation class IndexView
 */
@WebServlet(description = "IndexView", urlPatterns = { "/IndexView" })
public class IndexView extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ProductDAO productDao = new ProductDAO();
		List<Product> allProductList = productDao.findAll();
		request.getSession().setAttribute("allProductList", allProductList);
		response.sendRedirect(ProjectConstant.HOME_URL);
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
