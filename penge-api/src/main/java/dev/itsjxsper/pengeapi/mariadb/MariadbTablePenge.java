package dev.itsjxsper.pengeapi.mariadb;

import dev.itsjxsper.pengeapi.PengeAPI;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class MariadbTablePenge {
    private MariadbConnction mariadbConnction;

    public static boolean playerExists(UUID uuid) throws ClassNotFoundException, SQLException {
        ResultSet resultSet = PengeAPI.getInstance().getConnction().query("SELECT * FROM penge WHERE uuid = '" + uuid + "'");
        return resultSet.next() && resultSet.getCharacterStream("uuid")!= null;

    }
}
