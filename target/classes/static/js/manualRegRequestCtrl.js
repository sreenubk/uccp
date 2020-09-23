app.controller('manualRegRequestCtrl', ['$scope', '$rootScope','$uibModal', '$http',  '$location','myVars',function($scope, $rootScope,$uibModal, $http,$location,myVars) {
	$rootScope.showNavbar = true;
	var transRow = 1;
	 $scope.date = new Date();
	 $scope.currentUsername = $rootScope.globals.currentUser.username;
	 $rootScope.ruleData = null;
	 
	 
	 /*Start of Select Client*/
	 $scope. selectClient = function(){
			$scope.sucessMsg="";
	    	$scope.errorMsg="";
	    	
	    	var modalinstance = $uibModal.open({
		      scope: $scope,
		      templateUrl: 'selectClientIdModal.html'
		  	});
		}
	 
	 /*End of Select Client*/
	  
	 
	 
	//Start of Selection of Cohort ID
	$scope.selectedCohortID = null;
	$scope.selectedClientID = null;
	$scope.selectedReasonCode = null;
	$scope.selectedRuleID = null;
	 $scope.cohRulenamelocal = [];
	 $scope.cohReasonCodelocal = [];
	 
	
	$scope.selectCohortID = function(){
		$scope.sucessMsg="";
    	$scope.errorMsg="";
    	
    	var modalinstance = $uibModal.open({
	      scope: $scope,
	      templateUrl: 'selectCohortIdModal.html'
	  	});
	}
	
	  $scope.rowHighlighted=function(row,id)
	    {
	      $scope.selectedRow = row;  
	      $scope.selectedCohortID = id;
	      console.log("selected Cohortid"+$scope.selectedCohortID);
	    }
	   
	  
	
	$scope.selectedCohort= function(cohortid){
		$scope.selectCohID = cohortid;
		console.log("selected Cohortid"+$scope.selectCohID);
	}
	 var config = {
	            headers : {
	                'Content-Type': 'application/json'
	            }
	        }

	    	//console.log("search data is json object ---- "+data);
	    	
	    	$http.post(myVars.site_prefix +"/ConfigController/listAllCohort/", null, config)
			   .then(
			       function(response){
			    	   if(response.data.message){
				        	 if(response.data.message=='NO Group Found.'){
				        		 	$scope.sucessMsg="No Cohert  with this name exists. Please try searching with other name.";
					          		
				        	 }
				        	 
				         }else{
				        	 $scope.sucessMsg="";
				        	 $scope.myData = response.data;
				         }
			         
			       }, 
			       function(response){
			    	   $scope.errorMsg="Unable to search with given cohert name:: Error";
			         
			       }
			    );
	 
	//End of Selection of Cohort ID
	 
	 
	 //Start of Rule Selection
	 
		$scope.selectRuleID = function(){
			$scope.sucessMsg="";
	    	$scope.errorMsg="";
	    	
	    	var modalinstance = $uibModal.open({
		      scope: $scope,
		      templateUrl: 'selectRuleIdModal.html'
		  	});
	    	
	    	 var selectedRuledata = {
        			 "cohortid":$scope.selectCohID,
        			 "cohortdescription": null,
        		        "name": null
 			        
	        };
		    	//console.log("search data is json object ---- "+data);
		 http://localhost:8090/ConfigController/searchWithCohortID/
		    	
		    	$http.post(myVars.site_prefix +"/ConfigController/searchWithCohortID/", selectedRuledata, config)
				   .then(
				       function(response){
				    	   if(response.data.message){
					        	 if(response.data.message=='NO Rule Data Found.'){
					        		 	$scope.sucessMsg="No Rule  with this name exists. Please try searching with other name.";
						          		
					        	 }
					        	 
					         }else{
					        	 $scope.sucessMsg="";
					        	 $rootScope.ruleData = response.data;
					        	 console.log("rule"+ $scope.ruleData);
					        		for(i=0; i<$rootScope.ruleData.listCohortRules.length; i++){
					        			$scope.existCohListDataLocal = $rootScope.ruleData;
					        			console.log($scope.existCohListDataLocal);
					        			//$scope.existAttrDataLocal = $rootScope.existAttrData[i];
					        			$scope.currentExistingCohortId = $rootScope.ruleData.listCohortRules[i].cohortid;
					        		
					        			//transRow=transRow+1;
					        		    var cohortidlocal=$rootScope.ruleData.listCohortRules[i].cohortRuleID;
					        		    $scope.cohRulenamelocal=$rootScope.ruleData.listCohortRules[i].ruleID;
					        		    console.log( $scope.cohRulenamelocal);
					        			//var cohRuleShortDesclocal=$rootScope.existRuleData.listCohortRules[i].ruleShortDesc;
					        			//var cohRuleDesclocal=$rootScope.existRuleData.listCohortRules[i].ruleDescription;
					        			//console.log("rule Desc"+cohRuleDesclocal);
					        			//var cohRuleActionlocal=$rootScope.existRuleData.listCohortRules[i].ruleAction;
					        		    $scope.cohReasonCodelocal=$rootScope.ruleData.listCohortRules[i].reasonCode;
					        		    console.log( $scope.cohReasonCodelocal);
					        			//var cohIsEnabledlocal=$rootScope.existRuleData.listCohortRules[i].isEnabled;
					        			//var cohApprovalRequiredlocal=$rootScope.existRuleData.listCohortRules[i].approvalRequired;
					        			//var cohStartDatelocal=$rootScope.existRuleData.listCohortRules[i].startDate;
					        			//var cohEndDatelocal=$rootScope.existRuleData.listCohortRules[i].endDate;
					        			//var trid = "tr"+cohortidlocal;
					        			//var $el = $('<tr id="#'+cohortidlocal+'" class="'+cohortidlocal+'"><td>'+cohRulenamelocal+'</td></tr>');
					        			//$("table#transTable tbody.tranBody").append($el);
					        		    //$compile($el)($scope);
					        		    
					        		 
					        		}
					         }
				         
				       }, 
				       function(response){
				    	   $scope.errorMsg="Unable to search with given cohert name:: Error";
				         
				       }
				    );
		}
		
		$scope.selectedRule = function(selRuleID, selReasoncode){
			$scope.selectRuleID = selRuleID;
			console.log("selected Ruleid"+$scope.selectRuleID);
				$scope.selectReasonCode = selReasoncode;
				console.log("selected Reasoncode"+$scope.selectReasonCode);
		}
		
		 $scope.rowHighlightedRule=function(row,id,reasonCode)
		    {
		      $scope.selectedRuleRow = row;  
		      $scope.selectedRuleID = id;
		      console.log("selected Ruleid"+$scope.selectedRuleID);
		      $scope.selectedReasonCode = reasonCode;
		    }
		 //End of Rule ID Selection
		//Start of Save form
		 
		 $scope.saveRegForm = function(selectedCohortID,selectedRuleID,selectedReasonCode,selectedClientID,currentDate,currentUsername,sourceSystem,sourceSystemID,firstName,lastName,status,comments){
			 $scope.sucessMsg="";
			 $scope.errorMsg="";
			 console.log("selected Ruleid"+selectedRuleID);
			 var data = {
					 	"reqReqtID": null,
				        "cohortID": selectedCohortID,
				        "ruleID": selectedRuleID,
				        "reasoncode":selectedReasonCode,
				        "clientID": selectedClientID,
				        "createdDate": currentDate,
				        "createdByUser": currentUsername,
				        "sourceSys": sourceSystem,
				        "sourceSysID": sourceSystemID,
				        "fName": firstName,
				        "lname": lastName,
				        "status": status,
				        "comments": comments,
				        "targetName": null
			 }
			 
			 data=JSON.stringify(data);

		        var config = {
		            headers : {
		                'Content-Type': 'application/json'
		            }
		        }

		    	console.log("submitFormData is json object ---- "+data);
		        $http.post(myVars.site_prefix +"/TransactionController/saveRequest/", data, config)
				   .then(
				       function(response){
				    	   if(response.data.message){
					        	 if(response.data.message=='Fail'){
					        		 	$scope.errorMsg="Unable to submit Registration form data :: Error";
					        	 }
					        	
					         }else if(response.data!=''){
					        	 
					        		 $scope.sucessMsg="Registration Form data submitted successfully.";
					        		 document.getElementById("regForm").reset(); 
					        		// $scope.selCheckBox = false;
					        		// $scope.selClientID = "";
					        		 window.parent.scroll(0,0)
				        
			        	 }else {
				        		 $scope.errorMsg="Unable to submit Registration Form data :: Error";
				        	 }
				         
				       }, 
				       function(response){
				    	   $scope.errorMsg="Unable to submit Registration Form data :: Error";
				         
				       }
				    );
		 
		 }
		 
		 //End of Save Form
		 
		 //Start of Select ClientID
		 
		 $scope.selectClientID = function(){
			 $scope.sucessMsg="";
		    	$scope.errorMsg="";
		    	
		    	var modalinstance = $uibModal.open({
			      scope: $scope,
			      templateUrl: 'selectClientIdModal.html'
			  	});
		 }
		 
		 var config = {
		            headers : {
		                'Content-Type': 'application/json'
		            }
		        }

		    	//console.log("search data is json object ---- "+data);
		    	
		    	$http.post(myVars.site_prefix +"/PersonSearch/listAllReferralPersonLA/", null, config)
				   .then(
				       function(response){
				    	   if(response.data.message){
					        	 if(response.data.message=='NO History.'){
					        		 	$scope.sucessMsg="No Person  with this name exists. Please try searching with other name.";
						          		
					        	 }
					        	 
					         }else{
					        	 $scope.sucessMsg="";
					        	 $scope.myPersonData = response.data;
					         }
				         
				       }, 
				       function(response){
				    	   $scope.errorMsg="Unable to search with given Person name:: Error";
				         
				       }
				    );
		 $scope.rowHighlightedClient=function(row,id)
		    {
		      $scope.selectedRow = row;  
		      $scope.selectedClientID = id;
		      console.log("selected Clientid"+$scope.selectedClientID);
		    }
		 //End of Select Client ID
		 
		 /* Start of  Reset Registration Form */
			 $scope.reset = function() {
				 $scope.sucessMsg="";
				$scope.errorMsg="";
				document.getElementById("regForm").reset(); 
			 };
		 
		 /* End of  Reset Registration Form */
			 
			 
}]);