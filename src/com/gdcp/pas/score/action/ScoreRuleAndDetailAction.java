package com.gdcp.pas.score.action;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import org.apache.log4j.Logger;

import com.gdcp.common.extend.ExtendCodeUtil;
import com.gdcp.pas.manage.bo.UserBO;
import com.gdcp.pas.manage.vo.UserVO;
import com.gdcp.pas.score.bo.ScoreResultBO;
import com.gdcp.pas.score.bo.ScoreRuleBO;
import com.gdcp.pas.score.bo.ScoreRuleDetailBO;
import com.gdcp.pas.score.vo.ScoreResultVO;
import com.gdcp.pas.score.vo.ScoreRuleDetailVO;
import com.gdcp.pas.score.vo.ScoreRuleVO;
import com.opensymphony.xwork2.ActionContext;

/**
 * 主要用于生成“评价表”（未评的、保存的、提交的） （除了读取规则用于生成表格之外，还要读取分数用于填充生成表格的内容）
 * 
 * @author 陈伟镇
 * @version 0327-21:14
 * @see 对评分规则，及其评分规则子表的Action
 */
public class ScoreRuleAndDetailAction {
	private static Logger logger = Logger.getLogger(ScoreRuleAndDetailAction.class);
	private ScoreRuleBO sRuletBo = new ScoreRuleBO();
	private ScoreRuleDetailBO sRuleDetailBo = new ScoreRuleDetailBO();

	private ScoreResultBO sResultBo = new ScoreResultBO();
	private UserBO userBo = new UserBO();

	// 传入:scoreResultId(可根据这个id获取一个评分结果记录)
	private int scoreResultId;

	// 传人：objectId(teacherId:可根据这个id获取一系列评价结果)
	private String objectId;

	private int userCharacter;

	/**
	 * 生成“评价考核表”的唯一入口
	 */
	@SuppressWarnings("unchecked")
	public String showDetailTable() {
		try {
			ScoreResultVO scoreResultVo = sResultBo.getScoreResultByScoreResultId(scoreResultId);
			setObjectName(scoreResultVo);
			int scoreRuleId = scoreResultVo.getScoreRuleId();
			if (scoreRuleId == 1001) {
				if (userCharacter == 100213 || userCharacter == 100215) {
					scoreRuleId = 1009;
					scoreResultVo.setScoreRuleId(1009);
				}
			}

			// 获取主规则
			ScoreRuleVO scoreRuleVo = sRuletBo.getScoreRuleById(scoreRuleId);

			Map<String, Object> request = (Map<String, Object>) ActionContext.getContext().get("request");
			try {

				if (scoreRuleVo != null) {
					request.put("scoreRuleVo", scoreRuleVo);

					// 获取规则
					ArrayList<ScoreRuleDetailVO> srdLV1s = (ArrayList<ScoreRuleDetailVO>) sRuleDetailBo
							.getScoreRuleAndChildsByResultIdAndParentId(scoreResultVo, 0);
					request.put("scoreRuleDrtailLV1s", srdLV1s);

					// 再把本表对应的scoreResultVO放入session
					request.put("scoreResultVo", scoreResultVo);

					String scoreTypeKet = scoreResultVo.getScorerType() + "";
					String scorerTypeName = ExtendCodeUtil.getInstance().getValue("scorerType", scoreTypeKet);
					request.put("scorerTypeName", scorerTypeName);

				} else {
					request.put("scoreRule", null);
					request.put("tableName", "对不起!\n该教工没有被考核，所以无法显示考核结果");
					request.put("scoreRuleDrtailLV1s", null);
					request.put("scoreResultVo", null);
					request.put("heji", 0);// 总分
				}

			} catch (SQLException e) {
				logger.error("SQLException", e);
			} catch (Exception e) {
				logger.error("Exception", e);
			}

			if (scoreRuleVo.getDetailLevel() == 2) {
				return "showTableLV2_success";
			} else if (scoreRuleVo.getDetailLevel() == 3) {
				return "showTableLV3_success";
			}
		} catch (Exception e) {
			logger.error("生成“评价考核表”的唯一入口失败", e);
		}

		return null;
	}

	/**
	 * 为一个scoreResultVo添加页面需要的显示信息（对象名）
	 * 
	 * @param 一个缺少需要显示信息的scoreResultVo
	 */
	private void setObjectName(ScoreResultVO scoreResultVo) throws Exception {
		UserVO userVo = userBo.getUserByTeacherId(scoreResultVo.getObjectId());
		userCharacter = userVo.getUserCharacter();
		scoreResultVo.setObjectName(userVo.getUserName());
	}

	// 自动生成以下get、set方法。
	public int getScoreResultId() {
		return scoreResultId;
	}

	public void setScoreResultId(int scoreResultId) {
		this.scoreResultId = scoreResultId;
	}

	public String getObjectId() {
		return objectId;
	}

	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}

}
