package me.thejaao.mobcoins.impl;

import org.bukkit.command.CommandSender;

public interface Executable {

    void execute(CommandSender sender, String[] args);
}
