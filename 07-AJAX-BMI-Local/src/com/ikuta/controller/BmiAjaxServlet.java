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
        //第一步:接收参数
        String name = request.getParameter("name");
        float weight = Float.valueOf(request.getParameter("weight"));
        float height = Float.valueOf(request.getParameter("height"));

        //第二步:计算BMI
        float bmi = weight / (height * height);

        //第三步:判断BMI的范围
        String msg = "";
        if (bmi <= 18.5) {
            msg = "过轻";
        } else if (bmi > 18.5 && bmi <= 23.9) {
            msg = "正常";
        } else if (bmi > 24 && bmi <= 27) {
            msg = "过重";
        } else if (bmi > 28 && bmi <= 32) {
            msg = "肥胖";
        } else if (bmi > 32) {
            msg = "非常肥胖";
        }
        msg = name + "先生/女士,您好!您的BMI指数为:" + bmi + "[" + msg + "]";

        //第四步:响应AJAX需要的数据，使用HttpServletResponse输出数据
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.print(msg);
        out.flush();
        out.close();
    }
}
