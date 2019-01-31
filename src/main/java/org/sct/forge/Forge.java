package org.sct.forge;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import org.sct.core.Core;
import org.sct.forge.command.ForgeCommands;
import org.sct.forge.dao.BasicDao;
import org.sct.forge.file.Config;
import org.sct.forge.file.Message;
import org.sct.forge.util.EventUtil;
import org.sct.forge.util.ForgeUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author SCT_Alchemy
 * @date 2018/12/13 2:28 PM
 */

public final class Forge extends JavaPlugin {

    private static Forge instance;
    private static Connection connection;

    @Override
    public void onEnable() {
        instance = this;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            String host = getConfig().getString("Mysql.Host");
            String port = getConfig().getString("Mysql.Port");
            String dbname = getConfig().getString("Mysql.DataBase");
            String username = getConfig().getString("Mysql.User");
            String password = getConfig().getString("Mysql.PassWord");
            String tablename = getConfig().getString("Mysql.Table");
            Bukkit.getConsoleSender().sendMessage("测试一下" + host + password);
            String ip = "jdbc:mysql://" + host + ":" + port + "/" + dbname + "?useUnicode=true&characterEncoding=utf8&autoReconnect=true";
            String sql = "CREATE TABLE  IF NOT EXISTS `drawing` (\n" +
                    "  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,\n" +
                    "  `name` text,\n" +
                    "  `exp` int(11) DEFAULT '0',\n" +
                    "  PRIMARY KEY (`id`)\n" +
                    ") ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1;";
            connection = DriverManager.getConnection(ip, username, password);
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            BasicDao.setConnection(connection);
            Bukkit.getServer().getConsoleSender().sendMessage("§f[&eForge锻造&f] &6> &a数据库连接成功");
        } catch (ClassNotFoundException e) { e.printStackTrace();
        } catch (SQLException e) { e.printStackTrace(); }
        Message.reload();
        Config.reload();
        EventUtil.register();
        Bukkit.getPluginCommand("forge").setExecutor(new ForgeCommands());
        info("&f[&eForge锻造&f] &6> &f加载完成");
        info("&aplugin by Server CT");
    }

    @Override
    public void onDisable() {

    }

    public static Forge getInstance() { return instance; }

    public static void info(String message) {
        Core.info(ForgeUtil.getString(message));
    }
}
