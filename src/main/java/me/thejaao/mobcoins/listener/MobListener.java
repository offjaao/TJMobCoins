package me.thejaao.mobcoins.listener;

import me.thejaao.mobcoins.entity.MobCoins;
import me.thejaao.mobcoins.managers.MobCoinsManager;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

import static me.thejaao.mobcoins.TJMobCoins.*;

public class MobListener implements Listener {

    private MobCoinsManager MANAGER = getInstance().getMobCoinsManager();
    private Economy ECONOMY = getInstance().getEconomy();

    @EventHandler
    public void onKill(EntityDeathEvent event) {
        LivingEntity entity = event.getEntity();
        EntityType type = entity.getType();
        Player player = entity.getKiller();
        MobCoins mobCoins = MANAGER.getByType(type);

        if (mobCoins == null) return;

        player.sendMessage(getInstance().getConfig().getString("Message").replace("{type}", mobCoins.getEntityType().name())
                .replace("{money}", String.valueOf(mobCoins.getValue())).replaceAll("&", "§"));
        ECONOMY.depositPlayer(player.getName(), mobCoins.getValue());
    }

}
