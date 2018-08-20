package com.scheible.gwt.reduced.shared.framework.dto;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Indicator for having such a *Meta class for referencing the Java name.
 * 
 * @author sj
 */
@Target(value = {ElementType.TYPE})
@Retention(value = RetentionPolicy.SOURCE)
public @interface Typeable {
    
}
