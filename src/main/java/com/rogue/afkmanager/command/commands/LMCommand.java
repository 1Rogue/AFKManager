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
package com.rogue.afkmanager.command.commands;

import org.bukkit.command.CommandSender;

/**
 *
 * @since 0.1
 * @author 1Rogue
 * @version 0.1
 */
public interface LMCommand {
    
    /**
     * Executes the command.
     * 
     * @since 0.1
     * @version 0.1
     * 
     * @param sender The command executor
     * @param args command arguments
     * @return Success of command
     */
    public abstract boolean execute(CommandSender sender, String[] args);
    
    /**
     * Gets the name of the implemented command.
     * 
     * @since 0.1
     * @version 0.1
     * 
     * @return The command name
     */
    public abstract String getName();
    
    /**
     * Returns an array of help information for the relevant command. Note
     * the usage of the command always comes first, followed by an
     * explanation of what the command does.
     * 
     * @since 0.1
     * @version 0.1
     * 
     * @return String array of help info
     */
    public abstract String[] getHelp();

}
