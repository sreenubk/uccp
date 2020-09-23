app.controller('manageAlertRequestCtrl', ['$scope', '$rootScope','$uibModal', '$http',  '$location','myVars',function($scope, $rootScope,$uibModal, $http,$location,myVars) {
	$rootScope.showNavbar = true;
	$scope.date = new Date();
	$scope.selectedReqID = null;
	$scope.storeReqID = null;
	$rootScope.existODSData = "";
	$scope.selectedStatus = null;
	$scope.isVisible = false;
	$scope.selReasonCodeData = null;
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
	/* Start of Select Reason Code*/
	 	$scope.selectedReasonCode = function(selReasonCode){
	 		$scope.sucessMsg="";
	 		$scope.errorMsg="";
	    	$scope.selectedReasonCode = selReasonCode;
	    	var modalinstance = $uibModal.open({
		      scope: $scope,
		      templateUrl: 'selectReasonCodeModal.html'
		  	});
	    	
	    	var data = {
					  "reasonCode": $scope.selectedReasonCode
			}
			
			data = JSON.stringify(data);

	    	console.log("viewReq JSON object --- "+data);

	    	var config = {
	            headers : {
	                'Content-Type': 'application/json'
	            }
	        }
	    	
	    	$http.post(myVars.site_prefix +"/ConfigController/getReasonCode/", data, config)
			   .then(
			       function(response){
			        
			         if(response.data.message){
			        	 if(response.data.message=='Duplicate'){
			        		 $scope.sucessMsg='Please choose another name. Job with this name already exists in the system.';
				        }
			         }else{
			        	 $scope.sucessMsg='';
			        	 $scope.selReasonCodeData =  response.data;
			        	 console.log($scope.selReasonCodeData.reasonCode);
			        	 //$location.path('/viewJob');
			        	 
			         }
			         
			         
			       }, 
			       function(response){
			          var msg = response.data.message;
			          $scope.errorMsg='Unable to create new Job :: Error';
			       }
			    );
	 	}
	/* End of Select Reason Code*/
		//Approve Request
		$scope.approveRequest= function(selReqID){
			$scope.sucessMsg="";
	    	$scope.errorMsg="";
	    	$scope.selectedReqID = selReqID;
	    	$scope.isVisible = true;
	    	 var modalinstance = $uibModal.open({
			      scope: $scope,
			      templateUrl: 'approveRequestModal.html'
			  	});
	    	/*var data = 
	    	{
	    	    "userName": $scope.currentUsername,
	    	    "clientID": null,
	      	    "srcSystem": 'Connect360',
	    	    "transactionid": null,
	    	    "transName": "Approval_Request",
	    	    "constraintid": null,
	    	    "constraintName": null,
	    	    "constraintValue": null,
	    	    "privilegeRequested": null,
	    	    "accessReason": "Approved",
	    	    "apiSwith": "Policy",
	    	    "attributehm":{
	    	    	"txnname":"Approval_Request"
	    	    }*/
	    	};


	    	/*data=JSON.stringify(data);
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
				        	 var modalinstance = $uibModal.open({
			        		      scope: $scope,
			        		      templateUrl: 'authApproveRequestModal.html'
			        		  	});
				         }
			         
			       }, 
			       function(response){
			    	   $scope.errorMsg="Unable to search with given group name:: Error";
			         
			       }
			    );
	    	
	    	
		}*/
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

	    	$http.post(myVars.site_prefix +"/TransactionController/saveAlertStatusHist/", appReqdata, config)
			   .then(
			       function(response){
			        
			         if(response.data.message){
			        	 if(response.data.message=='Request Approved - Pending to send WCM Target'){
			        		 $scope.sucessMsg='Request Approved.';
			        		 $scope.selectedStatus = "PENDING INTERFACE";
			        	}
			         }else{
			        	 $scope.sucessMsg='';
			        	 $rootScope.addRequest =  response.data;
			        	 console.log($rootScope.addRequest);
			        	 
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
    	var modalinstance = $uibModal.open({
		      scope: $scope,
		      templateUrl: 'deleteRequestModal.html'
    	});
	}
    /*	var data = 
    	{
    	   "userName": $scope.currentUsername,
	       "clientID": null,
	       "srcSystem": 'Connect360',
	       "transactionid": null,
	       "transName": "Delete_Request",
	       "constraintid": null,
	       "constraintName": null,
	       "constraintValue": null,
	       "privilegeRequested": null,
	       "accessReason": "To Delete Request",
	       "apiSwith": "Policy",
	       "attributehm":{
	        "txnname":"Delete_Request"
	       }
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
			        		      templateUrl: 'deleteRequestModal.html'
			        		  	});
			        		 	//$scope.sucessMsg="This configuration search data doesn't exists. Please try searching again.";
			        		 	// console.log($scope.sucessMsg);
			        	 }
			        else{
			        	 var modalinstance = $uibModal.open({
		        		      scope: $scope,
		        		      templateUrl: 'authDeleteRequestModal.html'
		        		  	});
			         }
		         
		       }, 
		       function(response){
		    	   $scope.errorMsg="Unable to search with given group name:: Error";
		         
		       }
		    );
 	
	}*/
	
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
		        	 }
		         }else{
		        	 $scope.sucessMsg='';
		        	 $rootScope.delRequest =  response.data;
		        	 console.log($rootScope.delRequest);
		        	 
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
	 $scope.sortType     = 'reqReqtID'; // set the default sort type
	 console.log( "Sort Type" +$scope.sortType);
	  $scope.sortReverse  = false;  // set the default sort order
	  //$scope.searchList   = '';     // set the default search/filter term
	  
	  
	  
	  $scope.showPersonData = function(srcSys,srcSysID){
		  var data = {
				    "memIdnum": srcSysID,
			        "srcCode": srcSys
			};
			

	    	data=JSON.stringify(data);

	        var config = {
	            headers : {
	                'Content-Type': 'application/json'
	            }
	        }

	    	console.log("view ods data of existing json object ---- "+data);
	        
	        $http.post(myVars.site_prefix +"/PersonSearch/getClientList/", data, config)
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
				        	 //console.log($rootScope.existODSData.clientProfileAdsSeqNum);
				        	 var modalinstance = $uibModal.open({
				       	      scope: $scope,
				       	      templateUrl: 'showPersonDetailsModal.html'
				       	  	});
					        	
					        	
				       }
			         
			       }, 
			       function(response){
			    	   $scope.errorMsg="Unable to view with given group name:: Error";
			       }
			    );
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
						        	 //$location.path('/manageRegRequest');
							        	
							        	
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
	
		    	console.log("view data of existing json object ---- "+data);
		        
		        $http.post(myVars.site_prefix +"/TransactionController/getRegReqlistWithStatus/", data, config)
				   .then(
				       function(response){
				    	   if(response.data.message){
					        	 if(response.data.message=='NO Request Found.'){
					        		 $scope.sucessMsg="No Request with any Request exists.";
						          		
					        	 }
					       }else{
					    	     $scope.sucessMsg="";
					    	     $rootScope.existingCohort =  true;
					        	 $rootScope.existRegData =  response.data;
					        	 //$location.path('/manageRegRequest');
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

    	$http.post(myVars.site_prefix +"/TransactionController/listAllAlertStatusHistByAlertReqId/", viewStatHistorydata, config)
		   .then(
		       function(response){
		        
		         if(response.data.message){
		        	 if(response.data.message=='Request Approved - Pending to send WCM Target'){
		        		 $scope.sucessMsg='Request Approved.';
		        		 $scope.selectedStatus = "Pending";
		        	 }
		         }else{
		        	 $scope.sucessMsg='';
		        	 $rootScope.statusHistoryData =  response.data;
		        	 console.log($rootScope.statusHistoryData);
		        	 
		         }
		         
		         
		       }, 
		       function(response){
		          var msg = response.data.message;
		          $scope.errorMsg='Unable to create new Request :: Error';
		       }
		    );
		  
		  
	  }
	  
	  
	//View Send History
	 $scope.sendStatusHistory = function(selReqID){
		  $scope.sucessMsg="";
	    	$scope.errorMsg="";
	    	$scope.selectedReqID = selReqID;
	    	console.log($scope.selectedReqID);
	    	
	    	var modalinstance = $uibModal.open({
		      scope: $scope,
		      templateUrl: 'sendStatusHistoryModal.html'
		  	});
		  
		  var sendHistdata = {
				  "requestId": selReqID,
				  "apiAction": "ALERT_REQUEST"
		}
		
		appReqdata = JSON.stringify(sendHistdata);

    	console.log("viewReq JSON object --- "+sendHistdata);

    	var config = {
            headers : {
                'Content-Type': 'application/json'
            }
        }

    	$http.post(myVars.site_prefix +"/TransactionController/listAllSendHistByReqActionAndReqId/", sendHistdata, config)
		   .then(
		       function(response){
		        
		         if(response.data.message){
		        	 
		         }else{
		        	 $scope.sucessMsg='';
		        	 $rootScope.sendHistorydata =  response.data;
		        	 console.log($rootScope.sendHistorydata);
		        	 
		         }		         
		         
		       }, 
		       function(response){
		          var msg = response.data.message;
		          $scope.errorMsg='Unable to get send history :: Error';
		       }
		    );
		  
		  
	  }
	  
}]);