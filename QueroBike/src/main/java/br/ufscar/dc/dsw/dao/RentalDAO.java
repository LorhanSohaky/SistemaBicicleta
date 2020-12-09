package br.ufscar.dc.dsw.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.ufscar.dc.dsw.domain.Rental;
import br.ufscar.dc.dsw.domain.City;
import br.ufscar.dc.dsw.erros.SemanticError;

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

    public void insert(Rental rental) {

        String sql = "INSERT INTO rental (name,cnpj,email,description,"
                + "postal_code,street_name,neighborhood,street_number, "
                + "fk_city_name, fk_city_state,password,salt) "
                + "VALUES (?,?,?,?,?,?,?,?,?,?, ?,?);";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement = conn.prepareStatement(sql);
            statement.setString(1, rental.getName());
            statement.setString(2, rental.getCnpj());
            statement.setString(3, rental.getEmail());
            statement.setString(4, rental.getDescription());
            statement.setString(5, rental.getPostalCode());
            statement.setString(6, rental.getStreetName());
            statement.setString(7, rental.getNeighborhood());
            statement.setString(8, rental.getStreetNumber());
            statement.setString(9, rental.getCity().getName());
            statement.setString(10, rental.getCity().getState());
            statement.setString(11, rental.getPassword());
            statement.setString(12, rental.getSalt());

            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Rental findByEmail(String email) throws RuntimeException, SemanticError {
        String sql = "SELECT id, name, cnpj, description, neighborhood, complement,"
                + " street_number,street_name, postal_code, fk_city_name, fk_city_state,"
                + " password, salt FROM rental WHERE email = ?;";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, email);

            ResultSet resultSet = statement.executeQuery();

            if (!resultSet.next()) {
                throw new SemanticError("Usuário ou senha inválida");
            }

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
            String password = resultSet.getString("password");
            String salt = resultSet.getString("salt");

            City city = new City(cityName, cityState);
            Rental rental = new Rental(id, name, cnpj, description, postalCode,
                    streetName, neighborhood, complement, streetNumber, email,
                    password, salt, city);

            statement.close();
            conn.close();

            return rental;
        } catch (SQLException e) {
            throw new RuntimeException("Ops! Aconteceu um erro interno.", e);
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM rental WHERE id=?;";
        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException("Ops! Aconteceu um erro interno.", e);
        }
    }

    public Rental get(int rentalId) throws SemanticError {
        String sql = "SELECT id, name, cnpj, description, neighborhood, complement,"
                + " street_number,street_name, postal_code, fk_city_name, fk_city_state, email"
                + " FROM rental WHERE id = ?;";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, rentalId);

            ResultSet resultSet = statement.executeQuery();

            if (!resultSet.next()) {
                throw new SemanticError("Locadora não encontrada");
            }

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
            String email = resultSet.getString("email");

            City city = new City(cityName, cityState);
            Rental rental = new Rental(id, name, cnpj, description, postalCode,
                    streetName, neighborhood, complement, streetNumber, email, city);

            statement.close();
            conn.close();

            return rental;
        } catch (SQLException e) {
            throw new RuntimeException("Ops! Aconteceu um erro interno.", e);
        }
    }

    public void update(Rental rental) {
        String sql = "UPDATE rental SET name = ?, cnpj = ?, email = ?, description = ?,"
                + " postal_code = ?, street_name = ?, neighborhood = ?, street_number = ?, "
                + "fk_city_name = ?, fk_city_state = ?, password = ?, salt = ?"
                + "WHERE id = ?;";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement = conn.prepareStatement(sql);
            statement.setString(1, rental.getName());
            statement.setString(2, rental.getCnpj());
            statement.setString(3, rental.getEmail());
            statement.setString(4, rental.getDescription());
            statement.setString(5, rental.getPostalCode());
            statement.setString(6, rental.getStreetName());
            statement.setString(7, rental.getNeighborhood());
            statement.setString(8, rental.getStreetNumber());
            statement.setString(9, rental.getCity().getName());
            statement.setString(10, rental.getCity().getState());
            statement.setString(11, rental.getPassword());
            statement.setString(12, rental.getSalt());
            statement.setInt(13, rental.getId());

            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
