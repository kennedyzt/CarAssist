package com.ruiyi.carassistant;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidPooledConnection;

public class DataSourcePool {
    public static void main(String[] args) {
        DruidDataSource dataSource = new DruidDataSource();
        //dataSource.setUrl("jdbc:mysql://120.24.152.177:3308/carassist?autoReconnect=true");
        dataSource.setUsername("root");
        dataSource.setPassword("131415");
        dataSource.setMaxActive(20);
        try {
            DruidPooledConnection connection = dataSource.getConnection();
            String sql = "select * from userinfo";
            PreparedStatement prepareStatement = connection.prepareStatement(sql);
            ResultSet resultSet = prepareStatement.executeQuery();
            while(resultSet.next()){
                System.out.println(resultSet.getObject("phone"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
