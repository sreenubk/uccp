app.controller('createJobCtrl', ['$scope', '$rootScope','$uibModal', '$http', '$location','myVars',function($scope, $rootScope,$uibModal, $http,$location,myVars) {
		$rootScope.viewControlGroupView = 1;
		$rootScope.showNavbar = true;
		$scope.master = [];
		$rootScope.selectedJobId = null;
		$scope.selectedJobName = null;
		$scope.selectedJobDesc = null;
		$scope.sucessMsg="";
		$scope.errorMsg="";
		$rootScope.addJob= null;
		$rootScope.existingJob =  false;
		$rootScope.existRuleData = null;
		$scope.jobData = null;
		$scope.searchJob = function(jobName){
			$rootScope.existingJob =  false;
			$rootScope.existRuleData = null;
			$scope.jobData = jobName;

			if ($scope.jobData != null && $scope.jobData != undefined ){
				
				var data = {
					       "jobname": $scope.jobData
				}

		    	data=JSON.stringify(data);

		        var config = {
		            headers : {
		                'Content-Type': 'application/json'
		            }
		        }
			
		    	console.log("search data is json object ---- "+data);
		    	
		    	$http.post(myVars.site_prefix +"/UserMgmt/getJobListWithName/", data, config)
				   .then(
				       function(response){
				    	   if(response.data.message){
					        	 if(response.data.message=='NO Job Found.'){
					        		 	$scope.sucessMsg="No Job  with this name exists. Please try searching with other name.";
						          		
					        	 }else if(response.data.message=='Duplicate'){
					        		 $scope.errorMsg="Job with this name already exists. Please try with another name."
						          		
					        	 }
					        	 
					         }else{
					        	 $scope.sucessMsg="";
					        	 $scope.jobDataResponse = response.data;
					         }
				         
				       }, 
				       function(response){
				    	   $scope.errorMsg="Unable to search with given Job name:: Error";
				         
				       }
				    );
		    	
			}else{
				$http.get(myVars.site_prefix +"/UserMgmt/getJobList/")
					   .then(
							   function(response){
								   $scope.jobDataResponse =  response.data;
						         
						       }, 
						       function(response){
						          var msg = response.data.message;
						          $scope.errorMsg='Unable to load job units :: Error';
						          
						       }
					    );
				
			}
		} //end of search

		$scope.reset = function() {
			$scope.sucessMsg="";
			$scope.errorMsg="";
			$scope.jobDataResponse = [];
			//$window.location.reload();
	        $scope.jobDetail = angular.copy($scope.master);
	    };

	    /** start of view Job **/
	   /* $scope.viewJobDetails = function(jobUnitID){
	    	
			var data = {
			        "organisationunitid": jobUnitID

			}

	    	data=JSON.stringify(data);

	        var config = {
	            headers : {
	                'Content-Type': 'application/json'
	            }
	        }

	    	console.log("view Job data of existing json object ---- "+data);
	    	
	    	$http.post(myVars.site_prefix +"/UserMgmt/getJobWithID/", data, config)
			   .then(
			       function(response){
			    	   if(response.data.message){
				        	 if(response.data.message=='NO Job Found.'){
				        		 $scope.sucessMsg="No Job with this name exists. Please try searching with other name.";
					          		
				        	 }
				       }else{
				    	     $scope.sucessMsg="";
				    	     $rootScope.existingJob =  true;
				        	 $rootScope.addJob =  response.data;
				        	 $location.path('/viewJob');	
				       }
			         
			       }, 
			       function(response){
			    	   $scope.errorMsg="Unable to view with given Job name:: Error";
			       }
			    );// end of get Job details
		 
		}; */
	    /** End of view Job **/
	    
	    /** Start of Edit Job Name **/
	    $scope.open = function(itemId,itemName,itemDesc) {
	    	$rootScope.existingJob =  false;
	    	//$rootScope.existRuleData = null;
	    	$scope.selectedJobId = itemId;
	    	console.log("JobID"+$scope.selectedJobId);
	    	$scope.selectedJobName = itemName;
	    	console.log("Jobname"+$scope.selectedJobName);
	    	$scope.selectedJobDesc = itemDesc;
		    var modalinstance = $uibModal.open({
		      scope: $scope,
		      templateUrl: 'editJobModal.html',
		      resolve: {
		    	  jobData: function() {
		          return $scope.jobData;
		        }
		      }
		    });
		  
	  	};

	  
	  	$scope.editJobSave = function(updatedJobName,updatedJobDesc){
	  		console.log("updatedDesc="+updatedJobDesc);
	        var data = {
	            "jobid": $scope.selectedJobId,
		        "jobName": $scope.selectedJobName,
		        "jobdesc": updatedJobDesc
	        };

	    	data=JSON.stringify(data);
	    	
	    	var config = {
	            headers : {
	                'Content-Type': 'application/json'
	            }
	        }

	    	console.log("selected data is json object ---- "+data);
	    	
	    	$http.post(myVars.site_prefix +"/UserMgmt/updateJob/", data, config)
			   .then(
			       function(response){
			    	 $scope.sucessMsg='Successfully changed the Job Details.';
			         var editJobDesc="td."+$scope.selectedJobId+"jobDesciption";
			         console.log(editJobDesc);
			 	   	 $(editJobDesc).text(updatedJobDesc);
			       }, 
			       function(response){
			    	   $scope.errorMsg='Unable to change the Job Details:: Error ';
			       }
			    );
	    };
	    /** End of Edit Job Name **/


	    /** Start of Delete Group  **/
	    $scope.deleteJob = function(itemId,itemName) {
	    	$scope.selectedJobId = itemId;
	    	$scope.selectedJobName = itemName;
	    	var modalinstance = $uibModal.open({
		      scope: $scope,
		      templateUrl: 'deleteJobModal.html'
		  	});
		    
	  	};

	  	$scope.delJob = function(){
	  		var respDelData = {
	            "jobid": $scope.selectedJobId
	        };

	    	respDelData = JSON.stringify(respDelData);

	    	console.log("deleteJob JSON object --- "+respDelData);

	    	var config = {
	            headers : {
	                'Content-Type': 'application/json'
	            }
	        }

	    	$http.post(myVars.site_prefix +"/UserMgmt/deleteJob/", respDelData, config)
			   .then(
			       function(response){
			    	   if(response.data.message){
				        	 if(response.data.message=='Error - in deletion'){
				        		 $scope.errorMsg="Data is present for this Job. Deletion not possible.";
				        	 }else if(response.data.message=='Success'){
					        	 $scope.sucessMsg="Deleted "+$scope.selectedJobName+" Job successfully";
						         var editJobname="td."+$scope.selectedJobId+"name";
						 	   	 $(editJobname).closest('tr').remove();
					         }
				         }
			         
			         
			       }, 
			       function(response){
			    	   $scope.errorMsg="Unable to delete the Job :: Error";
			       }
			    );
	  	};
	    /** End of Delete Job **/


	    /** Start of View Job  **/
	    $scope.viewJob = function() {
	    	$rootScope.existRuleData = null;
	    	var modalinstance = $uibModal.open({
		      scope: $scope,
		      templateUrl: 'createJobModal.html'
		  	});
		  
	  	};

	  	$scope.viewJobName = function(jobName,jobDesc){
	  		
	  		var viewJobData = {
	  				"jobid": null,
	  			    "jobName": jobName,
	  			    "jobdesc": jobDesc
		  	        };

	  		
	  		viewJobData = JSON.stringify(viewJobData);

	    	console.log("viewGroup JSON object --- "+viewJobData);

	    	var config = {
	            headers : {
	                'Content-Type': 'application/json'
	            }
	        }

	    	$http.post(myVars.site_prefix +"/UserMgmt/saveJob/", viewJobData, config)
			   .then(
			       function(response){
			        
			         if(response.data.message){
			        	 if(response.data.message=='Duplicate'){
			        		 $scope.sucessMsg='Please choose another name. Job with this name already exists in the system.';
				        }
			         }else{
			        	 $scope.sucessMsg='';
			        	 $rootScope.addJob =  response.data;
			        	 console.log($rootScope.addJob.jobName);
			        	 $location.path('/viewJob');
			        	 
			         }
			         
			         
			       }, 
			       function(response){
			          var msg = response.data.message;
			          $scope.errorMsg='Unable to create new Job :: Error';
			       }
			    );
	  	};

	  	/** End of View Job**/

	}]);