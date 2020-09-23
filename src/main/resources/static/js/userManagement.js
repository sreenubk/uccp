app.controller('userManagementCtrl', ['$scope', '$rootScope','$uibModal', '$http',  '$location', '$compile','myVars',function($scope, $rootScope,$uibModal, $http, $location, $compile,myVars) {
    $scope.msg = " I AM IN viewControlGroup";
    $rootScope.showNavbar = true;
    $rootScope.viewControlGroupView = 3;
    var transRow = 1;
    var existtransRow = 1;
    $scope.sucessMsg="";
	$scope.errorMsg="";
    $scope.selectedAttrId = null;
    $scope.attrRrowid = null;
	$rootScope.selectedAttrName = null;
	$rootScope.selectedAttrValue = null;
	$rootScope.selectedaccessconstrainttype = null;
	







}]);

