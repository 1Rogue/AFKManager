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
package com.rogue.afkmanager.configuration;

import com.rogue.afkmanager.AFKManager;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import org.bukkit.configuration.file.FileConfiguration;

/**
 * @since 0.1
 * @author 1Rogue
 * @version 0.1
 */
public class Configuration {

    FileConfiguration config;
    File configPath;

    public Configuration() {
        config = AFKManager.getPlugin().getConfig();
        configPath = new File(AFKManager.getPlugin().getDataFolder(), "config.yml");
    }
    
    /**
     * Checks the validity of the config file, and if it exists. This will make
     * sure the config is in the data folder.
     * 
     * @since 0.1
     * @version 0.1
     */
    public void loadDefaults() {
        if (!configPath.exists()) {
            AFKManager.getPlugin().saveDefaultConfig();
        }

        if (!config.contains("update-check")) {
            config.set("update-check", true);
        }
        
        try {
            config.save(configPath);
        } catch (IOException e) {
            AFKManager.getPlugin().getLogger().log(Level.SEVERE, null, e);
        }
    }
    
    /**
     * Gets a string value from the YAML config file.
     * 
     * @since 0.1
     * @version 0.1
     * 
     * @param path The path to the string value, using dots as delimiters
     * @return The string's value
     */
    public String getString(String path) {
        return config.getString(path, null);
    }
    
    /**
     * Gets a boolean value from the YAML config file.
     * 
     * @since 0.1
     * @version 0.1
     * 
     * @param path The path to the boolean value, using dots as delimiters
     * @return The boolean's value
     */
    public boolean getBoolean(String path) {
        return config.getBoolean(path);
    }
}
