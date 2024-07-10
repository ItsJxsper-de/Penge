package dev.itsjxsper.penge;

import dev.itsjxsper.penge.listeners.PlayerPickupListener;
import dev.itsjxsper.pengeapi.mariadb.MariadbConnction;
import org.bukkit.plugin.java.JavaPlugin;

public final class Penge extends JavaPlugin {
    private static Penge instance;
    private MariadbConnction mariadbConnction;

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
        mariadbConnction = new MariadbConnction("192.168.2.30", 3306, "Penge", "Penge", "Penge");
        Registry registry = new Registry(instance);
        registry.registerListener(new PlayerPickupListener());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public MariadbConnction getMariadbConnction() {
        return mariadbConnction;
    }
}
