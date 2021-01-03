package com.ikuta.demo;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * 使用DOM4J的XPATH解析XML文件-获取元素节点属性对象的值
 * 1.创建解析器
 * 2.通过解析器的read()方法将配置文件读取到内存中,生成一个Document[org.dom4j]对象树
 * 3.获取元素节点对象
 * 4.获取元素节点对象的属性对象的值
 */
public class XPATHDemo02 {
    public static void main(String[] args) {
        try {
            // 1.创建解析器
            SAXReader reader = new SAXReader();
            // 2.通过解析器的read()方法将配置文件读取到内存中,生成一个Document[org.dom4j]对象树
            Document document = reader.read("conf/demo-of-xpath-attribute.xml");
            // 3.获取元素节点对象
            Element connectorElt = (Element) document.selectSingleNode("/server/service/connector");
            // 4.获取元素节点对象的属性对象
            Attribute portAttr = connectorElt.attribute("port");
            // 5.获取属性对象的值
            String portValue1 = portAttr.getStringValue();
            System.out.println(portValue1);

            // 第四步和第五步可以合并:通过元素节点直接获取属性对象的值
            String portValue2 = connectorElt.attributeValue("port");
            System.out.println(portValue2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
