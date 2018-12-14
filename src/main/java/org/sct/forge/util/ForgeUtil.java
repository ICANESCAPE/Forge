package org.sct.forge.util;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.sct.core.file.FileTool;

import java.util.List;

/**
 * @author SCT_Alchemy
 * @date 2018/12/13 2:28 PM
 * 工具类
 */

public class ForgeUtil {

    /**
     * 转换字符串颜色
     * @param message 被转换的字符串
     * @return 转换后的字符串
     */
    public static String getString(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    /**
     * 将物品名字转换成对应物品
     * @param item 物品在item.yml内储存的名字
     * @return 转换后的物品道具
     */
    public static ItemStack toItem(String item) {
        return FileTool.getItem(item);
    }

    /**
     * 移除字符串的颜色
     * @param msg 字符串
     * @return 移除颜色后的
     */
    public static String removeColor(String msg) { return ChatColor.stripColor(msg); }

    /**
     * 发送奖励列表给玩家
     * @param reward 奖励列表
     * @param player 受到奖励的玩家
     */
    public static void sentReward(List<String> reward, Player player) {
        for(int i = 0; i < reward.size(); i++) {
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), reward.get(i).replace("%player%", player.getName()));
        }
    }

}
