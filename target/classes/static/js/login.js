app.controller('loginCtrl', ['$scope', '$rootScope','$uibModal', '$http',  '$location','$cookies','$cookieStore','myVars' ,function($scope, $rootScope,$uibModal, $http,$location,$cookies,$cookieStore,myVars) {
	$rootScope.showNavbar = false;
	$rootScope.viewControlGroupView = 0;
	$scope.sucessMsg="";
	$scope.errorMsg="";
	$rootScope.authenticated = false;
	var TOKEN_KEY = "jwtToken";
	var GROUP_USERNAME = "groupUserName";
	//var site_prefix = '/connect360-web';
	// reset login status
    //AuthenticationService.ClearCredentials();
	
	/*$rootScope.getJwtToken = function () {
        return localStorage.getItem(TOKEN_KEY);
        return localStorage.getItem(GROUP_USERNAME);
    }

	$scope.setJwtToken = function (token, username) {
		localStorage.setItem(TOKEN_KEY, token);
		localStorage.setItem(GROUP_USERNAME, username);
    }

	$rootScope.removeJwtToken = function () {
		localStorage.removeItem(TOKEN_KEY);
		localStorage.removeItem(GROUP_USERNAME);
    }*/
	
    $scope.login = function (username,password) {
    	//$scope.errorMsg= ""
    	$scope.username = username;
    	$scope.password = password
    	//Adding LDAP Authentication
    	var ldapData = {
 				"username": $scope.username,
 	            "password": $scope.password
 	        };
    	
    	 ldapData = JSON.stringify(ldapData);

     	console.log("LDAP JSON object --- "+ldapData);

     	var config = {
             headers : {
                 'Content-Type': 'application/json'
             }
     	}
     	
    	$http.post(myVars.site_prefix +"/pwdcontrol/ldapAuthticate/", ldapData, config)
		   .then(
		       function(response){
		    	   $rootScope.rolePermission = response.data.rolePermission;
		    	   console.log($rootScope.rolePermission);
		    	   if(response.data.message ==="sucess"){
		    		   
		    		   var data = {
		    					"username": "User1",
		    		            "password": "Password1"
		    		        };

		    	    	data = JSON.stringify(data);

		    	    	console.log("Rest JSON object --- "+data);

		    	    	var config = {
		    	            headers : {
		    	                'Content-Type': 'application/json'
		    	            }
		    	        }
		    	    	

		    	    	$http.post(myVars.site_prefix +"/rest/", data, config)
		    			   .then(
		    			       function(response){
		    			    		   if(response.status==200){
			    			    		   console.log('in success status 200');
			    			    		   $rootScope.authenticated = true;
			    			    		   $rootScope.showNavbar = true;
			    			    		   //setJwtToken(data.token);
			    			    		  // $scope.setJwtToken("jwtToken",$scope.username);
			    			    		   $rootScope.SetCredentials("jwtToken",$scope.username);
			    			                //showTokenInformation()
			    			    		   //$scope.showUserInformation(); /// activate this
			    			    		   $location.path('/');
			    							
			    			         }else if (response.status == 401){
			    			        	 $rootScope.authenticated = false;
			    			    		   $rootScope.showNavbar = false;
			    			    		   $scope.errorMsg="Please enter correct Username/Password.";
			    			         }
		    			    	  
		    			    	  
		    			         
		    			         
		    			       }, 
		    			       function(response){
		    			    	   $scope.errorMsg="Please check the username and password.";
		    			    	   $rootScope.authenticated = false;
		    			    	   
		    			    	  // console.log('in sucess staus 200');
		    		    		   //$rootScope.authenticated = true;
		    		    		   //setJwtToken(data.token);
		    		    		  // $scope.setJwtToken("jwtToken",$scope.username);
		    		    		  // $rootScope.SetCredentials("jwtToken",$scope.username);
		    		                //showTokenInformation()
		    		    		   //$scope.showUserInformation(); /// activate this
		    		    		  // $location.path('/');
		    			      
		    			       }
		    			    );
						
		         }else if(response.data.message ==="fail"){
		        	 $scope.errorMsg = "Please enter valid credentials";
		         }
		         
		         
		       }, 
		       function(response){
		    	   $scope.errorMsg = "Please enter correct Username/Password";
		      
		       }
		    );
      

    };//end of login()
    
    
    
    
    
    $rootScope.SetCredentials = function (token, username) {
        //var authdata = Base64.encode(username + ':' + password);
    	$rootScope.currentUserName = username;
    	var authdata = token;
    	
        $rootScope.globals = {
            currentUser: {
                username: username,
                authdata: authdata
            }
        };

        $http.defaults.headers.common['Authorization'] = 'Basic ' + authdata; // jshint ignore:line
        $cookieStore.put('globals', $rootScope.globals);
    };

   
    
    
   /* 
    $scope.createAuthorizationTokenHeader = function () {
    	var token = $scope.getJwtToken();
        if (token) {
            return {"Authorization": token};
        } else {
            return {};
        }
    };//end of createAuthorizationTokenHeader
    
    $scope.showUserInformation = function () {
    	
    	var config = {
	            headers : {
	            	'Content-Type': 'application/json; charset=utf-8',
	            	'Authentication': $scope.createAuthorizationTokenHeader()
	            }
	        };
    	$http.get(myVars.site_prefix +"/user/", "", config)
		   .then(
				   function(response){
					   //SetCredentials
					   $location.path('/');
			       }, 
			       function(response){
			    	   $scope.errorMsg="Unable to send token or get user info";
			    	   $rootScope.authenticated = false;
			      
			       }
		    );
    	
	    
    };//end of showUserInformation
    
   

    
    $scope.showTokenInformation = function () {
    	$loggedIn
            .text("Token: " + getJwtToken())
            .attr("title", "Token: " + getJwtToken())
            .show();
    };//end of showTokenInformation
    
    */
    
}]);