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
import java.util.Map;
import org.bukkit.Location;

/**
 *
 * @since 0.1
 * @author 1Rogue
 * @version 0.1
 */
public class PlayerHandler {
    
    private Map<String, LMPlayer> players = new HashMap<String, LMPlayer>();
    
    public void updatePlayer(String name, Long time) {
        LMPlayer temp = this.getPlayer(name);
        temp.setTime(time);
        this.putPlayer(name, temp);
    }
    
    public void updatePlayer(String name, Location place) {
        LMPlayer temp = this.getPlayer(name);
        temp.setSavedLocation(place);
        this.putPlayer(name, temp);
    }
    
    public void putPlayer(String name, Long time, Location location) {
        LMPlayer temp = new LMPlayer(time, location);
        players.put(name.toLowerCase().trim(), temp);
    }
    public void putPlayer(String name, LMPlayer player) {
        players.put(name.toLowerCase().trim(), player);
    }
    
    public void remPlayer(String name) {
        players.remove(name.toLowerCase().trim());
    }
    
    public LMPlayer getPlayer(String name) {
        return players.get(name.toLowerCase().trim());
    }
    
    public Long checkTime(String name) {
        return this.getPlayer(name).getTime();
    }
    
    public Location checkLocation(String name) {
        return this.getPlayer(name).getSavedLocation();
    }

}
