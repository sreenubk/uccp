package com.lotus.uccp.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.lotus.uccp.consentmgmt.wcm.dao.AlertRequestDao;
import com.lotus.uccp.consentmgmt.wcm.dao.CohortDao;
import com.lotus.uccp.consentmgmt.wcm.dao.ReasonCodeDao;
import com.lotus.uccp.consentmgmt.wcm.dao.RegistrationRequestDao;
import com.lotus.uccp.consentmgmt.wcm.dto.AlertRequest;
import com.lotus.uccp.consentmgmt.wcm.dto.AlertStatusHist;
import com.lotus.uccp.consentmgmt.wcm.dto.ApiHist;
import com.lotus.uccp.consentmgmt.wcm.dto.Cohort;
import com.lotus.uccp.consentmgmt.wcm.dto.CohortRule;
import com.lotus.uccp.consentmgmt.wcm.dto.CohortTarget;
import com.lotus.uccp.consentmgmt.wcm.dto.ReasonCode;
import com.lotus.uccp.consentmgmt.wcm.dto.ReferralPersonLADto;
import com.lotus.uccp.consentmgmt.wcm.dto.ReferralStatusHist;
import com.lotus.uccp.consentmgmt.wcm.dto.RegistrationRequest;
import com.lotus.uccp.consentmgmt.wcm.dto.Target;
import com.lotus.uccp.wcm.vo.AlertRequestVO;
import com.lotus.uccp.wcm.vo.AlertStatusHistVO;
import com.lotus.uccp.wcm.vo.ApiHistVO;
import com.lotus.uccp.wcm.vo.CohortRuleVO;
import com.lotus.uccp.wcm.vo.CohortTargetVO;
import com.lotus.uccp.wcm.vo.CohortVO;
import com.lotus.uccp.wcm.vo.ReasonCodeVO;
import com.lotus.uccp.wcm.vo.ReferralPersonLAVo;
import com.lotus.uccp.wcm.vo.ReferralStatusHistVO;
import com.lotus.uccp.wcm.vo.RegistrationRequestVO;
import com.lotus.uccp.wcm.vo.TargetVO;

public class WCMConverter {

	public RegistrationRequestVO convertToVo(RegistrationRequest req) {
		RegistrationRequestVO obj = new RegistrationRequestVO();

		try {

			obj.setReqReqtID(req.getReqReqtID());
			obj.setCohortID(req.getCohortID());

			SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");

			if (req.getCreatedDate() != null) {
				String startDate = formatter.format(req.getCreatedDate());
				obj.setCreatedDate(startDate);
			}
			obj.setfName(req.getfName());
			obj.setLname(req.getLname());
			obj.setReasoncode(req.getReasoncode());
			obj.setCreatedByUser(req.getCreatedByUser());
			obj.setRuleID(req.getRuleID());
			obj.setSourceSys(req.getSourceSys());
			obj.setSourceSysID(req.getSourceSysID());
			obj.setStatus(req.getStatus());
			obj.setComments(req.getComments());
			obj.setTargetId(req.getTargetId());
		} catch (Exception ex) {

		}
		return obj;
	}

	public RegistrationRequest convertTo(RegistrationRequestVO req) {
		RegistrationRequest obj = new RegistrationRequest();

		try {

			obj.setReqReqtID(req.getReqReqtID());
			obj.setCohortID(req.getCohortID());

			SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");

			if (req.getCreatedDate() != null) {
				// String startDate= formatter.format(req.getCreatedDate());
				Date startdate = formatter.parse(req.getCreatedDate());
				obj.setCreatedDate(startdate);
			}

			obj.setfName(req.getfName());
			obj.setLname(req.getLname());
			obj.setReasoncode(req.getReasoncode());
			obj.setCreatedByUser(req.getCreatedByUser());
			obj.setRuleID(req.getRuleID());
			obj.setSourceSys(req.getSourceSys());
			obj.setSourceSysID(req.getSourceSysID());
			obj.setStatus(req.getStatus());
			obj.setComments(req.getComments());
			obj.setTargetId(req.getTargetId());
		} catch (Exception ex) {

		}
		return obj;
	}

