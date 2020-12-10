package br.ufscar.dc.dsw.controller;

import br.ufscar.dc.dsw.dao.CustomerDAO;
import br.ufscar.dc.dsw.dao.ReserveDAO;
import br.ufscar.dc.dsw.domain.Customer;
import br.ufscar.dc.dsw.domain.Reserve;
import br.ufscar.dc.dsw.erros.SemanticError;
import br.ufscar.dc.dsw.utils.DateParser;
import br.ufscar.dc.dsw.utils.ErrorList;
import br.ufscar.dc.dsw.utils.HashPassword;
import br.ufscar.dc.dsw.validator.CustomerValidator;
import com.goterl.lazycode.lazysodium.exceptions.SodiumException;
import java.io.IOException;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns = {"/customers/", "/customers/register", "/customers/login", "/customers/logout", "/customers/delete", "/customers/update"})
public class CustomerController extends HttpServlet {

    private static final Logger logger = Logger.getLogger(CustomerController.class.getName());
    private static final long serialVersionUID = 1L;
    private static final List<String> privateRoutes = Arrays.asList("/", "/update", "/delete");
    private String contextPath = "";

    private CustomerDAO customerDAO;
    private ReserveDAO reserveDAO;

    @Override
    public void init() {
        customerDAO = new CustomerDAO();
        reserveDAO = new ReserveDAO();
    }

    private String getAction(HttpServletRequest request) {
        this.contextPath = request.getContextPath();
        int length = this.contextPath.length() + "/customers".length();

        String action = request.getRequestURI().substring(length);
        if (action == null) {
            action = "";
        }
        return action;
    }

