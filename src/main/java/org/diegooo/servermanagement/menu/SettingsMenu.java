package org.diegooo.servermanagement.menu;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.diegooo.servermanagement.utils.CC;

public class SettingsMenu implements CommandExecutor {

    private static final String MENU_TITLE = CC.chat("&b&lServer Management Menu");

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("You must be a player to use this command!");
            return true;
        }

        if (!(sender.hasPermission("servermanagement.*"))) {
            sender.sendMessage(CC.chat("&cYou don't have permission to use this command!"));
            return true;
        }

        Player player = (Player) sender;
        openSettingsMenu(player);
        return true;
    }

    public static void openSettingsMenu(Player player) {
        Inventory settingsMenu = Bukkit.createInventory(null, 27, MENU_TITLE);

        settingsMenu.setItem(13, SettingsItems.chatItem());

        player.openInventory(settingsMenu);
    }

    public static void updateSettingsMenu(Player player) {
        Inventory settingsMenu = player.getOpenInventory().getTopInventory();

        if (settingsMenu != null && MENU_TITLE.equals(settingsMenu.getTitle())) {
            settingsMenu.setItem(13, SettingsItems.chatItem());
            player.updateInventory();
        }
    }
}
