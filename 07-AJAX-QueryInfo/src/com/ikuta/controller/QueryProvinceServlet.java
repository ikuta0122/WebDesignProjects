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
        // ����get����
        String strProid = req.getParameter("proid");
        String name = "Ĭ����������";
        // ����dao����ѯ���ݿ�
        if (strProid != null && !"".equals(strProid.trim())) {
            // ����dao���󣬵������ķ���
            ProvinceDao dao = new ProvinceDao();
            name = dao.queryProvinceNameById(Integer.valueOf(strProid));
        }

        // ʹ��HttpServletResponse�������
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter out = resp.getWriter();
        out.println(name);
        out.flush();
        out.close();
    }
}
