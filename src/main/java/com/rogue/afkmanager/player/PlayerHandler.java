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
package com.rogue.afkmanager.player;

import java.util.HashMap;
import org.bukkit.Location;

/**
 *
 * @since 0.1
 * @author 1Rogue
 * @version 0.1
 */
public class PlayerHandler {
    
    private HashMap<String, LMPlayer> players = new HashMap<String, LMPlayer>();
    
    /**
     * Updates the AFK-start time for the provided player.
     * 
     * @since 0.1
     * @version 0.1
     * 
     * @param name The relevant player's name
     * @param time The time being AFK started
     */
    public void updatePlayer(String name, int time) {
        LMPlayer temp = this.getPlayer(name);
        temp.setTime(time);
        this.putPlayer(name, temp);
    }
    
    /**
     * Updates the AFK-start location for the provided player.
     * 
     * @since 0.1
     * @version 0.1
     * 
     * @param name The relevant player's name
     * @param place The place the player was at the start of being AFK
     */
    public void updatePlayer(String name, Location place) {
        LMPlayer temp = this.getPlayer(name);
        temp.setSavedLocation(place);
        this.putPlayer(name, temp);
    }
    
    /**
     * Adds a player to the Plugin's tracked list of players.
     * 
     * @since 0.1
     * @version 0.1
     * 
     * @param name The player name
     * @param time The AFK-start time
     * @param location The location when a player went AFK
     */
    public void putPlayer(String name, int time, Location location) {
        this.putPlayer(name.toLowerCase().trim(), new LMPlayer(name.toLowerCase().trim(), time, location));
    }
    
    /**
     * Adds an LMPlayer to the plugin's tracked list of players.
     * 
     * @since 0.1
     * @version 0.1
     * 
     * @param name The player name
     * @param player The LMPlayer object of the player
     */
    public void putPlayer(String name, LMPlayer player) {
        players.put(name.toLowerCase().trim(), player);
    }
    
    /**
     * Removes a player from the plugin's tracked list of players
     * 
     * @since 0.1
     * @version 0.1
     * 
     * @param name The player name
     */
    public void remPlayer(String name) {
        players.remove(name.toLowerCase().trim());
    }
    
    /**
     * Gets the plugin's instance of the player
     * 
     * @since 0.1
     * @version 0.1
     * 
     * @param name The player name
     * @return The player instance
     */
    public LMPlayer getPlayer(String name) {
        return players.get(name.toLowerCase().trim());
    }
    
    /**
     * Gets the time the player went AFK. 0 if they are not AFK.
     * 
     * @since 0.1
     * @version 0.1
     * 
     * @param name The player name
     * @return AFK Starting time for the player
     */
    public int checkTime(String name) {
        return this.getPlayer(name).getTime();
    }
    
    /**
     * Gets the location where the player went AFK. Returns null if they have
     * never gone AFK.
     * 
     * @since 0.1
     * @version 0.1
     * 
     * @param name The player name
     * @return Location where a player went AFK
     */
    public Location checkLocation(String name) {
        return this.getPlayer(name).getSavedLocation();
    }
    
    /**
     * Sets whether or not the player is AFK.
     * 
     * @since 0.1
     * @version 0.1
     * 
     * @param name The player name
     * @param value Whether or not the player is AFK
     */
    public void changeAFK(String name, boolean value) {
        this.getPlayer(name).setAFK(value);
    }
    
    /**
     * Returns the map of all LMPlayers.
     * 
     * @since 0.1
     * @version 0.1
     * 
     * @return Map of LMPlayers players
     */
    public HashMap<String, LMPlayer> getPlayers() {
        return players;
    }

}
