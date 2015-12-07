package convertutil.test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import convertutil.sample.FinalEnty1;

public class TestReflectFinal {

	public static void testSetFinal() throws InstantiationException,
			IllegalAccessException, NoSuchFieldException, SecurityException {

		Class<?> targetClass = FinalEnty1.class;

		Object object = targetClass.newInstance();

		Field field = targetClass.getDeclaredField("strVal");

		boolean accessible = field.isAccessible();
		if (!accessible) {
			field.setAccessible(true);
		}

		field.set(object, "strVal");

		if (!accessible) {
			field.setAccessible(accessible);
		}

	}

	public static void testInstanceFianl() throws InstantiationException,
			IllegalAccessException, IllegalArgumentException,
			InvocationTargetException {
		Class<?> targetClass = FinalEnty1.class;

		Constructor<?> constructor = targetClass.getDeclaredConstructors()[0];

		int parameterCount = constructor.getParameterTypes().length;
		Object[] initargs = new Object[parameterCount];
		initargs[0] = 1;
		initargs[1] = "strVal";

		FinalEnty1 obj = (FinalEnty1) constructor.newInstance(initargs);

		System.out.println(obj.getVal());
	}

	@SuppressWarnings("rawtypes")
	class TestModel{
		Map map;

		public TestModel(Map map) {
			super();
			this.map = map;
		}
		
	}
	
	
	
	public static void main(String[] args) throws InstantiationException,
			IllegalAccessException, IllegalArgumentException,
			InvocationTargetException {
		Class<?> targetClass = TestModel.class;

		Constructor<?> constructor = targetClass.getDeclaredConstructors()[0];

		Type[] pTypes = constructor.getGenericParameterTypes();
		for(Type ptype : pTypes){
			System.out.println("--");
			System.out.println(ptype);
			System.out.println(ptype instanceof Class);
			if(ptype instanceof ParameterizedType){
				ParameterizedType pt = (ParameterizedType) ptype;
				for(Type t : pt.getActualTypeArguments()){
					System.out.println(t);
				}
			}
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("a", "1");
		map.put("b", "2");
		map.put("c", "3");
		JSONObject jmap = new JSONObject(map);
		System.out.println(jmap);
		
		System.out.println(Integer.class.isPrimitive());
		
	}
}
