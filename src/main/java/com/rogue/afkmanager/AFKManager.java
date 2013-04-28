/*
 * Copyright (C) 2013 Spencer Alderman
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.rogue.afkmanager;

import com.rogue.afkmanager.permission.PermManager;
import com.rogue.afkmanager.command.CommandHandler;
import com.rogue.afkmanager.configuration.Configuration;
import com.rogue.afkmanager.listener.AFKListener;
import com.rogue.afkmanager.player.PlayerHandler;
import com.rogue.afkmanager.runnable.UpdateRunnable;
import java.util.logging.Level;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @since 0.1
 * @author 1Rogue
 * @version 0.1
 */
public class AFKManager extends JavaPlugin {

    private PermManager perms;
    private Configuration configs;
    private AFKListener listener;
    private PlayerHandler phandle;
    private CommandHandler chandle;
    
    /**
     * Loads config files and checks for updates on server loading.
     * 
     * @since 0.1
     * @version 0.1
     */
    @Override
    public void onLoad() {
        this.getLogger().log(Level.INFO, "Loading configs");
        configs = new Configuration();
        configs.loadDefaults();
        
        this.getLogger().log(Level.INFO, "Running update check");
        this.getLogger().log(Level.INFO, "This can be disabled in your config.yml");
        if (configs.getBoolean("update-check")) {
            Bukkit.getScheduler().runTaskLater(this, new UpdateRunnable(), 1);
        }
    }

    /**
     * Creates an instance of the permissions manager and listener.
     * 
     * @since 0.1
     * @version 0.1
     */
    @Override
    public void onEnable() {
        this.getLogger().log(Level.INFO, "Enabling Listener");
        listener = new AFKListener(this);
        Bukkit.getPluginManager().registerEvents(listener, this);
        
        this.getLogger().log(Level.INFO, "Enabling Permissions");
        perms = new PermManager(this.getServer().getPluginManager().getPlugin("Vault").isEnabled());
        
        this.getLogger().log(Level.INFO, "Enabling Player Handler");
        phandle = new PlayerHandler();
        
        this.getLogger().log(Level.INFO, "Enabling Command Handler");
        chandle = new CommandHandler();
        this.getLogger().log(Level.INFO, "{0} is enabled!", this.getName());
    }
    
    /**
     * Currently serves no purpose, will most likely stop the AFK runnable in
     * the future.
     * 
     * @since 0.1
     * @version 0.1
     */
    @Override
    public void onDisable() {
        this.getLogger().log(Level.INFO, "{0} is disabled!", this.getName());
    }
    
    /**
     * Gets the instance of the plugin in its entirety.
     * 
     * @since 0.1
     * @version 0.1
     * 
     * @return The plugin instance
     */
    public static AFKManager getPlugin() {
        return (AFKManager)Bukkit.getServer().getPluginManager().getPlugin("AFKManager");
    }
    
    /**
     * Gets the instance of the permissions manager.
     * 
     * @since 0.1
     * @version 0.1
     * 
     * @return AFKManager's permissions manager
     */
    public PermManager getPermManager() {
        return perms;
    }
    
    /**
     * Gets the instance of the listener.
     * 
     * @since 0.1
     * @version 0.1
     * 
     * @return AFKManager's listener
     */
    public AFKListener getListener() {
        return listener;
    }
    
    /**
     * Gets the instance of the player handler.
     * 
     * @since 0.1
     * @version 0.1
     * 
     * @return AFKManager's player handler
     */
    public PlayerHandler getPlayerHandler() {
        return phandle;
    }
    
    /**
     * Gets the instance of the command handler.
     * 
     * @since 0.1
     * @version 0.1
     * 
     * @return AFKManager's command handler
     */
    public CommandHandler getCommandHandler() {
        return chandle;
    }
}
