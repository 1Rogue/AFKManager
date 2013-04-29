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
import java.util.Map;
import java.util.Random;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

/**
 *
 * @since 0.1
 * @author 1Rogue
 * @version 0.1
 */
public class AFKRunnable implements Runnable {

    public AFKRunnable() {
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
        //From Ralex: DEAR GOD HELP ME I AM TRAPPED IN THIS CODE
        Map<String, LMPlayer> people = AFKManager.getPlugin().getPlayerHandler().getPlayers();
        Configuration config = AFKManager.getPlugin().getConfiguration();
        for (Player p : Bukkit.getOnlinePlayers()) {
            LMPlayer play = people.get(p.getName().toLowerCase().trim());
            if (!play.isAFK()) { //If not AFK
                if (!Utils.strictCompare(play.getSavedLocation(), p.getLocation())) { // If not the same location when last checked
                    play.setSavedLocation(p.getLocation()); // Set the saved location as the current
                } else {
                    //This is the especially bad part. Have fun.
                    if (play.getTime() >= config.getInt("afk.timeout")) {
                        play.setSavedLocation(p.getLocation());
                        if (config.getBoolean("afk.random.enabled")) {
                            Random gen = new Random();
                            int rad = config.getInt("afk.random.radius");
                            p.teleport(new Location(Bukkit.getWorld(config.getString("afk.location.world-name")),
                                    gen.nextInt(rad - (rad * 2)) + config.getDouble("afk.location.x"),
                                    config.getDouble("afk.location.x"),
                                    gen.nextInt(rad - (rad * 2)) + config.getDouble("afk.location.z")));
                        } else {
                            p.teleport(new Location(
                                    Bukkit.getWorld(config.getString("afk.location.world-name")),
                                    config.getDouble("afk.location.x"),
                                    config.getDouble("afk.location.y"),
                                    config.getDouble("afk.location.z")));
                        }
                    } else {
                        play.setTime(play.getTime() + config.getInt("afk.check-interval"));
                    }
                }
                AFKManager.getPlugin().getPlayerHandler().putPlayer(play.getName(), play);
            } else {
                p.sendMessage("You are currently AFK");
            }
        }
    }
}
