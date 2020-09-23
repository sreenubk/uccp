app.controller('createTargetCtrl', ['$scope', '$rootScope','$uibModal', '$http',  '$location','$compile','myVars',function($scope, $rootScope,$uibModal, $http,$location,$compile,myVars) {
		$rootScope.viewControlGroupView = 1;
		$rootScope.showNavbar = true;
		$scope.master = [];
		//$scope.myData = [];
		$scope.selectedTargetId = null;
		$rootScope.selectedTargetName = null;
		$rootScope.selectedTargetDesc = null;
		$scope.sucessMsg="";
		$scope.errorMsg="";
		$rootScope.addTarget= null;
		$rootScope.existingTarget =  false;
		//$rootScope.existAttrData = null;
		$rootScope.existTargetData = null;
	    var transRow = 1;
	    
		$scope.search = function(targetDetail){
			$rootScope.existingTarget =  false;
			//$rootScope.existAttrData = null;
			//$rootScope.existRuleData = null;
			var data = {};

	    	data=JSON.stringify(data);

	        var config = {
	            headers : {
	                'Content-Type': 'application/json'
	            }
	        }

	    	console.log("search data is json object ---- "+data);
	    	
	    	$http.post(myVars.site_prefix +"/ConfigController/listAllTarget/", data, config)
			   .then(
			       function(response){
			    	   if(response.data.message){
				        	 if(response.data.message=='NO Group Found.'){
				        		 	$scope.sucessMsg="No Target  with this name exists. Please try searching with other name.";
					          		
				        	 }
				        	 
				         }else{
				        	 $scope.sucessMsg="";
				        	 $scope.myData = response.data;
				         }
			         
			       }, 
			       function(response){
			    	   $scope.errorMsg="Unable to search with given target name:: Error";
			         
			       }
			    );
		 
		} //end of search

		$scope.reset = function() {
			$scope.sucessMsg="";
			$scope.errorMsg="";
			$scope.myData = [];
	        $scope.cohortDetail = angular.copy($scope.master);
	    };

	    /** start of view group **/
	    $scope.viewTargetDetails = function(targetId){
	    	
			var data = {
			        "targetedID": targetId,
			        "targetName": null,
			        "targetAddress": null,
			        "targetAltAddress": null,
			        "targetDescription": null
			};
			

	    	data=JSON.stringify(data);

	        var config = {
	            headers : {
	                'Content-Type': 'application/json'
	            }
	        }

	    	console.log("view target data of existing json object ---- "+data);
	    	
	    	$http.post(myVars.site_prefix +"/ConfigController/getTarget/", data, config)
			   .then(
			       function(response){
			    	   if(response.data.message){
				        	 if(response.data.message=='NO Target Found.'){
				        		 $scope.sucessMsg="No Target with this name exists. Please try searching with other name.";
					          		
				        	 }
				       }else{
				    	     $scope.sucessMsg="";
				    	     $rootScope.existingTarget =  true;
				        	 $rootScope.addTarget =  response.data;
				        	// console.log("cohort"+$rootScope.addCohort.cohortvo.name);
				        	 //$location.path('/view-cohort');
				        	 //$location.path('/viewControlGroup');
					        	//******** get cohort rule details of control group
					        	 var targetCohdata = {
					 			       "targetedID": targetId,
								        "targetName": null,
								        "targetAddress": null,
								        "targetAltAddress": null,
								        "targetDescription": null
			        	        };

					        	 targetCohdata=JSON.stringify(targetCohdata);

			        	        var config = {
			        	            headers : {
			        	                'Content-Type': 'application/json'
			        	            }
			        	        }

			        	    	console.log("get target details of existing control group json object ---- "+targetCohdata);
					        	$http.post(myVars.site_prefix +"/ConfigController/getlistTarget/", targetCohdata, config) 
							  		   .then(
							  		       function(response){
							  		    	 $scope.sucessMsg="";
							  		    	 $rootScope.existTargetData = response.data;
							  		    	 $location.path('/viewTarget');
							  		    	
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
	    
	    /** Start of Edit Target Name **/
	    $scope.open = function(itemId,itemName,itemAddr,itemAltAddr,itemDesc) {
	    	$scope.selectedTargetId = itemId;
	    	console.log("targetID"+$scope.selectedTargetId);
	    	$rootScope.selectedTargetName = itemName;
	    	console.log("targetname"+$scope.selectedTargetName);
	    	$rootScope.selectedTargetAddress = itemAddr;
	    	$rootScope.selectedTargetAltAddress = itemAltAddr;
	    	$rootScope.selectedTargetDesc = itemDesc;
		    var modalinstance = $uibModal.open({
		      scope: $scope,
		      templateUrl: 'editTargetModel.html',
		      resolve: {
		        myData: function() {
		          return $scope.myData;
		        }
		      }
		    });
		  
	  	};

	  
	  	$scope.editTargetSave = function(updatedName,updatedAddress,updatedAltAddress,updatedDesc){
	  		console.log("updatedName="+updatedName);
	        var data = {
		        "targetedID": $scope.selectedTargetId,
		        "targetName": updatedName,
		        "targetAddress": updatedAddress,
		        "targetAltAddress": updatedAltAddress,
		        "targetDescription": updatedDesc
	        };

	    	data=JSON.stringify(data);
	    	
	    	var config = {
	            headers : {
	                'Content-Type': 'application/json'
	            }
	        }

	    	console.log("selected data in json object ---- "+data);
	    	
	    	$http.post(myVars.site_prefix +"/ConfigController/updateTarget/", data, config)
			   .then(
			       function(response){
			    	   var editTargetname="td."+$scope.selectedTargetId+"targetName";
				 	   	 $(editTargetname).text(updatedName);
				 	    var editTargetAddress="td."+$scope.selectedTargetId+"targetAddress";
				 	   	 $(editTargetAddress).text(updatedAddress);
				 	    var editTargetAltAddress="td."+$scope.selectedTargetId+"targetAltAddress";
				 	   	 $(editTargetAltAddress).text(updatedAltAddress);
				 	    var editTargetDesc="td."+$scope.selectedTargetId+"targetDescription";
				 	   	 $(editTargetDesc).text(updatedDesc);
			    	 $scope.sucessMsg='Successfully changed the Target Details ';
			        
			       }, 
			       function(response){
			    	   $scope.errorMsg='Unable to change the Target name:: Error ';
			       }
			    );
	    };
	    /** End of Edit Target Name **/


	    /** Start of Delete Target  **/
	    $scope.deleteTarget = function(itemId,itemName) {
	    	//$scope.sucessMsg="";
	    	//$scope.errorMsg="";
	    	$scope.selectedTargetId = itemId;
	    	$scope.selectedTargetName = itemName;
	    	var modalinstance = $uibModal.open({
		      scope: $scope,
		      templateUrl: 'deleteTargetModel.html'
		  	});
		    
	  	};

	  	$scope.delTarget = function(){
	  		var delTargetData = {
		        	"targetedID": $scope.selectedTargetId,
	        	    "targetName": $scope.selectedTargetName,
	        	    "targetAddress": "",
	        	    "targetAltAddress": "",
	        	    "targetDescription": ""
	        };

	  		delTargetData = JSON.stringify(delTargetData);

	    	console.log("deleteTarget JSON object --- "+delTargetData);

	    	var config = {
	            headers : {
	                'Content-Type': 'application/json'
	            }
	        }

	    	$http.post(myVars.site_prefix +"/ConfigController/deleteTarget/", delTargetData, config)
			   .then(
			       function(response){
			    	   if(response.data.message){
				        	 if(response.data.message=='Error-child record present'){
				        		 $scope.errorMsg="Transaction data is present for this Target. Deletion not possible.";
					        }else if(response.data.message=='deleted'){
						         var editTargetname="td."+$scope.selectedTargetId+"targetName";
						         console.log(editTargetname);
						 	   	 $(editTargetname).closest('tr').remove();
						 	   	$scope.sucessMsg="Deleted Target successfully";
					         }
				         }
			         
			         
			       }, 
			       function(response){
			    	   $scope.errorMsg="Unable to delete the target :: Error";
			       }
			    );
	  	};
	    /** End of Delete Target **/


	    /** Start of View Target  **/
	    $scope.viewTarget = function() {
	    	var modalinstance = $uibModal.open({
		      scope: $scope,
		      templateUrl: 'createTargetModel.html'
		  	});
		  
	  	};

	  	$scope.viewTargetName = function(targetName,targetAddress,targetAltAddress,targetDesc){
	  		var viewTargetData = {
	  				"targetedID": null,
	  		        "targetName": targetName,
	  		        "targetAddress": targetAddress,
	  		        "targetAltAddress": targetAltAddress,
	  		        "targetDescription": targetDesc
		  	        };


	  		viewCohortData = JSON.stringify(viewTargetData);

	    	console.log("viewTarget JSON object --- "+viewTargetData);

	    	var config = {
	            headers : {
	                'Content-Type': 'application/json'
	            }
	        }

	    	$http.post(myVars.site_prefix +"/ConfigController/saveTarget/", viewTargetData, config)
			   .then(
			       function(response){
			        	 if(response.data.message=='Duplicate'){
			        		 $scope.sucessMsg='Please choose another name. Target with this name already exists in the system.';
			        	 }
			         else{
			        	 $scope.sucessMsg='';
			        	 $rootScope.addTarget =  response.data;
			        	console.log($scope.addTarget);
			        	 $location.path('/viewTarget');
			         }
			         
			         
			       }, 
			       function(response){
			          var msg = response.data.message;
			          $scope.errorMsg='Unable to create new target :: Error';
			       }
			    );
	  	};

	  	/** End of View Target**/

	}]);