package io.github.hvna9.consultantutils.annotations.data.file.excel;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * Use this annotation on a model to indicate that the annotated model can be part of an excel row.
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ToExcel {
	boolean exportToExcel() default true;
}
