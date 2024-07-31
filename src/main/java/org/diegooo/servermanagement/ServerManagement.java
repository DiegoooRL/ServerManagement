package org.diegooo.servermanagement;

import org.bukkit.plugin.java.JavaPlugin;
import org.diegooo.servermanagement.commands.ServerManagementCommand;
import org.diegooo.servermanagement.commands.SetMaxPlayers;
import org.diegooo.servermanagement.events.CannotExceedMaxPlayers;

public final class ServerManagement extends JavaPlugin {

    @Override
    public void onEnable() {
        registerCommands();
        registerListeners();

    }

    @Override
    public void onDisable() {

    }

    private void registerCommands(){
        getCommand("setmaxplayers").setExecutor(new SetMaxPlayers());
        getCommand("servermanagement").setExecutor(new ServerManagementCommand());
    }

    private void registerListeners(){
        getServer().getPluginManager().registerEvents(new CannotExceedMaxPlayers(), this);

    }

}
