package br.ufscar.dc.dsw.controller;

import br.ufscar.dc.dsw.dao.CityDAO;
import br.ufscar.dc.dsw.domain.City;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/cities/")
public class CityController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private CityDAO dao;

    @Override
    public void init() {
        dao = new CityDAO();
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
        
        //TODO use Actions
        

        lista(request, response);

    }

    private void lista(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<City> list = dao.getAll();
        System.out.println(list);
        request.setAttribute("cityList", list);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/cities/list.jsp");
        dispatcher.forward(request, response);
    }
}
