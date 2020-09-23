app.controller('manualAlertRequestCtrl', ['$scope', '$rootScope','$uibModal', '$http',  '$location','myVars',function($scope, $rootScope,$uibModal, $http,$location,myVars) {
	
	$rootScope.showNavbar = true;
	
	 /* Start of  Reset Alert Form */
	 $scope.reset = function() {
		 $scope.sucessMsg="";
		$scope.errorMsg="";
		document.getElementById("regForm").reset(); 
	 };

/* End of  Reset Alert Form */
	
}]);