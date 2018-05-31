package com.gdcp.pas.score.dao;

import java.util.List;

import com.gdcp.common.Page;
import com.gdcp.pas.manage.vo.UserVO;
import com.gdcp.pas.score.vo.ScoreResultVO;

/**
 * @author ��ΰ�� 0329-00:47
 * @version 0402-14:07
 * @see �����ֽ���������������ӿ�
 */
public interface ScoreResultDAO {

	/**
	 * @see ��ȡָ�����۽��
	 * @param scoreResultId
	 *            ���۽�������id
	 * @return ָ�����۽��Vo
	 */
	public ScoreResultVO getScoreResultByScoreResultId(int scoreResultId) throws Exception;

	/**
	 * ��ȡĳ��ָ����ָ���������塢���򣩵����۽��
	 * 
	 * @param objectId
	 * @param scorerId
	 * @param scoreReuleId
	 */
	public ScoreResultVO getScoreResultByObjectIdAndScorerId(String objectId, String scorerId, int scoreReuleId)
			throws Exception;

	/**
	 * @see ��ҳ�� ��ȡĳ�û�����Ӧ�����������۽����
	 * @param ScorerId
	 *            �������壨�����ˡ��̹���Id
	 * @return �������� ���е� Ӧ������ ���������۽������
	 */
	public List<ScoreResultVO> getScoreResultByScorerIdPage(String teacherId, Page page) throws Exception;

	/**
	 * @see ��ҳ�� ��ȡĳ�û� ĳ��״̬ �����۽����
	 * @param ScorerId
	 *            �������壨�����ˡ��̹���Id
	 * @param status
	 *            ״̬��0:δ���ۡ�1�����棬δ�ύ��2:���ύ
	 * @return һ������Ҫ��״̬�ģ��������۽������
	 */
	public List<ScoreResultVO> getScoreResultByScorerIdPage(String teacherId, int status, Page page) throws Exception;

	/**
	 * @see ��ȡĳ�û�����Ӧ�����������۽����
	 * @param ScorerId
	 *            �������壨�����ˡ��̹���Id
	 * @return �������� ���е� Ӧ������ ���������۽������
	 */
	public List<ScoreResultVO> getScoreResultByScorerId(String teacherId) throws Exception;

	/**
	 * @see ��ȡĳ�û�����Ӧ���������۵����۽����
	 * @param ScorerId
	 *            ���۶��󣨱����ˡ��̹���Id
	 * @return �������� ���е� Ӧ������ ���������۽������
	 */
	public List<ScoreResultVO> getScoreResultByObjectId(String teacherId) throws Exception;

	/**
	 * @see ��ȡĳ�û� ĳ��״̬ �����۽����
	 * @param ScorerId
	 *            �������壨�����ˡ��̹���Id
	 * @param status
	 *            ״̬��0:δ���ۡ�1�����棬δ�ύ��2:���ύ
	 * @return һ������Ҫ��״̬�ģ��������۽������
	 */
	public List<ScoreResultVO> getScoreResultByScorerId(String teacherId, int status) throws Exception;

	/**
	 * @see ��ȡĳ�û� ĳ��״̬ Ӧ���������۵����۽����
	 * @param ScorerId
	 *            ���۶��󣨱����ˡ��̹���Id
	 * @param status
	 *            ״̬��0:δ���ۡ�1�����棬δ�ύ��2:���ύ
	 * @return һ������Ҫ��״̬�ģ��������۽������
	 */
	public List<ScoreResultVO> getScoreResultByObjectId(String teacherId, int status) throws Exception;

	/**
	 * @see ��ȡĳ���� ĳ��״̬ Ӧ���������۵����۽����
	 * @param ScorerId
	 *            ���۶��󣨱����ˡ��̹���Id
	 * @param scoreRuleId
	 *            ��������Id
	 * @param status
	 *            ״̬��0:δ���ۡ�1�����棬δ�ύ��2:���ύ
	 * @return һ������Ҫ��״̬�ģ��������۽������
	 */
	public List<ScoreResultVO> getScoreResultByObjectIdAndScoreRuleId(String teacherId, int scoreRuleId, int status)
			throws Exception;

	/**
	 * ��ȡ���п��˶�������飨����ObjectId���ظ���
	 * 
	 * @return ���п��˶��������
	 */
	public List<ScoreResultVO> getAllObject() throws Exception;

	/**
	 * �����ݿ�ġ����ֽ�������в���һ�������ֹ�ϵ����״̬Ϊ��0���ġ����ֽ������
	 * 
	 * @param scoreResultVo
	 *            ״̬Ϊ��0���ġ����ֽ����
	 * @return ��Ӱ������
	 */
	public int insertScoreResult(ScoreResultVO scoreResultVo) throws Exception;

