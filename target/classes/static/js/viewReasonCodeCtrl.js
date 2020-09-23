app.controller('viewReasonCodeCtrl', ['$scope', '$rootScope','$uibModal', '$http',  '$location','$compile','myVars',function($scope, $rootScope,$uibModal, $http,$location,$compile,myVars) {
    $scope.msg = " I AM IN viewReasonCode";
    $rootScope.showNavbar = true;
    //$rootScope.viewControlGroupView = 3;
    var transRow = 1;
    //var existtransRow = 1;
    $scope.sucessMsg="";
	$scope.errorMsg="";
	$rootScope.selectedaccessconstrainttype = null;
	$scope.selectedReasonCode = null;
	if($rootScope.existingCohort==true){
		//$rootScope.existReasonCodeData.push($rootScope.saveReasonCodeData);
		$("#transTable .tranBody tr").remove();
		for(i=0; i < $rootScope.existReasonCodeData.length; i++){
		//transRow=transRow+1;
	    var reasonCodelocal=$rootScope.existReasonCodeData[i].reasonCode;
	    console.log(reasonCodelocal);
		var reasonCodeDesclocal=$rootScope.existReasonCodeData[i].reasonDesc;
		console.log("rule Desc"+reasonCodeDesclocal);
		
		var $el = $('<tr><td class = "'+reasonCodelocal+'reasonCode">'+reasonCodelocal+'</td> <td class = "'+reasonCodelocal+'reasonDesc">'+reasonCodeDesclocal+'</td><td> <span> <i style="cursor:pointer;" title="Edit" class="fa fa-pencil-square-o" aria-hidden="true" ng-click="openReasonCodeEdit(\''+reasonCodelocal+'\',\''+reasonCodeDesclocal+'\')"></i> <i style="margin-right:10px;cursor:pointer;" title="Delete" class="fa fa-times" aria-hidden="true" ng-click="deleteReasonCode(\''+reasonCodelocal+'\')"></i></span> </td> </tr>');
		$("table#transTable tbody.tranBody").append($el);
	    $compile($el)($scope);
	    
		}
	    
	};
	 /*var config = {
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
	    );*/
	 /** Start of Edit Reason Code **/
    $scope.openReasonCodeEdit = function(selectedReasonCode,selectedReasonCodeDesc) {
    	$rootScope.existingCohort =  false;
    	//$rootScope.existAttrData = null;
    	//$rootScope.existRuleData = null;
    	$scope.selectedReasonCode = selectedReasonCode;
    	$rootScope.selectedReasonDesc = selectedReasonCodeDesc;
	    var modalinstance = $uibModal.open({
	      scope: $scope,
	      templateUrl: 'editReasonCodeModal.html',
	      resolve: {
	        myData: function() {
	          return $scope.myData;
	        }
	      }
	    });
    
  	};
 	 
  	
  	
  	$scope.editReasonCodeSave = function(upReasonDesc){
  		//console.log("updatedName="+upReasonCode);
    	// use $.param jQuery function to serialize data from JSON 
        var data = {
	        "reasonCode": $scope.selectedReasonCode,
	        "reasonDesc": upReasonDesc
        };

    	data=JSON.stringify(data);
    	
    	var config = {
            headers : {
                'Content-Type': 'application/json'
            }
        }

    	console.log("selected data is json object ---- "+data);
    	
    	$http.post(myVars.site_prefix +"/ConfigController/updateReasonCode/", data, config)
		   .then(
		       function(response){
		    	 $scope.sucessMsg='Successfully changed the Reason Code Description to '+ upReasonDesc;
		         //alert('Successfully changed the group name to '+ updatedName);
		         var editReasonCodeDesc="td."+$scope.selectedReasonCode+"reasonDesc";
		         console.log(editReasonCodeDesc);
		 	   	 $(editReasonCodeDesc).text(upReasonDesc);
		       }, 
		       function(response){
		    	   $scope.errorMsg='Unable to change the Reason Code Description:: Error ';
		         //alert('Unable to change the group name:: Error '+ response.statusText);
		       }
		    );
    };
    /** End of Edit Reason Code Details **/
 	/** Start Reason Code delete **/
    /** Start of Delete Group  **/
    $scope.deleteReasonCode = function(itemId) {
    	$scope.selectedReasonCode = itemId;
    	//$scope.selectedCohortName = itemName;
    	var modalinstance = $uibModal.open({
	      scope: $scope,
	      templateUrl: 'deleteReasonCodeModal.html'
	  	});
	    
  	};
  	
 	$scope.delReasonCode = function(){
 		//console.log("deletereasoncodeName="+selectedReasonCode);
 		$scope.sucessMsg="";
     	$scope.errorMsg="";
   		var respDelData = {
            "reasonCode": $scope.selectedReasonCode,
            "reasonDesc": null
         };

     	respDelData = JSON.stringify(respDelData);

     	console.log("delete Reason Code JSON object --- "+respDelData);

     	var config = {
             headers : {
                 'Content-Type': 'application/json'
             }
         }

     	$http.post(myVars.site_prefix +"/ConfigController/deleteReasonCode/", respDelData, config)
 		   .then(
 		       function(response){
 		         $scope.msg = response.data;
 		         
 		         if(response.data.message){
 		        	/* if(response.data.message=='Error-constraint Record Present'){
 		        		 $scope.errorMsg="Constraint data is present for this Reason Code. Deletion not possible.";
 			          		
 		        	 }else*/
 		        		 if(response.data.message=='Error - in deletion'){
 		        		 $scope.errorMsg="This reason Code is used in Cohort Rule. Deletion not possible";
 		        	 }else if(response.data.message=='deleted'){
 		        		 var removeReasonCode="td."+$scope.selectedReasonCode+"reasonCode";
				 	   	 $(removeReasonCode).closest('tr').remove();
 		        		// $('.'+selectedReasonCode).next('tr').remove();
 				 		// $('.'+$scope.selectedReasonCode).remove();
 				 		$scope.sucessMsg="Reason Code "+$scope.selectedReasonCode+" deleted successfully";
 				 		
 		        	 }
 		         }
 		       }, 
 		       function(response){
 		    	   $scope.errorMsg="Unable to delete the Reason Code:: Error";
 		        
 		       }
 		    );
   	};
     /** End of Reason Code delete  **/
   	

}]);