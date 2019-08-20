package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBHelper {
    //数据库驱动
    private static final String driver="com.mysql.jdbc.Driver";
    //连接数据库的URL地址
    private static final String url="jdbc:mysql://localhost:3306/shopping?useUnicode=true&charaterEncoding=UTF-8";
    //数据库用户名
    private static final String username="root";
    //数据库密码
    private static final String password="123456";

    private static Connection connection=null;

    //静态代码块负责加载驱动
    static {
        try{
            Class.forName(driver);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //单例模式返回数据库连接对象
    public static Connection getConnection() throws SQLException {
        if (connection==null) {
                connection= DriverManager.getConnection(url,username,password);
                return connection;
            }
        return connection;
        }

    public static void main(String[] args) {
        try {
            Connection connection = DBHelper.getConnection();
            if (connection!=null){
                System.out.println("数据库连接正常");
            }else {
                System.out.println("数据库连接异常");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    }

