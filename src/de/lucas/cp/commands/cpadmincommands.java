/*
 * Copyright (c) 23.9.2020.
 *
 * CP copyright by Lucas
 */

package de.lucas.cp.commands;

import de.lucas.cp.api.API;
import de.lucas.cp.api.PermissionsAPI;
import de.lucas.cp.main.ControlPanel;
import de.lucas.cp.main.Datasave;
import de.lucas.cp.mysql.MySQL;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scheduler.BukkitTask;

public class cpadmincommands implements CommandExecutor {

    public int time = 10;
    public BukkitTask task;

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player player = (Player) sender;
        if (sender instanceof Player) {
            if (PermissionsAPI.getPLevel(player.getUniqueId().toString()) >= Datasave.adminplevel) {
                if (args.length == 0) {
                    player.sendMessage(Datasave.prefix + "§6-- Commands Team --");
                    player.sendMessage(Datasave.prefix + " ");
                    player.sendMessage(Datasave.prefix + "§6/cp <login/logout>");
                    player.sendMessage(Datasave.prefix + "§6/cp <reports>");
                    player.sendMessage(Datasave.prefix + "§6/cp <supports>");
                    player.sendMessage(Datasave.prefix + " ");
                    player.sendMessage(Datasave.prefix + "§c-- Commands DEV --");
                    player.sendMessage(Datasave.prefix + " ");
                    player.sendMessage(Datasave.prefix + "§6/cp <devmode>");
                    player.sendMessage(Datasave.prefix + "§6/cp <resource>");
                    player.sendMessage(Datasave.prefix + "§6/cp <stop> <Plugin>");
                    player.sendMessage(Datasave.prefix + " ");
                    player.sendMessage(Datasave.prefix + "§c-- Commands ADMIN --");
                    player.sendMessage(Datasave.prefix + " ");
                    player.sendMessage(Datasave.prefix + "§6/cp <createlogin / deletelogin> <Name> <password> <rang>");
                    player.sendMessage(Datasave.prefix + "§6/cp <restart>");
                    player.sendMessage(Datasave.prefix + "§6/cp <message>");
                    player.sendMessage(Datasave.prefix + " ");

                } else if (args[0].equalsIgnoreCase("createlogin")) {
                    Player target = Bukkit.getPlayer(args[1]);
                    String password = args[2];
                    String rang = args[3];

                    if(Datasave.status.contains(player)) {
                        API.createPlayer(target.getName(), target.getUniqueId().toString(), player.getName(), "OFFLINE", password, rang);
                        player.sendMessage(Datasave.prefix + "§a-- Login created --");
                        player.sendMessage(Datasave.prefix + "§aName: " + target.getName());
                        player.sendMessage(Datasave.prefix + "§aUUID: " + target.getUniqueId().toString());
                        player.sendMessage(Datasave.prefix + "§aRANG: " + rang);
                        player.sendMessage(Datasave.prefix + "§aPASSWORD: " + password);
                        player.sendMessage(Datasave.prefix + "§a-- Login created --");
                        target.kickPlayer("§aCP Login erstellt!");

                    } else {
                        player.sendMessage(Datasave.prefix + "§cDu must eingeloggt sein um dies zu tun!");
                    }
                } else if (args[0].equalsIgnoreCase("restart")) {
                    if (Datasave.status.contains(player)) {
                        countdown();
                    }
                }
            }
        }
        return false;
    }

    int taskID;

    public void countdown(){
            BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
            taskID = scheduler.scheduleSyncRepeatingTask(ControlPanel.getInstance(), new Runnable() {
                @Override
                public void run() {
                    for(Player all : Bukkit.getOnlinePlayers()) {
                        if(PermissionsAPI.getPLevel(all.getUniqueId().toString()) >= 2) {
                            if(Datasave.status.contains(all)) {
                                all.sendTitle("§cControlPanel Restart", "§a in " + time);
                            }
                        }
                    }
                    for(Player all : Bukkit.getOnlinePlayers()) {
                        if (time == 0) {
                            Datasave.status.clear();
                            all.kickPlayer("ControlPanel restart!");
                            Bukkit.reload();
                            ControlPanel.mysql.close();
                            ControlPanel.mysql.connect();
                            stopTimer();
                        }
                    }
                    time --;
                }
            }, 0L, 20L);
        }

    public void stopTimer() {
        Bukkit.getScheduler().cancelTask(taskID);
    }
}
