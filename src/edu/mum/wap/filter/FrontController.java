package edu.mum.wap.filter;

import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.mum.wap.dao.CategoryDAO;
import edu.mum.wap.dao.ProductDAO;
import edu.mum.wap.model.Category;
import edu.mum.wap.model.Product;
import edu.mum.wap.model.User;
import edu.mum.wap.util.ProjectConstant;

/**
 * Servlet Filter implementation class AuthenticationFilter
 */
@WebFilter(description = "/FrontController", urlPatterns = { "/*" })
public class FrontController implements Filter {

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		try {
			HttpServletRequest httpServletRequest = (HttpServletRequest) request;
			HttpServletResponse httpServletresponse = (HttpServletResponse) response;
			String URL = httpServletRequest.getRequestURI();
			if (URL != null && (URL.equals(ProjectConstant.PROJECT_URL) || URL.equals(ProjectConstant.HOME_URL)
					|| URL.equals(ProjectConstant.INDEX_VIEW) || URL.equals(ProjectConstant.LOGIN_VIEW)
					|| URL.equals(ProjectConstant.LOGIN_URL) || URL.equals(ProjectConstant.LOGIN_CONTROLLER)
					|| URL.equals(ProjectConstant.SIGN_UP_URL) || URL.equals(ProjectConstant.SIGN_UP_VIEW)
					|| URL.equals(ProjectConstant.SIGN_UP_CONTROLLER))) {
				ProductDAO productDao = new ProductDAO();
				List<Product> allProductList = productDao.findAll();
				httpServletRequest.getSession().setAttribute("allProductList", allProductList);
				CategoryDAO product = new CategoryDAO();
				List<Category> list = product.findAll();
				httpServletRequest.getSession().setAttribute("categories", list);
				chain.doFilter(request, response);
			} else {
				User user = (User) httpServletRequest.getSession().getAttribute("user");
				if (user == null) {
					httpServletresponse.sendRedirect(ProjectConstant.LOGIN_VIEW);
				} else {
					chain.doFilter(request, response);
				}
			}
		} catch (Exception e) {
			chain.doFilter(request, response);
		}
	}

}
