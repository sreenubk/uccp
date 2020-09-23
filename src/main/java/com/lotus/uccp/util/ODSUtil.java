package com.lotus.uccp.util;

import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.http.ResponseEntity;

import com.lotus.uccp.consentmgmt.controller.ODSController;

public class ODSUtil {

	public static LinkedHashMap<Object, Object> getODSData(String memIdnum, String srcCode) {
//
//		LinkedHashMap<Object, Object> odsData = new LinkedHashMap<>();
//		ODSController odsController = new ODSController();
//
//		// Legal
//		ResponseEntity<List<String>> clientProfileLglSrcVoList = null;
//		String clientProfileLglSrcVo = new String();
//		clientProfileLglSrcVo.setClientId(memIdnum);
//		clientProfileLglSrcVo.setSourceSystemId(srcCode);
//		clientProfileLglSrcVoList = odsController.getClientProfileLglSrcFromODS(clientProfileLglSrcVo);
//
//		if (clientProfileLglSrcVoList.getStatusCodeValue() == 400) {
//			odsData.put("Legal", "null");
//		} else {
//			odsData.put("Legal", clientProfileLglSrcVoList.getBody());
//		}
//
//		// Child Support
//		ResponseEntity<List<ClientProfileCsSrcVo>> clientProfileCsSrcVoList = null;
//		ClientProfileCsSrcVo clientProfileCsSrcVo = new ClientProfileCsSrcVo();
//		clientProfileCsSrcVo.setClientId(memIdnum);
//		clientProfileCsSrcVo.setSourceSystemId(srcCode);
//		clientProfileCsSrcVoList = odsController.getClientProfileCsSrcFromODS(clientProfileCsSrcVo);
//
//		if (clientProfileCsSrcVoList.getStatusCodeValue() == 400) {
//			odsData.put("ChildSupport", "null");
//		} else {
//			odsData.put("ChildSupport", clientProfileCsSrcVoList.getBody());
//		}
//
//		// PSC
//		ResponseEntity<List<ClientProfilePscSrcVo>> clientProfilePscSrcVoList = null;
//		ClientProfilePscSrcVo clientProfilePscSrcVo = new ClientProfilePscSrcVo();
//		clientProfilePscSrcVo.setClientId(memIdnum);
//		clientProfilePscSrcVo.setSourceSystemId(srcCode);
//		clientProfilePscSrcVoList = odsController.getClientProfilePscSrcFromODS(clientProfilePscSrcVo);
//
//		if (clientProfilePscSrcVoList.getStatusCodeValue() == 400) {
//			odsData.put("PSC", "null");
//		} else {
//			odsData.put("PSC", clientProfilePscSrcVoList.getBody());
//		}
//
//		// Criminal Justice
//		ResponseEntity<List<ClientProfileCjSrcVo>> clientProfileCjSrcVoList = null;
//		ClientProfileCjSrcVo clientProfileCjSrcVo = new ClientProfileCjSrcVo();
//		clientProfileCjSrcVo.setClientId(memIdnum);
//		clientProfileCjSrcVo.setSourceSystemId(srcCode);
//		clientProfileCjSrcVoList = odsController.getClientProfileCjSrcFromODS(clientProfileCjSrcVo);
//
//		if (clientProfileCjSrcVoList.getStatusCodeValue() == 400) {
//			odsData.put("CriminalJustice", "null");
//		} else {
//			odsData.put("CriminalJustice", clientProfileCjSrcVoList.getBody());
//		}
//
//		// Health Information
//		ResponseEntity<List<ClientProfileHlthSrcVo>> clientProfileHlthSrcVoList = null;
//		ClientProfileHlthSrcVo clientProfileHlthSrcVo = new ClientProfileHlthSrcVo();
//		clientProfileHlthSrcVo.setClientId(memIdnum);
//		clientProfileHlthSrcVo.setSourceSystemId(srcCode);
//		clientProfileHlthSrcVoList = odsController.getClientProfileHlthSrcFromODS(clientProfileHlthSrcVo);
//
//		if (clientProfileHlthSrcVoList.getStatusCodeValue() == 400) {
//			odsData.put("HealthInfo", "null");
//		} else {
//			odsData.put("HealthInfo", clientProfileHlthSrcVoList.getBody());
//		}
//
//		// Med Information
//		ResponseEntity<List<ClientProfileMedSrcVo>> clientProfileMedSrcVoList = null;
//		ClientProfileMedSrcVo clientProfileMedSrcVo = new ClientProfileMedSrcVo();
//		clientProfileMedSrcVo.setClientId(memIdnum);
//		clientProfileMedSrcVo.setSourceSystemId(srcCode);
//		clientProfileMedSrcVoList = odsController.getClientProfileMedSrcFromODS(clientProfileMedSrcVo);
//
//		if (clientProfileMedSrcVoList.getStatusCodeValue() == 400) {
//			odsData.put("MedInfo", "null");
//		} else {
//			odsData.put("MedInfo", clientProfileMedSrcVoList.getBody());
//		}
//
//		// Mental health
//		ResponseEntity<List<ClientProfileMhsSrcVo>> clientProfileMhsSrcVoList = null;
//		ClientProfileMhsSrcVo clientProfileMhsSrcVo = new ClientProfileMhsSrcVo();
//		clientProfileMhsSrcVo.setClientId(memIdnum);
//		clientProfileMhsSrcVo.setSourceSystemId(srcCode);
//		clientProfileMhsSrcVoList = odsController.getClientProfileMhsSrcFromODS(clientProfileMhsSrcVo);
//
//		if (clientProfileMhsSrcVoList.getStatusCodeValue() == 400) {
//			odsData.put("MentalHealth", "null");
//		} else {
//			odsData.put("MentalHealth", clientProfileMhsSrcVoList.getBody());
//		}
//
//		// Alcohol & Drug Use
//		ResponseEntity<List<ClientProfileAdsSrcVO>> clientProfileAdsSrcVoList = null;
//		ClientProfileAdsSrcVO clientProfileAdsSrcVo = new ClientProfileAdsSrcVO();
//		clientProfileAdsSrcVo.setClientId(memIdnum);
//		clientProfileAdsSrcVo.setSourceSystemId(srcCode);
//		clientProfileAdsSrcVoList = odsController.getClientProfileAdsSrcFromODS(clientProfileAdsSrcVo);
//
//		if (clientProfileAdsSrcVoList.getStatusCodeValue() == 400) {
//			odsData.put("AlcoholDrugUse", "null");
//		} else {
//			odsData.put("AlcoholDrugUse", clientProfileAdsSrcVoList.getBody());
//		}

//		return odsData;
		return new LinkedHashMap<Object, Object>();
	}

}
