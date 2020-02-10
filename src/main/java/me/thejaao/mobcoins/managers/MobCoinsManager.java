package me.thejaao.mobcoins.managers;

import me.thejaao.mobcoins.entity.MobCoins;
import me.thejaao.mobcoins.models.Manager;
import org.bukkit.entity.EntityType;

public class MobCoinsManager extends Manager<MobCoins> {

    public MobCoins getByType(EntityType type) {
        return get(entityType -> entityType.getEntityType().equals(type))
                .orElse(null);
    }

}
