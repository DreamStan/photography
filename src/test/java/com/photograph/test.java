package com.photograph;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.sql.*;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Eminem on 2018/1/3.
 */
public class test {

    static String URL = "jdbc:mysql://localhost:3306/photograph";
    static String USER = "root";
    static String PWD = "parkour1998";
    static String DRIVER = "com.mysql.jdbc.Driver";

    public static Connection getConnection(){
        Connection connection = null;
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL,USER,PWD);
            if (!connection.isClosed()){
                System.out.println("成功");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static void main(String[] args) {
        getConnection();
    }


}