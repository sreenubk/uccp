app.controller('manageRegRequestCtrl', ['$scope', '$rootScope','$uibModal', '$http',  '$location',function($scope, $rootScope,$uibModal, $http,$location) {
	$rootScope.showNavbar = true;
	$scope.date = new Date();
	$scope.selectedReqID = null;
	$scope.storeReqID = null;
	$rootScope.existODSData = "";
	$scope.selectedStatus = null;
	$scope.currentUsername = $rootScope.globals.currentUser.username;
	/*$scope.getcohortDetails = function(cohortId){
		$scope.sucessMsg="";
    	$scope.errorMsg="";
    	
    	var modalinstance = $uibModal.open({
	      scope: $scope,
	      templateUrl: 'showCohertDetailsModal.html'
	  	});
    	

		var data = {
				"cohortid":cohortId,
		        "cohortdescription": null,
		        "name": null
		};
		

    	data=JSON.stringify(data);

        var config = {
            headers : {
                'Content-Type': 'application/json'
            }
        }

    	console.log("view group data of existing json object ---- "+data);
    	
    	$http.post(myVars.site_prefix +"/ConfigController/getCohortwithID/", data, config)
		   .then(
		       function(response){
		    	   if(response.data.message){
			        	 if(response.data.message=='NO Cohort Found.'){
			        		 $scope.sucessMsg="No Cohort with this name exists. Please try searching with other name.";
				          		
			        	 }
			       }else{
			    	     $scope.sucessMsg="";
			    	     $rootScope.existingCohort =  true;
			        	 $rootScope.addCohort =  response.data;
			        	// console.log("cohort"+$rootScope.addCohort.cohortvo.name);
			        	 //$location.path('/view-cohort');
			        	 //$location.path('/viewControlGroup');
				        	//******** get cohort rule details of control group
				        	 var cohRuledata = {
				        			 "cohortid":cohortId,
				 			        "cohortdescription": null,
				 			        "name": null
		        	        };

				        	 cohRuledata=JSON.stringify(cohRuledata);

		        	        var config = {
		        	            headers : {
		        	                'Content-Type': 'application/json'
		        	            }
		        	        }

		        	    	console.log("get cohort rule details of existing control group json object ---- "+cohRuledata);
				        	$http.post(myVars.site_prefix +"/ConfigController/searchWithCohortID/", cohRuledata, config) 
						  		   .then(
						  		       function(response){
						  		    	 $scope.sucessMsg="";
						  		    	 $scope.existRuleData = response.data;
						  		    	// $location.path('/viewCohort');
						  		    	
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
	 
	
	}*/
  
	//Approve Request
	$scope.approveRequest= function(selReqID){
		$scope.sucessMsg="";
    	$scope.errorMsg="";
    	$scope.selectedReqID = selReqID;
    	
    	var data =   {
    	           "userName": $scope.currentUsername,
    	           "clientID": 1,
    	             "srcSystem": 'WCM',
    	           "transactionid": null,
    	           "transName": 'Approval_Request',
    	         //  "transName": "tue_tn",
    	           "constraintid": null,
    	           "constraintName": null,
    	           "constraintValue": null,
    	           "privilegeRequested": null,
    	           "accessReason": null,
    	           "apiSwith": "BTG"
    	        };


    	data=JSON.stringify(data);
    	console.log("string"+data);
        var config = {
            headers : {
                'Content-Type': 'application/json'
            }
        }

    	console.log("search data is json object ---- "+data);
    	
    	$http.post(myVars.site_prefix +"/authRes/getAuthRespData/", data, config)
		   .then(
		       function(response){
			        	 if(response.data.response=='Allow'){
			        		 var modalinstance = $uibModal.open({
			        		      scope: $scope,
			        		      templateUrl: 'approveRequestModal.html'
			        		  	});
			        		 	//$scope.sucessMsg="This configuration search data doesn't exists. Please try searching again.";
			        		 	// console.log($scope.sucessMsg);
			        	 }
			        else{
			        	 $scope.errorMsg="Not Allowed";
			         }
		         
		       }, 
		       function(response){
		    	   $scope.errorMsg="Unable to search with given group name:: Error";
		         
		       }
		    );
    	
    	
	}
	$scope.approveReqData = function(selReqID,statusDate,statusUser,statusComment,statusMessage){
		var appReqdata = {
				  "his_reqreqtid": null,
			        "reqReqtID": selReqID,
			        "statusDate": statusDate,
			        "status": statusMessage,
			        "statusUser": statusUser,
			        "statusComments": statusComment
		}
		
		appReqdata = JSON.stringify(appReqdata);

    	console.log("viewReq JSON object --- "+appReqdata);

    	var config = {
            headers : {
                'Content-Type': 'application/json'
            }
        }

    	$http.post(myVars.site_prefix +"/TransactionController/saveReferralStatusHist/", appReqdata, config)
		   .then(
		       function(response){
		        
		         if(response.data.message){
		        	 if(response.data.message=='Request Approved - Pending to send WCM Target'){
		        		 $scope.sucessMsg='Request Approved.';
		        		 $scope.selectedStatus = "Pending";
			          		//alert('Please choose another name. Control Group with this name already exists in the system.');
		        	 }
		         }else{
		        	 $scope.sucessMsg='';
		        	 $rootScope.addRequest =  response.data;
		        	 console.log($rootScope.addRequest);
		        	 //$location.path('/viewCohort');
		        	 
		         }
		         
		         
		       }, 
		       function(response){
		          var msg = response.data.message;
		          $scope.errorMsg='Unable to create new Request :: Error';
		          //}
		       }
		    );
	}
	//Delete Request
	$scope.deleteRequest= function(selReqID){
		$scope.sucessMsg="";
    	$scope.errorMsg="";
    	$scope.selectedReqID = selReqID;
    	//console.log($scope.selectedReqID);
    	var modalinstance = $uibModal.open({
	      scope: $scope,
	      templateUrl: 'deleteRequestModal.html'
	  	});
	}
	
	$scope.deleteReqData = function(selReqID){
		var delReqdata = {
				  "his_reqreqtid": null,
			        "reqReqtID": selReqID,
			        "statusDate": null,
			        "status": null,
			        "statusUser": null,
			        "statusComments": null
		}
		
		delReqdata = JSON.stringify(delReqdata);

    	console.log("viewReq JSON object --- "+delReqdata);

    	var config = {
            headers : {
                'Content-Type': 'application/json'
            }
        }

    	$http.post(myVars.site_prefix +"/TransactionController/deleteReferralStatusHist/", delReqdata, config)
		   .then(
		       function(response){
		        
		         if(response.data.message){
		        	 if(response.data.message=='Request Approved - Pending to send WCM Target'){
		        		 $scope.sucessMsg='Request Deleted.';
		        		 $scope.selectedStatus = "Rejected";
			          		//alert('Please choose another name. Control Group with this name already exists in the system.');
		        	 }
		         }else{
		        	 $scope.sucessMsg='';
		        	 $rootScope.delRequest =  response.data;
		        	 console.log($rootScope.delRequest);
		        	 //$location.path('/viewCohort');
		        	 
		         }
		         
		         
		       }, 
		       function(response){
		          var msg = response.data.message;
		          $scope.errorMsg='Unable to create new Request :: Error';
		          //}
		       }
		    );
	}
	
	//Table Sorting
	//$scope.storeReqID = $rootScope.existRegData.reqReqtID;
	//console.log("Reqid"+$scope.storeReqID);
	 $scope.sortType     = $scope.selectedReqID; // set the default sort type
	  $scope.sortReverse  = false;  // set the default sort order
	  $scope.searchList   = '';     // set the default search/filter term
	  
	  
	  $scope.showPersonData = function(clientID){
		  var data = {
					"clientId":clientID
			};
			

	    	data=JSON.stringify(data);

	        var config = {
	            headers : {
	                'Content-Type': 'application/json'
	            }
	        }

	    	console.log("view ods data of existing json object ---- "+data);
	        
	        $http.post(myVars.site_prefix +"/ODSController/getODSWithClientID/", data, config)
			   .then(
			       function(response){
			    	   if(response.data.message){
				        	 if(response.data.message=='NO Request Found.'){
				        		 $scope.sucessMsg="No Request with any Cohort exists.";
					          		
				        	 }
				       }else{
				    	     $scope.sucessMsg="";
				    	     $rootScope.existingCohort =  true;
				        	 $rootScope.existODSData =  response.data;
				        	 console.log($rootScope.existODSData.clientProfileAdsSeqNum);
				        	 var modalinstance = $uibModal.open({
				       	      scope: $scope,
				       	      templateUrl: 'showPersonDetailsModal.html'
				       	  	});
					        	
					        	
				       }
			         
			       }, 
			       function(response){
			    	   $scope.errorMsg="Unable to view with given group name:: Error";
			       }
			    );// end of get select all cohort request
	  }
	  
	  
	  //Filter Table
	  $scope.getdetails = function (selStatus) {
		  console.log("status"+selStatus);
		  if (selStatus == "All"){
			  
			  var config = {
			            headers : {
			                'Content-Type': 'application/json'
			            }
			        }
			    	
			    	$http.post(myVars.site_prefix +"/TransactionController/listAllRegRequest/", null, config)
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
						        	 $location.path('/manageRegRequest');
							        	
							        	
						       }
					         
					       }, 
					       function(response){
					    	   $scope.errorMsg="Unable to view with given group name:: Error";
					       }
					    );// end of get select all cohort request
			  
		  }else{
			  var data = {
					  "status" : selStatus
			  }
	
		    	data=JSON.stringify(data);
	
		        var config = {
		            headers : {
		                'Content-Type': 'application/json'
		            }
		        }
	
		    	console.log("view ods data of existing json object ---- "+data);
		        
		        $http.post(myVars.site_prefix +"/TransactionController/getRegReqlistWithStatus/", data, config)
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
					        	 
						        	
						        	
					       }
				         
				       }, 
				       function(response){
				    	   $scope.errorMsg="Unable to view with given group name:: Error";
				       }
				    );// end of get select all cohort request
		  }
	  }
	//View Status History
	  $scope.viewStatusHistory = function(selReqID){
		  $scope.sucessMsg="";
	    	$scope.errorMsg="";
	    	$scope.selectedReqID = selReqID;
	    	//console.log($scope.selectedReqID);
	    	var modalinstance = $uibModal.open({
		      scope: $scope,
		      templateUrl: 'viewStatusHistoryModal.html'
		  	});
		  
		  var viewStatHistorydata = {
				  "his_reqreqtid": null,
			        "reqReqtID": selReqID
		}
		
		appReqdata = JSON.stringify(viewStatHistorydata);

    	console.log("viewReq JSON object --- "+viewStatHistorydata);

    	var config = {
            headers : {
                'Content-Type': 'application/json'
            }
        }

    	$http.post(myVars.site_prefix +"/TransactionController/listAllRefStatusHistByRegReqId/", viewStatHistorydata, config)
		   .then(
		       function(response){
		        
		         if(response.data.message){
		        	 if(response.data.message=='Request Approved - Pending to send WCM Target'){
		        		 $scope.sucessMsg='Request Approved.';
		        		 $scope.selectedStatus = "Pending";
			          		//alert('Please choose another name. Control Group with this name already exists in the system.');
		        	 }
		         }else{
		        	 $scope.sucessMsg='';
		        	 $rootScope.statusHistoryData =  response.data;
		        	 console.log($rootScope.statusHistoryData);
		        	 //$location.path('/viewCohort');
		        	 
		         }
		         
		         
		       }, 
		       function(response){
		          var msg = response.data.message;
		          $scope.errorMsg='Unable to create new Request :: Error';
		          //}
		       }
		    );
		  
		  
	  }
	  
}]);