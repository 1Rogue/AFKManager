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
package com.rogue.afkmanager.command;

import com.rogue.afkmanager.command.commands.*;
import java.util.HashMap;
import java.util.Map;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 *
 * @since 0.1
 * @author 1Rogue
 * @version 0.1
 */
public class CommandHandler implements CommandExecutor {

    protected final Map<String, LMCommand> commands = new HashMap<String, LMCommand>();

    public CommandHandler() {
        Afk afk = new Afk();
        commands.put(afk.getName().toLowerCase().trim(), afk);
    }

    /**
     * Main executor of commands. Grabs the appropriate command and executes it.
     *
     * @since 0.1
     * @version 0.1
     *
     * @param cs The command executor
     * @param cmd The command instance
     * @param string The label of the command
     * @param args The command arguments
     *
     * @return Command success
     */
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        LMCommand exec = commands.get(cmd.getName().toLowerCase().trim());
        if (exec != null) {
            exec.execute(sender, args);
        }
        return false;
    }
}
