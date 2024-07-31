package org.diegooo.servermanagement.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.diegooo.servermanagement.utils.CC;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class SetMaxPlayers implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(CC.chat("&cOnly players can execute this command!"));
            return true;
        }

        if (!sender.hasPermission("servermanagement.setmaxplayers")) {
            sender.sendMessage(CC.chat("&cYou don't have permission to use this command!"));
            return true;
        }
        Player player = (Player) sender;
        if (args.length > 0) {
            try {
                int setMaxPlayers = Integer.parseInt(args[0]);
                setMaxPlayersInConfig(setMaxPlayers);
                player.sendMessage(CC.chat("&8&l[&b&lServer Management&8&L] &7You have set the &bmax &7players &7to &b" + setMaxPlayers + "&7!"));
            } catch (NumberFormatException e) {
                player.sendMessage(CC.chat("&8&l[&b&lServer Management&8&L] &cInvalid number format!"));
            } catch (Exception e) {
                player.sendMessage(CC.chat("&8&l[&b&lServer Management&8&L] &cAn error occurred while setting the max players."));
                e.printStackTrace();
            }
        } else {
            player.sendMessage(CC.chat("&8&l[&b&lServer Management&8&L] &cCorrect Usage: /setmaxplayers <amount>"));
        }
        return true;
    }

    private void setMaxPlayersInConfig(int maxPlayers) throws IOException {
        File configFile = new File("server.properties");
        Properties properties = new Properties();

        try (FileInputStream in = new FileInputStream(configFile)) {
            properties.load(in);
        }

        properties.setProperty("max-players", String.valueOf(maxPlayers));

        try (FileOutputStream out = new FileOutputStream(configFile)) {
            properties.store(out, null);
        }
    }
}
