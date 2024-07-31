package org.diegooo.servermanagement;

import org.bukkit.plugin.java.JavaPlugin;
import org.diegooo.servermanagement.commands.ServerManagementCommand;
import org.diegooo.servermanagement.commands.SetIcon;
import org.diegooo.servermanagement.commands.SetMOTD;
import org.diegooo.servermanagement.commands.SetMaxPlayers;
import org.diegooo.servermanagement.events.CannotExceedMaxPlayers;
import org.diegooo.servermanagement.menu.ServerManagementGUI;

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
        getCommand("setmotd").setExecutor(new SetMOTD());
        getCommand("seticon").setExecutor(new SetIcon());
        getCommand("servermanagement").setExecutor(new ServerManagementCommand());
        getCommand("servermanagementmenu").setExecutor(new ServerManagementGUI());
    }

    private void registerListeners(){
        getServer().getPluginManager().registerEvents(new CannotExceedMaxPlayers(), this);

    }

}
