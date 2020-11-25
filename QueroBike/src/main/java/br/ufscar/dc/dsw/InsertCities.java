package br.ufscar.dc.dsw;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import br.ufscar.dc.dsw.dao.GenericDAO;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Statement;
import java.util.List;

public class InsertCities extends GenericDAO {

    private static Connection connect() throws ClassNotFoundException, SQLException {
        String url = "jdbc:sqlite:../database.db";
        Class.forName("org.sqlite.JDBC");

        Connection connection = DriverManager.getConnection(url);

        return connection;

    }

    private static void insertCities(Connection connection, String path) throws IOException, SQLException {
        Statement statement = connection.createStatement();
        List<String> inserts = loadSql(path);

        for (String insert : inserts) {
            statement.addBatch(insert);
        }

        statement.executeBatch();

        statement.close();
    }

    private static List<String> loadSql(String path) throws IOException {
        Path filePath = Paths.get(path + "insert_cities.sql");
        List<String> inserts = Files.readAllLines(filePath);

        return inserts;
    }

    public static void insertCities(String path) throws IOException, ClassNotFoundException, SQLException {
        Connection connection = connect();
        insertCities(connection, path);
        connection.close();
    }
}
