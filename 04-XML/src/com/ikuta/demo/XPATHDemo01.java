package com.ikuta.demo;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * 使用DOM4J的XPATH解析XML文件-获取元素节点的文本内容
 * 1.创建解析器
 * 2.通过解析器的read()方法将配置文件读取到内存中,生成一个Document[org.dom4j]对象树
 * 3.获取driver-name节点元素
 * 4.获取driverNameElt节点元素的文本内容[getStringValue()或者getText()]
 */
public class XPATHDemo01 {
    public static void main(String[] args) {
        try {
            // 1.创建解析器
            SAXReader reader = new SAXReader();
            // 2.通过解析器的read()方法将配置文件读取到内存中,生成一个Document[org.dom4j]对象树
            Document document = reader.read("conf/demo-of-xpath-text.xml");

            // 3.获取driver-name节点元素
            // driver-name节点元素的路径:config--->database-info-->driver-name

            // driver-name节点元素的xpath路径:/config/database-info/driver-name
            Element driverNameElt = (Element) document.selectSingleNode("/config/database-info/driver-name");

            // driver-name节点元素的xpath路径:config//driver-name
            // Element driverNameElt = (Element) document.selectSingleNode("config//driver-name");


            // driver-name节点元素的xpath路径://driver-name
            // Element driverNameElt = (Element) document.selectSingleNode("//driver-name");

            // 确保driver-name节点只有一个可以使用以下方法
            // Element driverNameElt = (Element) document.selectObject("//driver-name");

            // 4.获取driverNameElt节点元素的文本内容
            String driverName = driverNameElt.getStringValue();
            // String driverName = driverNameElt.getText();
            // String driverName = driverNameElt.getTextTrim();
            System.out.println(driverName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

