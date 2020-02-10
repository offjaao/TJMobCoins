package me.thejaao.mobcoins.models;

import me.thejaao.mobcoins.impl.Executable;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public abstract class ICommand implements CommandExecutor, Executable {

    private CommandSender sender;
    private String[] args;

    private IArgument[] arguments;

    private boolean argumentFound;

    public ICommand() { }

    public ICommand(IArgument... arguments) {
        this.arguments = arguments;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        //if (sender instanceof ConsoleCommandSender) return false;

        this.sender = sender;
        this.args = args;

        if (!argIs(0)) {
            String argument0 = args[0];
            for (IArgument argument : arguments) {
                if (argument.equals(argument0)) {
                    argument.execute(sender, args);
                    argumentFound = true;
                    return false;
                }
            }
        }

        argumentFound = false;

        execute(sender, args);
        return false;
    }

    public boolean hasPermission(String permission) {
        Player player = (Player) sender;
        player.playSound(player.getLocation(), Sound.VILLAGER_NO, 1.0f, 1.0f);
        return player.hasPermission(permission);
    }

    public boolean argumentNotFound() {
        return argumentFound;
    }

    public boolean argIs(int length) {
        return args.length == length;
    }

    public void register(JavaPlugin plugin, String name) {
        plugin.getCommand(name).setExecutor(this);

        System.out.println("Command \"" + name + "\" registered");
    }
}
