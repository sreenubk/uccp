app.controller('apiManagerCtrl', ['$scope', '$rootScope','$uibModal', '$http',  '$location',,'myVars' ,function($scope, $rootScope,$uibModal, $http,$location,myVars) {
	$rootScope.viewApiManager = true;
	$rootScope.viewPersonHome = false;
	$rootScope.viewMdmSearch = false;
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

  


}]);