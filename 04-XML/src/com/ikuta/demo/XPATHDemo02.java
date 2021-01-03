package com.ikuta.demo;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * ʹ��DOM4J��XPATH����XML�ļ�-��ȡԪ�ؽڵ����Զ����ֵ
 * 1.����������
 * 2.ͨ����������read()�����������ļ���ȡ���ڴ���,����һ��Document[org.dom4j]������
 * 3.��ȡԪ�ؽڵ����
 * 4.��ȡԪ�ؽڵ��������Զ����ֵ
 */
public class XPATHDemo02 {
    public static void main(String[] args) {
        try {
            // 1.����������
            SAXReader reader = new SAXReader();
            // 2.ͨ����������read()�����������ļ���ȡ���ڴ���,����һ��Document[org.dom4j]������
            Document document = reader.read("conf/demo-of-xpath-attribute.xml");
            // 3.��ȡԪ�ؽڵ����
            Element connectorElt = (Element) document.selectSingleNode("/server/service/connector");
            // 4.��ȡԪ�ؽڵ��������Զ���
            Attribute portAttr = connectorElt.attribute("port");
            // 5.��ȡ���Զ����ֵ
            String portValue1 = portAttr.getStringValue();
            System.out.println(portValue1);

            // ���Ĳ��͵��岽���Ժϲ�:ͨ��Ԫ�ؽڵ�ֱ�ӻ�ȡ���Զ����ֵ
            String portValue2 = connectorElt.attributeValue("port");
            System.out.println(portValue2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
