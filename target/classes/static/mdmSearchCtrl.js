app.controller('mdmSearchCtrl', ['$scope', '$rootScope','$uibModal', '$http',  '$location',function($scope, $rootScope,$uibModal, $http,$location) {
	$rootScope.showNavbar = true;
	//$scope.master = [];
	$scope.sucessMsg="";
	$scope.errorMsg="";
	$rootScope.matchScore = "";
	$scope.personData = [];
	$scope.priviledgeRecord = "";
	$scope.searchMDM = function(firstName,middleName,lastName,gender,ssn,address,city,state,zipCode,dateofBirth,phone){
		var data = {
			    "firstName": firstName,
			    "middleName": middleName,
			    "lastName": lastName,
			    "ssn": ssn,
			    "address": address,
			    "city": city,
			    "state": state,
			    "zipCode": zipCode,
			    "dateofBirth": dateofBirth,
			    "phone": phone,
			    "gender": gender
			};

    	data=JSON.stringify(data);

        var config = {
            headers : {
                'Content-Type': 'application/json'
            }
        }

    	console.log("search data is json object ---- "+data);
    	
    	$http.post(myVars.site_prefix +"/PersonSearch/searchForIdList", data, config)
		   .then(
		       function(response){
		    	   if(response.data.message){
		    		   $scope.errorMsg = response.data.message;
		    		   $scope.sucessMsg="";
		    		   $scope.personData = [];
			         }else{
			        	 $scope.sucessMsg="";
			        	 $scope.errorMsg="";
			        	 $scope.personData = response.data;
			        	 for(var i = 0; i<$scope.personData.length;i++){
			        		 if ($scope.personData[i].idEntity.client.privilegedRecordList != null){
			        			 $scope.priviledgeRecord = $scope.personData[i].idEntity.client.privilegedRecordList.privilegedRecord[0].attrVal;
					        	 console.log($scope.priviledgeRecord);
			        			 
			        		 }else{
			        			 $scope.priviledgeRecord = "";
			        		 }
			        		
			        	 }
			        	 }
			        
		       }, 
		       function(response){
		    	   $scope.errorMsg="Unable to search with given inputs :: Error";
		         
		       }
		    );
	 
	} //end of search
	
	$scope.searchByClient = function(clientId,srcCode){
		var data = {
			    "memIdnum": clientId,
			    "srcCode": srcCode
			};

    	data=JSON.stringify(data);

        var config = {
            headers : {
                'Content-Type': 'application/json'
            }
        }

    	console.log("search data is json object ---- "+data);
    	
    	$http.post(myVars.site_prefix +"/PersonSearch/getIdEntityList", data, config)
		   .then(
		       function(response){
		    	   if(response.data.message){
		    		   $scope.errorMsg = response.data.message;
		    		   $scope.sucessMsg="";
		    		   $scope.personData = [];
			         }else{
			        	 $scope.sucessMsg="";
			        	 $scope.errorMsg="";
			        	 $scope.personData = response.data;
			        	 for(var i = 0; i<$scope.personData.length;i++){
			        		 if ($scope.personData[i].idEntity.client.privilegedRecordList != null){
			        			 $scope.priviledgeRecord = $scope.personData[i].idEntity.client.privilegedRecordList.privilegedRecord[0].attrVal;
					        	 console.log($scope.priviledgeRecord);
			        			 
			        		 }else{
			        			 $scope.priviledgeRecord = "";
			        		 }
			        		
			        	 }
			        	 
			        	 }
			        
		       }, 
		       function(response){
		    	   $scope.errorMsg="Unable to search with given inputs :: Error";
		         
		       }
		    );
	 
	} //end of search by Client
	$scope.reset = function() {
		$scope.sucessMsg="";
		$scope.errorMsg="";
		$scope.personData = [];
        $scope.mdmDetail = angular.copy($scope.master);
    };

  
    $scope.selectPersonCard = function(enterpriseId,score){
    	$rootScope.matchScore = score;
    	//console.log($rootScope.matchScore)
    	
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
        if($rootScope.rolePermission === "allow"){
        	if($scope.priviledgeRecord === "No -"){
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
				        	 $location.path('/entityDetails');
				         }
			         
			       }, 
			       function(response){
			    	   $scope.errorMsg="Unable to search with given group name:: Error";
			         
			       }
			    );
        	}else{
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
 				        	 $location.path('/entityDetails');
 				         }
 			         
 			       }, 
 			       function(response){
 			    	   $scope.errorMsg="Unable to search with given group name:: Error";
 			         
 			       }
 			    );
        	}
	
    	}else{
    			if($scope.priviledgeRecord === "No -"){
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
    					        	 $location.path('/entityDetails');
    					         }
    				         
    				       }, 
    				       function(response){
    				    	   $scope.errorMsg="Unable to search with given group name:: Error";
    				         
    				       }
    				    );
    	        	}else{
    	        		$scope.errorMsg="User has not been granted consent to view this client's record.";
    	        	}
    	}
        
       
	 
	}

}]);