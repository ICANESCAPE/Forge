package org.sct.forge.service;

import org.sct.forge.dao.ForgeDao;

/**
 * @author SCT_Alchemy
 * @date 2018/12/14 10:38 AM
 */

public class ForgeServiceImpl implements ForgeService {
    @Override
    public void initData(String name) {
        if (name == null || name.equalsIgnoreCase("")) {
            return;
        }
        if (!hasData(name)) {
            ForgeDao.initData(name);
        }
    }

    @Override
    public boolean hasData(String name) {
        if (name == null || name.equalsIgnoreCase("")) {
            return false;
        }
        if (ForgeDao.hasData(name)) {
            return true;
        }
        return false;
    }

    @Override
    public void addExp(String name, int amount) {
        if (name == null || name.equalsIgnoreCase("")) {
            return;
        }
        if (!hasData(name)) {
            ForgeDao.initData(name);
            return;
        }
        ForgeDao.addExp(name, amount);
    }

    @Override
    public int getExp(String name) {
        if (name == null || name.equalsIgnoreCase("")) {
            return 0;
        }
        if (!hasData(name)) {
            ForgeDao.initData(name);
            return 0;
        }
        return ForgeDao.getExp(name);
    }

}
