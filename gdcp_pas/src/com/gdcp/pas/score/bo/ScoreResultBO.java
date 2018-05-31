package com.gdcp.pas.score.bo;

import java.util.List;

import com.gdcp.common.Page;
import com.gdcp.pas.manage.vo.UserVO;
import com.gdcp.pas.score.dao.ScoreResultDAO;
import com.gdcp.pas.score.dao.impl.ScoreResultDAOImpl;
import com.gdcp.pas.score.vo.ScoreResultVO;

/**
 * @author ��ΰ��
 * @version 0329=0730
 * @see �����ֽ��������������BO
 */
public class ScoreResultBO {
	ScoreResultDAO srd = new ScoreResultDAOImpl();

	/**
	 * @see ��ȡָ�����۽��
	 * @param scoreResultId
	 *            ���۽�������id
	 * @return ָ�����۽��Vo
	 */
	public ScoreResultVO getScoreResultByScoreResultId(int scoreResultId) throws Exception {
		return srd.getScoreResultByScoreResultId(scoreResultId);
	}

	/**
	 * ��ȡĳ��ָ����ָ���������塢���򣩵����۽��
	 * 
	 * @param objectId
	 * @param scorerId
	 * @param scoreReuleId
	 */
	public ScoreResultVO getScoreResultByObjectIdAndScorerId(String objectId, String scorerId, int scoreReuleId)
			throws Exception {
		return srd.getScoreResultByObjectIdAndScorerId(objectId, scorerId, scoreReuleId);
		//
	}

	/**
	 * @see ��ҳ�� ��ȡĳ�û�����Ӧ�����������۽����
	 * @param ScorerId
	 *            �������壨�����ˡ��̹���Id
	 * @return �������� ���е� Ӧ������ ���������۽������
	 */
	public List<ScoreResultVO> getScoreResultByScorerIdPage(String teacherId, Page page) throws Exception {
		return srd.getScoreResultByScorerIdPage(teacherId, page);
	}

	/**
	 * @see ��ҳ�� ��ȡĳ�û� ĳ��״̬ �����۽����
	 * @param ScorerId
	 *            �������壨�����ˡ��̹���Id
	 * @param status
	 *            ״̬��0:δ���ۡ�1�����棬δ�ύ��2:���ύ
	 * @return һ������Ҫ��״̬�ģ��������۽������
	 */
	public List<ScoreResultVO> getScoreResultByScorerIdPage(String teacherId, int status, Page page) throws Exception {
		return srd.getScoreResultByScorerIdPage(teacherId, status, page);
	}

	/**
	 * @see ��ȡĳ�û�����Ӧ�����������۽����
	 * @param ScorerId
	 *            �������壨�����ˡ��̹���Id
	 * @return �������� ���е� Ӧ������ ���������۽������
	 */
	public List<ScoreResultVO> getScoreResultByScorerId(String scorerId) throws Exception {
		return srd.getScoreResultByScorerId(scorerId);
	}

	/**
	 * @see ��ȡĳ�û�����Ӧ���������۵����۽����
	 * @param ScorerId
	 *            ���۶��󣨱����ˡ��̹���Id
	 * @return �������� ���е� Ӧ������ ���������۽������
	 */
	public List<ScoreResultVO> getScoreResultByObjectId(String objectId) throws Exception {
		return srd.getScoreResultByObjectId(objectId);
	}

	/**
	 * @see ��ȡĳ�û� ĳ��״̬ �����۽����
	 * @param ScorerId
	 *            �������壨�����ˡ��̹���Id
	 * @param status
	 *            ״̬��0:δ���ۡ�1�����棬δ�ύ��2:���ύ
	 * @return һ������Ҫ��״̬�ģ��������۽������
	 */
	public List<ScoreResultVO> getScoreResultByScorerId(String scorerId, int status) throws Exception {
		return srd.getScoreResultByScorerId(scorerId, status);
	}

	/**
	 * @see ��ȡĳ�û� ĳ��״̬ Ӧ���������۵����۽����
	 * @param ScorerId
	 *            ���۶��󣨱����ˡ��̹���Id
	 * @param status
	 *            ״̬��0:δ���ۡ�1�����棬δ�ύ��2:���ύ
	 * @return һ������Ҫ��״̬�ģ��������۽������
	 */
	public List<ScoreResultVO> getScoreResultByObjectId(String objectId, int status) throws Exception {
		return srd.getScoreResultByObjectId(objectId, status);
	}