	public List<RegistrationRequestVO> covertList(List<RegistrationRequest> doObjs, RegistrationRequestVO Cohortvo) {
		List<RegistrationRequestVO> voObjs = new ArrayList<RegistrationRequestVO>();
		doObjs.forEach(doObj -> voObjs.add(convertToVo(doObj)));

		return voObjs;
	}

	/*-----------------------------------------------*/

	public AlertRequestVO convertToVo(AlertRequest req) {
		AlertRequestVO obj = new AlertRequestVO();

		try {

			obj.setReqReqtID(req.getReqReqtID());
			obj.setCohortID(req.getCohortID());

			SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");

			if (req.getCreatedDate() != null) {
				String startDate = formatter.format(req.getCreatedDate());
				obj.setCreatedDate(startDate);
			}

			obj.setfName(req.getfName());
			obj.setLname(req.getLname());
			obj.setReasoncode(req.getReasoncode());
			obj.setCreatedByUser(req.getCreatedByUser());
			obj.setRuleID(req.getRuleID());
			obj.setSourceSys(req.getSourceSys());
			obj.setSourceSysID(req.getSourceSysID());
			obj.setStatus(req.getStatus());
			obj.setComments(req.getComments());

		} catch (Exception ex) {

		}
		return obj;
	}

	public List<AlertRequestVO> covertList(List<AlertRequest> doObjs, AlertRequestVO Cohortvo) {
		List<AlertRequestVO> voObjs = new ArrayList<AlertRequestVO>();
		doObjs.forEach(doObj -> voObjs.add(convertToVo(doObj)));

		return voObjs;
	}

	/*-----------------------------------------------*/

	public ReasonCodeVO convertToVo(ReasonCode reason) {
		ReasonCodeVO reasonVo = new ReasonCodeVO();

		reasonVo.setReasonCode(reason.getReasonCode());
		reasonVo.setReasonDesc(reason.getReasonDesc());
		return reasonVo;
	}

	public ReasonCode convertTo(ReasonCodeVO reason) {
		ReasonCode reasonVo = new ReasonCode();

		reasonVo.setReasonCode(reason.getReasonCode());
		reasonVo.setReasonDesc(reason.getReasonDesc());
		return reasonVo;
	}

	public List<ReasonCodeVO> covertList(List<ReasonCode> doObjs, ReasonCodeVO Cohortvo) {
		List<ReasonCodeVO> voObjs = new ArrayList<ReasonCodeVO>();
		doObjs.forEach(doObj -> voObjs.add(convertToVo(doObj)));

		return voObjs;
	}

	/*-----------------------------------------------*/

	public CohortTargetVO convertToVo(CohortTarget cohtarget) {
		CohortTargetVO targetvo1 = new CohortTargetVO();

		targetvo1.setCohortid(cohtarget.getCohortid().getCohortid());
		targetvo1.setTargetcohortid(cohtarget.getTargetcohortid());
		targetvo1.setTargetedID(cohtarget.getTargetedID().getTargetedID());
		targetvo1.setName(cohtarget.getName());
		long cohortID = cohtarget.getCohortid().getCohortid().longValue();

		CohortDao cohortDao = new CohortDao();
		Cohort cohort = cohortDao.getById(cohortID);
		targetvo1.setCohortName(cohort.getName());
		targetvo1.setCohortDesc(cohort.getCohortdescription());
		return targetvo1;
	}

	public List<CohortTargetVO> covertList(List<CohortTarget> doObjs, CohortTargetVO Cohortvo) {
		List<CohortTargetVO> voObjs = new ArrayList<CohortTargetVO>();
		doObjs.forEach(doObj -> voObjs.add(convertToVo(doObj)));

		return voObjs;
	}

	/*-----------------------------------------------*/

	public TargetVO convertToVo(Target target) {
		TargetVO targetvo1 = new TargetVO();

		targetvo1.setTargetedID(target.getTargetedID());
		targetvo1.setTargetDescription(target.getTargetDescription());
		targetvo1.setTargetName(target.getTargetName());
		targetvo1.setTargetAddress(target.getTargetAddress());
		targetvo1.setTargetAltAddress(target.getTargetAltAddress());

		return targetvo1;
	}

