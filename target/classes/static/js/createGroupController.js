app.controller('createControlGroupCtrl', ['$scope', '$rootScope','$uibModal', '$http',  '$location','myVars',function($scope, $rootScope,$uibModal, $http,$location,myVars) {
	$rootScope.showNavbar = true;
	$rootScope.viewControlGroupView = 1;
	$scope.master = [];
	//$scope.myData = [];
	$scope.selectedId = null;
	$rootScope.selectedName = null;
	$scope.sucessMsg="";
	$scope.errorMsg="";
	$rootScope.addGroup = null;
	$rootScope.existingGroup =  false;
	$rootScope.existAttrData = null;
	$rootScope.existTranData = null;

	$scope.search = function(ctrlGroup){
		$rootScope.existingGroup =  false;
		$rootScope.existAttrData = null;
		$rootScope.existTranData = null;
		var data = {
            "controlgroupid": "",
	        "controlgroupname": ctrlGroup,
	        "createdby": "",
	        "createdon": null,
	        "lastupdatedby": "",
	        "lastupdatedon":""
        };

    	data=JSON.stringify(data);

        var config = {
            headers : {
                'Content-Type': 'application/json'
            }
        }

    	console.log("search data is json object ---- "+data);
    	
    	$http.post(myVars.site_prefix +"/control/listgrpwithgrpName/", data, config)
		   .then(
		       function(response){
		    	   if(response.data.message){
			        	 if(response.data.message=='NO Group Found.'){
			        		 	$scope.sucessMsg="No Control Group with this name exists. Please try searching with other name.";
				          		
			        	 }
			        	 
			         }else{
			        	 $scope.sucessMsg="";
			        	 $scope.myData = response.data;
			         }
		         
		       }, 
		       function(response){
		    	   $scope.errorMsg="Unable to search with given group name:: Error";
		         
		       }
		    );
	 
	} //end of search

	$scope.reset = function() {
		$scope.sucessMsg="";
		$scope.errorMsg="";
		$scope.myData = [];
        $scope.ctrlGroup = angular.copy($scope.master);
    };

    /** start of view group **/
    $scope.viewGroupDetails = function(ctrlGroupId){
    	
		var data = {
            "controlgroupid": ctrlGroupId,
	        "controlgroupname": "",
	        "createdby": "",
	        "createdon": "",
	        "lastupdatedby": "",
	        "lastupdatedon":""
        };

    	data=JSON.stringify(data);

        var config = {
            headers : {
                'Content-Type': 'application/json'
            }
        }

    	console.log("view group data of existing json object ---- "+data);
    	
    	$http.post(myVars.site_prefix +"/control/getgrpWithGrpID/ ", data, config)
		   .then(
		       function(response){
		    	   if(response.data.message){
			        	 if(response.data.message=='NO Group Found.'){
			        		 $scope.sucessMsg="No Control Group with this name exists. Please try searching with other name.";
				          		
			        	 }
			       }else{
			    	     $scope.sucessMsg="";
			    	     $rootScope.existingGroup =  true;
			        	 $rootScope.addGroup =  response.data;
			        	 
			        	 //$location.path('/viewControlGroup');
			        	
			        	//******** get transaction details of control group
			        	 var trandata = {
			        			 "controlgroupid": ctrlGroupId,
			        		        "controlgroupname": "",
			        		        "createdby": "",
			        		        "createdon": null,
			        		        "lastupdatedby": "",
			        		        "lastupdatedon":""
	        	        };

			        	trandata=JSON.stringify(trandata);

	        	        var config = {
	        	            headers : {
	        	                'Content-Type': 'application/json'
	        	            }
	        	        }

	        	    	console.log("get transaction details of existing control group json object ---- "+trandata);
			        	$http.post(myVars.site_prefix +"/control/SearchAllControl/", trandata, config) 
					  		   .then(
					  		       function(response){
					  		    	 $scope.sucessMsg="";
					  		    	 $rootScope.existTranData = response.data;
					  		    	 $location.path('/viewControlGroup');
					  		    	
					  		       }, 
					  		       function(response){
					  		    	 $scope.errorMsg='Unable to get the transactions for this group :: Error ';
					  		         
					  		       }
					  		    );// end of get transaction details of control group 
			        	
			       }
		         
		       }, 
		       function(response){
		    	   $scope.errorMsg="Unable to view with given group name:: Error";
		        
		         //alert('Unable to view with given group name:: Error '+ response.statusText);
		       }
		    );// end of get control group details
	 
	}; 
    /** End of view group **/
    
    /** Start of Edit Group Name **/
    $scope.open = function(itemId,itemName) {
    	$rootScope.existingGroup =  false;
    	$rootScope.existAttrData = null;
    	$rootScope.existTranData = null;
    	$scope.selectedId = itemId;
    	$rootScope.selectedName = itemName;
	    var modalinstance = $uibModal.open({
	      scope: $scope,
	      templateUrl: 'editGroupModel.html',
	      resolve: {
	        myData: function() {
	          return $scope.myData;
	        }
	      }
	    });
	  
  	};

  	
  	$scope.editSave = function(updatedName){
  		//console.log("updatedName="+updatedName);
    	// use $.param jQuery function to serialize data from JSON 
        var data = {
            "controlgroupid": $scope.selectedId,
	        "controlgroupname": updatedName,
	        "createdby": "",
	        "createdon": null,
	        "lastupdatedby": "",
	        "lastupdatedon":""
        };

    	data=JSON.stringify(data);
    	
    	var config = {
            headers : {
                'Content-Type': 'application/json'
            }
        }

    	console.log("selected data is json object ---- "+data);
    	
    	$http.post(myVars.site_prefix +"/control/updateControlGrp/", data, config)
		   .then(
		       function(response){
		    	 $scope.sucessMsg='Successfully changed the group name to '+ updatedName;
		         //alert('Successfully changed the group name to '+ updatedName);
		         var editcontrolgroupname="td."+$scope.selectedId+"controlgroupname";
		 	   	 $(editcontrolgroupname).text(updatedName);
		       }, 
		       function(response){
		    	   $scope.errorMsg='Unable to change the group name:: Error ';
		         //alert('Unable to change the group name:: Error '+ response.statusText);
		       }
		    );
    };
    /** End of Edit Group Name **/


    /** Start of Delete Group  **/
    $scope.deleteGroup = function(itemId,itemName) {
    	$scope.selectedId = itemId;
    	$scope.selectedName = itemName;
    	var modalinstance = $uibModal.open({
	      scope: $scope,
	      templateUrl: 'deleteGroupModel.html'
	  	});
	    
  	};

  	$scope.delGroup = function(){
  		//$('#'+$scope.selectedId).closest('tr').remove();
  		var respDelData = {
            "controlgroupid": $scope.selectedId,
	        "controlgroupname": $scope.selectedName,
	        "createdby": "",
	        "createdon": null,
	        "lastupdatedby": "",
	        "lastupdatedon":""
        };

    	respDelData = JSON.stringify(respDelData);

    	console.log("deleteGroup JSON object --- "+respDelData);

    	var config = {
            headers : {
                'Content-Type': 'application/json'
            }
        }

    	$http.post(myVars.site_prefix +"/control/delControlGrp/", respDelData, config)
		   .then(
		       function(response){
		    	   if(response.data.message){
			        	 if(response.data.message=='Error-child record present'){
			        		 $scope.errorMsg="Transaction data is present for this control group. Deletion not possible.";
				          		//alert('Constraint data or Transaction data is present for this control group. Deletion not possible.');
			        	 }else{
				        	 $scope.sucessMsg="Deleted the group successfully";
					         //alert('Deleted the group successfully');
					         var editcontrolgroupname="td."+$scope.selectedId+"controlgroupname";
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


    /** Start of View Control Group  **/
    $scope.viewControlGroup = function() {
    	$rootScope.existingGroup =  false;
    	$rootScope.existAttrData = null;
    	$rootScope.existTranData = null;
    	var modalinstance = $uibModal.open({
	      scope: $scope,
	      templateUrl: 'createViewGroupModel.html'
	  	});
	  
  	};

  	$scope.viewGroup = function(groupName){

  		
  		
  		var viewGroupData = {
  				"controlgroupid": null,
  		        "controlgroupname": groupName,
  		        "createdby": $rootScope.globals.currentUser.username,
  		        "createdon": null,
  		        "lastupdatedby": null,
  		        "lastupdatedon": null
  	        };
  		
  		

    	viewGroupData = JSON.stringify(viewGroupData);

    	console.log("viewGroup JSON object --- "+viewGroupData);

    	var config = {
            headers : {
                'Content-Type': 'application/json'
            }
        }

    	$http.post(myVars.site_prefix +"/control/saveControlGrp/", viewGroupData, config)
		   .then(
		       function(response){
		        
		         if(response.data.message){
		        	 if(response.data.message=='Duplicate'){
		        		 $scope.sucessMsg='Please choose another name. Control Group with this name already exists in the system.';
			          		//alert('Please choose another name. Control Group with this name already exists in the system.');
		        	 }
		         }else{
		        	 $scope.sucessMsg='';
		        	 $rootScope.addGroup =  response.data;
		        	 $location.path('/viewControlGroup');
		        	 
		         }
		         
		         
		       }, 
		       function(response){
		          var msg = response.data.message;
		          //if(msg=='Duplicate'){
		         // 		alert('Please choose another name. Control Group with this name already exists in the system.');
		          //}else
		          //{
		          		//alert('Unable to create new group :: Error  '+ response.statusText);
		          $scope.errorMsg='Unable to create new group :: Error';
		          //}
		       }
		    );
  	};

  	/** End of View Control Group**/

}]);