package com.gdcp.pas.permission;

/**
 * @author liubr 2015-04-05
 * �ṩ������Ȩ�޹��÷��� 
 */

import java.util.List;
import com.gdcp.pas.manage.vo.UserVO;

public class PermissionUtil {

	/**
	 * @see �ж��û��Ƿ���ϵͳ����Ա
	 */
	public static boolean isAdmin(UserVO userVO) {
		boolean flag = false;
		if (userVO != null && "ϵͳ����Ա".equals(userVO.getRoleName())) {
			flag = true;
		}
		return flag;

	}

	public static boolean isPermission(UserVO vo, String rightId) {

		List<String> rightList = vo.getRightList();
		if (!isAdmin(vo) && !rightList.contains(rightId)) {
			return false;
		}
		return true;

	}

	public static boolean isPermissionLike(UserVO vo, String likeRightId) {

		List<String> rightList = vo.getRightList();
		if (isAdmin(vo)) {
			return true;
		}

		for (int i = 0; i < rightList.size(); i++) {
			if (rightList.get(i).startsWith(likeRightId)) {
				return true;
			}
		}

		return false;

	}
}