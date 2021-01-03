package com.ikuta.demo;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.util.Iterator;

/**
 * DOM4J����XML�ļ�
 * 1.����������
 * 2.��ȡDocument����
 * 3.��ȡ���ڵ�
 * 4.�������ڵ�
 */
public class DOM4JDemo {
    public static void main(String[] args) {
        try {
            // 1.����������
            SAXReader reader = new SAXReader();
            // 2.��ȡDocument����
            Document document = reader.read("conf/demo-of-dom4j.xml");
            // 3.��ȡ���ڵ�
            Element rootElement = document.getRootElement();
            // 4.�������ڵ�
            for (Iterator<Element> iterator = rootElement.elementIterator(); iterator.hasNext(); ) {//�����ڵ�
                Element element = iterator.next();
                for (Iterator<Element> innerIter = element.elementIterator(); innerIter.hasNext(); ) {//�����ڵ�
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
