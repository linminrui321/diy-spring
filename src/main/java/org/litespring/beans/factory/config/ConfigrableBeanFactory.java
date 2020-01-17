package org.litespring.beans.factory.config;

public interface ConfigrableBeanFactory {
    void setBeanClassLoader(ClassLoader classBeanLoader);
    ClassLoader getBeanClassLoader();
}
