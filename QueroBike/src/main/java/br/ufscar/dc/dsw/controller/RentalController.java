package br.ufscar.dc.dsw.controller;

import br.ufscar.dc.dsw.dao.RentalDAO;
import br.ufscar.dc.dsw.domain.Rental;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/rentals")
public class RentalController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private RentalDAO dao;

    @Override
    public void init() {
        dao = new RentalDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getPathInfo();
        if (action == null) {
            action = "";
        }

        System.out.println("cheguei");

        //TODO use Actions
        lista(request, response);

    }

    private void lista(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Rental> list = dao.getAll();
        System.out.println(list);
        request.setAttribute("rentalList", list);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/rentals/list.jsp");
        dispatcher.forward(request, response);
    }
}
