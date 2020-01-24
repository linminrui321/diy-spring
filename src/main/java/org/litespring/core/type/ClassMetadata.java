package org.litespring.core.type;

public interface ClassMetadata {

    boolean isAbstract();

    boolean isFinal();

    boolean hasSuperClass();

    String getSuperName();

}
