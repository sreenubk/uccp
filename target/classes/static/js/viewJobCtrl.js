app.controller('viewJobCtrl', ['$scope', '$rootScope','$uibModal', '$http',  '$location','$compile','myVars',function($scope, $rootScope,$uibModal, $http,$location,$compile,myVars) {
   $rootScope.showNavbar = true;
    var transRow = 1;
    $scope.sucessMsg="";
	$scope.errorMsg="";
 

	 
    /** Start of Edit Job Name **/
    $scope.open = function(itemId,itemName,itemDesc) {
    	$rootScope.existingJob =  false;
    	//$rootScope.existRuleData = null;
    	$rootScope.selectedJobId = itemId;
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
            "jobid": $rootScope.selectedJobId,
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
    	console.log($scope.selectedJobId);
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
			        	 }else{
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
    
  	
}]);