package org.litespring.test.v4;

import org.junit.Assert;
import org.junit.Test;
import org.litespring.beans.factory.annotation.Component;
import org.litespring.core.annotation.AnnotationAttributes;
import org.litespring.core.io.ClassPathResource;
import org.litespring.core.type.AnnotationMetadata;
import org.litespring.core.type.classreading.SimpleMetadataReader;

import java.io.IOException;

public class MetadataReaderTest {

    @Test
    public void testGetMetadata() throws IOException{
        ClassPathResource resource = new ClassPathResource("org/litespring/service/v4/PetStoreService.class");
        SimpleMetadataReader reader = new SimpleMetadataReader(resource);
        AnnotationMetadata amd =  reader.getAnnotationMetadata();
        String annotation = Component.class.getName();
        Assert.assertTrue(amd.hasAnnotation(annotation));
        AnnotationAttributes attributes =  amd.getAnnotationAttributes(annotation);
        Assert.assertEquals("petStore", attributes.get("value"));
        Assert.assertFalse(amd.isAbstract());
        Assert.assertFalse(amd.isFinal());
        Assert.assertEquals("org.litespring.service.v4.PetStoreService",
                amd.getClassName());
    }
}
