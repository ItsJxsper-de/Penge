package dev.itsjxsper.pengeapi.item;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

public class PangeItem {
    private static NamespacedKey key;

    public PangeItem() {
        key = new NamespacedKey("pange", "pange");
    }

    public static ItemStack getPange(int value) {
        ItemStack item = null;
        if (value < 1000) {
            item = new ItemStack(Material.EMERALD);
        } else if (value > 1000) {
            item = new ItemStack(Material.EMERALD_BLOCK);
        }

        assert item != null;
        ItemMeta meta = item.getItemMeta();
        meta.getPersistentDataContainer().set(key, PersistentDataType.INTEGER, value);
        item.setItemMeta(meta);
        return item;
    }

    public static NamespacedKey getKey() {
        return key;
    }
}
