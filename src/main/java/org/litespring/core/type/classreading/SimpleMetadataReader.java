package org.litespring.core.type.classreading;

import jdk.internal.org.objectweb.asm.ClassReader;
import org.litespring.core.annotation.AnnotationAttributes;
import org.litespring.core.io.Resource;
import org.litespring.core.type.AnnotationMetadata;
import org.litespring.core.type.ClassMetadata;

import java.io.BufferedInputStream;
import java.io.IOException;

public class SimpleMetadataReader  {

    private Resource resource;

    private ClassMetadata classMetadata;

    private AnnotationMetadata annotationMetadata;

    public SimpleMetadataReader(Resource resource) throws IOException {
        BufferedInputStream is = new BufferedInputStream(resource.getInputStream());
        ClassReader classReader;
        try {
            classReader =  new ClassReader(is);
        }finally {
            is.close();
        }


        AnnotationMetadataReadingVisitor visitor = new AnnotationMetadataReadingVisitor();
        classReader.accept(visitor, ClassReader.SKIP_DEBUG);

        this.annotationMetadata = visitor;
        this.classMetadata = visitor;
        this.resource = resource;

    }

    public Resource getResource() {
        return this.resource;
    }

    public ClassMetadata getClassMetadata() {
        return this.classMetadata;
    }

    public AnnotationMetadata getAnnotationMetadata() {
        return this.annotationMetadata;
    }


}
