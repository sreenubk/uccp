app.controller('viewTargetCtrl', ['$scope', '$rootScope','$uibModal', '$http',  '$location','$compile','myVars',function($scope, $rootScope,$uibModal, $http,$location,$compile,myVars) {
    $scope.msg = " I AM IN viewTarget";
    $rootScope.showNavbar = true;
    var transRow = 1;
    $scope.sucessMsg="";
	$scope.errorMsg="";
	$rootScope.selectedCohortId = null;
	$rootScope.selectedTargetCohortId = null;
	$rootScope.selectedCohTargetName = null;
	$rootScope.selectedCohtName = null;
	$rootScope.selectedCohortDesc = null;
	$scope.targetrowid = null;
	$scope.selectedTargetCohId = null;
	if($rootScope.existingTarget==true){
		$("#transTable .tranBody tr").remove();
		
	
	for(i=0; i<$rootScope.existTargetData.length; i++){
		$scope.existTargetDataLocal = $rootScope.existTargetData;
		console.log($scope.existTargetDataLocal);
		//$scope.existAttrDataLocal = $rootScope.existAttrData[i];
		//$scope.currentExistingCohortId = $rootScope.existRuleData.listCohortRules[i].cohortid;
	
		transRow=transRow+1;
	    var cohortidlocal=$rootScope.existTargetData[i].cohortid;
	    var targetIDlocal=$rootScope.existTargetData[i].targetedID;
	    var targetCohIDLocal = $rootScope.existTargetData[i].targetcohortid;
		var namelocal=$rootScope.existTargetData[i].name;
		var cohNamelocal=$rootScope.existTargetData[i].cohortName;
		var cohDesclocal=$rootScope.existTargetData[i].cohortDesc;
		var trid = "tr"+targetCohIDLocal;
		var $el = $('<tr id='+trid+'><td class="targetCohIDLocal">'+targetCohIDLocal+'</td><td class="cohortidlocal">'+cohortidlocal+'</td> <td class="namelocal">'+namelocal+'</td> <td class="cohNamelocal">'+cohNamelocal+'</td><td class="cohDesclocal">'+cohDesclocal+'</td><td> <span><i style="cursor:pointer;" title="Edit" class="fa fa-pencil-square-o" aria-hidden="true" ng-click="openCohortTargetEdit('+targetIDlocal+',\''+cohortidlocal+'\',\''+targetCohIDLocal+'\',\''+namelocal+'\',\''+cohNamelocal+'\',\''+cohDesclocal+'\')"></i> <i style="margin-right:10px;cursor:pointer;" title="Delete" class="fa fa-times" aria-hidden="true" ng-click="deleteCohTarget('+targetCohIDLocal+')"></i> </span> </td> </tr>');
		$("table#transTable tbody.tranBody").append($el);
	    $compile($el)($scope);
	    
	 
	}
	    
	};
	


    $scope.associateCohort = function() {
    	$scope.sucessMsg="";
    	$scope.errorMsg="";
    	
    	var modalinstance = $uibModal.open({
	      scope: $scope,
	      templateUrl: 'associateCohortModal.html'
	  	});
	  
  	};
  	
  	var config = {
            headers : {
                'Content-Type': 'application/json'
            }
        }
  	
    	
    	$http.post(myVars.site_prefix +"/ConfigController/listAllCohort/", null, config)
		   .then(
		       function(response){
		    	   if(response.data.message){
			        	 if(response.data.message=='NO Group Found.'){
			        		 	$scope.sucessMsg="No Cohert  with this name exists. Please try searching with other name.";
				          		
			        	 }
			        	 
			         }else{
			        	 $scope.sucessMsg="";
			        	 $scope.myCohortData = response.data;
			         }
		         
		       }, 
		       function(response){
		    	   $scope.errorMsg="Unable to search with given cohert name:: Error";
		         
		       }
		    );
	 
  	
 	//Associate Cohort with target
  	$scope.associateCohortTarget = function(cohortid, name){
  		
  		var addCohortData = {
  		       "cohortid": cohortid,
	  	        "targetedID": $rootScope.addTarget.targetedID,
	  	        "targetcohortid": null,
	  	        "name": name
	  	        };


  		addCohortData = JSON.stringify(addCohortData);

    	console.log("viewTarget JSON object --- "+addCohortData);

    	var config = {
            headers : {
                'Content-Type': 'application/json'
            }
        }

    	$http.post(myVars.site_prefix +"/ConfigController/saveCohortTarget/", addCohortData, config)
		   .then(
		       function(response){
		        
		         if(response.data.message){
		        	 if(response.data.message=='Duplicate'){
		        		 $scope.sucessMsg='Please choose another name. Cohort with this name already exists in the system.';
			          		//alert('Please choose another name. Control Group with this name already exists in the system.');
		        	 }
		         }else{
		        	 $scope.sucessMsg='';
		        	 $rootScope.addCohortTarget =  response.data;
		        	// console.log($rootScope.addCohort.name);
		        	 //$location.path('/viewCohort');
		        	 transRow=transRow+1;
	        	 	var cohortidlocal=$rootScope.addCohortTarget.cohortid;
		     	    var targetIDlocal=$rootScope.addCohortTarget.targetedID;
		     	    var targetCohIDLocal = $rootScope.addCohortTarget.targetcohortid;
		     		var namelocal=$rootScope.addCohortTarget.name;
		     		var cohNamelocal=$rootScope.addCohortTarget.cohortName;
		     		var cohDesclocal=$rootScope.addCohortTarget.cohortDesc;
				   		//console.log('<tr id="#'+cohortidlocal+'" class="'+cohortidlocal+'"><td>'+cohRulenamelocal+'</td> <td>'+cohRuleShortDesclocal+'</td> <td>'+cohRuleDesclocal+'</td><td>'+cohRuleActionlocal+'</td><td>'+cohReasonCodelocal+'</td><td>'+cohIsEnabledlocal+'</td><td>'+cohStartDatelocal+'</td><td>'+cohEndDatelocal+'</td><td> <span style="float:right"> <i style="margin-right:10px;cursor:pointer;" title="Delete" class="fa fa-times" aria-hidden="true" ng-click="delTran('+cohortidlocal+')"></i></span> </td> </tr> <tr>  </tr>');
					       
				        var $el = $('<tr id="#'+targetCohIDLocal+'" class="'+targetIDlocal+'"><td class = "'+targetCohIDLocal+'cohortidlocal">'+cohortidlocal+'</td> <td class = "'+targetCohIDLocal+'targetCohIDLocal">'+targetCohIDLocal+'</td> <td class = "'+targetCohIDLocal+'namelocal">'+namelocal+'</td><td class = "'+targetCohIDLocal+'cohNamelocal">'+cohNamelocal+'</td><td class = "'+targetCohIDLocal+'cohDesclocal">'+cohDesclocal+'</td><td> <span><i style="cursor:pointer;" title="Edit" class="fa fa-pencil-square-o" aria-hidden="true" ng-click="openCohortTargetEdit('+targetIDlocal+',\''+cohortidlocal+'\',\''+targetCohIDLocal+'\',\''+namelocal+'\',\''+cohNamelocal+'\',\''+cohDesclocal+'\')"></i> <i style="margin-right:10px;cursor:pointer;" title="Delete" class="fa fa-times" aria-hidden="true" ng-click="deleteCohTarget('+targetCohIDLocal+')"></i></span> </td> </tr> <tr>  </tr>');
				        $("table#transTable tbody.tranBody").append($el);
				        $compile($el)($scope);
		         }
		         
		         
		       }, 
		       function(response){
		          var msg = response.data.message;
		          //if(msg=='Duplicate'){
		         // 		alert('Please choose another name. Control Group with this name already exists in the system.');
		          //}else
		          //{
		          		//alert('Unable to create new group :: Error  '+ response.statusText);
		          $scope.errorMsg='Unable to create new Cohort :: Error';
		          //}
		       }
		    );
  		
  	}

    //Start of edit Cohort Target
  	$scope.openCohortTargetEdit = function(targetId, cohortId, targetCohId,name,cohName,cohDesc){
  		$scope.selectedTargetedId = targetId;
    	$rootScope.selectedCohortId = cohortId;
    	$rootScope.selectedTargetCohortId = targetCohId;
    	$rootScope.selectedCohTargetName = name;
    	$rootScope.selectedCohtName = cohName;
    	$rootScope.selectedCohortDesc = cohDesc;
    	$scope.targetrowid = targetCohId;
    	var editCohId="tr#"+$rootScope.selectedTargetCohortId+" td.cohortidlocal";
   		console.log(editCohId)
	 	   	 $(editCohId).text();
	 	   	 var editName="tr#"+$rootScope.selectedTargetCohortId+" td.namelocal";
	 	   	 console.log(editName)
	 	   	 $(editName).text();
	    var modalinstance = $uibModal.open({
	      scope: $scope,
	      templateUrl: 'editCohortTargetModel.html',
	      resolve: {
	        myData: function() {
	          return $scope.myData;
	        }
	      }
	    });
  	}
 	 
  	$scope.editCohTargetSave = function(selCohId,selName){
  		var trid = "tr"+$scope.targetrowid;
  		 var data = {
  		    "cohortid": selCohId,
  	        "targetedID": $rootScope.addTarget.targetedID,
  	        "targetcohortid": $rootScope.selectedTargetCohortId,
  	        "name": selName
  	        };

  	    	data=JSON.stringify(data);
  	    	
  	    	var config = {
  	            headers : {
  	                'Content-Type': 'application/json'
  	            }
  	        }

  	    	console.log("selected data in json object ---- "+data);
  	    	
  	    	$http.post(myVars.site_prefix +"/ConfigController/updateCohortTarget/", data, config)
  			   .then(
  			       function(response){
  			    	   $scope.myData= response.data;
  		 		         if(response.data.message){
  		 		        	 if(response.data.message=='Error-constraint Record Present'){
  		 		        		 $scope.errorMsg="Constraint data is present for this Cohort Rule. Deletion not possible.";
  		 			          		
  		 		        	 }else if(response.data.message=='Updated'){
  		 		        		var editCohId="tr#"+trid+" td.cohortidlocal";
  		 		        		console.log(editCohId)
  		 				 	   	 $(editCohId).text(selCohId);
  		 				 	   	 var editName="tr#"+trid+" td.namelocal";
  		 				 	   	 console.log(editName)
		 				 	   	 $(editName).text(selName);
  						 	   	$scope.sucessMsg='Successfully changed the Cohort Target details';
  		 				 		
  		 		        	 }
  		 		         }
  			    	  /* if(response.data.message){
  				    	   if(response.data.message=='Updated'){
  					    	 $scope.sucessMsg='Successfully changed the cohort rule name';
  					         var editcohertRulename="td."+$scope.selectedCohortId+"ruleID";
  					 	   	 $(editcohertRulename).text(updatedCohRuleName);
  				    	   }
  			    	   }else{
  			    		   $scope.sucessMsg='';
  			    	   }*/
  			       }, 
  			       function(response){
  			    	   $scope.errorMsg='Unable to change the cohort Target details:: Error ';
  			         //alert('Unable to change the group name:: Error '+ response.statusText);
  			       }
  			    );
  	}
  	
 	/** Start Cohort Target delete **/
	$scope.deleteCohTarget= function(targetCohID){
		$scope.sucessMsg="";
    	$scope.errorMsg="";
    	$scope.selectedTargetCohId = targetCohID;
    	var modalinstance = $uibModal.open({
	      scope: $scope,
	      templateUrl: 'deleteCohortTargetModal.html'
	  	});
	}
 	$scope.delCohTarget = function(){
 		$scope.sucessMsg="";
     	$scope.errorMsg="";
   		var respDelData = {
            "cohortid": "",
            "targetedID": "",
            "targetcohortid": $scope.selectedTargetCohId,
            "name": ""
         };

     	respDelData = JSON.stringify(respDelData);

     	console.log("deleteCohort Rule JSON object --- "+respDelData);

     	var config = {
             headers : {
                 'Content-Type': 'application/json'
             }
         }

     	$http.post(myVars.site_prefix +"/ConfigController/deleteCohortTarget/", respDelData, config)
 		   .then(
 		       function(response){
 		         $scope.msg = response.data;
 		         
 		         if(response.data.message){
 		        	 if(response.data.message=='Error-constraint Record Present'){
 		        		 $scope.errorMsg="Constraint data is present for this Cohort. Deletion not possible.";
 			          		
 		        	 }else if(response.data.message=='deleted'){
 		        		 var editTargetCohname="td."+$scope.selectedTargetCohId+"namelocal";
				 	   	 $(editTargetCohname).closest('tr').remove();
 		        		
 				 		$scope.sucessMsg="Associated Cohort deleted successfully";
 				 		
 		        	 }
 		         }
 		       }, 
 		       function(response){
 		    	   $scope.errorMsg="Unable to delete the Associated Cohort:: Error";
 		        
 		       }
 		    );
   	};
  

  
   

}]);