package br.ufscar.dc.dsw.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.ufscar.dc.dsw.domain.Rental;
import br.ufscar.dc.dsw.domain.City;

public class RentalDAO extends GenericDAO {

    public List<Rental> getAll() {

        List<Rental> list = new ArrayList<>();

        String sql = "SELECT id, name, cnpj, description, neighborhood, complement, street_number,street_name, postal_code, fk_city_name, fk_city_state FROM rental;";

        try {
            Connection conn = this.getConnection();
            Statement statement = conn.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String cnpj = resultSet.getString("cnpj");
                String description = resultSet.getString("description");
                String postalCode = resultSet.getString("postal_code");
                String streetName = resultSet.getString("street_name");
                String neighborhood = resultSet.getString("neighborhood");
                String complement = resultSet.getString("complement");
                String streetNumber = resultSet.getString("street_number");
                String cityName = resultSet.getString("fk_city_name");
                String cityState = resultSet.getString("fk_city_state");

                City city = new City(cityName, cityState);
                Rental rental = new Rental(id, name, cnpj, description, postalCode, streetName, neighborhood, complement, streetNumber, city);
                list.add(rental);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }
}
