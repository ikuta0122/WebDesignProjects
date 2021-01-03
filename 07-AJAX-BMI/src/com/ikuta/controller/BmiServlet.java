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
        //第一步:接受请求参数
        String name = req.getParameter("name");
        float weight = Float.valueOf(req.getParameter("weight"));
        float height = Float.valueOf(req.getParameter("height"));

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

        //第四步:把数据存入到request并请求转发到新的页面
        req.setAttribute("msg", msg);
        req.getRequestDispatcher("/result.jsp").forward(req, resp);
    }

}
