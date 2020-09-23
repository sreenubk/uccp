app.controller('viewCohortCtrl', ['$scope', '$rootScope','$uibModal', '$http',  '$location','$compile','myVars',function($scope, $rootScope,$uibModal, $http,$location,$compile,myVars) {
    $scope.msg = " I AM IN viewCohort";
    $rootScope.showNavbar = true;
    //$rootScope.viewControlGroupView = 3;
    var transRow = 1;
    //var existtransRow = 1;
    $scope.sucessMsg="";
	$scope.errorMsg="";
    //$scope.selectedAttrId = null;
    //$scope.attrRrowid = null;
	//$rootScope.selectedAttrName = null;
	//$rootScope.selectedAttrValue = null;
	$rootScope.existReasonCodeData = null;
	$rootScope.saveReasonCodeData = null;
	$rootScope.selectedaccessconstrainttype = null;
	$rootScope.selectedCohortRuleId = null;
	$rootScope.selectedCohortRuleID = null;
	$rootScope.selectedRuleShortDesc = null;
	$rootScope.selectedRuleDesc = null;
	$rootScope.selectedReasonCode = null;
	$rootScope.selectedRuleAction = null;
	$rootScope.selectedIsEnabled = null;
	$rootScope.selectedApprvRequired = null;
	$rootScope.selectedStartDate = null;
	$rootScope.selectedEndDate = null;
	$scope.cohRulRowid = null;
	if($rootScope.existingCohort==true){
		$("#transTable .tranBody tr").remove();
		
	
	for(i=0; i<$rootScope.existRuleData.listCohortRules.length; i++){
		$scope.existCohRuleDataLocal = $rootScope.existRuleData;
		console.log($scope.existCohRuleDataLocal);
		//$scope.existAttrDataLocal = $rootScope.existAttrData[i];
		//$scope.currentExistingCohortId = $rootScope.existRuleData.listCohortRules[i].cohortid;
		if($rootScope.existRuleData.listCohortRules[i].ruleID != null){
		transRow=transRow+1;
	    var cohortidlocal=$rootScope.existRuleData.listCohortRules[i].cohortRuleID;
	    var cohRulenamelocal=$rootScope.existRuleData.listCohortRules[i].ruleID;
		var cohRuleShortDesclocal=$rootScope.existRuleData.listCohortRules[i].ruleShortDesc;
		var cohRuleDesclocal=$rootScope.existRuleData.listCohortRules[i].ruleDescription;
		console.log("rule Desc"+cohRuleDesclocal);
		var cohRuleActionlocal=$rootScope.existRuleData.listCohortRules[i].ruleAction;
	    var cohReasonCodelocal=$rootScope.existRuleData.listCohortRules[i].reasonCode;
	    console.log("rule Desc"+cohReasonCodelocal);
		var cohIsEnabledlocal=$rootScope.existRuleData.listCohortRules[i].isEnabled;
		var cohApprovalRequiredlocal=$rootScope.existRuleData.listCohortRules[i].approvalRequired;
		var cohStartDatelocal=$rootScope.existRuleData.listCohortRules[i].startDate;
		var cohEndDatelocal=$rootScope.existRuleData.listCohortRules[i].endDate;
		var trid = "tr"+cohortidlocal;
		var $el = $('<tr id="tr'+cohRulenamelocal+'" class="'+cohRulenamelocal+'"><td class = "cohRulenamelocal">'+cohRulenamelocal+'</td> <td class = "cohRuleShortDesclocal">'+cohRuleShortDesclocal+'</td> <td class = "cohRuleDesclocal">'+cohRuleDesclocal+'</td><td class = "cohRuleActionlocal">'+cohRuleActionlocal+'</td><td class = "cohReasonCodelocal">'+cohReasonCodelocal+'</td><td class = "cohIsEnabledlocal">'+cohIsEnabledlocal+'</td><td class = "cohApprovalRequiredlocal">'+cohApprovalRequiredlocal+'</td><td class = "cohStartDatelocal">'+cohStartDatelocal+'</td><td class = "cohEndDatelocal">'+cohEndDatelocal+'</td> <td> <span style="float:right"><i style="cursor:pointer;" title="Edit" class="fa fa-pencil-square-o" aria-hidden="true" ng-click="openCohortRuleEdit('+cohortidlocal+',\''+cohRulenamelocal+'\',\''+cohRuleShortDesclocal+'\',\''+cohRuleDesclocal+'\',\''+cohRuleActionlocal+'\',\''+cohReasonCodelocal+'\',\''+cohIsEnabledlocal+'\',\''+cohApprovalRequiredlocal+'\',\''+cohStartDatelocal+'\',\''+cohEndDatelocal+'\')"></i> <i style="margin-right:10px;cursor:pointer;" title="Delete" class="fa fa-times" aria-hidden="true" ng-click="delCohRule('+cohortidlocal+')"></i> </span> </td> </tr>');
		$("table#transTable tbody.tranBody").append($el);
	    $compile($el)($scope);
		}  
	 
	}
	    
	};
	

    /*** Start of Add Cohort Rule **/
	 var config = {
	            headers : {
	                'Content-Type': 'application/json'
	            }
	        }
		
	
	$http.post(myVars.site_prefix +"/ConfigController/listReasonCode/", null, config)
	 .then(
	       function(response){
	    	   $rootScope.existReasonCodeData =  response.data;
	    	   console.log($rootScope.existReasonCodeData);
	    	   //$location.path('/viewReasonCode');
	       }, 
	       function(response){
	          var msg = response.data.message;
	          $scope.errorMsg='Unable to load Reason Code :: Error';
	          
	       }
	    );
    $scope.addCohortRule = function() {
    	$scope.sucessMsg="";
    	$scope.errorMsg="";
    	
    	var modalinstance = $uibModal.open({
	      scope: $scope,
	      templateUrl: 'addCohortRuleModal.html'
	  	});
	  
  	};
  	
  
// Post request for Reason Code data
  	$rootScope.viewReasonCodeData = function(){
  		$scope.sucessMsg="";
    	$scope.errorMsg="";
    	$location.path('/viewReasonCode');
  	}
  	
  	
 	 $scope.addCohortRuleSave = function(cohRuleName,ruleShortDesc,ruleDesc,ruleAction,reasonCode,isEnabled,apprvRequired,startdate,enddate){
 		
 		// document.getElementById('err-startDate').innerHTML = "";
		//document.getElementById('err-endDate').innerHTML = "";
 		var startDate = $("#startdate").val();
	    var endDate = $("#enddate").val();
	       if (startDate != '' && endDate !='') {
	           if (Date.parse(startDate) > Date.parse(endDate)) {
	        	   $("#enddate").val('');
	        	   document.getElementById('err-enddate').innerHTML = "End date should be greater than Start date";
	        	   return false;
	           }
	       }
 		 var data = {
	        
            "cohortid": $rootScope.addCohort.cohortid,
            "ruleID": cohRuleName,
            "ruleShortDesc": ruleShortDesc,
            "ruleDescription": ruleDesc,
            "ruleAction": ruleAction,
            "reasonCode": reasonCode,
            "isEnabled": isEnabled,
            "approvalRequired": apprvRequired,
            "startDate": startdate,
            "endDate": enddate,
            "cohortRuleID": null

        };

    	data=JSON.stringify(data);

        var config = {
            headers : {
                'Content-Type': 'application/json'
            }
        }

    	console.log("add Cohort Rule in json object ---- "+data);
    	
    	$http.post(myVars.site_prefix +"/ConfigController/saveCohortRule/", data, config)
		   .then(
		       function(response){
		    	 if(response.data.message=="Duplicate"){
		    		 $scope.errorMsg="Cohort Rule with this name already exists. Please try with another name."
		    	 }else{
			         $scope.myRuleData = response.data;
			         
			        transRow=transRow+1;
			        var cohortidlocal=$scope.myRuleData.cohortid;
			        var cohRulenamelocal=$scope.myRuleData.ruleID;
			        var cohRuleShortDesclocal=$scope.myRuleData.ruleShortDesc;
			        var cohRuleDesclocal=$scope.myRuleData.ruleDescription;
			        var cohRuleActionlocal=$scope.myRuleData.ruleAction;
			        var cohReasonCodelocal=$scope.myRuleData.reasonCode;
			        var cohIsEnabledlocal=$scope.myRuleData.isEnabled;
			        var cohApprovalRequiredlocal=$scope.myRuleData.approvalRequired;
			        var cohStartDatelocal=$scope.myRuleData.startDate;
			        var cohEndDatelocal=$scope.myRuleData.endDate;
			   		//console.log('<tr id="#'+cohortidlocal+'" class="'+cohortidlocal+'"><td>'+cohRulenamelocal+'</td> <td>'+cohRuleShortDesclocal+'</td> <td>'+cohRuleDesclocal+'</td><td>'+cohRuleActionlocal+'</td><td>'+cohReasonCodelocal+'</td><td>'+cohIsEnabledlocal+'</td><td>'+cohStartDatelocal+'</td><td>'+cohEndDatelocal+'</td><td> <span style="float:right"> <i style="margin-right:10px;cursor:pointer;" title="Delete" class="fa fa-times" aria-hidden="true" ng-click="delTran('+cohortidlocal+')"></i></span> </td> </tr> <tr>  </tr>');
				       
			        var $el = $('<tr id="#'+cohortidlocal+'" class="'+cohortidlocal+'"><td>'+cohRulenamelocal+'</td> <td>'+cohRuleShortDesclocal+'</td> <td>'+cohRuleDesclocal+'</td><td>'+cohRuleActionlocal+'</td><td>'+cohReasonCodelocal+'</td><td>'+cohIsEnabledlocal+'</td><td>'+cohApprovalRequiredlocal+'</td><td>'+cohStartDatelocal+'</td><td>'+cohEndDatelocal+'</td><td> <span style="float:right"><i style="cursor:pointer;" title="Edit" class="fa fa-pencil-square-o" aria-hidden="true" ng-click="openCohortRuleEdit('+cohortidlocal+',\''+cohRulenamelocal+'\',\''+cohRuleShortDesclocal+'\',\''+cohRuleDesclocal+'\',\''+cohRuleActionlocal+'\',\''+cohReasonCodelocal+'\',\''+cohIsEnabledlocal+'\',\''+cohApprovalRequiredlocal+'\',\''+cohStartDatelocal+'\',\''+cohEndDatelocal+'\')"></i> <i style="margin-right:10px;cursor:pointer;" title="Delete" class="fa fa-times" aria-hidden="true" ng-click="delCohRule('+cohortidlocal+')"></i></span> </td> </tr> <tr>  </tr>');
			        $("table#transTable tbody.tranBody").append($el);
			        $compile($el)($scope);
		    	 }
		       
		       }, 
		       function(response){
		         $scope.errorMsg='Unable to create new Cohort Rule:: Error';
		         //alert('Unable to create new transaction:: Error - '+ response.statusText);
		       }
		    );
	 
	}

	/*** End of Add Cohort Rule **/
 	 
 	/** Start transaction delete **/
 	$scope.delCohRule = function(selectedCohId){
 		$scope.sucessMsg="";
     	$scope.errorMsg="";
   		var respDelData = {
        	 "cohortid": null,
            "ruleID": null,
            "ruleShortDesc": null,
            "ruleDescription": null,
            "ruleAction": null,
            "reasonCode": null,
            "isEnabled": null,
            "approvalRequired": null,
            "startDate": null,
            "endDate": null,
            "cohortRuleID": selectedCohId
         };

     	respDelData = JSON.stringify(respDelData);

     	console.log("deleteCohort Rule JSON object --- "+respDelData);

     	var config = {
             headers : {
                 'Content-Type': 'application/json'
             }
         }

     	$http.post(myVars.site_prefix +"/ConfigController/deleteCohortRule/", respDelData, config)
 		   .then(
 		       function(response){
 		         $scope.msg = response.data;
 		         
 		         if(response.data.message){
 		        	 if(response.data.message=='Error-constraint Record Present'){
 		        		 $scope.errorMsg="Constraint data is present for this Cohort Rule. Deletion not possible.";
 			          		
 		        	 }else if(response.data.message=='deleted'){
 		        		
 		        		 //$('.'+selectedCohId).next('tr').remove();
 				 		 $('.'+selectedCohId).remove();
 				 		$scope.sucessMsg="Cohort Rule deleted successfully";
 				 		
 		        	 }
 		         }
 		       }, 
 		       function(response){
 		    	   $scope.errorMsg="Unable to delete the Cohort Rule:: Error";
 		        
 		       }
 		    );
   	};
     /** End of transaction delete  **/
    /** Start of Create Reason Code  **/
    $scope.createReasonCode = function() {
    	//$rootScope.existingGroup =  false;
    	//$rootScope.existAttrData = null;
    	//$rootScope.existRuleData = null;
    	var modalinstance = $uibModal.open({
	      scope: $scope,
	      templateUrl: 'createReasonCodeModal.html'
	  	});
	  
  	};

  	$scope.viewReasonCode = function(reasonCode,reasonDesc){
  		var viewReasonCodeData = {
	  			 "reasonCode": reasonCode,
	  	        "reasonDesc": reasonDesc
	  	        };


  		viewReasonCodeData = JSON.stringify(viewReasonCodeData);

    	console.log("viewReasonCode JSON object --- "+viewReasonCodeData);

    	var config = {
            headers : {
                'Content-Type': 'application/json'
            }
        }
    	
    	$http.post(myVars.site_prefix +"/ConfigController/saveReasonCode/", viewReasonCodeData, config)
		   .then(
		       function(response){
		        
		         if(response.data.message){
		        	 if(response.data.message=='Duplicate'){
		        		 $scope.sucessMsg='Please choose another name. Reason Code with this name already exists in the system.';
			          		//alert('Please choose another name. Control Group with this name already exists in the system.');
		        	 }
		         }else{
		        	 $scope.sucessMsg='';
		        	 $rootScope.saveReasonCodeData =  response.data;
		        	$rootScope.existReasonCodeData.push($rootScope.saveReasonCodeData);
		        	 //console.log($rootScope.saveReasonCodeData);
		        	 $location.path('/viewReasonCode');
		        	 
		         }
		         
		         
		       }, 
		       function(response){
		          var msg = response.data.message;
		          $scope.errorMsg='Unable to create new Reason Code :: Error';
		       }
		    );
  	};

  	/** End of Reason Code **/
  	//Start of Edit Cohort Rule
  	$scope.openCohortRuleEdit = function(cohRuleID,cohRuleName,ruleShortDesc,ruleDesc,ruleAction,reasonCode,isEnabled,apprvRequired,startdate,enddate){
  		$scope.cohRulRowid = cohRuleName;
  		$scope.selectedCohortRuleId = cohRuleID;
    	console.log("cohortID"+$scope.selectedCohortRuleId);
    	$rootScope.selectedCohortRuleID = cohRuleName;
    	console.log("cohortname"+$scope.selectedCohortRuleID);
    	$rootScope.selectedRuleShortDesc = ruleShortDesc;
    	$rootScope.selectedRuleDesc = ruleDesc;
    	$rootScope.selectedReasonCode = reasonCode;
    	$rootScope.selectedRuleAction = ruleAction;
    	$rootScope.selectedIsEnabled = isEnabled;
    	console.log($rootScope.selectedIsEnabled );
    	$rootScope.selectedApprvRequired = apprvRequired;
    	console.log($rootScope.selectedApprvRequired );
    	$rootScope.selectedStartDate = startdate;
    	$rootScope.selectedEndDate = enddate;
	    var modalinstance = $uibModal.open({
	      scope: $scope,
	      templateUrl: 'editCohortRuleModel.html',
	      resolve: {
	        myData: function() {
	          return $scope.myData;
	        }
	      }
	    });
  	}
    
  	$scope.editCohortRuleSave = function(updatedRuleShortDesc,updatedRuleDesc,updatedRuleAction,updatedReasonCode,updatedIsEnabled,updatedApprvRequired,updatedStartdate,updatedEnddate){
  		//console.log("updatedName="+updatedName);
    	// use $.param jQuery function to serialize data from JSON 
  		var trid = "tr"+$scope.cohRulRowid;
        var data = {
	        "cohortid": $rootScope.addCohort.cohortid,
	        "ruleID": $rootScope.selectedCohortRuleID,
	        "ruleShortDesc": updatedRuleShortDesc,
	        "ruleDescription": updatedRuleDesc,
	        "ruleAction": updatedRuleAction,
	        "reasonCode": updatedReasonCode,
	        "isEnabled": updatedIsEnabled,
	        "approvalRequired": updatedApprvRequired,
	        "startDate": updatedStartdate,
	        "endDate": updatedEnddate,
	        "cohortRuleID": $scope.selectedCohortRuleId
        };

    	data=JSON.stringify(data);
    	
    	var config = {
            headers : {
                'Content-Type': 'application/json'
            }
        }

    	console.log("selected data in json object ---- "+data);
    	
    	$http.post(myVars.site_prefix +"/ConfigController/updateCohortRule/", data, config)
		   .then(
		       function(response){
		    	   $scope.msg = response.data;
	 		         if(response.data.message){
	 		        	 if(response.data.message=='Error-constraint Record Present'){
	 		        		 $scope.errorMsg="Constraint data is present for this Cohort Rule. Deletion not possible.";
	 			          		
	 		        	 }else if(response.data.message=='Updated'){
	 		        		var editRuleShortDesc="tr#"+trid+" td.cohRuleShortDesclocal";
	 		        		//console.log(editRuleShortDesc);
	 				 	   	 $(editRuleShortDesc).text(updatedRuleShortDesc);
	 				 	   	 var editRuleDesc="tr#"+trid+" td.cohRuleDesclocal";
	 				 	   	 $(editRuleDesc).text(updatedRuleDesc);
	 				 	   	 var editRuleAction="tr#"+trid+" td.cohRuleActionlocal";
	 				 	   	 $(editRuleAction).text(updatedRuleAction);
	 				 	   	 var editReasonCode="tr#"+trid+" td.cohReasonCodelocal";
	 				 	   	 $(editReasonCode).text(updatedReasonCode);
	 				 	   	 var editIsEnabled="tr#"+trid+" td.cohIsEnabledlocal";
	 				 	   	 $(editIsEnabled).text(updatedIsEnabled);
	 				 	   	 var editApprReq="tr#"+trid+" td.cohApprovalRequiredlocal";
	 				 	   	 $(editApprReq).text(updatedApprvRequired);
	 				 	   	 var editStartDate="tr#"+trid+" td.cohStartDatelocal";
	 				 	   	 $(editStartDate).text(updatedStartdate);
	 				 	   	 var editEndDate="tr#"+trid+" td.cohEndDatelocal";
	 				 	   	 $(editEndDate).text(updatedEnddate);
	 		        		 //var editcohertRulename="td."+$scope.selectedCohortId + "ruleID";
					 	   	// $(editcohertRulename).text(updatedCohRuleName);
					 	   	$scope.sucessMsg='Successfully changed the cohort rule details';
	 				 		
	 		        	 }
	 		         }
		    	  /* if(response.data.message){
			    	   if(response.data.message=='Updated'){
				    	 $scope.sucessMsg='Successfully changed the cohort rule name';
				         var editcohertRulename="td."+$scope.selectedCohortId+"ruleID";
				 	   	 $(editcohertRulename).text(updatedCohRuleName);
			    	   }
		    	   }else{
		    		   $scope.sucessMsg='';
		    	   }*/
		       }, 
		       function(response){
		    	   $scope.errorMsg='Unable to change the cohort rule name:: Error ';
		         //alert('Unable to change the group name:: Error '+ response.statusText);
		       }
		    );
    };
    /** End of Edit Cohort Name **/

}]);