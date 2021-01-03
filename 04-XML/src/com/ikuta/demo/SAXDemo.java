package com.ikuta.demo;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * SAX解析XML文件
 * 1.创建解析工厂
 * 2.通过解析工厂的newSAXParser()方法获取解析器
 * 3.创建事件处理器并重写character,startElement,endElement方法
 * 4.执行解析器的parser(XML文件路径,事件处理器)方法解析XML文件
 */
public class SAXDemo {
    public static void main(String[] args) {
        try {
            // 1.创建解析工厂
            SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
            // 2.通过解析工厂的newSAXParser()方法获取解析器
            SAXParser saxParser = saxParserFactory.newSAXParser();
            // 4.执行解析器的parser(XML文件路径,事件处理器)方法解析XML文件
            saxParser.parse("conf/demo-of-sax.xml", new MYDefaultHandler());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

// 3.创建事件处理器并重写character,startElement,endElement方法
class MYDefaultHandler extends DefaultHandler {
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        System.out.print("<" + qName + ">");
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        System.out.print(new String(ch, start, length));
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        System.out.print("</" + qName + ">");
    }
}