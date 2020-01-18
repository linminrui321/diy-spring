package org.litespring.beans.propertyeditors;

import org.litespring.util.NumberUtils;
import org.litespring.util.StringUtils;

import java.beans.PropertyEditorSupport;
import java.text.NumberFormat;

public class CustomNumberEditor  extends PropertyEditorSupport {
    private Class<? extends Number> numberClass;
    private NumberFormat numberFormat;
    private boolean allowEmpty;

    public CustomNumberEditor(Class<? extends Number> numberClass, boolean allowEmpty) {
        this(numberClass, null, allowEmpty);
    }

    public CustomNumberEditor(Class<? extends Number> numberClass,
                              NumberFormat numberFormat,
                              boolean allowEmpty) {
        this.numberClass = numberClass;
        this.numberFormat = numberFormat;
        this.allowEmpty = allowEmpty;
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException{
        if(this.allowEmpty && !StringUtils.hasText(text)){
            setValue(null);
        }else if(this.numberFormat != null){
            setValue(NumberUtils.parseNumber(text, this.numberClass, this.numberFormat));
        }else {
            setValue(NumberUtils.parseNumber(text, this.numberClass));
        }

    }

    @Override
    public void setValue(Object value) {
        if (value instanceof Number) {
            super.setValue(NumberUtils.convertNumberToTargetClass((Number)value, this.numberClass));
        }else {
            super.setValue(value);
        }
    }

}



