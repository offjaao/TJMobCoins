package me.thejaao.mobcoins.managers.loader;

import me.thejaao.mobcoins.entity.MobCoins;
import me.thejaao.mobcoins.managers.MobCoinsManager;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.EntityType;
import static me.thejaao.mobcoins.TJMobCoins.*;

public class MobCoinsLoader {

    private MobCoinsManager mobCoinsManager;

    public MobCoinsLoader(MobCoinsManager mobCoinsManager) {
        this.mobCoinsManager = mobCoinsManager;
    }

    public void loadMobsValue() {
        FileConfiguration config = getInstance().getConfig();
        ConfigurationSection entity = config.getConfigurationSection("Entities");

        if (entity == null) return;

        for (String key : entity.getKeys(false)) {
            String type = entity.getString(key + ".Type", "");
            double value = entity.getDouble(key + ".Value", 0);

            mobCoinsManager.getElements().add(new MobCoins(EntityType.valueOf(type), value));
        }
    }
}
