package org.sct.forge.service;

/**
 * @author SCT_Alchemy
 * @date 2018/12/14 10:38 AM
 */

public interface ForgeService {
    /**
     * 初始化玩家数据
     * @param name 玩家名字
     */
    void initData(String name);

    /**
     * 判断玩家是否拥有数据
     * @param name 玩家名字
     * @return true-有数据/false-没数据
     */
    boolean hasData(String name);

    /**
     * 给玩家增加exp
     * @param name 玩家名字
     * @param amount 经验点数量
     */
    void addExp(String name, int amount);

    /**
     * 获取玩家的EXP
     * @param name 名字
     * @return exp数量
     */
    int getExp(String name);
}
