//package com.lotus.uccp.consentmgmt.controller;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.LinkedHashMap;
//import java.util.List;
//
//import javax.servlet.http.HttpServletRequest;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.lotus.uccp.util.Message;
//import com.lotus.uccp.util.ODSUtil;
//import com.lotus.uccp.util.PersonSearchUtil;
//import com.lotus.uccp.util.WCMConverter;
//
//import com.lotus.uccp.consentmgmt.wcm.dao.ReferralPersonLADao;
//import com.lotus.uccp.consentmgmt.wcm.dto.ReferralPersonLADto;
//import com.lotus.uccp.wcm.vo.PersonSearchVo;
//import com.lotus.uccp.wcm.vo.ReferralPersonLAVo;
//
//
//@RestController
//@RequestMapping("/PersonSearch")
//public class PersonSearchMDM {
//
//	public static final Logger logger = LoggerFactory.getLogger(PersonSearchMDM.class);
//
//	/*---------------Services for MDM Person Search - START------------------*/
//
//	@SuppressWarnings({ "unchecked", "rawtypes" })
//	@RequestMapping(value = "/searchForIdList", method = RequestMethod.POST, produces = "application/json")
//	public ResponseEntity<List<IdEntitySearchResult>> searchForIdList(@RequestBody PersonSearchVo personSearchVo) {
//
//		System.out.println(":: start searchForIdList ##::");
//
//		/*------------- INPUTS - START--------------*/
//		System.out.println(":: Input First Name ::" + personSearchVo.getFirstName());
//		System.out.println(":: Input Middle Name ::" + personSearchVo.getMiddleName());
//		System.out.println(":: Input Last Name ::" + personSearchVo.getLastName());
//		System.out.println(":: Input SSN ::" + personSearchVo.getSsn());
//		System.out.println(":: Input Address ::" + personSearchVo.getAddress());
//		System.out.println(":: Input City ::" + personSearchVo.getCity());
//		System.out.println(":: Input State::" + personSearchVo.getState());
//		System.out.println(":: Input Zip ::" + personSearchVo.getZipCode());
//		System.out.println(":: Input Date of Birth ::" + personSearchVo.getDateofBirth());
//		System.out.println(":: Input Phone ::" + personSearchVo.getPhone());
//		System.out.println(":: Input Gender ::" + personSearchVo.getGender());
//
//		List<IdEntitySearchResult> idEntitySearchResults = null;
//		List<IdEntitySearchResult> idEntitySearchNewResults = new ArrayList<IdEntitySearchResult>();
//
//		try {
//
//			MasterDataWebServiceService service = new MasterDataWebServiceService();
//			MasterDataWebService mds = service.getMasterDataWebServicePort();
//
//			// Name
//			Memlngnm inputName = new Memlngnm();
//			inputName.setFirstName(personSearchVo.getFirstName());
//			inputName.setMiddleName(personSearchVo.getMiddleName());
//			inputName.setLastName(personSearchVo.getLastName());
//
//			ClientNameList clientNameList = new ClientNameList();
//			clientNameList.getClientName().add(inputName);
//
//			// Gender
//			Memattr inputGender = new Memattr();
//			inputGender.setAttrVal(personSearchVo.getGender());
//
//			GenderList genderList = new GenderList();
//			genderList.getGender().add(inputGender);
//
//			// Date of Birth
//			Memdate inputDOB = new Memdate();
//			inputDOB.setDateVal(personSearchVo.getDateofBirth());
//
//			DateofBirthList dateofBirthList = new DateofBirthList();
//			dateofBirthList.getDateofBirth().add(inputDOB);
//
//			// SSN
//			Memident inputSSN = new Memident();
//			inputSSN.setIdNumber(personSearchVo.getSsn());
//			inputSSN.setIdIssuer("SSA");
//
//			SSNList ssnList = new SSNList();
//			ssnList.getSSN().add(inputSSN);
//
//			// Address - StreetLine1
//			Memlgaddr inputAddr = new Memlgaddr();
//			inputAddr.setStreetLine1(personSearchVo.getAddress());
//			inputAddr.setCity(personSearchVo.getCity());
//			inputAddr.setState(personSearchVo.getState());
//			inputAddr.setZipCode(personSearchVo.getZipCode());
//
//			// HomeAddress
//			HomeAddressList homeAddrList = new HomeAddressList();
//			homeAddrList.getHomeAddress().add(inputAddr);
//			// MailingAddress
//			MailingAddressList mailingAddrList = new MailingAddressList();
//			mailingAddrList.getMailingAddress().add(inputAddr);
//			// WorkAddress
//			WorkAddressList workAddrList = new WorkAddressList();
//			workAddrList.getWorkAddress().add(inputAddr);
//			// OtherAddress
//			OtherAddressList otherAddrList = new OtherAddressList();
//			otherAddrList.getOtherAddress().add(inputAddr);
//
//
//			// Phone
//			Memphone inputPhone = new Memphone();
//			inputPhone.setPhNumber(personSearchVo.getPhone());
//
//			// HomePhone
//			HomePhoneList homePhoneList = new HomePhoneList();
//			homePhoneList.getHomePhone().add(inputPhone);
//			// MobilePhone
//			MobilePhoneList mobilePhoneList = new MobilePhoneList();
//			mobilePhoneList.getMobilePhone().add(inputPhone);
//			// WorkPhone
//			WorkPhoneList workPhoneList = new WorkPhoneList();
//			workPhoneList.getWorkPhone().add(inputPhone);
//			// OtherPhone
//			OtherPhoneList otherPhoneList = new OtherPhoneList();
//			otherPhoneList.getOtherPhone().add(inputPhone);
//
//
//			// Bean
//			Client clientBean = new Client();
//			clientBean.setClientNameList(clientNameList);
//			clientBean.setGenderList(genderList);
//			clientBean.setDateofBirthList(dateofBirthList);
//			clientBean.setSSNList(ssnList);
//			clientBean.setHomeAddressList(homeAddrList);
//			clientBean.setMailingAddressList(mailingAddrList);
//			clientBean.setWorkAddressList(workAddrList);
//			clientBean.setOtherAddressList(otherAddrList);
//			clientBean.setHomePhoneList(homePhoneList);
//			clientBean.setMobilePhoneList(mobilePhoneList);
//			clientBean.setMobilePhoneList(mobilePhoneList);
//			clientBean.setWorkPhoneList(workPhoneList);
//			clientBean.setOtherPhoneList(otherPhoneList);
//
//
//			idEntitySearchResults = mds.searchForIdList(clientBean, (short) 0, 100, null, null, null);
//			System.out.println(" Entity List Size ::" + idEntitySearchResults.size());
//
//			if (idEntitySearchResults.isEmpty()) {
//				return new ResponseEntity(new Message("No records found matching search criteria"), HttpStatus.OK);
//			} else {
//				// Get latest records based on maudRecNum
//
//				for (IdEntitySearchResult idEntitySearchResult : idEntitySearchResults) {
//
//					IdEntitySearchResult idEntitySearchNewResult = new IdEntitySearchResult();
//					IdEntity idEntity = new IdEntity();
//
//					Client client = new Client();
//					client.setClientNameList(idEntitySearchResult.getIdEntity().getClient().getClientNameList());
//					client.setGenderList(idEntitySearchResult.getIdEntity().getClient().getGenderList());
//					client.setDateofBirthList(idEntitySearchResult.getIdEntity().getClient().getDateofBirthList());
//					client.setSSNList(idEntitySearchResult.getIdEntity().getClient().getSSNList());
//					client.setHomePhoneList(idEntitySearchResult.getIdEntity().getClient().getHomePhoneList());
//					client.setMobilePhoneList(idEntitySearchResult.getIdEntity().getClient().getMobilePhoneList());
//					client.setWorkPhoneList(idEntitySearchResult.getIdEntity().getClient().getWorkPhoneList());
//					client.setOtherPhoneList(idEntitySearchResult.getIdEntity().getClient().getOtherPhoneList());
//					client.setHomeAddressList(idEntitySearchResult.getIdEntity().getClient().getHomeAddressList());
//					client.setMailingAddressList(idEntitySearchResult.getIdEntity().getClient().getMailingAddressList());
//					client.setWorkAddressList(idEntitySearchResult.getIdEntity().getClient().getWorkAddressList());
//					client.setOtherAddressList(idEntitySearchResult.getIdEntity().getClient().getOtherAddressList());
//					client.setPrivilegedRecordList(idEntitySearchResult.getIdEntity().getClient().getPrivilegedRecordList());
//
//					ClientIdList clientIdList = idEntitySearchResult.getIdEntity().getClientIdList();
//					IdEntityId entityId = idEntitySearchResult.getIdEntity().getEntityId();
//
//					idEntity.setClient(client);
//					idEntity.setClientIdList(clientIdList);
//					idEntity.setEntityId(entityId);
//
//					idEntitySearchNewResult.setScore(idEntitySearchResult.getScore());
//					idEntitySearchNewResult.setIdEntity(idEntity);
//
//					idEntitySearchNewResults.add(idEntitySearchNewResult);
//				}
//
//				idEntitySearchNewResults = PersonSearchUtil.getFilteredData(idEntitySearchNewResults);
//			}
//
//		} catch (MasterDataServiceException_Exception exception) {
//			return new ResponseEntity(new Message(exception.getFaultInfo().getErrText()), HttpStatus.OK);
//		}
//
//		return new ResponseEntity<List<IdEntitySearchResult>>(idEntitySearchNewResults, HttpStatus.OK);
//	}
//
//	@SuppressWarnings({ "unchecked", "rawtypes" })
//	@RequestMapping(value = "/getIdEntityList", method = RequestMethod.POST, produces = "application/json")
//	public ResponseEntity<List<IdEntitySearchResult>> getIdEntityList(@RequestBody PersonSearchVo personSearchVo) {
//
//		System.out.println(":: getIdEntityList ##::");
//
//		/*------------- INPUTS - START--------------*/
//		System.out.println(":: Input memIdnum ::" + personSearchVo.getMemIdnum());
//		System.out.println(":: Input srcCode ::" + personSearchVo.getSrcCode());
//
//		List<IdEntity> idEntityList = null;
//		List<IdEntitySearchResult> idEntitySearchResults = null;
//
//		try {
//
//			MasterDataWebServiceService service = new MasterDataWebServiceService();
//			MasterDataWebService mds = service.getMasterDataWebServicePort();
//
//			ClientId clientId = new ClientId();
//			clientId.setMemIdnum(personSearchVo.getMemIdnum());
//			clientId.setSrcCode(personSearchVo.getSrcCode());
//
//			List<ClientId> clientIdList = new ArrayList<ClientId>();
//			clientIdList.add(clientId);
//
//			/*------------- INPUTS - END--------------*/
//
//			/*------------- EXECUTE - START--------------*/
//			idEntityList = mds.getIdEntityList(clientIdList, null, null, null);
//			System.out.println(" IdEntityList.size() ::" + idEntityList.size());
//
//			if (idEntityList.isEmpty()) {
//				return new ResponseEntity(new Message("No records found matching search criteria"), HttpStatus.OK);
//			} else {
//				// Filter Golden Records
//				idEntitySearchResults = new ArrayList<IdEntitySearchResult>();
//				IdEntitySearchResult idEntitySearchResult = new IdEntitySearchResult();
//				idEntitySearchResult.setIdEntity(idEntityList.get(0));
//				idEntitySearchResults.add(idEntitySearchResult);
//				idEntitySearchResults = PersonSearchUtil.getFilteredData(idEntitySearchResults);
//
//				/*personSearchVo.setFirstName(idEntitySearchResults.get(0).getIdEntity().getClient().getClientNameList().getClientName().get(0).getFirstName());
//				personSearchVo.setMiddleName(idEntitySearchResults.get(0).getIdEntity().getClient().getClientNameList().getClientName().get(0).getMiddleName());
//				personSearchVo.setLastName(idEntitySearchResults.get(0).getIdEntity().getClient().getClientNameList().getClientName().get(0).getLastName());
//				personSearchVo.setSsn(idEntitySearchResults.get(0).getIdEntity().getClient().getSSNList().getSSN().get(0).getIdNumber());
//				personSearchVo.setAddress(idEntitySearchResults.get(0).getIdEntity().getClient().getHomeAddressList().getHomeAddress().get(0).getStreetLine1());
//				personSearchVo.setCity(idEntitySearchResults.get(0).getIdEntity().getClient().getHomeAddressList().getHomeAddress().get(0).getCity());
//				personSearchVo.setState(idEntitySearchResults.get(0).getIdEntity().getClient().getHomeAddressList().getHomeAddress().get(0).getState());
//				personSearchVo.setZipCode(idEntitySearchResults.get(0).getIdEntity().getClient().getHomeAddressList().getHomeAddress().get(0).getZipCode());
//				personSearchVo.setDateofBirth(idEntitySearchResults.get(0).getIdEntity().getClient().getDateofBirthList().getDateofBirth().get(0).getDateVal());
//				personSearchVo.setGender(idEntitySearchResults.get(0).getIdEntity().getClient().getGenderList().getGender().get(0).getAttrVal());
//				personSearchVo.setPhone(idEntitySearchResults.get(0).getIdEntity().getClient().getHomePhoneList().getHomePhone().get(0).getPhNumber());
//
//				String score = getScore(personSearchVo);*/
//			}
//			/*------------- EXECUTE - END--------------*/
//
//		} catch (MasterDataServiceException_Exception exception) {
//			return new ResponseEntity(new Message(exception.getFaultInfo().getErrText()), HttpStatus.OK);
//		}
//
//		return new ResponseEntity<List<IdEntitySearchResult>>(idEntitySearchResults, HttpStatus.OK);
//	}
//
//	/*private String getScore(PersonSearchVo personSearchVo) {
//		String score = "0";
//		searchForIdList(personSearchVo);
//
//		return score;
//	}
//	*/
//
//	@SuppressWarnings({ "unchecked", "rawtypes" })
//	@RequestMapping(value = "/getIdEntityListByIdEntityIds", method = RequestMethod.POST, produces = "application/json")
//	public ResponseEntity<IdEntity> getIdEntityListByIdEntityIds(@RequestBody PersonSearchVo personSearchVo) {
//
//		System.out.println(":: getIdEntityListByIdEntityIds ##::");
//
//		/*------------- INPUTS - START--------------*/
//		System.out.println(":: Input EnterpriseId ::" + personSearchVo.getEnterpriseId());
//
//		List<IdEntity> idEntityList = null;
//		IdEntity idEntity = null;
//
//		try {
//
//			MasterDataWebServiceService service = new MasterDataWebServiceService();
//			MasterDataWebService mds = service.getMasterDataWebServicePort();
//
//			IdEntityId idEntityId = new IdEntityId();
//			idEntityId.setEnterpriseId(new Long(personSearchVo.getEnterpriseId()));
//
//			List<IdEntityId> idEntityIdList = new ArrayList<IdEntityId>();
//			idEntityIdList.add(idEntityId);
//
//			/*------------- INPUTS - END--------------*/
//
//			/*------------- EXECUTE - START--------------*/
//			idEntityList = mds.getIdEntityListByIdEntityIds(idEntityIdList, null, null, null);
//
//			System.out.println(" IdEntityList.size() ::" + idEntityList.size());
//
//			if (idEntityList.isEmpty()) {
//				return new ResponseEntity(new Message("NO_RECORDS_FOUND"), HttpStatus.OK);
//			} else {
//				// Filter Golden Records
//				List<IdEntitySearchResult> idEntitySearchResults = new ArrayList<IdEntitySearchResult>();
//				IdEntitySearchResult idEntitySearchResult = new IdEntitySearchResult();
//				idEntitySearchResult.setIdEntity(idEntityList.get(0));
//				idEntitySearchResults.add(idEntitySearchResult);
//				idEntitySearchResults = PersonSearchUtil.getFilteredData(idEntitySearchResults);
//				idEntity = idEntitySearchResults.get(0).getIdEntity();
//			}
//			/*------------- EXECUTE - END--------------*/
//
//		} catch (Exception ex) {
//			System.out.println("Exception ex");
//			ex.printStackTrace();
//		}
//
//		return new ResponseEntity<IdEntity>(idEntity, HttpStatus.OK);
//	}
//
//	@SuppressWarnings({ "unchecked", "rawtypes" })
//	@RequestMapping(value = "/getClientList", method = RequestMethod.POST, produces = "application/json")
//	public ResponseEntity<HashMap> getClientList(@RequestBody PersonSearchVo personSearchVo) {
//
//		System.out.println(":: getClientList ##::");
//
//		/*------------- INPUTS - START--------------*/
//		System.out.println(":: Input memIdnum ::" + personSearchVo.getMemIdnum());
//		System.out.println(":: Input srcCode ::" + personSearchVo.getSrcCode());
//
//		HashMap personSearchData = new HashMap<>();
//		List<Client> clientList = null;
//
//		try {
//
//			MasterDataWebServiceService service = new MasterDataWebServiceService();
//			MasterDataWebService mds = service.getMasterDataWebServicePort();
//
//			ClientId clientId = new ClientId();
//			clientId.setMemIdnum(personSearchVo.getMemIdnum());
//			clientId.setSrcCode(personSearchVo.getSrcCode());
//
//			List<ClientId> clientIdList = new ArrayList<ClientId>();
//			clientIdList.add(clientId);
//
//			/*------------- INPUTS - END--------------*/
//
//			/*------------- EXECUTE - START--------------*/
//			clientList = mds.getClientList(clientIdList, null, null, null);
//
//			System.out.println(" clientList.size() ::" + clientList.size());
//
//			if (clientList.isEmpty()) {
//				return new ResponseEntity(new Message("NO_RECORDS_FOUND"), HttpStatus.OK);
//			} else {
//				personSearchData.put("MDMData", clientList);
//			}
//
//			LinkedHashMap odsData = ODSUtil.getODSData(personSearchVo.getMemIdnum(), personSearchVo.getSrcCode());
//			if (odsData == null) {
//				personSearchData.put("ODSData", "null");
//			} else {
//				personSearchData.put("ODSData", odsData);
//			}
//
//			/*------------- EXECUTE - END--------------*/
//
//		} catch (Exception ex) {
//			System.out.println("Exception ex");
//			ex.printStackTrace();
//		}
//
//		return new ResponseEntity<HashMap>(personSearchData, HttpStatus.OK);
//	}
//	/*---------------Services for MDM Person Search - END------------------*/
//
//	/*---------------Services for ReferralPersonLA - Start------------------*/
//
//	@SuppressWarnings({ "unchecked", "rawtypes" })
//	@RequestMapping(value = "/saveReferralPersonLA/", method = RequestMethod.POST, produces = "application/json")
//	public ResponseEntity<ReferralPersonLAVo> saveReferralPersonLA(@RequestBody ReferralPersonLAVo vo,
//			HttpServletRequest request) {
//
//		System.out.println(":: saveReferralPersonLA ::");
//		ReferralPersonLADao dao = new ReferralPersonLADao();
//		WCMConverter conv = new WCMConverter();
//		ReferralPersonLADto dto = conv.convertToDto(vo);
//
//		dto = dao.save(dto);
//		if (dto == null) {
//			return new ResponseEntity(new Message("FAILED"), HttpStatus.OK);
//		}
//
//		vo = conv.convertToVo(dto);
//
//		System.out.println(":: saved ::");
//
//		return new ResponseEntity<ReferralPersonLAVo>(vo, HttpStatus.OK);
//	}
//
//	@SuppressWarnings({ "unchecked", "rawtypes" })
//	@RequestMapping(value = "/listAllReferralPersonLA/", method = RequestMethod.POST, produces = "application/json")
//	public ResponseEntity<List<ReferralPersonLAVo>> listAllReferralPersonLA() {
//		System.out.println(":: listAllReferralPersonLA() ::");
//		ReferralPersonLADao dao = new ReferralPersonLADao();
//
//		List<ReferralPersonLADto> list = dao.getlist();
//		if (list == null) {
//			return new ResponseEntity(new Message("NO HISTORY"), HttpStatus.OK);
//		}
//
//		WCMConverter conv = new WCMConverter();
//		List<ReferralPersonLAVo> voList = conv.convertList(list);
//
//		return new ResponseEntity<List<ReferralPersonLAVo>>(voList, HttpStatus.OK);
//	}
//
//	/*---------------Services for ReferralPersonLA - End------------------*/
//}
