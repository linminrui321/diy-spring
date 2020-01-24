package org.litespring.test.v4;

import jdk.internal.org.objectweb.asm.ClassReader;
import org.junit.Assert;
import org.junit.Test;
import org.litespring.core.annotation.AnnotationAttributes;
import org.litespring.core.io.ClassPathResource;
import org.litespring.core.type.classreading.AnnotationMetadataReadingVisitor;
import org.litespring.core.type.classreading.ClassMetadataReadingVisitor;

import java.io.IOException;

public class ClassReaderTest {
    @Test
    public void testGetClassMetaDate() throws IOException{
        ClassPathResource resource = new ClassPathResource("org/litespring/service/v4/PetStoreService.class");
        ClassReader reader = new ClassReader(resource.getInputStream());
        ClassMetadataReadingVisitor visitor =  new ClassMetadataReadingVisitor();
        reader.accept(visitor, ClassReader.SKIP_DEBUG);
        Assert.assertFalse(visitor.isAbstract());
        Assert.assertFalse(visitor.isInterface());
        Assert.assertFalse(visitor.isFinal());
        Assert.assertEquals("org.litespring.service.v4.PetStoreService", visitor.getClassName());
        Assert.assertEquals("java.lang.Object", visitor.getSuperClassName());
        Assert.assertEquals(0, visitor.getInterfaceNames().length);
    }


    @Test
    public void testGetAnnotation() throws Exception{
        ClassPathResource resource = new ClassPathResource("org/litespring/service/v4/PetStoreService.class");
        ClassReader reader = new ClassReader(resource.getInputStream());
        AnnotationMetadataReadingVisitor visitor =  new AnnotationMetadataReadingVisitor();
        reader.accept(visitor, ClassReader.SKIP_DEBUG );
        String annotation = "org.litespring.beans.factory.annotation.Component";
        Assert.assertTrue(visitor.hasAnnotation(annotation));
        AnnotationAttributes attriubtes =  visitor.getAnnotationAttributes(annotation);
        Assert.assertEquals("petStore", attriubtes.get("value"));

    }
}
