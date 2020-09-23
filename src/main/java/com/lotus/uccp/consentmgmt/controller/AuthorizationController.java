package com.lotus.uccp.consentmgmt.controller;

import java.util.HashMap;
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
import com.lotus.uccp.consentmgmt.dao.UsersDao;
import com.lotus.uccp.authentication.model.Users;
import com.lotus.uccp.consentmgmt.service.ConsentMgmtService;
import com.lotus.uccp.consentmgmt.service.LdapService;
import com.lotus.uccp.util.Converters;
import com.lotus.uccp.vo.ConstraintVO;
import com.lotus.uccp.vo.ControlGroupVO;
import com.lotus.uccp.vo.ProgramaticDataInput;
import com.lotus.uccp.vo.UsersVO;

@RestController
@RequestMapping("/pwdcontrol")
public class AuthorizationController {

	public static final Logger logger = LoggerFactory.getLogger(AuthorizationController.class);

	@RequestMapping(value = "/getAuthticate/", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<UsersVO> getAuthticate(@RequestBody UsersVO usersvo) {
		UsersDao Usersdao = new UsersDao();
		String user = usersvo.getUsername();
		String password = usersvo.getPassword();

		Users userdo = Usersdao.getById(user);
		if (userdo == null) {
			usersvo.setResult("false");
			return new ResponseEntity<UsersVO>(usersvo, HttpStatus.OK);
		}
		String result = userdo.getPassword().equals(password) ? "true" : "false";
		usersvo.setResult(result);
		if (result.equals("true"))
			usersvo.setUserRole(userdo.getRolename());
		return new ResponseEntity<UsersVO>(usersvo, HttpStatus.OK);
	}

	@RequestMapping(value = "/getListUser/", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<List<UsersVO>> getListUser() {
		UsersDao Usersdao = new UsersDao();
		UsersVO UsersVO = new UsersVO();
		Converters conv = new Converters();
		List<Users> UsersList = Usersdao.getlist();
		List<UsersVO> cosdabacconstraintVoList = conv.covertList(UsersList, UsersVO);
		return new ResponseEntity<List<UsersVO>>(cosdabacconstraintVoList, HttpStatus.OK);

	}

	@RequestMapping(value = "/getConsentResult/", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<HashMap> getConsentResult(@RequestBody ProgramaticDataInput pgmInput) {
		ConsentMgmtService consentService = new ConsentMgmtService();
		HashMap<String, String> hm = new HashMap();

		hm = consentService.getConsentResult(pgmInput);
		return new ResponseEntity<HashMap>(hm, HttpStatus.OK);
	}

	@RequestMapping(value = "/getpgmInput/", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<ProgramaticDataInput> getpgmInput() {
		ProgramaticDataInput pgmInput = new ProgramaticDataInput();
		return new ResponseEntity<ProgramaticDataInput>(pgmInput, HttpStatus.OK);
	}

	@RequestMapping(value = "/ldapAuthticate/", method = RequestMethod.POST)
	public ResponseEntity<?> ldapAuthticate(@RequestBody UsersVO usersvo) {
		String result = "fail";
		String rolelevelPermission = "deny";
		LdapService ldapService = new LdapService();
		try {

			// result = ldapService.getldapAuthticate(usersvo);
			// if(result !=null&& result.equalsIgnoreCase("sucess"))
			// rolelevelPermission = ldapService.checkPrivilege(usersvo.getUsername());
			result = "sucess";
			rolelevelPermission = "allow";

			System.out.println("##Auth - Result :: " + result + "permission - " + rolelevelPermission);
		} catch (Exception ex) {
			System.out.println(ex);
		}
		return new ResponseEntity(new Message(result, rolelevelPermission), HttpStatus.OK);
	}
}
