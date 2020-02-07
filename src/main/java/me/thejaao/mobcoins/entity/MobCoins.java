package me.thejaao.mobcoins.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.EntityType;

@Getter @Setter @AllArgsConstructor
public class MobCoins {

    private EntityType entityType;
    private double value;

}
