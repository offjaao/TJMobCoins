package me.thejaao.mobcoins;

import lombok.Getter;
import me.thejaao.mobcoins.listener.MobListener;
import me.thejaao.mobcoins.managers.MobCoinsManager;
import me.thejaao.mobcoins.managers.loader.MobCoinsLoader;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public final class TJMobCoins extends JavaPlugin {

    @Getter
    private static TJMobCoins instance;
    @Getter
    private MobCoinsManager mobCoinsManager;
    @Getter
    private Economy economy;

    @Override
    public void onEnable() {
        instance = this;
        loadConfiguration();
        initialize();
    }

    private void initialize() {
        mobCoinsManager = new MobCoinsManager();
        MobCoinsLoader mobCoinsLoader = new MobCoinsLoader(mobCoinsManager);
        mobCoinsLoader.loadMobsValue();
        setupEconomy();
        Bukkit.getPluginManager().registerEvents(new MobListener(), this);
        Bukkit.getConsoleSender().sendMessage("§6[TJ-MobCoins] §ePlugin iniciado com sucesso.");
    }

    private void loadConfiguration() {
        getConfig().options().copyDefaults(false);
        saveDefaultConfig();
    }

    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        economy = rsp.getProvider();
        return economy != null;
    }
}
