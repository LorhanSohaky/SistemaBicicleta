package br.ufscar.dc.dsw.controller;

import br.ufscar.dc.dsw.dao.RentalDAO;
import br.ufscar.dc.dsw.domain.City;
import br.ufscar.dc.dsw.domain.Rental;
import br.ufscar.dc.dsw.erros.SemanticError;
import br.ufscar.dc.dsw.utils.ErrorList;
import br.ufscar.dc.dsw.utils.HashPassword;
import br.ufscar.dc.dsw.validator.CustomerValidator;
import com.goterl.lazycode.lazysodium.LazySodiumJava;
import com.goterl.lazycode.lazysodium.SodiumJava;
import com.goterl.lazycode.lazysodium.exceptions.SodiumException;
import com.goterl.lazycode.lazysodium.interfaces.PwHash;
import com.sun.jna.NativeLong;
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

@WebServlet(urlPatterns = {"/rentals/", "/rentals/home", "/rentals/register", "/rentals/login", "/rentals/logout"})
public class RentalController extends HttpServlet {

    private static final Logger logger = Logger.getLogger(
            RentalController.class.getName()
    );
    private static final long serialVersionUID = 1L;
    private String contextPath = "";

    private RentalDAO dao;

    @Override
    public void init() {
        dao = new RentalDAO();
    }

    private String getAction(HttpServletRequest request) {
        this.contextPath = request.getContextPath();
        int length = this.contextPath.length() + "/rentals".length();

        String action = request.getRequestURI().substring(length);
        if (action == null) {
            action = "";
        }
        return action;
    }

    private boolean hasAValidSession(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();

        return session.getAttribute("rentalData") != null;
    }

    private void renderPage(String page, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher(page);
        dispatcher.forward(request, response);
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
                case "/login":
                    login(request, response);
                    break;
                default:
                    throw new Error("[POST] - Invalid path");
            }
        } catch (SodiumException | ServletException | IOException ex) {
            logger.log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response
    ) {
        try {
            String action = this.getAction(request);
            List<String> privateRoutes = Arrays.asList("/home", "/update", "/delete");

            if (privateRoutes.contains(action) && !hasAValidSession(request, response)) {
                response.sendRedirect(this.contextPath + "/rentals/login");
                return;
            }

            switch (action) {
                case "/":
                    List<Rental> list = dao.getAll();
                    request.setAttribute("rentalList", list);
                    renderPage("/rentals/index.jsp", request, response);
                    break;
                case "/home":
                    renderPage("/rentals/home.jsp", request, response);
                    break;
                case "/login":
                    renderPage("/rentals/login.jsp", request, response);
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

    private void register(
            HttpServletRequest request,
            HttpServletResponse response
    )
            throws ServletException, IOException, SodiumException {
        LazySodiumJava lazySodium = new LazySodiumJava(new SodiumJava());

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

        String salt = lazySodium.toHexStr(
                lazySodium.randomBytesBuf(PwHash.ARGON2ID_SALTBYTES)
        );

        String password = lazySodium.cryptoPwHash(
                plainTextPassword,
                64,
                lazySodium.toBinary(salt),
                PwHash.ARGON2ID_OPSLIMIT_INTERACTIVE,
                new NativeLong(PwHash.ARGON2ID_MEMLIMIT_INTERACTIVE),
                PwHash.Alg.PWHASH_ALG_ARGON2ID13
        );

        Rental rental = new Rental(
                name,
                cnpj,
                description,
                postalCode,
                streetName,
                neighborhood,
                complement,
                streetNumber,
                email,
                password,
                salt,
                city
        );
        dao.insert(rental);
        response.sendRedirect("/rentals/");
    }

    private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ErrorList errors = CustomerValidator.loginCustomerValidation(request);
        try {
            if (errors.isNotEmpty()) {
                request.setAttribute("errorList", errors);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/rentals/login.jsp");
                dispatcher.forward(request, response);
                return;
            }

            String email = request.getParameter("email");
            String password = request.getParameter("password");

            Rental rental = dao.findByEmail(email);

            if (!HashPassword.isSamePassword(password, rental.getSalt(), rental.getPassword())) {
                throw new SemanticError("Usuário ou senha inválida");
            }

            rental.setPassword(null);
            rental.setSalt(null);

            request.getSession().setAttribute("rentalData", rental);
            response.sendRedirect(this.contextPath + "/rentals/home");
        } catch (SemanticError | SodiumException e) {
            if (e instanceof SemanticError) {
                errors.add(e.getMessage());
                request.setAttribute("errorList", errors);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/rentals/login.jsp");
                dispatcher.forward(request, response);
                logger.log(Level.WARNING, e.getMessage());
                return;
            }
            logger.log(Level.SEVERE, e.getMessage(), e.getCause());
            throw new RuntimeException(e);
        }
    }

    private void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().removeAttribute("rentalData");
        response.sendRedirect(this.contextPath + "/rentals/login");
    }
}
