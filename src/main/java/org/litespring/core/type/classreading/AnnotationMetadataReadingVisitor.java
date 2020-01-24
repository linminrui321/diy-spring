package org.litespring.core.type.classreading;

import jdk.internal.org.objectweb.asm.AnnotationVisitor;
import org.litespring.core.annotation.AnnotationAttributes;

import org.springframework.asm.Type;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;


public class AnnotationMetadataReadingVisitor extends ClassMetadataReadingVisitor  {
	
	//记录有哪些注解
	private final Set<String> annotationSet = new LinkedHashSet<String>(4);
	//拥有的注解对应都有哪些属性
	private final Map<String, AnnotationAttributes> attributeMap = new LinkedHashMap<String, AnnotationAttributes>(4);
	
	public AnnotationMetadataReadingVisitor() {
		
	}
	
	/**
	 * desc:Lorg/litespring/stereotype/Component; 
	 * 		Lorg中的L代表他就是一个Object 在java字节码中就是用这种方式表达的
	 * 
	 * visible:
	 */
	@Override
	public AnnotationVisitor visitAnnotation(final String desc, boolean visible) {
		//Lorg/litespring/stereotype/Component;	-->	org/litespring/stereotype/Component;
		String className = Type.getType(desc).getClassName();
		this.annotationSet.add(className);
		//通过AnnotationAttributesReadingVisitor解析该注解
		return new AnnotationAttributesReadingVisitor(className, this.attributeMap);
	}
	
	public Set<String> getAnnotationTypes() {
		return this.annotationSet;
	}

	public boolean hasAnnotation(String annotationType) {
		return this.annotationSet.contains(annotationType);
	}

	public AnnotationAttributes getAnnotationAttributes(String annotationType) {
		return this.attributeMap.get(annotationType);
	}
}
