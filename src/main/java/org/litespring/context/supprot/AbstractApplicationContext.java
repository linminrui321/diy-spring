package org.litespring.context.supprot;

import org.litespring.beans.factory.support.DefaultBeanFactory;
import org.litespring.beans.factory.xml.XmlBeanDefinitionReader;
import org.litespring.context.ApplicationContext;
import org.litespring.core.io.Resource;
import org.litespring.util.ClassUtils;

public abstract class AbstractApplicationContext implements ApplicationContext {

    private DefaultBeanFactory factory = null;

    private ClassLoader beanClassLoader;


    public AbstractApplicationContext(String configFile) {
       this(configFile, ClassUtils.getDefaultClassLoader());
    }

    public AbstractApplicationContext(String configFile, ClassLoader classLoader) {
        factory = new DefaultBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        Resource resource = getResourceByPath(configFile);
        reader.loadBeanDefinitions(resource);
        factory.setBeanClassLoader(classLoader);
    }

    public Object getBean(String beanID) {
        return factory.getBean(beanID);
    }

    protected abstract Resource getResourceByPath(String path);

    public void setBeanClassLoader(ClassLoader classBeanLoader) {
        this.beanClassLoader = classBeanLoader;
    }

    public ClassLoader getBeanClassLoader() {
        return  null != this.beanClassLoader ? this.beanClassLoader : ClassUtils.getDefaultClassLoader();
    }
}
