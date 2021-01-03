package com.ikuta.daos;

import java.sql.*;

//使用jdbc访问数据库
public class ProvinceDao {
    // 根据id获取名称
    public String queryProvinceNameById(Integer provinceId) {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        String url = "jdbc:mysql://localhost:3306/learn_for_ajax";
        String username = "root";
        String password = "admin";

        String sql;
        String name = "";
        // 加载驱动
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, username, password);
            // 创建PreparedStatement
            sql = "select name from province where id=?";
            pst = conn.prepareStatement(sql);
            // 设置参数值
            pst.setInt(1, provinceId);
            // 执行sql
            rs = pst.executeQuery();
            if (rs.next()) {
                name = rs.getString("name");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (pst != null) {
                    pst.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return name;
    }
}
