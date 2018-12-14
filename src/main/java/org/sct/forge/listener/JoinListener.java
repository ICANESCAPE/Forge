package org.sct.forge.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.sct.forge.api.ForgeApi;
import org.sct.forge.util.ForgeUtil;

/**
 * @author SCT_Alchemy
 * @date 2018/12/14 10:48 AM
 */

public class JoinListener implements Listener {

    @EventHandler
    void onJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();

        /* 为第一次进入服务器的玩家建立锻造数据 */
        if (!ForgeApi.hasData(player.getName())) {
            ForgeApi.initialData(player.getName());
            player.sendMessage(ForgeUtil.getString("&a初次加入服务器，正在为你建立数据..."));
        }

    }
}
