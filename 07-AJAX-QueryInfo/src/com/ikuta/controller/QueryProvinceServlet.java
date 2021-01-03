package com.ikuta.controller;

import com.ikuta.daos.ProvinceDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class QueryProvinceServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 处理get请求
        String strProid = req.getParameter("proid");
        String name = "默认是无数据";
        // 访问dao，查询数据库
        if (strProid != null && !"".equals(strProid.trim())) {
            // 创建dao对象，调用它的方法
            ProvinceDao dao = new ProvinceDao();
            name = dao.queryProvinceNameById(Integer.valueOf(strProid));
        }

        // 使用HttpServletResponse输出数据
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter out = resp.getWriter();
        out.println(name);
        out.flush();
        out.close();
    }
}
