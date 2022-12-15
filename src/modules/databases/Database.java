package modules.databases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
    private Connection connection;

    public Database(String host, String databaseName, String username, String password)
            throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        this.connection = DriverManager.getConnection(
                "jdbc:mysql://" + host + "/" + databaseName,
                username,
                password);
    }

    public ResultSet executeQuery(String query) throws SQLException {
        return this.connection.createStatement().executeQuery(query);
    }

    public void execute(String query) throws SQLException {
        this.connection.createStatement().execute(query);
    }

    public int executeAndGetInsertedId(String query) throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);

        ResultSet resultSet = statement.getGeneratedKeys();

        if (resultSet.next()) {
            return resultSet.getInt(1);
        } else {
            return -1;
        }
    }
}
