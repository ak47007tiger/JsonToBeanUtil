package convertutil.sample;

import convertutil.FinalConvert;

@FinalConvert
public class FinalEnty1 {
	final int intVal;
	final String val;
	public FinalEnty1(int intVal, String val) {
		super();
		this.intVal = intVal;
		this.val = val;
	}
	public int getIntVal() {
		return intVal;
	}
	public String getVal() {
		return val;
	}
	
}
