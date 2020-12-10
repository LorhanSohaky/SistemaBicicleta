package br.ufscar.dc.dsw.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.ufscar.dc.dsw.domain.City;
import java.sql.PreparedStatement;

public class CityDAO extends GenericDAO {

    public List<City> getAll() {

        List<City> list = new ArrayList<>();

        String sql = "SELECT * FROM city;";

        try {
            Connection conn = this.getConnection();
            Statement statement = conn.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String state = resultSet.getString("state");
                City city = new City(name, state);
                list.add(city);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public boolean hasCity(City city) {

        String sql = "SELECT name, state FROM city WHERE name = ? and state = ?";

        try {
            Connection conn = this.getConnection();

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, city.getName());
            statement.setString(2, city.getState());

            ResultSet resultSet = statement.executeQuery();

            boolean hasNext = resultSet.next();

            resultSet.close();
            statement.close();
            conn.close();

            return hasNext;
        } catch (SQLException e) {
            System.out.println("Erro:"+e.getLocalizedMessage());
            throw new RuntimeException(e);
        }
    }
}
