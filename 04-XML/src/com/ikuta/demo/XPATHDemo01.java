package com.ikuta.demo;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * ʹ��DOM4J��XPATH����XML�ļ�-��ȡԪ�ؽڵ���ı�����
 * 1.����������
 * 2.ͨ����������read()�����������ļ���ȡ���ڴ���,����һ��Document[org.dom4j]������
 * 3.��ȡdriver-name�ڵ�Ԫ��
 * 4.��ȡdriverNameElt�ڵ�Ԫ�ص��ı�����[getStringValue()����getText()]
 */
public class XPATHDemo01 {
    public static void main(String[] args) {
        try {
            // 1.����������
            SAXReader reader = new SAXReader();
            // 2.ͨ����������read()�����������ļ���ȡ���ڴ���,����һ��Document[org.dom4j]������
            Document document = reader.read("conf/demo-of-xpath-text.xml");

            // 3.��ȡdriver-name�ڵ�Ԫ��
            // driver-name�ڵ�Ԫ�ص�·��:config--->database-info-->driver-name

            // driver-name�ڵ�Ԫ�ص�xpath·��:/config/database-info/driver-name
            Element driverNameElt = (Element) document.selectSingleNode("/config/database-info/driver-name");

            // driver-name�ڵ�Ԫ�ص�xpath·��:config//driver-name
            // Element driverNameElt = (Element) document.selectSingleNode("config//driver-name");


            // driver-name�ڵ�Ԫ�ص�xpath·��://driver-name
            // Element driverNameElt = (Element) document.selectSingleNode("//driver-name");

            // ȷ��driver-name�ڵ�ֻ��һ������ʹ�����·���
            // Element driverNameElt = (Element) document.selectObject("//driver-name");

            // 4.��ȡdriverNameElt�ڵ�Ԫ�ص��ı�����
            String driverName = driverNameElt.getStringValue();
            // String driverName = driverNameElt.getText();
            // String driverName = driverNameElt.getTextTrim();
            System.out.println(driverName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

