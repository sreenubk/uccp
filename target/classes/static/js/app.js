var app = angular.module('myApp', ['ui.bootstrap', 'ngRoute','ngCookies']);
app.value('myVars', {
	site_prefix:''
})
app.controller('appCtrl', ['$scope', '$rootScope','$uibModal', '$http',  '$location','myVars',function($scope, $rootScope,$uibModal, $http,$location,myVars) {
	$rootScope.showNavbar = true;
	$scope.showWcmApi = true;
	$rootScope.site_prefix = '';
$scope.getClass = function (path) {
	if(path === '/') {
        if($location.path() === '/') {
            return "activeClass";
        } else {
            return "";
        }
    }
	if ($location.path().substr(0, path.length) === path) {
        return "activeClass";
    } else {
        return "";
    }
	}

// Switch to WCM API
	$scope.switchTo = function(){
		$scope.showWcmApi = true;
		
	}
	$scope.switchToConsent = function(){
		$scope.showWcmApi = false;
	}
}]);
app.directive('autoActive', ['$location', function ($location) {
    return {
        restrict: 'A',
        scope: false,
        link: function (scope, element) {
            function setActive() {
                var path = $location.path();
                if (path) {
                    angular.forEach(element.find('li'), function (li) {
                        var anchor = li.querySelector('a');
                        	if (anchor.href.match('#' + path + '(?=\\?|$!)')) {
                         	   angular.element(li).addClass('active');
                             if(angular.element(li).parent().parent().children('a').hasClass("dd"))
                             {angular.element(li).parent().parent().children('a.dd').addClass('active');}
                        	} else {
                        	    angular.element(li).removeClass('active');
                              angular.element(li).children('a').removeClass('active');
                        	}
                    });
                }
            }

            setActive();

            scope.$on('$locationChangeSuccess', setActive);
        }
    }
}]);
app.config(['$routeProvider', function ($routeProvider) {
    $routeProvider
    .when("/login", {
        templateUrl : "login.html",
        controller : "loginCtrl"
    })
    .when("/", {
    	templateUrl : "mdmSearch.html",
        controller : "mdmSearchCtrl"
    })
    .when("/create-controlgroup", {
    	templateUrl : "createControlgroup.html",
        controller : "createControlGroupCtrl"
    })
    .when("/authorization", {
        templateUrl : "authorization.html",
        controller : "authorizationCtrl"
    })
    .when("/viewControlGroup", {
        templateUrl : "viewControlGroup.html",
        controller : "viewControlGroupCtrl"
    })
    .when("/consentCategories", {
        templateUrl : "consentCategories.html",
        controller : "consentCategoriesCtrl"
    })
    .when("/viewConsentCategory", {
        templateUrl : "viewConsentCategory.html",
        controller : "viewConsentCategoryCtrl"
    })
    .when("/personconsent", {
        templateUrl : "personconsent.html",
        controller : "personConsentCtrl"
    })
    .when("/searchconsent", {
        templateUrl : "searchConsentPage.html",
        controller : "searchConsentCtrl"
    })
    .when("/policyset", {
        templateUrl : "policyset.html",
        controller : "policySetCtrl"
    })
    .when("/viewPolicySet", {
        templateUrl : "viewPolicySet.html",
        controller : "viewPolicySetCtrl"
    })
    .when("/audit", {
        templateUrl : "audit.html",
        controller : "auditCtrl"
    })
    .when("/config", {
        templateUrl : "config.html",
        controller : "configCtrl"
    })
    .when("/personReport", {
        templateUrl : "personreport.html",
        controller : "personReportCtrl"
    })
    .when("/mdmSearch", {
        templateUrl : "mdmSearch.html",
        controller : "mdmSearchCtrl"
    })
    .when("/manualRegRequest", {
        templateUrl : "manualRegRequest.html",
        controller : "manualRegRequestCtrl"
    })
    .when("/manualAlertRequest", {
        templateUrl : "manualAlertRequest.html",
        controller : "manualAlertRequestCtrl"
    })
    .when("/selectCohort", {
        templateUrl : "selectCohort.html",
        controller : "selectCohortCtrl"
    })
    .when("/manageRegRequest", {
        templateUrl : "manageRegRequest.html",
        controller : "manageRegRequestCtrl"
    })
    .when("/manageAlertRequest", {
        templateUrl : "manageAlertRequest.html",
        controller : "manageAlertRequestCtrl"
    })
    .when("/entityDetails", {
        templateUrl : "entityDetails.html",
        controller : "entityDetailsCtrl"
    })
     .when("/createCohort", {
        templateUrl : "createCohort.html",
        controller : "createCohortCtrl"
    })
     .when("/viewCohort", {
        templateUrl : "viewCohort.html",
        controller : "viewCohortCtrl"
    })
     .when("/createTarget", {
        templateUrl : "createTarget.html",
        controller : "createTargetCtrl"
    })
     .when("/viewTarget", {
        templateUrl : "viewTarget.html",
        controller : "viewTargetCtrl"
    })
    .when("/viewReasonCode", {
        templateUrl : "viewReasonCode.html",
        controller : "viewReasonCodeCtrl"
    })
    .when("/associateCohortTarget", {
        templateUrl : "associateCohortTarget.html",
        controller : "associateCohortTargetCtrl"
    })
    .when("/entityResults", {
        templateUrl : "entityResults.html",
        controller : "entityResultsCtrl"
    })
    .when("/memberDetails", {
        templateUrl : "memberDetails.html",
        controller : "memberDetailsCtrl"
    })
    .when("/createOrganization", {
        templateUrl : "createOrganization.html",
        controller : "createOrganizationCtrl"
    })
    .when("/createRole", {
        templateUrl : "createRole.html",
        controller : "createRoleCtrl"
    })
    .when("/createUser", {
        templateUrl : "createUser.html",
        controller : "createUserCtrl"
    })
    .when("/viewOrganization", {
        templateUrl : "viewOrganization.html",
        controller : "viewOrganizationCtrl"
    })
    .when("/viewRole", {
        templateUrl : "viewRole.html",
        controller : "viewRoleCtrl"
    })
    .when("/selectCohReg", {
        templateUrl : "selectCohortRegistration.html",
        controller : "selCohRegCtrl"
    })
     .when("/viewRules", {
        templateUrl : "viewRules.html",
        controller : "viewRulesCtrl"
    })
    .when("/selectCohAlert", {
        templateUrl : "selectCohortAlert.html",
        controller : "selCohAlertCtrl"
    })
     .when("/viewPersonAlert", {
        templateUrl : "viewPersonAlert.html",
        controller : "viewPersonAlertCtrl"
    })
     .when("/newPersonDetail", {
        templateUrl : "newPersonDetail.html",
        controller : "newPersonDetailCtrl"
    })
     .when("/createJob", {
        templateUrl : "createJob.html",
        controller : "createJobCtrl"
    })
    .when("/viewJob", {
        templateUrl : "viewJob.html",
        controller : "viewJobCtrl"
    })
    .otherwise({ redirectTo: '/login' });
}]);


