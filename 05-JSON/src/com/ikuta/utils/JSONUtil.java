package com.ikuta.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;

/**
 * 工具类不能被继承
 */
public final class JSONUtil {

    /**
     * 工具类不能实例化
     */
    private JSONUtil() {
    }

    /**
     * 将Java对象转换为JSON格式字符串
     *
     * @param obj Java对象
     * @return JSON格式字符串
     */
    public static String objectToJSON(Object obj) {
        if (obj == null) {
            return null;
        }
        Class clz = obj.getClass();
        Field[] fields = clz.getDeclaredFields();
        StringBuffer stringBuffer = new StringBuffer(1024);
        stringBuffer.append("{");
        try {
            for (int i = 0; i < fields.length; i++) {
                fields[i].setAccessible(true);
                Object field = fields[i].get(obj);
                if (field == null) {
                    continue;
                } else {
                    String value = field.toString();
                    stringBuffer.append("\"")
                            .append(fields[i].getName())
                            .append("\"")
                            .append(":")
                            .append("\"")
                            .append(value)
                            .append("\"")
                            .append(",");
                }
            }
            int index = stringBuffer.lastIndexOf(",");
            stringBuffer.replace(index, index + 1, "}");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return stringBuffer.toString();
    }

    /**
     * 将Java对象数组转换为JSON格式字符串
     *
     * @param objs Java对象数组
     * @return JSON格式字符串
     */
    public static String objectsToJSON(Object[] objs) {
        if (objs == null) {
            return null;
        }
        StringBuffer stringBuffer = new StringBuffer(1024);
        stringBuffer.append("[");
        for (int i = 0; i < objs.length; i++) {
            stringBuffer.append(objectToJSON(objs[i]));
            if (i != objs.length - 1) {
                stringBuffer.append(",");
            }
        }
        stringBuffer.append("]");
        return stringBuffer.toString();
    }

    /**
     * 将JSON格式字符串转化为Java对象
     *
     * @param json JSON格式字符串
     * @param clz  类
     * @return Java对象
     */
    public static Object JSONToObject(String json, Class clz) {
        if (json == null || clz == null) {
            return null;
        }
        Object object = null;
        StringBuffer stringBuffer = new StringBuffer(1024);
        try {
            object = clz.newInstance();
            stringBuffer.append(json);
            int start = 0;
            while (start != stringBuffer.length() - 1) {
                //获取属性名
                int first = stringBuffer.indexOf("\"", start);
                int second = stringBuffer.indexOf("\"", first + 1);
                String fieldName = stringBuffer.substring(first + 1, second);

                //获取属性值
                first = stringBuffer.indexOf(":", second);
                second = stringBuffer.indexOf(",", first);
                if (second < 0) {
                    second = stringBuffer.indexOf("}", first);
                }
                String fieldValue = stringBuffer.substring(first + 1, second);
                if (fieldValue.contains("\"")) {
                    fieldValue = fieldValue.substring(1, fieldValue.length() - 1);
                }

                //设置Java对象的属性及其属性值
                Field field = clz.getDeclaredField(fieldName);
                field.setAccessible(true);
                if (field.getType() == Integer.class || field.getType() == Integer.TYPE) {
                    field.set(object, Integer.parseInt(fieldValue));
                } else if (field.getType() == Double.class || field.getType() == Double.TYPE) {
                    field.set(object, Double.parseDouble(fieldValue));
                } else if (field.getType() == Float.class || field.getType() == Float.TYPE) {
                    field.set(object, Float.parseFloat(fieldValue));
                } else if (field.getType() == Boolean.class || field.getType() == Boolean.TYPE) {
                    field.set(object, Boolean.parseBoolean(fieldValue));
                } else {
                    field.set(object, fieldValue);
                }
                //下标后移
                start = second;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return object;
    }

    /**
     * 将JSON格式字符串转换为Java对象的集合
     *
     * @param json JSON格式字符串
     * @param clz  类
     * @return Java对象的集合
     */
    public static ArrayList<Object> JSONToObjects(String json, Class clz) {
        if (json == null || clz == null) {
            return null;
        }
        StringBuffer stringBuffer = new StringBuffer(1024);
        ArrayList<Object> list = new ArrayList<Object>();
        stringBuffer.append(json);
        int start = 0;
        while (start != stringBuffer.length() - 1) {
            int first = stringBuffer.indexOf("{", start);
            int second = stringBuffer.indexOf("}", start);
            Object temp = JSONUtil.JSONToObject(stringBuffer.substring(first, second + 1), clz);
            list.add(temp);
            start = second + 1;
        }
        return list;
    }

    /**
     * 将Java对象集合转换为Java对象数组
     *
     * @param list Java对象集合
     * @return Java对象数组
     */
    public static Object[] ListToArray(ArrayList<Object> list) {
        if (list == null) {
            return null;
        }
        Object[] objects = new Object[list.size()];
        for (int i = 0; i < objects.length; i++) {
            objects[i] = list.get(i);
        }
        return objects;
    }

}