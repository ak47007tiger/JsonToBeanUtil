package convertutil;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

public class ConvertUtil {

	public static <T> T convertChangeableModel(JSONObject json, Class<T> clazz)
			throws InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {
		T result = clazz.newInstance();
		Field[] dFields = clazz.getDeclaredFields();
		for (Field field : dFields) {
			boolean accessible = field.isAccessible();
			field.setAccessible(true);

			FieldRelation frAnnotation;
			frAnnotation = field.getAnnotation(FieldRelation.class);
			String fieldKey;
			fieldKey = null != frAnnotation ? frAnnotation.key() : field
					.getName();

			Class<?> fieldType = field.getType();

			if (fieldType.isPrimitive()) {
				field.set(result, getPrimitiveVal(json, fieldKey, fieldType));
			} else if (String.class == fieldType) {
				field.set(result, json.getString(fieldKey));
			} else if (Object.class == fieldType) {
				field.set(result, json.getJSONObject(fieldKey));
			} else if (ArrayList.class == fieldType || List.class == fieldType) {
				JSONArray items = json.getJSONArray(fieldKey);
				Type type = field.getGenericType();
				field.set(result, convertArray(items, type));
			} else if (Map.class == fieldType || HashMap.class == fieldType) {
				Type type = field.getGenericType();
				JSONObject fieldJson = json.getJSONObject(fieldKey);
				field.set(result, convertToMap(fieldJson, type));
			} else {
				JSONObject fieldJson = json.getJSONObject(fieldKey);
				Object val = convert(fieldJson, fieldType);
				field.set(result, val);
			}
			field.setAccessible(accessible);
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	public static <T> T convertFinalModel(JSONObject json, Class<T> clazz)
			throws IllegalArgumentException, InstantiationException,
			IllegalAccessException, InvocationTargetException {

		Class<T> targetClass = clazz;

		Constructor<T> constructor;
		constructor = (Constructor<T>) targetClass.getDeclaredConstructors()[0];

		int parameterCount = constructor.getParameterTypes().length;
		Object[] initargs = new Object[parameterCount];

		Class<?>[] parameterClazzs = constructor.getParameterTypes();

		Type[] parameterTypes = constructor.getGenericParameterTypes();

		Annotation[][] parametersAnnotations;
		parametersAnnotations = constructor.getParameterAnnotations();
		for (int i = 0; i < parameterCount; i++) {
			Annotation[] parameterAnnotations = parametersAnnotations[i];
			String fieldKey = getFieldKey(parameterAnnotations);
			if (null != fieldKey) {
				Class<?> parameterClazz = parameterClazzs[i];
				if (parameterClazz.isPrimitive()) {
					initargs[i] = getPrimitiveVal(json, fieldKey,
							parameterClazz);
				} else if (String.class == parameterClazz) {
					initargs[i] = json.getString(fieldKey);
				} else if (List.class == parameterClazz
						|| ArrayList.class == parameterClazz) {
					JSONArray items = json.getJSONArray(fieldKey);
					Type type = parameterTypes[i];
					initargs[i] = convertArray(items, type);
				} else if (Map.class == parameterClazz
						|| HashMap.class == parameterClazz) {
					Type type = parameterTypes[i];
					JSONObject parameterJson = json.getJSONObject(fieldKey);
					initargs[i] = convertToMap(parameterJson, type);
				} else {
					JSONObject parameterJson = json.getJSONObject(fieldKey);
					initargs[i] = convert(parameterJson, parameterClazz);
				}
			} else {
				throw new IllegalArgumentException(
						"parameter of constructor of final model class must has field relation");
			}
		}

		return constructor.newInstance(initargs);
	}

	private static String getFieldKey(Annotation[] annotations) {
		for (Annotation annotation : annotations) {
			if (annotation instanceof FieldRelation) {
				return ((FieldRelation) annotation).key();
			}
		}
		return null;
	}

	private static Object getPrimitiveVal(JSONObject json, String fieldKey,
			Class<?> fieldType) {
		Object object = null;
		if (byte.class == fieldType) {
			object = json.getInt(fieldKey);
		} else if (char.class == fieldType) {
			object = json.getString(fieldKey).charAt(0);
		} else if (short.class == fieldType) {
			object = json.getInt(fieldKey);
		} else if (int.class == fieldType) {
			object = json.getInt(fieldKey);
		} else if (float.class == fieldType) {
			object = json.getDouble(fieldKey);
		} else if (double.class == fieldType) {
			object = json.getDouble(fieldKey);
		} else if (long.class == fieldType) {
			object = json.getLong(fieldKey);
		} else if (boolean.class == fieldType) {
			object = json.getBoolean(fieldKey);
		}
		return object;
	}

	public static <T> List<T> convertArray(JSONArray items, Class<T> clazz)
			throws InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {
		List<T> list = new ArrayList<T>();
		for (int i = 0; i < items.length(); i++) {
			list.add(convert(items.getJSONObject(i), clazz));
		}
		return list;
	}

	public static Map<Object, Object> convertToMap(JSONObject json) {
		Map<Object, Object> map = new HashMap<>();
		Iterator<String> iterator = json.keys();
		while (iterator.hasNext()) {
			String tempKey = iterator.next();
			map.put(tempKey, json.get(tempKey));
		}
		return map;
	}

	public static Map<Object, Object> convertToMap(JSONObject json, Type type)
			throws InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {
		if (type instanceof ParameterizedType) {
			ParameterizedType ptype = (ParameterizedType) type;
			Class<?> valClazz;
			valClazz = (Class<?>) ptype.getActualTypeArguments()[1];

			if (Integer.class == valClazz) {
				return MapConvertUtil.convertToIntMap(json);
			} else if (Byte.class == valClazz) {
				return MapConvertUtil.convertToByteMap(json);
			} else if (Short.class == valClazz) {
				return MapConvertUtil.convertToShortMap(json);
			} else if (Character.class == valClazz) {
				return MapConvertUtil.convertToCharacterMap(json);
			} else if (Float.class == valClazz) {
				return MapConvertUtil.convertToFloatMap(json);
			} else if (Double.class == valClazz) {
				return MapConvertUtil.convertToDoubleMap(json);
			} else if (Long.class == valClazz) {
				return MapConvertUtil.convertToLongMap(json);
			} else if (Boolean.class == valClazz) {
				return MapConvertUtil.convertToBooleanMap(json);
			} else if (String.class == valClazz) {
				return MapConvertUtil.convertToStringMap(json);
			} else {
				return MapConvertUtil.convertToCustomeMap(json, valClazz);
			}
		} else {
			return convertToMap(json);
		}
	}

	public static List<?> convertArray(JSONArray items, Type type)
			throws InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {
		List<?> list = new ArrayList<>();
		if (type instanceof ParameterizedType) {
			ParameterizedType ptype = (ParameterizedType) type;
			Class<?> genericType;
			genericType = (Class<?>) ptype.getActualTypeArguments()[0];
			list = convertArray(items, genericType);
		} else {
			list = convertArray(items, Object.class);
		}
		return list;
	}

	public static <T> T convert(JSONObject json, Class<T> clazz)
			throws InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {

		if (null != clazz.getAnnotation(FinalConvert.class)) {
			return convertFinalModel(json, clazz);
		} else {
			return convertChangeableModel(json, clazz);
		}
	}

}
