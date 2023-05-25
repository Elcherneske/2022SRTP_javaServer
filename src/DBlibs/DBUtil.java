package DBlibs;

import java.sql.*;

/**
 * @author Peter Cheung
 * @user Liao Yucheng
 * @date 2023/2/23 15:11
 */

public class DBUtil {
    //连接信息
    private static String driverName = "com.mysql.cj.jdbc.Driver";
    private static String url = "jdbc:mysql://localhost:3306/srtp2022?useUnicode=true&characterEncoding=utf8&serverTimezone=UTC&useSSL=false";
    private static String username = "root";
    private static String password = "fanggroup";

    //注册驱动，使用静态块，只需注册一次
    static {
        //1、注册驱动
        try {
            //通过反射，注册驱动
            Class.forName(driverName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    //jdbc对象
    private Connection connection = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    //获取连接
    public void getConnection() {
        if(connection == null) {
            try {
                //2、建立连接
                connection = DriverManager.getConnection(url, username, password);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    //更新操作：增删改
    public int update(String sql, Object[] objs) {
        int i = 0;
        try {
            getConnection();
            //3、创建sql对象
            preparedStatement = connection.prepareStatement(sql);
            for (int j = 0; j < objs.length; j++) {
                preparedStatement.setObject(j + 1, objs[j]);
            }
            //4、执行sql，返回改变的行数
            i = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }

    //查询操作
    public ResultSet select(String sql, Object[] objs) {
        try {
            getConnection();
            //3、创建sql对象
            preparedStatement = connection.prepareStatement(sql);
            for (int j = 0; j < objs.length; j++) {
                preparedStatement.setObject(j + 1, objs[j]);
            }
            //4、执行sql，返回查询到的set集合
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public boolean db_execute(String sql)
    {
        boolean result = false;
        try{
            getConnection();
            Statement state = connection.createStatement();
            result = state.execute(sql);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }

    //断开连接
    public void closeConnection() {
        //5、断开连接
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
