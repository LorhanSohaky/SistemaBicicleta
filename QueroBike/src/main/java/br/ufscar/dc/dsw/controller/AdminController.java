package br.ufscar.dc.dsw.controller;

import br.ufscar.dc.dsw.dao.AdminDAO;
import br.ufscar.dc.dsw.dao.RentalDAO;
import br.ufscar.dc.dsw.domain.Admin;
import br.ufscar.dc.dsw.domain.City;
import br.ufscar.dc.dsw.domain.Rental;
import br.ufscar.dc.dsw.erros.SemanticError;
import br.ufscar.dc.dsw.utils.ErrorList;
import br.ufscar.dc.dsw.utils.HashPassword;
import br.ufscar.dc.dsw.validator.AdminValidator;
import br.ufscar.dc.dsw.validator.RentalValidator;
import com.goterl.lazycode.lazysodium.exceptions.SodiumException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns = {"/admins/", "/admins/login", "/admins/update", "/admins/rentals", "/admins/rentals/edit", "/admins/rentals/delete", "/admins/logout"})
public class AdminController extends HttpServlet {

    private static final Logger logger = Logger.getLogger(AdminController.class.getName());
    private static final long serialVersionUID = 1L;
    private static final List<String> privateRoutes = Arrays.asList("/", "/update", "/rentals", "/logout", "/rentals/delete", "/rentals/edit");
    private String contextPath = "";

    private AdminDAO adminDAO;
    private RentalDAO rentalDAO;

    @Override
    public void init() {
        adminDAO = new AdminDAO();
        rentalDAO = new RentalDAO();
    }

    private String getAction(HttpServletRequest request) {
        this.contextPath = request.getContextPath();
        int length = this.contextPath.length() + "/admins".length();

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

            if (privateRoutes.contains(action) && !hasAValidSession(request, response)) {
                response.sendRedirect(this.contextPath + "/admins/login");
                return;
            }

            switch (action) {
                case "/login":
                    login(request, response);
                    break;
                case "/update":
                    update(request, response);
                    break;
                case "/rentals/edit":
                    editRental(request, response);
                    break;
                default:
                    throw new Error("[POST] - AdminController Invalid path");
            }
        } catch (ServletException | IOException ex) {
            logger.log(Level.SEVERE, null, ex);
        }
    }

    private boolean hasAValidSession(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();

        return session.getAttribute("adminData") != null;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            String action = this.getAction(request);

            if (privateRoutes.contains(action) && !hasAValidSession(request, response)) {
                response.sendRedirect(this.contextPath + "/admins/login");
                return;
            }

            switch (action) {
                case "/":
                    renderPage("/admins/home.jsp", request, response);
                    break;
                case "/login":
                    renderPage("/admins/login.jsp", request, response);
                    break;
                case "/update":
                    renderPage("/admins/update.jsp", request, response);
                    break;
                case "/rentals":
                    List<Rental> list = rentalDAO.getAll();
                    request.setAttribute("rentalList", list);
                    renderPage("/admins/rentals/list.jsp", request, response);
                    break;
                case "/rentals/delete":
                    deleteRental(request, response);
                    break;
                case "/rentals/edit":
                    renderEditRental(request, response);
                    break;
                case "/logout":
                    logout(request, response);
                    break;
                default:
                    throw new Error("[GET] - Invalid path");
            }
        } catch (ServletException | IOException ex) {
            logger.log(Level.SEVERE, null, ex);
        }
    }

    private void deleteRental(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int rentalId = Integer.parseInt(request.getParameter("id"));
        rentalDAO.delete(rentalId);
        response.sendRedirect(this.contextPath + "/admins/rentals");

    }

    private void renderEditRental(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String idString = request.getParameter("id");
        if (idString == null || idString.length() == 0) {
            throw new RuntimeException("É preciso informar o id da locadora");
        }

        int rentalId = Integer.parseInt(idString);
        Rental rental;

        try {
            rental = rentalDAO.get(rentalId);
            request.setAttribute("rental", rental);
            renderPage("/admins/rentals/edit.jsp", request, response);
        } catch (SemanticError ex) {
            logger.log(Level.SEVERE, null, ex);
            response.sendRedirect(this.contextPath + "/admins/rentals");
        }
    }

    private void editRental(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        ErrorList errors = RentalValidator.editRentalValidation(request);

        if (errors.isNotEmpty()) {
            int id = Integer.parseInt(request.getParameter("id"));
            String name = request.getParameter("name");
            String cnpj = request.getParameter("cnpj");
            String email = request.getParameter("email");
            String description = request.getParameter("description");
            String postalCode = request.getParameter("postalCode");
            String streetName = request.getParameter("streetName");
            String neighborhood = request.getParameter("neighborhood");
            String streetNumber = request.getParameter("streetNumber");
            String complement = request.getParameter("complement");
            String plainTextPassword = request.getParameter("password");
            City city = new City(
                    request.getParameter("city"),
                    request.getParameter("state")
            );

            if (complement == null) {
                complement = "";
            }

            Rental rental = new Rental(
                    id,
                    name,
                    cnpj,
                    description,
                    postalCode,
                    streetName,
                    neighborhood,
                    complement,
                    streetNumber,
                    email,
                    city
            );
            request.setAttribute("errorList", errors);
            request.setAttribute("rental", rental);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/admins/rentals/edit.jsp");
            dispatcher.forward(request, response);
            return;
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/rentals/edit");
        dispatcher.forward(request, response);
    }

    private void renderPage(String page, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher(page);
        dispatcher.forward(request, response);
    }

    private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ErrorList errors = AdminValidator.loginAdminValidation(request);
        try {
            if (errors.isNotEmpty()) {
                request.setAttribute("errorList", errors);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/admins/login.jsp");
                dispatcher.forward(request, response);
                return;
            }

            String email = request.getParameter("email");
            String password = request.getParameter("password");

            Admin admin = adminDAO.findByEmail(email);

            if (!HashPassword.isSamePassword(password, admin.getSalt(), admin.getPassword())) {
                throw new SemanticError("Usuário ou senha inválida");
            }

            admin.setPassword(null);
            admin.setSalt(null);

            request.getSession().setAttribute("adminData", admin);
            response.sendRedirect(this.contextPath + "/admins/");
        } catch (SemanticError | SodiumException e) {
            if (e instanceof SemanticError) {
                errors.add(e.getMessage());
                request.setAttribute("errorList", errors);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/admins/login.jsp");
                dispatcher.forward(request, response);
                logger.log(Level.WARNING, e.getMessage());
                return;
            }
            logger.log(Level.SEVERE, e.getMessage(), e.getCause());
        }

    }

    private void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().removeAttribute("adminData");
        response.sendRedirect(this.contextPath + "/admins/login");
    }

    private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ErrorList errors = AdminValidator.updateAdminValidation(request);
        if (errors.isNotEmpty()) {
            request.setAttribute("errorList", errors);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/admins/update.jsp");
            dispatcher.forward(request, response);
            return;
        }

        HttpSession session = request.getSession();

        Admin adminFromSession = (Admin) session.getAttribute("adminData");

        int id = adminFromSession.getId();
        String email = request.getParameter("email");
        String name = request.getParameter("name");

        Admin admin = new Admin(id, name, email);

        admin = adminDAO.update(admin);
        admin.setPassword(null);
        admin.setSalt(null);

        request.getSession().setAttribute("adminData", admin);
        response.sendRedirect(this.contextPath + "/admins/");

    }
}
