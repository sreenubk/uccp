app.controller('personConsentCtrl', ['$scope', '$rootScope','$uibModal', '$http',  '$location','myVars', function($scope, $rootScope,$uibModal, $http,$location,myVars) {
	$rootScope.viewControlGroupView = 6;
	$rootScope.showNavbar = true;
	$scope.master = [];
	$scope.selCustomer ="";
	$scope.selClientID = "";
	var config = {
	            headers : {
	                'Content-Type': 'application/json'
	            }
	        }
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
	$http.post(myVars.site_prefix +"/consent/getRefList/", null, config)
	 .then(
	       function(response){
	    	   $rootScope.listRefData =  response.data;
	    	   //console.log("ref data"+$rootScope.listRefData);
	    	 //Method for retrieving client id based on Customer name selected
	    	   $scope.getId = function(){
	   		    var co = $scope.listRefData.filter(function (el) {  return el.customerName == $scope.customerNameModal });
	   		      if(typeof co !== 'undefined' && co.length > 0){
	   		        $scope.selClientID =co[0].clientID;
	   		        $scope.selCustomer =co[0].customerName;
	   		        console.log();
	   		      }else{
	   		        $scope.selClientID = null;
	   		      }
	   		  };
	   		  
	   		  $scope.$watch('customerNameModal',function(n,o){
	   			    $scope.getId();
	   			  });
	         
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
	
	
	
	//Method for selecting checkbox
	   
	$scope.selectedCheckBox = [];
	$scope.checkBoxModal = [];
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
	
	$scope.uploadedFile = function(element) {
		$scope.files = [];
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
				   $scope.files = element.files; 
			   }
			   
		   }
		   
		           
		 });
		}
	/*
	$scope.addFile = function() {
		$scope.sucessMsg="";
		$scope.errorMsg="";
		
		if($scope.files){
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
				        	 $scope.sucessMsg="File Uploaded successfully.";
						
						 console.log(response);
					 },
					 function(response) {
						
							 $scope.errorMsg="Unable to upload file :: Error";
						
						 console.log(response);
					 }
			 );
		 } // end of files check empty
		 
	}*/ // end of addFile
	
	$scope.resetConsent = function(){
		$scope.sucessMsg="";
		$scope.errorMsg="";
		 $scope.selClientID = "";
		document.getElementById("upload").reset(); 
	}
	
	$scope.submitFormData = function(){
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
		
		var startDate = $("#startdate").val();
	    var endDate = $("#enddate").val();
	       if (startDate != '' && endDate !='') {
	           if (Date.parse(startDate) > Date.parse(endDate)) {
	        	   $("#enddate").val('');
	        	   document.getElementById('err-enddate').innerHTML = "End date should be greater than Start date";
	        	   return false;
	           }
	       }
	       
	    // console.log($scope.selectedCheckBox.length);  
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
			document.getElementById('err-customerName').innerHTML = "Please enter Customer Identifier.";
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
		}
		/*else if (upload.thirdpartyrelationship.value.length == 0)
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
		
		var categoryStr = $('.checkBoxModal:checked').map(function() {
		    return this.value;
		}).get().join();
		//console.log("category"+categoryStr);
		
		//$scope.addFile();
		
		if($scope.files){
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
						 	
						 	/*** Start of form data submit  after file is uploaded **/
						 	
						 	var data = {
							        "personconsentid": null,
							        "clientid": $scope.selClientID,
							        "concernroleid": null,
							        "fileid": $scope.fileid,
							        "startdate": $scope.startdate,
							        "enddate": $scope.enddate,
							        "thirdpartyrelationship": $scope.thirdpartyrelationship,
							        "thirdpartyfirstname": $scope.thirdpartyfirstname,
							        "thirdpartylastname": $scope.thirdpartylastname,
							        "thirdpartyotherrelationship": $scope.thirdpartyotherrelationship,
							        "identificationno": $scope.identificationno,
							        "identificationtype": $scope.identificationtype,
							        "identificationother": null,
							        "contactaddressid": null,
							        "contactphone": $scope.contactphone,
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
							        "zipCode": $scope.zipCode,
							        "consentCatList": categoryStr,
							        "consentUser": null,
							        "customerName": $scope.selCustomer,
							        "state": $scope.state,
							        "street": $scope.street,
							        "appSuite": $scope.appSuite,
							        "city": $scope.city
							    }; 
							
							data=JSON.stringify(data);

					        var config = {
					            headers : {
					                'Content-Type': 'application/json'
					            }
					        }

					    	console.log("submitFormData is json object ---- "+data);
					         
					    	$http.post(myVars.site_prefix +"/consent/saveConsPerson/", data, config)
							   .then(
							       function(response){
							    	   if(response.data.message){
								        	 if(response.data.message=='Fail'){
								        		 	$scope.errorMsg="Unable to submit Person Consent data :: Error";
									          		
								        	 }
								        	 if(response.data.message=='duplicate'){
								        		 	$scope.errorMsg="Customer already Present";
								        	 }
								        	 
								         }else if(response.data!=''){
								        	 
								        		 $scope.sucessMsg="Person Consent data submitted successfully.";
								        		 document.getElementById("upload").reset(); 
								        		 window.parent.scroll(0,0)
								          		
							        	 }else {
							        		 $scope.errorMsg="Unable to submit Person Consent data :: Error";
							        	 }
							         
							       }, 
							       function(response){
							    	   $scope.errorMsg="Unable to submit Person Consent data :: Error";
							         
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
			/*** Start of form data submit  if file is not present **/
		 	
			var data = {
			        "personconsentid": null,
			        "clientid": $scope.selClientID,
			        "concernroleid": null,
			        "fileid": $scope.fileid,
			        "startdate": $scope.startdate,
			        "enddate": $scope.enddate,
			        "thirdpartyrelationship": $scope.thirdpartyrelationship,
			        "thirdpartyfirstname": $scope.thirdpartyfirstname,
			        "thirdpartylastname": $scope.thirdpartylastname,
			        "thirdpartyotherrelationship": $scope.thirdpartyotherrelationship,
			        "identificationno": $scope.identificationno,
			        "identificationtype": $scope.identificationtype,
			        "identificationother": $scope.identificationother,
			        "contactaddressid": null,
			        "customerName": $scope.selCustomer,
			        "contactphone": $scope.contactphone,
			        "authpersonnameone": $scope.authpersonnameone,
			        "authpersonrelone": $scope.authpersonrelone,
			        "authpersoncontactinfoone": $scope.authpersoncontactinfoone,
			        "authpersonnametwo": $scope.authpersonnametwo,
			        "authpersonreltwo": $scope.authpersonreltwo,
			        "authpersoncontactinfotwo": $scope.authpersoncontactinfotwo,
			        "createdby": null,
			        "createdon": null,
			        "lastupdatedby": null,
			        "lastupdatedon": null,
			        "zipCode": $scope.zipCode,
			        "consentCatList": categoryStr,
			        "state": $scope.state,
			        "street": $scope.street,
			        "appSuite": $scope.appSuite,
			        "city": $scope.city
			    }; 
			data=JSON.stringify(data);

	        var config = {
	            headers : {
	                'Content-Type': 'application/json'
	            }
	        }

	    	console.log("submitFormData is json object ---- "+data);
	         
	    	$http.post(myVars.site_prefix +"/consent/saveConsPerson/", data, config)
			   .then(
			       function(response){
			    	   if(response.data.message){
				        	 if(response.data.message=='Fail'){
				        		 	$scope.errorMsg="Unable to submit Person Consent data :: Error";
				        	 }
				        	 if(response.data.message=='duplicate'){
				        		 	$scope.errorMsg="Customer already Present";
				        	 }

				         }else if(response.data!=''){
				        	 
				        		 $scope.sucessMsg="Person Consent data submitted successfully.";
				        		 document.getElementById("upload").reset(); 
				        		 $scope.selCheckBox = false;
				        		 $scope.selClientID = "";
				        		 window.parent.scroll(0,0)
				          		
			        	 }else if(response.data.clientid === $scope.selClientID){
				        	 
			        		 $scope.errorMsg="Person Consent data already submitted.";
			        		 
			        	 
			          		
		        	 }else {
			        		 $scope.errorMsg="Unable to submit Person Consent data :: Error";
			        	 }
			         
			       }, 
			       function(response){
			    	   $scope.errorMsg="Unable to submit Person Consent data :: Error";
			         
			       }
			    );
	    	/*** End  of form data submit  after file is uploaded **/
		}
		
		
	 
	} // end of submit formdata fucntion
	
	

}])

