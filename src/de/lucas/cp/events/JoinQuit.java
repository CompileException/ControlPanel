/*
 * Copyright (c) 23.9.2020.
 *
 * CP copyright by Lucas
 */

package de.lucas.cp.events;

import de.lucas.cp.api.API;
import de.lucas.cp.api.PermissionsAPI;
import de.lucas.cp.main.Datasave;
import de.lucas.cp.utils.Addons;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinQuit implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        API.createPlayer(player.getName(), player.getUniqueId().toString(), "SYSTEM", "online", "unused", "unused");
        PermissionsAPI.setRang(player.getUniqueId().toString(), "ADMIN");
        PermissionsAPI.setPLevel(player.getUniqueId().toString(), 4);

        player.sendMessage(Datasave.prefix + "§aDu hast den Rang: " + PermissionsAPI.getRang(player.getUniqueId().toString()));
        player.sendMessage(Datasave.prefix + "§aUnd das PLevel: " + PermissionsAPI.getPLevel(player.getUniqueId().toString()));

        Addons.runActiobar(player, "CP-Login Status: " + API.getStatus(player.getUniqueId().toString()), 10);
    }



}