	/**
	 * @see ��ȡĳ�û� ĳ��״̬ Ӧ���������۵����۽����
	 * @param ScorerId
	 *            ���۶��󣨱����ˡ��̹���Id
	 * @param scoreRuleId
	 *            ��������Id
	 * @param status
	 *            ״̬��0:δ���ۡ�1�����棬δ�ύ��2:���ύ
	 * @return һ������Ҫ��״̬�ģ��������۽������
	 */
	public List<ScoreResultVO> getScoreResultByObjectIdAndScoreRuleId(String objectId, int scoreRuleId, int status)
			throws Exception {
		return srd.getScoreResultByObjectIdAndScoreRuleId(objectId, scoreRuleId, status);
	}

	/**
	 * ��ȡ���п��˶����list������ObjectId���ظ���
	 * 
	 * @return ���п��˶����list
	 * @throws Exception
	 */
	public List<ScoreResultVO> getAllObject() throws Exception {
		return srd.getAllObject();
	}

	/**
	 * �����ݿ�ġ����ֽ�������в���һ�������ֹ�ϵ����״̬Ϊ��0���ġ����ֽ������
	 * 
	 * @param scoreResultVo
	 *            ״̬Ϊ��0���ġ����ֽ����
	 * @return ��������
	 * @throws Exception
	 */
	public int insertScoreResult(ScoreResultVO srVo) throws Exception {
		return srd.insertScoreResult(srVo);
	}

	/**
	 * �����ݿ�ġ����ֽ�������в���100�������ֹ�ϵ����״̬Ϊ��0���ġ����ֽ������
	 * 
	 * @param scoreResultVo
	 *            ״̬Ϊ��0���ġ����ֽ����
	 * @return ��������
	 * @throws Exception
	 */
	public int batchInsertScoreResult(List<ScoreResultVO> scoreResultVos) throws Exception {
		return srd.batchInsertScoreResult(scoreResultVos);
	}

	/**
	 * �޸�ĳ��ָ���ģ�ָ��id�������۽��
	 * 
	 * @param scoreResultVo
	 *            �ں�id��Ҫ�޸ĵ���Ϣ
	 * @return ��Ӱ������
	 */
	public int updateScoreResult(ScoreResultVO srVo) throws Exception {
		return srd.updateScoreResult(srVo);
	}

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
			int status) throws Exception {
		return srd.getCountByObjectIdAndScorerIdsAndScoreRuleId(objectId, scorerIdsStr, scoreRuleId, status);
	}

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
	public int getCountByObjectIdAndScoreRuleId(String objectId, int scoreRuleId, int status) throws Exception {
		return srd.getCountByObjectIdAndScoreRuleId(objectId, scoreRuleId, status);
	}

	/**
	 * @see �ı�ĳ�����۽����״̬
	 * @param scoreResultId
	 *            ָ�����۽����Id
	 * @param status
	 *            Ҫ�ı������״̬��0:δ���ۡ�1�����棬δ�ύ��2:���ύ
	 * @param commitDate
	 *            �ύ���ڣ�YYYY��MM��DD�գ�
	 * @param score
	 *            �ܷ֣��������ݿ���remark(��ע)��
	 * @return ���ؽ����˲���������
	 */
	public int changeScoreResultStatusByID(int scoreResultId, int status, double scoreResult, String commitDate)
			throws Exception {
		return srd.changeScoreResultStatusByID(scoreResultId, status, scoreResult, commitDate);
	}

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
	public double getAverageScoreByObjectIdAndScoreRuleIdAndScorerIdsX(String objectId, int scoreRuleId,
			String scorerIdsStr, int scoreType) throws Exception {
		return srd.getAverageScoreByObjectIdAndScoreRuleIdAndScorerIdsX(objectId, scoreRuleId, scorerIdsStr, scoreType);
	}

	public double getAverageScoreByObjectIdAndScoreRuleIdAndScorerIds(String objectId, int scoreRuleId,
			String scorerIdsStr) throws Exception {
		return srd.getAverageScoreByObjectIdAndScoreRuleIdAndScorerIds(objectId, scoreRuleId, scorerIdsStr);
	}

	/**
	 * ��ȡĳ���˶��� �� ĳ���������塢�� ĳ�ֿ��˹��� ���ۣ������� �ύ ״̬�����۽��
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
			String scorerIdsStr) throws Exception {
		return srd.getScoreResultByObjectIdAndScoreRuleIdAndScorerIds(objectId, scoreRuleId, scorerIdsStr);
	}

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
	public List<UserVO> getScorerByObjectType(String objectId, String objectType, String scorerType) throws Exception {
		return srd.getScorerByObjectType(objectId, objectType, scorerType);
	}

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
	public double getScore(String objectId, int objectType, String scorerId) throws Exception {
		return srd.getScore(objectId, objectType, scorerId);
	}
}
