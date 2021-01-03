package com.ikuta.demo;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.util.Iterator;

/**
 * DOM4J解析XML文件
 * 1.创建解析器
 * 2.获取Document对象
 * 3.获取根节点
 * 4.遍历根节点
 */
public class DOM4JDemo {
    public static void main(String[] args) {
        try {
            // 1.创建解析器
            SAXReader reader = new SAXReader();
            // 2.获取Document对象
            Document document = reader.read("conf/demo-of-dom4j.xml");
            // 3.获取根节点
            Element rootElement = document.getRootElement();
            // 4.遍历根节点
            for (Iterator<Element> iterator = rootElement.elementIterator(); iterator.hasNext(); ) {//二级节点
                Element element = iterator.next();
                for (Iterator<Element> innerIter = element.elementIterator(); innerIter.hasNext(); ) {//三级节点
                    Element innerElt = innerIter.next();
                    String innerValue = innerElt.getStringValue();
                    System.out.println(innerValue);
                }
                System.out.println();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
