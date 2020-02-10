package me.thejaao.mobcoins.managers;

import me.thejaao.mobcoins.entity.UserEconomy;
import me.thejaao.mobcoins.models.Manager;

public class UserManager extends Manager<UserEconomy> {

    public void loadUser(UserEconomy user) {
        this.getElements().add(user);
    }

    public UserEconomy getUser(String playerName) {
        return get(userCoin -> userCoin.getName().equals(playerName)).orElse(null);
    }

    public double getMobCoins(String playerName) {
        return this.getUser(playerName).getMobCoins();
    }

    public void addMobCoins(String playerName, double value) {
        double current = this.getMobCoins(playerName);
        this.getUser(playerName).setMobCoins(current + value);
    }

    public void removeMobCoins(String playerName, double value) {
        double current = this.getMobCoins(playerName);
        this.getUser(playerName).setMobCoins(current - value);
    }
    public void setMobCoins(String playerName, double value) {
        this.getUser(playerName).setMobCoins(value);
    }
}
