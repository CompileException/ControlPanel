package de.lucas.cp.utils;

import de.lucas.cp.main.ControlPanel;

import java.sql.*;

public class MySQL {

    private String HOST = "";
    private String DATABASE = "";
    private String USER = "";
    private String PASSWORD = "";
    private Connection con;

    public MySQL(String host, String database, String user, String password) {
        this.HOST = host;
        this.DATABASE = database;
        this.USER = user;
        this.PASSWORD = password;
        this.connect();
    }

    public void connect() {
        try {
            this.con = DriverManager.getConnection("jdbc:mysql://" + this.HOST + ":3306/" + this.DATABASE + "?autoReconnect=true", this.USER, this.PASSWORD);
            System.out.println("[MySQL] Die Verbindung zur MySQL wurde hergestellt!");
        } catch (SQLException var2) {
            System.out.println("[MySQL] Die Verbindung zur MySQL ist fehlgeschlagen! Fehler: " + var2.getMessage());
        }

    }

    public void close() {
        try {
            if (this.con != null) {
                this.con.close();
                System.out.println("[MySQL] Die Verbindung zur MySQL wurde Erfolgreich beendet!");
            }
        } catch (SQLException var2) {
            System.out.println("[MySQL] Fehler beim beenden der Verbindung zur MySQL! Fehler: " + var2.getMessage());
        }

    }

    public void update(String qry) {
        try {
            Statement st = this.con.createStatement();
            st.executeUpdate(qry);
            st.close();
        } catch (SQLException var3) {
            this.connect();
            System.err.println(var3);
        }

    }

    public ResultSet query(String qry) {
        ResultSet rs = null;

        try {
            Statement st = this.con.createStatement();
            rs = st.executeQuery(qry);
        } catch (SQLException var4) {
            this.connect();
            System.err.println(var4);
        }

        return rs;
    }

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
}
