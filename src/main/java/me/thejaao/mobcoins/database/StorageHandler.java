package me.thejaao.mobcoins.database;


import me.thejaao.mobcoins.entity.UserEconomy;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import static me.thejaao.mobcoins.TJMobCoins.*;

public class StorageHandler {

    private MySQL mySQL;

    public StorageHandler() {
        this.mySQL = getInstance().getMySQL();
        generateTables();
    }

    public void insertUser(String player) {
        try {
            PreparedStatement statement = getConnection().prepareStatement("INSERT INTO tj_mobcoins (name) VALUES (?)");
            statement.setString(1, player);
            statement.execute();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public UserEconomy getUser(String playerName) {
        UserEconomy user = new UserEconomy(playerName, 0.0);
        try {
            PreparedStatement statement = getConnection().prepareStatement("SELECT * FROM tj_mobcoins WHERE name = ?");
            statement.setString(1, playerName);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user.setMobCoins(resultSet.getDouble("mobcoins"));
            } else {
                insertUser(playerName);
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public void updateUser(UserEconomy user) {
        try {
            PreparedStatement statement = getConnection().prepareStatement("UPDATE tj_mobcoins SET mobcoins = ? WHERE name = ?");
            statement.setDouble(1, user.getMobCoins());
            statement.setString(2, user.getName());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void generateTables() {
        try {
            PreparedStatement statement = getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS tj_mobcoins (id INT NOT NULL AUTO_INCREMENT, name VARCHAR(16) NOT NULL, mobcoins BIGINT, PRIMARY KEY (id))");
            statement.execute();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Connection getConnection() {
        return mySQL.getConnection();
    }


}
