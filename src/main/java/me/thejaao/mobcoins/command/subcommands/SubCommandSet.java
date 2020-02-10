package me.thejaao.mobcoins.command.subcommands;

import me.thejaao.mobcoins.helper.BalanceHelper;
import me.thejaao.mobcoins.managers.UserManager;
import me.thejaao.mobcoins.models.IArgument;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static me.thejaao.mobcoins.TJMobCoins.*;

public class SubCommandSet extends IArgument {

    public SubCommandSet(String name, String... alias) {
        super(name, alias);
    }

    private UserManager USER_MANAGER = getInstance().getUserManager();

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (!sender.hasPermission("tjmobcoins.admin")) {
            sender.sendMessage("§cVocê não possui permissão para executar esta ação.");
            return;
        }
        if (args.length != 3) {
            sender.sendMessage("§cUtilize: /mobcoins set <player> <quantidade>.");
            return;
        }
        Player target = Bukkit.getPlayer(args[1]);
        if (!target.isOnline()) {
            sender.sendMessage("§cO player " + args[1] + "§c não foi encontrado.");
            return;
        }
        if (isDouble(args[2])) {
            USER_MANAGER.setMobCoins(target.getName(), Double.parseDouble(args[2]));
            sender.sendMessage("§aForam definidos §d" + BalanceHelper.prefix(Double.parseDouble(args[2])) + "§a de mobcoin para §d" + target.getName() + "§a.");

        } else sender.sendMessage("§cO valor inserido não é um número.");

    }
}
