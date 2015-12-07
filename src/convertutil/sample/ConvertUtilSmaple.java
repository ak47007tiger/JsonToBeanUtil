package convertutil.sample;

import org.json.JSONArray;
import org.json.JSONObject;

import convertutil.ConvertUtil;

public class ConvertUtilSmaple {
	public static void main(String[] args) throws Exception {
		JSONObject jEnty1 = new JSONObject();
		jEnty1.put("intVal", 1);
		jEnty1.put("strVal", "str1");

		JSONObject jEnty2 = new JSONObject();
		jEnty2.put("intVal", 2);
		jEnty2.put("strVal", "str2");

		JSONArray objList = new JSONArray();
		objList.put(jEnty1);
		objList.put(jEnty2);

		JSONObject jkmVal = new JSONObject();
		JSONObject jEnty3 = new JSONObject();
		jEnty3.put("intVal", 3);
		jEnty3.put("strVal", "str3");
		jkmVal.put("object", jEnty3);

		JSONObject jsmp = new JSONObject();
		jsmp.put("enty1", jEnty1);
		jsmp.put("objList", objList);
		jsmp.put("intVal", 3);
		jsmp.put("kmVal", jkmVal);

		// Enty1 enty1 = convert(jEnty1, Enty1.class);

		SampleModel smp = ConvertUtil.convert(jsmp, SampleModel.class);
		System.out.println(smp.getIntVal());
		System.out.println(smp.getEnty1().getStrVal());
		System.out.println(smp.getObjList().get(1).getStrVal());
		System.out.println(((Enty1) smp.getKmVal().get("object")).getStrVal());
		
		System.out.println("--");
		
		FinalSampleModel fsmp = ConvertUtil.convert(jsmp, FinalSampleModel.class);
		System.out.println(fsmp.getIntVal());
		System.out.println(fsmp.getEnty1().getStrVal());
		System.out.println(fsmp.getObjList().get(1).getStrVal());
		System.out.print(((Enty1) fsmp.getKmVal().get("object")).getStrVal());
	}
}
