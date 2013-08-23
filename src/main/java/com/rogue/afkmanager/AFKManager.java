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

import com.rogue.afkmanager.command.CommandHandler;
import com.rogue.afkmanager.configuration.Configuration;
import com.rogue.afkmanager.listener.AFKListener;
import com.rogue.afkmanager.player.PlayerHandler;
import com.rogue.afkmanager.runnable.AFKRunnable;
import com.rogue.afkmanager.runnable.UpdateRunnable;
import java.io.File;
import java.util.logging.Level;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @since 1.0.0
 * @author 1Rogue
 * @version 1.0.0
 */
public class AFKManager extends JavaPlugin {

    private Configuration configs;
    private AFKListener listener;
    private PlayerHandler phandle;
    private CommandHandler chandle;
    private int debug = 0;

    /**
     * Loads config files and checks for updates on server loading.
     *
     * @since 1.0.0
     * @version 1.0.0
     */
    @Override
    public void onLoad() {

        if (!this.getDataFolder().exists()) {
            this.getDataFolder().mkdirs();
        }
        if (!(new File(getDataFolder(), "config.yml").exists())) {
            this.saveResource("config.yml", true);
        }
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
     * Creates an instance of the permissions manager, listener, player handler,
     * command handler, and sets the AFK checking task.
     *
     * @since 1.0.0
     * @version 1.0.0
     */
    @Override
    public void onEnable() {
        
        debug = configs.getInt("general.debug-level");
        if (debug > 3) {
            debug = 3;
        }
        if (debug < 0) {
            debug = 0;
        }
        if (debug >= 1) {
            this.getLogger().log(Level.INFO, "Debug level set to {0}!", debug);
        }
        
        this.getLogger().log(Level.INFO, "Enabling Listener");
        listener = new AFKListener(this);
        Bukkit.getPluginManager().registerEvents(listener, this);

        this.getLogger().log(Level.INFO, "Enabling Player Handler");
        phandle = new PlayerHandler(this, configs.getInt("afk.check-interval"), configs.getInt("afk.timeout"));

        this.getLogger().log(Level.INFO, "Enabling Command Handler");
        chandle = new CommandHandler();

        long interval = this.getConfig().getInt("afk.check-interval") * 20; // multiplied by 20, due to a server tick being 1/20th of a second
        Bukkit.getServer().getScheduler().runTaskTimer(this, new AFKRunnable(this), interval, interval);

        this.getLogger().log(Level.INFO, "{0} is enabled!", this.getName());
    }

    /**
     * Currently serves no purpose, will most likely stop the AFK runnable in
     * the future.
     *
     * @since 1.0.0
     * @version 1.0.0
     */
    @Override
    public void onDisable() {
        this.getLogger().log(Level.INFO, "{0} is disabled!", this.getName());
    }
    
    /**
     * Gets the level of debugging for players
     *
     * @since 1.0.0
     * @version 1.0.0
     *
     * @return The debug level
     */
    public int getDebug() {
        return debug;
    }

    /**
     * Gets the instance of the plugin in its entirety.
     *
     * @since 1.0.0
     * @version 1.0.0
     *
     * @return The plugin instance
     */
    public static AFKManager getPlugin() {
        return (AFKManager) Bukkit.getServer().getPluginManager().getPlugin("AFKManager");
    }

    /**
     * Gets the instance of the listener.
     *
     * @since 1.0.0
     * @version 1.0.0
     *
     * @return AFKManager's listener
     */
    public AFKListener getListener() {
        return listener;
    }

    /**
     * Gets the instance of the player handler.
     *
     * @since 1.0.0
     * @version 1.0.0
     *
     * @return AFKManager's player handler
     */
    public PlayerHandler getPlayerHandler() {
        return phandle;
    }

    /**
     * Gets the instance of the command handler.
     *
     * @since 1.0.0
     * @version 1.0.0
     *
     * @return AFKManager's command handler
     */
    public CommandHandler getCommandHandler() {
        return chandle;
    }

    /**
     * Gets the instance of the configuration
     *
     * @since 1.0.0
     * @version 1.0.0
     *
     * @return The plugin's configuration manager
     */
    public Configuration getConfiguration() {
        return configs;
    }
}
