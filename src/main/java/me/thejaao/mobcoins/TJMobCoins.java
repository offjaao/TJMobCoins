package me.thejaao.mobcoins;

import lombok.Getter;
import me.thejaao.mobcoins.command.MobCoinCommand;
import me.thejaao.mobcoins.command.subcommands.SubCommandAdd;
import me.thejaao.mobcoins.command.subcommands.SubCommandHelp;
import me.thejaao.mobcoins.command.subcommands.SubCommandRemove;
import me.thejaao.mobcoins.command.subcommands.SubCommandSet;
import me.thejaao.mobcoins.database.MySQL;
import me.thejaao.mobcoins.database.StorageHandler;
import me.thejaao.mobcoins.listener.MobListener;
import me.thejaao.mobcoins.listener.UserListener;
import me.thejaao.mobcoins.managers.MobCoinsManager;
import me.thejaao.mobcoins.managers.UserManager;
import me.thejaao.mobcoins.managers.loader.MobCoinsLoader;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class TJMobCoins extends JavaPlugin {

    @Getter
    private static TJMobCoins instance;
    @Getter
    private MobCoinsManager mobCoinsManager;
    @Getter
    private UserManager userManager;
    @Getter
    private MySQL mySQL;
    @Getter
    private StorageHandler storageHandler;

    @Override
    public void onEnable() {
        instance = this;
        loadConfiguration();
        mySQL = new MySQL(getConfig().getString("MySQL.host"), getConfig().getString("MySQL.database"), getConfig().getString("MySQL.user"), getConfig().getString("MySQL.password"));
        initialize();
    }

    private void initialize() {
        mobCoinsManager = new MobCoinsManager();
        userManager = new UserManager();
        storageHandler = new StorageHandler();
        MobCoinsLoader mobCoinsLoader = new MobCoinsLoader(mobCoinsManager);
        mobCoinsLoader.loadMobsValue();
        Bukkit.getPluginManager().registerEvents(new MobListener(), this);
        Bukkit.getPluginManager().registerEvents(new UserListener(), this);
        registerCommands();
        Bukkit.getConsoleSender().sendMessage("§6[TJ-MobCoins] §ePlugin iniciado com sucesso.");
    }

    private void registerCommands() {
        new MobCoinCommand(new SubCommandAdd("add", "adicionar"),
                new SubCommandHelp("help", "ajuda"),
                new SubCommandRemove("remove", "remover"),
                new SubCommandSet("set", "definir"))
                .register(this, "mobcoins");
    }

    private void loadConfiguration() {
        getConfig().options().copyDefaults(false);
        saveDefaultConfig();
    }
}
