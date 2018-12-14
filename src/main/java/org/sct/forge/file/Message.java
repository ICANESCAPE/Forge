package org.sct.forge.file;

import org.inventivetalent.itembuilder.util.FileUtil;
import org.sct.forge.Forge;

/**
 * @author SCT_Alchemy
 * @date 2018/12/14 11:02 AM
 */

public class Message extends FileUtil {

    private static Message message;

    public Message() { super(Forge.getInstance(), "message.yml"); }

    public static void reload() { message = new Message(); }

    @Override
    public void check() { }

    public static String getForgeSuccess() { return message.getString("Message.ForgeSuccess"); }

    public static String getForgeExpNotEnough() { return message.getString("Message.ForgeExpNotEnough"); }

    public static String getForgeExpMax() { return message.getString("Message.ForgeExpMax"); }

    public static String getForgeExpAdd() { return message.getString("Message.ForgeExpAdd"); }

    public static String getAddPoint() { return message.getString("Message.AddPoint"); }

    public static String getMonitForgeExp() { return message.getString("Message.MonitForgeExp"); }

    public static String getNotCorrectMaterial() { return message.getString("Message.NotCorrectMaterial"); }

}
