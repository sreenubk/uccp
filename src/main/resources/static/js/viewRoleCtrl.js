app.controller('viewRoleCtrl', ['$scope', '$rootScope','$uibModal', '$http',  '$location','$compile','myVars',function($scope, $rootScope,$uibModal, $http,$location,$compile,myVars) {
   $rootScope.showNavbar = true;
    var transRow = 1;
    $scope.sucessMsg="";
	$scope.errorMsg="";
	$scope.selectedRoleId = null;
	$scope.selectedRoleName = null;
	$scope.selectedRoleDesc = null;

    /** Start of Edit Role Name **/
    $scope.open = function(rolename,orgname) {
    	$rootScope.existingRole =  false;
    	//$rootScope.existRuleData = null;
    	$scope.selectedRoleName = rolename;
    	console.log("RoleName"+$scope.selectedRoleName);
    	$scope.selectedOrgName = orgname;
    	console.log("Orgname"+$scope.selectedOrgName);
	    var modalinstance = $uibModal.open({
	      scope: $scope,
	      templateUrl: 'editRoleModal.html',
	      resolve: {
	    	  roleDataEntry: function() {
	          return $scope.roleDataEntry;
	        }
	      }
	    });
  	};

  
  	$scope.editRoleSave = function(updatedOrgName,updatedJobName,updatedRoleLevel){
        var data = {
	        "rolename": $scope.selectedRoleName,
	        "orgname": updatedOrgName
        };

    	data=JSON.stringify(data);
    	
    	var config = {
            headers : {
                'Content-Type': 'application/json'
            }
        }

    	console.log("selected data is json object ---- "+data);
    	
    	$http.post(myVars.site_prefix +"/UserMgmt/updateRole/", data, config)
		   .then(
		       function(response){
		    	 $scope.sucessMsg='Successfully changed the Role Details of '+ $scope.selectedRoleName;
		         var editOrgName="td."+$scope.selectedRoleName+"orgname";
		 	   	 $(editOrgName).text(updatedOrgName);
		 	   	
		       }, 
		       function(response){
		    	   $scope.errorMsg='Unable to change the Role Details:: Error ';
		       }
		    );
    };
    /** End of Edit Role Name **/



    /** Start of Delete Group  **/
    $scope.deleteRole = function(itemName) {
    	$scope.selectedRoleName = itemName;
    	var modalinstance = $uibModal.open({
	      scope: $scope,
	      templateUrl: 'deleteRoleModal.html'
	  	});
  	};

  	$scope.delRole = function(){
  		var respDelData = {
	        "rolename": $scope.selectedRoleName
        };

    	respDelData = JSON.stringify(respDelData);

    	console.log("deleteRole JSON object --- "+respDelData);

    	var config = {
            headers : {
                'Content-Type': 'application/json'
            }
        }

    	$http.post(myVars.site_prefix +"/UserMgmt/deleteRole/", respDelData, config)
		   .then(
		       function(response){
		    	   if(response.data.message){
			        	 if(response.data.message=='Error - in deletion'){
			        		 $scope.errorMsg="Data is present for this Role. Deletion not possible.";
			        	 }else{
				        	 $scope.sucessMsg="Deleted Role "+$scope.selectedRoleName+" successfully";
					         var editRolename="td."+$scope.selectedRoleName+"rolename";
					 	   	 $(editRolename).closest('tr').remove();
				         }
			         }
		       }, 
		       function(response){
		    	   $scope.errorMsg="Unable to delete the Role :: Error";
		       }
		    );
  	};
    /** End of Delete Role **/

  

}]);