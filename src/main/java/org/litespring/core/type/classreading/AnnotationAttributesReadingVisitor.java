package org.litespring.core.type.classreading;

import jdk.internal.org.objectweb.asm.AnnotationVisitor;
import org.litespring.core.annotation.AnnotationAttributes;

import org.springframework.asm.SpringAsmInfo;

import java.util.Map;

final class AnnotationAttributesReadingVisitor extends AnnotationVisitor {

	private final String annotationType;

	private final Map<String, AnnotationAttributes> attributesMap;

	AnnotationAttributes attributes = new AnnotationAttributes();


	public AnnotationAttributesReadingVisitor(
			String annotationType, Map<String, AnnotationAttributes> attributesMap) {
		super(SpringAsmInfo.ASM_VERSION);
		
		this.annotationType = annotationType;
		this.attributesMap = attributesMap;
	}
	
	@Override
	public final void visitEnd(){
		this.attributesMap.put(this.annotationType, this.attributes);
	}
	
	public void visit(String attributeName, Object attributeValue) {
		this.attributes.put(attributeName, attributeValue);
	}
}