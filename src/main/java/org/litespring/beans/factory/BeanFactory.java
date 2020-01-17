package org.litespring.beans.factory;


import org.litespring.beans.factory.config.ConfigrableBeanFactory;

public interface BeanFactory extends ConfigrableBeanFactory {
    Object getBean(String beanID);
}
