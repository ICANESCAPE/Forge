package org.sct.forge.file;

import lombok.Getter;
import org.bukkit.configuration.ConfigurationSection;
import org.inventivetalent.itembuilder.util.FileUtil;
import org.sct.forge.Forge;
import org.sct.forge.dto.ForgeData;


import java.util.ArrayList;
import java.util.List;

/**
 * @author SCT_Alchemy
 * @date 2018/12/14 10:43 AM
 */

public class Config extends FileUtil {

    private static Config config;

    @Getter
    private static List<ForgeData> data = new ArrayList<>();

    public Config() { super(Forge.getInstance(), "forge.yml"); }

    public static void reload() { config = new Config(); }

    @Override
    public void check() {
        ConfigurationSection cs = this.getConfigurationSection("Drawing");
        for(String key : cs.getKeys(false)) {
            data.add(new ForgeData(
                    cs.getString(key + ".icon"),
                    cs.getString(key + ".item"),
                    cs.getInt(key + ".exp"),
                    cs.getInt(key + ".add"),
                    cs.getInt(key + ".slot"),
                    cs.getStringList(key + ".require"),
                    cs.getStringList(key + ".reward"))
            );
        }
    }

    public static int getMaxExp() { return config.getInt("MaxExp"); }

    public static String getConfirm() { return config.getString("confirm"); }

    public static ForgeData getForgeData(int slot) {
        for(ForgeData key : data) {
            if(key.getSlot() == slot) {
                return key;
            }
        }
        return null;
    }
}
