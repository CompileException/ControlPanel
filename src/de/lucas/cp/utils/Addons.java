/*
 * Copyright (c) 23.9.2020.
 *
 * CP copyright by Lucas
 */

package de.lucas.cp.utils;

import com.connorlinfoot.actionbarapi.ActionBarAPI;
import com.connorlinfoot.actionbarapi.ActionBarMessageEvent;
import org.bukkit.entity.Player;

import java.util.Timer;
import java.util.TimerTask;

public class Addons {

    public static void runActiobar(Player player, String message, int time) {
        ActionBarAPI.sendActionBar(player, message, time);
    }
}
