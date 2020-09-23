/*
 * Copyright (c) 23.9.2020.
 *
 * CP copyright by Lucas
 */

package de.lucas.cp.api;

import de.lucas.cp.main.ControlPanel;
import de.lucas.cp.main.Datasave;
import org.bukkit.Bukkit;

import java.sql.ResultSet;
import java.sql.SQLException;

public class API {

    /*
    [TABLE INFOS]
    - cplogin (NAME, UUID, RANG, STATUS, PASSWORD)
    - cpsupstats (NAME, UUID, RANG, SUPPORTS, REPORTS)
    - cpusers (NAME, UUID, RANG)
    - cppermissions (NAME, UUID, RANG, PLEVEL, CREATOR)
     */

    public static boolean playerExists(String uuid) {
        try {
            ResultSet rs = ControlPanel.mysql.query("SELECT * FROM cpusers WHERE UUID= '" + uuid + "'");
            if (rs.next()) {
                return rs.getString("UUID") != null;
            } else {
                return false;
            }
        } catch (SQLException var2) {
            var2.printStackTrace();
            return false;
        }
    }

    public static void createPlayer(String name, String uuid, String creator, String status, String password, String rang) {
        if (!playerExists(uuid)) {
            //CPLOGIN
            ControlPanel.mysql.update("INSERT INTO cplogin (NAME, UUID, RANG, STATUS, PASSWORD) VALUES ('" + name + "', '" + uuid + "', '"+rang+"', '"+status+"', '"+password+"');");
            //CPSUPSTATUS
            ControlPanel.mysql.update("INSERT INTO cpsupstats (NAME, UUID, RANG, SUPPORTS, REPORTS) VALUES ('" + name + "', '" + uuid + "', '"+rang+"', '0', '0');");
            //CPUSERS
            ControlPanel.mysql.update("INSERT INTO cpusers (NAME, UUID, RANG) VALUES ('" + name + "', '" + uuid + "', '"+rang+"');");
            //CPPERMISSIONS
            ControlPanel.mysql.update("INSERT INTO cppermissions (NAME, UUID, RANG, PLEVEL, CREATOR) VALUES ('" + name + "', '" + uuid + "', '"+rang+"', '0', '"+creator+"');");
            Bukkit.getConsoleSender().sendMessage(Datasave.prefix + "§a[USERS] Player created!");
        }
        Bukkit.getConsoleSender().sendMessage(Datasave.prefix + "§a[USERS] Player existing!");
    }


    /*
    STATUS
     */
    public static void setStatus(String uuid, String status) {
        if (playerExists(uuid)) {
            ControlPanel.mysql.update("UPDATE `cplogin` SET `STATUS`= '"+status+"' WHERE UUID= '"+uuid+"'");
        }
    }

    public static String getStatus(String uuid) {
        String status = "";
        if (playerExists(uuid)) {
            try {
                ResultSet rs = ControlPanel.mysql.query("SELECT * FROM cplogin WHERE UUID= '" + uuid + "'");
                if (rs.next() && rs.getString("STATUS") == null) {
                }

                status = rs.getString("STATUS");
            } catch (SQLException var3) {
                var3.printStackTrace();
            }
        }

        return status;
    }

    /*
    SUPPORT
     */
    public static void setsupports(String uuid, Integer supports) {
        if (playerExists(uuid)) {
            ControlPanel.mysql.update("UPDATE cpsupstats SET supports= '" + supports + "' WHERE UUID= '" + uuid + "';");
        } else {
            setsupports(uuid, supports);
        }
    }

    public static Integer getSupports(String uuid) {
        Integer i = 0;
        if (playerExists(uuid)) {
            try {
                ResultSet rs = ControlPanel.mysql.query("SELECT * FROM cpsupstats WHERE UUID= '" + uuid + "'");
                i = rs.getInt("supports");
            } catch (SQLException var3) {
                var3.printStackTrace();
            }
        } else {
            getSupports(uuid);
        }

        return i;
    }

    /*
    REPORTS
     */
    public static void setreports(String uuid, Integer reports) {
        if (playerExists(uuid)) {
            ControlPanel.mysql.update("UPDATE cpsupstats SET reports= '" + reports + "' WHERE UUID= '" + uuid + "';");
        } else {
            setsupports(uuid, reports);
        }
    }

    public static Integer getReports(String uuid) {
        Integer i = 0;
        if (playerExists(uuid)) {
            try {
                ResultSet rs = ControlPanel.mysql.query("SELECT * FROM cpsupstats WHERE UUID= '" + uuid + "'");
                i = rs.getInt("reports");
            } catch (SQLException var3) {
                var3.printStackTrace();
            }
        } else {
            getSupports(uuid);
        }

        return i;
    }


    /*

    public static void addsupport(String uuid) {
        Integer i = 1;
        if (playerExists(uuid)) {
            setsupports(uuid, getSupports(uuid) + i);
        } else {
            addsupport(uuid);
        }
    }
     */
}
