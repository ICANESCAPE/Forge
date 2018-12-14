package org.sct.forge.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.sct.forge.Forge;
import org.sct.forge.api.ForgeApi;
import org.sct.forge.file.Config;
import org.sct.forge.file.Message;
import org.sct.forge.gui.GuiBuilder;
import org.sct.forge.util.ForgeUtil;

/**
 * @author SCT_Alchemy
 * @date 2018/12/14 11:31 AM
 */

public class ForgeCommands implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            GuiBuilder.openSelectInv(player);
        } else {
            Forge.info("&c这个指令只有玩家可以使用");
        }

        if (args[0].equalsIgnoreCase("add")) {
            ForgeApi.addExp(args[1], Integer.parseInt(args[2]));
            Forge.info("&a给与成功");
            Bukkit.getPlayer(args[1]).sendMessage(ForgeUtil.getString("&a你收到了 &6&l" + args[2] + " &a点锻造经验"));
        } else if (args[0].equalsIgnoreCase("reload")) {
            Config.reload();
            Message.reload();
            Forge.info("&a重载配置完成");
        }
        return false;
    }

}