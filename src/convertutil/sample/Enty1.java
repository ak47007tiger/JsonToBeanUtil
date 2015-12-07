package convertutil.sample;

import convertutil.FieldRelation;

public class Enty1 {

	@FieldRelation(key = "intVal")
	int intVal;
	@FieldRelation(key = "strVal")
	String strVal;
	public int getIntVal() {
		return intVal;
	}
	public void setIntVal(int intVal) {
		this.intVal = intVal;
	}
	public String getStrVal() {
		return strVal;
	}
	public void setStrVal(String strVal) {
		this.strVal = strVal;
	}
	
}
