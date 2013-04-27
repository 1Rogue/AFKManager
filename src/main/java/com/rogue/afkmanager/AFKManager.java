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

import com.rogue.afkmanager.configuration.Configuration;
import com.rogue.afkmanager.runnable.UpdateRunnable;
import java.util.logging.Level;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
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

    @Override
    public void onLoad() {
        configs = new Configuration();
        configs.loadDefaults();

        if (configs.getBoolean("update-check")) {
            Bukkit.getScheduler().runTaskLater(this, new UpdateRunnable(), 1);
        }
    }

    @Override
    public void onEnable() {
        perms = new PermManager(this.getServer().getPluginManager().getPlugin("Vault").isEnabled());
        this.getLogger().log(Level.INFO, "{0} is enabled!", this.getName());
    }

    @Override
    public void onDisable() {
        this.getLogger().log(Level.INFO, "{0} is disabled!", this.getName());
    }

    public static Plugin getPlugin() {
        return Bukkit.getServer().getPluginManager().getPlugin("SayFixer");
    }

    public PermManager getPermManager() {
        return perms;
    }
}
