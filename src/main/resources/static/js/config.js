app.controller('configCtrl', ['$scope', '$rootScope','$uibModal', '$http',  '$location','myVars', function($scope, $rootScope,$uibModal, $http,$location,myVars) {
	$rootScope.viewControlGroupView = 10;
	$rootScope.showNavbar = true;
	$scope.master = [];
	
	var config = {
            headers : {
                'Content-Type': 'application/json'
            }
        }
	
	$http.get(myVars.site_prefix +"/auth/getListOrg/")
	   .then(
	       function(response){
	    	   $rootScope.orgUnits =  response.data;
	         
	       }, 
	       function(response){
	          var msg = response.data.message;
	          $scope.errorMsg='Unable to load orgganisation units :: Error';
	          
	       }
	    );
	
	$http.get(myVars.site_prefix +"/UserMgmt/getJobList/")
	   .then(
	       function(response){
	    	   $rootScope.jobLists =  response.data;
	         
	       }, 
	       function(response){
	          var msg = response.data.message;
	          $scope.errorMsg='Unable to load role list :: Error ';
	          
	       }
	    );
	
	$http.get(myVars.site_prefix +"/auth/getSRoleList/")
	   .then(
	       function(response){
	    	   $rootScope.roleLists =  response.data;
	         
	       }, 
	       function(response){
	          var msg = response.data.message;
	          $scope.errorMsg='Unable to load role list :: Error  ';
	          
	       }
	    );
	$http.get(myVars.site_prefix +"/auth/getUserList/")
	   .then(
	       function(response){
	    	   $rootScope.userLists =  response.data;
	         
	       }, 
	       function(response){
	          var msg = response.data.message;
	          $scope.errorMsg='Unable to load user list :: Error  ';
	          
	       }
	    );

	

	
	$scope.getResult = function(srcSystem,jobList,orgUnit,roleList){
		var srcSystem = srcSystem;
		console.log("srcSystem"+srcSystem);
		var joblist = jobList;
		console.log("jobList"+jobList);
		var orgunit = orgUnit;
		console.log("orgunit"+orgunit);
		var rolelist = roleList;
		console.log("rolelist"+roleList);
		
		var authData = {
	           "sourceSys":srcSystem,
	 		   "role":roleList,
	 		   "org":orgUnit,
	 		   "job": jobList,


  	        };
		
		authData=JSON.stringify(authData);

        var config = {
            headers : {
                'Content-Type': 'application/json'
            }
        }

    	console.log("search authData is json object ---- "+authData);
    	
    	$http.post(myVars.site_prefix +"/pwdcontrol/getConsentResult/", authData, config)
		   .then(
				   function(response){
    		    	   if(response.data.message){
    			        	 if(response.data.message=='NO Auth Group Found.'){
    			        		 console.log("not found");
 			        		 	$scope.sucessMsg="No Control Group with this name exists. Please try searching with other name.";
 			        		 	$scope.errorMsg = "";
    			        	 }
    			        	 
    			         }else if(response.data!=''){
    			        	 $scope.sucessMsg="";
    			        	 $scope.myauthData = response.data;
    			        	 console.log("public"+$scope.myauthData["PUBLIC ASSISTANCE PROGRAMS"]);
    			        	 //alert(test["No. of interfaces"]);

    			        	 $scope.errorMsg = "";
				          		
			        	 }else if(response.data ===''){
			        		 $scope.sucessMsg="";
			        		 $scope.myauthData = [];
			        		 console.log("not found anywhere");
			        		 $scope.errorMsg="No Control Group with this name exists. Please try searching with other name.";
			        		 $scope.ctrlGroup = angular.copy($scope.master);
			        	 }
    		         
    		       }, 
    		       function(response){
    		    	   $scope.errorMsg="Unable to search with given group name:: Error";
    		         
    		       }
    		  );
	 
	} //end of search
	
	
	
	
	//Method to show Selected Transaction
	var selectedTransValue;
	var getAttrUsingTrans;
	 $scope.selectedTransaction= function (value) {  
        console.log(value);
        selectedTransValue = value;
        console.log(selectedTransValue);
        getAttrUsingTrans ="/control/listAllConstraintWithTxn/" +selectedTransValue + "/";
    	console.log(getAttrUsingTrans);
    	$http.get(getAttrUsingTrans, null, config)
 	   .then(
 	       function(response){
 	    	   $rootScope.existAttrDataNew =  response.data;
 	         
 	       }, 
 	       function(response){
 	          var msg = response.data.message;
 	          $scope.errorMsg='Unable to load transaction data :: Error';
 	          
 	       }
 	    );
    };
    
    //Show Modal on click of Sample Input Format
    $scope.showModal = false;
    $scope.buttonClicked = "";
    $scope.toggleModal = function(btnClicked){
        $scope.buttonClicked = btnClicked;
        $scope.showModal = !$scope.showModal;
    };
	    
	    
	
	
	
	$http.post(myVars.site_prefix +"/control/listAlltxn/", null, config)
	   .then(
	       function(response){
	    	   $rootScope.existTranDataNew =  response.data;
	         
	       }, 
	       function(response){
	          var msg = response.data.message;
	          $scope.errorMsg='Unable to load transaction data :: Error';
	          
	       }
	    );
	$scope.reset = function() {
		$scope.sucessMsg="";
		$scope.errorMsg="";
		$scope.userName="";
		$scope.sourceSystem="";
	
        $scope.ctrlGroup = angular.copy($scope.master);
    };
    //$scope.model = {};
    //$scope.jsonData = $scope.model.data;
   // console.log("jsonData"+$scope.jsonData);
	$scope.search = function(data){
		console.log("jsonData"+data);
		var sdata = {	
	      	    "userName": (data.userName==undefined)?null:data.userName,
	      	    "clientID": (data.clientid==undefined)?null:data.clientid,
	    	    "srcSystem": (data.sourceSystem==undefined)?null:data.sourceSystem,
	      	    "transactionid": null,
	      	    "transName": (conTranData==undefined)?null:conTranData,
	      	    "constraintid": null,
	      	    "constraintName": null,
	      	    "constraintValue": null,
	      	    "privilegeRequested": null,
	      	    "accessReason": null,
	      	    "apiSwith": (data.apiSwitch==undefined)?null:data.apiSwitch,
	      	    "reason":"Config Tests"
	      	};

    	sdata=JSON.stringify(data);
    	console.log("string"+sdata);
        var config = {
            headers : {
                'Content-Type': 'application/json'
            }
        }

    	console.log("search data is json object ---- "+data);
    	
    	$http.post(myVars.site_prefix +"/authRes/getAuthRespData/", data, config)
		   .then(
		       function(response){
		    	   if(response.data.message){
			        	 if(response.data.message=='NO Group Found.'){
			        		 	$scope.sucessMsg="This configuration search data doesn't exists. Please try searching again.";
			        		 	 console.log($scope.sucessMsg);
			        	 }
			        	 
			         }else{
			        	 $scope.sucessMsg="";
			        	 $scope.result = response.data.response;
			        	 console.log($scope.result);
			         }
		         
		       }, 
		       function(response){
		    	   $scope.errorMsg="Unable to search with given group name:: Error";
		         
		       }
		    );
	 
	} //end of search
	
}])
app.directive('modal', function () {
	        return {
	          template: '<div class="modal fade">' + 
	              '<div class="modal-dialog">' + 
	                '<div class="modal-content">' + 
	                  '<div class="modal-header">' + 
	                    '<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>' + 
	                    '<h4 class="modal-title">Sample Input Format</h4>' + 
	                  '</div>' + 
	                  '<div class="modal-body" style="height:400px;" ng-transclude></div>' + 
	                '</div>' + 
	              '</div>' + 
	            '</div>',
	          restrict: 'E',
	          transclude: true,
	          replace:true,
	          scope:true,
	          link: function postLink(scope, element, attrs) {
	              scope.$watch(attrs.visible, function(value){
	              if(value == true)
	                $(element).modal('show');
	              else
	                $(element).modal('hide');
	            });

	            $(element).on('shown.bs.modal', function(){
	              scope.$apply(function(){
	                scope.$parent[attrs.visible] = true;
	              });
	            });

	            $(element).on('hidden.bs.modal', function(){
	              scope.$apply(function(){
	                scope.$parent[attrs.visible] = false;
	              });
	            });
	          }
	        };
	      });




