package org.diegooo.servermanagement.menu;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.diegooo.servermanagement.ServerManagement;
import org.diegooo.servermanagement.utils.CC;

public class MenuHandler implements Listener {

    private static final String MENU_TITLE = CC.chat("&b&lServer Management Menu");
    private static final String CHAT_SETTINGS_ITEM_NAME = CC.chat("&bChat Settings");
    private static final String ENABLE_CHAT_LORE = CC.chat("&7Click to &aenable &7chatting.");
    private static final String DISABLE_CHAT_LORE = CC.chat("&7Click to &cdisable &7chatting.");

    @EventHandler
    public void onMenuOpen(InventoryClickEvent event) {
        Inventory inventory = event.getInventory();

        if (!MENU_TITLE.equalsIgnoreCase(inventory.getTitle())) {
            return;
        }

        event.setCancelled(true);

        ItemStack currentItem = event.getCurrentItem();
        if (currentItem == null || !currentItem.hasItemMeta()) {
            return;
        }

        ItemMeta itemMeta = currentItem.getItemMeta();
        if (itemMeta == null || !itemMeta.hasLore() || !itemMeta.hasDisplayName()) {
            return;
        }

        Player player = (Player) event.getWhoClicked();

        if (!itemMeta.getDisplayName().equals(CHAT_SETTINGS_ITEM_NAME)) {
            return;
        }

        boolean allowChatting = itemMeta.getLore().contains(ENABLE_CHAT_LORE);
        ServerManagement.getPlugin().getConfig().set("allowChatting", allowChatting);
        ServerManagement.getPlugin().saveConfig();

        if (allowChatting) {
            player.sendMessage(CC.chat("&8&l[&b&lServer Management&8&L] &aChatting has been enabled."));
        } else {
            player.sendMessage(CC.chat("&8&l[&b&lServer Management&8&L] &cChatting has been disabled."));
        }


        SettingsMenu.updateSettingsMenu(player);
    }
}
