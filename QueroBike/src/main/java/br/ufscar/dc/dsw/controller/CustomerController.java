package br.ufscar.dc.dsw.controller;

import br.ufscar.dc.dsw.dao.CustomerDAO;
import br.ufscar.dc.dsw.domain.City;
import br.ufscar.dc.dsw.domain.Customer;
import br.ufscar.dc.dsw.erros.SemanticError;
import br.ufscar.dc.dsw.utils.DateParser;
import br.ufscar.dc.dsw.utils.HashPassword;
import br.ufscar.dc.dsw.validator.CustomerValidator;
import com.goterl.lazycode.lazysodium.LazySodiumJava;
import com.goterl.lazycode.lazysodium.SodiumJava;
import com.goterl.lazycode.lazysodium.exceptions.SodiumException;
import com.goterl.lazycode.lazysodium.interfaces.PwHash;
import com.sun.jna.NativeLong;
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



@WebServlet(urlPatterns = { "/customers/", "/customers/register" })
public class CustomerController extends HttpServlet {
  private static final Logger logger = Logger.getLogger(
    CustomerController.class.getName()
  );
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
  protected void doPost(
    HttpServletRequest request,
    HttpServletResponse response
  )
    throws ServletException, IOException {
    try {
      String action = this.getAction(request);

      switch (action) {
        case "/register":
          register(request, response);
          break;
        default:
          throw new Error("Invalid path");
      }
    } catch (SodiumException | ServletException | IOException ex) {
      Logger
        .getLogger(CustomerController.class.getName())
        .log(Level.SEVERE, null, ex);
    }
  }

  @Override
  protected void doGet(
    HttpServletRequest request,
    HttpServletResponse response
  ) {
    String action = this.getAction(request);
    switch (action) {
      case "/":
        //list(request, response);
        break;
      default:
        throw new Error("Invalid path");
    }
  }

  private void register(
    HttpServletRequest request,
    HttpServletResponse response
  )
    throws ServletException, IOException, SodiumException {
    String contextPath = request.getContextPath();

    List<String> errors = CustomerValidator.registerCustomerValidation(request);

    if (errors.size() > 0) {
      request.setAttribute("errors", errors);
      RequestDispatcher dispatcher = request.getRequestDispatcher(
        "/customers/register.jsp"
      );
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

    try {
      Date birthdate = DateParser.format(birthdateString, "dd/MM/yyyy");

      String salt = HashPassword.generateSalt();
      String password = HashPassword.hashPassword(plainTextPassword, salt);

      Customer customer = new Customer(
        email,
        password,
        salt,
        cpf,
        name,
        phone,
        gender,
        birthdate
      );

      customer = dao.insert(customer);
      customer.setPassword(null);
      customer.setSalt(null);
      
      request.setAttribute("customerData", customer);
        RequestDispatcher dispatcher = request.getRequestDispatcher(
          "/customers/home.jsp"
        );
        dispatcher.forward(request, response);
    } catch (ParseException | SemanticError e) {
      if (e instanceof SemanticError) {
        errors.add(e.getMessage());
        request.setAttribute("errors", errors);
        RequestDispatcher dispatcher = request.getRequestDispatcher(
          "/customers/register.jsp"
        );
        dispatcher.forward(request, response);
        logger.log(Level.WARNING, e.getMessage());
        return;
      }
      logger.log(Level.SEVERE, e.getMessage(), e.getCause());
    }
  }
}
