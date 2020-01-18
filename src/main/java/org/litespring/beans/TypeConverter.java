package org.litespring.beans;

import java.beans.PropertyEditor;

public interface TypeConverter {
  <T> T  convertIfNecessary(Object value, Class<T> integerClass);

    PropertyEditor getDefaultEditor(Class<?> requiredType);
}
