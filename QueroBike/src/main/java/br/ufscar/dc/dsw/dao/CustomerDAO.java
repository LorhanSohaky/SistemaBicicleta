package br.ufscar.dc.dsw.dao;

import br.ufscar.dc.dsw.domain.Customer;
import br.ufscar.dc.dsw.erros.*;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerDAO extends GenericDAO {

    // TODO:
    // - delete
    public Customer insert(Customer customer) throws SemanticError, RuntimeException {
        String sql
                = "INSERT INTO customer (email,password, salt, cpf,"
                + " name, phone, gender, birthdate) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?);";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement = conn.prepareStatement(sql);
            statement.setString(1, customer.getEmail());
            statement.setString(2, customer.getPassword());
            statement.setString(3, customer.getSalt());
            statement.setString(4, customer.getCpf());
            statement.setString(5, customer.getName());
            statement.setString(6, customer.getPhone());
            statement.setString(7, customer.getGender());
            statement.setDate(8, new Date(customer.getBirthdate().getTime()));

            statement.executeUpdate();

            ResultSet generatedKeys = statement.getGeneratedKeys();
            generatedKeys.next();
            int id = generatedKeys.getInt(1);
            customer.setId(id);

            statement.close();
            conn.close();

            return customer;
        } catch (SQLException e) {
            if (e.getMessage().contains("UNIQUE constraint failed: customer.email")) {
                throw new SemanticError(
                        "Ops! Já existe uma conta cadastrada com este e-mail.",
                        e
                );
            }

            throw new RuntimeException("Ops! Aconteceu um erro interno.", e);
        }
    }

    public Customer findByEmail(String email) throws RuntimeException, SemanticError {
        String sql = "SELECT id, cpf, name, phone, gender, birthdate, password, salt FROM customer WHERE email = ?;";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, email);

            ResultSet resultSet = statement.executeQuery();

            if (!resultSet.next()) {
                throw new SemanticError("Usuário ou senha inválida");
            }

            int id = resultSet.getInt("id");
            String cpf = resultSet.getString("cpf");
            String name = resultSet.getString("name");
            String phone = resultSet.getString("phone");
            String gender = resultSet.getString("gender");
            java.util.Date birthdate = resultSet.getDate("birthdate");
            String password = resultSet.getString("password");
            String salt = resultSet.getString("salt");
            Customer customer = new Customer(id, cpf, name, phone, gender, birthdate, email, password, salt);

            statement.close();
            conn.close();

            return customer;
        } catch (SQLException e) {
            throw new RuntimeException("Ops! Aconteceu um erro interno.", e);
        }

    }

    public Customer getById(int id) throws SemanticError {
        String sql = "SELECT cpf, name, phone, gender, email, birthdate FROM customer WHERE id = ?;";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            if (!resultSet.next()) {
                throw new SemanticError("Usuário não encontrado");
            }

            String cpf = resultSet.getString("cpf");
            String name = resultSet.getString("name");
            String email = resultSet.getString("email");
            String phone = resultSet.getString("phone");
            String gender = resultSet.getString("gender");
            java.util.Date birthdate = resultSet.getDate("birthdate");
            Customer customer = new Customer(id, cpf, name, phone, gender, birthdate, email);

            statement.close();
            conn.close();

            return customer;
        } catch (SQLException e) {
            throw new RuntimeException("Ops! Aconteceu um erro interno.", e);
        }
    }
}
