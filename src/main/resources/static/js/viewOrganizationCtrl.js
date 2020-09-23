app.controller('viewOrganizationCtrl', ['$scope', '$rootScope','$uibModal', '$http',  '$location','$compile','myVars',function($scope, $rootScope,$uibModal, $http,$location,$compile,myVars) {
   $rootScope.showNavbar = true;
    var transRow = 1;
    $scope.sucessMsg="";
	$scope.errorMsg="";
	$scope.selectedOrganizationId = null;
	$scope.selectedOrganizationName = null;
	$scope.selectedOrganizationDesc = null;
	 
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


    /** Start of Delete Organization  **/
   
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

    
  	
}]);