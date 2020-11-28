package br.ufscar.dc.dsw.controller;

import br.ufscar.dc.dsw.dao.RentalDAO;
import br.ufscar.dc.dsw.domain.Rental;
import br.ufscar.dc.dsw.domain.City;
import com.goterl.lazycode.lazysodium.LazySodiumJava;
import com.goterl.lazycode.lazysodium.SodiumJava;
import com.goterl.lazycode.lazysodium.exceptions.SodiumException;
import com.goterl.lazycode.lazysodium.interfaces.PwHash;
import com.sun.jna.NativeLong;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/rentals/", "/rentals/register", "/rentals/list"})
public class RentalController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private RentalDAO dao;

    @Override
    public void init() {
        dao = new RentalDAO();
    }

    private String getAction(HttpServletRequest request) {
        String action = request.getRequestURI().substring("/rentals".length());
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
                default:
                    throw new Error("Invalid path");
            }
        } catch (SodiumException | ServletException | IOException ex) {
            Logger.getLogger(RentalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            String action = this.getAction(request);
            System.out.println(action);
            switch (action) {
                case "/":
                    list(request, response);
                    break;
                default:
                    throw new Error("Invalid path");
            }
        } catch (ServletException | IOException ex) {
            Logger.getLogger(RentalController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void list(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Rental> list = dao.getAll();
        request.setAttribute("rentalList", list);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/rentals/list.jsp");
        dispatcher.forward(request, response);
    }

    private void register(HttpServletRequest request, HttpServletResponse response)
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
        City city = new City(request.getParameter("city"), request.getParameter("state"));

        if (complement == null) {
            complement = "";
        }

        String salt = lazySodium.toHexStr(lazySodium.randomBytesBuf(PwHash.ARGON2ID_SALTBYTES));

        String password = lazySodium.cryptoPwHash(plainTextPassword, 64, lazySodium.toBinary(salt), PwHash.ARGON2ID_OPSLIMIT_INTERACTIVE, new NativeLong(PwHash.ARGON2ID_MEMLIMIT_INTERACTIVE), PwHash.Alg.PWHASH_ALG_ARGON2ID13);

        Rental rental = new Rental(name, cnpj, description, postalCode, streetName, neighborhood, complement, streetNumber, email, password, salt, city);
        dao.insert(rental);
        response.sendRedirect("/rentals/");
    }
}
