package com.gdcp.pas.permission.readBase;

public class ActionAndMethod {

	private String actionName;
	private String methodName;

	public ActionAndMethod(String actionName, String methodName) {
		this.actionName = actionName;
		this.methodName = methodName;
	}

	public String getActionName() {
		return actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

}
