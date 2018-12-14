package org.sct.forge.dao;

/**
 * @author SCT_Alchemy
 * @date 2018/12/13 2:35 PM
 */

import java.sql.Connection;
import java.sql.SQLException;

public class BasicDao {

    public static Connection connection;

    public static void setConnection(Connection connection) { BasicDao.connection  = connection; }

    public static Connection getConnection() {
        return connection;
    }

    public static void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            System.out.println("错误：" + e.getMessage());
        }
    }

    public static boolean isClose() {
        try {
            if (connection != null &&
                    connection.isClosed()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            System.out.println("错误: " + e.getMessage());
        }
        return false;
    }


}
