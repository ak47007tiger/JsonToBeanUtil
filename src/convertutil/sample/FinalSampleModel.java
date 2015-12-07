package convertutil.sample;

import java.util.List;
import java.util.Map;

import convertutil.FieldRelation;
import convertutil.FinalConvert;

@FinalConvert
public class FinalSampleModel {

	@FieldRelation(key = "enty1")
	final Enty1 enty1;

	@FieldRelation(key = "objList")
	final List<Enty1> objList;

	@FieldRelation(key = "intVal")
	final int intVal;

	@FieldRelation(key = "kmVal")
	final Map<String, Enty1> kmVal;

	public FinalSampleModel(@FieldRelation(key = "enty1") Enty1 enty1,
			@FieldRelation(key = "objList") List<Enty1> objList,
			@FieldRelation(key = "intVal") int intVal,
			@FieldRelation(key = "kmVal") Map<String, Enty1> kmVal) {
		super();
		this.enty1 = enty1;
		this.objList = objList;
		this.intVal = intVal;
		this.kmVal = kmVal;
	}

	public Enty1 getEnty1() {
		return enty1;
	}

	public List<Enty1> getObjList() {
		return objList;
	}

	public int getIntVal() {
		return intVal;
	}

	public Map<String, Enty1> getKmVal() {
		return kmVal;
	}

}
