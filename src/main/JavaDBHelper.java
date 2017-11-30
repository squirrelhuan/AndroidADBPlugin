package main;

import java.sql.*;
import java.util.Properties;

/** javaDB helper
 * Created by huan on 2017/11/30.
 */
public class JavaDBHelper {

    /** how to use javaDB refrence this method
     * @param args
     */
    public static void main(String[] args) {
        try {
            open();
            execute("drop table  user_uer");
            System.out.println("create table user_uer");
            execute("create table user_uer ( name varchar(20), score int)");
            execute("insert into user_uer ( name,score) values ('小明',89)");
            execute("insert into user_uer (name ,score) values ('小花',90)");
            ResultSet rSet = executeQuery("select name,score from user_uer");
            System.out.println("------------------------");
            while (rSet.next()) {
                System.out.println(rSet.getString("name"));
                System.out.println(rSet.getInt("score"));
            }
            System.out.println("query user_uer data");
            close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void execute(String sql) {
        try {
            statement = conn.createStatement();
            statement.execute(sql);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static ResultSet executeQuery(String sql) {
        ResultSet rSet = null;
            try {
                System.out.println("create derbyDB");
                conn.setAutoCommit(false);
                statement = conn.createStatement();
                rSet = statement.executeQuery(sql);
            } catch (Exception e) {
                e.printStackTrace();
            }
        return rSet;
    }

    //关闭连接
    public static void close() {
        try {
            statement.close();
            conn.commit();
            conn.close();
            DriverManager.getConnection("jdbc:derby:helloDB;shutDown=true");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static Statement statement;
    private static Connection conn;

    //开启连接
    public static void open() {
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance();
            System.out.println("Loaded the EmbeddedDriver");

            Properties props = new Properties();
            props.setProperty("user", "user");
            props.setProperty("password", "password");
            try {
                conn = DriverManager.getConnection("jdbc:derby:helloDB;create=true", props);
                System.out.println("create derbyDB");
                conn.setAutoCommit(false);

                statement = conn.createStatement();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
