app.controller('auditCtrl', ['$scope', '$rootScope','$uibModal', '$http',  '$location','myVars', function($scope, $rootScope,$uibModal, $http,$location,myVars) {
	$rootScope.viewControlGroupView = 10;
	$rootScope.showNavbar = true;
	$scope.master = [];
	
	var config = {
	            headers : {
	                'Content-Type': 'application/json'
	            }
	        }
	$http.post(myVars.site_prefix +"/authRes/AuthServiceReportAll/", null, config)
	 .then(
	       function(response){
	    	   $rootScope.auditData =  response.data;
	    	  // $scope.sucessMsg="File Uploaded successfully.";
	       }, 
	       function(response){
	          //var msg = response.data.message;
	          $scope.errorMsg='Unable to load Audit data :: Error';
	          
	       }
	    );
	
	$scope.reset = function() {
		$scope.sucessMsg="";
		$scope.errorMsg="";
		$scope.userName="";
		$scope.sourceSystem="";
	
        $scope.ctrlGroup = angular.copy($scope.master);
    };
	$scope.search = function(userName,sourceSystem,apiswitcch){
		var data = {
	      	    "userName": (userName== undefined)?null:userName,
	      	    "clientID": null,
	    	    "srcSystem": (sourceSystem==undefined)?null:sourceSystem,
	      	    "transactionid": null,
	      	    "transName": null,
	      	    "constraintid": null,
	      	    "constraintName": null,
	      	    "constraintValue": null,
	      	    "privilegeRequested": null,
	      	    "accessReason": null,
	      	    "apiSwith": (apiswitcch=="")?null:apiswitcch
	      	};

    	data=JSON.stringify(data);

        var config = {
            headers : {
                'Content-Type': 'application/json'
            }
        }

    	console.log("search data is json object ---- "+data);
    	
    	$http.post(myVars.site_prefix +"/authRes/criteriaSearchReport//", data, config)
		   .then(
		       function(response){
		    	   if(response.data.message){
			        	 if(response.data.message=='NO Group Found.'){
			        		 	$scope.sucessMsg="No User Name with this Source System exists. Please try searching again.";
				          		
			        	 }
			        	 
			         }else{
			        	 $scope.sucessMsg="";
			        	 $scope.auditData = response.data;
			         }
		         
		       }, 
		       function(response){
		    	   $scope.errorMsg="Unable to search with given group name:: Error";
		         
		       }
		    );
	 
	} //end of search
	
}])