	public List<TargetVO> covertList(List<Target> doObjs, TargetVO Cohortvo) {
		List<TargetVO> voObjs = new ArrayList<TargetVO>();
		doObjs.forEach(doObj -> voObjs.add(convertToVo(doObj)));

		return voObjs;
	}

	/*-----------------------------------------------*/

	public CohortVO convertToVo(Cohort cohort) {
		CohortVO Cohortvo = new CohortVO();
		// Cohort Cohort = new Cohort();
		Cohortvo.setCohortdescription(cohort.getCohortdescription());
		Cohortvo.setCohortid(cohort.getCohortid());
		Cohortvo.setName(cohort.getName());

		return Cohortvo;
	}

	public List<CohortVO> covertList(List<Cohort> doObjs, CohortVO Cohortvo) {
		List<CohortVO> voObjs = new ArrayList<CohortVO>();
		doObjs.forEach(doObj -> voObjs.add(convertToVo(doObj)));

		return voObjs;
	}

	/*-----------------------------------------------*/

	/*
	 * CREATE TABLE WCM_COHORT_RULE ( cohortID number(25,2) NULL, ruleID
	 * varchar2(30) NULL, ruleShortDesc varchar2(100) NULL, ruleDescription
	 * varchar2(100) NOT NULL, ruleAction varchar2(40) NOT NULL, reasonCode
	 * varchar2(40) NOT NULL, isEnabled varchar2(20) NOT NULL, approvalRequired
	 * varchar2(40) NOT NULL, startDate date NOT NULL, endDate date NOT NULL,
	 * cohortRuleID number(19,2) NOT NULL, CONSTRAINT COHORT_RULE_pk PRIMARY KEY
	 * (cohortRuleID) ) ;
	 */
	public CohortRuleVO convertToVo(CohortRule cohortRule) {
		CohortRuleVO cohortRulevo = new CohortRuleVO();
		// Cohort Cohort = new Cohort();
		cohortRulevo.setCohortid(cohortRule.getCohortid().getCohortid());
		cohortRulevo.setRuleID(cohortRule.getRuleID());
		cohortRulevo.setRuleShortDesc(cohortRule.getRuleShortDesc());
		cohortRulevo.setRuleDescription(cohortRule.getRuleDescription());
		cohortRulevo.setRuleAction(cohortRule.getRuleAction());
		cohortRulevo.setReasonCode(cohortRule.getReasonCode().getReasonCode());
		cohortRulevo.setIsEnabled(cohortRule.getIsEnabled());
		cohortRulevo.setApprovalRequired(cohortRule.getApprovalRequired());

		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		if (cohortRule.getStartDate() != null) {
			String startDate = formatter.format(cohortRule.getStartDate());
			cohortRulevo.setStartDate(startDate);
		}
		if (cohortRule.getEndDate() != null) {
			String endDate = formatter.format(cohortRule.getEndDate());
			cohortRulevo.setEndDate(endDate);
		}
		cohortRulevo.setCohortRuleID(cohortRule.getCohortRuleID());
		return cohortRulevo;
	}

