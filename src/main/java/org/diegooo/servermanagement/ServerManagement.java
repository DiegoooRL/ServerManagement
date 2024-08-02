package org.diegooo.servermanagement;

import org.bukkit.plugin.java.JavaPlugin;
import org.diegooo.servermanagement.chat.ChatListener;
import org.diegooo.servermanagement.commands.ServerManagementCommand;
import org.diegooo.servermanagement.commands.SetSpawnCommand;
import org.diegooo.servermanagement.commands.SpawnCommand;
import org.diegooo.servermanagement.events.CannotExceedMaxPlayers;
import org.diegooo.servermanagement.menu.MenuHandler;
import org.diegooo.servermanagement.menu.SettingsMenu;
import org.diegooo.servermanagement.serverutils.SetIcon;
import org.diegooo.servermanagement.serverutils.SetMOTD;
import org.diegooo.servermanagement.serverutils.SetMaxPlayers;

public final class ServerManagement extends JavaPlugin {

    private static ServerManagement plugin;

    @Override
    public void onEnable() {

        plugin = this;
        getConfig().options().copyDefaults();
        saveDefaultConfig();

        registerCommands();
        registerListeners();

    }

    @Override
    public void onDisable() {

    }

    private void registerCommands() {
        getCommand("setmaxplayers").setExecutor(new SetMaxPlayers());
        getCommand("settings").setExecutor(new SettingsMenu());
        getCommand("setmotd").setExecutor(new SetMOTD());
        getCommand("seticon").setExecutor(new SetIcon());
        getCommand("spawn").setExecutor(new SpawnCommand());
        getCommand("servermanagement").setExecutor(new ServerManagementCommand());
        getCommand("setworldspawn").setExecutor(new SetSpawnCommand());
    }

    private void registerListeners() {
        getServer().getPluginManager().registerEvents(new CannotExceedMaxPlayers(), this);
        getServer().getPluginManager().registerEvents(new MenuHandler(), this);
        getServer().getPluginManager().registerEvents(new ChatListener(), this);

    }

    public static ServerManagement getPlugin() {
        return plugin;
    }

}
