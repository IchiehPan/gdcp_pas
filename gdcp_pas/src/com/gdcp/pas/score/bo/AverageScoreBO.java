package com.gdcp.pas.score.bo;

import java.util.List;

import com.gdcp.pas.score.dao.AverageScoreDAO;
import com.gdcp.pas.score.dao.impl.AverageScoreDAOImpl;
import com.gdcp.pas.score.vo.AverageScoreVO;

public class AverageScoreBO {
	AverageScoreDAO asd = new AverageScoreDAOImpl();

	/**
	 * ��ȡ���б����˶�����������ճɼ� ����������
	 */
	public List<AverageScoreVO> getAllWeightScore() throws Exception {
		return asd.getAllWeightScore();
	}

	/**
	 * ��ȡĳ�����˶�������ճɼ�
	 * 
	 * @param objertId
	 * @return һ��AverageScoreVO����
	 */
	public AverageScoreVO getWeightScoreByObjectId(String objertId) throws Exception {
		return asd.getWeightScoreByObjectId(objertId);
	}

	/**
	 * ��ȡĳ����ĳ�����˱��˵� ���ж���� ���ճɼ��� ArrayList
	 * 
	 * @param scoerBuleId
	 *            ָ�����˱�
	 * @return
	 */
	public List<AverageScoreVO> getWeightScoreByScoreBuleId(int scoerRuleId) throws Exception {
		return asd.getWeightScoreByScoreBuleId(scoerRuleId);
	}

	/**
	 * ��ȡĳ����ĳ�����˱��˵� ĳ�����ŵ� ���ж���� ���ճɼ��� ArrayList
	 * 
	 * @param scoerBuleId
	 *            ָ�����˱�
	 * @param deptId
	 *            ָ������
	 * @return
	 */
	public List<AverageScoreVO> getWeightScoreByScoreBuleIdAndDeptId(int scoerBuleId, int deptId) throws Exception {
		return asd.getWeightScoreByScoreBuleIdAndDeptId(scoerBuleId, deptId);
	}

	/**
	 * ��ȡ ĳ�����˶��� ��Ϊ ĳ�ֿ��˶������� ���� ĳ�ֹ��� ʱ������Щ������������
	 * 
	 * @param objectId
	 *            ָ�����˶���
	 * @param objectType
	 *            ָ�����˶�������
	 * @param scoreRuleId
	 *            ָ������
	 * @return
	 */
	public List<AverageScoreVO> getAllAverageScoreByObjectIdAndObjectTypeAndScoreRuleID(String objectId, int objectType,
			int scoreRuleId) throws Exception {
		return asd.getAllAverageScoreByObjectIdAndObjectTypeAndScoreRuleID(objectId, objectType, scoreRuleId);
	}

	/**
	 * ��ȡ���в����쵼�Ŀ��˽��(����ƽ���ͼ�Ȩ��ɼ�)
	 * 
	 * @return
	 */
	public List<AverageScoreVO> getAllMiddleWeightScore() throws Exception {
		return asd.getAllMiddleWeightScore();
	}

	// /**
	// * �ж�ĳ������������Ƿ��Ѿ�����
	// * @param objectId ָ������
	// * @return �Ƿ�
	// */
	// public boolean isFinishByObjectIdAndObjectType(String objectId, int
	// objectType) throws Exception {
	// return asdi.isFinishByObjectIdAndObjectType(objectId, objectType);
	// }

	/**
	 * �жϷ�����¼�Ƿ��Ѿ�����
	 * 
	 * @param averageScoreVo
	 * @return �Ƿ�
	 */
	public boolean isExistScore(AverageScoreVO averageScoreVo) throws Exception {
		return asd.isExistScore(averageScoreVo);
	}

	/**
	 * ����һ���ɼ�
	 * 
	 * @param averageScoreVo
	 * @return
	 */
	public int insertScore(AverageScoreVO averageScoreVo) throws Exception {
		return asd.insertScore(averageScoreVo);
	}

	/**
	 * �޸�һ���ɼ�
	 * 
	 * @param averageScoreVo
	 * @return
	 */
	public int updateScore(AverageScoreVO averageScoreVo) throws Exception {
		return asd.updateScore(averageScoreVo);
	}

	/**
	 * �����ѧ�����ܷ�
	 */
	public void TeachingDepartmentAggregateScore() throws Exception {
		asd.TeachingDepartmentAggregateScore();
	}

	/**
	 * ����ǽ�ѧ�����ܷ�
	 */
	public void NotTeachingDepartmentAggregateScore() throws Exception {
		asd.NotTeachingDepartmentAggregateScore();
	}

	/**
	 * ������������쵼�ĸ����ܷ�
	 */
	public void LeaderAggregateScore() throws Exception {
		// �������������ְ�쵼�ĸ����ܷ�
		asd.PrincipalAggregateScore();
		// �������������ְ�쵼�ĸ����ܷ�
		asd.NOtPrincipalAggregateScore();
	}

	/**
	 * ���㲿��������Ա�ĸ����ܷ�
	 */
	public void otherAggregateScore() throws Exception {
		asd.otherAggregateScore();
	}
}
