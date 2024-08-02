package org.diegooo.servermanagement.chat;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.diegooo.servermanagement.ServerManagement;
import org.diegooo.servermanagement.utils.CC;


public class ChatListener implements Listener {

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {

        event.setFormat(CC.chat("&b" + event.getPlayer().getName() + "&7: &f" + event.getMessage()));

        if (!ServerManagement.getPlugin().getConfig().getBoolean("allowChatting")
                && !event.getPlayer().hasPermission("servermanagement.*")) {
            event.setCancelled(true);
            event.getPlayer().sendMessage(CC.chat("&8&l[&b&lServer Management&8&L] &cChat has been disabled."));
            return;
        }

    }
}
