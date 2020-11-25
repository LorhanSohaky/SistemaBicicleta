package br.ufscar.dc.dsw.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

abstract public class GenericDAO {

    public GenericDAO() {
        try {
            /* Setup Banco de dados Derby */
            // Class.forName("org.apache.derby.jdbc.ClientDriver");
            /* Setup Banco de dados MySQL */
            Class.forName("org.sqlite.JDBC");

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    protected Connection getConnection() throws SQLException {
        String url = "jdbc:sqlite:../database.db";

        return DriverManager.getConnection(url);
    }
}
