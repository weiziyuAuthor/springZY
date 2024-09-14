package cn.hello.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @Author ziyu.wei
 * <p>
 * 2024/4/25 21:12
 */
public class Utils {

//    private static final String driver = "com.mysql.cj.jdbc.Driver";
    private static final String driver = "com.mysql.jdbc.Driver";
    public static Connection getConn(String url, String userName, String userPwd) {
        try {
            Class.forName(driver);

//            Properties props =new Properties();
//            props.setProperty("user", userName);
//            props.setProperty("password", userPwd);
//            props.setProperty("remarks", "true");
//            props.setProperty("useInformationSchema", "true");

//            return DriverManager.getConnection(url, props);
            return DriverManager.getConnection(url, userName, userPwd);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void closeConn(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
