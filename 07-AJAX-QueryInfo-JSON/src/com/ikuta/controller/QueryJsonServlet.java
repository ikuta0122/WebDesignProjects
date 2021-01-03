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
		// ��ȡ���������ʡ�ݵ�id
		String strProid = request.getParameter("proid");
		// Ĭ��ֵ�� {} ����ʾjson��ʽ������
		String json = "{}";
		// ��proid��ֵʱ������dao��ѯ����
		if (strProid != null && strProid.trim().length() > 0) {
			ProvinceDao pd = new ProvinceDao();
			Province province = pd.queryProvinceById(Integer.valueOf(strProid));
			// ʹ��JACKSON �� Province����תΪ JSON��ʽ���ַ���
			ObjectMapper om = new ObjectMapper();
			json = om.writeValueAsString(province);
		}

		// �ѻ�ȡ�����ݣ�ͨ�����紫��ajax�е��첽������Ӧ�������
		// ָ���������ˣ�servlet�����ظ����������json��ʽ������,json����ʹ��utf-8�����
		response.setContentType("application/json;charset=utf-8");
		PrintWriter out = response.getWriter();
		// �������---�Ḷ��ajax�� responseText����
		out.println(json);
		out.flush();
		out.close();
	}
}
