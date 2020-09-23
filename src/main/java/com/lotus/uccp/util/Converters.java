/* Licensed Materials - 
 Property of IBM 6949 - 67L 
 Copyright IBM Corp. 2017, 2018 All Rights Reserved */
package com.lotus.uccp.util;

import com.lotus.uccp.consentmgmt.dao.OrgRoleJobUserDao;
import com.lotus.uccp.authentication.model.*;

import com.lotus.uccp.consentmgmt.dto.Job;
import com.lotus.uccp.authentication.model.ReferralLA;
import com.lotus.uccp.vo.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Converters {

	public AuthorizationGroupVO convertToVo(AuthorizationGroup authorizationGroup) {
		AuthorizationGroupVO authorizationGroupVO = new AuthorizationGroupVO();

		authorizationGroupVO.setAuthorizationGroupId(authorizationGroup.getAuthorizationgroupid());

		com.lotus.uccp.authentication.model.ControlGroup controlGroup = authorizationGroup.getControlGroup();
		if (controlGroup != null) {
			// ControlGroup controlGroup =
			// authorizationGroup.getControlGroup() ;
			authorizationGroupVO.setControlGroup(controlGroup.getControlgroupid().longValue());
			authorizationGroupVO.setControlGroupName(controlGroup.getControlgroupname());
		}
		if (authorizationGroup.getOrganizationUnit() != null) {
			authorizationGroupVO.setOrganizationUnit(
					authorizationGroup.getOrganizationUnit().getorganizationUnitid().longValue());
			authorizationGroupVO.setOrganizationName(authorizationGroup.getOrganizationUnit().getName());
		}
		if (authorizationGroup.getSecurityRole() != null)
			authorizationGroupVO.setSecurityRole(authorizationGroup.getSecurityRole().getRoleName());
		if (authorizationGroup.getUsers() != null)
			authorizationGroupVO.setUsers(authorizationGroup.getUsers().getUsername());
		authorizationGroupVO.setAuthGroupName(authorizationGroup.getAuthGroupName());
		//authorizationGroupVO.setJobid(authorizationGroup.getJobid());
		OrgRoleJobUserDao cosdorgrolejobuserdao = new OrgRoleJobUserDao();
		authorizationGroupVO.setJob(null);
		authorizationGroupVO.setJobUserName(null);
		authorizationGroupVO.setJobName(authorizationGroup.getJobName());

       System.out.println("===Jobname==" + authorizationGroupVO.getJobName());
		authorizationGroupVO.setLastupdatedby(authorizationGroup.getLastupdatedby());
		authorizationGroupVO.setCreatedby(authorizationGroup.getCreatedby());
		authorizationGroupVO.setCreatedon(authorizationGroup.getCreatedon());
		authorizationGroupVO.setLastupdatedon(authorizationGroup.getLastupdatedon());

		return authorizationGroupVO;
	}

	public List<AuthorizationGroupVO> covertList(List<AuthorizationGroup> doObjs,
			AuthorizationGroupVO AuthorizationGroup) {
		List<AuthorizationGroupVO> voObjs = new ArrayList<AuthorizationGroupVO>();
		doObjs.forEach(doObj -> voObjs.add(convertToVo(doObj)));

		return voObjs;
	}

	public ControlGroupVO convertToVo(com.lotus.uccp.authentication.model.ControlGroup controlGroup) {
		ControlGroupVO controlGroupVO = new ControlGroupVO();
		// ControlGroup controlGroup = new ControlGroup();
		controlGroupVO.setControlgroupid(controlGroup.getControlgroupid());

		controlGroupVO.setControlgroupname(controlGroup.getControlgroupname());
		controlGroupVO.setCreatedby(controlGroup.getCreatedby());
		controlGroupVO.setCreatedon(controlGroup.getCreatedon());
		controlGroupVO.setLastupdatedby(controlGroup.getLastupdatedby());
		controlGroupVO.setLastupdatedon(controlGroup.getLastupdatedon());
		return controlGroupVO;
	}

	public List<ControlGroupVO> covertList(List<com.lotus.uccp.authentication.model.ControlGroup> doObjs,
			ControlGroupVO controlGroupVO) {
		List<ControlGroupVO> voObjs = new ArrayList<ControlGroupVO>();
		doObjs.forEach(doObj -> voObjs.add(convertToVo(doObj)));

		return voObjs;
	}

	public ConstraintVO convertToVo(Constraint cosdabacconstraint) {
		ConstraintVO cosdabacconstraintvo = new ConstraintVO();
		cosdabacconstraintvo.setAttributeconstraintid(cosdabacconstraint.getAttributeconstraintid());
		cosdabacconstraintvo.setAccessconstrainttype(cosdabacconstraint.getAccessconstrainttype());
		cosdabacconstraintvo.setAttributename(cosdabacconstraint.getAttributename());
		cosdabacconstraintvo.setCreatedby(cosdabacconstraint.getCreatedby());
		cosdabacconstraintvo.setCreatedon(cosdabacconstraint.getCreatedon());
		cosdabacconstraintvo.setAttributevalue(cosdabacconstraint.getAttributevalue());
		cosdabacconstraintvo.setLastupdatedby(cosdabacconstraint.getLastupdatedby());
		cosdabacconstraintvo.setLastupdatedon(cosdabacconstraint.getLastupdatedon());
		return cosdabacconstraintvo;
	}

	public List<ConstraintVO> covertList(List<Constraint> doObjs,
			ConstraintVO cosdabacconstraintvo) {
		List<ConstraintVO> voObjs = new ArrayList<ConstraintVO>();
		doObjs.forEach(doObj -> voObjs.add(convertToVo(doObj)));

		return voObjs;

	}

	public TransactionVO convertToVo(Transaction transaction) {
		TransactionVO transactionvo = new TransactionVO();

		// transactionvo.setControlgroupid(transaction.C);
		transactionvo.setTransactionid(transaction.getTransactionid());
		transactionvo.setTransactionname(transaction.getTransactionname());
		transactionvo.setTransactiontype(transaction.getTransactiontype());
		transactionvo.setDefaultaccess(transaction.getDefaultaccess());
		transactionvo.setCreatedby(transaction.getCreatedby());
		transactionvo.setLastupdatedby(transaction.getLastupdatedby());

		return transactionvo;
	}

	public List<TransactionVO> covertList(List<Transaction> doObjs,
			TransactionVO cosdabacconstraintvo) {
		List<TransactionVO> voObjs = new ArrayList<TransactionVO>();

		doObjs.forEach(doObj -> voObjs.add(convertToVo(doObj)));

		return voObjs;

	}

	public com.lotus.uccp.authentication.model.OrganizationUnit convertToDTO(OrganizationUnitVO organisationunit, com.lotus.uccp.authentication.model.OrganizationUnit organisationunitvo) {

		System.out.println("DTO -:: Bus code ::-" + organisationunit.getBusinesstypecode());
		System.out.println("DTO -:: Name ::-" + organisationunit.getName());

		organisationunitvo.setBusinesstypecode(organisationunit.getBusinesstypecode().toUpperCase());
		organisationunitvo.setorganizationUnitid(organisationunit.getOrganisationunitid());
		organisationunitvo.setName(organisationunit.getName().toUpperCase());
		organisationunitvo.setRecordstatus(organisationunit.getRecordstatus());
		organisationunitvo.setWebaddress(organisationunit.getWebaddress());

		return organisationunitvo;
	}

	public OrganizationUnitVO convertToVo(com.lotus.uccp.authentication.model.OrganizationUnit organisationunit) {
		OrganizationUnitVO organisationunitvo = new OrganizationUnitVO();

		organisationunitvo.setBusinesstypecode(organisationunit.getBusinesstypecode());
		organisationunitvo.setOrganisationunitid(organisationunit.getorganizationUnitid());
		organisationunitvo.setName(organisationunit.getName());
		organisationunitvo.setRecordstatus(organisationunit.getRecordstatus());
		organisationunitvo.setWebaddress(organisationunit.getWebaddress());
		return organisationunitvo;
	}

	public List<OrganizationUnitVO> covertList(List<com.lotus.uccp.authentication.model.OrganizationUnit> doObjs, OrganizationUnitVO organisationunitvo) {
		List<OrganizationUnitVO> voObjs = new ArrayList<OrganizationUnitVO>();
		doObjs.forEach(doObj -> voObjs.add(convertToVo(doObj)));

		return voObjs;
	}

	public OrgRoleJobUserVO convertToVo(OrgRoleJobUser cosdorgrolejobuser) {
		OrgRoleJobUserVO cosdorgrolejobuservo = new OrgRoleJobUserVO();

		cosdorgrolejobuservo.setRolename(cosdorgrolejobuser.getRolename());
		cosdorgrolejobuservo.setUsername(cosdorgrolejobuser.getUsername());
		cosdorgrolejobuservo.setJobid(cosdorgrolejobuser.getJobid());
		cosdorgrolejobuservo.setOrganisationunitid(cosdorgrolejobuser.getOrganisationunitid());
		cosdorgrolejobuservo.setOrgrolejobuserid(cosdorgrolejobuser.getOrgrolejobuserid());

		return cosdorgrolejobuservo;
	}

	public List<OrgRoleJobUserVO> covertList(List<OrgRoleJobUser> doObjs,
			OrgRoleJobUserVO cosdorgrolejobuser) {
		List<OrgRoleJobUserVO> voObjs = new ArrayList<OrgRoleJobUserVO>();

		doObjs.forEach(doObj -> voObjs.add(convertToVo(doObj)));

		return voObjs;
	}

	public Users convertToDTO(UsersVO usersVo) {
		Users users = new Users();

		users.setUsername(usersVo.getUsername().toLowerCase());
		users.setPassword(usersVo.getPassword());
		users.setRolename(usersVo.getRolename());
		users.setFirstname(usersVo.getFirstname());
		users.setLastname(usersVo.getLastname());
		users.setEmail(usersVo.getEmail());
		users.setOrgUnitId(usersVo.getOrgUnitId());
		users.setOrgName(usersVo.getOrgName());

		return users;
	}

	public List<UsersVO> covertList(List<Users> doObjs, UsersVO UsersVO) {
		List<UsersVO> voObjs = new ArrayList<UsersVO>();

		doObjs.forEach(doObj -> voObjs.add(convertToVo(doObj)));

		return voObjs;
	}

	public UsersVO convertToVo(Users users) {
		UsersVO usersvo = new UsersVO();

		usersvo.setUsername(users.getUsername());
		usersvo.setRolename(users.getRolename());
		usersvo.setFirstname(users.getFirstname());
		usersvo.setLastname(users.getLastname());
		usersvo.setEmail(users.getEmail());
		usersvo.setOrgUnitId(users.getOrgUnitId());
		usersvo.setOrgName(users.getOrgName());

		return usersvo;
	}

	public SecurityroleVO convertToVo(SecurityRole securityRole) {
		SecurityroleVO securityRolevo = new SecurityroleVO();

		securityRolevo.setOrgname(securityRole.getOrgName());
		securityRolevo.setRolelevel(securityRole.getRoleLevel());
		securityRolevo.setJobname(securityRole.getJobnName());
		securityRolevo.setRolename(securityRole.getRoleName());
		securityRolevo.setLastwritten(securityRole.getLastwritten());
		return securityRolevo;
	}

	public SecurityRole convertToDTO(SecurityroleVO securityRoleVO) {
		SecurityRole securityRolevo = new SecurityRole();

		securityRolevo.setOrgName(securityRoleVO.getOrgname().toUpperCase());
		/*securityRolevo.setRolelevel(securityRole.getRolelevel().toUpperCase());
		securityRolevo.setJobname(securityRole.getJobname().toUpperCase());*/
		securityRolevo.setRoleName(securityRoleVO.getRolename().toUpperCase());
		securityRolevo.setLastwritten(new Date());
		return securityRolevo;
	}

	public List<SecurityroleVO> covertList(List<SecurityRole> doObjs, SecurityroleVO securityRolevo) {
		List<SecurityroleVO> voObjs = new ArrayList<SecurityroleVO>();

		doObjs.forEach(doObj -> voObjs.add(convertToVo(doObj)));

		return voObjs;
	}

	public AuthorizationPolicySetVO convertToVo(AuthorizationPolicySet authorizationPolicySet) {
		AuthorizationPolicySetVO authorizationPolicySetvo = new AuthorizationPolicySetVO();

		authorizationPolicySetvo.setClassificationlevel(authorizationPolicySet.getClassificationlevel());
		authorizationPolicySetvo.setComments(authorizationPolicySet.getComments());
		authorizationPolicySetvo.setConsentstatus(authorizationPolicySet.getConsentstatus());
		authorizationPolicySetvo.setCreatedby(authorizationPolicySet.getCreatedby());
		authorizationPolicySetvo.setLastupdatedby(authorizationPolicySet.getLastupdatedby());
		authorizationPolicySetvo.setPolicysetid(authorizationPolicySet.getPolicysetid());
		authorizationPolicySetvo.setTransactionType(authorizationPolicySet.getTransactionType());
		authorizationPolicySetvo.setName(authorizationPolicySet.getName());
		return authorizationPolicySetvo;
	}

	public List<AuthorizationPolicySetVO> covertList(List<AuthorizationPolicySet> doObjs,
			AuthorizationPolicySetVO authorizationPolicySetvo) {
		List<AuthorizationPolicySetVO> voObjs = new ArrayList<AuthorizationPolicySetVO>();

		doObjs.forEach(doObj -> voObjs.add(convertToVo(doObj)));

		return voObjs;
	}

	public List<ReferralLAVO> covertList(List<ReferralLA> doObjs, ReferralLAVO referrallavo) {
		List<ReferralLAVO> voObjs = new ArrayList<ReferralLAVO>();
		doObjs.forEach(doObj -> voObjs.add(convertToVo(doObj)));

		return voObjs;
	}

	public ReferralLAVO convertToVo(ReferralLA referralla) {
		ReferralLAVO referrallavo = new ReferralLAVO();

		referrallavo.setReferralID(referralla.getReferralID());
		referrallavo.setClientID(referralla.getClientID());
		referrallavo.setCustomerName(referralla.getCustomer_name());
		referrallavo.setReferral_Type(referralla.getReferral_Type());
		referrallavo.setOther_info(referralla.getOther_info());
		referrallavo.setFname(referralla.getFname());
		referrallavo.setLname(referralla.getLname());
		referrallavo.setCvwName(referralla.getCvwName());
		referrallavo.setSrccode(referralla.getSrccode());
		referrallavo.setMemberID(referralla.getMemberID());
		referrallavo.setMinor(referralla.getMinor());
		referrallavo.setParolee(referralla.getParolee());
		referrallavo.setTransName(referralla.getTransName());
		return referrallavo;
	}
	
	public Job convertToDTO(JobVO jobvo) {
		
		Job job = new Job();
		job.setJobid(jobvo.getJobid());
		job.setJobdesc(jobvo.getJobdesc());
		job.setJobname(jobvo.getJobName());

		
		return job;
	}

	public List<JobVO> covertList(List<Job> doObjs, JobVO UsersVO) {
		List<JobVO> voObjs = new ArrayList<JobVO>();

		doObjs.forEach(doObj -> voObjs.add(convertToVo(doObj)));

		return voObjs;
	}

	public JobVO convertToVo(Job j) {
		
		JobVO job = new JobVO();
		job.setJobid(j.getJobid());
		job.setJobdesc(j.getJobdesc());
		job.setJobName(j.getJobname());


		return job;
	}
}
