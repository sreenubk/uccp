app.controller('createUserCtrl', ['$scope', '$rootScope','$uibModal', '$http', '$location','myVars',function($scope, $rootScope,$uibModal, $http,$location,myVars) {
		$rootScope.viewControlGroupView = 1;
		$rootScope.showNavbar = true;
		$scope.master = [];
		$rootScope.selectedUserId = null;
		$rootScope.selectedUserName = null;
		$rootScope.selectedUserDesc = null;
		$scope.sucessMsg="";
		$scope.errorMsg="";
		$rootScope.addUser= null;
		$scope.userData = null;
		$rootScope.existingUser =  false;
		//HTTP call to retrieve all organization units
		$http.get(myVars.site_prefix +"/auth/getListOrg/")
		   .then(
		       function(response){
		    	   $rootScope.orgUnits =  response.data;
		         
		       }, 
		       function(response){
		          var msg = response.data.message;
		          $scope.errorMsg='Unable to load orgganisation units :: Error';
		          
		       }
		    );
		$scope.searchUser = function(userName){
			$rootScope.existingUser =  false;
			$scope.userData = userName;
			if ($scope.userData != null && $scope.userData != undefined ){
				
				var data = {
					       "username": $scope.userData
				}
	
		    	data=JSON.stringify(data);
	
		        var config = {
		            headers : {
		                'Content-Type': 'application/json'
		            }
		        }
			
		    	console.log("search data is json object ---- "+data);
		    	
		    	$http.post(myVars.site_prefix +"/UserMgmt/getUserWithName/", data, config)
				   .then(
				       function(response){
				    	   if(response.data.message){
					        	 if(response.data.message=='User with given name not found'){
					        		 	$scope.sucessMsg="No User  with this name exists. Please try searching with other name.";
						          		
					        	 }else if(response.data.message=='Duplicate'){
					        		 $scope.errorMsg="User with this name already exists. Please try with another name."
						          		
					        	 }
					        	 
					         }else{
					        	 $scope.sucessMsg="";
					        	 $scope.userDataEntry = response.data;
					         }
				         
				       }, 
				       function(response){
				    	   $scope.errorMsg="Unable to search with given User name:: Error";
				         
				       }
				    );
		    	
			}else{
				$http.get(myVars.site_prefix +"/UserMgmt/getUserList/")
					   .then(
							   function(response){
						    	   $rootScope.userDataEntry =  response.data;
						         
						       }, 
						       function(response){
						          var msg = response.data.message;
						          $scope.errorMsg='Unable to load organisation units :: Error';
						          
						       }
					    );
				
			}
		} //end of search

		$scope.reset = function() {
			$scope.sucessMsg="";
			$scope.errorMsg="";
			$scope.userDataEntry = [];
	        $scope.userDetail = angular.copy($scope.master);
	    };

	    
	    /** Start of Edit User Name **/
	    $scope.open = function(username,rolename,jobname,firstname,lastname,orgName) {
	    	$rootScope.existingUser =  false;
	    	//$rootScope.existRuleData = null;
	    	$rootScope.selectedUserName = username;
	    	$rootScope.selectedRoleName = rolename;
	    	$rootScope.selectedJobName = jobname;
	    	$rootScope.selectedFirstName = firstname;
	    	$rootScope.selectedLastName = lastname;
	    	$rootScope.selectedOrgName = orgName;
		    var modalinstance = $uibModal.open({
		      scope: $scope,
		      templateUrl: 'editUserModal.html',
		      resolve: {
		    	  userDataEntry: function() {
		          return $scope.userDataEntry;
		        }
		      }
		    });
		  
	  	};

	  
	  	$scope.editUserSave = function(selectedUserName,updatedRoleName,updatedJobName,updatedFirstName,updatedLastName,updatedOrgName){
	  		  var data = {
		        "username": $rootScope.selectedUserName ,
		        "password": null,
		        "rolename": updatedRoleName,
		        "firstname": updatedFirstName,
		        "lastname": updatedLastName,
		        "email": null,
		        "result": null,
		        "userRole": updatedJobName,
		        "orgName": updatedOrgName
	        };

	    	data=JSON.stringify(data);
	    	
	    	var config = {
	            headers : {
	                'Content-Type': 'application/json'
	            }
	        }

	    	console.log("selected data is json object ---- "+data);
	    	
	    	$http.post(myVars.site_prefix +"/UserMgmt/updateUser/", data, config)
			   .then(
			       function(response){
			    	 $scope.sucessMsg='Successfully changed the User Description';
		    		var editRoleName="td."+$scope.selectedUserName+"rolename";
			        var editJobName="td."+$scope.selectedUserName+"userRole";
			        var editFirstName="td."+$scope.selectedUserName+"firstname";
			 	   	var editLastName="td."+$scope.selectedUserName+"lastname";
			 	   var editOrgName="td."+$scope.selectedUserName+"orgname";
			 	   	 $(editOrgName).text(updatedOrgName);
			 	   	$(editJobName).text(updatedJobName);
			 	   $(editRoleName).text(updatedRoleName);
			 	   $(editFirstName).text(updatedFirstName);
			 	   $(editLastName).text(updatedLastName);
			 	   	
			       }, 
			       function(response){
			    	   $scope.errorMsg='Unable to change the User Description:: Error ';
			       }
			    );
	    };
	    /** End of Edit User Name **/


	    /** Start of Delete User  **/
	    $scope.deleteUser = function(itemName) {
	    	$scope.selectedUserName = itemName;
	    	var modalinstance = $uibModal.open({
		      scope: $scope,
		      templateUrl: 'deleteUserModal.html'
		  	});
		    
	  	};

	  	$scope.delUser = function(){
	  		var respDelData = {
		        "username": $scope.selectedUserName
	        };

	    	respDelData = JSON.stringify(respDelData);

	    	console.log("deleteUser JSON object --- "+respDelData);

	    	var config = {
	            headers : {
	                'Content-Type': 'application/json'
	            }
	        }

	    	$http.post(myVars.site_prefix +"/UserMgmt/deleteUser/", respDelData, config)
			   .then(
			       function(response){
			    	   if(response.data.message){
				        	 if(response.data.message=='Error - in deletion'){
				        		 $scope.errorMsg="Data is present for this User. Deletion not possible.";
				        	 }else{
					        	 $scope.sucessMsg="Deleted User "+$scope.selectedUserName+" successfully";
						         var editUsername="td."+$scope.selectedUserName+"username";
						 	   	 $(editUsername).closest('tr').remove();
					         }
				         }
			         
			         
			       }, 
			       function(response){
			    	   $scope.errorMsg="Unable to delete the User :: Error";
			       }
			    );
	  	};
	    /** End of Delete User **/


	    /** Start of View User  **/
	    $scope.viewUser = function() {
	    	var modalinstance = $uibModal.open({
		      scope: $scope,
		      templateUrl: 'createUserModal.html'
		  	});
		  
	  	};

	  	$scope.viewUserName = function(userName,roleName,jobName,fName,lName,orgName,roleLevel,emailID){
	  		//$scope.newUserName = userName;
	  		var viewUserData = {
	  			   "username": userName,
	  		        "password": null,
	  		        "rolename": roleName,
	  		        "firstname": fName,
	  		        "lastname": lName,
	  		        "email": null,
	  		        "result": null,
	  		        "userRole": jobName,
	  		        "orgName": orgName
		  	        };


	  		viewUserData = JSON.stringify(viewUserData);

	    	console.log("viewGroup JSON object --- "+viewUserData);

	    	var config = {
	            headers : {
	                'Content-Type': 'application/json'
	            }
	        }

	    	$http.post(myVars.site_prefix +"/UserMgmt/saveUser/", viewUserData, config)
			   .then(
			       function(response){
			        
			         if(response.data.message){
			        	 if(response.data.message=='Duplicate'){
			        		 $scope.sucessMsg='Please choose another name. User with this name already exists in the system.';
			        	 }
			         }else{
			        	 $scope.sucessMsg='';
			        	 $rootScope.addUser =  response.data;
			        	  /*  var newUserName="td."+$scope.addUser.username+"username";
					        var newRoleName="td."+$scope.addUser.username+"rolename";
					        var newJobName="td."+$scope.addUser.username+"userRole";
					        var newFirstName="td."+$scope.addUser.username+"firstname";
					 	   	var newLastName="td."+$scope.addUser.username+"lastname";
					 	    var newOrgName="td."+$scope.addUser.username+"orgname";
					 	  
					 	   $(newUserName).text($scope.addUser.username);
					 	   $(newRoleName).text($scope.addUser.rolename);
					 	   $(newJobName).text($scope.addUser.userRole);
					 	   $(newFirstName).text($scope.addUser.firstname);
					 	   $(newLastName).text($scope.addUser.lastname);
					 	   $(newOrgName).text($scope.addUser.orgName);*/
					 	 
			        	 $location.path('/viewUser');
			        	 
			         }
			         
			         
			       }, 
			       function(response){
			          var msg = response.data.message;
			          $scope.errorMsg='Unable to create new User :: Error';
			       }
			    );
	  	};

	  	/** End of View User**/

	}]);
