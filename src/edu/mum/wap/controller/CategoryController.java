package edu.mum.wap.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.mum.wap.dao.CategoryDAO;
import edu.mum.wap.model.Category;

/**
 * Servlet implementation class CategoryController
 */
@WebServlet("/CategoryController")
public class CategoryController extends HttpServlet {
	private static final long serialVersionUID = 1L;

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
		String name = request.getParameter("name");
		String description = request.getParameter("description");
		String errorMessage = "";
		String color = "";

		Category category = new Category();
		category.setName(name);
		category.setDescription(description);

		CategoryDAO dao = new CategoryDAO();
		if (dao.create(category)) {
			errorMessage = "Category Added Successfully";
			color = "style=\"color: green;\"";
		} else {
			errorMessage = "Add Category Fail, there is an issue data provided";
			color = "style=\"color: red;\"";
		}

		request.getSession().setAttribute("name", name);
		request.getSession().setAttribute("description", description);
		request.getSession().setAttribute("errorMessage", errorMessage);
		request.getSession().setAttribute("messageColor", color);

		RequestDispatcher view = request.getRequestDispatcher("/CategoryView");
		view.forward(request, response);
	}

}
