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
package com.rogue.afkmanager.utils;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;

/**
 *
 * @since
 * @author 1Rogue
 * @version
 */
public class Threaded {
    
     /**
     * Gets the Bukkit instance of a Player
     * 
     * @since 0.1
     * @version 0.1
     * 
     * @param name The player name
     * @return The player objects
     */
    public Player getPlayer(String name) {
        return Bukkit.getServer().getPlayer(name);
    }
    
    public World getWorld(String name) {
        return Bukkit.getServer().getWorld(name);
    }

}
