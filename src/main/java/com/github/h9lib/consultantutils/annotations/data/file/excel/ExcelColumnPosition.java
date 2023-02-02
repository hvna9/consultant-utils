package com.github.h9lib.consultantutils.annotations.data.file.excel;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 * Put this annotation on a model property (previously annotated with @ToExcel), to specify the position of the property as
 * Excel column.
 * The columns indexes start form 0.
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ExcelColumnPosition {
	int position() default 0;
}
