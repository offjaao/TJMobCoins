package me.thejaao.mobcoins.managers;

import me.thejaao.mobcoins.entity.MobCoins;
import me.thejaao.mobcoins.model.Manager;
import org.bukkit.entity.EntityType;

public class MobCoinsManager extends Manager<MobCoins> {

    public MobCoins get(EntityType type) {
        return get(entityType -> entityType.getEntityType().equals(type))
                .orElse(null);
    }

}
