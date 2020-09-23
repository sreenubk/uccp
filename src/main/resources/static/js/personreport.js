app.controller('personReportCtrl', ['$scope', '$rootScope','$uibModal', '$http',  '$location','myVars', function($scope, $rootScope,$uibModal, $http,$location,myVars) {
	$rootScope.viewControlGroupView = 10;
	$rootScope.showNavbar = true;
	$scope.master = [];
	
	var config = {
	            headers : {
	                'Content-Type': 'application/json'
	            }
	        }
	$http.post(myVars.site_prefix +" /consent/listConsentPerson/", null, config)
	 .then(
	       function(response){
	    	   $rootScope.personConsentData =  response.data;
	    	  // $scope.sucessMsg="File Uploaded successfully.";
	       }, 
	       function(response){
	          //var msg = response.data.message;
	          $scope.errorMsg='Unable to load person consent data :: Error';
	          
	       }
	    );
	
	
	
}])




