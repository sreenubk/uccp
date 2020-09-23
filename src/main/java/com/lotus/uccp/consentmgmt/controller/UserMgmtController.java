package com.lotus.uccp.consentmgmt.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lotus.uccp.util.Message;
import com.lotus.uccp.consentmgmt.dao.JobDao;
import com.lotus.uccp.consentmgmt.dao.OrganizationUnitDao;
import com.lotus.uccp.consentmgmt.dao.SecurityroleDao;
import com.lotus.uccp.consentmgmt.dao.UsersDao;
import com.lotus.uccp.consentmgmt.dto.Job;
import com.lotus.uccp.authentication.model.OrganizationUnit;
import com.lotus.uccp.authentication.model.SecurityRole;
import com.lotus.uccp.authentication.model.Users;
import com.lotus.uccp.util.Converters;
import com.lotus.uccp.vo.JobVO;
import com.lotus.uccp.vo.OrganizationUnitVO;
import com.lotus.uccp.vo.SecurityroleVO;
import com.lotus.uccp.vo.UsersVO;

@RestController
@RequestMapping("/UserMgmt")
public class UserMgmtController {

	public static final Logger logger = LoggerFactory.getLogger(UserMgmtController.class);

	/* OrganisationUnit Entity - Start */
	@RequestMapping(value = "/getOrgListWithID/", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<OrganizationUnitVO> getOrgListWithID(@RequestBody OrganizationUnitVO vo) {
		
		logger.info("::getOrgListWithID ::");

		OrganizationUnitDao dao = new OrganizationUnitDao();
		com.lotus.uccp.authentication.model.OrganizationUnit dto = dao.getById(vo.getOrganisationunitid().longValue());

		Converters conv = new Converters();
		vo = conv.convertToVo(dto);
		logger.info("::-- Organisation Name --::" + vo.getName());
		return new ResponseEntity<OrganizationUnitVO>(vo, HttpStatus.OK);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/getOrgListWithName/", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<List<OrganizationUnitVO>> getOrgListWithName(@RequestBody OrganizationUnitVO vo) {
		
		logger.info("::getOrgListWithName ::");

		OrganizationUnitDao dao = new OrganizationUnitDao();
		List<OrganizationUnit> dtoList = dao.getlistByGroupName(vo.getName().toUpperCase());

		if (dtoList.isEmpty()) {
			return new ResponseEntity(new Message("Organisationunit List is Empty"), HttpStatus.OK);
		}

		Converters conv = new Converters();

		List<OrganizationUnitVO> voList = conv.covertList(dtoList, vo);
		logger.info("::getOrgListWithName ::" + voList.size());

		return new ResponseEntity<List<OrganizationUnitVO>>(voList, HttpStatus.OK);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/getListOrg/", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<OrganizationUnitVO>> getOrgList() {
		
		logger.info(":: getOrgList ::");
		
		OrganizationUnitVO vo = new OrganizationUnitVO();
		OrganizationUnitDao dao = new OrganizationUnitDao();
		List<OrganizationUnit> dtoList = dao.getlist();

		if (dtoList.isEmpty()) {
			return new ResponseEntity(new Message("Organisationunit List is Empty"), HttpStatus.OK);
		}

		Converters conv = new Converters();

		List<OrganizationUnitVO> voList = conv.covertList(dtoList, vo);
		logger.info("::getOrgListWithName ::" + voList.size());

		return new ResponseEntity<List<OrganizationUnitVO>>(voList, HttpStatus.OK);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/saveOrgUnit/", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<OrganizationUnitVO> saveOrgUnit(@RequestBody OrganizationUnitVO vo) {
		
		logger.info(":: saveOrgUnit ::");

		Converters conv = new Converters();
		OrganizationUnit dto = new OrganizationUnit();
		dto = conv.convertToDTO(vo, dto);

		OrganizationUnitDao dao = new OrganizationUnitDao();
		dto = dao.save(dto);
		if (dto == null) {
			return new ResponseEntity(new Message("Save Failed"), HttpStatus.OK);
		}
		vo = conv.convertToVo(dto);
		return new ResponseEntity<OrganizationUnitVO>(vo, HttpStatus.OK);
	}
	// /UserMgmt/updateorgUnit/
	// /UserMgmt/updateorgUnit/
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/updateOrgUnit/", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<OrganizationUnitVO> updateOrgUnit(@RequestBody OrganizationUnitVO vo) {
		
		logger.info("===:: updateOrgUnit ::===");
		
		OrganizationUnit dto = new OrganizationUnit();
		Converters conv = new Converters();
		OrganizationUnitDao dao = new OrganizationUnitDao();

		dto = conv.convertToDTO(vo, dto);

		if (!dao.update(dto)) {
			return new ResponseEntity(new Message("Update Failed"), HttpStatus.OK);
		}

		return new ResponseEntity<OrganizationUnitVO>(vo, HttpStatus.OK);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/deleteOrgUnit/", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<OrganizationUnitVO> deleteOrgUnit(@RequestBody OrganizationUnitVO vo) {
		
		logger.info(":: deleteOrgUnit ::");

		OrganizationUnitDao dao = new OrganizationUnitDao();

		if (dao.delete(vo.getOrganisationunitid().longValue())) {
			return new ResponseEntity(new Message("Success"), HttpStatus.OK);
		} else {
			return new ResponseEntity(new Message("Failed"), HttpStatus.OK);
		}
	}
	/* OrganisationUnit Entity - End */
	
