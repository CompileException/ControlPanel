/*
 * Copyright (c) 23.9.2020.
 *
 * CP copyright by Lucas
 */

package de.lucas.cp.mysql;

import de.lucas.cp.main.ControlPanel;
import de.lucas.cp.main.Datasave;
import org.bukkit.Bukkit;

public class MySQLTables {

    public static void createifnotexist() {
        try {
            //USERS
            ControlPanel.mysql.update("CREATE TABLE IF NOT EXISTS cpusers(NAME varchar(64), UUID varchar(64), RANG varchar(64));");
            Bukkit.getConsoleSender().sendMessage(Datasave.prefix + "§a[Cp-MySQL] 'USERS' TABLE CREATED!");

            //PERMISSIONS
            ControlPanel.mysql.update("CREATE TABLE IF NOT EXISTS cppermissions(NAME varchar(64), UUID varchar(64), RANG varchar(64), PLevel int, Creator varchar(64));");
            Bukkit.getConsoleSender().sendMessage(Datasave.prefix + "§a[Cp-MySQL] 'PERMISSIONS' TABLE CREATED!");

            //SUPSTATS
            ControlPanel.mysql.update("CREATE TABLE IF NOT EXISTS cpsupstats(NAME varchar(64), UUID varchar(64), RANG varchar(64), Supports int, Reports int);");
            Bukkit.getConsoleSender().sendMessage(Datasave.prefix + "§a[Cp-MySQL] 'SUPSTATS' TABLE CREATED!");

            //LOGIN
            ControlPanel.mysql.update("CREATE TABLE IF NOT EXISTS cplogin(NAME varchar(64), UUID varchar(64), RANG varchar(64), Status varchar(64), Password varchar(64));");
            Bukkit.getConsoleSender().sendMessage(Datasave.prefix + "§a[Cp-MySQL] 'LOGIN' TABLE CREATED!");

        } catch (Exception e) {
            Bukkit.getConsoleSender().sendMessage(Datasave.prefix + "§c[Cp-MySQL] CANNOT CREATE TABLES");
            Bukkit.getConsoleSender().sendMessage(Datasave.prefix + "§c[Cp-MySQL] " + e.getMessage());

        }
    }


}
