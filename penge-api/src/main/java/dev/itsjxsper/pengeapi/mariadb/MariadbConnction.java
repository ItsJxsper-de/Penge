package dev.itsjxsper.pengeapi.mariadb;

import org.bukkit.Bukkit;

import java.sql.*;

public class MariadbConnction {
    private Connection connection;
    private final String[] information = new String[5];

    public MariadbConnction(String host, int port, String database, String user, String password) {
        this.information[0] = host;
        this.information[1] = Integer.toString(port);
        this.information[2] = database;
        this.information[3] = user;
        this.information[4] = password;
    }

    public void connect() throws ClassNotFoundException {
        if (!isConnected()) {
            Class.forName("org.mariadb.jdbc.Driver");
            try {
                this.connection = DriverManager.getConnection("jdbc:mariadb://" + this.information[0] + ":" + this.information[1] + "/" + this.information[2], this.information[3], this.information[4]);
                Bukkit.getServer().getConsoleSender().sendMessage("[MariaDB] Die Verbindung wurde hergestellt");
            } catch (SQLException e) {
                Bukkit.getServer().getConsoleSender().sendMessage("[MariaDB] Die Verbindung zur MariaDB ist fehlgeschlagen! Fehler: " + e.getMessage());
            }
        }
    }

    public boolean isConnected() {
        return this.connection != null;
    }

    public void close() {
        try {
            if (connection != null) {
                connection.close();
                System.out.println("[MariaDB] Die Verbindung zur MariaDB wurde Erfolgreich beendet!");
            }
        } catch (SQLException e) {
            System.out.println("[MariaDB] Fehler beim beenden der Verbindung zur MariaDB! Fehler: " + e.getMessage());
        }
    }

    public void update(String qry) throws ClassNotFoundException {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(qry);
            statement.close();
        } catch (SQLException e) {
            connect();
            System.err.println(e);
        }
    }

    public ResultSet query(String qry) throws ClassNotFoundException {
        ResultSet resultSet = null;
        try {
            resultSet = connection.createStatement().executeQuery(qry);
        } catch (SQLException e) {
            connect();
            System.err.println(e);
        }
        return resultSet;
    }


    public Connection getConnection() {
        return connection;
    }
}
