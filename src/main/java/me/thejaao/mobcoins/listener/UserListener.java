package me.thejaao.mobcoins.listener;

import me.thejaao.mobcoins.database.StorageHandler;
import me.thejaao.mobcoins.entity.UserEconomy;
import me.thejaao.mobcoins.managers.UserManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import static me.thejaao.mobcoins.TJMobCoins.*;

public class UserListener implements Listener {

    private StorageHandler STORAGE_MANAGER = getInstance().getStorageHandler();
    private UserManager USER_MANAGER = getInstance().getUserManager();

    @EventHandler
    public void onLoad(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        UserEconomy user = STORAGE_MANAGER.getUser(player.getName());
        USER_MANAGER.loadUser(user);

    }

    @EventHandler
    public void onSave(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        UserEconomy user = USER_MANAGER.getUser(player.getName());
        STORAGE_MANAGER.updateUser(user);
    }

}
