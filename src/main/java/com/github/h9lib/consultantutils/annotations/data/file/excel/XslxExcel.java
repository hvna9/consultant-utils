package com.github.h9lib.consultantutils.annotations.data.file.excel;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface XslxExcel {
	//true if the excel version is 2007 or higher (.xslt extension) - false if the excel version is 2003
	boolean isXslxExcel() default true;
}