	/* SecurityRole Entity - End */
	@RequestMapping(value = "/getRoleListWithName/", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<List<SecurityroleVO>> getRoleListWithName(@RequestBody SecurityroleVO vo) {
		
		logger.info("::getRoleListWithName ::");

		SecurityroleDao dao = new SecurityroleDao();
		List<SecurityRole> dtoList = dao.getlistByGroupName(vo.getRolename().toUpperCase());

		Converters conv = new Converters();
		List<SecurityroleVO> voList = conv.covertList(dtoList, vo);
		
		return new ResponseEntity<List<SecurityroleVO>>(voList, HttpStatus.OK);
	}

	@RequestMapping(value = "/getRoleWithName/", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<SecurityroleVO> getRoleWithName(@RequestBody SecurityroleVO vo) {
		
		logger.info("::getRoleWithName ::");

		SecurityroleDao dao = new SecurityroleDao();
		SecurityRole dto = dao.getById(vo.getRolename());

		Converters conv = new Converters();
		vo = conv.convertToVo(dto);
		
		return new ResponseEntity<SecurityroleVO>(vo, HttpStatus.OK);
	}

	@RequestMapping(value = "/getRoleList/", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<SecurityroleVO>> getRoleList() {
		
		logger.info(":: getRoleList ::");
		
		SecurityroleVO vo = new SecurityroleVO();
		SecurityroleDao dao = new SecurityroleDao();
		List<SecurityRole> dtoList = dao.getlist();

		if (dtoList.isEmpty()) {
			return new ResponseEntity(new Message("Role List empty"), HttpStatus.OK);
		}

		Converters conv = new Converters();

		List<SecurityroleVO> voList = conv.covertList(dtoList, vo);

		return new ResponseEntity<List<SecurityroleVO>>(voList, HttpStatus.OK);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/saveRole/", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<SecurityroleVO> saveRole(@RequestBody SecurityroleVO vo) {
		
		logger.info(":: saveRole ::");

		Converters conv = new Converters();
		SecurityRole dto = conv.convertToDTO(vo);
		SecurityroleDao dao = new SecurityroleDao();
		dto =dao.save(dto);
		vo = conv.convertToVo(dto);
		if (dto == null) {
			return new ResponseEntity(new Message("Save Failed"), HttpStatus.OK);
		}

		return new ResponseEntity<SecurityroleVO>(vo, HttpStatus.OK);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/updateRole/", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<SecurityRole> updateRole(@RequestBody SecurityroleVO vo) {
		
		logger.info(":: updateRole ::");

		Converters conv = new Converters();
		SecurityRole dto = conv.convertToDTO(vo);
		SecurityroleDao dao = new SecurityroleDao();

		if (dao.update(dto)) {
			return new ResponseEntity(new Message("Updated"), HttpStatus.OK);
		} else {
			return new ResponseEntity(new Message("Failed"), HttpStatus.OK);
		}

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/deleteRole/", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<OrganizationUnitVO> deleteRole(@RequestBody SecurityroleVO vo) {
		
		logger.info(":: deleteRole ::");

		SecurityroleDao dao = new SecurityroleDao();

		if (dao.delete(vo.getRolename())) {
			return new ResponseEntity(new Message("Success"), HttpStatus.OK);
		} else {
			return new ResponseEntity(new Message("Failed"), HttpStatus.OK);
		}

	}	
	/* SecurityRole Entity - End */

	/* Users Entity - START */
	@RequestMapping(value = "/saveUser/", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<UsersVO> saveUser(@RequestBody UsersVO uservo) {
		System.out.println(":: saveRole ::");

		Converters conv = new Converters();
		Users user = conv.convertToDTO(uservo);
		UsersDao usersdao = new UsersDao();
		usersdao.save(user);

		return new ResponseEntity<UsersVO>(uservo, HttpStatus.OK);
	}

	@RequestMapping(value = "/getUserList/", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<UsersVO>> getUserList() {
		UsersDao usersdao = new UsersDao();
		UsersVO usersVO = new UsersVO();
		Converters conv = new Converters();
		List<Users> usersList = usersdao.getlist();
		List<UsersVO> usersVoList = conv.covertList(usersList, usersVO);
		return new ResponseEntity<List<UsersVO>>(usersVoList, HttpStatus.OK);

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/getUserWithName/", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<UsersVO> getUserWithName(@RequestBody UsersVO usersvo) {
		logger.info("::getUserWithName ::");

		UsersDao usersdao = new UsersDao();
		Users user = usersdao.getById(usersvo.getUsername());

		if (user == null) {
			return new ResponseEntity(new Message("User with given name not found"), HttpStatus.OK);
		}

		Converters conv = new Converters();
		usersvo = conv.convertToVo(user);

		return new ResponseEntity<UsersVO>(usersvo, HttpStatus.OK);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/getUserListWithName/", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<List<UsersVO>> getUserListWithName(@RequestBody UsersVO usersvo) {
		logger.info("::getUserListWithName ::");

		UsersDao usersdao = new UsersDao();
		List<Users> userlist = usersdao.getlistByName(usersvo.getUsername().toLowerCase());

		if (userlist.isEmpty()) {
			return new ResponseEntity(new Message("User List is Empty"), HttpStatus.OK);
		}

		Converters conv = new Converters();

		List<UsersVO> usersVoList = conv.covertList(userlist, usersvo);
		return new ResponseEntity<List<UsersVO>>(usersVoList, HttpStatus.OK);

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/updateUser/", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<?> updateUser(@RequestBody UsersVO uservo) {
		System.out.println(":: updateUser ::");

		Converters conv = new Converters();
		Users user = conv.convertToDTO(uservo);
		UsersDao usersdao = new UsersDao();

		if (usersdao.update(user)) {
			return new ResponseEntity(new Message("Updated"), HttpStatus.OK);
		} else {
			return new ResponseEntity(new Message("fail"), HttpStatus.OK);
		}

	}
		
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/deleteUser/", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<?> deleteUser(@RequestBody UsersVO vo) {

		logger.info(":: deleteUser ::");

		UsersDao dao = new UsersDao();

		if (dao.delete(vo.getUsername())) {
			return new ResponseEntity(new Message("Success"), HttpStatus.OK);
		} else {
			return new ResponseEntity(new Message("Failed"), HttpStatus.OK);
		}

	}
	/* Users Entity - END */
	
	/*Job*/
	
	/* Job Entity - START */
	@RequestMapping(value = "/saveJob/", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<JobVO> saveJob(@RequestBody JobVO uservo) {
		
		logger.info("::saveJob ::");

		Converters conv = new Converters();
		Job user = conv.convertToDTO(uservo);
		JobDao usersdao = new JobDao();
		user= usersdao.save(user);
		uservo = conv.convertToVo(user);
		return new ResponseEntity<JobVO>(uservo, HttpStatus.OK);
	}

	@RequestMapping(value = "/getJobList/", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<JobVO>> getJobList() {
		
		logger.info("::getJobList ::");
		
		JobDao usersdao = new JobDao();
		JobVO usersVO = new JobVO();
		Converters conv = new Converters();
		List<Job> usersList = usersdao.getlist();
		List<JobVO> usersVoList = conv.covertList(usersList, usersVO);
		return new ResponseEntity<List<JobVO>>(usersVoList, HttpStatus.OK);

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/getJobWithID/", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<JobVO> getJobWithName(@RequestBody JobVO usersvo) {
		
		logger.info("::getJobWithName ::");
		long jobid = usersvo.getJobid().longValue();

		JobDao usersdao = new JobDao();
		Job user = usersdao.getById(usersvo.getJobid().longValue());

		if (user == null) {
			return new ResponseEntity(new Message("Job with given name not found"), HttpStatus.OK);
		}

		Converters conv = new Converters();
		usersvo = conv.convertToVo(user);

		return new ResponseEntity<JobVO>(usersvo, HttpStatus.OK);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/getJobListWithName/", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<List<JobVO>> getJobListWithName(@RequestBody JobVO usersvo) {
		
		logger.info("::getJobListWithName ::");

		JobDao usersdao = new JobDao();
		List<Job> userlist = usersdao.getlistwithName(usersvo.getJobName().toUpperCase());

		if (userlist.isEmpty()) {
			return new ResponseEntity(new Message("Job List is Empty"), HttpStatus.OK);
		}

		Converters conv = new Converters();

		List<JobVO> usersVoList = conv.covertList(userlist, usersvo);
		return new ResponseEntity<List<JobVO>>(usersVoList, HttpStatus.OK);

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/updateJob/", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<?> updateJob(@RequestBody JobVO uservo) {
		
		logger.info("::getJobListWithName ::");

		Converters conv = new Converters();
		Job user = conv.convertToDTO(uservo);
		JobDao usersdao = new JobDao();

		if (usersdao.update(user).equalsIgnoreCase("Updated")) {
			return new ResponseEntity(new Message("Updated"), HttpStatus.OK);
		} else {
			return new ResponseEntity(new Message("fail"), HttpStatus.OK);
		}

	}
		
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/deleteJob/", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<?> deleteJob(@RequestBody JobVO vo) {

		logger.info(":: deleteJob ::");

		JobDao dao = new JobDao();
		String result = dao.delete(vo.getJobid().longValue());

		if (result.equalsIgnoreCase("deleted")) {
			return new ResponseEntity(new Message("Success"), HttpStatus.OK);
		} else {
			return new ResponseEntity(new Message("Failed"), HttpStatus.OK);
		}

	}
}
