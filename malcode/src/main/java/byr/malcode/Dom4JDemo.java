package byr.malcode;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * Hello world!
 *
 */
public class Dom4JDemo {
    public static void main(String[] args) throws Exception {
        //1.创建Reader对象
        SAXReader reader = new SAXReader();
        //2.加载xml
        Document document = reader.read(new File("src/xmlData/test.xml"));
        //3.获取根节点
        Element rootElement = document.getRootElement();
        Element fileList = rootElement.element("file_list");
        System.out.println("file_error: "+fileList.attributeValue("file_error"));
        System.out.println("file_name: "+fileList.attributeValue("file_name"));
        System.out.println("file_uid: "+fileList.attributeValue("file_uid"));
        Element file = fileList.element("file");
        Element startBoot = file.element("start_boot");
        Element reBoot = file.element("reboot");
        if(startBoot != null) {
        	Element actionList = startBoot.element("action_list");
        	Iterator iterator = actionList.elementIterator();
        	while (iterator.hasNext()){
        		Element stu = (Element) iterator.next();
        		List<Attribute> attributes = stu.attributes();
        		System.out.println("======获取属性值======");
        		for (Attribute attribute : attributes) {
        			System.out.println(attribute.getName()+" : "+attribute.getValue());
        		}
        		System.out.println("======遍历子节点======");
        		Iterator iterator1 = stu.elementIterator();
        		while (iterator1.hasNext()){
        			Element stuChild = (Element) iterator1.next();
        			System.out.println("节点名："+stuChild.getName()+"---节点数："+stuChild.attributeValue("count"));
        			Iterator stuIterator = stuChild.elementIterator();
        			while(stuIterator.hasNext()) {
        				Element cc = (Element) stuIterator.next();
                		List<Attribute> attributesC = cc.attributes();
                		for (Attribute attribute : attributesC) {
                			System.out.println(attribute.getName()+" : "+attribute.getValue());
                		}
        			}
        		}
        	}
        }
        if(reBoot != null) {
        	Element actionList = startBoot.element("action_list");
        	Iterator iterator = actionList.elementIterator();
        	while (iterator.hasNext()){
        		Element stu = (Element) iterator.next();
        		List<Attribute> attributes = stu.attributes();
        		System.out.println("======获取属性值======");
        		for (Attribute attribute : attributes) {
        			System.out.println(attribute.getName()+" : "+attribute.getValue());
        		}
        		System.out.println("======遍历子节点======");
        		Iterator iterator1 = stu.elementIterator();
        		while (iterator1.hasNext()){
        			Element stuChild = (Element) iterator1.next();
        			System.out.println("节点名："+stuChild.getName()+"---节点数："+stuChild.attributeValue("count"));
        			Iterator stuIterator = stuChild.elementIterator();
        			while(stuIterator.hasNext()) {
        				Element cc = (Element) stuIterator.next();
                		List<Attribute> attributesC = cc.attributes();
                		for (Attribute attribute : attributesC) {
                			System.out.println(attribute.getName()+" : "+attribute.getValue());
                		}
        			}
        		}
        	}
        }
    }
}
