package com.gdcp.pas.score.bo;

import java.util.List;

import com.gdcp.pas.score.dao.AverageScoreDAO;
import com.gdcp.pas.score.dao.impl.AverageScoreDAOImpl;
import com.gdcp.pas.score.vo.AverageScoreVO;

public class AverageScoreBO {
	AverageScoreDAO asd = new AverageScoreDAOImpl();

	/**
	 * 获取所有被考核对象的所有最终成绩 按部门排序
	 */
	public List<AverageScoreVO> getAllWeightScore() throws Exception {
		return asd.getAllWeightScore();
	}

	/**
	 * 获取某个考核对象的最终成绩
	 * 
	 * @param objertId
	 * @return 一个AverageScoreVO（）
	 */
	public AverageScoreVO getWeightScoreByObjectId(String objertId) throws Exception {
		return asd.getWeightScoreByObjectId(objertId);
	}

	/**
	 * 获取某个被某个考核表考核的 所有对象的 最终成绩的 ArrayList
	 * 
	 * @param scoerBuleId
	 *            指定考核表
	 * @return
	 */
	public List<AverageScoreVO> getWeightScoreByScoreBuleId(int scoerRuleId) throws Exception {
		return asd.getWeightScoreByScoreBuleId(scoerRuleId);
	}

	/**
	 * 获取某个被某个考核表考核的 某个部门的 所有对象的 最终成绩的 ArrayList
	 * 
	 * @param scoerBuleId
	 *            指定考核表
	 * @param deptId
	 *            指定部门
	 * @return
	 */
	public List<AverageScoreVO> getWeightScoreByScoreBuleIdAndDeptId(int scoerBuleId, int deptId) throws Exception {
		return asd.getWeightScoreByScoreBuleIdAndDeptId(scoerBuleId, deptId);
	}

	/**
	 * 获取 某个考核对象 作为 某种考核对象类型 而被 某种规则 时，被哪些主体类型评价
	 * 
	 * @param objectId
	 *            指定考核对象
	 * @param objectType
	 *            指定考核对象类型
	 * @param scoreRuleId
	 *            指定规则
	 * @return
	 */
	public List<AverageScoreVO> getAllAverageScoreByObjectIdAndObjectTypeAndScoreRuleID(String objectId, int objectType,
			int scoreRuleId) throws Exception {
		return asd.getAllAverageScoreByObjectIdAndObjectTypeAndScoreRuleID(objectId, objectType, scoreRuleId);
	}

	/**
	 * 获取所有部门领导的考核结果(包括平均和加权后成绩)
	 * 
	 * @return
	 */
	public List<AverageScoreVO> getAllMiddleWeightScore() throws Exception {
		return asd.getAllMiddleWeightScore();
	}

	// /**
	// * 判断某个对象的评分是否已经结束
	// * @param objectId 指定对象
	// * @return 是否
	// */
	// public boolean isFinishByObjectIdAndObjectType(String objectId, int
	// objectType) throws Exception {
	// return asdi.isFinishByObjectIdAndObjectType(objectId, objectType);
	// }

	/**
	 * 判断分数记录是否已经存在
	 * 
	 * @param averageScoreVo
	 * @return 是否
	 */
	public boolean isExistScore(AverageScoreVO averageScoreVo) throws Exception {
		return asd.isExistScore(averageScoreVo);
	}

	/**
	 * 插入一条成绩
	 * 
	 * @param averageScoreVo
	 * @return
	 */
	public int insertScore(AverageScoreVO averageScoreVo) throws Exception {
		return asd.insertScore(averageScoreVo);
	}

	/**
	 * 修改一条成绩
	 * 
	 * @param averageScoreVo
	 * @return
	 */
	public int updateScore(AverageScoreVO averageScoreVo) throws Exception {
		return asd.updateScore(averageScoreVo);
	}

	/**
	 * 计算教学部门总分
	 */
	public void TeachingDepartmentAggregateScore() throws Exception {
		asd.TeachingDepartmentAggregateScore();
	}

	/**
	 * 计算非教学部门总分
	 */
	public void NotTeachingDepartmentAggregateScore() throws Exception {
		asd.NotTeachingDepartmentAggregateScore();
	}

	/**
	 * 计算内设机构领导的个人总分
	 */
	public void LeaderAggregateScore() throws Exception {
		// 计算内设机构正职领导的个人总分
		asd.PrincipalAggregateScore();
		// 计算内设机构正职领导的个人总分
		asd.NOtPrincipalAggregateScore();
	}

	/**
	 * 计算部门其他人员的个人总分
	 */
	public void otherAggregateScore() throws Exception {
		asd.otherAggregateScore();
	}
}
