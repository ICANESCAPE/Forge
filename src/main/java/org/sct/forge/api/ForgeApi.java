package org.sct.forge.api;

import org.sct.forge.service.ForgeService;
import org.sct.forge.service.ForgeServiceImpl;

/**
 * @author SCT_Alchemy
 * @date 2018/12/14 10:38 AM
 * 锻造API
 */

public class ForgeApi {

    private static ForgeService forgeService = new ForgeServiceImpl();

    /**
     * 判断一名玩家是否拥有数据
     *
     * @param name
     *            玩家名
     * @return true[有]/false[没有]
     */
    public static boolean hasData(String name) {
        return forgeService.hasData(name);
    }

    /**
     * 初始化玩家数据
     *
     * @param name
     *            玩家名
     */
    public static void initialData(String name) {
        forgeService.initData(name);
    }

    /**
     * 取玩家的锻造点
     *
     * @param name
     *            玩家名
     * @return 成长点数量
     */
    public static int getExp(String name) {
        return forgeService.getExp(name);
    }

    /**
     * 给一个玩家增加指定数量锻造经验
     *
     * @param name
     *            玩家名
     * @param amount
     *            成长点
     */
    public static void addExp(String name, int amount) {
        forgeService.addExp(name, amount);
    }

}
