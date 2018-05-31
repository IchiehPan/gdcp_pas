package com.gdcp.pas.manage.action;

import java.io.File;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

public class ReadExcelAction {
	private static Logger logger = Logger.getLogger(ReadExcelAction.class);
	private File excelFile;

	public String insertRelation() {

		try {
			ServletActionContext.getServletContext().getRealPath("");
			return "success";
		} catch (Exception e) {
			logger.error("增加关系出错", e);
		}
		return null;
	}

	public File getExcelFile() {
		return excelFile;
	}

	public void setExcelFile(File excelFile) {
		this.excelFile = excelFile;
	}

}
