package com.gdcp.pas.permission.readBase;

import java.util.ArrayList;

public class Function {

	private int functionId;
	private ArrayList<ActionAndMethod> aAndmList = new ArrayList<>();

	public Function(int functionId) {
		this.functionId = functionId;
	}

	public void add(ActionAndMethod aAndm) {
		aAndmList.add(aAndm);
	}

	public int getFunctionId(String ActionName, String Method) {
		for (ActionAndMethod aAndm : aAndmList) {
			if (aAndm.getActionName().equals(ActionName) && aAndm.getMethodName().equals(Method)) {
				return functionId;
			}
		}

		return -1;
	}

	public int getFunctionId() {
		return functionId;
	}

	public void setFunctionId(int functionId) {
		this.functionId = functionId;
	}

	public ArrayList<ActionAndMethod> getaAndmList() {
		return aAndmList;
	}

}
