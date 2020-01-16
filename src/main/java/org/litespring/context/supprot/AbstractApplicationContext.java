package org.litespring.context.supprot;

import org.litespring.beans.factory.support.DefaultBeanFactory;
import org.litespring.beans.factory.xml.XmlBeanDefinitionReader;
import org.litespring.context.ApplicationContext;

public class AbstractApplicationContext implements ApplicationContext {

    private DefaultBeanFactory factory = null;

    public AbstractApplicationContext(String configFile) {
        factory = new DefaultBeanFactory();
        XmlBeanDefinitionReader reader =  new XmlBeanDefinitionReader(factory);
        reader.loadBeanDefinitions();
    }

    public Object getBean(String beanID) {
        return null;
    }
}
