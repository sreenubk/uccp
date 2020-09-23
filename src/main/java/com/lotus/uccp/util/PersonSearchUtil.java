package com.lotus.uccp.util;

import java.util.List;



public class PersonSearchUtil {

//	public static List<IdEntitySearchResult> getFilteredData(List<IdEntitySearchResult> idEntitySearchResults) {

//		for (IdEntitySearchResult idEntitySearchResult : idEntitySearchResults) {
//
//			//AB109FlagList
//			AB109FlagList aB109FlagList = idEntitySearchResult.getIdEntity().getClient().getAB109FlagList();
//			if (aB109FlagList != null) {
//
//				if (aB109FlagList.getAB109Flag().size() > 1) {
//					long max = 0;
//					for (int x = 0; x < aB109FlagList.getAB109Flag().size(); x++) {
//						if (aB109FlagList.getAB109Flag().get(x).getMaudRecno() > max) {
//							max = aB109FlagList.getAB109Flag().get(x).getMaudRecno();
//						}
//					}
//
//					for (int x = 0; x < aB109FlagList.getAB109Flag().size(); x++) {
//						if (max != aB109FlagList.getAB109Flag().get(x).getMaudRecno()) {
//							idEntitySearchResult.getIdEntity().getClient().getAB109FlagList().getAB109Flag()
//									.remove(x);
//							--x;
//						}
//					}
//				}
//			}
//
//			//AliasList
//			AliasList aliasList = idEntitySearchResult.getIdEntity().getClient().getAliasList();
//			if (aliasList != null) {
//
//				if (aliasList.getAlias().size() > 1) {
//					long max = 0;
//					for (int x = 0; x < aliasList.getAlias().size(); x++) {
//						if (aliasList.getAlias().get(x).getMaudRecno() > max) {
//							max = aliasList.getAlias().get(x).getMaudRecno();
//						}
//					}
//
//					for (int x = 0; x < aliasList.getAlias().size(); x++) {
//						if (max != aliasList.getAlias().get(x).getMaudRecno()) {
//							idEntitySearchResult.getIdEntity().getClient().getAliasList().getAlias()
//									.remove(x);
//							--x;
//						}
//					}
//				}
//			}
//
//			//AlienRegistrationNumberList
//			AlienRegistrationNumberList alienRegistrationNumberList = idEntitySearchResult.getIdEntity().getClient().getAlienRegistrationNumberList();
//			if (alienRegistrationNumberList != null) {
//
//				if (alienRegistrationNumberList.getAlienRegistrationNumber().size() > 1) {
//					long max = 0;
//					for (int x = 0; x < alienRegistrationNumberList.getAlienRegistrationNumber().size(); x++) {
//						if (alienRegistrationNumberList.getAlienRegistrationNumber().get(x).getMaudRecno() > max) {
//							max = alienRegistrationNumberList.getAlienRegistrationNumber().get(x).getMaudRecno();
//						}
//					}
//
//					for (int x = 0; x < alienRegistrationNumberList.getAlienRegistrationNumber().size(); x++) {
//						if (max != alienRegistrationNumberList.getAlienRegistrationNumber().get(x).getMaudRecno()) {
//							idEntitySearchResult.getIdEntity().getClient().getAlienRegistrationNumberList().getAlienRegistrationNumber()
//									.remove(x);
//							--x;
//						}
//					}
//				}
//			}
//
//			//AlternateEmailList
//			AlternateEmailList alternateEmailList = idEntitySearchResult.getIdEntity().getClient().getAlternateEmailList();
//			if (alternateEmailList != null) {
//
//				if (alternateEmailList.getAlternateEmail().size() > 1) {
//					long max = 0;
//					for (int x = 0; x < alternateEmailList.getAlternateEmail().size(); x++) {
//						if (alternateEmailList.getAlternateEmail().get(x).getMaudRecno() > max) {
//							max = alternateEmailList.getAlternateEmail().get(x).getMaudRecno();
//						}
//					}
//
//					for (int x = 0; x < alternateEmailList.getAlternateEmail().size(); x++) {
//						if (max != alternateEmailList.getAlternateEmail().get(x).getMaudRecno()) {
//							idEntitySearchResult.getIdEntity().getClient().getAlternateEmailList().getAlternateEmail()
//									.remove(x);
//							--x;
//						}
//					}
//				}
//			}
//
//			//BirthLastNameList
//			BirthLastNameList birthLastNameList = idEntitySearchResult.getIdEntity().getClient().getBirthLastNameList();
//			if (birthLastNameList != null) {
//
//				if (birthLastNameList.getBirthLastName().size() > 1) {
//					long max = 0;
//					for (int x = 0; x < birthLastNameList.getBirthLastName().size(); x++) {
//						if (birthLastNameList.getBirthLastName().get(x).getMaudRecno() > max) {
//							max = birthLastNameList.getBirthLastName().get(x).getMaudRecno();
//						}
//					}
//
//					for (int x = 0; x < birthLastNameList.getBirthLastName().size(); x++) {
//						if (max != birthLastNameList.getBirthLastName().get(x).getMaudRecno()) {
//							idEntitySearchResult.getIdEntity().getClient().getBirthLastNameList().getBirthLastName()
//									.remove(x);
//							--x;
//						}
//					}
//				}
//			}
//
//			//ChildDueDateList
//			ChildDueDateList childDueDateList = idEntitySearchResult.getIdEntity().getClient().getChildDueDateList();
//			if (childDueDateList != null) {
//
//				if (childDueDateList.getChildDueDate().size() > 1) {
//					long max = 0;
//					for (int x = 0; x < childDueDateList.getChildDueDate().size(); x++) {
//						if (childDueDateList.getChildDueDate().get(x).getMaudRecno() > max) {
//							max = childDueDateList.getChildDueDate().get(x).getMaudRecno();
//						}
//					}
//
//					for (int x = 0; x < childDueDateList.getChildDueDate().size(); x++) {
//						if (max != childDueDateList.getChildDueDate().get(x).getMaudRecno()) {
//							idEntitySearchResult.getIdEntity().getClient().getChildDueDateList().getChildDueDate()
//									.remove(x);
//							--x;
//						}
//					}
//				}
//			}
//
//			//CircumstancesofDeathList
//			CircumstancesofDeathList circumstancesofDeathList = idEntitySearchResult.getIdEntity().getClient().getCircumstancesofDeathList();
//			if (circumstancesofDeathList != null) {
//
//				if (circumstancesofDeathList.getCircumstancesofDeath().size() > 1) {
//					long max = 0;
//					for (int x = 0; x < circumstancesofDeathList.getCircumstancesofDeath().size(); x++) {
//						if (circumstancesofDeathList.getCircumstancesofDeath().get(x).getMaudRecno() > max) {
//							max = circumstancesofDeathList.getCircumstancesofDeath().get(x).getMaudRecno();
//						}
//					}
//
//					for (int x = 0; x < circumstancesofDeathList.getCircumstancesofDeath().size(); x++) {
//						if (max != circumstancesofDeathList.getCircumstancesofDeath().get(x).getMaudRecno()) {
//							idEntitySearchResult.getIdEntity().getClient().getCircumstancesofDeathList().getCircumstancesofDeath()
//									.remove(x);
//							--x;
//						}
//					}
//				}
//			}
//
//			//CitizenshipList
//			CitizenshipList citizenshipList = idEntitySearchResult.getIdEntity().getClient().getCitizenshipList();
//			if (citizenshipList != null) {
//
//				if (citizenshipList.getCitizenship().size() > 1) {
//					long max = 0;
//					for (int x = 0; x < citizenshipList.getCitizenship().size(); x++) {
//						if (citizenshipList.getCitizenship().get(x).getMaudRecno() > max) {
//							max = citizenshipList.getCitizenship().get(x).getMaudRecno();
//						}
//					}
//
//					for (int x = 0; x < citizenshipList.getCitizenship().size(); x++) {
//						if (max != citizenshipList.getCitizenship().get(x).getMaudRecno()) {
//							idEntitySearchResult.getIdEntity().getClient().getCitizenshipList().getCitizenship()
//									.remove(x);
//							--x;
//						}
//					}
//				}
//			}
//
//			// ClientNameList
//			ClientNameList clientNameList = idEntitySearchResult.getIdEntity().getClient().getClientNameList();
//			if (clientNameList != null) {
//
//				if (clientNameList.getClientName().size() > 1) {
//					long max = 0;
//					for (int x = 0; x < clientNameList.getClientName().size(); x++) {
//						if (clientNameList.getClientName().get(x).getMaudRecno() > max) {
//							max = clientNameList.getClientName().get(x).getMaudRecno();
//						}
//					}
//
//					for (int x = 0; x < clientNameList.getClientName().size(); x++) {
//						if (max != clientNameList.getClientName().get(x).getMaudRecno()) {
//							idEntitySearchResult.getIdEntity().getClient().getClientNameList().getClientName()
//									.remove(x);
//							--x;
//						}
//					}
//				}
//			}
//
//			// ClientNameSuffixList
//			ClientNameSuffixList clientNameSuffixList = idEntitySearchResult.getIdEntity().getClient().getClientNameSuffixList();
//			if (clientNameSuffixList != null) {
//
//				if (clientNameSuffixList.getClientNameSuffix().size() > 1) {
//					long max = 0;
//					for (int x = 0; x < clientNameSuffixList.getClientNameSuffix().size(); x++) {
//						if (clientNameSuffixList.getClientNameSuffix().get(x).getMaudRecno() > max) {
//							max = clientNameSuffixList.getClientNameSuffix().get(x).getMaudRecno();
//						}
//					}
//
//					for (int x = 0; x < clientNameSuffixList.getClientNameSuffix().size(); x++) {
//						if (max != clientNameSuffixList.getClientNameSuffix().get(x).getMaudRecno()) {
//							idEntitySearchResult.getIdEntity().getClient().getClientNameSuffixList().getClientNameSuffix()
//									.remove(x);
//							--x;
//						}
//					}
//				}
//			}
//
//			// ClientNameTitleList
//			ClientNameTitleList clientNameTitleList = idEntitySearchResult.getIdEntity().getClient().getClientNameTitleList();
//			if (clientNameTitleList != null) {
//
//				if (clientNameTitleList.getClientNameTitle().size() > 1) {
//					long max = 0;
//					for (int x = 0; x < clientNameTitleList.getClientNameTitle().size(); x++) {
//						if (clientNameTitleList.getClientNameTitle().get(x).getMaudRecno() > max) {
//							max = clientNameTitleList.getClientNameTitle().get(x).getMaudRecno();
//						}
//					}
//
//					for (int x = 0; x < clientNameTitleList.getClientNameTitle().size(); x++) {
//						if (max != clientNameTitleList.getClientNameTitle().get(x).getMaudRecno()) {
//							idEntitySearchResult.getIdEntity().getClient().getClientNameTitleList().getClientNameTitle()
//									.remove(x);
//							--x;
//						}
//					}
//				}
//			}
//
//			//CountryRegionofBirthList
//			CountryRegionofBirthList countryRegionofBirthList = idEntitySearchResult.getIdEntity().getClient().getCountryRegionofBirthList();
//			if (countryRegionofBirthList != null) {
//
//				if (countryRegionofBirthList.getCountryRegionofBirth().size() > 1) {
//					long max = 0;
//					for (int x = 0; x < countryRegionofBirthList.getCountryRegionofBirth().size(); x++) {
//						if (countryRegionofBirthList.getCountryRegionofBirth().get(x).getMaudRecno() > max) {
//							max = countryRegionofBirthList.getCountryRegionofBirth().get(x).getMaudRecno();
//						}
//					}
//
//					for (int x = 0; x < countryRegionofBirthList.getCountryRegionofBirth().size(); x++) {
//						if (max != countryRegionofBirthList.getCountryRegionofBirth().get(x).getMaudRecno()) {
//							idEntitySearchResult.getIdEntity().getClient().getCountryRegionofBirthList().getCountryRegionofBirth()
//									.remove(x);
//							--x;
//						}
//					}
//				}
//			}
//
//			//CurrentLivingArrangementsList
//			CurrentLivingArrangementsList currentLivingArrangementsList = idEntitySearchResult.getIdEntity().getClient().getCurrentLivingArrangementsList();
//			if (currentLivingArrangementsList != null) {
//
//				if (currentLivingArrangementsList.getCurrentLivingArrangements().size() > 1) {
//					long max = 0;
//					for (int x = 0; x < currentLivingArrangementsList.getCurrentLivingArrangements().size(); x++) {
//						if (currentLivingArrangementsList.getCurrentLivingArrangements().get(x).getMaudRecno() > max) {
//							max = currentLivingArrangementsList.getCurrentLivingArrangements().get(x).getMaudRecno();
//						}
//					}
//
//					for (int x = 0; x < currentLivingArrangementsList.getCurrentLivingArrangements().size(); x++) {
//						if (max != currentLivingArrangementsList.getCurrentLivingArrangements().get(x).getMaudRecno()) {
//							idEntitySearchResult.getIdEntity().getClient().getCurrentLivingArrangementsList().getCurrentLivingArrangements()
//									.remove(x);
//							--x;
//						}
//					}
//				}
//			}
//
//			//CurrentWorkExperienceList
//			CurrentWorkExperienceList currentWorkExperienceList = idEntitySearchResult.getIdEntity().getClient().getCurrentWorkExperienceList();
//			if (currentWorkExperienceList != null) {
//
//				if (currentWorkExperienceList.getCurrentWorkExperience().size() > 1) {
//					long max = 0;
//					for (int x = 0; x < currentWorkExperienceList.getCurrentWorkExperience().size(); x++) {
//						if (currentWorkExperienceList.getCurrentWorkExperience().get(x).getMaudRecno() > max) {
//							max = currentWorkExperienceList.getCurrentWorkExperience().get(x).getMaudRecno();
//						}
//					}
//
//					for (int x = 0; x < currentWorkExperienceList.getCurrentWorkExperience().size(); x++) {
//						if (max != currentWorkExperienceList.getCurrentWorkExperience().get(x).getMaudRecno()) {
//							idEntitySearchResult.getIdEntity().getClient().getCurrentWorkExperienceList().getCurrentWorkExperience()
//									.remove(x);
//							--x;
//						}
//					}
//				}
//			}
//
//			//DateLastWorkedList
//			DateLastWorkedList dateLastWorkedList = idEntitySearchResult.getIdEntity().getClient().getDateLastWorkedList();
//			if (dateLastWorkedList != null) {
//
//				if (dateLastWorkedList.getDateLastWorked().size() > 1) {
//					long max = 0;
//					for (int x = 0; x < dateLastWorkedList.getDateLastWorked().size(); x++) {
//						if (dateLastWorkedList.getDateLastWorked().get(x).getMaudRecno() > max) {
//							max = dateLastWorkedList.getDateLastWorked().get(x).getMaudRecno();
//						}
//					}
//
//					for (int x = 0; x < dateLastWorkedList.getDateLastWorked().size(); x++) {
//						if (max != dateLastWorkedList.getDateLastWorked().get(x).getMaudRecno()) {
//							idEntitySearchResult.getIdEntity().getClient().getDateLastWorkedList().getDateLastWorked()
//									.remove(x);
//							--x;
//						}
//					}
//				}
//			}
//
//			//DateofBirthList
//			DateofBirthList dateofBirthList = idEntitySearchResult.getIdEntity().getClient().getDateofBirthList();
//			if (dateofBirthList != null) {
//
//				if (dateofBirthList.getDateofBirth().size() > 1) {
//					long max = 0;
//					for (int x = 0; x < dateofBirthList.getDateofBirth().size(); x++) {
//						if (dateofBirthList.getDateofBirth().get(x).getMaudRecno() > max) {
//							max = dateofBirthList.getDateofBirth().get(x).getMaudRecno();
//						}
//					}
//
//					for (int x = 0; x < dateofBirthList.getDateofBirth().size(); x++) {
//						if (max != dateofBirthList.getDateofBirth().get(x).getMaudRecno()) {
//							idEntitySearchResult.getIdEntity().getClient().getDateofBirthList().getDateofBirth()
//									.remove(x);
//							--x;
//						}
//					}
//				}
//			}
//
//			//DateofDeathList
//			DateofDeathList dateofDeathList = idEntitySearchResult.getIdEntity().getClient().getDateofDeathList();
//			if (dateofDeathList != null) {
//
//				if (dateofDeathList.getDateofDeath().size() > 1) {
//					long max = 0;
//					for (int x = 0; x < dateofDeathList.getDateofDeath().size(); x++) {
//						if (dateofDeathList.getDateofDeath().get(x).getMaudRecno() > max) {
//							max = dateofDeathList.getDateofDeath().get(x).getMaudRecno();
//						}
//					}
//
//					for (int x = 0; x < dateofDeathList.getDateofDeath().size(); x++) {
//						if (max != dateofDeathList.getDateofDeath().get(x).getMaudRecno()) {
//							idEntitySearchResult.getIdEntity().getClient().getDateofDeathList().getDateofDeath()
//									.remove(x);
//							--x;
//						}
//					}
//				}
//			}
//
//			//DisabilityList
//			DisabilityList disabilityList = idEntitySearchResult.getIdEntity().getClient().getDisabilityList();
//			if (disabilityList != null) {
//
//				if (disabilityList.getDisability().size() > 1) {
//					long max = 0;
//					for (int x = 0; x < disabilityList.getDisability().size(); x++) {
//						if (disabilityList.getDisability().get(x).getMaudRecno() > max) {
//							max = disabilityList.getDisability().get(x).getMaudRecno();
//						}
//					}
//
//					for (int x = 0; x < disabilityList.getDisability().size(); x++) {
//						if (max != disabilityList.getDisability().get(x).getMaudRecno()) {
//							idEntitySearchResult.getIdEntity().getClient().getDisabilityList().getDisability()
//									.remove(x);
//							--x;
//						}
//					}
//				}
//			}
//
//			//EducationLevelList
//			EducationLevelList educationLevelList = idEntitySearchResult.getIdEntity().getClient().getEducationLevelList();
//			if (educationLevelList != null) {
//
//				if (educationLevelList.getEducationLevel().size() > 1) {
//					long max = 0;
//					for (int x = 0; x < educationLevelList.getEducationLevel().size(); x++) {
//						if (educationLevelList.getEducationLevel().get(x).getMaudRecno() > max) {
//							max = educationLevelList.getEducationLevel().get(x).getMaudRecno();
//						}
//					}
//
//					for (int x = 0; x < educationLevelList.getEducationLevel().size(); x++) {
//						if (max != educationLevelList.getEducationLevel().get(x).getMaudRecno()) {
//							idEntitySearchResult.getIdEntity().getClient().getEducationLevelList().getEducationLevel()
//									.remove(x);
//							--x;
//						}
//					}
//				}
//			}
//
//			//EmergencyContactList
//			EmergencyContactList emergencyContactList = idEntitySearchResult.getIdEntity().getClient().getEmergencyContactList();
//			if (emergencyContactList != null) {
//
//				if (emergencyContactList.getEmergencyContact().size() > 1) {
//					long max = 0;
//					for (int x = 0; x < emergencyContactList.getEmergencyContact().size(); x++) {
//						if (emergencyContactList.getEmergencyContact().get(x).getMaudRecno() > max) {
//							max = emergencyContactList.getEmergencyContact().get(x).getMaudRecno();
//						}
//					}
//
//					for (int x = 0; x < emergencyContactList.getEmergencyContact().size(); x++) {
//						if (max != emergencyContactList.getEmergencyContact().get(x).getMaudRecno()) {
//							idEntitySearchResult.getIdEntity().getClient().getEmergencyContactList().getEmergencyContact()
//									.remove(x);
//							--x;
//						}
//					}
//				}
//			}
//
//			//EmploymentStatusList
//			EmploymentStatusList employmentStatusList = idEntitySearchResult.getIdEntity().getClient().getEmploymentStatusList();
//			if (employmentStatusList != null) {
//
//				if (employmentStatusList.getEmploymentStatus().size() > 1) {
//					long max = 0;
//					for (int x = 0; x < employmentStatusList.getEmploymentStatus().size(); x++) {
//						if (employmentStatusList.getEmploymentStatus().get(x).getMaudRecno() > max) {
//							max = employmentStatusList.getEmploymentStatus().get(x).getMaudRecno();
//						}
//					}
//
//					for (int x = 0; x < employmentStatusList.getEmploymentStatus().size(); x++) {
//						if (max != employmentStatusList.getEmploymentStatus().get(x).getMaudRecno()) {
//							idEntitySearchResult.getIdEntity().getClient().getEmploymentStatusList().getEmploymentStatus()
//									.remove(x);
//							--x;
//						}
//					}
//				}
//			}
//
//			//EnrolledInJobTrainingList
//			EnrolledInJobTrainingList enrolledInJobTrainingList = idEntitySearchResult.getIdEntity().getClient().getEnrolledInJobTrainingList();
//			if (enrolledInJobTrainingList != null) {
//
//				if (enrolledInJobTrainingList.getEnrolledInJobTraining().size() > 1) {
//					long max = 0;
//					for (int x = 0; x < enrolledInJobTrainingList.getEnrolledInJobTraining().size(); x++) {
//						if (enrolledInJobTrainingList.getEnrolledInJobTraining().get(x).getMaudRecno() > max) {
//							max = enrolledInJobTrainingList.getEnrolledInJobTraining().get(x).getMaudRecno();
//						}
//					}
//
//					for (int x = 0; x < enrolledInJobTrainingList.getEnrolledInJobTraining().size(); x++) {
//						if (max != enrolledInJobTrainingList.getEnrolledInJobTraining().get(x).getMaudRecno()) {
//							idEntitySearchResult.getIdEntity().getClient().getEnrolledInJobTrainingList().getEnrolledInJobTraining()
//									.remove(x);
//							--x;
//						}
//					}
//				}
//			}
//
//			//EnrolledinSchoolList
//			EnrolledinSchoolList enrolledinSchoolList = idEntitySearchResult.getIdEntity().getClient().getEnrolledinSchoolList();
//			if (enrolledinSchoolList != null) {
//
//				if (enrolledinSchoolList.getEnrolledinSchool().size() > 1) {
//					long max = 0;
//					for (int x = 0; x < enrolledinSchoolList.getEnrolledinSchool().size(); x++) {
//						if (enrolledinSchoolList.getEnrolledinSchool().get(x).getMaudRecno() > max) {
//							max = enrolledinSchoolList.getEnrolledinSchool().get(x).getMaudRecno();
//						}
//					}
//
//					for (int x = 0; x < enrolledinSchoolList.getEnrolledinSchool().size(); x++) {
//						if (max != enrolledinSchoolList.getEnrolledinSchool().get(x).getMaudRecno()) {
//							idEntitySearchResult.getIdEntity().getClient().getEnrolledinSchoolList().getEnrolledinSchool()
//									.remove(x);
//							--x;
//						}
//					}
//				}
//			}
//
//			//EthnicityList
//			EthnicityList ethnicityList = idEntitySearchResult.getIdEntity().getClient().getEthnicityList();
//			if (ethnicityList != null) {
//
//				if (ethnicityList.getEthnicity().size() > 1) {
//					long max = 0;
//					for (int x = 0; x < ethnicityList.getEthnicity().size(); x++) {
//						if (ethnicityList.getEthnicity().get(x).getMaudRecno() > max) {
//							max = ethnicityList.getEthnicity().get(x).getMaudRecno();
//						}
//					}
//
//					for (int x = 0; x < ethnicityList.getEthnicity().size(); x++) {
//						if (max != ethnicityList.getEthnicity().get(x).getMaudRecno()) {
//							idEntitySearchResult.getIdEntity().getClient().getEthnicityList().getEthnicity()
//									.remove(x);
//							--x;
//						}
//					}
//				}
//			}
//
//			//ForeignAddressList
//			ForeignAddressList foreignAddressList = idEntitySearchResult.getIdEntity().getClient().getForeignAddressList();
//			if (foreignAddressList != null) {
//
//				if (foreignAddressList.getForeignAddress().size() > 1) {
//					long max = 0;
//					for (int x = 0; x < foreignAddressList.getForeignAddress().size(); x++) {
//						if (foreignAddressList.getForeignAddress().get(x).getMaudRecno() > max) {
//							max = foreignAddressList.getForeignAddress().get(x).getMaudRecno();
//						}
//					}
//
//					for (int x = 0; x < foreignAddressList.getForeignAddress().size(); x++) {
//						if (max != foreignAddressList.getForeignAddress().get(x).getMaudRecno()) {
//							idEntitySearchResult.getIdEntity().getClient().getForeignAddressList().getForeignAddress()
//									.remove(x);
//							--x;
//						}
//					}
//				}
//			}
//
//			//GenderCommentList
//			GenderCommentList genderCommentList = idEntitySearchResult.getIdEntity().getClient().getGenderCommentList();
//			if (genderCommentList != null) {
//
//				if (genderCommentList.getGenderComment().size() > 1) {
//					long max = 0;
//					for (int x = 0; x < genderCommentList.getGenderComment().size(); x++) {
//						if (genderCommentList.getGenderComment().get(x).getMaudRecno() > max) {
//							max = genderCommentList.getGenderComment().get(x).getMaudRecno();
//						}
//					}
//
//					for (int x = 0; x < genderCommentList.getGenderComment().size(); x++) {
//						if (max != genderCommentList.getGenderComment().get(x).getMaudRecno()) {
//							idEntitySearchResult.getIdEntity().getClient().getGenderCommentList().getGenderComment()
//									.remove(x);
//							--x;
//						}
//					}
//				}
//			}
//
//			//GenderList
//			GenderList genderList = idEntitySearchResult.getIdEntity().getClient().getGenderList();
//			if (genderList != null) {
//
//				if (genderList.getGender().size() > 1) {
//					long max = 0;
//					for (int x = 0; x < genderList.getGender().size(); x++) {
//						if (genderList.getGender().get(x).getMaudRecno() > max) {
//							max = genderList.getGender().get(x).getMaudRecno();
//						}
//					}
//
//					for (int x = 0; x < genderList.getGender().size(); x++) {
//						if (max != genderList.getGender().get(x).getMaudRecno()) {
//							idEntitySearchResult.getIdEntity().getClient().getGenderList().getGender()
//									.remove(x);
//							--x;
//						}
//					}
//				}
//			}
//
//			//GraduatedFromHighSchoolList
//			GraduatedFromHighSchoolList graduatedFromHighSchoolList = idEntitySearchResult.getIdEntity().getClient().getGraduatedFromHighSchoolList();
//			if (graduatedFromHighSchoolList != null) {
//
//				if (graduatedFromHighSchoolList.getGraduatedFromHighSchool().size() > 1) {
//					long max = 0;
//					for (int x = 0; x < graduatedFromHighSchoolList.getGraduatedFromHighSchool().size(); x++) {
//						if (graduatedFromHighSchoolList.getGraduatedFromHighSchool().get(x).getMaudRecno() > max) {
//							max = graduatedFromHighSchoolList.getGraduatedFromHighSchool().get(x).getMaudRecno();
//						}
//					}
//
//					for (int x = 0; x < graduatedFromHighSchoolList.getGraduatedFromHighSchool().size(); x++) {
//						if (max != graduatedFromHighSchoolList.getGraduatedFromHighSchool().get(x).getMaudRecno()) {
//							idEntitySearchResult.getIdEntity().getClient().getGraduatedFromHighSchoolList().getGraduatedFromHighSchool()
//									.remove(x);
//							--x;
//						}
//					}
//				}
//			}
//
//			//HistoryofViolenceList
//			HistoryofViolenceList historyofViolenceList = idEntitySearchResult.getIdEntity().getClient().getHistoryofViolenceList();
//			if (historyofViolenceList != null) {
//
//				if (historyofViolenceList.getHistoryofViolence().size() > 1) {
//					long max = 0;
//					for (int x = 0; x < historyofViolenceList.getHistoryofViolence().size(); x++) {
//						if (historyofViolenceList.getHistoryofViolence().get(x).getMaudRecno() > max) {
//							max = historyofViolenceList.getHistoryofViolence().get(x).getMaudRecno();
//						}
//					}
//
//					for (int x = 0; x < historyofViolenceList.getHistoryofViolence().size(); x++) {
//						if (max != historyofViolenceList.getHistoryofViolence().get(x).getMaudRecno()) {
//							idEntitySearchResult.getIdEntity().getClient().getHistoryofViolenceList().getHistoryofViolence()
//									.remove(x);
//							--x;
//						}
//					}
//				}
//			}
//
//			//HomeAddressList
//			HomeAddressList homeAddressList = idEntitySearchResult.getIdEntity().getClient().getHomeAddressList();
//			if (homeAddressList != null) {
//
//				if (homeAddressList.getHomeAddress().size() > 1) {
//					long max = 0;
//					for (int x = 0; x < homeAddressList.getHomeAddress().size(); x++) {
//						if (homeAddressList.getHomeAddress().get(x).getMaudRecno() > max) {
//							max = homeAddressList.getHomeAddress().get(x).getMaudRecno();
//						}
//					}
//
//					for (int x = 0; x < homeAddressList.getHomeAddress().size(); x++) {
//						if (max != homeAddressList.getHomeAddress().get(x).getMaudRecno()) {
//							idEntitySearchResult.getIdEntity().getClient().getHomeAddressList().getHomeAddress()
//									.remove(x);
//							--x;
//						}
//					}
//				}
//			}
//
//			//HomePhoneList
//			HomePhoneList homePhoneList = idEntitySearchResult.getIdEntity().getClient().getHomePhoneList();
//			if (homePhoneList != null) {
//
//				if (homePhoneList.getHomePhone().size() > 1) {
//					long max = 0;
//					for (int x = 0; x < homePhoneList.getHomePhone().size(); x++) {
//						if (homePhoneList.getHomePhone().get(x).getMaudRecno() > max) {
//							max = homePhoneList.getHomePhone().get(x).getMaudRecno();
//						}
//					}
//
//					for (int x = 0; x < homePhoneList.getHomePhone().size(); x++) {
//						if (max != homePhoneList.getHomePhone().get(x).getMaudRecno()) {
//							idEntitySearchResult.getIdEntity().getClient().getHomePhoneList().getHomePhone()
//									.remove(x);
//							--x;
//						}
//					}
//				}
//			}
//
//			//ImmigrationStatusList
//			ImmigrationStatusList immigrationStatusList = idEntitySearchResult.getIdEntity().getClient().getImmigrationStatusList();
//			if (immigrationStatusList != null) {
//
//				if (immigrationStatusList.getImmigrationStatus().size() > 1) {
//					long max = 0;
//					for (int x = 0; x < immigrationStatusList.getImmigrationStatus().size(); x++) {
//						if (immigrationStatusList.getImmigrationStatus().get(x).getMaudRecno() > max) {
//							max = immigrationStatusList.getImmigrationStatus().get(x).getMaudRecno();
//						}
//					}
//
//					for (int x = 0; x < immigrationStatusList.getImmigrationStatus().size(); x++) {
//						if (max != immigrationStatusList.getImmigrationStatus().get(x).getMaudRecno()) {
//							idEntitySearchResult.getIdEntity().getClient().getImmigrationStatusList().getImmigrationStatus()
//									.remove(x);
//							--x;
//						}
//					}
//				}
//			}
//
//			//InterpreterList
//			InterpreterList interpreterList = idEntitySearchResult.getIdEntity().getClient().getInterpreterList();
//			if (interpreterList != null) {
//
//				if (interpreterList.getInterpreter().size() > 1) {
//					long max = 0;
//					for (int x = 0; x < interpreterList.getInterpreter().size(); x++) {
//						if (interpreterList.getInterpreter().get(x).getMaudRecno() > max) {
//							max = interpreterList.getInterpreter().get(x).getMaudRecno();
//						}
//					}
//
//					for (int x = 0; x < interpreterList.getInterpreter().size(); x++) {
//						if (max != interpreterList.getInterpreter().get(x).getMaudRecno()) {
//							idEntitySearchResult.getIdEntity().getClient().getInterpreterList().getInterpreter()
//									.remove(x);
//							--x;
//						}
//					}
//				}
//			}
//
//			//Last4SSNList
//			Last4SSNList last4SSNList = idEntitySearchResult.getIdEntity().getClient().getLast4SSNList();
//			if (last4SSNList != null) {
//
//				if (last4SSNList.getLast4SSN().size() > 1) {
//					long max = 0;
//					for (int x = 0; x < last4SSNList.getLast4SSN().size(); x++) {
//						if (last4SSNList.getLast4SSN().get(x).getMaudRecno() > max) {
//							max = last4SSNList.getLast4SSN().get(x).getMaudRecno();
//						}
//					}
//
//					for (int x = 0; x < last4SSNList.getLast4SSN().size(); x++) {
//						if (max != last4SSNList.getLast4SSN().get(x).getMaudRecno()) {
//							idEntitySearchResult.getIdEntity().getClient().getLast4SSNList().getLast4SSN()
//									.remove(x);
//							--x;
//						}
//					}
//				}
//			}
//
//			//LastUpdateList
//			LastUpdateList lastUpdateList = idEntitySearchResult.getIdEntity().getClient().getLastUpdateList();
//			if (lastUpdateList != null) {
//
//				if (lastUpdateList.getLastUpdate().size() > 1) {
//					long max = 0;
//					for (int x = 0; x < lastUpdateList.getLastUpdate().size(); x++) {
//						if (lastUpdateList.getLastUpdate().get(x).getMaudRecno() > max) {
//							max = lastUpdateList.getLastUpdate().get(x).getMaudRecno();
//						}
//					}
//
//					for (int x = 0; x < lastUpdateList.getLastUpdate().size(); x++) {
//						if (max != lastUpdateList.getLastUpdate().get(x).getMaudRecno()) {
//							idEntitySearchResult.getIdEntity().getClient().getLastUpdateList().getLastUpdate()
//									.remove(x);
//							--x;
//						}
//					}
//				}
//			}
//
//			//LiterateList
//			LiterateList literateList = idEntitySearchResult.getIdEntity().getClient().getLiterateList();
//			if (literateList != null) {
//
//				if (literateList.getLiterate().size() > 1) {
//					long max = 0;
//					for (int x = 0; x < literateList.getLiterate().size(); x++) {
//						if (literateList.getLiterate().get(x).getMaudRecno() > max) {
//							max = literateList.getLiterate().get(x).getMaudRecno();
//						}
//					}
//
//					for (int x = 0; x < literateList.getLiterate().size(); x++) {
//						if (max != literateList.getLiterate().get(x).getMaudRecno()) {
//							idEntitySearchResult.getIdEntity().getClient().getLiterateList().getLiterate()
//									.remove(x);
//							--x;
//						}
//					}
//				}
//			}
//
//			//LivesAtPrimaryAddressList
//			LivesAtPrimaryAddressList livesAtPrimaryAddressList = idEntitySearchResult.getIdEntity().getClient().getLivesAtPrimaryAddressList();
//			if (livesAtPrimaryAddressList != null) {
//
//				if (livesAtPrimaryAddressList.getLivesAtPrimaryAddress().size() > 1) {
//					long max = 0;
//					for (int x = 0; x < livesAtPrimaryAddressList.getLivesAtPrimaryAddress().size(); x++) {
//						if (livesAtPrimaryAddressList.getLivesAtPrimaryAddress().get(x).getMaudRecno() > max) {
//							max = livesAtPrimaryAddressList.getLivesAtPrimaryAddress().get(x).getMaudRecno();
//						}
//					}
//
//					for (int x = 0; x < livesAtPrimaryAddressList.getLivesAtPrimaryAddress().size(); x++) {
//						if (max != livesAtPrimaryAddressList.getLivesAtPrimaryAddress().get(x).getMaudRecno()) {
//							idEntitySearchResult.getIdEntity().getClient().getLivesAtPrimaryAddressList().getLivesAtPrimaryAddress()
//									.remove(x);
//							--x;
//						}
//					}
//				}
//			}
//
//			//LivesWithRelationList
//			LivesWithRelationList livesWithRelationList = idEntitySearchResult.getIdEntity().getClient().getLivesWithRelationList();
//			if (livesWithRelationList != null) {
//
//				if (livesWithRelationList.getLivesWithRelation().size() > 1) {
//					long max = 0;
//					for (int x = 0; x < livesWithRelationList.getLivesWithRelation().size(); x++) {
//						if (livesWithRelationList.getLivesWithRelation().get(x).getMaudRecno() > max) {
//							max = livesWithRelationList.getLivesWithRelation().get(x).getMaudRecno();
//						}
//					}
//
//					for (int x = 0; x < livesWithRelationList.getLivesWithRelation().size(); x++) {
//						if (max != livesWithRelationList.getLivesWithRelation().get(x).getMaudRecno()) {
//							idEntitySearchResult.getIdEntity().getClient().getLivesWithRelationList().getLivesWithRelation()
//									.remove(x);
//							--x;
//						}
//					}
//				}
//			}
//
//			//MailingAddressList
//			MailingAddressList mailingAddressList = idEntitySearchResult.getIdEntity().getClient().getMailingAddressList();
//			if (mailingAddressList != null) {
//
//				if (mailingAddressList.getMailingAddress().size() > 1) {
//					long max = 0;
//					for (int x = 0; x < mailingAddressList.getMailingAddress().size(); x++) {
//						if (mailingAddressList.getMailingAddress().get(x).getMaudRecno() > max) {
//							max = mailingAddressList.getMailingAddress().get(x).getMaudRecno();
//						}
//					}
//
//					for (int x = 0; x < mailingAddressList.getMailingAddress().size(); x++) {
//						if (max != mailingAddressList.getMailingAddress().get(x).getMaudRecno()) {
//							idEntitySearchResult.getIdEntity().getClient().getMailingAddressList().getMailingAddress()
//									.remove(x);
//							--x;
//						}
//					}
//				}
//			}
//
//			//MaritalStatusList
//			MaritalStatusList maritalStatusList = idEntitySearchResult.getIdEntity().getClient().getMaritalStatusList();
//			if (maritalStatusList != null) {
//
//				if (maritalStatusList.getMaritalStatus().size() > 1) {
//					long max = 0;
//					for (int x = 0; x < maritalStatusList.getMaritalStatus().size(); x++) {
//						if (maritalStatusList.getMaritalStatus().get(x).getMaudRecno() > max) {
//							max = maritalStatusList.getMaritalStatus().get(x).getMaudRecno();
//						}
//					}
//
//					for (int x = 0; x < maritalStatusList.getMaritalStatus().size(); x++) {
//						if (max != maritalStatusList.getMaritalStatus().get(x).getMaudRecno()) {
//							idEntitySearchResult.getIdEntity().getClient().getMaritalStatusList().getMaritalStatus()
//									.remove(x);
//							--x;
//						}
//					}
//				}
//			}
//
//			//MilitaryStatusList
//			MilitaryStatusList militaryStatusList = idEntitySearchResult.getIdEntity().getClient().getMilitaryStatusList();
//			if (militaryStatusList != null) {
//
//				if (militaryStatusList.getMilitaryStatus().size() > 1) {
//					long max = 0;
//					for (int x = 0; x < militaryStatusList.getMilitaryStatus().size(); x++) {
//						if (militaryStatusList.getMilitaryStatus().get(x).getMaudRecno() > max) {
//							max = militaryStatusList.getMilitaryStatus().get(x).getMaudRecno();
//						}
//					}
//
//					for (int x = 0; x < militaryStatusList.getMilitaryStatus().size(); x++) {
//						if (max != militaryStatusList.getMilitaryStatus().get(x).getMaudRecno()) {
//							idEntitySearchResult.getIdEntity().getClient().getMilitaryStatusList().getMilitaryStatus()
//									.remove(x);
//							--x;
//						}
//					}
//				}
//			}
//
//			//MobilePhoneList
//			MobilePhoneList mobilePhoneList = idEntitySearchResult.getIdEntity().getClient().getMobilePhoneList();
//			if (mobilePhoneList != null) {
//
//				if (mobilePhoneList.getMobilePhone().size() > 1) {
//					long max = 0;
//					for (int x = 0; x < mobilePhoneList.getMobilePhone().size(); x++) {
//						if (mobilePhoneList.getMobilePhone().get(x).getMaudRecno() > max) {
//							max = mobilePhoneList.getMobilePhone().get(x).getMaudRecno();
//						}
//					}
//
//					for (int x = 0; x < mobilePhoneList.getMobilePhone().size(); x++) {
//						if (max != mobilePhoneList.getMobilePhone().get(x).getMaudRecno()) {
//							idEntitySearchResult.getIdEntity().getClient().getMobilePhoneList().getMobilePhone()
//									.remove(x);
//							--x;
//						}
//					}
//				}
//			}
//
//			//NbrChildrenUnder18List
//			NbrChildrenUnder18List nbrChildrenUnder18List = idEntitySearchResult.getIdEntity().getClient().getNbrChildrenUnder18List();
//			if (nbrChildrenUnder18List != null) {
//
//				if (nbrChildrenUnder18List.getNbrChildrenUnder18().size() > 1) {
//					long max = 0;
//					for (int x = 0; x < nbrChildrenUnder18List.getNbrChildrenUnder18().size(); x++) {
//						if (nbrChildrenUnder18List.getNbrChildrenUnder18().get(x).getMaudRecno() > max) {
//							max = nbrChildrenUnder18List.getNbrChildrenUnder18().get(x).getMaudRecno();
//						}
//					}
//
//					for (int x = 0; x < nbrChildrenUnder18List.getNbrChildrenUnder18().size(); x++) {
//						if (max != nbrChildrenUnder18List.getNbrChildrenUnder18().get(x).getMaudRecno()) {
//							idEntitySearchResult.getIdEntity().getClient().getNbrChildrenUnder18List().getNbrChildrenUnder18()
//									.remove(x);
//							--x;
//						}
//					}
//				}
//			}
//
//			//NbrChildrenUnder5List
//			NbrChildrenUnder5List nbrChildrenUnder5List = idEntitySearchResult.getIdEntity().getClient().getNbrChildrenUnder5List();
//			if (nbrChildrenUnder5List != null) {
//
//				if (nbrChildrenUnder5List.getNbrChildrenUnder5().size() > 1) {
//					long max = 0;
//					for (int x = 0; x < nbrChildrenUnder5List.getNbrChildrenUnder5().size(); x++) {
//						if (nbrChildrenUnder5List.getNbrChildrenUnder5().get(x).getMaudRecno() > max) {
//							max = nbrChildrenUnder5List.getNbrChildrenUnder5().get(x).getMaudRecno();
//						}
//					}
//
//					for (int x = 0; x < nbrChildrenUnder5List.getNbrChildrenUnder5().size(); x++) {
//						if (max != nbrChildrenUnder5List.getNbrChildrenUnder5().get(x).getMaudRecno()) {
//							idEntitySearchResult.getIdEntity().getClient().getNbrChildrenUnder5List().getNbrChildrenUnder5()
//									.remove(x);
//							--x;
//						}
//					}
//				}
//			}
//
//			//OtherAddressList
//			OtherAddressList otherAddressList = idEntitySearchResult.getIdEntity().getClient().getOtherAddressList();
//			if (otherAddressList != null) {
//
//				if (otherAddressList.getOtherAddress().size() > 1) {
//					long max = 0;
//					for (int x = 0; x < otherAddressList.getOtherAddress().size(); x++) {
//						if (otherAddressList.getOtherAddress().get(x).getMaudRecno() > max) {
//							max = otherAddressList.getOtherAddress().get(x).getMaudRecno();
//						}
//					}
//
//					for (int x = 0; x < otherAddressList.getOtherAddress().size(); x++) {
//						if (max != otherAddressList.getOtherAddress().get(x).getMaudRecno()) {
//							idEntitySearchResult.getIdEntity().getClient().getOtherAddressList().getOtherAddress()
//									.remove(x);
//							--x;
//						}
//					}
//				}
//			}
//
//			//OtherPhoneList
//			OtherPhoneList otherPhoneList = idEntitySearchResult.getIdEntity().getClient().getOtherPhoneList();
//			if (otherPhoneList != null) {
//
//				if (otherPhoneList.getOtherPhone().size() > 1) {
//					long max = 0;
//					for (int x = 0; x < otherPhoneList.getOtherPhone().size(); x++) {
//						if (otherPhoneList.getOtherPhone().get(x).getMaudRecno() > max) {
//							max = otherPhoneList.getOtherPhone().get(x).getMaudRecno();
//						}
//					}
//
//					for (int x = 0; x < otherPhoneList.getOtherPhone().size(); x++) {
//						if (max != otherPhoneList.getOtherPhone().get(x).getMaudRecno()) {
//							idEntitySearchResult.getIdEntity().getClient().getOtherPhoneList().getOtherPhone()
//									.remove(x);
//							--x;
//						}
//					}
//				}
//			}
//
//			//PaidDaysLast30List
//			PaidDaysLast30List paidDaysLast30List = idEntitySearchResult.getIdEntity().getClient().getPaidDaysLast30List();
//			if (paidDaysLast30List != null) {
//
//				if (paidDaysLast30List.getPaidDaysLast30().size() > 1) {
//					long max = 0;
//					for (int x = 0; x < paidDaysLast30List.getPaidDaysLast30().size(); x++) {
//						if (paidDaysLast30List.getPaidDaysLast30().get(x).getMaudRecno() > max) {
//							max = paidDaysLast30List.getPaidDaysLast30().get(x).getMaudRecno();
//						}
//					}
//
//					for (int x = 0; x < paidDaysLast30List.getPaidDaysLast30().size(); x++) {
//						if (max != paidDaysLast30List.getPaidDaysLast30().get(x).getMaudRecno()) {
//							idEntitySearchResult.getIdEntity().getClient().getPaidDaysLast30List().getPaidDaysLast30()
//									.remove(x);
//							--x;
//						}
//					}
//				}
//			}
//
//			//PlaceofBirthList
//			PlaceofBirthList placeofBirthList = idEntitySearchResult.getIdEntity().getClient().getPlaceofBirthList();
//			if (placeofBirthList != null) {
//
//				if (placeofBirthList.getPlaceofBirth().size() > 1) {
//					long max = 0;
//					for (int x = 0; x < placeofBirthList.getPlaceofBirth().size(); x++) {
//						if (placeofBirthList.getPlaceofBirth().get(x).getMaudRecno() > max) {
//							max = placeofBirthList.getPlaceofBirth().get(x).getMaudRecno();
//						}
//					}
//
//					for (int x = 0; x < placeofBirthList.getPlaceofBirth().size(); x++) {
//						if (max != placeofBirthList.getPlaceofBirth().get(x).getMaudRecno()) {
//							idEntitySearchResult.getIdEntity().getClient().getPlaceofBirthList().getPlaceofBirth()
//									.remove(x);
//							--x;
//						}
//					}
//				}
//			}
//
//			//PopulationTypeList
//			PopulationTypeList populationTypeList = idEntitySearchResult.getIdEntity().getClient().getPopulationTypeList();
//			if (populationTypeList != null) {
//
//				if (populationTypeList.getPopulationType().size() > 1) {
//					long max = 0;
//					for (int x = 0; x < populationTypeList.getPopulationType().size(); x++) {
//						if (populationTypeList.getPopulationType().get(x).getMaudRecno() > max) {
//							max = populationTypeList.getPopulationType().get(x).getMaudRecno();
//						}
//					}
//
//					for (int x = 0; x < populationTypeList.getPopulationType().size(); x++) {
//						if (max != populationTypeList.getPopulationType().get(x).getMaudRecno()) {
//							idEntitySearchResult.getIdEntity().getClient().getPopulationTypeList().getPopulationType()
//									.remove(x);
//							--x;
//						}
//					}
//				}
//			}
//
//			//PreferredCommunicationList
//			PreferredCommunicationList preferredCommunicationList = idEntitySearchResult.getIdEntity().getClient().getPreferredCommunicationList();
//			if (preferredCommunicationList != null) {
//
//				if (preferredCommunicationList.getPreferredCommunication().size() > 1) {
//					long max = 0;
//					for (int x = 0; x < preferredCommunicationList.getPreferredCommunication().size(); x++) {
//						if (preferredCommunicationList.getPreferredCommunication().get(x).getMaudRecno() > max) {
//							max = preferredCommunicationList.getPreferredCommunication().get(x).getMaudRecno();
//						}
//					}
//
//					for (int x = 0; x < preferredCommunicationList.getPreferredCommunication().size(); x++) {
//						if (max != preferredCommunicationList.getPreferredCommunication().get(x).getMaudRecno()) {
//							idEntitySearchResult.getIdEntity().getClient().getPreferredCommunicationList().getPreferredCommunication()
//									.remove(x);
//							--x;
//						}
//					}
//				}
//			}
//
//			//PregnancyStatusList
//			PregnancyStatusList pregnancyStatusList = idEntitySearchResult.getIdEntity().getClient().getPregnancyStatusList();
//			if (pregnancyStatusList != null) {
//
//				if (pregnancyStatusList.getPregnancyStatus().size() > 1) {
//					long max = 0;
//					for (int x = 0; x < pregnancyStatusList.getPregnancyStatus().size(); x++) {
//						if (pregnancyStatusList.getPregnancyStatus().get(x).getMaudRecno() > max) {
//							max = pregnancyStatusList.getPregnancyStatus().get(x).getMaudRecno();
//						}
//					}
//
//					for (int x = 0; x < pregnancyStatusList.getPregnancyStatus().size(); x++) {
//						if (max != pregnancyStatusList.getPregnancyStatus().get(x).getMaudRecno()) {
//							idEntitySearchResult.getIdEntity().getClient().getPregnancyStatusList().getPregnancyStatus()
//									.remove(x);
//							--x;
//						}
//					}
//				}
//			}
//
//			//PrimaryEmailAddressList
//			PrimaryEmailAddressList primaryEmailAddressList = idEntitySearchResult.getIdEntity().getClient().getPrimaryEmailAddressList();
//			if (primaryEmailAddressList != null) {
//
//				if (primaryEmailAddressList.getPrimaryEmailAddress().size() > 1) {
//					long max = 0;
//					for (int x = 0; x < primaryEmailAddressList.getPrimaryEmailAddress().size(); x++) {
//						if (primaryEmailAddressList.getPrimaryEmailAddress().get(x).getMaudRecno() > max) {
//							max = primaryEmailAddressList.getPrimaryEmailAddress().get(x).getMaudRecno();
//						}
//					}
//
//					for (int x = 0; x < primaryEmailAddressList.getPrimaryEmailAddress().size(); x++) {
//						if (max != primaryEmailAddressList.getPrimaryEmailAddress().get(x).getMaudRecno()) {
//							idEntitySearchResult.getIdEntity().getClient().getPrimaryEmailAddressList().getPrimaryEmailAddress()
//									.remove(x);
//							--x;
//						}
//					}
//				}
//			}
//
//			//PrimaryLanguageList
//			PrimaryLanguageList primaryLanguageList = idEntitySearchResult.getIdEntity().getClient().getPrimaryLanguageList();
//			if (primaryLanguageList != null) {
//
//				if (primaryLanguageList.getPrimaryLanguage().size() > 1) {
//					long max = 0;
//					for (int x = 0; x < primaryLanguageList.getPrimaryLanguage().size(); x++) {
//						if (primaryLanguageList.getPrimaryLanguage().get(x).getMaudRecno() > max) {
//							max = primaryLanguageList.getPrimaryLanguage().get(x).getMaudRecno();
//						}
//					}
//
//					for (int x = 0; x < primaryLanguageList.getPrimaryLanguage().size(); x++) {
//						if (max != primaryLanguageList.getPrimaryLanguage().get(x).getMaudRecno()) {
//							idEntitySearchResult.getIdEntity().getClient().getPrimaryLanguageList().getPrimaryLanguage()
//									.remove(x);
//							--x;
//						}
//					}
//				}
//			}
//
//			//PrivilegedRecordList
//			PrivilegedRecordList privilegedRecordList = idEntitySearchResult.getIdEntity().getClient().getPrivilegedRecordList();
//			if (privilegedRecordList != null) {
//
//				if (privilegedRecordList.getPrivilegedRecord().size() > 1) {
//					long max = 0;
//					for (int x = 0; x < privilegedRecordList.getPrivilegedRecord().size(); x++) {
//						if (privilegedRecordList.getPrivilegedRecord().get(x).getMaudRecno() > max) {
//							max = privilegedRecordList.getPrivilegedRecord().get(x).getMaudRecno();
//						}
//					}
//
//					for (int x = 0; x < privilegedRecordList.getPrivilegedRecord().size(); x++) {
//						if (max != privilegedRecordList.getPrivilegedRecord().get(x).getMaudRecno()) {
//							idEntitySearchResult.getIdEntity().getClient().getPrivilegedRecordList().getPrivilegedRecord()
//									.remove(x);
//							--x;
//						}
//					}
//				}
//			}
//
//			//ProgramParticipationList
//			/*ProgramParticipationList programParticipationList = idEntitySearchResult.getIdEntity().getClient().getProgramParticipationList();
//			if (programParticipationList != null) {
//
//				if (programParticipationList.getProgramParticipation().size() > 1) {
//					long max = 0;
//					for (int x = 0; x < programParticipationList.getProgramParticipation().size(); x++) {
//						if (programParticipationList.getProgramParticipation().get(x).getMaudRecno() > max) {
//							max = programParticipationList.getProgramParticipation().get(x).getMaudRecno();
//						}
//					}
//
//					for (int x = 0; x < programParticipationList.getProgramParticipation().size(); x++) {
//						if (max != programParticipationList.getProgramParticipation().get(x).getMaudRecno()) {
//							idEntitySearchResult.getIdEntity().getClient().getProgramParticipationList().getProgramParticipation()
//									.remove(x);
//							--x;
//						}
//					}
//				}
//			}*/
//
//			//ProgramParticipationList
//			ProgramParticipationList programParticipationList = idEntitySearchResult.getIdEntity().getClient().getProgramParticipationList();
//			if (programParticipationList != null) {
//
//				if (programParticipationList.getProgramParticipation().size() > 1) {
//
//					//Unique programs based on program name
//					ProgramParticipationList uniqueProgramList  = new ProgramParticipationList();
//
//					for (int x = 0; x < programParticipationList.getProgramParticipation().size(); x++) {
//
//						boolean exist = false;
//						if(uniqueProgramList.getProgramParticipation().size() > 0 ) {
//							for(int y = 0; y < uniqueProgramList.getProgramParticipation().size(); y++) {
//								if (uniqueProgramList.getProgramParticipation().get(y).getProgramName()
//										.equalsIgnoreCase(programParticipationList.getProgramParticipation().get(x)
//												.getProgramName())) {
//									exist = true;
//								}
//							}
//
//						}
//						if(!exist) {
//							uniqueProgramList.getProgramParticipation().add(programParticipationList.getProgramParticipation().get(x));
//						}
//					}
//					idEntitySearchResult.getIdEntity().getClient().setProgramParticipationList(uniqueProgramList);
//
//				}
//			}
//
//			//RaceList
//			RaceList raceList = idEntitySearchResult.getIdEntity().getClient().getRaceList();
//			if (raceList != null) {
//
//				if (raceList.getRace().size() > 1) {
//					long max = 0;
//					for (int x = 0; x < raceList.getRace().size(); x++) {
//						if (raceList.getRace().get(x).getMaudRecno() > max) {
//							max = raceList.getRace().get(x).getMaudRecno();
//						}
//					}
//
//					for (int x = 0; x < raceList.getRace().size(); x++) {
//						if (max != raceList.getRace().get(x).getMaudRecno()) {
//							idEntitySearchResult.getIdEntity().getClient().getRaceList().getRace()
//									.remove(x);
//							--x;
//						}
//					}
//				}
//			}
//
//			//RentOrOwnList
//			RentOrOwnList rentOrOwnList = idEntitySearchResult.getIdEntity().getClient().getRentOrOwnList();
//			if (rentOrOwnList != null) {
//
//				if (rentOrOwnList.getRentOrOwn().size() > 1) {
//					long max = 0;
//					for (int x = 0; x < rentOrOwnList.getRentOrOwn().size(); x++) {
//						if (rentOrOwnList.getRentOrOwn().get(x).getMaudRecno() > max) {
//							max = rentOrOwnList.getRentOrOwn().get(x).getMaudRecno();
//						}
//					}
//
//					for (int x = 0; x < rentOrOwnList.getRentOrOwn().size(); x++) {
//						if (max != rentOrOwnList.getRentOrOwn().get(x).getMaudRecno()) {
//							idEntitySearchResult.getIdEntity().getClient().getRentOrOwnList().getRentOrOwn()
//									.remove(x);
//							--x;
//						}
//					}
//				}
//			}
//
//			//ResidenceTypeList
//			ResidenceTypeList residenceTypeList = idEntitySearchResult.getIdEntity().getClient().getResidenceTypeList();
//			if (residenceTypeList != null) {
//
//				if (residenceTypeList.getResidenceType().size() > 1) {
//					long max = 0;
//					for (int x = 0; x < residenceTypeList.getResidenceType().size(); x++) {
//						if (residenceTypeList.getResidenceType().get(x).getMaudRecno() > max) {
//							max = residenceTypeList.getResidenceType().get(x).getMaudRecno();
//						}
//					}
//
//					for (int x = 0; x < residenceTypeList.getResidenceType().size(); x++) {
//						if (max != residenceTypeList.getResidenceType().get(x).getMaudRecno()) {
//							idEntitySearchResult.getIdEntity().getClient().getResidenceTypeList().getResidenceType()
//									.remove(x);
//							--x;
//						}
//					}
//				}
//			}
//
//			//ResidentialStatusList
//			ResidentialStatusList residentialStatusList = idEntitySearchResult.getIdEntity().getClient().getResidentialStatusList();
//			if (residentialStatusList != null) {
//
//				if (residentialStatusList.getResidentialStatus().size() > 1) {
//					long max = 0;
//					for (int x = 0; x < residentialStatusList.getResidentialStatus().size(); x++) {
//						if (residentialStatusList.getResidentialStatus().get(x).getMaudRecno() > max) {
//							max = residentialStatusList.getResidentialStatus().get(x).getMaudRecno();
//						}
//					}
//
//					for (int x = 0; x < residentialStatusList.getResidentialStatus().size(); x++) {
//						if (max != residentialStatusList.getResidentialStatus().get(x).getMaudRecno()) {
//							idEntitySearchResult.getIdEntity().getClient().getResidentialStatusList().getResidentialStatus()
//									.remove(x);
//							--x;
//						}
//					}
//				}
//			}
//
//			//SSNList
//			SSNList sSNList = idEntitySearchResult.getIdEntity().getClient().getSSNList();
//			if (sSNList != null) {
//
//				if (sSNList.getSSN().size() > 1) {
//					long max = 0;
//					for (int x = 0; x < sSNList.getSSN().size(); x++) {
//						if (sSNList.getSSN().get(x).getMaudRecno() > max) {
//							max = sSNList.getSSN().get(x).getMaudRecno();
//						}
//					}
//
//					for (int x = 0; x < sSNList.getSSN().size(); x++) {
//						if (max != sSNList.getSSN().get(x).getMaudRecno()) {
//							idEntitySearchResult.getIdEntity().getClient().getSSNList().getSSN()
//									.remove(x);
//							--x;
//						}
//					}
//				}
//			}
//
//			//SchoolNameList
//			SchoolNameList schoolNameList = idEntitySearchResult.getIdEntity().getClient().getSchoolNameList();
//			if (schoolNameList != null) {
//
//				if (schoolNameList.getSchoolName().size() > 1) {
//					long max = 0;
//					for (int x = 0; x < schoolNameList.getSchoolName().size(); x++) {
//						if (schoolNameList.getSchoolName().get(x).getMaudRecno() > max) {
//							max = schoolNameList.getSchoolName().get(x).getMaudRecno();
//						}
//					}
//
//					for (int x = 0; x < schoolNameList.getSchoolName().size(); x++) {
//						if (max != schoolNameList.getSchoolName().get(x).getMaudRecno()) {
//							idEntitySearchResult.getIdEntity().getClient().getSchoolNameList().getSchoolName()
//									.remove(x);
//							--x;
//						}
//					}
//				}
//			}
//
//			//SecondaryLanguageList
//			SecondaryLanguageList secondaryLanguageList = idEntitySearchResult.getIdEntity().getClient().getSecondaryLanguageList();
//			if (secondaryLanguageList != null) {
//
//				if (secondaryLanguageList.getSecondaryLanguage().size() > 1) {
//					long max = 0;
//					for (int x = 0; x < secondaryLanguageList.getSecondaryLanguage().size(); x++) {
//						if (secondaryLanguageList.getSecondaryLanguage().get(x).getMaudRecno() > max) {
//							max = secondaryLanguageList.getSecondaryLanguage().get(x).getMaudRecno();
//						}
//					}
//
//					for (int x = 0; x < secondaryLanguageList.getSecondaryLanguage().size(); x++) {
//						if (max != secondaryLanguageList.getSecondaryLanguage().get(x).getMaudRecno()) {
//							idEntitySearchResult.getIdEntity().getClient().getSecondaryLanguageList().getSecondaryLanguage()
//									.remove(x);
//							--x;
//						}
//					}
//				}
//			}
//
//			//SpokenLanguageList
//			SpokenLanguageList spokenLanguageList = idEntitySearchResult.getIdEntity().getClient().getSpokenLanguageList();
//			if (spokenLanguageList != null) {
//
//				if (spokenLanguageList.getSpokenLanguage().size() > 1) {
//					long max = 0;
//					for (int x = 0; x < spokenLanguageList.getSpokenLanguage().size(); x++) {
//						if (spokenLanguageList.getSpokenLanguage().get(x).getMaudRecno() > max) {
//							max = spokenLanguageList.getSpokenLanguage().get(x).getMaudRecno();
//						}
//					}
//
//					for (int x = 0; x < spokenLanguageList.getSpokenLanguage().size(); x++) {
//						if (max != spokenLanguageList.getSpokenLanguage().get(x).getMaudRecno()) {
//							idEntitySearchResult.getIdEntity().getClient().getSpokenLanguageList().getSpokenLanguage()
//									.remove(x);
//							--x;
//						}
//					}
//				}
//			}
//
//			//StatusofCitizenshipList
//			StatusofCitizenshipList statusofCitizenshipList = idEntitySearchResult.getIdEntity().getClient().getStatusofCitizenshipList();
//			if (statusofCitizenshipList != null) {
//
//				if (statusofCitizenshipList.getStatusofCitizenship().size() > 1) {
//					long max = 0;
//					for (int x = 0; x < statusofCitizenshipList.getStatusofCitizenship().size(); x++) {
//						if (statusofCitizenshipList.getStatusofCitizenship().get(x).getMaudRecno() > max) {
//							max = statusofCitizenshipList.getStatusofCitizenship().get(x).getMaudRecno();
//						}
//					}
//
//					for (int x = 0; x < statusofCitizenshipList.getStatusofCitizenship().size(); x++) {
//						if (max != statusofCitizenshipList.getStatusofCitizenship().get(x).getMaudRecno()) {
//							idEntitySearchResult.getIdEntity().getClient().getStatusofCitizenshipList().getStatusofCitizenship()
//									.remove(x);
//							--x;
//						}
//					}
//				}
//			}
//
//			//TribeList
//			TribeList tribeList = idEntitySearchResult.getIdEntity().getClient().getTribeList();
//			if (tribeList != null) {
//
//				if (tribeList.getTribe().size() > 1) {
//					long max = 0;
//					for (int x = 0; x < tribeList.getTribe().size(); x++) {
//						if (tribeList.getTribe().get(x).getMaudRecno() > max) {
//							max = tribeList.getTribe().get(x).getMaudRecno();
//						}
//					}
//
//					for (int x = 0; x < tribeList.getTribe().size(); x++) {
//						if (max != tribeList.getTribe().get(x).getMaudRecno()) {
//							idEntitySearchResult.getIdEntity().getClient().getTribeList().getTribe()
//									.remove(x);
//							--x;
//						}
//					}
//				}
//			}
//
//			//VeteranStatusList
//			VeteranStatusList veteranStatusList = idEntitySearchResult.getIdEntity().getClient().getVeteranStatusList();
//			if (veteranStatusList != null) {
//
//				if (veteranStatusList.getVeteranStatus().size() > 1) {
//					long max = 0;
//					for (int x = 0; x < veteranStatusList.getVeteranStatus().size(); x++) {
//						if (veteranStatusList.getVeteranStatus().get(x).getMaudRecno() > max) {
//							max = veteranStatusList.getVeteranStatus().get(x).getMaudRecno();
//						}
//					}
//
//					for (int x = 0; x < veteranStatusList.getVeteranStatus().size(); x++) {
//						if (max != veteranStatusList.getVeteranStatus().get(x).getMaudRecno()) {
//							idEntitySearchResult.getIdEntity().getClient().getVeteranStatusList().getVeteranStatus()
//									.remove(x);
//							--x;
//						}
//					}
//				}
//			}
//
//			//VisaTypeList
//			VisaTypeList visaTypeList = idEntitySearchResult.getIdEntity().getClient().getVisaTypeList();
//			if (visaTypeList != null) {
//
//				if (visaTypeList.getVisaType().size() > 1) {
//					long max = 0;
//					for (int x = 0; x < visaTypeList.getVisaType().size(); x++) {
//						if (visaTypeList.getVisaType().get(x).getMaudRecno() > max) {
//							max = visaTypeList.getVisaType().get(x).getMaudRecno();
//						}
//					}
//
//					for (int x = 0; x < visaTypeList.getVisaType().size(); x++) {
//						if (max != visaTypeList.getVisaType().get(x).getMaudRecno()) {
//							idEntitySearchResult.getIdEntity().getClient().getVisaTypeList().getVisaType()
//									.remove(x);
//							--x;
//						}
//					}
//				}
//			}
//
//			//WorkAddressList
//			WorkAddressList workAddressList = idEntitySearchResult.getIdEntity().getClient().getWorkAddressList();
//			if (workAddressList != null) {
//
//				if (workAddressList.getWorkAddress().size() > 1) {
//					long max = 0;
//					for (int x = 0; x < workAddressList.getWorkAddress().size(); x++) {
//						if (workAddressList.getWorkAddress().get(x).getMaudRecno() > max) {
//							max = workAddressList.getWorkAddress().get(x).getMaudRecno();
//						}
//					}
//
//					for (int x = 0; x < workAddressList.getWorkAddress().size(); x++) {
//						if (max != workAddressList.getWorkAddress().get(x).getMaudRecno()) {
//							idEntitySearchResult.getIdEntity().getClient().getWorkAddressList().getWorkAddress()
//									.remove(x);
//							--x;
//						}
//					}
//				}
//			}
//
//			//WorkPhoneList
//			WorkPhoneList workPhoneList = idEntitySearchResult.getIdEntity().getClient().getWorkPhoneList();
//			if (workPhoneList != null) {
//
//				if (workPhoneList.getWorkPhone().size() > 1) {
//					long max = 0;
//					for (int x = 0; x < workPhoneList.getWorkPhone().size(); x++) {
//						if (workPhoneList.getWorkPhone().get(x).getMaudRecno() > max) {
//							max = workPhoneList.getWorkPhone().get(x).getMaudRecno();
//						}
//					}
//
//					for (int x = 0; x < workPhoneList.getWorkPhone().size(); x++) {
//						if (max != workPhoneList.getWorkPhone().get(x).getMaudRecno()) {
//							idEntitySearchResult.getIdEntity().getClient().getWorkPhoneList().getWorkPhone()
//									.remove(x);
//							--x;
//						}
//					}
//				}
//			}
//
//		}

//		return idEntitySearchResults;
//	}
}
