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
package com.rogue.afkmanager.runnable;

import com.rogue.afkmanager.AFKManager;
import com.rogue.afkmanager.configuration.Configuration;
import com.rogue.afkmanager.player.LMPlayer;
import com.rogue.afkmanager.utils.Utils;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

/**
 *
 * @since 0.1
 * @author 1Rogue
 * @version 0.1
 */
public class AFKRunnable implements Runnable {
    
    private HashMap<String, LMPlayer> people;
    
    public AFKRunnable() {
        people = AFKManager.getPlugin().getPlayerHandler().getPlayers();
    }
    
    /**
     * The executable for checking all players for being AFK.
     * 
     * @since 0.1
     * @version 0.1
     */
    public void run() {
        // This is all a giant mess of code
        // Forgive me for my sins
        Collection<LMPlayer> players = people.values();
        Configuration config = AFKManager.getPlugin().getConfiguration();
        for (LMPlayer play : players) {
           Player p = Utils.threaded().getPlayer(play.getName());
            if (!play.isAFK()) { //If not AFK
                if (!Utils.strictCompare(play.getSavedLocation(), p.getLocation())) { // If not the same location when last checked
                    play.setSavedLocation(p.getLocation()); // Set the saved location as the current
                }
                else {
                    //This is the especially bad part. Have fun.
                    if (play.getTime() >= config.getInt("afk.timeout")) {
                        play.setSavedLocation(p.getLocation());
                        if (config.getBoolean("afk.random.enabled")) {
                            Random gen = new Random();
                            int rad = config.getInt("afk.random.radius");
                            p.teleport(new Location(Utils.threaded().getWorld(config.getString("afk.location.world")),
                                    gen.nextInt(rad - (rad*2)) + config.getDouble("afk.location.x"),
                                    config.getDouble("afk.location.x"),
                                    gen.nextInt(rad - (rad*2)) + config.getDouble("afk.location.z")));
                        }
                        else {
                            p.teleport(new Location(
                                    Utils.threaded().getWorld(config.getString("afk.location.world")),
                                    config.getDouble("afk.location.x"),
                                    config.getDouble("afk.location.y"),
                                    config.getDouble("afk.location.z")));
                        }
                    }
                    else {
                        play.setTime(play.getTime() + config.getInt("afk.check-interval"));
                    }
                }
                AFKManager.getPlugin().getPlayerHandler().putPlayer(play.getName(), play);
            }
            else {
                Utils.threaded().getPlayer(play.getName()).sendMessage("You are currently AFK");
            }
        }
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
