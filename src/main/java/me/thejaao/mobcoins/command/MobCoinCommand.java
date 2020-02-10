package me.thejaao.mobcoins.command;

import me.thejaao.mobcoins.helper.BalanceHelper;
import me.thejaao.mobcoins.managers.UserManager;
import me.thejaao.mobcoins.models.IArgument;
import me.thejaao.mobcoins.models.ICommand;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import static me.thejaao.mobcoins.TJMobCoins.*;

public class MobCoinCommand extends ICommand {

    public MobCoinCommand(IArgument... arguments) {
        super(arguments);
    }

    private UserManager USER_MANAGER = getInstance().getUserManager();

    @Override
    public void execute(CommandSender sender, String[] args) {

        if (argIs(0)) {
            if (!(sender instanceof Player)) return;
            double mobCoins = USER_MANAGER.getMobCoins(sender.getName());
            sender.sendMessage("§aVocê possui: §d" + BalanceHelper.prefix(mobCoins) + "§a mobcoins.");
            return;
        }
        if (!argumentNotFound()) {
            Player target = Bukkit.getPlayer(args[0]);
            if (target == null) {
                sender.sendMessage("§cEste jogador encontra-se offline.");
                return;
            }
            double targetBalance = USER_MANAGER.getMobCoins(target.getName());
            sender.sendMessage("§aMobCoins de §7" + target.getName() + "§a: §f" + BalanceHelper.prefix(targetBalance));
        }

    }
}
