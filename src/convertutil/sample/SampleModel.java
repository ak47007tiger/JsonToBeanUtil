package convertutil.sample;

import java.util.List;
import java.util.Map;

import convertutil.FieldRelation;

public class SampleModel {

	@FieldRelation(key = "enty1")
	Enty1 enty1;
	
	@FieldRelation(key = "objList")
	List<Enty1> objList;
	
	@FieldRelation(key = "intVal")
	int intVal;
	
	@FieldRelation(key = "kmVal")
	Map<String, Enty1> kmVal;
	
	public Enty1 getEnty1() {
		return enty1;
	}
	public void setEnty1(Enty1 enty1) {
		this.enty1 = enty1;
	}
	public List<Enty1> getObjList() {
		return objList;
	}
	public void setObjList(List<Enty1> objList) {
		this.objList = objList;
	}
	public int getIntVal() {
		return intVal;
	}
	public void setIntVal(int intVal) {
		this.intVal = intVal;
	}
	public Map<String, Enty1> getKmVal() {
		return kmVal;
	}
	public void setKmVal(Map<String, Enty1> kmVal) {
		this.kmVal = kmVal;
	}
	
}
