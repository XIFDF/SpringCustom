package com.xifdf.spring;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClassPathXmlApplicationContext {

    //读取xml配置文件信息解析成对象到集合中
    Map<String, Object> map = new HashMap<String, Object>();
    //声明SAXBuilder对象， 解析配置文件中的信息
    SAXBuilder saxb = new SAXBuilder();

    public ClassPathXmlApplicationContext(String path) {

        try {
            //通过SAXBuilder对象的build方法，将读取到的配置文件解析为Document
            Document doc = saxb.build(this.getClass().getClassLoader().getResourceAsStream(path));
            //获取根节点
            Element root = doc.getRootElement();
            //获取子节点
            List<Element> childlist = root.getChildren();

            for(Element e : childlist) {
                //获取bean节点中的id类的名称
                String id = e.getAttributeValue("id");
                //获取bean节点中的className属性值，即类的全路径
                String className = e.getAttributeValue("className");
                //通过反射全路径，生成实例
                Object object = Class.forName(className).newInstance();
                map.put(id, object);

                //循环bean节点下的子节点
                for(Element ec : e.getChildren()) {
                    //获取property中的name属性值
                    String name = ec.getAttributeValue("name");
                    //获取property中的beanId属性
                    String bean = ec.getAttributeValue("beanId");
                    //操作字符串，按照驼峰命名规则来确定set方法的具体名称
                    String methodName = "set" + name.substring(0, 1).toUpperCase()
                                          + name.substring(1);
                    Object obj = map.get(bean);
                    //通过反射获得具体的方法
                    Method method = object.getClass().getMethod(methodName, obj.getClass().getInterfaces()[0]);
                    System.out.println("参数类型为：====" + obj.getClass().getInterfaces()[0]);
                    //object调用该方法，传入实参obj
                    method.invoke(object, obj);
                }
            }

        } catch (JDOMException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public Object getBean(String name){
        return map.get(name);
    }
}
