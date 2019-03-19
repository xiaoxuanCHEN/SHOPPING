package xm.chen.dao;

import java.util.Collection;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JSONUtil {
	/**
	 * 把对象转换成json格式的字符串
	 * 
	 * @param o
	 * @return
	 */
	public static String toJsonString(Object o) {
		if (o instanceof Collection) {
			StringBuffer buf = new StringBuffer("");
			Object[] objs = JSONArray.fromObject(o).toArray();
			for (int i = 0; i < objs.length; i++) {
				if (i != 0)
					buf.append(",");
				buf.append(JSONObject.fromObject(objs[i]).toString());
			}
			return "[" + buf.toString() + "]";
		}
		return JSONObject.fromObject(o).toString();
	}

	public static String toJsonString1(Object o) {
		if (o instanceof Collection) {
			StringBuffer buf = new StringBuffer("");
			Object[] objs = JSONArray.fromObject(o).toArray();
			for (int i = 0; i < objs.length; i++) {
				if (i != 0)
					buf.append(",");
				buf.append(JSONObject.fromObject(objs[i]).toString());
			}
			return "[" + buf.toString();
		}
		return JSONObject.fromObject(o).toString();
	}
}
