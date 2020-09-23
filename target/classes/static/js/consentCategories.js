app.controller('consentCategoriesCtrl', ['$scope', '$rootScope','$uibModal', '$http',  '$location','myVars',function($scope, $rootScope,$uibModal, $http,$location,myVars) {
	$rootScope.viewControlGroupView = 4;
	$rootScope.showNavbar = true;
	$scope.master = [];
	//$scope.myData = [];
	$scope.selectedId = null;
	$rootScope.selectedName = null;
	$scope.sucessMsg="";
	$scope.errorMsg="";
	$rootScope.addCategory = null;
	$rootScope.existingCategoryGroup =  false;
	$rootScope.existCatAttrData = null;
	$rootScope.existCatTranData = null;

	$scope.search = function(consentGroup){
		$scope.sucessMsg="";
		$scope.errorMsg="";
		$rootScope.existingCategoryGroup =  false;
		$rootScope.existCatAttrData = null;
		$rootScope.existCatTranData = null;
		var data = {
		        "consentgroupid": null,
		        "description": "",
		        "name": consentGroup,
		        "createdby": "",
		        "createdon": "",
		        "lastupdatedby": "",
		        "lastupdatedon": ""
		    };

    	data = JSON.stringify(data);

        var config = {
            headers : {
                'Content-Type': 'application/json'
            }
        }

    	console.log("search data is json object ---- "+data);
    	
    	$http.post(myVars.site_prefix +"/consent/listgrpwithgrpName/", data, config)
		   .then(
		       function(response){
		    	   if(response.data.message){
			        	 if(response.data.message=='NO Group Found.'){
			        		 	$scope.errorMsg="No Consent Category with this name exists. Please try searching with other name.";
				          		
			        	 }
			        	 
			         }else{
			        	 $scope.sucessMsg="";
			        	 $scope.myData = response.data;
			         }
		         
		       }, 
		       function(response){
		    	   $scope.errorMsg="Unable to search with given category name:: Error";
		         
		       }
		    );
	 
	} //end of search

	$scope.reset = function() {
		$scope.sucessMsg="";
		$scope.errorMsg="";
		$scope.myData = [];
        $scope.consentGroup = angular.copy($scope.master);
    };

    /** start of view Category Details **/
    $scope.viewCategoryDetails = function(consentgroupid){
    	$scope.sucessMsg="";
    	$scope.errorMsg="";
    	var data = {
	        "consentgroupid": consentgroupid,
	        "description": "",
	        "name": "",
	        "createdby": "",
	        "createdon": "",
	        "lastupdatedby": "",
	        "lastupdatedon": ""
	    };

    	data=JSON.stringify(data);

        var config = {
            headers : {
                'Content-Type': 'application/json'
            }
        }

    	console.log("view Consent Category data of existing json object ---- "+data);
    	
    	$http.post(myVars.site_prefix +"/consent/viewConsentGrp/", data, config)
		   .then(
		       function(response){
		    	   if(response.data.message){
			        	 if(response.data.message=='NO Category Found.'){
			        		 $scope.sucessMsg="No Consent Category with this name exists. Please try searching with other name.";
				          		
			        	 }
			       }else{
			    	     $scope.sucessMsg="";
			    	     $rootScope.existingCategoryGroup =  true;
			        	 $rootScope.addCategory =  response.data;
			        	 $location.path('/viewConsentCategory');
			       }
		         
		       }, 
		       function(response){
		    	   $scope.errorMsg="Unable to view with given Consent Category name:: Error";
		        
		       }
		    );// end of get viewConsentCategory details
	 
	}; 
    /** End of view Consent Category **/
    
    /** Start of Edit Consent Category Name **/
    $scope.open = function(itemId,itemName) {
    	$scope.sucessMsg="";
    	$scope.errorMsg="";
    	$rootScope.existingCategoryGroup =  false;
    	$rootScope.existCatAttrData = null;
    	$rootScope.existCatTranData = null;
    	$scope.selectedId = itemId;
    	$rootScope.selectedName = itemName;
	    var modalinstance = $uibModal.open({
	      scope: $scope,
	      templateUrl: 'editConsentCategoryModel.html',
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
		        "consentgroupid": $scope.selectedId,
		        "description": "",
		        "name": updatedName,
		        "createdby": "",
		        "createdon": "",
		        "lastupdatedby": "",
		        "lastupdatedon": ""
		    };

    	data=JSON.stringify(data);
    	
    	var config = {
            headers : {
                'Content-Type': 'application/json'
            }
        }

    	console.log("selected data is json object ---- "+data);
    	
    	$http.post(myVars.site_prefix +"/consent/updateConGrp/", data, config)
		   .then(
		       function(response){
		    	 $scope.sucessMsg='Successfully changed the consent category name to '+ updatedName;
		         var editcategoryname="td."+$scope.selectedId+"name a";
		 	   	 $(editcategoryname).text(updatedName);
		       }, 
		       function(response){
		    	   $scope.errorMsg='Unable to change the consent category name:: Error ';
		         
		       }
		    );
    };
    /** End of Edit Consent Category Name **/


    /** Start of Delete Consent Category  **/
    $scope.deleteCategory = function(itemId,itemName) {
    	$scope.sucessMsg="";
    	$scope.errorMsg="";
    	$scope.selectedId = itemId;
    	$scope.selectedName = itemName;
    	var modalinstance = $uibModal.open({
	      scope: $scope,
	      templateUrl: 'deleteCategoryModel.html'
	  	});
	    
  	};

  	$scope.delCategory = function(){
  		//$('#'+$scope.selectedId).closest('tr').remove();
  		var respDelData = {
  			"consentgroupid": $scope.selectedId,
  			"description": "",
	        "name": $scope.selectedName,
	        "createdby": "",
	        "createdon": "",
	        "lastupdatedby": "",
	        "lastupdatedon":""
        };
  		

    	respDelData = JSON.stringify(respDelData);

    	console.log("deleteGroup for consent category JSON object --- "+respDelData);

    	var config = {
            headers : {
                'Content-Type': 'application/json'
            }
        }

    	$http.post(myVars.site_prefix +"/consent/delConGrp/", respDelData, config)
		   .then(
		       function(response){
		    	   if(response.data.message){
			        	 if(response.data.message=='child record found'){
			        		 $scope.errorMsg="Transaction data is present for this consent category. Deletion not possible.";
				          		
			        	 }else if(response.data.message=='deleted'){
			        		 $scope.sucessMsg="Deleted the consent category successfully";
					         
					         var delcategoryname="td."+$scope.selectedId+"name";
					 	   	 $(delcategoryname).closest('tr').remove();
				          		
			        	 }
			         }
		         
		       }, 
		       function(response){
		    	   $scope.errorMsg="Unable to delete the consent category :: Error";
		        
		       }
		    );
  	};
    /** End of Delete Consent Category  **/


    /** Start of New Consent Category  **/
    $scope.newConsentCategory = function() {
    	$scope.sucessMsg="";
    	$scope.errorMsg="";
    	$rootScope.existingCategoryGroup =  false;
    	$rootScope.existCatAttrData = null;
    	$rootScope.existCatTranData = null;
    	var modalinstance = $uibModal.open({
	      scope: $scope,
	      templateUrl: 'addConsentGroup.html'
	  	});
	  
  	};

  	$scope.newConsentSave = function(consentCategoryName,consentCategoryDesp){
  		var data = {
   			"consentgroupid": null,
   			"description": consentCategoryDesp,
 	        "name": consentCategoryName,
 	        "createdby": $rootScope.globals.currentUser.username,
 	        "createdon": "",
 	        "lastupdatedby": "",
 	        "lastupdatedon":""
         };

  		data = JSON.stringify(data);

    	console.log("New Consent Category JSON object --- "+data);

    	var config = {
            headers : {
                'Content-Type': 'application/json'
            }
        }

    	$http.post(myVars.site_prefix +"/consent/saveConGrp/ ", data, config)
		   .then(
		       function(response){
		        
		         if(response.data.message){
		        	 if(response.data.message=='Duplicate'){
		        		 $scope.sucessMsg='Please choose another name. Consent Category with this name already exists in the system.';
			         }else if(response.data.message=='Fail'){
		        		 $scope.errorMsg='Unable to create new category :: Error';
		        		// $location.path('/viewConsentCategory');
			         }
		         }else{
		        	 $scope.sucessMsg='';
		        	 $rootScope.addCategory =  response.data;
		        	 $location.path('/viewConsentCategory');
		        	 
		         }
		         
		         
		       }, 
		       function(response){
		         $scope.errorMsg='Unable to create new group :: Error';
		         
		       }
		    );
  	};

  	/** End of  New Consent Category**/

}]);