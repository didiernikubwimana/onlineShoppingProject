package edu.mum.wap.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.mum.wap.dao.ProductDAO;
import edu.mum.wap.model.Product;

/**
 * Servlet implementation class ProductController
 */
@WebServlet("/ProductController")
public class ProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;

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
		String name = request.getParameter("name");
		double price = Double.parseDouble(request.getParameter("price"));
		Long category = Long.valueOf(request.getParameter("category"));
		Long availableQty = Long.valueOf(request.getParameter("qty"));
		String description = request.getParameter("description");
		String errorMessage = "";
		String color = "";

		Product product = new Product();
		product.setName(name);
		product.setPrice(price);
		product.setCategoryId(category);
		product.setAvailableQty(availableQty);

		String imageName = request.getParameter("file");
//		String filePath = UUID.randomUUID() + imageName;

		try {

			File sourceFile = new File("C:\\\\Users\\\\Mugenzi\\\\Desktop\\\\PROJECT_IMAGE\\\\" + imageName);
			String path = request.getServletContext().getRealPath(".") + "\\product-image\\";
//			String path = "D:\\CODES\\NewWorkplace\\wap-project\\product-image\\";
			File destinationFile = new File(path + imageName);
//			String contextPath = request.getContextPath();
			FileInputStream fileInputStream = new FileInputStream(sourceFile);
			FileOutputStream fileOutputStream = new FileOutputStream(destinationFile);

			int bufferSize;
			byte[] bufffer = new byte[512];
			while ((bufferSize = fileInputStream.read(bufffer)) > 0) {
				fileOutputStream.write(bufffer, 0, bufferSize);
			}
			fileInputStream.close();
			fileOutputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		product.setAvailableQty(availableQty);
		product.setDescription(description);
		product.setImage(imageName);

		ProductDAO dao = new ProductDAO();
		Product newObject = dao.create(product);
		if (newObject != null) {
			errorMessage = "Product Added Successfully";
			color = "style=\"color: green;\"";
		} else {
			errorMessage = "Add Product Fail, there is an issue data provided";
			color = "style=\"color: red;\"";
		}

		request.getSession().setAttribute("product", product);
		request.getSession().setAttribute("errorMessage", errorMessage);
		request.getSession().setAttribute("messageColor", color);

		RequestDispatcher view = request.getRequestDispatcher("/ProductView");
		view.forward(request, response);
	}

}
