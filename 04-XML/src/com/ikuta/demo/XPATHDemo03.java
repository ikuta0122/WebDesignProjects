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
 * 使用XPATH对象解析XML文件-综合训练
 */
public class XPATHDemo03 {
    public static void main(String[] args) {
        try {
            // 1.创建解析工厂
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            // 2.创建解析器
            DocumentBuilder builder = documentBuilderFactory.newDocumentBuilder();
            // 3.通过解析器读取配置文件,生成一个Document[org.w3c.dom]对象树
            Document document = builder.parse("conf/demo-of-xpath-comprehensive.xml");
            // 4.创建XPATH对象
            XPath xPath = XPathFactory.newInstance().newXPath();

            // Q1:获取bookstore节点下,book属性category值为web下,第二个title节点的文本内容
            String q1Xpath = "/bookstore/book[@category='web'][2]/title/text()";
            String q1Value = (String) xPath.evaluate(q1Xpath, document, XPathConstants.STRING);
            System.out.println(q1Value);

            // Q2:获取bookstore节点下,book属性category值为web下,title属性lang为en的节点内容
            String q2Xpath = "/bookstore/book[@category='web']/title[@lang='en']/text()";
            String q2Value = (String) xPath.evaluate(q2Xpath, document, XPathConstants.STRING);
            System.out.println(q2Value);

            // Q3:获取bookstore节点下,book属性category值为cooking下的title的属性lang的值
            String q3Xpath = "/bookstore/book[@category='cooking']/title/@lang";
            String q3Value = (String) xPath.evaluate(q3Xpath, document, XPathConstants.STRING);
            System.out.println(q3Value);

            // Q4:获取bookstore节点下所有book的节点集合
            String q4Xpath = "/bookstore/book";
            NodeList q4List = (NodeList) xPath.evaluate(q4Xpath, document, XPathConstants.NODESET);
            // 遍历q4Lsit
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

