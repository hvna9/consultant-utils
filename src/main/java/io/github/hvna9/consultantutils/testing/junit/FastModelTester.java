package io.github.hvna9.consultantutils.testing.junit;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class FastModelTester {
	/**
	 * It allows to create fast junit tests for getters and setters methods of simple Java models and beans.
	 * Call the methods passing as parameters all the classes you want to cover, for example:
	 * <code>FastModelTester.testAllModels(ClassOne.class);</code>
	 * <code>FastModelTester.testAllModels(ClassOne.class, Class2.class, Class3.class, ...);</code>
	 * 
	 * @param classes all DTO and Models classes must be tested in their getter and setters methods
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 */
	public static final void testAllModels(Class... classes) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException  {
			for(int i=0; i<classes.length; i++) {
				allGetSet(classes[i]);
			}
	}

	private static void allGetSet(Class c) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Object inst;
		Object obj = null;
			inst = c.newInstance();

			for(Method m : c.getMethods()) {
					if(m.getName().startsWith("get")) {
						m.invoke(inst);
					}else if(m.getName().startsWith("is")) {
						m.invoke(inst);
					}else {
						m.invoke(inst,obj);
					}
			}
			inst.toString();
	}
}
