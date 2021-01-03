package com.ikuta.demo;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * SAX����XML�ļ�
 * 1.������������
 * 2.ͨ������������newSAXParser()������ȡ������
 * 3.�����¼�����������дcharacter,startElement,endElement����
 * 4.ִ�н�������parser(XML�ļ�·��,�¼�������)��������XML�ļ�
 */
public class SAXDemo {
    public static void main(String[] args) {
        try {
            // 1.������������
            SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
            // 2.ͨ������������newSAXParser()������ȡ������
            SAXParser saxParser = saxParserFactory.newSAXParser();
            // 4.ִ�н�������parser(XML�ļ�·��,�¼�������)��������XML�ļ�
            saxParser.parse("conf/demo-of-sax.xml", new MYDefaultHandler());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

// 3.�����¼�����������дcharacter,startElement,endElement����
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