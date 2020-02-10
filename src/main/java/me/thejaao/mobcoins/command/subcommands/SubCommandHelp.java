package me.thejaao.mobcoins.command.subcommands;

import me.thejaao.mobcoins.models.IArgument;
import org.bukkit.command.CommandSender;

public class SubCommandHelp extends IArgument {

    public SubCommandHelp(String name, String... alias) {
        super(name, alias);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (!sender.hasPermission("tjmobcoins.admin")) {
            sender.sendMessage("§cVocê não possui permissão para executar esta ação.");
            return;
        }
        if (args[0].equalsIgnoreCase("help")) {
            sender.sendMessage("§1");
            sender.sendMessage("§5&l TJ_MOBCOINS");
            sender.sendMessage("§2");
            sender.sendMessage("§d /mobcoins <player> §8- §7Quantidade de mobcoin de um player.");
            sender.sendMessage("§d /mobcoins set <player> <valor> §8- §7Define mobcoin à um player.");
            sender.sendMessage("§d /mobcoins add <player> <valor> §8- §7Adiciona mobcoin à um player.");
            sender.sendMessage("§d /mobcoins remove <player> <valor> §8- §7Remove mobcoin de um player.");
            sender.sendMessage("§1");
        }
    }
}
