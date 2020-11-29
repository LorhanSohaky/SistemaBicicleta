package br.ufscar.dc.dsw.controller;

import br.ufscar.dc.dsw.dao.CustomerDAO;
import br.ufscar.dc.dsw.domain.Customer;
import br.ufscar.dc.dsw.erros.SemanticError;
import br.ufscar.dc.dsw.utils.DateParser;
import br.ufscar.dc.dsw.utils.HashPassword;
import br.ufscar.dc.dsw.validator.CustomerValidator;
import com.goterl.lazycode.lazysodium.exceptions.SodiumException;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = { "/customers/", "/customers/register", "/customers/login" })
public class CustomerController extends HttpServlet {

	private static final Logger logger = Logger.getLogger(CustomerController.class.getName());
	private static final long serialVersionUID = 1L;

	private CustomerDAO dao;

	@Override
	public void init() {
		dao = new CustomerDAO();
	}

	private String getAction(HttpServletRequest request) {
		String contextPath = request.getContextPath();
		int length = contextPath.length() + "/customers".length();

		String action = request.getRequestURI().substring(length);
		if (action == null) {
			action = "";
		}
		return action;
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String action = this.getAction(request);

			switch (action) {
				case "/register":
					register(request, response);
					break;
				case "/login":
					login(request, response);
					break;
				default:
					throw new Error("[POST] - Invalid path");
			}
		} catch (ServletException | IOException ex) {
			logger.log(Level.SEVERE, null, ex);
		}
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		String action = this.getAction(request);
		switch (action) {
			case "/":
				break;
			default:
				throw new Error("[GET] - Invalid path");
		}
	}

	private void register(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<String> errors = CustomerValidator.registerCustomerValidation(request);
		try {
			if (errors.size() > 0) {
				request.setAttribute("errors", errors);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/customers/register.jsp");
				dispatcher.forward(request, response);
				return;
			}

			String email = request.getParameter("email");
			String plainTextPassword = request.getParameter("password");
			String cpf = request.getParameter("cpf");
			String name = request.getParameter("name");
			String phone = request.getParameter("phone");
			String gender = request.getParameter("gender");
			String birthdateString = request.getParameter("birthdate");

			Date birthdate = DateParser.format(birthdateString, "dd/MM/yyyy");

			String salt = HashPassword.generateSalt();
			String password = HashPassword.hashPassword(plainTextPassword, salt);

			Customer customer = new Customer(email, password, salt, cpf, name, phone, gender, birthdate);

			customer = dao.insert(customer);
			customer.setPassword(null);
			customer.setSalt(null);

			request.setAttribute("customerData", customer);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/customers/home.jsp");
			dispatcher.forward(request, response);
		} catch (SodiumException | ParseException e) {
			logger.log(Level.SEVERE, e.getMessage(), e.getCause());
		}
	}

	private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<String> errors = CustomerValidator.loginCustomerValidation(request);
		try {
			if (errors.size() > 0) {
				request.setAttribute("errors", errors);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/customers/login.jsp");
				dispatcher.forward(request, response);
				return;
			}

			String email = request.getParameter("email");
			String password = request.getParameter("password");

			Customer customer = dao.findByEmail(email);

			if (!HashPassword.isSamePassword(password, customer.getSalt(), customer.getPassword())) {
				throw new SemanticError("Usuário ou senha inválida");
			}

			request.setAttribute("customerData", customer);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/customers/home.jsp");
			dispatcher.forward(request, response);
		} catch (SemanticError | SodiumException e) {
			if (e instanceof SemanticError) {
				errors.add(e.getMessage());
				request.setAttribute("errors", errors);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/customers/login.jsp");
				dispatcher.forward(request, response);
				logger.log(Level.WARNING, e.getMessage());
				return;
			}
			logger.log(Level.SEVERE, e.getMessage(), e.getCause());
		}
	}
}
