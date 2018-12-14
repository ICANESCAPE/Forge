package org.sct.forge.gui;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.sct.core.util.ItemStackUtil;
import org.sct.forge.api.ForgeApi;
import org.sct.forge.dto.ForgeData;
import org.sct.forge.file.Config;
import org.sct.forge.util.ForgeUtil;

/**
 * @author SCT_Alchemy
 * @date 2018/12/14 10:55 AM
 */

public class GuiBuilder {

    private static Inventory inventory;
    private static Inventory forge;

    public static void openSelectInv(Player player) {
        inventory = Bukkit.createInventory(null, 54, "§3§l锻造台");
        addIcon(player);
        player.closeInventory();
        player.openInventory(inventory);
    }

    public static void openForgeInv(Player player) {
        forge = Bukkit.createInventory(null, 36, "§3§l锻造台 - 放入锻造材料");
        add();
        player.closeInventory();
        player.openInventory(forge);
    }

    private static void addIcon(Player player) {
        for(ForgeData data : Config.getData()) {
            ItemStack item = ForgeUtil.toItem(data.getGuiName());
            if(ForgeApi.getExp(player.getName()) < data.getExp()) {
                if(!item.getItemMeta().getLore().contains("§c§l经验不足，无法锻造")) {
                    inventory.setItem(data.getSlot(), ItemStackUtil.addLore(item, "§c§l经验不足，无法锻造"));
                } else {
                    inventory.setItem(data.getSlot(), item);
                }
            } else {
                if(!item.getItemMeta().getLore().contains("§a§l可以锻造")) {
                    inventory.setItem(data.getSlot(), ItemStackUtil.addLore(item, "§a§l可以锻造"));
                } else {
                    inventory.setItem(data.getSlot(), item);
                }
            }
        }
    }

    private static void add() {
        forge.setItem(35, ForgeUtil.toItem(Config.getConfirm()));
    }

}
