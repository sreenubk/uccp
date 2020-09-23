app.controller('entityResultsCtrl', ['$scope', '$rootScope','$uibModal', '$http',  '$location','myVars',function($scope, $rootScope,$uibModal, $http,$location,myVars) {
	$rootScope.showNavbar = true;
	//$rootScope.viewMdmSearch = true;
	//$rootScope.viewPersonHome = true;
//	$rootScope.viewApiManager = false;
	$scope.master = [];
	//$scope.myData = [];
//	$scope.selectedId = null;
//	$rootScope.selectedName = null;
	$scope.sucessMsg="";
	$scope.errorMsg="";
//	$rootScope.addGroup = null;
//	$rootScope.existingGroup =  false;
//	$rootScope.existAttrData = null;
	//$rootScope.existTranData = null;
	$scope.getCustName = null;
	$scope.getClientID = null;
	$scope.getAddress = null;
	$scope.getDOB = null;
	$scope.getGender = null;
	$scope.getRelationship = null;
	$scope.getMemberID = null;
	$scope.getFirstName = null;
	$scope.getLastName = null;
	/*$scope.selectPersonCard = function(customerName,clientID,address,dob,gender,relationship,memberID,fname,lname){
		$scope.getCustName = customerName;
		$scope.getClientID = clientID;
		$scope.getAddress = address;
		$scope.getDOB = dob;
		$scope.getGender = gender;
		$scope.getRelationship = relationship;
		$scope.getMemberID = memberID;
		$scope.getFirstName = fname;
		$scope.getLastName = lname;
		 var modalinstance = $uibModal.open({
		      scope: $scope,
		      templateUrl: 'selectPersonModal.html'
		    });
	}*/
	$scope.selectPersonCard = function(enterpriseId){
		var data = {
			    "enterpriseId": enterpriseId
			};

    	data=JSON.stringify(data);

        var config = {
            headers : {
                'Content-Type': 'application/json'
            }
        }

    	console.log("search data is json object ---- "+data);
    	
    	$http.post(myVars.site_prefix +"/PersonSearch/getIdEntityListByIdEntityIds", data, config)
		   .then(
		       function(response){
		    	   if(response.data.message){
			        	 if(response.data.message=='NO_RECORDS_FOUND'){
			        		 	$scope.sucessMsg="No entity record found for the given enterpriseId.";										          		
			        	 }
			        	 
			         }else{
			        	 $scope.sucessMsg="";
			        	 $rootScope.personDetailsData = response.data;
			        	 console.log("person data"+$rootScope.personDetailsData.client.clientNameList.clientName.firstName);
			        	 $location.path('/entityDetails');
			         }
		         
		       }, 
		       function(response){
		    	   $scope.errorMsg="Unable to search with given group name:: Error";
		         
		       }
		    );
	 
	}
	/* Start of Confirm Person and store data in database*/
		$scope.confirmPerson = function(){
			var data = {
					  "clientID": $scope.getClientID,
				       "customerName": $scope.getCustName,
				       "gender": $scope.getGender,
				       "relationship": $scope.getRelationship,
				       "address": $scope.getAddress,
				       "memberID": $scope.getMemberID,
				       "fname": $scope.getFirstName,
				       "lname": $scope.getLastName,
				       "dob": $scope.getDOB 
			}
			
			

			data = JSON.stringify(data);

	    	console.log("viewGroup JSON object --- "+data);

	    	var config = {
	            headers : {
	                'Content-Type': 'application/json'
	            }
	        }

	    	$http.post(myVars.site_prefix +"/PersonSearch/saveReferralPersonLA/", data, config)
			   .then(
			       function(response){
			        
			         if(response.data.message){
			        	 if(response.data.message=='Duplicate'){
			        		 $scope.sucessMsg='Please choose another name. Person with this name already exists in the system.';
			        	 }
			         }else{
			        	 $scope.sucessMsg='';
			        	 $rootScope.addPerson =  response.data;
			        	 console.log("Person"+$rootScope.addPerson.clientID);
			        	 $location.path('/personResult');
			        	 
			         }
			         
			         
			       }, 
			       function(response){
			          var msg = response.data.message;
			          $scope.errorMsg='Unable to save new Person Record :: Error';
			          //}
			       }
			    );
		}
	
	/* End of Confirm Person and store data in database*/
	$scope.reset = function() {
		$scope.sucessMsg="";
		$scope.errorMsg="";
		$scope.myData = [];
        $scope.ctrlGroup = angular.copy($scope.master);
    };

  


}]);