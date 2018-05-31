package com.gdcp.pas.score.dao;

import java.util.List;

import com.gdcp.pas.score.vo.AverageScoreVO;

/**
 * @author ��ΰ��
 *
 */
public interface AverageScoreDAO {

	/**
	 * ��ȡ���б����˶�����������ճɼ� ����������
	 */
	public List<AverageScoreVO> getAllWeightScore() throws Exception;

	/**
	 * ��ȡĳ�����˶�������ճɼ�
	 * 
	 * @param objertId
	 * @return һ��AverageScoreVO����
	 */
	public AverageScoreVO getWeightScoreByObjectId(String objertId) throws Exception;

	/**
	 * ��ȡĳ����ĳ�����˱��˵� ���ж���� ���ճɼ��� ArrayList
	 * 
	 * @param scoerBuleId
	 *            ָ�����˱�
	 * @return
	 */
	public List<AverageScoreVO> getWeightScoreByScoreBuleId(int scoerRuleId) throws Exception;

	/**
	 * ��ȡĳ����ĳ�����˱��˵� ĳ�����ŵ� ���ж���� ���ճɼ��� ArrayList
	 * 
	 * @param scoerBuleId
	 *            ָ�����˱�
	 * @param deptId
	 *            ָ������
	 * @return
	 */
	public List<AverageScoreVO> getWeightScoreByScoreBuleIdAndDeptId(int scoerBuleId, int deptId) throws Exception;

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
			int scoreRuleId) throws Exception;

	/**
	 * ��ȡ���в����쵼�Ŀ��˽��(����ƽ���ͼ�Ȩ��ɼ�)
	 * 
	 * @return
	 */
	public List<AverageScoreVO> getAllMiddleWeightScore() throws Exception;

	// /**
	// * �ж�ĳ������������Ƿ��Ѿ�����
	// * @param objectId ָ������
	// * @return �Ƿ�
	// */
	// public boolean isFinishByObjectIdAndObjectType(String objectId, int
	// objectType) throws Exception ;

	/**
	 * �жϷ�����¼�Ƿ��Ѿ�����
	 * 
	 * @param averageScoreVo
	 * @return �Ƿ�
	 */
	public boolean isExistScore(AverageScoreVO averageScoreVo) throws Exception;

	/**
	 * ����һ���ɼ�
	 * 
	 * @param averageScoreVo
	 * @return
	 */
	public int insertScore(AverageScoreVO averageScoreVo) throws Exception;

	/**
	 * �޸�һ���ɼ�
	 * 
	 * @param averageScoreVo
	 * @return
	 */
	public int updateScore(AverageScoreVO averageScoreVo) throws Exception;

	/**
	 * �����ѧ�����ܷ�
	 */
	public void TeachingDepartmentAggregateScore() throws Exception;

	/**
	 * ����ǽ�ѧ�����ܷ�
	 */
	public void NotTeachingDepartmentAggregateScore() throws Exception;

	public void PrincipalAggregateScore() throws Exception;

	public void NOtPrincipalAggregateScore() throws Exception;

	public void otherAggregateScore() throws Exception;

}
