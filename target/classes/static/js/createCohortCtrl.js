app.controller('createCohortCtrl', ['$scope', '$rootScope','$uibModal', '$http', '$location','myVars',function($scope, $rootScope,$uibModal, $http,$location,myVars) {
		$rootScope.viewControlGroupView = 1;
		$rootScope.showNavbar = true;
		$scope.master = [];
		$rootScope.selectedCohortId = null;
		$rootScope.selectedCohortName = null;
		$rootScope.selectedCohortDesc = null;
		$scope.sucessMsg="";
		$scope.errorMsg="";
		$rootScope.addCohort= null;
		$rootScope.existingCohort =  false;
		$rootScope.existRuleData = null;
		$scope.cohortData = null;
		$scope.searchCohort = function(cohortName){
			$rootScope.existingCohort =  false;
			$rootScope.existRuleData = null;
			$scope.cohortData = cohortName;
			if ($scope.cohortData != null && $scope.cohortData != undefined ){
				
				var data = {
						"cohortid":"",
						"cohortdescription":"",
						"name":$scope.cohortData
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
				var data = {
						"cohortid":"",
						"cohortdescription":"",
						"name":$scope.cohortData
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
					        	 if(response.data.message=='NO Group Found.'){
					        		 	$scope.sucessMsg="No Cohert  with this name exists. Please try searching with other name.";
						          		
					        	 }
					        	 
					         }else{
					        	 $scope.sucessMsg="";
					        	 $scope.myData = response.data;
					         }
				         
				       }, 
				       function(response){
				    	   $scope.errorMsg="Unable to search with given cohert name:: Error";
				         
				       }
				    )
				
			}
		 
		} //end of search

		$scope.reset = function() {
			$scope.sucessMsg="";
			$scope.errorMsg="";
			$scope.myData = [];
			//$window.location.reload();
	        $scope.cohortDetail = angular.copy($scope.master);
	    };

	    /** start of view group **/
	    $scope.viewCohortDetails = function(cohertId){
	    	
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

	    	console.log("view group data of existing json object ---- "+data);
	    	
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
				        	
					        	 var cohRuledata = {
					        			 "cohortid":cohertId,
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
							  		    	 $rootScope.existRuleData = response.data;
							  		    	 $location.path('/viewCohort');
							  		    	
							  		       }, 
							  		       function(response){
							  		    	 $scope.errorMsg='Unable to get the cohort rule for this group :: Error ';
							  		         
							  		       }
							  		    );// end of get cohort rule details of cohort 
					        	
				       }
			         
			       }, 
			       function(response){
			    	   $scope.errorMsg="Unable to view with given group name:: Error";
			       }
			    );// end of get control group details
		 
		}; 
	    /** End of view Cohort **/
	    
	    /** Start of Edit Cohort Name **/
	    $scope.open = function(itemId,itemName,itemDesc) {
	    	$rootScope.existingCohort =  false;
	    	$rootScope.existRuleData = null;
	    	$rootScope.selectedCohortId = itemId;
	    	console.log("cohortID"+$scope.selectedCohortId);
	    	$rootScope.selectedCohortName = itemName;
	    	console.log("cohortname"+$scope.selectedCohortName);
	    	$rootScope.selectedCohortDesc = itemDesc;
		    var modalinstance = $uibModal.open({
		      scope: $scope,
		      templateUrl: 'editCohortModel.html',
		      resolve: {
		        myData: function() {
		          return $scope.myData;
		        }
		      }
		    });
		  
	  	};

	  
	  	$scope.editCohortSave = function(updatedDesc){
	  		console.log("updatedDesc="+updatedDesc);
	        var data = {
	            "cohortid": $rootScope.selectedCohortId,
		        "name": $rootScope.selectedCohortName,
		        "cohortdescription": updatedDesc
	        };

	    	data=JSON.stringify(data);
	    	
	    	var config = {
	            headers : {
	                'Content-Type': 'application/json'
	            }
	        }

	    	console.log("selected data is json object ---- "+data);
	    	
	    	$http.post(myVars.site_prefix +"/ConfigController/updateCohort/", data, config)
			   .then(
			       function(response){
			    	 $scope.sucessMsg='Successfully changed the cohort Description to '+ updatedDesc;
			         var editcohertDesc="td."+$scope.selectedCohortId+"cohortdescription";
			         console.log(editcohertDesc);
			 	   	 $(editcohertDesc).text(updatedDesc);
			       }, 
			       function(response){
			    	   $scope.errorMsg='Unable to change the cohort Description:: Error ';
			       }
			    );
	    };
	    /** End of Edit Cohort Name **/


	    /** Start of Delete Group  **/
	    $scope.deleteCohort = function(itemId,itemName) {
	    	$scope.selectedCohortId = itemId;
	    	$scope.selectedCohortName = itemName;
	    	var modalinstance = $uibModal.open({
		      scope: $scope,
		      templateUrl: 'deleteCohortModel.html'
		  	});
		    
	  	};

	  	$scope.delCohort = function(){
	  		var respDelData = {
	            "cohortid": $scope.selectedCohortId,
		        "name": $scope.selectedCohortName,
		        "cohortdescription": ""
	        };

	    	respDelData = JSON.stringify(respDelData);

	    	console.log("deleteCohort JSON object --- "+respDelData);

	    	var config = {
	            headers : {
	                'Content-Type': 'application/json'
	            }
	        }

	    	$http.post(myVars.site_prefix +"/ConfigController/deleteCohort/", respDelData, config)
			   .then(
			       function(response){
			    	   if(response.data.message){
				        	 if(response.data.message=='Error - in deletion'){
				        		 $scope.errorMsg="Data is present for this cohort. Deletion not possible.";
				        	 }else{
					        	 $scope.sucessMsg="Deleted "+$scope.selectedCohortName+" Cohort successfully";
						         var editcohortname="td."+$scope.selectedCohortId+"name";
						 	   	 $(editcohortname).closest('tr').remove();
					         }
				         }
			         
			         
			       }, 
			       function(response){
			    	   $scope.errorMsg="Unable to delete the cohort :: Error";
			       }
			    );
	  	};
	    /** End of Delete Cohort **/


	    /** Start of View Cohort  **/
	    $scope.viewCohort = function() {
	    	$rootScope.existRuleData = null;
	    	var modalinstance = $uibModal.open({
		      scope: $scope,
		      templateUrl: 'createCohortModel.html'
		  	});
		  
	  	};

	  	$scope.viewCohortName = function(cohortName,cohortDesc){
	  		
	  		var viewCohortData = {
		  				"cohortid": null,
		  		        "name": cohortName,
		  		        "cohortdescription": cohortDesc
		  	        };


	  		viewCohortData = JSON.stringify(viewCohortData);

	    	console.log("viewGroup JSON object --- "+viewCohortData);

	    	var config = {
	            headers : {
	                'Content-Type': 'application/json'
	            }
	        }

	    	$http.post(myVars.site_prefix +"/ConfigController/saveCohort/", viewCohortData, config)
			   .then(
			       function(response){
			        
			         if(response.data.message){
			        	 if(response.data.message=='Duplicate'){
			        		 $scope.sucessMsg='Please choose another name. Cohort with this name already exists in the system.';
				          		//alert('Please choose another name. Control Group with this name already exists in the system.');
			        	 }
			         }else{
			        	 $scope.sucessMsg='';
			        	 $rootScope.addCohort =  response.data;
			        	 console.log($rootScope.addCohort.name);
			        	 $location.path('/viewCohort');
			        	 
			         }
			         
			         
			       }, 
			       function(response){
			          var msg = response.data.message;
			          $scope.errorMsg='Unable to create new cohort :: Error';
			       }
			    );
	  	};

	  	/** End of View Cohort**/

	}]);