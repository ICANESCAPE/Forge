package org.sct.forge.listener;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;


/**
 * @author SCT_Alchemy
 * @date 2018/12/14 11:16 AM
 */

public class CloseInvListener implements Listener {

    @EventHandler
    void onClose(InventoryCloseEvent e) {
        Player player = (Player) e.getPlayer();
        if(e.getInventory().getName().equalsIgnoreCase("§3§l锻造台 - 放入锻造材料")) {
            ItemStack[] items = new ItemStack[e.getInventory().getSize()];
            for(int i = 0; i < e.getInventory().getSize(); i++) {
                if(i != 35 && e.getInventory().getItem(i) != null && !e.getInventory().getItem(i).getItemMeta().equals(Material.AIR)) {
                    ItemStack item = e.getInventory().getItem(i);
                    items[i] = item;
                }
            }
            player.getInventory().addItem(items);
        }
    }
}
