/*
 * Copyright (c) 23.9.2020.
 *
 * CP copyright by Lucas
 */

package de.lucas.cp.commands;

import de.lucas.cp.api.API;
import de.lucas.cp.api.PermissionsAPI;
import de.lucas.cp.main.Datasave;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class cpcommands implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player player = (Player)sender;
        if(sender instanceof Player) {
            if(PermissionsAPI.getPLevel(player.getUniqueId().toString()) >= Datasave.teamplevel) {
                if(args.length == 0) {
                    player.sendMessage(Datasave.prefix + "§6-- Commands Team --");
                    player.sendMessage(Datasave.prefix + "§6/cp <login/logout>");
                    player.sendMessage(Datasave.prefix + "§6/cp <reports>");
                    player.sendMessage(Datasave.prefix + "§6/cp <supports>");
                    player.sendMessage(Datasave.prefix + "§6-- Commands Team --");

                } else if(args[0].equalsIgnoreCase("login")) {
                    if(!Datasave.status.contains(player)) {
                        try {
                            API.setStatus(player.getUniqueId().toString(), "ONLINE");
                            Datasave.status.add(player);
                            player.sendMessage(Datasave.prefix + "§6Loginvorgang läuft ...");
                            player.sendMessage(Datasave.prefix + "§aLogin erfolgreich :D");
                        } catch (Exception e) {
                            player.sendMessage(Datasave.prefix + "§6Loginvorgang läuft ...");
                            player.sendMessage(Datasave.prefix + "§cLogin fehlgeschlagen :(");
                        }
                    }
                } else if(args[0].equalsIgnoreCase("logout")) {
                    if(Datasave.status.contains(player)) {
                        try {
                            API.setStatus(player.getUniqueId().toString(), "OFFLINE");
                            Datasave.status.remove(player);
                            player.sendMessage(Datasave.prefix + "§6Logoutvorgang läuft ...");
                            player.sendMessage(Datasave.prefix + "§aLogoutvorgang erfolgreich :D");
                        } catch (Exception e) {
                            player.sendMessage(Datasave.prefix + "§6Logoutvorgang läuft ...");
                            player.sendMessage(Datasave.prefix + "§cLogoutvorgang fehlgeschlagen :(");
                        }
                    } else {
                        player.sendMessage(Datasave.prefix + "§cDu must eingeloggt sein um dies zu tun!");
                    }
                } else if(args[0].equalsIgnoreCase("reports")) {
                    if(Datasave.status.contains(player)) {
                        try {
                            player.sendMessage(Datasave.prefix + "§6Ladevorgang läuft ...");
                            player.sendMessage(Datasave.prefix + "§aLadevorgang erfolgreich :D");
                            player.sendMessage(Datasave.prefix + "");
                            player.sendMessage(Datasave.prefix + "");
                            player.sendMessage(Datasave.prefix + "§bDeine Reports: " + API.getReports(player.getUniqueId().toString()));
                        } catch (Exception e) {
                            player.sendMessage(Datasave.prefix + "§6Ladevorgang läuft ...");
                            player.sendMessage(Datasave.prefix + "§cLadevorgang fehlgeschlagen :(");
                        }
                    } else {
                        player.sendMessage(Datasave.prefix + "§cDu must eingeloggt sein um dies zu tun!");
                    }
                } else if(args[0].equalsIgnoreCase("supports")) {
                    if(Datasave.status.contains(player)) {
                        try {
                            player.sendMessage(Datasave.prefix + "§6Ladevorgang läuft ...");
                            player.sendMessage(Datasave.prefix + "§aLadevorgang erfolgreich :D");
                            player.sendMessage(Datasave.prefix + "");
                            player.sendMessage(Datasave.prefix + "");
                            player.sendMessage(Datasave.prefix + "§bDeine Supports: " + API.getSupports(player.getUniqueId().toString()));
                        } catch (Exception e) {
                            player.sendMessage(Datasave.prefix + "§6Ladevorgang läuft ...");
                            player.sendMessage(Datasave.prefix + "§cLadevorgang fehlgeschlagen :(");
                        }
                    } else {
                        player.sendMessage(Datasave.prefix + "§cDu must eingeloggt sein um dies zu tun!");
                    }
                }
            }
        }


        return false;
    }
}
