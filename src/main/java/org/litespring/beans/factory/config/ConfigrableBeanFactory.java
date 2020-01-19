package org.litespring.beans.factory.config;

import org.litespring.beans.factory.BeanFactory;

public interface ConfigrableBeanFactory extends BeanFactory   {
    void setBeanClassLoader(ClassLoader classBeanLoader);
    ClassLoader getBeanClassLoader();
}
