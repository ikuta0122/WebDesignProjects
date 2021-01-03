package com.ikuta.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class BmiServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //��һ��:�����������
        String name = req.getParameter("name");
        float weight = Float.valueOf(req.getParameter("weight"));
        float height = Float.valueOf(req.getParameter("height"));

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

        //���Ĳ�:�����ݴ��뵽request������ת�����µ�ҳ��
        req.setAttribute("msg", msg);
        req.getRequestDispatcher("/result.jsp").forward(req, resp);
    }

}
