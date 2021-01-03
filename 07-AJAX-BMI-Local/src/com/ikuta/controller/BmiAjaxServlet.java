package com.ikuta.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class BmiAjaxServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //��һ��:���ղ���
        String name = request.getParameter("name");
        float weight = Float.valueOf(request.getParameter("weight"));
        float height = Float.valueOf(request.getParameter("height"));

        //�ڶ���:����BMI
        float bmi = weight / (height * height);

        //������:�ж�BMI�ķ�Χ
        String msg = "";
        if (bmi <= 18.5) {
            msg = "����";
        } else if (bmi > 18.5 && bmi <= 23.9) {
            msg = "����";
        } else if (bmi > 24 && bmi <= 27) {
            msg = "����";
        } else if (bmi > 28 && bmi <= 32) {
            msg = "����";
        } else if (bmi > 32) {
            msg = "�ǳ�����";
        }
        msg = name + "����/Ůʿ,����!����BMIָ��Ϊ:" + bmi + "[" + msg + "]";

        //���Ĳ�:��ӦAJAX��Ҫ�����ݣ�ʹ��HttpServletResponse�������
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.print(msg);
        out.flush();
        out.close();
    }
}
