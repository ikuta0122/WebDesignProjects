package com.ikuta.demo;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

/**
 * ʹ��XPATH�������XML�ļ�-�ۺ�ѵ��
 */
public class XPATHDemo03 {
    public static void main(String[] args) {
        try {
            // 1.������������
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            // 2.����������
            DocumentBuilder builder = documentBuilderFactory.newDocumentBuilder();
            // 3.ͨ����������ȡ�����ļ�,����һ��Document[org.w3c.dom]������
            Document document = builder.parse("conf/demo-of-xpath-comprehensive.xml");
            // 4.����XPATH����
            XPath xPath = XPathFactory.newInstance().newXPath();

            // Q1:��ȡbookstore�ڵ���,book����categoryֵΪweb��,�ڶ���title�ڵ���ı�����
            String q1Xpath = "/bookstore/book[@category='web'][2]/title/text()";
            String q1Value = (String) xPath.evaluate(q1Xpath, document, XPathConstants.STRING);
            System.out.println(q1Value);

            // Q2:��ȡbookstore�ڵ���,book����categoryֵΪweb��,title����langΪen�Ľڵ�����
            String q2Xpath = "/bookstore/book[@category='web']/title[@lang='en']/text()";
            String q2Value = (String) xPath.evaluate(q2Xpath, document, XPathConstants.STRING);
            System.out.println(q2Value);

            // Q3:��ȡbookstore�ڵ���,book����categoryֵΪcooking�µ�title������lang��ֵ
            String q3Xpath = "/bookstore/book[@category='cooking']/title/@lang";
            String q3Value = (String) xPath.evaluate(q3Xpath, document, XPathConstants.STRING);
            System.out.println(q3Value);

            // Q4:��ȡbookstore�ڵ�������book�Ľڵ㼯��
            String q4Xpath = "/bookstore/book";
            NodeList q4List = (NodeList) xPath.evaluate(q4Xpath, document, XPathConstants.NODESET);
            // ����q4Lsit
            for (int i = 0; i < q4List.getLength(); i++) {
                Element bookElt = (Element) q4List.item(i);
                String titleValue = (String) xPath.evaluate("title", bookElt, XPathConstants.STRING);
                String authorValue = (String) xPath.evaluate("author", bookElt, XPathConstants.STRING);
                String yearValue = (String) xPath.evaluate("year", bookElt, XPathConstants.STRING);
                String priceValue = (String) xPath.evaluate("price", bookElt, XPathConstants.STRING);
                System.out.println(titleValue + "|" + authorValue + "|" + yearValue + "|" + priceValue);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

