package org.litespring.beans.factory.support;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.litespring.beans.BeanDefinition;
import org.litespring.beans.factory.BeanCreationException;
import org.litespring.beans.factory.BeanDefinitionStoreException;
import org.litespring.beans.factory.BeanFactory;
import org.litespring.util.ClassUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DefaultBeanFactory implements BeanFactory {


    public static final String ID_ATTRIBUTE = "id";

    public static final String CLASS_ATTRIBUTE = "class";

    public static final String SCOPE_ATTRIBUTE = "scope";

    private final Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<String, BeanDefinition>(64);

    public DefaultBeanFactory(String configFile) {
        InputStream is = null;
        try{
            ClassLoader cl = ClassUtils.getDefaultClassLoader();
            is = cl.getResourceAsStream(configFile);
            SAXReader reader = new SAXReader();
            Document doc = reader.read(is);
            Element root = doc.getRootElement();
            Iterator<Element> iter = root.elementIterator();
            while (iter.hasNext()){
                Element element = iter.next();
                String id = element.attributeValue(ID_ATTRIBUTE);
                String beanClassName = element.attributeValue(CLASS_ATTRIBUTE);
                BeanDefinition bd = new GenericBeanDefinition(id, beanClassName);
                this.beanDefinitionMap.put(id, bd);

            }
        } catch (Exception e) {
            throw new BeanDefinitionStoreException("IOException parsing XML document",e);
        }finally{
            if(is != null){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public BeanDefinition getBeanDefinition(String beanID) {
        return beanDefinitionMap.get(beanID);
    }

    public Object getBean(String beanID) {
        BeanDefinition bd = this.getBeanDefinition(beanID);
        if(null == bd){
            throw new BeanCreationException("Bean Definition does not exist");
        }
        ClassLoader cl = ClassUtils.getDefaultClassLoader();
        String beanClassName = bd.getBeanClassName();
        try{
            Class<?> clz = cl.loadClass(beanClassName);
            return clz.newInstance();
        }catch (Exception e){
            throw new BeanCreationException("create" + beanClassName + "fail");
        }
    }
}
