package br.ufscar.dc.dsw.dao;

import br.ufscar.dc.dsw.domain.City;
import br.ufscar.dc.dsw.domain.Customer;
import br.ufscar.dc.dsw.domain.Rental;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.ufscar.dc.dsw.domain.Reserve;
import java.sql.PreparedStatement;
import java.util.Date;

public class ReserveDAO extends GenericDAO {

    public List<Reserve> listReserveFrom(Customer customer) {
        List<Reserve> list = new ArrayList<>();

        try {
            String sql = "SELECT reserve.id, fk_rental, moment, rental.name as rentalName, fk_city_name, fk_city_state FROM reserve INNER JOIN rental ON rental.id = fk_rental WHERE fk_customer = ?;";

            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setInt(1, customer.getId());

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int rentalId = resultSet.getInt("fk_rental");
                String rentalName = resultSet.getString("rentalName");

                String cityName = resultSet.getString("fk_city_name");
                String state = resultSet.getString("fk_city_state");
                City city = new City(cityName, state);

                Rental rental = new Rental(rentalId, rentalName, city);

                Date moment = resultSet.getDate("moment");

                list.add(new Reserve(id, rental, moment));
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Ops! Aconteceu um erro interno.", ex);
        }

        return list;
    }

    public List<Reserve> listReserveFrom(Rental rental) {
        List<Reserve> list = new ArrayList<>();

        try {
            String sql = "SELECT id, fk_customer, moment FROM reserve WHERE fk_rental = ?;";

            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setInt(1, rental.getId());

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int customerId = resultSet.getInt("fk_customer");
                Customer customer = new Customer(customerId);

                Date moment = resultSet.getDate("moment");

                list.add(new Reserve(id, customer, moment));
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Ops! Aconteceu um erro interno.", ex);
        }

        return list;
    }

    public List<Reserve> filterReserveFromRange(Rental rental, Date start, Date end) {
        List<Reserve> list = new ArrayList<>();

        try {
            String sql = "SELECT id, fk_customer, moment FROM reserve WHERE fk_rental = ? AND moment >= ? AND moment <= ?;";

            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setInt(1, rental.getId());
            statement.setDate(2, new java.sql.Date(start.getTime()));
            statement.setDate(3, new java.sql.Date(end.getTime()));

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int customerId = resultSet.getInt("fk_customer");
                Customer customer = new Customer(customerId);

                Date moment = resultSet.getDate("moment");

                list.add(new Reserve(id, customer, moment));
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Ops! Aconteceu um erro interno.", ex);
        }

        return list;
    }

    public List<Reserve> filterReserveFromRange(Date start, Date end) {
        List<Reserve> list = new ArrayList<>();

        try {
            String sql = "SELECT id, fk_customer, fk_rental, moment FROM reserve WHERE moment >= ? AND moment <= ?;";

            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setDate(1, new java.sql.Date(start.getTime()));
            statement.setDate(2, new java.sql.Date(end.getTime()));

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int customerId = resultSet.getInt("fk_customer");
                Customer customer = new Customer(customerId);

                int rentalId = resultSet.getInt("fk_rental");
                Rental rental = new Rental(rentalId);

                Date moment = resultSet.getDate("moment");

                list.add(new Reserve(rental, customer, moment));
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Ops! Aconteceu um erro interno.", ex);
        }

        return list;
    }
}
