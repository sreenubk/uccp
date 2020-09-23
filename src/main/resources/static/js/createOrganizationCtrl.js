app.controller('createOrganizationCtrl', ['$scope', '$rootScope','$uibModal', '$http', '$location','myVars',function($scope, $rootScope,$uibModal, $http,$location,myVars) {
		$rootScope.viewControlGroupView = 1;
		$rootScope.showNavbar = true;
		$scope.master = [];
		$scope.selectedOrganizationId = null;
		$scope.selectedOrganizationName = null;
		$scope.selectedOrganizationDesc = null;
		$scope.sucessMsg="";
		$scope.errorMsg="";
		$rootScope.addOrganization= null;
		$rootScope.existingOrganization =  false;
		$rootScope.existRuleData = null;
		$scope.orgData = null;
		$scope.searchOrganization = function(orgName){
			$rootScope.existingOrganization =  false;
			$rootScope.existRuleData = null;
			$scope.orgData = orgName;

			if ($scope.orgData != null && $scope.orgData != undefined ){
				
				var data = {
					       "name": $scope.orgData
				}

		    	data=JSON.stringify(data);

		        var config = {
		            headers : {
		                'Content-Type': 'application/json'
		            }
		        }
			
		    	console.log("search data is json object ---- "+data);
		    	
		    	$http.post(myVars.site_prefix +"/UserMgmt/getOrgListWithName/", data, config)
				   .then(
				       function(response){
				    	   if(response.data.message){
					        	 if(response.data.message=='NO Organization Found.'){
					        		 	$scope.sucessMsg="No Organization  with this name exists. Please try searching with other name.";
						          		
					        	 }else if(response.data.message=='Duplicate'){
					        		 $scope.errorMsg="Organization with this name already exists. Please try with another name."
						          		
					        	 }
					        	 
					         }else{
					        	 $scope.sucessMsg="";
					        	 $scope.organizationData = response.data;
					         }
				         
				       }, 
				       function(response){
				    	   $scope.errorMsg="Unable to search with given Organization name:: Error";
				         
				       }
				    );
		    	
			}else{
				$http.get(myVars.site_prefix +"/UserMgmt/getListOrg/")
					   .then(
							   function(response){
						    	   $rootScope.organizationData =  response.data;
						         
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
			$scope.organizationData = [];
			//$window.location.reload();
	        $scope.organizationDetail = angular.copy($scope.master);
	    };

	    /** start of view Organization **/
	    $scope.viewOrganizationDetails = function(orgUnitID){
	    	
			var data = {
			        "organisationunitid": orgUnitID

			}

	    	data=JSON.stringify(data);

	        var config = {
	            headers : {
	                'Content-Type': 'application/json'
	            }
	        }

	    	console.log("view Organization data of existing json object ---- "+data);
	    	
	    	$http.post(myVars.site_prefix +"/UserMgmt/getOrgListWithID/", data, config)
			   .then(
			       function(response){
			    	   if(response.data.message){
				        	 if(response.data.message=='NO Organization Found.'){
				        		 $scope.sucessMsg="No Organization with this name exists. Please try searching with other name.";
					          		
				        	 }
				       }else{
				    	     $scope.sucessMsg="";
				    	     $rootScope.existingOrganization =  true;
				        	 $rootScope.addOrganization =  response.data;
				        	 $location.path('/viewOrganization');	
				       }
			         
			       }, 
			       function(response){
			    	   $scope.errorMsg="Unable to view with given Organization name:: Error";
			       }
			    );// end of get Organization details
		 
		}; 
	    /** End of view Organization **/
	    
	    /** Start of Edit Organization Name **/
	    $scope.open = function(itemId,itemName,itemBusiness) {
	    	$rootScope.existingOrganization =  false;
	    	$rootScope.existRuleData = null;
	    	$scope.selectedOrganizationId = itemId;
	    	console.log("OrganizationID"+$scope.selectedOrganizationId);
	    	$scope.selectedOrganizationName = itemName;
	    	console.log("Organizationname"+$scope.selectedOrganizationName);
	    	$scope.selectedBusinessType = itemBusiness;
		    var modalinstance = $uibModal.open({
		      scope: $scope,
		      templateUrl: 'editOrganizationModal.html',
		      resolve: {
		    	  organizationData: function() {
		          return $scope.organizationData;
		        }
		      }
		    });
		  
	  	};


	  	$scope.editOrganizationSave = function(updatedBusiness){
	  		console.log("updatedDesc="+updatedBusiness);
	        var data = {
	            "organisationunitid": $scope.selectedOrganizationId,
		        "name": $scope.selectedOrganizationName,
		        "businesstypecode": updatedBusiness
	        };

	    	data=JSON.stringify(data);
	    	
	    	var config = {
	            headers : {
	                'Content-Type': 'application/json'
	            }
	        }

	    	console.log("selected data is json object ---- "+data);
	    	
	    	$http.post(myVars.site_prefix +"/UserMgmt/updateOrgUnit/", data, config)
			   .then(
			       function(response){
						    	 $scope.sucessMsg='Successfully changed the Organization Description to '+ updatedBusiness;
						         var editBusiness="td."+$scope.selectedOrganizationId+"business";
						         console.log(editBusiness);
						 	   	 $(editBusiness).text(updatedBusiness);
				      
			       }, 
			       function(response){
			    	   $scope.errorMsg='Unable to change the Organization:: Error ';
			       }
			    );
	    };
	    /** End of Edit Organization Name **/


	    /** Start of Delete Group  **/
	    $scope.deleteOrganization = function(itemId,itemName) {
	    	$scope.selectedOrganizationId = itemId;
	    	$scope.selectedOrganizationName = itemName;
	    	var modalinstance = $uibModal.open({
		      scope: $scope,
		      templateUrl: 'deleteOrganizationModal.html'
		  	});
		    
	  	};

	  	$scope.delOrganization = function(){
	  		var respDelData = {
	            "organisationunitid": $scope.selectedOrganizationId
	        };

	    	respDelData = JSON.stringify(respDelData);

	    	console.log("deleteOrganization JSON object --- "+respDelData);

	    	var config = {
	            headers : {
	                'Content-Type': 'application/json'
	            }
	        }

	    	$http.post(myVars.site_prefix +"/UserMgmt/deleteOrgUnit/", respDelData, config)
			   .then(
			       function(response){
			    	   if(response.data.message){
				        	 if(response.data.message=='Error - in deletion'){
				        		 $scope.errorMsg="Data is present for this Organization. Deletion not possible.";
				        	 }else{
					        	 $scope.sucessMsg="Deleted "+$scope.selectedOrganizationName+" Organization successfully";
						         var editOrganizationname="td."+$scope.selectedOrganizationId+"name";
						 	   	 $(editOrganizationname).closest('tr').remove();
					         }
				         }
			         
			         
			       }, 
			       function(response){
			    	   $scope.errorMsg="Unable to delete the Organization :: Error";
			       }
			    );
	  	};
	    /** End of Delete Organization **/


	    /** Start of View Organization  **/
	    $scope.viewOrganization = function() {
	    	$rootScope.existRuleData = null;
	    	var modalinstance = $uibModal.open({
		      scope: $scope,
		      templateUrl: 'createOrganizationModal.html'
		  	});
		  
	  	};

	  	$scope.viewOrgName = function(orgName,businesstypeCd){
	  		
	  		var viewOrgData = {
		 	         "businesstypecode": businesstypeCd,
		 	         "name": orgName
		  	        };

	  		
	  		viewOrgData = JSON.stringify(viewOrgData);

	    	console.log("viewGroup JSON object --- "+viewOrgData);

	    	var config = {
	            headers : {
	                'Content-Type': 'application/json'
	            }
	        }

	    	$http.post(myVars.site_prefix +"/UserMgmt/saveOrgUnit/", viewOrgData, config)
			   .then(
			       function(response){
			        
			         if(response.data.message){
			        	 if(response.data.message=='Duplicate'){
			        		 $scope.sucessMsg='Please choose another name. Organization with this name already exists in the system.';
				        }
			         }else{
			        	 $scope.sucessMsg='';
			        	 $rootScope.addOrganization =  response.data;
			        	 console.log($rootScope.addOrganization.name);
			        	 $location.path('/viewOrganization');
			        	 
			         }
			         
			         
			       }, 
			       function(response){
			          var msg = response.data.message;
			          $scope.errorMsg='Unable to create new Organization :: Error';
			       }
			    );
	  	};

	  	/** End of View Organization**/

	}]);