/*
app.run(['$route', '$rootScope', '$location', function ($route, $rootScope, $location) {
    var original = $location.path;
    $location.path = function (path, reload) {
        if (reload === false) {
            var lastRoute = $route.current;
            var un = $rootScope.$on('$locationChangeSuccess', function () {
                $route.current = lastRoute;
                un();
            });
        }
        return original.apply($location, [path]);
    };
}]);
*/
app.run(['$route', '$rootScope', '$location','$cookies','$cookieStore', '$http', function ($route, $rootScope, $location,$cookies,$cookieStore, $http) {
	$rootScope.authenticated = false;
	//$rootScope.currentUser = "";
    // keep user logged in after page refresh
        $rootScope.globals = $cookieStore.get('globals') || {};
        if ($rootScope.globals.currentUser) {
            $http.defaults.headers.common['Authorization'] = 'Basic ' + $rootScope.globals.currentUser.authdata; // jshint ignore:line
            $rootScope.authenticated = true;
          
        }

        $rootScope.$on('$', function (event, next, current) {
            // redirect to login page if notlocationChangeStart logged in
            if ($location.path() !== '/login' && !$rootScope.globals.currentUser) {
            	$rootScope.authenticated = false;
                $location.path('/login');
            }
        });
        
        $rootScope.$on('$routeChangeStart', function(event, currRoute, prevRoute){
           // var logged = Auth.isLogin();

            //check if the user is going to the login page
            // i use ui.route so not exactly sure about this one but you get the picture
            var appTo = currRoute.$$route.originalPath.indexOf('/') !== -1;

            if(appTo && $rootScope.authenticated== false) {
                //event.preventDefault();
                $location.path('/login');
            }
        });
        
        $rootScope.logout = function () {
        	$rootScope.authenticated = false;
        	$rootScope.ClearCredentials();
        	$location.path('/login');
            
        };//end of logout
        
        $rootScope.ClearCredentials = function () {
            $rootScope.globals = {};
            $cookieStore.remove('globals');
            $rootScope.currentUser = "";
            $http.defaults.headers.common.Authorization = 'Basic ';
            
        };
        
        $rootScope.createAuthorizationTokenHeader = function () {
        	var token = $scope.getJwtToken();
            if (token) {
                return {"Authorization": token};
            } else {
                return {};
            }
        };//end of createAuthorizationTokenHeader
        
        
}]);
