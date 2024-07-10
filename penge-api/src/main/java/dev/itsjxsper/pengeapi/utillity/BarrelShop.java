package dev.itsjxsper.pengeapi.utillity;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Barrel;
import org.bukkit.block.Sign;

import java.util.UUID;

public class BarrelShop {

    private final Sign sign;
    private final Barrel barrel;
    private final UUID owner;
    private int id;
    private int amount;
    private int sell;
    private int buy;
    private final Material type;

    public BarrelShop(Sign sign, Barrel barrel, UUID owner, int id, int amount, Material type) {
        this.sign = sign;
        this.barrel = barrel;
        this.owner = owner;
        this.amount = amount;
        this.type = type;
    }

    public void update() {

    }

    public Location getSignLocation() {
        return this.sign.getLocation();
    }

    public Location getBerrelLocation() {
        return this.barrel.getLocation();
    }

    public UUID getOwner() {
        return this.owner;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setSell(int amount) {
        this.sell = amount;
    }

    public void setBuy(int amount) {
        this.buy = amount;
    }

    public int getId() {
        return id;
    }
    public int setId(int id) {
      return this.id = id;
    }
}
