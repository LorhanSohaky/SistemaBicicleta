package br.ufscar.dc.dsw.dao;

import br.ufscar.dc.dsw.domain.Admin;
import br.ufscar.dc.dsw.erros.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminDAO extends GenericDAO {

    public Admin findByEmail(String email) throws RuntimeException, SemanticError {
        String sql = "SELECT id, name, password, salt FROM admin WHERE email = ?;";

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
            String password = resultSet.getString("password");
            String salt = resultSet.getString("salt");
            Admin admin = new Admin(id, name, email, password, salt);

            statement.close();
            conn.close();

            return admin;
        } catch (SQLException e) {
            throw new RuntimeException("Ops! Aconteceu um erro interno.", e);
        }

    }

    public Admin getById(int id) throws SemanticError {
        String sql = "SELECT name, email FROM admin WHERE id = ?;";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            if (!resultSet.next()) {
                throw new SemanticError("Usuário não encontrado");
            }

            String name = resultSet.getString("name");
            String email = resultSet.getString("email");
            Admin admin = new Admin(id, name, email);

            statement.close();
            conn.close();

            return admin;
        } catch (SQLException e) {
            throw new RuntimeException("Ops! Aconteceu um erro interno.", e);
        }
    }

    public Admin update(Admin admin) {
        String sql
                = "UPDATE admin SET email = ?, name = ? WHERE id = ?;";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement = conn.prepareStatement(sql);
            statement.setString(1, admin.getEmail());
            statement.setString(2, admin.getName());
            statement.setInt(3, admin.getId());

            statement.executeUpdate();
            statement.close();
            conn.close();

            return admin;
        } catch (SQLException e) {
            throw new RuntimeException("Ops! Aconteceu um erro interno.", e);
        }
    }
}
