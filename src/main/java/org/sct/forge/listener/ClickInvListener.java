package org.sct.forge.listener;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import org.sct.forge.api.ForgeApi;
import org.sct.forge.file.Config;
import org.sct.forge.file.Message;
import org.sct.forge.gui.GuiBuilder;
import org.sct.forge.util.ForgeUtil;

import java.util.*;

/**
 * @author SCT_Alchemy
 * @date 2018/12/14 10:51 AM
 */

public class ClickInvListener implements Listener {

    /* 玩家数据储存 */
    private static Map<Player, Integer> playerMap = new HashMap<>();

    @EventHandler
    void onClick(InventoryClickEvent e) {
        if (!(e.getWhoClicked() instanceof Player)) {
            return;
        }
        Player player = (Player) e.getWhoClicked();
        int slot = e.getSlot();

        if (e.getRawSlot() < 54) {
            if ((e.isShiftClick()) || (e.getHotbarButton() != -1)) {
                e.setCancelled(true);
                player.sendMessage(ForgeUtil.getString("&c用鼠标操作不要用键盘操作哟"));
                return;
            }
        }

        if (e.getInventory().getName().equals("§3§l锻造台")) {
            if (Config.getForgeData(slot) != null) {
                e.setCancelled(true);
                if (ForgeApi.getExp(player.getName()) < Config.getForgeData(slot).getExp()) {
                    player.sendMessage(ForgeUtil.getString(Message.getForgeExpNotEnough())
                            .replace("%require%", "" + Config.getForgeData(slot).getExp())
                            .replace("%old%", "" + ForgeApi.getExp(player.getName()))
                    );
                    player.closeInventory();
                } else {
                    playerMap.put(player, e.getSlot());
                    player.closeInventory();
                    GuiBuilder.openForgeInv(player);
                }
            }
        } else if (e.getInventory().getName().contains("锻造台 - 放入锻造材料")) {
            if (slot == 35) {
                List<String> required = Config.getForgeData(playerMap.get(player)).getRequire();
                List<String> putList = new ArrayList<>();
                for (int i = 0; i < e.getInventory().getSize() - 1; i++) {
                    if (e.getInventory().getItem(i) == null || e.getInventory().getItem(i).getType().equals(Material.AIR)) {
                        return;
                    } else {
                        /* 如果读取到的不是空的物品，那么存入list中进行对比 */
                        putList.add(e.getInventory().getItem(i).getItemMeta().getDisplayName());
                    }
                }

                /* 使用Collections接口序列化物品以及config内所需求的物品进行对比 */
                Collections.sort(required);
                Collections.sort(putList);

                /* 长度不一样说明没有放入正确物品 */
                if (required.size() != putList.size()) {
                    player.sendMessage(ForgeUtil.getString(Message.getNotCorrectMaterial()));
                } else {
                    boolean flag = true;
                    for (int index = 0; index < required.size(); index++) {
                        if (ForgeUtil.removeColor(required.get(index)).equals(ForgeUtil.removeColor(putList.get(index)))) {
                            flag = false;
                            break;
                        }
                    }
                    if (flag) {
                        /* 成功，那么清除掉消耗道具，给与奖励 */
                        e.getInventory().clear();
                        player.closeInventory();
                        ForgeUtil.sentReward(Config.getForgeData(playerMap.get(player)).getReward(), player);
                        player.sendMessage(ForgeUtil.getString(Message.getForgeSuccess()));
                        if (ForgeApi.getExp(player.getName()) >= Config.getMaxExp()) {
                            player.sendMessage(ForgeUtil.getString(Message.getForgeExpMax()));
                        } else {
                            ForgeApi.addExp(player.getName(), Config.getForgeData(playerMap.get(player)).getAdd());
                            player.sendMessage(ForgeUtil.getString(Message.getForgeExpAdd())
                                    .replace("%amount%",
                                            String.valueOf(Config.getForgeData(playerMap.get(player)).getAdd())));
                        }
                    } else {
                        player.sendMessage(ForgeUtil.getString(Message.getNotCorrectMaterial()));
                    }
                }
            }
        }
    }
}
