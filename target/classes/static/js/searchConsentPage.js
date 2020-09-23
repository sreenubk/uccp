app.controller('searchConsentCtrl', ['$scope', '$rootScope','$uibModal', '$http',  '$location','myVars',function($scope, $rootScope,$uibModal, $http,$location,myVars) {
	$rootScope.viewControlGroupView = 1;
	$rootScope.showNavbar = true;
	$scope.master = [];
	//$scope.myData = [];
	$scope.selectedId = null;
	$rootScope.selectedName = null;
	$scope.sucessMsg="";
	$scope.errorMsg="";
	$rootScope.editerrorMsg="";
	$rootScope.addGroup = null;
	$rootScope.existingGroup =  false;
	$rootScope.existAttrData = null;
	$rootScope.existTranData = null;
	
	var config = {
            headers : {
                'Content-Type': 'application/json'
            }
        }
	/*$http.post(myVars.site_prefix +" /consent/listConsentPerson/", null, config)
	 .then(
	       function(response){
	    	   $rootScope.personConsentData =  response.data;
	    	  // $scope.sucessMsg="File Uploaded successfully.";
	       }, 
	       function(response){
	          //var msg = response.data.message;
	          $scope.errorMsg='Unable to load person consent data :: Error';
	          
	       }
	    );*/
	
	$http.post(myVars.site_prefix +"/consent/listConsentGroup/", null, config)
	 .then(
	       function(response){
	    	   $rootScope.listConsentGroup =  response.data;
	         
	       }, 
	       function(response){
	          var msg = response.data.message;
	          $scope.errorMsg='Unable to load Consent Categories :: Error';
	          
	       }
	    );
	$http.get(myVars.site_prefix +"/auth/getUserList/")
	   .then(
	       function(response){
	    	   $rootScope.userLists =  response.data;
	         
	       }, 
	       function(response){
	          var msg = response.data.message;
	          $scope.errorMsg='Unable to load user list :: Error  ';
	          
	       }
	    );

	$scope.search = function(startdate, enddate, custName){
		$rootScope.existingGroup =  false;
		$rootScope.existAttrData = null;
		$rootScope.existTranData = null;
		$rootScope.baseserstartdate = startdate;
		$rootScope.baseserenddate = enddate;
		$rootScope.basesercustName = custName;
		var data = {
		        "personconsentid": null,
		        "concernroleid": null,
		        "fileid": null,
		        "startdate": $rootScope.baseserstartdate,
		        "enddate": $rootScope.baseserenddate,
		        "thirdpartyrelationship": null,
		        "thirdpartyfirstname": null,
		        "thirdpartylastname": null,
		        "thirdpartyotherrelationship": null,
		        "identificationno": null,
		        "identificationtype": null,
		        "identificationother": null,
		        "contactaddressid": null,
		        "contactphone": null,
		        "authpersonnameone": null,
		        "authpersonrelone": null,
		        "authpersoncontactinfoone": null,
		        "authpersonnametwo": null,
		        "authpersonreltwo": null,
		        "authpersoncontactinfotwo": null,
		        "createdby": null,
		        "createdon": null,
		        "lastupdatedby": null,
		        "lastupdatedon": null,
		        "zipCode": null,
		        "consentCatList": null,
		        "consentUser": null,
		        "customerName": $rootScope.basesercustName,
		        "state": null,
		        "street": null,
		        "appSuite": null,
		        "city": null
		    }; 

    	data=JSON.stringify(data);

        var config = {
            headers : {
                'Content-Type': 'application/json'
            }
        }

    	console.log("search data is json object ---- "+data);
    	
    	$http.post(myVars.site_prefix +"/consent/listConsentPersonByCriteria/", data, config)
		   .then(
		       function(response){
		    	   if(response.data.message){
			        	 if(response.data.message=='NO Group Found.'){
			        		 	$scope.sucessMsg="No Control Group with this name exists. Please try searching with other name.";
				          		
			        	 }
			        	 
			         }else{
			        	 $scope.sucessMsg="";
			        	 $scope.myData = response.data;
			         }
		         
		       }, 
		       function(response){
		    	   $scope.errorMsg="Unable to search with given group name:: Error";
		         
		       }
		    );
	 
	} //end of search

	$scope.reset = function() {
		$scope.sucessMsg="";
		$scope.errorMsg="";
		$scope.myData = [];
        $scope.ctrlGroup = angular.copy($scope.master);
    };

    
    /** Start of Edit Person Consent **/
    $scope.open = function(cpersonconsentid, ciseditAllow, cstartdate, cenddate, cconsentCatList, ccustomerName,cclientid, ccontactphone, cstreet, cappSuite, ccity, cstate, czipCode, cidentificationno, cidentificationtype, cthirdpartyrelationship,cthirdpartyfirstname,cthirdpartyotherrelationship, cthirdpartylastname, cfileid) {
    	$rootScope.epersonconsentid = cpersonconsentid;
    	$rootScope.eiseditAllow = ciseditAllow;
    	$rootScope.estartdate = cstartdate;
    	$rootScope.eenddate = cenddate;
    	$scope.consentCatList = cconsentCatList;
    	$rootScope.ecustomerName = ccustomerName;
    	$rootScope.eclientid = cclientid;
    	console.log("clientid"+$rootScope.eclientid);
    	$rootScope.econtactphone = ccontactphone;
    	$rootScope.estreet = cstreet;
    	$rootScope.eappSuite = cappSuite;
    	$rootScope.ecity = ccity;
    	$rootScope.estate = cstate;
    	$rootScope.ezipCode = czipCode;
    	$rootScope.eidentificationno = cidentificationno;
    	$rootScope.eidentificationtype = cidentificationtype;
    	$rootScope.ethirdpartyrelationship = cthirdpartyrelationship;
    	$rootScope.ethirdpartyfirstname = cthirdpartyfirstname;
    	$rootScope.ethirdpartyotherrelationship = cthirdpartyotherrelationship;
    	$rootScope.ethirdpartylastname = cthirdpartylastname;
    	$rootScope.efileid = cfileid;
    	$rootScope.showDetails = function(hidediv){
    		if(hidediv=="upload1"){
    			$("#upload2").removeClass("hide");
    			$("#upload1").addClass("hide");
    			$rootScope.sendfile=true;
    		}else{
    			$("#upload1").removeClass("hide");
    			$("#upload2").addClass("hide");
    			$rootScope.sendfile=false;
    		}
    	}
    	/*
    	if($rootScope.iseditAllow ==false){
    		$rootScope.editerrorMsg="End Date is less than Current Date So You could not edit it";
    		$rootScope.isSaving = true;
    	}else{
    		$rootScope.editerrorMsg="";
    		$rootScope.isSaving = false;
    	}*/
    	
    	 
		//var updatedorganisationunitid = updatedorganisation[1];
		//var updatedorganisationname =  updatedorganisation[0];
    	
    	//Method for selecting checkbox
 	   
    	$scope.selectedCheckBox = [];
    	$scope.checkBoxModal = []
    	$scope.selCheckError = false;
    	$scope.toggleCheckBox = function(x){
    	    var index = $scope.selectedCheckBox.indexOf(x);
    	    
    	    if(index == -1) {
    	    	//$scope.selCheckError = true;
    	    	$scope.selectedCheckBox.push(x);
    	    	//console.log("checkbox checked"+$scope.selectedCheckBox);
    	    }
    	    else{
    	      $scope.selectedCheckBox.splice(index, 1);
    	      //$scope.errorMsg='At least one checkbox must be selected.';
    	      //console.log("checkbox checked"+$scope.selectedCheckBox);
    	    }
    	}
    	
	    var modalinstance = $uibModal.open({
	      scope: $scope,
	      templateUrl: 'editConsentModal.html',
	      resolve: {
	        myData: function() {
	        	if ($scope.consentCatList!=null){
		        	var splitconsentCatList = $scope.consentCatList.split(",");
		        	console.log("split"+splitconsentCatList);
		        	var arr=[];
		        	angular.forEach(splitconsentCatList, function(item) {
		        		var count=0;
		        		angular.forEach($scope.listConsentGroup, function(itemper) {
			        		if(item == itemper.consentgroupid){
			        			//$scope.listConsentGroup[count].Selected=true;
			        			arr.push(count);
			        		}else{
			        			$scope.listConsentGroup[count].Selected=false;
			        		}
			        		count++;
			        	});
		        	});
		        	
		        	return angular.forEach(arr, function(itemcount) {
		        		$scope.listConsentGroup[itemcount].Selected=true;
		        	});
	        	}
	        
	        }
	      }
	    });
	    
	    	
	    
  	};
  	
  	$scope.changeStartDate = function(){
  		var cdateToday = new Date();
  		var strDate = (cdateToday.getMonth()+1) + "/" + cdateToday.getDate() + "/" + cdateToday.getFullYear();
  		var startDate = $("#startdate").val();
  		var inistartDate = $("#startdate").val();
	   
	       if (startDate != '') {
	           if (Date.parse(startDate) < Date.parse(strDate)) {
	        	   $("#startdate").val(strDate);
	        	   document.getElementById('err-startdate').innerHTML = "The start date expired. Start date can the current date or future date.";
	        	   return false;
	           }else{
	        	   $("#startdate").val(inistartDate);
	        	   document.getElementById('err-startdate').innerHTML ="";
	           }
	       }
  	};

  	$scope.uploadedFile = function(element) {
		$rootScope.files = [];
		document.getElementById('err-file').innerHTML = "";
		 $scope.$apply(function($scope) {
		   if(element.files.length!=0){
			   
			   if(element.files[0].type!="application/pdf"){
				   document.getElementById('err-file').innerHTML = "Please select a pdf file to upload.";
				   return false;
				   
			   }else if(element.files[0].size > 1000000){
				   document.getElementById('err-file').innerHTML = "File size should not exceed 1MB.";
				   return false;
			   }
			   else{
				   $rootScope.files = element.files; 
			   }
			   
		   }
		   
		           
		 });
		}
  	$scope.editSave = function(cstartdate, cenddate, cconsentCatList, ccustomerName, ccontactphone, cstreet, cappSuite, ccity, cstate, czipCode, cidentificationno, cidentificationtype, cthirdpartyrelationship,cthirdpartyfirstname,cthirdpartyotherrelationship, cthirdpartylastname, cfileid){
  		//console.log("updatedName="+updatedName);
    	// use $.param jQuery function to serialize data from JSON 
  		
  		$scope.sucessMsg="";
		$scope.errorMsg="";
		document.getElementById('err-startDate').innerHTML = "";
		document.getElementById('err-endDate').innerHTML = "";
		document.getElementById('err-selCheckBox').innerHTML = "";
		document.getElementById('err-enddate').innerHTML = "";
		document.getElementById('err-customerName').innerHTML = "";
		document.getElementById('err-street').innerHTML = "";
		document.getElementById('err-suite').innerHTML = "";
		document.getElementById('err-city').innerHTML = "";
		document.getElementById('err-state').innerHTML = "";
		document.getElementById('err-zipCode').innerHTML = "";
		document.getElementById('err-thirdpartyrelationship').innerHTML = "";
		document.getElementById('err-thirdpartyotherrelationship').innerHTML = "";
		var upload=document.editGroupForm.elements;
		
		var startDate = $("#startdate").val();
	    var endDate = $("#enddate").val();
	       if (startDate != '' && endDate !='') {
	           if (Date.parse(startDate) > Date.parse(endDate)) {
	        	   $("#enddate").val('');
	        	   document.getElementById('err-enddate').innerHTML = "End date should be greater than Start date";
	        	   return false;
	           }
	       }
	       
	       //checking the state of checkbox and show message is none selected
	       var hasChecked = false;
           var chkBox = $(':checkbox');
           for (i = 0; i < chkBox.length; i++) {

               if (chkBox[i].checked) {

                   hasChecked = true;

                   break;

               }

           }
         //Show error message if empty
		if (upload.customerName.value.length == 0)
		{
			document.getElementById('err-enddate').innerHTML = "Please enter Customer Name.";
			upload.customerName.focus();
			return false;
		}else if (upload.customerName.value.length == 0)
		{
			document.getElementById('err-customerName').innerHTML = "Please enter Customer Name.";
			upload.customerName.focus();
			return false;
		}else if (upload.street.value.length == 0)
		{
			document.getElementById('err-street').innerHTML = "Please enter Street.";
			upload.street.focus();
			return false;
		}else if (upload.suite.value.length == 0)
		{
			document.getElementById('err-suite').innerHTML = "Please enter App/Suite.";
			upload.suite.focus();
			return false;
		}else if (upload.city.value.length == 0)
		{
			document.getElementById('err-city').innerHTML = "Please enter City.";
			upload.city.focus();
			return false;
		}else if (upload.state.value.length == 0)
		{
			document.getElementById('err-state').innerHTML = "Please select State.";
			upload.state.focus();
			return false;
		}else if (upload.zipCode.value.length == 0)
		{
			document.getElementById('err-zipCode').innerHTML = "Please enter Zip Code.";
			upload.zipCode.focus();
			return false;
		}else if (!hasChecked) {
	     	   chkBox[0].focus();
	    	   document.getElementById('err-selCheckBox').innerHTML = "At least one checkbox must be selected.";
	           return false;
       }else if (upload.startdate.value.length == 0)
		{
			document.getElementById('err-startDate').innerHTML = "Please enter Start Date.";
			upload.startdate.focus();
			return false;
		}else if (upload.enddate.value.length == 0)
		{
			document.getElementById('err-endDate').innerHTML = "Please enter End Date.";
			upload.enddate.focus();
			return false;
		}/*else if (upload.thirdpartyrelationship.value.length == 0)
		{
			document.getElementById('err-thirdpartyrelationship').innerHTML = "Please select a Third Party Relationship.";
			upload.thirdpartyrelationship.focus();
			return false;
		}*/ else if (upload.thirdpartyrelationship.value == "Other" && upload.thirdpartyotherrelationship.value.length == 0)
		{
			document.getElementById('err-thirdpartyotherrelationship').innerHTML = "Please enter Third Party Other Relationship.";
			upload.thirdpartyotherrelationship.focus();
			return false;
		}
		
		var categoryStr = $('.consentCatCheck:checked').map(function() {
		    return this.value;
		}).get().join();
		
		if($scope.files && $rootScope.sendfile){
			if($scope.files.length!=0){
			var fd = new FormData();
			
			var url = '/consent/uploadFile';
		 
		 

			 angular.forEach($scope.files,function(file){
				 fd.append('file',file);
			 });
	
			 
			 
	
			 $http.post(url, fd, {
				  withCredentials : false,
				  headers : {
				  'Content-Type' : undefined
				  },
				  transformRequest : angular.identity
			 })
			 .then(
					 function(response){
						 	$scope.fileid=response.data;
				        	// $scope.sucessMsg="File Uploaded successfully.";
						 	
						 	/*** Satrt of form data submit  after file is uploaded **/
		
  		var data = {
		        "personconsentid": $rootScope.epersonconsentid,
		        "concernroleid": null,
		        "fileid": $scope.fileid,
		        "startdate": cstartdate,
		        "enddate": cenddate,
		        "iseditAllow":null,
		        "thirdpartyrelationship": cthirdpartyrelationship,
		        "thirdpartyfirstname": cthirdpartyfirstname,
		        "thirdpartylastname": cthirdpartylastname,
		        "thirdpartyotherrelationship": cthirdpartyotherrelationship,
		        "identificationno": cidentificationno,
		        "identificationtype": cidentificationtype,
		        "identificationother": null,
		        "contactaddressid": null,
		        "contactphone": ccontactphone,
		        "authpersonnameone": null,
		        "authpersonrelone": null,
		        "authpersoncontactinfoone": null,
		        "authpersonnametwo": null,
		        "authpersonreltwo": null,
		        "authpersoncontactinfotwo": null,
		        "createdby": null,
		        "createdon": null,
		        "lastupdatedby": null,
		        "lastupdatedon": null,
		        "zipCode": czipCode,
		        "consentCatList": categoryStr,
		        "consentUser": null,
		        "customerName": ccustomerName,
		        "state": cstate,
		        "street": cstreet,
		        "appSuite": cappSuite,
		        "city": ccity
		    }; 

    	data=JSON.stringify(data);
    	
    	var config = {
            headers : {
                'Content-Type': 'application/json'
            }
        }

    	console.log("selected data is json object ---- "+data);
    	
    	$http.post(myVars.site_prefix +"/consent/editConsPerson/", data, config)
		   .then(
		       function(response){
		    	   if(response.data.message!='Fail'){
				    	 $scope.sucessMsg='Successfully updated the Person Consent Info';
				    	 
				    	 /*var cout = 0;
				    	 angular.forEach($scope.myData, function (item) {
				    	        
				    		 	
			    	            if (item.personconsentid == response.data.personconsentid) {
			    	            	$scope.myData[cout] = response.data;
			    	            }
			    	            cout++;
				    	 });*/
				    	 
				    	 
				    	 /*** Start of making search to update search table ****/
				    	 var data = {
				 		        "personconsentid": null,
				 		        "concernroleid": null,
				 		        "fileid": null,
				 		        "startdate": $rootScope.baseserstartdate,
				 		        "enddate": $rootScope.baseserenddate,
				 		        "thirdpartyrelationship": null,
				 		        "thirdpartyfirstname": null,
				 		        "thirdpartylastname": null,
				 		        "thirdpartyotherrelationship": null,
				 		        "identificationno": null,
				 		        "identificationtype": null,
				 		        "identificationother": null,
				 		        "contactaddressid": null,
				 		        "contactphone": null,
				 		        "authpersonnameone": null,
				 		        "authpersonrelone": null,
				 		        "authpersoncontactinfoone": null,
				 		        "authpersonnametwo": null,
				 		        "authpersonreltwo": null,
				 		        "authpersoncontactinfotwo": null,
				 		        "createdby": null,
				 		        "createdon": null,
				 		        "lastupdatedby": null,
				 		        "lastupdatedon": null,
				 		        "zipCode": null,
				 		        "consentCatList": null,
				 		        "consentUser": null,
				 		        "customerName": $rootScope.basesercustName,
				 		        "state": null,
				 		        "street": null,
				 		        "appSuite": null,
				 		        "city": null
				 		    }; 

				     	data=JSON.stringify(data);

				         var config = {
				             headers : {
				                 'Content-Type': 'application/json'
				             }
				         }

				     	console.log("search data is json object ---- "+data);
				     	
				     	$http.post(myVars.site_prefix +"/consent/listConsentPersonByCriteria/", data, config)
				 		   .then(
				 		       function(response){
				 		    	   if(response.data.message){
				 			        	 if(response.data.message=='NO Group Found.'){
				 			        		 	$scope.sucessMsg="No Control Group with this name exists. Please try searching with other name.";
				 				          		
				 			        	 }
				 			        	 
				 			         }else{
				 			        	 $scope.sucessMsg="";
				 			        	 $scope.myData = response.data;
				 			         }
				 		         
				 		       }, 
				 		       function(response){
				 		    	   $scope.errorMsg="Unable to search with given group name:: Error";
				 		         
				 		       }
				 		    );
				    	 
				    	 
				    	 /*** End of making search to update search table ****/
				    	 
				    	 
		    	   } else {
		    		   $scope.errorMsg='Unable to update the Person Consent Info:: Error ';
		    	   }
		       }, 
		       function(response){
		    	   $scope.errorMsg='Unable to update the Person Consent Info:: Error ';
		         
		       }
		    );
    	/*** End  of form data submit  after file is uploaded **/
		
		 console.log(response);
	 },
	 function(response) {
		
			 $scope.errorMsg="Unable to upload file. Please try again.";
		
		 console.log(response);
	 }
);
}
} // end of files check empty
		
		
		else{
			var data = {
			        "personconsentid": $rootScope.epersonconsentid,
			        "concernroleid": null,
			        "fileid": $rootScope.efileid,
			        "startdate": cstartdate,
			        "enddate": cenddate,
			        "iseditAllow":null,
			        "thirdpartyrelationship": cthirdpartyrelationship,
			        "thirdpartyfirstname": cthirdpartyfirstname,
			        "thirdpartylastname": cthirdpartylastname,
			        "thirdpartyotherrelationship": cthirdpartyotherrelationship,
			        "identificationno": cidentificationno,
			        "identificationtype": cidentificationtype,
			        "identificationother": null,
			        "contactaddressid": null,
			        "contactphone": ccontactphone,
			        "authpersonnameone": null,
			        "authpersonrelone": null,
			        "authpersoncontactinfoone": null,
			        "authpersonnametwo": null,
			        "authpersonreltwo": null,
			        "authpersoncontactinfotwo": null,
			        "createdby": null,
			        "createdon": null,
			        "lastupdatedby": null,
			        "lastupdatedon": null,
			        "zipCode": czipCode,
			        "consentCatList": categoryStr,
			        "consentUser": null,
			        "customerName": ccustomerName,
			        "state": cstate,
			        "street": cstreet,
			        "appSuite": cappSuite,
			        "city": ccity
			    }; 

	    	data=JSON.stringify(data);
	    	
	    	var config = {
	            headers : {
	                'Content-Type': 'application/json'
	            }
	        }

	    	console.log("selected data is json object ---- "+data);
	    	
	    	$http.post(myVars.site_prefix +"/consent/editConsPerson/", data, config)
			   .then(
			       function(response){
			    	   if(response.data.message!='Fail'){
					    	 $scope.sucessMsg='Successfully updated the Person Consent Info';
					    	 
					    	 /*var cout = 0;
					    	 angular.forEach($scope.myData, function (item) {
					    	        
					    		 	
				    	            if (item.personconsentid == response.data.personconsentid) {
				    	            	$scope.myData[cout] = response.data;
				    	            }
				    	            cout++;
					    	 });*/
					    	 
					    	 
					    	 
					    	 /*** Start of making search to update search table ****/
					    	 var data = {
					 		        "personconsentid": null,
					 		        "concernroleid": null,
					 		        "fileid": null,
					 		        "startdate": $rootScope.baseserstartdate,
					 		        "enddate": $rootScope.baseserenddate,
					 		        "thirdpartyrelationship": null,
					 		        "thirdpartyfirstname": null,
					 		        "thirdpartylastname": null,
					 		        "thirdpartyotherrelationship": null,
					 		        "identificationno": null,
					 		        "identificationtype": null,
					 		        "identificationother": null,
					 		        "contactaddressid": null,
					 		        "contactphone": null,
					 		        "authpersonnameone": null,
					 		        "authpersonrelone": null,
					 		        "authpersoncontactinfoone": null,
					 		        "authpersonnametwo": null,
					 		        "authpersonreltwo": null,
					 		        "authpersoncontactinfotwo": null,
					 		        "createdby": null,
					 		        "createdon": null,
					 		        "lastupdatedby": null,
					 		        "lastupdatedon": null,
					 		        "zipCode": null,
					 		        "consentCatList": null,
					 		        "consentUser": null,
					 		        "customerName": $rootScope.basesercustName,
					 		        "state": null,
					 		        "street": null,
					 		        "appSuite": null,
					 		        "city": null
					 		    }; 

					     	data=JSON.stringify(data);

					         var config = {
					             headers : {
					                 'Content-Type': 'application/json'
					             }
					         }

					     	console.log("search data is json object ---- "+data);
					     	
					     	$http.post(myVars.site_prefix +"/consent/listConsentPersonByCriteria/", data, config)
					 		   .then(
					 		       function(response){
					 		    	   if(response.data.message){
					 			        	 if(response.data.message=='NO Group Found.'){
					 			        		 	$scope.sucessMsg="No Control Group with this name exists. Please try searching with other name.";
					 				          		
					 			        	 }
					 			        	 
					 			         }else{
					 			        	 $scope.sucessMsg="";
					 			        	 $scope.myData = response.data;
					 			         }
					 		         
					 		       }, 
					 		       function(response){
					 		    	   $scope.errorMsg="Unable to search with given group name:: Error";
					 		         
					 		       }
					 		    );
					    	 
					    	 
					    	 /*** End of making search to update search table ****/
					    	 
					    	 
					    	 
			    	   } else {
			    		   $scope.errorMsg='Unable to update the Person Consent Info:: Error ';
			    	   }
			       }, 
			       function(response){
			    	   $scope.errorMsg='Unable to update the Person Consent Info:: Error ';
			         
			       }
			    );
		}
		
		
		
    	return true;
    };
    /** End of Edit Person Consent **/
   

}]);