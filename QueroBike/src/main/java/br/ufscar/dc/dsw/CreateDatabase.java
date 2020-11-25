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

public class CreateDatabase extends GenericDAO {

    private static Connection connect() throws ClassNotFoundException, SQLException {
        String url = "jdbc:sqlite:../database.db";
        Class.forName("org.sqlite.JDBC");

        Connection connection = DriverManager.getConnection(url);

        return connection;

    }

    private static void createTables(Connection connection, String path) throws IOException, SQLException {
        Statement statement = connection.createStatement();
        String[] commands = loadSql(path);

        for (String command : commands) {
            statement.executeUpdate(command);
        }

        statement.close();
    }

    private static String[] loadSql(String path) throws IOException {
        Path filePath = Paths.get(path + "create_tables.sql");
        byte[] encoded = Files.readAllBytes(filePath);
        String sql = new String(encoded, "UTF-8");

        String[] commands = sql.split(";");

        return commands;
    }

    public static void createDatabase(String path) throws SQLException, IOException, ClassNotFoundException {
        Connection connection = connect();
        createTables(connection, path);
        connection.close();
    }
}
