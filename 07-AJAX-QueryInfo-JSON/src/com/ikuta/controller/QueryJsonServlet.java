package com.ikuta.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ikuta.beans.Province;
import com.ikuta.daos.ProvinceDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class QueryJsonServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 获取请求参数，省份的id
		String strProid = request.getParameter("proid");
		// 默认值， {} ：表示json格式的数据
		String json = "{}";
		// 当proid有值时，调用dao查询数据
		if (strProid != null && strProid.trim().length() > 0) {
			ProvinceDao pd = new ProvinceDao();
			Province province = pd.queryProvinceById(Integer.valueOf(strProid));
			// 使用JACKSON 把 Province对象转为 JSON格式的字符串
			ObjectMapper om = new ObjectMapper();
			json = om.writeValueAsString(province);
		}

		// 把获取的数据，通过网络传给ajax中的异步对象，响应结果数据
		// 指定服务器端（servlet）返回给浏览器的是json格式的数据,json数据使用utf-8编码的
		response.setContentType("application/json;charset=utf-8");
		PrintWriter out = response.getWriter();
		// 输出数据---会付给ajax中 responseText属性
		out.println(json);
		out.flush();
		out.close();
	}
}
