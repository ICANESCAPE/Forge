package org.sct.forge.util;

import org.bukkit.event.Listener;
import org.sct.forge.Forge;
import org.sct.forge.listener.ClickInvListener;
import org.sct.forge.listener.CloseInvListener;
import org.sct.forge.listener.JoinListener;

/**
 * @author SCT_Alchemy
 * @date 2018/12/14 11:18 AM
 */

public class EventUtil {

    public static void register(){
        register(new ClickInvListener());
        register(new JoinListener());
        register(new CloseInvListener());
    }

    private static void register(Listener listener){
        Forge.getInstance().getServer().getPluginManager().registerEvents(listener, Forge.getInstance());
    }

}
