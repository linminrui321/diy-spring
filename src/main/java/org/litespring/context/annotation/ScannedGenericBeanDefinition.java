package org.litespring.context.annotation;

import org.litespring.beans.factory.annotation.AnnotationBeanDefinition;
import org.litespring.beans.factory.support.GenericBeanDefinition;
import org.litespring.core.type.AnnotationMetadata;

public class ScannedGenericBeanDefinition extends GenericBeanDefinition implements AnnotationBeanDefinition {
    private AnnotationMetadata metadate;



    public ScannedGenericBeanDefinition(AnnotationMetadata metadate){
        super();
        this.metadate = metadate;
        setBeanClassName(this.metadate.getClassName());
    }



    public AnnotationMetadata getMetadata(){
        return this.metadate;
    }

}
