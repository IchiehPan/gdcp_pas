package com.gdcp.pas.permission;

/**
 * @author liubr 2015-04-05
 * 提供基本的权限公用方法 
 */

import java.util.List;
import com.gdcp.pas.manage.vo.UserVO;

public class PermissionUtil {

	/**
	 * @see 判断用户是否是系统管理员
	 */
	public static boolean isAdmin(UserVO userVO) {
		boolean flag = false;
		if (userVO != null && "系统管理员".equals(userVO.getRoleName())) {
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