app.controller('viewRulesCtrl', ['$scope', '$rootScope','$uibModal', '$http',  '$location','$compile','myVars',function($scope, $rootScope,$uibModal, $http,$location,$compile,myVars) {
    $scope.msg = " I AM IN viewCohort";
    $rootScope.showNavbar = true;
    var transRow = 1;
    $scope.sucessMsg="";
	$scope.errorMsg="";
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
	$scope.selRuleID = "";
	$scope.selectedRoleID = "";
	$scope.selectRuleID = "";
	if($rootScope.existingCohort==true){
		$("#transTable .tranBody tr").remove();
		
	
	for(i=0; i<$rootScope.existRuleData.listCohortRules.length; i++){
		$scope.existCohRuleDataLocal = $rootScope.existRuleData;
		console.log($scope.existCohRuleDataLocal);
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
		var $el = $('<tr id="tr'+cohRulenamelocal+'" class="'+cohRulenamelocal+'"> <td class = "cohRulenamelocal">'+cohRulenamelocal+'</td> <td class = "cohRuleShortDesclocal">'+cohRuleShortDesclocal+'</td> <td class = "cohRuleDesclocal">'+cohRuleDesclocal+'</td><td class = "cohRuleActionlocal">'+cohRuleActionlocal+'</td><td class = "cohReasonCodelocal">'+cohReasonCodelocal+'</td><td class = "cohIsEnabledlocal">'+cohIsEnabledlocal+'</td><td class = "cohStartDatelocal">'+cohStartDatelocal+'</td><td class = "cohEndDatelocal">'+cohEndDatelocal+'</td><td id="td'+cohRulenamelocal+'"> <span style ="text-align:center"><input type="checkbox" id = "checkBoxModal" class="checkBoxModal" value="'+cohRulenamelocal+'" name = "checkBoxModal" ng-model="selRuleID'+cohRulenamelocal+'"  ng-change="change('+cohRulenamelocal+', selRuleID'+cohRulenamelocal+')" ng-true-value = "'+cohRulenamelocal+'"> </span> </td></tr>');
		$("table#transTable tbody.tranBody").append($el);
	    $compile($el)($scope);
		}  
	 
	}
	    
	};
	
	
	//Method for selecting checkbox
	$scope.lst = [];
	$scope.change = function(list, active){
        if (active){
            $scope.lst.push(list);
        console.log("list"+$scope.lst);
        }
        else{
            $scope.lst.splice($scope.lst.indexOf(list), 1);
        console.log("list1"+$scope.lst);
        }
	};  
	$scope.lst.toString();
	
	/* Method to execute Send Registration Request */
	 $scope.sendRegRequest = function(cohortID,ruleID){
    	$scope.sucessMsg="";
    	$scope.errorMsg="";
    	$scope.selectedCohortID = cohortID;
    	$scope.selectRuleID = ruleID;
    	console.log("cohid"+$scope.selectedCohortID);
    	console.log("ruleid"+$scope.selectRuleID);
    	if($scope.selectRuleID != null && $scope.selectRuleID != undefined && $scope.selectRuleID != ""){
    		$scope.sucessMsg="";
        	$scope.errorMsg="";
	    	var modalinstance = $uibModal.open({
		      scope: $scope,
		      templateUrl: 'openRegistrationRequestModal.html'
		  	});
    	}else{
    		$scope.errorMsg=" Please select atleast one checkbox to continue."
    	}
    }
    
    $scope.saveRoleName = function(cohortID,ruleID){
    	//$scope.selRuleName = ruleName;
    	$scope.selCohID = cohortID;
    	$scope.selRuleID = $scope.selectRuleID.toString();
    	console.log($scope.selectRuleID.toString() );
    	
    	var data = {
    	        
                "cohortid": $rootScope.addCohort.cohortid,
                "ruleID": $scope.selRuleID

            };

        	data=JSON.stringify(data);

            var config = {
                headers : {
                    'Content-Type': 'application/json'
                }
            }

        	console.log("add Rule in json object ---- "+data);
        	
        	$http.post(myVars.site_prefix +"/TransactionController/postRequest/", data, config)
    		   .then(
    		       function(response){
    		    	 if(response.data.message=="Duplicate"){
    		    		 $scope.errorMsg=" Role with this name already exists. Please try with another name."
    		    	 }else if(response.data.message=='sucess'){
    			         $scope.sucessMsg = "Data sent for Person Registration";
    			        
    		    	 }
    		       
    		       }, 
    		       function(response){
    		         $scope.errorMsg='Unable to create new  Role:: Error';
    		       }
    		    );
    }

}]);