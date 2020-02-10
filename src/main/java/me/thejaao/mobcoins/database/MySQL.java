package me.thejaao.mobcoins.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;

public class MySQL {

    private Connection connection;
    private final String user;
    private final String password;
    private final String host;
    private final String database;

    public MySQL(String host, String database, String user, String password) {
        this.host = host;
        this.database = database;
        this.user = user;
        this.password = password;
        this.connection = getConnection();
    }

    private Connection createConnection() {
        try {
            String connStr = "jdbc:mysql://" + this.host + ":3306/" + this.database + "?autoReconnect=true";
            try {
                Class.forName("com.mysql.jdbc.Driver").newInstance();
            } catch (InstantiationException | IllegalAccessException exception) {
                exception.printStackTrace();
            }
            connection = DriverManager.getConnection(connStr, this.user, this.password);
            return connection;
        } catch (ClassNotFoundException exception) {
            exception.printStackTrace();
            return null;
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    public synchronized Connection getConnection() {
        if (connection == null) {
            connection = createConnection();
            try {
                Objects.requireNonNull(connection).setAutoCommit(true);
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        }
        return connection;
    }
}