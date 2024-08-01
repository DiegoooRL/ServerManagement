package org.diegooo.servermanagement.serverutils;

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

public class SetMOTD implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage("You must be a player to use this command!");
            return true;
        }

        if (!sender.hasPermission("servermanagement.*")) {
            sender.sendMessage("You don't have permission to use this command!");
            return true;
        }

        Player player = (Player) sender;
        if (args.length == 0) {
            player.sendMessage(CC.chat("&8&l[&b&lServer Management&8&L] &cIncorrect usage! Please provide a message for the MOTD."));
            return true;
        }

        StringBuilder motdMessage = new StringBuilder();
        for (String arg : args) {
            motdMessage.append(arg).append(" ");
        }

        String motd = motdMessage.toString().trim();
        String coloredMotd = motd.replace("&", "§"); // Convert '&' to '§'

        try {
            setMOTDInConfig(coloredMotd);
            player.sendMessage(CC.chat("&8&l[&b&lServer Management&8&L] &7MOTD has been &bupdated&7!"));
            player.sendMessage(CC.chat("&8&l[&b&lServer Management&8&L] &7New MOTD: &b" + motd));
        } catch (IOException e) {
            player.sendMessage(CC.chat("&8&l[&b&lServer Management&8&L] &cAn error occurred while updating the MOTD."));
            e.printStackTrace();
        }

        return true;
    }

    private void setMOTDInConfig(String motdMessage) throws IOException {
        File configFile = new File("server.properties");
        Properties properties = new Properties();

        try (FileInputStream in = new FileInputStream(configFile)) {
            properties.load(in);
        }

        properties.setProperty("motd", motdMessage);

        try (FileOutputStream out = new FileOutputStream(configFile)) {
            properties.store(out, null);
        }
    }
}
