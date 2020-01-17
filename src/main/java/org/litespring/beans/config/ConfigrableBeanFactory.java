package org.litespring.beans.config;

public interface ConfigrableBeanFactory {
    void setBeanClassLoader(ClassLoader classBeanLoader);
    ClassLoader getBeanClassLoader();
}
