package br.ufscar.dc.dsw.controller;

import br.ufscar.dc.dsw.CreateDatabase;
import br.ufscar.dc.dsw.InsertCities;
import br.ufscar.dc.dsw.InsertRentals;
import br.ufscar.dc.dsw.dao.CityDAO;
import br.ufscar.dc.dsw.domain.City;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/reset-database")
public class ResetDatabaseController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private CityDAO dao;

    @Override
    public void init() {
        dao = new CityDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            resetDatabase(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ResetDatabaseController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ResetDatabaseController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void resetDatabase(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ClassNotFoundException {
        
        ServletContext context = request.getServletContext();
        
        String path = context.getRealPath("/WEB-INF/classes/sql/");
        System.out.println(path);
        CreateDatabase.createDatabase(path);
        InsertCities.insertCities(path);
        InsertRentals.insertRentals(path);
        
        List<City> list = dao.getAll();
        request.setAttribute("cityList", list);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/cities/list.jsp");
        dispatcher.forward(request, response);
    }
}
