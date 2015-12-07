package convertutil;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.json.JSONObject;

public class MapConvertUtil {
	public static Map<Object, Object> convertToCustomeMap(JSONObject json,
			Class<?> valClazz) throws InstantiationException,
			IllegalAccessException, IllegalArgumentException,
			InvocationTargetException {
		Map<Object, Object> map = new HashMap<Object, Object>();
		Iterator<String> iterator = json.keys();
		while (iterator.hasNext()) {
			String tempKeyStr = iterator.next();
			JSONObject tempValJson = json.getJSONObject(tempKeyStr);

			Object tempVal;
			tempVal = ConvertUtil.convert(tempValJson, valClazz);

			map.put(tempKeyStr, tempVal);
		}
		return map;
	}

	public static Map<Object, Object> convertToStringMap(JSONObject json) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		Iterator<String> iterator = json.keys();
		while (iterator.hasNext()) {
			String tempKeyStr = iterator.next();
			map.put(tempKeyStr, json.getString(tempKeyStr));
		}
		return map;
	}

	public static Map<Object, Object> convertToByteMap(JSONObject json) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		Iterator<String> iterator = json.keys();
		while (iterator.hasNext()) {
			String tempKeyStr = iterator.next();
			map.put(tempKeyStr, (byte) json.getInt(tempKeyStr));
		}
		return map;
	}

	public static Map<Object, Object> convertToShortMap(JSONObject json) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		Iterator<String> iterator = json.keys();
		while (iterator.hasNext()) {
			String tempKeyStr = iterator.next();
			map.put(tempKeyStr, (short) json.getInt(tempKeyStr));
		}
		return map;
	}

	public static Map<Object, Object> convertToIntMap(JSONObject json) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		Iterator<String> iterator = json.keys();
		while (iterator.hasNext()) {
			String tempKeyStr = iterator.next();
			map.put(tempKeyStr, json.getInt(tempKeyStr));
		}
		return map;
	}

	public static Map<Object, Object> convertToCharacterMap(JSONObject json) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		Iterator<String> iterator = json.keys();
		while (iterator.hasNext()) {
			String tempKeyStr = iterator.next();
			map.put(tempKeyStr, (char) json.getInt(tempKeyStr));
		}
		return map;
	}

	public static Map<Object, Object> convertToFloatMap(JSONObject json) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		Iterator<String> iterator = json.keys();
		while (iterator.hasNext()) {
			String tempKeyStr = iterator.next();
			map.put(tempKeyStr, (float) json.getDouble(tempKeyStr));
		}
		return map;
	}

	public static Map<Object, Object> convertToDoubleMap(JSONObject json) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		Iterator<String> iterator = json.keys();
		while (iterator.hasNext()) {
			String tempKeyStr = iterator.next();
			map.put(tempKeyStr, json.getDouble(tempKeyStr));
		}
		return map;
	}

	public static Map<Object, Object> convertToLongMap(JSONObject json) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		Iterator<String> iterator = json.keys();
		while (iterator.hasNext()) {
			String tempKeyStr = iterator.next();
			map.put(tempKeyStr, json.getLong(tempKeyStr));
		}
		return map;
	}

	public static Map<Object, Object> convertToBooleanMap(JSONObject json) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		Iterator<String> iterator = json.keys();
		while (iterator.hasNext()) {
			String tempKeyStr = iterator.next();
			map.put(tempKeyStr, json.getBoolean(tempKeyStr));
		}
		return map;
	}
}
