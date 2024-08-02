package org.diegooo.servermanagement.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.diegooo.servermanagement.utils.CC;

public class ServerManagementCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage("&cYou must be a player to use this command!");
            return true;
        }


        Player player = (Player) sender;
        if(!player.hasPermission("servermanagement.*")) {
            player.sendMessage("&cYou don't have permission to use this command!");
            return true;
        }
        player.sendMessage(CC.chat("&8&m-----------------------------"));
        player.sendMessage(CC.chat("&3&lServer Management Help&7"));
        player.sendMessage(" ");
        player.sendMessage(CC.chat("&b❀ &7/settings <world> &8&l| &7opens the &bmenu"));
        player.sendMessage(CC.chat("&b❀ &7/setspawnworld <world> &8&l| &7sets spawn of &bworlds"));
        player.sendMessage(CC.chat("&b❀ &7/setmaxplayers <amount> &8&l| &7sets &bmax&7 players"));
        player.sendMessage(CC.chat("&b❀ &7/setmotd <motd> &8&l| &7sets the &bserver&7 motd"));
        player.sendMessage(CC.chat("&b❀ &7/seticon <img url> &8&l| &7sets the &bserver&7 icon"));
        player.sendMessage(CC.chat("&b❀ &7BETA-RELEASE &8&l| &7Made by &bDiegooo"));
        player.sendMessage(CC.chat("&8&m-----------------------------"));


        return false;
    }
}
