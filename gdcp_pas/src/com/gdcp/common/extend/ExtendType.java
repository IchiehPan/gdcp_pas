package com.gdcp.common.extend;

import java.util.ArrayList;

public class ExtendType {

	private String typeName;
	private ArrayList<ExtendCode> extendCodes = new ArrayList<>();

	// List²Ù×÷£º
	public void addExtendCode(ExtendCode extendCode) {
		extendCodes.add(extendCode);
	}

	public ExtendCode getExtendCode(String numKey) {
		ExtendCode extCode = new ExtendCode();
		for (int i = 0; i < extendCodes.size(); i++) {
			if (extendCodes.get(i).getNumKey().equals(numKey)) {
				extCode.setType(extendCodes.get(i).getType());
				extCode.setNumKey(extendCodes.get(i).getNumKey());
				extCode.setValue(extendCodes.get(i).getValue());
				extCode.setSort(extendCodes.get(i).getSort());
			}
		}
		return extCode;
	}

	// get¡¢set
	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public ArrayList<ExtendCode> getExtendCodes() {
		return extendCodes;
	}

	public void setExtendCodes(ArrayList<ExtendCode> extendCodes) {
		this.extendCodes = extendCodes;
	}

}
