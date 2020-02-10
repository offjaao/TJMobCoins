package me.thejaao.mobcoins.listener;

import me.thejaao.mobcoins.entity.MobCoins;
import me.thejaao.mobcoins.helper.BalanceHelper;
import me.thejaao.mobcoins.managers.MobCoinsManager;
import me.thejaao.mobcoins.managers.UserManager;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

import static me.thejaao.mobcoins.TJMobCoins.*;

public class MobListener implements Listener {

    private MobCoinsManager MOB_MANAGER = getInstance().getMobCoinsManager();
    private UserManager USER_MANAGER = getInstance().getUserManager();

    @EventHandler
    public void onKill(EntityDeathEvent event) {
        LivingEntity entity = event.getEntity();
        EntityType type = entity.getType();
        Player player = entity.getKiller();
        MobCoins mobCoins = MOB_MANAGER.getByType(type);

        if (mobCoins == null) return;

        player.sendMessage(getInstance().getConfig().getString("Message").replace("{type}", mobCoins.getEntityType().name())
                .replace("{money}", BalanceHelper.prefix(mobCoins.getValue())).replaceAll("&", "§"));
        USER_MANAGER.addMobCoins(player.getName(), mobCoins.getValue());
    }

}
