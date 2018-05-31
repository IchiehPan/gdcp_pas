package com.gdcp.pas.permission;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.gdcp.pas.permission.readBase.ActionAndMethod;
import com.gdcp.pas.permission.readBase.Function;

public class PermissionRead {
	private static Logger logger = Logger.getLogger(PermissionRead.class);

	private static PermissionRead instance = null;
	private ArrayList<Function> functionList = new ArrayList<>();

	/**
	 * ����ģʽ��һ��˽�еĹ��췽��
	 */
	private PermissionRead() {
		try {
			init();
		} catch (Exception e) {
			logger.error("PermissionRead��ʼ��ʧ�ܣ�", e);
		}
	}

	/**
	 * ��ȡ����ʵ��ķ���
	 */
	public static PermissionRead getInstance() {
		if (instance == null) {
			instance = new PermissionRead();
		}
		return instance;
	}

	/**
	 * ��ȡӵ��ָ����Ȩ�ޡ������н�ɫid ActionName ������ Method ������
	 */
	public static List<Integer> getFunctionId(String ActionName, String Method) {

		if (instance == null) {
			instance = new PermissionRead();
		}

		ArrayList<Integer> fIds = new ArrayList<>();

		int functionId = -1;
		for (Function function : instance.getFunctionList()) {
			functionId = function.getFunctionId(ActionName, Method);
			if (functionId > -1) {
				fIds.add(functionId);
			}
		}

		return fIds;
	}

	/**
	 * ��ʼ������
	 */
	@SuppressWarnings("unchecked")
	public void init() {
		functionList.clear();

		String filePath = this.getClass().getResource("/").getPath();
		String fileName = "permission.xml";

		SAXReader reader = new SAXReader();
		try {
			Document document = reader.read(filePath + fileName);

			Element root = document.getRootElement();// ���ڵ�

			Iterator<Element> fElements = root.elementIterator("function");
			while (fElements.hasNext()) {
				Element fElement = fElements.next();
				int functionId = Integer.parseInt(fElement.attributeValue("functionid"));
				Function funtion = new Function(functionId);

				Iterator<Element> aElements = fElement.elementIterator("action");
				while (aElements.hasNext()) {
					Element aElement = aElements.next();
					String actionName = aElement.attributeValue("actionName");

					Iterator<Element> mElements = aElement.elementIterator("method");
					while (mElements.hasNext()) {
						Element mElement = mElements.next();
						String methodName = mElement.getText();

						ActionAndMethod aAndm = new ActionAndMethod(actionName, methodName);
						funtion.add(aAndm);
					}
				}
				functionList.add(funtion);
			}
		} catch (Exception e) {
			logger.error("��ȡȨ��XMLʧ�ܣ�", e);
		}

		instance = this;
	}

	/**
	 * ��ȡ����Ȩ�޵��б�
	 */
	public ArrayList<Function> getFunctionList() {
		return functionList;
	}
}