    private boolean hasAValidSession(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();

        return session.getAttribute("customerData") != null;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String action = this.getAction(request);

            if (privateRoutes.contains(action) && !hasAValidSession(request, response)) {
                response.sendRedirect(this.contextPath + "/customers/login");
                return;
            }

            switch (action) {
                case "/register":
                    register(request, response);
                    break;
                case "/login":
                    login(request, response);
                    break;
                case "/delete":
                    delete(request, response);
                    break;
                case "/update":
                    update(request, response);
                    break;
                default:
                    throw new Error("[POST] - CustomerController Invalid path");
            }
        } catch (ServletException | IOException ex) {
            logger.log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            String action = this.getAction(request);

            if (privateRoutes.contains(action) && !hasAValidSession(request, response)) {
                response.sendRedirect(this.contextPath + "/customers/login");
                return;
            }

            switch (action) {
                case "/":
                    renderHome(request, response);
                    break;
                case "/register":
                    renderPage("/customers/register.jsp", request, response);
                    break;
                case "/login":
                    renderPage("/customers/login.jsp", request, response);
                    break;
                case "/delete":
                    renderPage("/customers/delete.jsp", request, response);
                    break;
                case "/update":
                    renderUpdate(request, response);
                    break;
                case "/logout":
                    logout(request, response);
                    break;
                default:
                    throw new Error("[GET] - CustomerController Invalid path");
            }
        } catch (ServletException | IOException ex) {
            logger.log(Level.SEVERE, null, ex);
        }
    }

    private void renderUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, IOException {
        request.setAttribute("data", request.getSession().getAttribute("customerData"));
        renderPage("/customers/update.jsp", request, response);
    }

    private void renderHome(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, IOException {
        Customer customer = (Customer) request.getSession().getAttribute("customerData");

        List<Reserve> list = reserveDAO.listReserveFrom(customer);
        request.setAttribute("reservesList", list);
        renderPage("/customers/home.jsp", request, response);
    }

    private void renderPage(String page, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher(page);
        dispatcher.forward(request, response);
    }

    private void register(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ErrorList errors = CustomerValidator.registerCustomerValidation(request);

        String email = request.getParameter("email");
        String plainTextPassword = request.getParameter("password");
        String cpf = request.getParameter("cpf");
        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        String gender = request.getParameter("gender");
        String birthdateString = request.getParameter("birthdate");

        Map<String, String> fields = new HashMap<String, String>();
        fields.put("email", email);
        fields.put("cpf", cpf);
        fields.put("name", name);
        fields.put("phone", phone);
        fields.put("gender", gender);
        fields.put("birthdate", birthdateString);
        try {
            if (errors.isNotEmpty()) {
                request.setAttribute("data", fields);
                request.setAttribute("errorList", errors);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/customers/register.jsp");
                dispatcher.forward(request, response);
                return;
            }

            Date birthdate = DateParser.format(birthdateString, "yyyy-MM-dd");

            String salt = HashPassword.generateSalt();
            String password = HashPassword.hashPassword(plainTextPassword, salt);

            Customer customer = new Customer(email, password, salt, cpf, name, phone, gender, birthdate);

            customer = customerDAO.insert(customer);
            customer.setPassword(null);
            customer.setSalt(null);

            request.getSession().setAttribute("customerData", customer);
            response.sendRedirect(this.contextPath + "/customers/");
        } catch (SemanticError | SodiumException | ParseException e) {
            if (e instanceof SemanticError) {
                errors.add(e.getMessage());
                request.setAttribute("errorList", errors);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/customers/register.jsp");
                dispatcher.forward(request, response);
                logger.log(Level.WARNING, e.getMessage());
                return;
            }
            logger.log(Level.SEVERE, e.getMessage(), e.getCause());
            throw new RuntimeException(e);
        }
    }

    private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ErrorList errors = CustomerValidator.loginCustomerValidation(request);
        try {
            if (errors.isNotEmpty()) {
                request.setAttribute("errorList", errors);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/customers/login.jsp");
                dispatcher.forward(request, response);
                return;
            }

            String email = request.getParameter("email");
            String password = request.getParameter("password");

            Customer customer = customerDAO.findByEmail(email);

            if (!HashPassword.isSamePassword(password, customer.getSalt(), customer.getPassword())) {
                throw new SemanticError("Usuário ou senha inválida");
            }

            customer.setPassword(null);
            customer.setSalt(null);

            request.getSession().setAttribute("customerData", customer);
            response.sendRedirect(this.contextPath + "/customers/");
        } catch (SemanticError | SodiumException e) {
            if (e instanceof SemanticError) {
                errors.add(e.getMessage());
                request.setAttribute("errorList", errors);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/customers/login.jsp");
                dispatcher.forward(request, response);
                logger.log(Level.WARNING, e.getMessage());
                return;
            }
            logger.log(Level.SEVERE, e.getMessage(), e.getCause());
            throw new RuntimeException(e);
        }

    }

    private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ErrorList errors = CustomerValidator.deleteCustomerValidation(request);
        if (errors.isNotEmpty()) {
            request.setAttribute("errors", errors);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/customers/delete.jsp");
            dispatcher.forward(request, response);
            return;
        }

        int id = Integer.parseInt(request.getParameter("id"));

        customerDAO.delete(id);

        request.getSession().removeAttribute("customerData");
        response.sendRedirect(this.contextPath + "/customers/login");

    }

    private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ErrorList errors = CustomerValidator.updateCustomerValidation(request);
        String email = request.getParameter("email");
        String plainTextPassword = request.getParameter("password");
        String cpf = request.getParameter("cpf");
        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        String gender = request.getParameter("gender");
        String birthdateString = request.getParameter("birthdate");

        Map<String, String> fields = new HashMap<String, String>();
        fields.put("email", email);
        fields.put("cpf", cpf);
        fields.put("name", name);
        fields.put("phone", phone);
        fields.put("gender", gender);
        fields.put("birthdate", birthdateString);
        try {
            if (errors.isNotEmpty()) {
                request.setAttribute("data", fields);
                request.setAttribute("errorList", errors);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/customers/update.jsp");
                dispatcher.forward(request, response);
                return;
            }

            int id = Integer.parseInt(request.getParameter("id"));

            Date birthdate = DateParser.format(birthdateString, "yyyy-MM-dd");

            Customer customer = new Customer(id, cpf, name, phone, gender, birthdate, email);

            if (plainTextPassword != null && plainTextPassword.length() != 0) {
                String salt = HashPassword.generateSalt();
                String password = HashPassword.hashPassword(plainTextPassword, salt);

                customer.setPassword(password);
                customer.setSalt(salt);
            } else {
                Customer customerFromDatabase = customerDAO.findByEmail(email);

                customer.setPassword(customerFromDatabase.getPassword());
                customer.setSalt(customerFromDatabase.getSalt());
            }

            customer = customerDAO.update(customer);
            customer.setPassword(null);
            customer.setSalt(null);

            request.getSession().setAttribute("customerData", customer);
            response.sendRedirect(this.contextPath + "/customers/");
        } catch (ParseException | SodiumException e) {
            logger.log(Level.SEVERE, e.getMessage(), e.getCause());
            throw new RuntimeException(e);
        } catch (SemanticError e) {
            errors.add(e.getMessage());
            request.setAttribute("errorList", errors);
            request.setAttribute("data", fields);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/customers/update.jsp");
            dispatcher.forward(request, response);
            logger.log(Level.WARNING, e.getMessage());
        }
    }

    private void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().removeAttribute("customerData");
        response.sendRedirect(this.contextPath + "/customers/login");
    }
}
