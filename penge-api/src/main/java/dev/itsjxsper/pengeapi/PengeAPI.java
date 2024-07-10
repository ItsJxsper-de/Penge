package dev.itsjxsper.pengeapi;

import dev.itsjxsper.pengeapi.mariadb.MariadbConnction;
import org.bukkit.plugin.java.JavaPlugin;

public final class PengeAPI extends JavaPlugin {
    private static PengeAPI instance;
    MariadbConnction connction;

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public MariadbConnction getConnction() {
        return connction;
    }

    public static PengeAPI getInstance() {
        return instance;
    }
}
