package edu.mum.wap.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mindrot.jbcrypt.BCrypt;

import edu.mum.wap.dao.AddressDAO;
import edu.mum.wap.dao.PaymentSimulatorDAO;
import edu.mum.wap.dao.PersonDAO;
import edu.mum.wap.dao.UserDAO;
import edu.mum.wap.model.Address;
import edu.mum.wap.model.EGander;
import edu.mum.wap.model.ERoleType;
import edu.mum.wap.model.PaymentSimulator;
import edu.mum.wap.model.Person;
import edu.mum.wap.model.User;
import edu.mum.wap.util.ProjectConstant;

/**
 * Servlet implementation class SignUpController
 */
@WebServlet("/SignUpController")
public class SignUpController extends HttpServlet {
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

		UserDAO userDAO = new UserDAO();
		String email = request.getParameter("email");
		User user = userDAO.findByUserName(email);

		String emailError = "";
		String successMessage = "";
		String emailBorderColor = "";
		boolean notCreated = true;

		// GET ADDRESS PARAMETER FROM JSP PAGE
		String country = "USA";
		String state = request.getParameter("state");
		String city = request.getParameter("city");
		String street = request.getParameter("street");
		String apartment = request.getParameter("apartment");
		String zipCode = request.getParameter("zipCode");

		// ASSIGN VALUE TO PERSON OBJECT
		Address address = new Address();
		address.setCountry(country);
		address.setState(state);
		address.setCity(city);
		address.setStreet(street);
		address.setApartment(apartment);
		address.setZipCode(zipCode);

		// GET PERSON PARAMETER FROM JSP PAGE
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String gender = request.getParameter("gender");
		String maleSelected = "checked=\"checked\"";
		String femaleSelected = "";
		if (gender.equals("FEMALE")) {
			femaleSelected = "checked=\"checked\"";
			maleSelected = "";
		}
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate dob = LocalDate.parse(request.getParameter("dob"), formatter);
		String password = request.getParameter("password");

		// ASSIGN VALUE TO PERSON OBJECT
		Person person = new Person();
		person.setFirstName(firstName);
		person.setLastName(lastName);
		person.setGender(EGander.valueOf(gender));
		person.setDob(dob);
		person.setEmail(email);

		if (user != null) {
			emailError = "Email already exist in the system";
			successMessage = "Sign Up Fail, there is an issue in personal details";
			emailBorderColor = "style=\"border-color: red;\"";
		} else {

			// SAVE ADDRESS OBJECT
			AddressDAO addressDAO = new AddressDAO();
			Address newAddress = addressDAO.create(address);
			if (newAddress != null) {
				// SAVE PERSON OBJECT
				PersonDAO personDAO = new PersonDAO();
				person.setAddressId(newAddress.getId());
				Person newPerson = personDAO.create(person);
				if (newPerson != null) {
					User newUser = new User();
					newUser.setUserName(email);
					newUser.setPassword(BCrypt.hashpw(password, BCrypt.gensalt(12)));
					newUser.setRememberMe(false);
					newUser.setRole(ERoleType.CLIENT);
					newUser.setPersonId(newPerson.getId());

					User userObject = userDAO.create(newUser);
					if (userObject != null) {
						PaymentSimulator payment = new PaymentSimulator();
						payment.setUserId(userObject.getId());
						payment.setCardNumber("2561 2516 2738 1563");
						payment.setCardHolder(person.getFirstName() + " " + person.getLastName());
						payment.setSecurityCode(844);
						payment.setExpiryDate("09/45");
						payment.setCardType("CREDIT");
						payment.setCardProvider("VISA");
						payment.setBalance(20000);
						PaymentSimulatorDAO paymentDAO = new PaymentSimulatorDAO();
						paymentDAO.create(payment);
						notCreated = false;
						successMessage = "Account Created Successfuly";
						request.getSession().setAttribute("user", newUser);
						response.sendRedirect(ProjectConstant.INDEX_VIEW);
					} else {
						successMessage = "Sign Up Fail, there is an issue in user details";
					}
				} else {
					successMessage = "Sign Up Fail, there is an issue in personal details";
				}
			} else {
				successMessage = "Sign Up Fail, there is an issue in address fields";
			}
		}
		if (notCreated) {
			request.setAttribute("successMessage", successMessage);
			request.setAttribute("emailError", emailError);
			request.setAttribute("emailBorderColor", emailBorderColor);
			request.setAttribute("person", person);
			request.setAttribute("address", address);
			request.setAttribute("maleSelected", maleSelected);
			request.setAttribute("femaleSelected", femaleSelected);
			RequestDispatcher view = request.getRequestDispatcher("signup.jsp");
			view.forward(request, response);
		}
	}
}
