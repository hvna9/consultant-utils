package com.github.h9lib.consultantutils.junit;

import java.lang.reflect.Method;

public class FastModelTester {
	/**
	 * It allows to create fast junit tests for getters and setters methods of simple Java models and beans.
	 * Call the methods passing as parameters all the classess you want to cover, for example:
	 * FastModelTester.testAllModels(ClassOne.class);
	 * FastModelTester.testAllModels(ClassOne.class, Class2.class, Class3.class, ...);
	 * 
	 * @param classes all DTO and Models classes must be tested in their getter and setters methods
	 */
	public static void testAllModels(Class... classes) {
		try {
			for(int i=0; i<classes.length; i++) {
				allGetSet(classes[i]);
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}

	private static void allGetSet(Class c) {
		Object inst;
		Object obj = null;
		try {
			inst = c.newInstance();

			for(Method m : c.getMethods()) {
				try {	
					if(m.getName().startsWith("get")) {
						m.invoke(inst);
					}else if(m.getName().startsWith("is")) {
						m.invoke(inst);
					}else {
						m.invoke(inst,obj);
					}
				}catch (Exception ex) {
					ex.printStackTrace();
				}
			}
			inst.toString();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
