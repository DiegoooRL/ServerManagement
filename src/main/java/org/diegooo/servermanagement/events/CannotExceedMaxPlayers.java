package org.diegooo.servermanagement.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.diegooo.servermanagement.utils.CC;

public class CannotExceedMaxPlayers implements Listener {

    @EventHandler
    public void onPlayerLogin(PlayerLoginEvent event) {
        int maxPlayers = event.getPlayer().getServer().getMaxPlayers();
        int onlinePlayers = event.getPlayer().getServer().getOnlinePlayers().size();

        if (onlinePlayers >= maxPlayers) {
            event.disallow(PlayerLoginEvent.Result.KICK_FULL, CC.chat("&8&l[&b&lServer Manager&8&l] &cServer is full."));
        }

        if (event.getPlayer().hasPermission("servermanager.*")) {
            event.allow();
        }
    }

}
