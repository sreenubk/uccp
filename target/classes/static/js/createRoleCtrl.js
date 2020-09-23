app.controller('createRoleCtrl', ['$scope', '$rootScope','$uibModal', '$http', '$location','myVars',function($scope, $rootScope,$uibModal, $http,$location,myVars) {
		$rootScope.viewControlGroupView = 1;
		$rootScope.showNavbar = true;
		$scope.master = [];
		$scope.selectedRoleId = null;
		$scope.selectedRoleName = null;
		$scope.selectedRoleDesc = null;
		$scope.sucessMsg="";
		$scope.errorMsg="";
		$rootScope.addRole= null;
		$rootScope.existingRole =  false;
		$rootScope.existRuleData = null;
		$scope.roleData = null;
		$scope.searchRole = function(roleName){
			$rootScope.existingRole =  false;
			$rootScope.existRuleData = null;
			$scope.roleData = roleName;
			if ($scope.roleData != null && $scope.roleData != undefined ){
				
				var data = {
					       "rolename": $scope.roleData
				}
	
		    	data=JSON.stringify(data);
	
		        var config = {
		            headers : {
		                'Content-Type': 'application/json'
		            }
		        }
			
		    	console.log("search data is json object ---- "+data);
		    	
		    	$http.post(myVars.site_prefix +"/UserMgmt/getRoleListWithName/", data, config)
				   .then(
				       function(response){
				    	   if(response.data.message){
					        	 if(response.data.message==''){
					        		 	$scope.sucessMsg="No Role  with this name exists. Please try searching with other name.";
						          		
					        	 }else if(response.data.message=='Duplicate'){
					        		 $scope.errorMsg="Role with this name already exists. Please try with another name."
						          		
					        	 }
					        	 
					         }else{
					        	 $scope.sucessMsg="";
					        	 $scope.roleDataEntry = response.data;
					         }
				         
				       }, 
				       function(response){
				    	   $scope.errorMsg="Unable to search with given Role name:: Error";
				         
				       }
				    );
		    	
			}else{
				$scope.roleData = null;
				$http.get(myVars.site_prefix +"/UserMgmt/getRoleList/")
					   .then(
							   function(response){
								   $scope.sucessMsg="";
						    	   $scope.roleDataEntry =  response.data;
						         
						       }, 
						       function(response){
						          var msg = response.data.message;
						          $scope.errorMsg='Unable to load roles :: Error';
						          
						       }
					    );
				
			}
		} //end of search

		$scope.reset = function() {
			$scope.sucessMsg="";
			$scope.errorMsg="";
			$scope.roleDataEntry = [];
			//$window.location.reload();
	        $scope.roleDetail = angular.copy($scope.master);
	    };

	    $http.get(myVars.site_prefix +"/auth/getListOrg/")
		   .then(
		       function(response){
		    	   $rootScope.orgUnits =  response.data;
		         
		       }, 
		       function(response){
		          var msg = response.data.message;
		          $scope.errorMsg='Unable to load organisation units :: Error';
		          
		       }
		    );
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

	  
	  	$scope.editRoleSave = function(updatedOrgName){
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


	    /** Start of View Role  **/
	    $scope.viewRole = function() {
	    	$rootScope.existRuleData = null;
	    	var modalinstance = $uibModal.open({
		      scope: $scope,
		      templateUrl: 'createRoleModal.html'
		  	});
		  
	  	};

	  	$scope.viewRoleName = function(roleName,orgName){
	  		
	  		var viewRoleData = {
		  		        "rolename": roleName,
			  	        "orgname": orgName
		  	        };


	  		viewRoleData = JSON.stringify(viewRoleData);

	    	console.log("viewGroup JSON object --- "+viewRoleData);

	    	var config = {
	            headers : {
	                'Content-Type': 'application/json'
	            }
	        }
	    	$http.post(myVars.site_prefix +"/UserMgmt/saveRole/", viewRoleData, config)
			   .then(
			       function(response){
			        
			         if(response.data.message){
			        	 if(response.data.message=='Duplicate'){
			        		 $scope.sucessMsg='Please choose another name. Role with this name already exists in the system.';
			        	 }
			         }else{
			        	 $scope.sucessMsg='';
			        	 $rootScope.addRole =  response.data;
			        	 console.log($rootScope.addRole.rolename);
			        	 $location.path('/viewRole');
			         }
			       }, 
			       function(response){
			          var msg = response.data.message;
			          $scope.errorMsg='Unable to create new Role :: Error';
			       }
			    );
	  	};

	  	/** End of View Role**/

	}]);