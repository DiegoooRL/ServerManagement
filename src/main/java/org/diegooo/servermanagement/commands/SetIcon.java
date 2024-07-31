package org.diegooo.servermanagement.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.diegooo.servermanagement.utils.CC;

import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class SetIcon implements CommandExecutor {
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
        if (args.length != 1) {
            player.sendMessage(CC.chat("&8&l[&b&lServer Management&8&L] &cIncorrect usage! Please provide a URL (IMGUR) for the icon."));
            return true;
        }

        String imageUrl = args[0];

        try {
            setServerIcon(imageUrl);
            player.sendMessage(CC.chat("&8&l[&b&lServer Management&8&L] &7Server icon has been &bupdated&7!"));
        } catch (IOException e) {
            player.sendMessage(CC.chat("&8&l[&b&lServer Management&8&L] &cAn error occurred while updating the server icon."));
            e.printStackTrace();
        }

        return true;
    }

    private void setServerIcon(String imageUrl) throws IOException {
        URL url = new URL(imageUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setDoInput(true);
        connection.connect();

        BufferedImage bufferedImage;
        try (InputStream in = connection.getInputStream()) {
            bufferedImage = ImageIO.read(in);
        }

        if (bufferedImage == null) {
            throw new IOException("Failed to read image from URL.");
        }

        // Resize image to 64x64 if necessary
        BufferedImage resizedImage = new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(bufferedImage, 0, 0, 64, 64, null);
        g.dispose();

        File iconFile = new File("server-icon.png");
        try (FileOutputStream out = new FileOutputStream(iconFile)) {
            ImageIO.write(resizedImage, "png", out);
        }
    }
}
