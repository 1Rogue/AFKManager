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
package com.rogue.afkmanager.listener;

import com.rogue.afkmanager.AFKManager;
import com.rogue.afkmanager.player.LMPlayer;
import com.rogue.afkmanager.utils.Utils;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 *
 * @since 0.1
 * @author 1Rogue
 * @version 0.1
 */
public class AFKListener implements Listener {

    protected final AFKManager plugin;

    public AFKListener(AFKManager main) {
        this.plugin = main;
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerActivity(PlayerMoveEvent e) {
        LMPlayer player = AFKManager.getPlugin().getPlayerHandler().getPlayer(e.getPlayer().getName());
        if (player.isAFK()) {
            if (Utils.compare(e.getPlayer().getLocation(), null/*TODO: Needs to be the set area that the player/config specified*/)) {
                e.getPlayer().sendMessage(ChatColor.YELLOW + "You are currently AFK. Move around to leave this area.");
            } else {
                e.getPlayer().teleport(player.getSavedLocation());
                e.getPlayer().sendMessage(ChatColor.GREEN + "Teleporting back.");
                AFKManager.getPlugin().getPlayerHandler().putPlayer(e.getPlayer().getName(), 0, player.getSavedLocation());
                AFKManager.getPlugin().getPlayerHandler().getPlayer(e.getPlayer().getName()).setAFK(false);
            }
        }
    }
    
    @EventHandler(priority = EventPriority.NORMAL)
    public void onPlayerChat(AsyncPlayerChatEvent e) {
        LMPlayer player = AFKManager.getPlugin().getPlayerHandler().getPlayer(e.getPlayer().getName());
        if (player.isAFK()) {
            e.getPlayer().teleport(player.getSavedLocation());
            e.getPlayer().sendMessage(ChatColor.GREEN + "Teleporting back.");
            AFKManager.getPlugin().getPlayerHandler().putPlayer(e.getPlayer().getName(), 0, player.getSavedLocation());
            AFKManager.getPlugin().getPlayerHandler().getPlayer(e.getPlayer().getName()).setAFK(false);
        }
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onPlayerLogout(PlayerQuitEvent e) {
        LMPlayer player = AFKManager.getPlugin().getPlayerHandler().getPlayer(e.getPlayer().getName());
        if (player.isAFK()) {
            Location back = player.getSavedLocation();
            e.getPlayer().teleport(back);
        }
        AFKManager.getPlugin().getPlayerHandler().remPlayer(e.getPlayer().getName());
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onPlayerKick(PlayerKickEvent e) {
        LMPlayer player = AFKManager.getPlugin().getPlayerHandler().getPlayer(e.getPlayer().getName());
        if (player.isAFK()) {
            Location back = player.getSavedLocation();
            e.getPlayer().teleport(back);
        }
        AFKManager.getPlugin().getPlayerHandler().remPlayer(e.getPlayer().getName());
    }
    
    @EventHandler(priority = EventPriority.NORMAL)
    public void onPlayerJoin(PlayerJoinEvent e) {
        AFKManager.getPlugin().getPlayerHandler().putPlayer(e.getPlayer().getName(), 0, e.getPlayer().getLocation());
    }
}
