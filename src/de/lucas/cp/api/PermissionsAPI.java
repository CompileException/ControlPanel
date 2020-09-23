/*
 * Copyright (c) 23.9.2020.
 *
 * CP copyright by Lucas
 */

package de.lucas.cp.api;

import de.lucas.cp.main.ControlPanel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PermissionsAPI {

    /*
    [TABLE INFOS]
    - cplogin (NAME, UUID, RANG, STATUS, PASSWORD)
    - cpsupstats (NAME, UUID, RANG, SUPPORTS, REPORTS)
    - cpusers (NAME, UUID, RANG)
    - cppermissions (NAME, UUID, RANG, PLEVEL, CREATOR)
     */

    /*
    [PERMISSIONS INFO]
    ADMIN: 4
    DEV: 3
    TEAM: 2
    USER: 1
     */

    /*
    RANG
     */
    public static void setRang(String uuid, String rang) {
        if (API.playerExists(uuid)) {
            ControlPanel.mysql.update("UPDATE `cpusers` SET `RANG`= '"+rang+"' WHERE UUID= '"+uuid+"'");
            ControlPanel.mysql.update("UPDATE `cplogin` SET `RANG`= '"+rang+"' WHERE UUID= '"+uuid+"'");
            ControlPanel.mysql.update("UPDATE `cpsupstats` SET `RANG`= '"+rang+"' WHERE UUID= '"+uuid+"'");
            ControlPanel.mysql.update("UPDATE `cppermissions` SET `RANG`= '"+rang+"' WHERE UUID= '"+uuid+"'");
        }
    }
    public static String getRang(String uuid) {
        String rang = "";
        if (API.playerExists(uuid)) {
            try {
                ResultSet rs = ControlPanel.mysql.query("SELECT * FROM cpusers WHERE UUID= '" + uuid + "'");
                if (rs.next() && rs.getString("RANG") == null) {
                }

                rang = rs.getString("RANG");
            } catch (SQLException var3) {
                var3.printStackTrace();
            }
        }
        return rang;
    }

    /*
    [PLEVEL]
     */
    public static void setPLevel(String uuid, Integer plevel) {
        if (API.playerExists(uuid)) {
            ControlPanel.mysql.update("UPDATE cppermissions SET PLevel= '" + plevel + "' WHERE UUID= '" + uuid + "';");
        } else {
            setPLevel(uuid, plevel);
        }
    }

    public static Integer getPLevel(String uuid) {
        int i = 0;
        if (API.playerExists(uuid)) {
            try {
                ResultSet rs = ControlPanel.mysql.query("SELECT * FROM cppermissions WHERE UUID= '" + uuid + "'");
                if (rs.next() && rs.getInt("PLevel") == 0) {
                }

                i = rs.getInt("PLevel");
            } catch (SQLException var3) {
                var3.printStackTrace();
            }
        }
        return i;
    }
}
