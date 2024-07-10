package dev.itsjxsper.penge.listeners;

import dev.itsjxsper.pengeapi.item.PangeItem;
import dev.itsjxsper.pengeapi.managers.PengeManager;
import org.bukkit.Sound;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerAttemptPickupItemEvent;
import org.bukkit.persistence.PersistentDataType;

public class PlayerPickupListener implements Listener {
    @EventHandler
    public void onPlayerPickup(PlayerAttemptPickupItemEvent event) {
        if (event.getItem().getItemStack().getItemMeta().getPersistentDataContainer().has(PangeItem.getKey(), PersistentDataType.INTEGER)) {
            int value = event.getItem().getItemStack().getItemMeta().getPersistentDataContainer().get(PangeItem.getKey(), PersistentDataType.INTEGER);
            value *= event.getItem().getItemStack().getAmount();
            PengeManager.set(event.getPlayer().getUniqueId(), PengeManager.get(event.getPlayer().getUniqueId()) + value);
            event.setCancelled(true);
            event.getItem().remove();
            event.getPlayer().playSound(event.getItem().getLocation(), Sound.ENTITY_ITEM_PICKUP, 0.2f, 1f);
        }
    }
}
