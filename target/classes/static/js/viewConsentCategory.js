app.controller('viewConsentCategoryCtrl', ['$scope', '$rootScope','$uibModal', '$http',  '$location', '$compile','myVars',function($scope, $rootScope,$uibModal, $http, $location, $compile,myVars) {
    $scope.msg = " I AM IN viewControlGroup";
    $rootScope.viewControlGroupView = 5;
    $rootScope.showNavbar = true;
	var transRow = 1;
    var existtransRow = 1;
    $scope.sucessMsg="";
	$scope.errorMsg="";
    $scope.selectedAttrId = null;
    $scope.attrRrowid = null;
	$rootScope.selectedCatAttrName = null;
	$rootScope.selectedCatAttrValue = null;
	$rootScope.selectedaccessconstrainttype = null;
	
	
	
	$rootScope.existCatTranData = $rootScope.addCategory;
	$scope.existCatTranDataLocal = $rootScope.existCatTranData;
	//$scope.existCatAttrDataLocal = $rootScope.existCatAttrData;
	//$scope.currentExistingTransactionId = $rootScope.existCatTranData.transactionid;
	
	 //Load groupnames to dropdown
    var config = {
            headers : {
                'Content-Type': 'application/json'
            }
        }

	$http.post(myVars.site_prefix +"/control/listgrp/", null, config)
	   .then(
	       function(response){
	    	   $rootScope.groupNames =  response.data;
	         
	       }, 
	       function(response){
	          var msg = response.data.message;
	          $scope.errorMsg='Unable to load group names :: Error';
	          
	       }
	    );
    
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
    
    /*** Start of Get transaction of a control group **/
    $scope.getTranData = function(ctrlGroupId){
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
	 		    	 $scope.sucessMsg="Hello search";
	 		    	 $rootScope.existTranData = response.data.transaction;
	 		    	console.log($scope.sucessMsg);
	 		    	
	 		       }, 
	 		       function(response){
	 		    	 $scope.errorMsg='Unable to get the transactions for this group :: Error ';
	 		         
	 		       }
	 		    );// end of get transaction details of control group 
    }

	/*** End of Get transaction of a control group **/
	
	if($rootScope.existingCategoryGroup==true){
		$(".tranBody tr").remove();
		
	
	for(i=0; i<$rootScope.existCatTranData.transList.length; i++){
		$scope.existCatTranDataLocal = $rootScope.existCatTranData;
		//$scope.existAttrDataLocal = $rootScope.existAttrData[i];
		$scope.currentExistingTransactionId = $rootScope.existCatTranData.transList[i].transactionid;
	
		transRow=transRow+1;
	    var transactionidlocal=$rootScope.existCatTranData.transList[i].transactionid;
	    var transactionnamelocal=$rootScope.existCatTranData.transList[i].transactionname;
		var transactiontypelocal=$rootScope.existCatTranData.transList[i].transactiontype;
		var defaultaccesslocal=$rootScope.existCatTranData.transList[i].defaultaccess;
	    var $el = $('<tr id="#'+transactionidlocal+'" class="'+transactionidlocal+'"> <td>'+transactionnamelocal+'</td> <td>'+transactiontypelocal+'</td> <td>'+defaultaccesslocal+'</td> <td> <span style="float:right"> <i style="margin-right:10px;cursor:pointer;" title="Delete" class="fa fa-times" aria-hidden="true" ng-click="delTran('+transactionidlocal+')"></i> </span> </td> </tr>');
	    $("table#transTable tbody.tranBody").append($el);
	    $compile($el)($scope);
	     
	}
	    
	};
	
	
    /*** Start of Add transaction **/
    $scope.addTransaction = function() {
    	$scope.sucessMsg="";
    	$scope.errorMsg="";
    	var modalinstance = $uibModal.open({
	      scope: $scope,
	      templateUrl: 'addConsentTransactionModal.html'
	  	});
	  
  	};

  	console.log("$rootScope.existCatAttrData"+$rootScope.existCatAttrData);
  	console.log("$rootScope.existCatTranData"+$rootScope.existCatTranData);
  	console.log("$rootScope.addCategory"+$rootScope.addCategory);

    $scope.addTransactionSave = function(tranName,tranType,accessType){
    	if($rootScope.existingCategoryGroup==true){
			var data = {
	            "transactiontype": tranType,
		        "transactionname": tranName,
		        "defaultaccess": accessType,
		        "createdby": $rootScope.globals.currentUser.username,
		        "createdon": "",
		        "lastupdatedby": "",
		        "lastupdatedon":"",
		        "versionno":"",
		        "controlgroupid":$rootScope.addCategory.consentGroup.consentgroupid
	        };
    	} else {
    		var data = {
    	            "transactiontype": tranType,
    		        "transactionname": tranName,
    		        "defaultaccess": accessType,
    		        "createdby": $rootScope.globals.currentUser.username,
    		        "createdon": "",
    		        "lastupdatedby": "",
    		        "lastupdatedon":"",
    		        "versionno":"",
    		        "controlgroupid":$rootScope.addCategory.consentgroupid
    	        };
    	}

    	data=JSON.stringify(data);

        var config = {
            headers : {
                'Content-Type': 'application/json'
            }
        }

    	console.log("add transaction for  consent group is json object ---- "+data);
    	
    	$http.post(myVars.site_prefix +"/consent/saveConsTxn/", data, config)
		   .then(
		       function(response){
		         $scope.myTransData = response.data;
		         
		         if(response.data.message){
		        	 if(response.data.message=='Fail'){
		        		 $scope.errorMsg="Unable to create new transaction:: Error";
			          		
		        	 }else
		        	 if(response.data.message=='Duplicate'){
		        		 $scope.errorMsg='This Transaction already exists.';
		        	 }
		         } else {
		        		 transRow=transRow+1;
		 		         var transactionidlocal=$scope.myTransData.transactionid;
		 		         var transactionnamelocal=$scope.myTransData.transactionname;
		 		   		 var transactiontypelocal=$scope.myTransData.transactiontype;
		 		   		 var defaultaccesslocal=$scope.myTransData.defaultaccess;
		 		         var $el = $('<tr id="#'+transactionidlocal+'" class="'+transactionidlocal+'">  <td>'+transactionnamelocal+'</td> <td>'+transactiontypelocal+'</td> <td>'+defaultaccesslocal+'</td> <td> <span style="float:right"> <i style="margin-right:10px;cursor:pointer;" title="Delete" class="fa fa-times" aria-hidden="true" ng-click="delTran('+transactionidlocal+')"></i> </span> </td> </tr> ');
		 		         $("table#transTable tbody.tranBody").append($el);
		 		         $compile($el)($scope);
			          		
		        	 }
		       }, 
		       function(response){
		         $scope.errorMsg='Unable to create new transaction:: Error';
		        
		       }
		    );
	 
	}

	/*** End of Add transaction **/
    
    
    /*** Start of Use Existing transaction **/
    $scope.useExistTransaction = function() {
    	$scope.sucessMsg="";
    	$scope.errorMsg="";
    	var modalinstance = $uibModal.open({
	      scope: $scope,
	      templateUrl: 'useConsentExistTransactionModal.html'
	  	});
	  
  	};

  	console.log("$rootScope.existCatAttrData"+$rootScope.existCatAttrData);
  	console.log("$rootScope.existCatTranData"+$rootScope.existCatTranData);
  	console.log("$rootScope.addCategory"+$rootScope.addCategory);

    $scope.useExistTransactionSave = function(ControupNames,conTranData){
    	
    	var conTranDataNew = conTranData.split(",");
		var contransactionid = conTranDataNew[0];
		var contransactionname = conTranDataNew[1];
		var contransactiontype = conTranDataNew[2];
		var condefaultaccess = conTranDataNew[3];
    	
    	if($rootScope.existingCategoryGroup==true){
			var data = {
				"transactionid": contransactionid,
				"transactiontype": null,
		        "transactionname": null,
		        "defaultaccess": null,
		        "createdby": $rootScope.globals.currentUser.username,
		        "createdon": null,
		        "lastupdatedby": null,
		        "lastupdatedon":null,
		        "versionno":null,
		        "controlgroupid":$rootScope.addCategory.consentGroup.consentgroupid
	        };
    	} else {
    		var data = {
    				"transactionid": contransactionid,
    				"transactiontype": null,
    		        "transactionname": null,
    		        "defaultaccess": null,
    		        "createdby": $rootScope.globals.currentUser.username,
    		        "createdon": null,
    		        "lastupdatedby": null,
    		        "lastupdatedon":null,
    		        "versionno":null,
    		        "controlgroupid":$rootScope.addCategory.consentgroupid
    	        };
    	}

    	data=JSON.stringify(data);

        var config = {
            headers : {
                'Content-Type': 'application/json'
            }
        }

    	console.log("add transaction for  consent group is json object ---- "+data);
    	
    	$http.post(myVars.site_prefix +"/consent/existTrans/", data, config)
		   .then(
		       function(response){
		         $scope.myTransData = response.data;
		         
		         if(response.data.message){
		        	 if(response.data.message=='fail'){
		        		 $scope.errorMsg="Unable to create new transaction:: Error";
			          		
		        	 }else 
		        	 if(response.data.message=='duplicate'){
		        		 $scope.errorMsg='This Transaction already exists.';
		        	 }else
		        		 if(response.data.message=='success'){
		        			 transRow=transRow+1;
			 		         var transactionidlocal=contransactionid;
			 		         var transactionnamelocal=contransactionname;
			 		   		 var transactiontypelocal=contransactiontype;
			 		   		 var defaultaccesslocal=condefaultaccess;
			 		         var $el = $('<tr id="#'+transactionidlocal+'" class="'+transactionidlocal+'">  <td>'+transactionnamelocal+'</td> <td>'+transactiontypelocal+'</td> <td>'+defaultaccesslocal+'</td> <td> <span style="float:right"> <i style="margin-right:10px;cursor:pointer;" title="Delete" class="fa fa-times" aria-hidden="true" ng-click="delTran('+transactionidlocal+')"></i> </span> </td> </tr> ');
			 		         $("table#transTable tbody.tranBody").append($el);
			 		         $compile($el)($scope);
			        	 }
		         } 
		       }, 
		       function(response){
		         $scope.errorMsg='Unable to create new transaction:: Error';
		        
		       }
		    );
	 
	}

	/*** End of Use Existing transaction **/

    /** Start transaction delete **/
	$scope.delTran = function(selectedtranId){
		
		$scope.sucessMsg="";
		$scope.errorMsg="";
		
  		var respDelData = {
        	"transactionid": selectedtranId,
            "transactiontype": "",
	        "transactionname": "",
	        "defaultaccess": "",
	        "createdby": "",
	        "createdon": "",
	        "lastupdatedby": "",
	        "lastupdatedon":"",
	        "versionno":""
        };

    	respDelData = JSON.stringify(respDelData);

    	console.log("deleteGroup JSON object --- "+respDelData);

    	var config = {
            headers : {
                'Content-Type': 'application/json'
            }
        }

    	$http.post(myVars.site_prefix +"/consent/deleteConsTxn/", respDelData, config)
		   .then(
		       function(response){
		         $scope.msg = response.data;
		         
		         if(response.data.message){
		        	 if(response.data.message=='Fail'){
		        		 $scope.errorMsg="Unable to delete the transaction:: Error";
			          		
		        	 }else if(response.data.message=='Deleted..'){
		        		 $scope.errorMsg="";
		        		 //$('.'+selectedtranId).closest('tr').next('tr').remove();
				 		// $('.'+selectedtranId).closest('tr').remove();
		        		 $('.'+selectedtranId).remove();
				 		$scope.sucessMsg="Transaction deleted successfully";
			          		
		        	 }
		         }else{
		        	 $scope.errorMsg="";
		        	 $('.'+selectedtranId).closest('tr').next('tr').remove();
			 		 $('.'+selectedtranId).closest('tr').remove();
			 		$scope.sucessMsg="Transaction deleted successfully";
			        
		         }
		       }, 
		       function(response){
		    	   $scope.errorMsg="Unable to delete the transaction:: Error";
		        
		       }
		    );
  	};
    /** End of transaction delete  **/


}]);

