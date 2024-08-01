package org.diegooo.servermanagement.commands;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.diegooo.servermanagement.utils.CC;

public class SpawnCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("This command can only be executed by a player");
            return true;
        }

        if (!(sender.hasPermission("servermanagement.*"))) {
            sender.sendMessage(CC.chat("&cYou don't have permission to use this command"));
            return true;
        }

        if (args.length == 0) {
            sender.sendMessage(CC.chat("&8&l[&b&lServer Management&8&L] &cUsage: /spawn <world>"));
            return true;
        }

        String world = args[0];
        World bukkitWorld = Bukkit.getWorld(world);


        if (bukkitWorld == null) {
            sender.sendMessage(CC.chat("&8&l[&b&lServer Management&8&L] &cWorld &4" + world + " &cnot found"));
            return true;
        }

        Player player = (Player) sender;
        player.teleport(bukkitWorld.getSpawnLocation());
        player.sendMessage(CC.chat("&8&l[&b&lServer Management&8&L] &7Teleported you to the spawn of &b" + world));
        return false;
    }
}