	/**
	 * �����ݿ�ġ����ֽ�������в���100�������ֹ�ϵ����״̬Ϊ��0���ġ����ֽ������
	 * 
	 * @param scoreResultVo
	 *            ״̬Ϊ��0���ġ����ֽ����
	 * @return ��Ӱ������
	 */
	public int batchInsertScoreResult(List<ScoreResultVO> scoreResultVos) throws Exception;

	/**
	 * �޸�ĳ��ָ���ģ�ָ��id�������۽��
	 * 
	 * @param scoreResultVo
	 *            �ں�id��Ҫ�޸ĵ���Ϣ
	 * @return ��Ӱ������
	 */
	public int updateScoreResult(ScoreResultVO scoreResultVo) throws Exception;

	/**
	 * @see �ı�ĳ�����۽����״̬
	 * @param scoreResultId
	 *            ָ�����۽����Id
	 * @param status
	 *            Ҫ�ı������״̬��0:δ���ۡ�1�����棬δ�ύ��2:���ύ
	 * @param scoreResult
	 *            �ܷ�
	 * @param commitDate
	 *            �ύ���ڣ�YYYY��MM��DD�գ�
	 * @return ��Ӱ������
	 */
	public int changeScoreResultStatusByID(int scoreResultId, int status, double scoreResult, String commitDate)
			throws Exception;

	/**
	 * ��ȡĳ�� ���˶��� �� ָ���������� �� ָ������ �����ض�״̬�ؼ�¼��
	 * 
	 * @param ObjectId
	 *            ָ�����˶���
	 * @param scorerIdsStr
	 *            �������������������������ID����ʽ����' scorerId',' scorerId',' scorerId'.....����
	 * @param scoreRuleId
	 *            ָ������
	 * @param status
	 *            ָ��״̬ ��ע�⣺3�����ȡȫ������״̬��
	 * @return ��¼��
	 */
	public int getCountByObjectIdAndScorerIdsAndScoreRuleId(String objectId, String scorerIdsStr, int scoreRuleId,
			int status) throws Exception;

	/**
	 * ��ȡĳ�� ���˶��� �� ָ������ �����ض�״̬�ؼ�¼��
	 * 
	 * @param ObjectId
	 *            ָ�����˶���
	 * @param scoreRuleId
	 *            ָ������
	 * @param status
	 *            ָ��״̬ ��ע�⣺3�����ȡȫ������״̬��
	 * @return ��¼��
	 */
	public int getCountByObjectIdAndScoreRuleId(String objectId, int scoreRuleId, int status) throws Exception;

	// --------------------------------���£��������ר��-------------------------------//
	/**
	 * ��ȡĳ���˶��� �� ĳ���������塢�� ĳ�ֿ��˹��� ���ۣ������� �ύ ״̬������ �� ƽ�����
	 * 
	 * @param teacherId
	 *            ����id
	 * @param scoreRuleId
	 *            ����id
	 * @param scorerIdsStr
	 *            �������������������������ID����ʽ����' scorerId',' scorerId',' scorerId'.....����
	 * @return
	 */
	public double getAverageScoreByObjectIdAndScoreRuleIdAndScorerIds(String objectId, int scoreRuleId,
			String scorerIdsStr) throws Exception;

	public double getAverageScoreByObjectIdAndScoreRuleIdAndScorerIdsX(String objectId, int scoreRuleId,
			String scorerIdsStr, int scoreType) throws Exception;

	/**
	 * ��ȡĳ���˶��� �� ĳ���������塢�� ĳ�ֿ��˹��� ���ۣ������� �ύ ״̬����������
	 * 
	 * @param teacherId
	 *            ����id
	 * @param scoreRuleId
	 *            ����id
	 * @param scorerIdsStr
	 *            �������������������������ID����ʽ����' scorerId',' scorerId',' scorerId'.....����
	 * @return
	 */
	public List<ScoreResultVO> getScoreResultByObjectIdAndScoreRuleIdAndScorerIds(String objectId, int scoreRuleId,
			String scorerIdsStr) throws Exception;

	/**
	 * @param objectId
	 *            String �����˶����id
	 * @param objectType
	 *            String �����˶���Ŀ�������
	 * @param scorerType
	 *            String �����������������
	 * @return list �ƶ����˶���Ŀ������͵���������
	 * @throws Exception
	 */
	public List<UserVO> getScorerByObjectType(String objectId, String objectType, String scorerType) throws Exception;

	/**
	 * @param objectId
	 *            String �����˶����id
	 * @param objectType
	 *            int �����˶���Ŀ�������
	 * @param scorerId
	 *            String �����������������
	 * @return tempScore ��ѯ�ķ���
	 * @throws Exception
	 */
	public double getScore(String objectId, int objectType, String scorerId) throws Exception;

}
