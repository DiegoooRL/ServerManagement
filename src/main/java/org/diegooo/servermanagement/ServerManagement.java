package org.diegooo.servermanagement;

import org.bukkit.plugin.java.JavaPlugin;
import org.diegooo.servermanagement.commands.ServerManagementCommand;
import org.diegooo.servermanagement.commands.SetSpawnCommand;
import org.diegooo.servermanagement.commands.SpawnCommand;
import org.diegooo.servermanagement.events.CannotExceedMaxPlayers;
import org.diegooo.servermanagement.serverutils.SetIcon;
import org.diegooo.servermanagement.serverutils.SetMOTD;
import org.diegooo.servermanagement.serverutils.SetMaxPlayers;

public final class ServerManagement extends JavaPlugin {

    @Override
    public void onEnable() {
        registerCommands();
        registerListeners();

    }

    @Override
    public void onDisable() {

    }

    private void registerCommands() {
        getCommand("setmaxplayers").setExecutor(new SetMaxPlayers());
        getCommand("setmotd").setExecutor(new SetMOTD());
        getCommand("seticon").setExecutor(new SetIcon());
        getCommand("spawn").setExecutor(new SpawnCommand());
        getCommand("servermanagement").setExecutor(new ServerManagementCommand());
        getCommand("setworldspawn").setExecutor(new SetSpawnCommand());
    }

    private void registerListeners() {
        getServer().getPluginManager().registerEvents(new CannotExceedMaxPlayers(), this);

    }

}
