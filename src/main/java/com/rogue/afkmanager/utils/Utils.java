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

import org.bukkit.Location;

/**
 *
 * @since @author 1Rogue
 * @version
 */
public class Utils {
    
    public Utils() {
    }
    
    /**
     * Compares two locations
     * 
     * @since 0.1
     * @version 0.1
     * 
     * @param one First Location
     * @param two Second Location
     * @return True if locations are the same
     */
    public static boolean compare(Location one, Location two) {
        if ((Math.abs(one.getX() - two.getX()) < 2) && (Math.abs(one.getY() - two.getY()) < 0.5D) && (Math.abs(one.getZ() - two.getZ()) < 2)) {
            return true;
        }
        return false;
    }
    
    /**
     * Compares two locations strictly
     * 
     * @since 0.1
     * @version 0.1
     * 
     * @param one First Location
     * @param two Second Location
     * @return True if locations are the same
     */
    public static boolean strictCompare(Location one, Location two) {
        if ((Math.abs(one.getX() - two.getX()) < 1) && (Math.abs(one.getY() - two.getY()) < 0.5D) && (Math.abs(one.getZ() - two.getZ()) < 1)) {
            return true;
        }
        return false;
    }
}
