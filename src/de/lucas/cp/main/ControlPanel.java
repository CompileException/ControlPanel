package de.lucas.cp.main;

import de.lucas.cp.mysql.MySQL;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class ControlPanel extends JavaPlugin {
    private static ControlPanel instance;
    public static MySQL mysql;
    public static File path;

    @Override
    public void onEnable() {
        path = this.getDataFolder();
        instance = this;
        if (!this.getConfig().contains("MySQL")) {
            this.getConfig().set("MySQL.ip", "localhost");
            this.getConfig().set("MySQL.database", "cp");
            this.getConfig().set("MySQL.name", "root");
            this.getConfig().set("MySQL.password", "");
            this.saveConfig();
        }

        //register catch
        try{
            register();
            fetchingData();

            //Datasave.sendstartMessagesuccesfully();
        } catch (Exception e) {
            //Datasave.sendstartMessageshutdown();
            Bukkit.getPluginManager().disablePlugin(this);
        }
    }

    @Override
    public void onDisable() {
        mysql.close();
    }

    /*
    FETCHING THE DATA
     */
    public void fetchingData() { connectMySQl(); }

    /*
    REGISTER COMMANDS/LISTENER
     */
    public void register() {
        PluginManager pm = Bukkit.getPluginManager();
    }

    /*
    [MYSQL]
     */
    public void connectMySQl() {
        String ip = this.getConfig().getString("MySQL.ip");
        String database = this.getConfig().getString("MySQL.database");
        String name = this.getConfig().getString("MySQL.name");
        String passwort = this.getConfig().getString("MySQL.password");

        try {
            mysql = new MySQL(ip, database, name, passwort);
            mysql.update("CREATE TABLE IF NOT EXISTS cpusers(NAME varchar(64), UUID varchar(64), STATUS varchar(64), PASSWORD varchar(64));");

            Bukkit.getConsoleSender().sendMessage("§6[CP-MYSQL] starting connecting...");
            Bukkit.getConsoleSender().sendMessage("§a[CP-MYSQL] connected!");
        } catch (Exception var6) {
            Bukkit.getConsoleSender().sendMessage("§6[CP-MYSQL] starting connecting...");
            Bukkit.getConsoleSender().sendMessage("§4[CP-MYSQL] Connection refused Plugin shutdown!");
            Bukkit.getPluginManager().disablePlugin(this);
        }
    }

    public static ControlPanel getInstance() {
        return instance;
    }
}