	/**
	 * @param cohortRule
	 * @return
	 */
	public CohortRule convertTo(CohortRuleVO cohortRule) {
		CohortRule cohortRulevo = new CohortRule();
		try {
			CohortDao CohortDao = new CohortDao();
			ReasonCodeDao reasonDao = new ReasonCodeDao();

			Cohort cohort = CohortDao.getById(cohortRule.getCohortid().longValue());
			cohortRulevo.setCohortid(cohort);
			cohortRulevo.setRuleID(cohortRule.getRuleID());
			cohortRulevo.setRuleShortDesc(cohortRule.getRuleShortDesc());
			cohortRulevo.setRuleDescription(cohortRule.getRuleDescription());
			cohortRulevo.setRuleAction(cohortRule.getRuleAction());

			cohortRulevo.setReasonCode(reasonDao.getById(cohortRule.getReasonCode()));
			cohortRulevo.setIsEnabled(cohortRule.getIsEnabled());
			cohortRulevo.setApprovalRequired(cohortRule.getApprovalRequired());

			SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
			if (cohortRule.getStartDate() != null) {

				Date startdate = formatter.parse(cohortRule.getStartDate());
				cohortRulevo.setStartDate(startdate);
			}
			if (cohortRule.getEndDate() != null) {

				Date endDate = formatter.parse(cohortRule.getEndDate());
				cohortRulevo.setEndDate(endDate);
			}
			cohortRulevo.setCohortRuleID(cohortRule.getCohortRuleID());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return cohortRulevo;
	}

	public List<CohortRuleVO> covertList(List<CohortRule> doObjs, CohortRuleVO CohortRulevo) {
		List<CohortRuleVO> voObjs = new ArrayList<CohortRuleVO>();
		doObjs.forEach(doObj -> voObjs.add(convertToVo(doObj)));

		return voObjs;
	}

	/*----------------------ReferralPerson_LA - Start-------------------*/
	public ReferralPersonLAVo convertToVo(ReferralPersonLADto dtoObj) {
		ReferralPersonLAVo voObj = new ReferralPersonLAVo();

		try {

			voObj.setPersonID(dtoObj.getPersonID());
			voObj.setClientID(dtoObj.getClientID());
			voObj.setCustomerName(dtoObj.getCustomerName());
			voObj.setDOB(dtoObj.getDOB());
			voObj.setGender(dtoObj.getGender());
			voObj.setRelationship(dtoObj.getRelationship());
			voObj.setAddress(dtoObj.getAddress());
			voObj.setMemberID(dtoObj.getMemberID());
			voObj.setFName(dtoObj.getFName());
			voObj.setLName(dtoObj.getLName());

		} catch (Exception ex) {

		}
		return voObj;
	}

	public ReferralPersonLADto convertToDto(ReferralPersonLAVo voObj) {
		ReferralPersonLADto dtoObj = new ReferralPersonLADto();

		try {

			dtoObj.setPersonID(voObj.getPersonID());
			dtoObj.setClientID(voObj.getClientID());
			dtoObj.setCustomerName(voObj.getCustomerName());
			dtoObj.setDOB(voObj.getDOB());
			dtoObj.setGender(voObj.getGender());
			dtoObj.setRelationship(voObj.getRelationship());
			dtoObj.setAddress(voObj.getAddress());
			dtoObj.setMemberID(voObj.getMemberID());
			dtoObj.setFName(voObj.getFName());
			dtoObj.setLName(voObj.getLName());

		} catch (Exception ex) {

		}
		return dtoObj;
	}

	public List<ReferralPersonLAVo> convertList(List<ReferralPersonLADto> dtoObjList) {
		List<ReferralPersonLAVo> voList = new ArrayList<ReferralPersonLAVo>();
		dtoObjList.forEach(obj -> voList.add(convertToVo(obj)));

		return voList;
	}

	/*----------------------ReferralPerson_LA - End-------------------*/

	/*----------------------ReferralStatusHist - Start-------------------*/
	public ReferralStatusHistVO convertToVo(ReferralStatusHist obj) {
		ReferralStatusHistVO voObj = new ReferralStatusHistVO();

		try {

			voObj.setHis_reqreqtid(obj.getHis_reqreqtid());
			voObj.setReqReqtID(obj.getRegReq().getReqReqtID());
			voObj.setStatus(obj.getStatus());
			voObj.setStatusComments(obj.getStatusComments());

			SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
			if (obj.getStatusDate() != null) {
				String statusDate = formatter.format(obj.getStatusDate());
				voObj.setStatusDate(statusDate);
			}

			voObj.setStatusUser(obj.getStatusUser());

		} catch (Exception ex) {

		}
		return voObj;
	}

	public ReferralStatusHist convertTo(ReferralStatusHistVO voObj) {
		ReferralStatusHist obj = new ReferralStatusHist();

		try {

			obj.setHis_reqreqtid(voObj.getHis_reqreqtid());

			RegistrationRequest regReq = new RegistrationRequest();
			RegistrationRequestDao regReqDao = new RegistrationRequestDao();
			regReq = regReqDao.getById(voObj.getReqReqtID().longValue());
			obj.setRegReq(regReq);

			obj.setStatus(voObj.getStatus());
			obj.setStatusComments(voObj.getStatusComments());

			SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
			if (voObj.getStatusDate() != null) {
				Date statusDate = formatter.parse(voObj.getStatusDate());
				obj.setStatusDate(statusDate);
			}

			obj.setStatusUser(voObj.getStatusUser());

		} catch (Exception ex) {

		}
		return obj;
	}

	public List<ReferralStatusHistVO> convertList(List<ReferralStatusHist> list, ReferralStatusHistVO vo) {
		List<ReferralStatusHistVO> voList = new ArrayList<ReferralStatusHistVO>();
		list.forEach(obj -> voList.add(convertToVo(obj)));

		return voList;
	}

	/*----------------------ReferralStatusHist - End-------------------*/
	
	/*----------------------AlertStatusHist - Start-------------------*/
	public AlertStatusHistVO convertToVo(AlertStatusHist obj) {
		AlertStatusHistVO voObj = new AlertStatusHistVO();

		try {

			voObj.setHis_reqreqtid(obj.getHis_reqreqtid());
			voObj.setReqReqtID(obj.getRegReq().getReqReqtID());
			voObj.setStatus(obj.getStatus());
			voObj.setStatusComments(obj.getStatusComments());

			SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
			if (obj.getStatusDate() != null) {
				String statusDate = formatter.format(obj.getStatusDate());
				voObj.setStatusDate(statusDate);
			}

			voObj.setStatusUser(obj.getStatusUser());

		} catch (Exception ex) {

		}
		return voObj;
	}

	public AlertStatusHist convertTo(AlertStatusHistVO voObj) {
		AlertStatusHist obj = new AlertStatusHist();

		try {

			obj.setHis_reqreqtid(voObj.getHis_reqreqtid());

			AlertRequest regReq = new AlertRequest();
			AlertRequestDao regReqDao = new AlertRequestDao();
			regReq = regReqDao.getById(voObj.getReqReqtID().longValue());
			obj.setRegReq(regReq);

			obj.setStatus(voObj.getStatus());
			obj.setStatusComments(voObj.getStatusComments());

			SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
			if (voObj.getStatusDate() != null) {
				Date statusDate = formatter.parse(voObj.getStatusDate());
				obj.setStatusDate(statusDate);
			}

			obj.setStatusUser(voObj.getStatusUser());

		} catch (Exception ex) {

		}
		return obj;
	}

	public List<AlertStatusHistVO> convertList(List<AlertStatusHist> list, AlertStatusHistVO vo) {
		List<AlertStatusHistVO> voList = new ArrayList<AlertStatusHistVO>();
		list.forEach(obj -> voList.add(convertToVo(obj)));

		return voList;
	}

	/*----------------------AlertStatusHist - End-------------------*/

	

	/*----------------------ApiHist - Start-------------------*/
	public ApiHistVO convertToVo(ApiHist obj) {
		ApiHistVO voObj = new ApiHistVO();

		try {

			voObj.setApiHistId(obj.getApiHistId());
			voObj.setTargetID(obj.getTargetID());
			voObj.setRequestId(obj.getRequestId());
			voObj.setApiAction(obj.getApiAction());
			SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
			if (obj.getSendDate() != null) {
				String sendDate = formatter.format(obj.getSendDate());
				voObj.setSendDate(sendDate);
			}
			voObj.setSendStatus(obj.getSendStatus());
			voObj.setTargetTransID(obj.getTargetTransID());

		} catch (Exception ex) {

		}
		return voObj;
	}

	public ApiHist convertTo(ApiHistVO voObj) {
		ApiHist obj = new ApiHist();

		try {

			obj.setApiHistId(voObj.getApiHistId());
			obj.setTargetID(voObj.getTargetID());
			obj.setRequestId(voObj.getRequestId());
			obj.setApiAction(voObj.getApiAction());
			
			SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
			if (voObj.getSendDate() != null) {
				Date sendDate = formatter.parse(voObj.getSendDate());
				obj.setSendDate(sendDate);
			}
			obj.setSendStatus(obj.getSendStatus());
			obj.setTargetTransID(obj.getTargetTransID());

		} catch (Exception ex) {

		}
		return obj;
	}

	public List<ApiHistVO> convertList(List<ApiHist> list, ApiHistVO vo) {
		List<ApiHistVO> voList = new ArrayList<ApiHistVO>();
		list.forEach(obj -> voList.add(convertToVo(obj)));

		return voList;
	}

	/*----------------------ApiHist - End-------------------*/
}
