package org.diegooo.servermanagement.menu;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.diegooo.servermanagement.utils.CC;

public class ServerManagementGUI implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage("This command can only be executed by a player");
            return true;

        }

        if(!sender.hasPermission("servermanagement.*")) {
            sender.sendMessage(CC.chat("&cYou don't have permission to use this command"));
            return true;
        }

        Inventory serverManagementMenu = Bukkit.createInventory(null, 27, CC.chat("&b&lServer Management Menu"));

        Player player = (Player) sender;

        player.openInventory(serverManagementMenu);

        return false;
    }
}
