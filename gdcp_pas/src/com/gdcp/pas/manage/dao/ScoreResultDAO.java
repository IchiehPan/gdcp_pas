package com.gdcp.pas.manage.dao;

/**
 * @author �ſ���  2015-03-26 
 * @see ���ṩ���۹�ϵ�����ѯ��ɾ�������Ӳ���
 */
import java.util.List;

import com.gdcp.common.Page;
import com.gdcp.pas.manage.vo.ScoreResultVO;

public interface ScoreResultDAO {
	/**
	 * @param scoreResultVO
	 *            ScoreResultVO
	 * @see �����ݿ��ѯ�������ֹ�ϵ
	 */
	public List<ScoreResultVO> queryPage(Page page, ScoreResultVO vo) throws Exception;

	/**
	 * @param scoreResultVO
	 *            ScoreResultVO
	 * @see �����ݿ�ɾ��һ�����ֹ�ϵ�ļ�¼
	 */
	public int deleteRec(ScoreResultVO scoreResult) throws Exception;

	/**
	 * @param scoreResultVO
	 *            ScoreResultVO
	 * @see �����û�������û�����ѯ���ֶ������������
	 */
	public List<ScoreResultVO> query(ScoreResultVO scoreResult) throws Exception;

	public List<ScoreResultVO> queryByStatus(int status) throws Exception;

	/**
	 * @param scoreResultVO
	 *            ScoreResultVO
	 * @see ��ѯ�������۹�������
	 */
	public List<ScoreResultVO> queryRuleName() throws Exception;

	/**
	 * @param scoreResultVO
	 *            ScoreResultVO
	 * @see �������ֶ�����id��ѯ�Ĳ����µ������û���
	 */
	public List<ScoreResultVO> queryObjectName(int odeptId) throws Exception;

	/**
	 * @param scoreResultVO
	 *            ScoreResultVO
	 * @see �����������岿��id��ѯ�Ĳ����µ������û���
	 */
	public List<ScoreResultVO> queryScorerName(int sdeptId) throws Exception;

	/**
	 * @param scoreResultVO
	 *            ScoreResultVO
	 * @see ��ѯ���п��˶�������
	 */
	public List<ScoreResultVO> queryObjectType() throws Exception;

	/**
	 * @param scoreResultVO
	 *            ScoreResultVO
	 * @see �����ֽ�����������һ�����ֹ�ϵ��¼
	 */
	public int insertRec(ScoreResultVO scoreResult) throws Exception;

	/**
	 * @param scoreResultVO
	 *            ScoreResultVO
	 * @see ��ѯ����������������
	 */
	public List<ScoreResultVO> queryScorerType() throws Exception;

}
