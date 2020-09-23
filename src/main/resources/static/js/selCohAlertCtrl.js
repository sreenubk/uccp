app.controller('selCohAlertCtrl', ['$scope', '$rootScope','$uibModal', '$http',  '$location','myVars',function($scope, $rootScope,$uibModal, $http,$location,myVars) {
	$rootScope.viewControlGroupView = 1;
	$rootScope.showNavbar = true;
	$scope.master = [];
	$scope.selectedCohortId = null;
	$rootScope.selectedCohortName = null;
	$rootScope.selectedCohortDesc = null;
	$scope.sucessMsg="";
	$scope.errorMsg="";
	$rootScope.addCohort= null;
	$rootScope.existingCohort =  false;
	$rootScope.existRegData = null;
    $scope.cohortNameData = null;
	$scope.searchCohortName = function(cohortName){
		$rootScope.existingCohort =  false;
		$rootScope.existRuleData = null;
		$scope.cohortNameData  = cohortName;
		console.log($scope.cohortNameData);
		if ($scope.cohortNameData != null && $scope.cohortNameData != undefined ){
			var data = {
					"cohortid":"",
					"cohortdescription":"",
					"name":$scope.cohortNameData
			};

	    	data=JSON.stringify(data);

	        var config = {
	            headers : {
	                'Content-Type': 'application/json'
	            }
	        }
		
	    	console.log("search data is json object ---- "+data);
	    	
	    	$http.post(myVars.site_prefix +"/ConfigController/listAllCohortWithName/", data, config)
			   .then(
			       function(response){
			    	   if(response.data.message){
				        	 if(response.data.message=='NO Cohort Found.'){
				        		 	$scope.sucessMsg="No Cohert  with this name exists. Please try searching with other name.";
					          		
				        	 }else if(response.data.message=='Duplicate'){
				        		 $scope.errorMsg="Cohort with this name already exists. Please try with another name."
					          		
				        	 }
				        	 
				         }else{
				        	 $scope.sucessMsg="";
				        	 $scope.myData = response.data;
				         }
			         
			       }, 
			       function(response){
			    	   $scope.errorMsg="Unable to search with given cohert name:: Error";
			         
			       }
			    );
	    	
		}else{

			//when cohortName is null
			var data = {
					"cohortid":"",
					"cohortdescription":"",
					"name":$scope.cohortNameData
			};

	    	data=JSON.stringify(data);

	        var config = {
	            headers : {
	                'Content-Type': 'application/json'
	            }
	        }
		
	    	console.log("search data is json object ---- "+data);
	    	
	    	$http.post(myVars.site_prefix +"/ConfigController/listAllCohort/", data, config)
			   .then(
			       function(response){
			    	   if(response.data.message){
				        	 if(response.data.message=='NO Cohort Found.'){
				        		 	$scope.sucessMsg="No Cohert  with this name exists. Please try searching with other name.";
					          		
				        	 }else if(response.data.message=='Duplicate'){
				        		 $scope.errorMsg="Cohort with this name already exists. Please try with another name."
					          		
				        	 }
				        	 
				         }else{
				        	 $scope.sucessMsg="";
				        	 $scope.myData = response.data;
				         }
			         
			       }, 
			       function(response){
			    	   $scope.errorMsg="Unable to search with given cohert name:: Error";
			         
			       }
			    );
			
		}
		
    	
	} //end of search
	
	//Row Highlight function
	 $scope.rowHighlighted=function(row,id)
	    {
	      $scope.selectedRow = row;  
	      $scope.selectedCohortID = id;
	      console.log("selected Cohortid"+$scope.selectedCohortID);
	    }

	$scope.reset = function() {
		$scope.sucessMsg="";
		$scope.errorMsg="";
		$scope.myData = [];
        $scope.cohortDetail = angular.copy($scope.master);
    };

    /** start of show Registration Request **/
    $scope.showRegRequest = function(cohertId){
    	
		var data = {
				"cohortid":cohertId,
		        "cohortdescription": null,
		        "name": null
		};
		

    	data=JSON.stringify(data);

        var config = {
            headers : {
                'Content-Type': 'application/json'
            }
        }

    	console.log("view registration request of existing json object ---- "+data);
    	
    	$http.post(myVars.site_prefix +"/ConfigController/getCohortwithID/", data, config)
		   .then(
		       function(response){
		    	   if(response.data.message){
			        	 if(response.data.message=='NO Cohert Found.'){
			        		 $scope.sucessMsg="No Cohert with this name exists. Please try searching with other name.";
				          		
			        	 }
			       }else{
			    	     $scope.sucessMsg="";
			    	     $rootScope.existingCohort =  true;
			        	 $rootScope.addCohort =  response.data;
			        	// console.log("cohort"+$rootScope.addCohort.cohortvo.name);
			        	 //$location.path('/view-cohort');
			        	 //$location.path('/viewControlGroup');
				        	//******** get cohort rule details of control group
				        	 var regReqdata = {
				        			 "cohortid":cohertId,
				 			        "cohortdescription": null,
				 			        "name": null
		        	        };

				        	 regReqdata=JSON.stringify(regReqdata);

		        	        var config = {
		        	            headers : {
		        	                'Content-Type': 'application/json'
		        	            }
		        	        }

		        	    	console.log("get cohort rule details of existing control group json object ---- "+regReqdata);
				        	$http.post(myVars.site_prefix +"/TransactionController/listAllAlertRequestWCohort/", regReqdata, config) 
						  		   .then(
						  		       function(response){
						  		    	 $scope.sucessMsg="";
						  		    	 $rootScope.existRegData = response.data;
						  		    	 $location.path('/manageAlertRequest');
						  		    	
						  		       }, 
						  		       function(response){
						  		    	 $scope.errorMsg='Unable to get the cohort rule for this group :: Error ';
						  		         
						  		       }
						  		    );// end of get cohort rule details of cohort 
				        	
			       }
		         
		       }, 
		       function(response){
		    	   $scope.errorMsg="Unable to view with given group name:: Error";
		        
		         //alert('Unable to view with given group name:: Error '+ response.statusText);
		       }
		    );// end of get control group details
	 
	}; 
    /** End of view Cohort **/
   
	 $scope.selAllCohort = function(){

	        var config = {
	            headers : {
	                'Content-Type': 'application/json'
	            }
	        }
	    	
	    	$http.post(myVars.site_prefix +"/TransactionController/listAllAltRequest/", null, config)
			   .then(
			       function(response){
			    	   if(response.data.message){
				        	 if(response.data.message=='NO Request Found.'){
				        		 $scope.sucessMsg="No Request with any Cohort exists.";
					          		
				        	 }
				       }else{
				    	     $scope.sucessMsg="";
				    	     $rootScope.existingCohort =  true;
				        	 $rootScope.existRegData =  response.data;
				        	 $location.path('/manageAlertRequest');
					        	
					        	
				       }
			         
			       }, 
			       function(response){
			    	   $scope.errorMsg="Unable to view with given group name:: Error";
			       }
			    );// end of get select all cohort request
		 
		}; 

		
			       
}]);