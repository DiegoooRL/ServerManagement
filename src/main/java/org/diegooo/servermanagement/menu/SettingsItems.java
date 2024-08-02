package org.diegooo.servermanagement.menu;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.diegooo.servermanagement.ServerManagement;
import org.diegooo.servermanagement.utils.CC;

import java.util.Arrays;

public class SettingsItems {

    public static ItemStack chatItem() {
        ItemStack chatSetting = new ItemStack(Material.PAPER);
        ItemMeta chatMeta = chatSetting.getItemMeta();
        chatMeta.setDisplayName(CC.chat("&bChat Settings"));

        if (ServerManagement.getPlugin().getConfig().getBoolean("allowChatting")) {
            chatMeta.setLore(Arrays.asList(
                    CC.chat("&3❀ &7Chatting: &aAllowed"),
                    CC.chat(" "),
                    CC.chat("&7Click to &cdisable &7chatting.")));
        } else {
            chatMeta.setLore(Arrays.asList(
                    CC.chat("&3❀ &7Chatting: &cDisallowed"),
                    CC.chat(" "),
                    CC.chat("&7Click to &aenable &7chatting.")));
        }

        chatSetting.setItemMeta(chatMeta);
        return chatSetting;
    }
}
