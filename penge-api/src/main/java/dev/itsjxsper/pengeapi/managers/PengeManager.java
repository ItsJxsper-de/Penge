package dev.itsjxsper.pengeapi.managers;

import dev.itsjxsper.pengeapi.mariadb.MariadbConnction;
import dev.itsjxsper.pengeapi.mariadb.MariadbDataType;
import dev.itsjxsper.pengeapi.mariadb.MariadbTabel;

import java.util.HashMap;
import java.util.UUID;

public class PengeManager {
    public static MariadbTabel tabel;

    public PengeManager(MariadbConnction connection) {
        HashMap<String, MariadbDataType> columns = new HashMap<>();
        columns.put("uuid", MariadbDataType.CHAR);
        columns.put("value", MariadbDataType.INT);

        tabel = new MariadbTabel((MariadbConnction) connection.getConnection(), "penge", columns);
    }

    public static void set(UUID uuid, int value) {
        MariadbTabel.Condition condition = new MariadbTabel.Condition("uuid", uuid.toString());
        if(tabel.exits(condition)) {
            tabel.set("value", value, condition);
        } else {
            tabel.set("uuid", uuid.toString(), condition);
            tabel.set("value", value, condition);
        }
    }

    public static int get(UUID uuid) {
        MariadbTabel.Condition condition = new MariadbTabel.Condition("uuid", uuid.toString());
        if(tabel.exits(condition)) {
            return tabel.getInt("value", condition);
        }
        set(uuid, 0);
        return 0;
    }
}
