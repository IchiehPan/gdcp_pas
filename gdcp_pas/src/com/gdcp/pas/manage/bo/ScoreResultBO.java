package com.gdcp.pas.manage.bo;

/**
 * @author �ſ���  2015-03-26 
 * @see �ṩ���۹�ϵ�����ѯ��ɾ�������Ӳ���
 */
import java.util.List;

import com.gdcp.common.Page;
import com.gdcp.pas.manage.dao.ScoreResultDAO;
import com.gdcp.pas.manage.dao.impl.ScoreResultDAOImpl;
import com.gdcp.pas.manage.vo.ScoreResultVO;

public class ScoreResultBO {
	private ScoreResultDAO srDAO = new ScoreResultDAOImpl();

	/**
	 * @param scoreResultVO
	 *            ScoreResultVO
	 * @see �����ݿ��ѯ�������ֹ�ϵ
	 */
	public List<ScoreResultVO> queryPage(Page page, ScoreResultVO vo) throws Exception {
		return srDAO.queryPage(page, vo);
	}

	/**
	 * @param scoreResultVO
	 *            ScoreResultVO
	 * @see �����ݿ�ɾ��һ�����ֹ�ϵ�ļ�¼
	 */
	public int deleteRec(ScoreResultVO scoreResult) throws Exception {
		return srDAO.deleteRec(scoreResult);
	}

	/**
	 * @param scoreResultVO
	 *            ScoreResultVO
	 * @see �����û�������û�����ѯ���ֶ������������
	 */
	public List<ScoreResultVO> query(ScoreResultVO scoreResult) throws Exception {
		return srDAO.query(scoreResult);
	}

	public List<ScoreResultVO> queryByStatus(int status) throws Exception {
		return srDAO.queryByStatus(status);
	}

	/**
	 * @param scoreResultVO
	 *            ScoreResultVO
	 * @see ��ѯ�������۹�������
	 */
	public List<ScoreResultVO> queryRuleName() throws Exception {
		return srDAO.queryRuleName();
	}

	/**
	 * @param scoreResultVO
	 *            ScoreResultVO
	 * @see �������ֶ�����id��ѯ�Ĳ����µ������û���
	 */
	public List<ScoreResultVO> queryObjectName(int odeptId) throws Exception {
		return srDAO.queryObjectName(odeptId);
	}

	/**
	 * @param scoreResultVO
	 *            ScoreResultVO
	 * @see �����������岿��id��ѯ�Ĳ����µ������û���
	 */
	public List<ScoreResultVO> queryScorerName(int sdeptId) throws Exception {
		return srDAO.queryScorerName(sdeptId);
	}

	/**
	 * @param scoreResultVO
	 *            ScoreResultVO
	 * @see ��ѯ���п��˶�������
	 */
	public List<ScoreResultVO> queryObjectType() throws Exception {
		return srDAO.queryObjectType();
	}

	/**
	 * @param scoreResultVO
	 *            ScoreResultVO
	 * @see ��ѯ����������������
	 */
	public List<ScoreResultVO> queryScorerType() throws Exception {
		return srDAO.queryScorerType();
	}

	/**
	 * @param scoreResultVO
	 *            ScoreResultVO
	 * @see �����ֽ�����������һ�����ֹ�ϵ��¼
	 */
	public int insertRec(ScoreResultVO scoreResult) throws Exception {
		return srDAO.insertRec(scoreResult);
	}

}
