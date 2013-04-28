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
package com.rogue.afkmanager.permission;

import com.rogue.afkmanager.AFKManager;
import java.util.logging.Level;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;

/**
 *
 * @since 0.1
 * @author 1Rogue
 * @version 0.1
 */
public class PermManager {
    
    private boolean vault = false;
    protected Permission perms;
    
    public PermManager(boolean init) {
        vault = init;
        this.setupPermissions();
    }
    
    /**
     * Checks if the player has the proper permission node.
     * 
     * @since 0.1
     * @version 0.1
     * 
     * @param sender The command executor
     * @param permission The permission node
     * @return True if player has the permission node
     */
    public boolean has (CommandSender sender, String permission) {
        if (vault && perms.has(sender, permission)) {
            return true;
        }
        if (sender.hasPermission(permission)) {
            return true;
        }
        return false;
    }
    
    /**
     * Checks if the player has the proper permission node.
     * 
     * @since 0.1
     * @version 0.1
     * 
     * @param player A player object to check
     * @param permission The permission node
     * @return True if player has the permission node
     */
    public boolean has (Player player, String permission) {
        if (vault) {
            // check perm
            AFKManager.getPlugin().getLogger().log(Level.INFO, "Vault is enabled!");
        }
        if (player.hasPermission(permission)) {
            return true;
        }
        return false;
    }
    
    /**
     * Vault's method for loading permissions
     * 
     * @since 0.1
     * @version 0.1
     */
    private void setupPermissions() {
        RegisteredServiceProvider<Permission> rsp = Bukkit.getServer().getServicesManager().getRegistration(Permission.class);
        perms = rsp.getProvider();
    }

}
