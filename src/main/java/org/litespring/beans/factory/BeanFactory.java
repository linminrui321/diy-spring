package org.litespring.beans.factory;


import org.litespring.beans.factory.config.ConfigrableBeanFactory;

public interface BeanFactory    {
    Object getBean(String beanID);
}
