package org.litespring.beans.factory;


import org.litespring.beans.config.ConfigrableBeanFactory;

public interface BeanFactory extends ConfigrableBeanFactory {
    Object getBean(String beanID);
}
