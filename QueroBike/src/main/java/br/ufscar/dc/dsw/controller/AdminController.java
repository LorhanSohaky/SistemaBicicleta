package br.ufscar.dc.dsw.controller;

import br.ufscar.dc.dsw.dao.AdminDAO;
import br.ufscar.dc.dsw.domain.Admin;
import br.ufscar.dc.dsw.erros.SemanticError;
import br.ufscar.dc.dsw.utils.ErrorList;
import br.ufscar.dc.dsw.utils.HashPassword;
import br.ufscar.dc.dsw.validator.CustomerValidator;
import com.goterl.lazycode.lazysodium.exceptions.SodiumException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns = {"/admins/", "/admins/login", "/admins/update"})
public class AdminController extends HttpServlet {

    private static final Logger logger = Logger.getLogger(AdminController.class.getName());
    private static final long serialVersionUID = 1L;
    private String contextPath = "";

    private AdminDAO dao;

    @Override
    public void init() {
        dao = new AdminDAO();
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

            switch (action) {
                case "/login":
                    login(request, response);
                    break;
                case "/update":
                    update(request, response);
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
        try {
            String action = this.getAction(request);
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
                default:
                    throw new Error("[GET] - Invalid path");
            }
        } catch (ServletException | IOException ex) {
            logger.log(Level.SEVERE, null, ex);
        }
    }

    private void renderPage(String page, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher(page);
        dispatcher.forward(request, response);
    }

    private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO: Replace by AdminValidator
        ErrorList errors = CustomerValidator.loginCustomerValidation(request);
        try {
            if (errors.isNotEmpty()) {
                request.setAttribute("errorList", errors);
                RequestDispatcher dispatcher = request.getRequestDispatcher( "/admins/login.jsp");
                dispatcher.forward(request, response);
                return;
            }

            String email = request.getParameter("email");
            String password = request.getParameter("password");

            Admin admin = dao.findByEmail(email);
            
            if (!HashPassword.isSamePassword(password, admin.getSalt(), admin.getPassword())) {
                throw new SemanticError("Usuário ou senha inválida");
            }

            admin.setPassword(null);
            admin.setSalt(null);

            request.getSession().setAttribute("adminData", admin);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/admins/home.jsp");
            dispatcher.forward(request, response);
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

    private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ErrorList errors = CustomerValidator.updateCustomerValidation(request);
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

        admin = dao.update(admin);
        admin.setPassword(null);
        admin.setSalt(null);

        request.getSession().setAttribute("adminData", admin);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/admins/home.jsp");
        dispatcher.forward(request, response);

    }
}
