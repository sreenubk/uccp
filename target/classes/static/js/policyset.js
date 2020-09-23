app.controller('policySetCtrl', ['$scope', '$rootScope','$uibModal', '$http',  '$location','myVars',function($scope, $rootScope,$uibModal, $http,$location,myVars) {
	$rootScope.viewControlGroupView = 7;
	$rootScope.showNavbar = true;
	$scope.master = [];
	//$scope.myData = [];
	$scope.selectedId = null;
	$rootScope.selectedName = null;
	$scope.sucessMsg="";
	$scope.errorMsg="";
	$rootScope.addPolicy = null;
	$rootScope.existingGroup =  false;
	$rootScope.existAttrData = null;
	$rootScope.existTranData = null;
	$rootScope.ctrlPolicyNameSearch =null;

	$scope.search = function(ctrlPolicyName){
		//$rootScope.existingGroup =  false;
		//$rootScope.existAttrData = null;
		//$rootScope.existTranData = null;
		$rootScope.ctrlPolicyNameSearch = ctrlPolicyName;
		var data = {
	        "policysetid": null,
	        "consentstatus": null,
	        "classificationlevel": null,
	        "comments": null,
	        "createdby": null,
	        "createdon": null,
	        "lastupdatedby": null,
	        "lastupdatedon": null,
	        "name": ctrlPolicyName,
	        "transactionType": null
	    }; 

    	data=JSON.stringify(data);

        var config = {
            headers : {
                'Content-Type': 'application/json'
            }
        }

    	console.log("search data for policy set is json object ---- "+data);
    	
    	$http.post(myVars.site_prefix +"/auth/listgrpwithgrpName/", data, config)
		   .then(
		       function(response){
		    	   if(response.data.message){
			        	 if(response.data.message=='NO Group Found.'){
			        		 	$scope.errorMsg="No Policy Set with this name exists. Please try searching with other name.";
				          		
			        	 }
			        	 
			         }else{
			        	 $scope.sucessMsg="";
			        	 $scope.errorMsg="";
			        	 $scope.myData = response.data;
			         }
		         
		       }, 
		       function(response){
		    	   $scope.errorMsg="Unable to search with given Policy Set name:: Error";
		         
		       }
		    );
	 
	} //end of search

	$scope.reset = function() {
		$scope.sucessMsg="";
		$scope.errorMsg="";
		$scope.myData=[];
        $scope.ctrlGroup = angular.copy($scope.master);
    };

    /** start of view group **/
    $scope.viewGroupDetails = function(policysetid){
    	
		var data = {
		        "policysetid": policysetid,
		        "consentstatus": null,
		        "classificationlevel": null,
		        "comments": null,
		        "createdby": null,
		        "createdon": null,
		        "lastupdatedby": null,
		        "lastupdatedon": null,
		        "name": null,
		        "transactionType": null
		    }; 

    	data=JSON.stringify(data);

        var config = {
            headers : {
                'Content-Type': 'application/json'
            }
        }

    	console.log("view group data of existing json object ---- "+data);
    	
    	$http.post(myVars.site_prefix +"/auth/viewAuthPolicy/", data, config)
		   .then(
		       function(response){
		    	   if(response.data.message){
			        	 if(response.data.message=='NO Group Found.'){
			        		 $scope.sucessMsg="No Control Group with this name exists. Please try searching with other name.";
				          		
			        	 }
			       }else{
			    	     $scope.sucessMsg="";
			    	     $rootScope.existingGroup =  true;
			        	 $rootScope.addPolicy =  response.data;
			    	     //$rootScope.existTranData =  response.data;
			        	 $location.path('/viewPolicySet');
			        	 //$location.path('/viewControlGroup');
			        	
			        	
			       }
		         
		       }, 
		       function(response){
		    	   $scope.errorMsg="Unable to view with given group name:: Error";
		        
		         //alert('Unable to view with given group name:: Error '+ response.statusText);
		       }
		    );// end of get control group details
	 
	}; 
    /** End of view group **/
    
    /** Start of Edit Policy Set x.policysetid,x.classificationlevel **/
    $scope.open = function(itemId,itemName,itemconsentstatus,itemcomments) {
    	$rootScope.existingGroup =  false;
    	$rootScope.existAttrData = null;
    	$rootScope.existTranData = null;
    	$scope.selectedId = itemId;
    	$rootScope.selectedName = itemName;
    	$rootScope.selectedcnsentstatus = itemconsentstatus;
    	$rootScope.selectedcomments =itemcomments; 
    	$scope.sucessMsg='';
    	$scope.errorMsg='';
	    var modalinstance = $uibModal.open({
	      scope: $scope,
	      templateUrl: 'editPolicySetModal.html',
	      resolve: {
	        myData: function() {
	          return $scope.myData;
	        }
	      }
	    });
	  
  	};

  	
  	
  	$scope.editSave = function(updatedName,updatedcnsentstatus, updatedcomments){
  		//console.log("updatedName="+updatedName);
    	// use $.param jQuery function to serialize data from JSON 
        
        var data = {
    	        "policysetid": $scope.selectedId,
    	        "consentstatus": updatedcnsentstatus,
    	        "classificationlevel": null,
    	        "comments": updatedcomments,
    	        "createdby": null,
    	        "createdon": null,
    	        "lastupdatedby": null,
    	        "lastupdatedon": null,
    	        "name": updatedName,
    	        "transactionType": null
    	    }; 

    	data=JSON.stringify(data);
    	
    	var config = {
            headers : {
                'Content-Type': 'application/json'
            }
        }

    	console.log("selected data is json object ---- "+data);
    	
    	$http.post(myVars.site_prefix +"/auth/updatePolySet/", data, config)
		   .then(
		       function(response){
		    	   if(response.data.message=="Unable to Update Record."){
		    		   $scope.errorMsg="Unable to Update Record :: Error";
		    	   }else{
		    		   $scope.sucessMsg='Successfully changed the policy set  - '+ updatedName;
				         // var editcontrolgroupname="td."+$scope.selectedId+"policySetName a";
				 	   	 //$(editcontrolgroupname).text(updatedName);
		    		   var edata = {
		    			        "policysetid": null,
		    			        "consentstatus": null,
		    			        "classificationlevel": null,
		    			        "comments": null,
		    			        "createdby": null,
		    			        "createdon": null,
		    			        "lastupdatedby": null,
		    			        "lastupdatedon": null,
		    			        "name": $rootScope.ctrlPolicyNameSearch,
		    			        "transactionType": null
		    			    }; 

		    		    	edata=JSON.stringify(edata);

		    		        var config = {
		    		            headers : {
		    		                'Content-Type': 'application/json'
		    		            }
		    		        }

		    		    	
		    		    	$http.post(myVars.site_prefix +"/auth/listgrpwithgrpName/", edata, config)
		    				   .then(
		    				       function(response){
		    				    	   if(response.data.message){
		    					        	 if(response.data.message=='NO Group Found.'){
		    					        		 	//$scope.errorMsg="No Policy Set with this name exists. Please try searching with other name.";
		    						          		
		    					        	 }
		    					        	 
		    					         }else{
		    					        	// $scope.sucessMsg="";
		    					        	 $scope.myData = response.data;
		    					         }
		    				         
		    				       }, 
		    				       function(response){
		    				    	  // $scope.errorMsg="Unable to search with given Policy Set name:: Error";
		    				         
		    				       }
		    				    );
		    	   }
		    	 
		       }, 
		       function(response){
		    	   $scope.errorMsg='Unable to change the policy set name:: Error ';
		         //alert('Unable to change the group name:: Error '+ response.statusText);
		       }
		    );
    };
    /** End of Edit Group Name **/


    /** Start of Delete Group  **/
    $scope.deleteGroup = function(itemId,itemName) {
    	$scope.selectedId = itemId;
    	$scope.selectedName = itemName;
    	$scope.sucessMsg='';
    	$scope.errorMsg='';
    	var modalinstance = $uibModal.open({
	      scope: $scope,
	      templateUrl: 'deletePolicySetModal.html'
	  	});
	    
  	};

  	$scope.delGroup = function(){
  		//$('#'+$scope.selectedId).closest('tr').remove();
  		var respDelData={
	        "policysetid": $scope.selectedId,
	        "consentstatus": null,
	        "classificationlevel": null,
	        "comments": null,
	        "createdby": null,
	        "createdon": null,
	        "lastupdatedby": null,
	        "lastupdatedon": null,
	        "name": $scope.selectedName,
	        "transactionType": null
	    }; 

    	respDelData = JSON.stringify(respDelData);

    	console.log("deleteGroup JSON object --- "+respDelData);

    	var config = {
            headers : {
                'Content-Type': 'application/json'
            }
        }

    	$http.post(myVars.site_prefix +"/auth/deletePolySet/", respDelData, config)
		   .then(
		       function(response){
		    	   if(response.data.message){
			        	 if(response.data.message=='Error-child record present'){
			        		 $scope.errorMsg="Authorization data is present for this policy set. Deletion not possible.";
				          		//alert('Constraint data or Transaction data is present for this control group. Deletion not possible.');
			        	 }else{
				        	 $scope.sucessMsg="Deleted the policy set successfully";
					         //alert('Deleted the group successfully');
					         var editcontrolgroupname="td."+$scope.selectedId+"policySetName";
					 	   	 $(editcontrolgroupname).closest('tr').remove();
				         }
			         }
		         
		         
		       }, 
		       function(response){
		    	   $scope.errorMsg="Unable to delete the group :: Error";
		        // alert('Unable to delete the group :: Error  '+ response.statusText);
		       }
		    );
  	};
    /** End of Delete Group  **/


    /** Start of createPolicySet  **/
    $scope.createPolicySet = function() {
    	$rootScope.existingGroup =  false;
    	$rootScope.existAttrData = null;
    	$rootScope.existTranData = null;
    	$scope.sucessMsg='';
    	$scope.errorMsg='';
    	var modalinstance = $uibModal.open({
	      scope: $scope,
	      templateUrl: 'createPolicySetModal.html'
	  	});
	  
  	};

  	$scope.savePolicySet = function(consentStatus,classificationLevel,comments,policySetName,transactionType){

  		
  		var savePolicySetData = {
  				"policysetid": null,
  		        "consentstatus": consentStatus,
  		        "classificationlevel": classificationLevel,
  		        "comments": comments,
  		        "createdby": $rootScope.globals.currentUser.username,
  		        "createdon": null,
  		        "lastupdatedby": null,
  		        "lastupdatedon": null,
  		        "name": policySetName,
  		        "transactionType": transactionType
  	    };
  		
  		
  		

  		savePolicySetData = JSON.stringify(savePolicySetData);

    	console.log("savePolicySetData JSON object --- "+savePolicySetData);

    	var config = {
            headers : {
                'Content-Type': 'application/json'
            }
        }

    	$http.post(myVars.site_prefix +"/auth/savePolySet/", savePolicySetData, config)
		   .then(
		       function(response){
		        
		         if(response.data.message){
		        	 if(response.data.message=='Duplicate'){
		        		 $scope.sucessMsg='Please choose another name. Policy Set with this name already exists in the system.';
			          		
		        	 }
		         }else{
		        	 $scope.sucessMsg='';
		        	 $rootScope.addPolicy =  response.data;
		        	 $location.path('/viewPolicySet');
		        	 
		         }
		         
		         
		       }, 
		       function(response){
		          var msg = response.data.message;
		          
		          $scope.errorMsg='Unable to create new group :: Error';
		          //}
		       }
		    );
  	};

  	/** End of savePolicySet**/

}]);