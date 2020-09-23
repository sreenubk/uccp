app.controller('authorizationCtrl', ['$scope', '$rootScope','$uibModal', '$http',  '$location','$window','myVars',function($scope, $rootScope,$uibModal, $http,$location,$window,myVars) {
	$rootScope.showNavbar = true;
    $scope.msg = "I AM IN Authorization";
    $rootScope.viewControlGroupView = 2;
    $scope.sucessMsg="";
	$scope.errorMsg="";
	$scope.selectedAuthId = '';
	$scope.selectedAuthName = '';
	$scope.myauthData = [];
	
	//$scope.noResults=false;
    
    ///Connect360/control/listgrp/ 
    
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
    

    	
    	$scope.search = function(groupName,jobList,orgUnit,roleList){
    		var groupname = groupName +"";
    		console.log("groupname"+groupname);
    		var joblist = jobList + "";
    		console.log("jobList"+jobList);
    		var orgunit = orgUnit +"";
    		console.log("orgunit"+orgunit);
    		var rolelist = roleList + "";
    		console.log("rolelist"+roleList);
    		
    		var authData = {
    				"authorizationgroupid": null,
    	            "cosdabaccontrolgroup": groupName,
    	            "organisationunit": orgUnit,
    	            "securityrole": roleList,
    	            "users": null,
    	            "jobid": jobList,
    	            "createdby": null,
    	            "createdon": null,
    	            "lastupdatedby": null,
    	            "lastupdatedon": null

      	        };
    		
    		authData=JSON.stringify(authData);

            var config = {
                headers : {
                    'Content-Type': 'application/json'
                }
            }

        	console.log("search authData is json object ---- "+authData);
        	
        	$http.post(myVars.site_prefix +"/auth/searchAuthGroup/", authData, config)
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

    	$scope.reset = function() {
    		//$scope.sucessMsg="";
    		//$scope.errorMsg="";
    		//$scope.myauthData = [];
    		$window.location.reload();
            $scope.ctrlGroup = angular.copy($scope.master);
        };
    
    /** Start of add auth Control Group  **/
    $scope.addAuthGroup = function() {
    	$scope.org = '';
    	$scope.role = '';
    	$scope.job = '';
    	$scope.user = '';
    	$scope.sucessMsg="";
		$scope.errorMsg="";
    	var modalinstance = $uibModal.open({
	      scope: $scope,
	      templateUrl: 'addAuthGroupModal.html'
	  	});
	  
  	};

    
  	$scope.saveAuthGroup = function(newAuthName,ctrlGroupName,orgid,role,job,user){
  		
  		for (i=0;i<$rootScope.groupNames.length;i++){
  			$scope.result = angular.equals(ctrlGroupName.controlgroupname, $rootScope.groupNames[i].controlgroupname);
  			
  			if($scope.result == true){
  				break;
  			}
  		}
  		console.log("$scope.result = "+$scope.result);
  		if($scope.result == true){
  			var authGroupData={
  					"authGroupName": newAuthName,
  					"authorizationgroupid": null,
  		  	        "cosdabaccontrolgroup":ctrlGroupName.controlgroupid,
  		  	        "organisationunit": orgid,
  		  	        "securityrole": role,
  		  	        "users": user,
  		  	        "jobid": null,
  		  	        "createdby": $rootScope.globals.currentUser.username,
  		  	        "createdon":null,
  		  	        "lastupdatedby": null,
  		  	        "lastupdatedon": null,
  		  	        "controlgroupname": null,
  		  	        "organisationname": null,
  		  	        "jobName": job,
  		  	        "jobuserName": null,
  		  	    }; 
	    	viewGroupData = JSON.stringify(authGroupData);
	
	    	console.log("viewGroup JSON object --- "+authGroupData);
	
	    	var config = {
	            headers : {
	                'Content-Type': 'application/json'
	            }
	        }
	    	
	    	
	
	    	$http.post(myVars.site_prefix +"/auth/saveAuthGroup/", authGroupData, config)
			   .then(
			       function(response){
			        
			         if(response.data.message){
			        	 if(response.data.message=='Duplicate'){
			        		 $scope.errorMsg='Authorization Group Name is already in use or CONTROLGROUPID ,ORGANIZATIONUNITID,ROLENAME,USERNAME,JOBNAME is same in other auth group ';
			        	 }
			         }else{
			        	 //$rootScope.addGroup =  response.data;
			        	// $location.path('/viewControlGroup');
			        	 $scope.sucessMsg="Successfully added new Authorization Group.";
			         }
			         
			         
			       }, 
			       function(response){
			          var msg = response.data.message;
			         $scope.errorMsg="Unable to create new group :: Error ";
			         
			       }
			    );
	    	$scope.authDone=true;
  		}//end of $scope.result == true
  		else if($scope.result == false){
  			console.log('In false');
  			$scope.authDone=false;
  			    
  				/** Start of create Control Group  **/
  		    	$scope.newcontrolgroupname =  ctrlGroupName;
  		    	var modalinstance = $uibModal.open({
  			      scope: $scope,
  			      templateUrl: 'confirmCreateGroupModal.html'
  			  	});
  		    console.log('back to auth');
  		  for (i=0;i<$rootScope.groupNames.length;i++){
    			$scope.result = angular.equals(ctrlGroupName.controlgroupname, $rootScope.groupNames[i].controlgroupname);
    			
    			if($scope.result == true){
    				break;
    			}
    		}
  		  
  		}
  	};

  	/** End of add auth Control Group**/
  	
  /*	$scope.confirmGroup = function(){
  		
	    	var modalinstance = $uibModal.open({
		      scope: $scope,
		      templateUrl: 'createViewGroupModel.html'
		  	});
  	
  	}*/
  	
  	/** Start of View Control Group - to create new control group from auth**/
  	$scope.confirmGroup = function(){
  		
  		//go to view control group and create transactions
  		
  		
		var viewGroupData = {
  				"controlgroupid": null,
  		        "controlgroupname": $scope.newcontrolgroupname,
  		        "createdby": null,
  		        "createdon": null,
  		        "lastupdatedby": null,
  		        "lastupdatedon": null
  	        };
  		
  		

    	viewGroupData = JSON.stringify(viewGroupData);

    	console.log("Auth - new control group JSON object --- "+viewGroupData);

    	var config = {
            headers : {
                'Content-Type': 'application/json'
            }
        }

    	$http.post(myVars.site_prefix +"/control/saveControlGrp/", viewGroupData, config)
		   .then(
		       function(response){
		        
		         if(response.data.message){
		        	 if(response.data.message=='Duplicate'){
		        		 alert('Please choose another name. Control Group with this name already exists in the system.');
			          
		        	 }
		         }else{
		        	 $scope.sucessMsg='';
		        	 $rootScope.addGroup =  response.data;
		        	 // $location.path('/viewControlGroup');
		        	 /*var config = {
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
		        			    	   alert('Unable to load group names :: Error  ');
		        			          
		        			       }
		        			    );*/
		        	 
		        	 
		         }//end of else
		         
		         
		       }, 
		       function(response){
		    	   alert('Unable to create new group :: Error');
		          }
		       
		    );
  	};

  	/** End of View Control Group - to create new control group from auth**/
  	
  	
  	 /** Start of Edit Auth Group Name **/

	 /** Start of Edit Auth Group Name **/
  $scope.editAuth = function(authorizationgroupid,authGroupName,controlgroupname,cosdabaccontrolgroup,jobid,organisationunitid,organisationname,securityrole,job,jobuserName) {
  	$scope.authorizationgroupid = authorizationgroupid;
  	$rootScope.authGroupNameLocal = authGroupName;
  	$rootScope.controlgroupname = controlgroupname;
  	$scope.cosdabaccontrolgroup = cosdabaccontrolgroup;
  	$scope.jobid = jobid;
  	$scope.organisationunitid = organisationunitid;
  	$rootScope.orgLocal = organisationname+','+organisationunitid;
  	$rootScope.roleLocal = securityrole;
  	$rootScope.jobLocal = job;
  	console.log($rootScope.jobLocal);
  	$rootScope.userLocal = jobuserName;
	    var modalinstance = $uibModal.open({
	      scope: $scope,
	      templateUrl: 'editAuthModal.html',
	      resolve: {
	    	  myauthData: function() {
	          return $scope.myauthData;
	        }
	      }
	    });
	  
	};


	
	$scope.editSave = function(ctrlGroupName,updatedorganisation,updatedsecurityrole,updatedjob,updatedjobuserName){
		//console.log("updatedName="+updatedName);
  	// use $.param jQuery function to serialize data from JSON 
		var updatedorganisation = updatedorganisation.split(",");
		var updatedorganisationunitid = updatedorganisation[1];
		var updatedorganisationname =  updatedorganisation[0];
		var updatedjob = updatedjob;
		//var updatedjobname = updatedjob[0];
		//var updatedjobid = updatedjob[1];
		var authGroupData = {
	  	        "authorizationgroupid": $scope.authorizationgroupid,
	  	        "cosdabaccontrolgroup": $scope.cosdabaccontrolgroup,
	  	        "organisationunit": updatedorganisationunitid,
	  	        "securityrole": updatedsecurityrole,
	  	        "users": updatedjobuserName,
	  	        "jobid": $scope.jobid,
	  	       "jobName": updatedjob,
	  	      "createdby": null,
	          "createdon": null,
	          "lastupdatedby": null,
	          "lastupdatedon": null
	  	    };
		
		
		
      authGroupData=JSON.stringify(authGroupData);
  	
  	var config = {
          headers : {
              'Content-Type': 'application/json'
          }
      }

  	console.log("selected data is json object ---- "+authGroupData);

  	$http.post(myVars.site_prefix +"/auth/updateAuthGroup/", authGroupData, config)
		   .then(
		       function(response){
		    	
		         //alert('Successfully changed the group name to '+ updatedName);
		        /* var editorganisationname="td#"+$scope.authorizationgroupid+"organisationname";
		         var editsecurityrole="td#"+$scope.authorizationgroupid+"securityrole";
		         var editjob="td#"+$scope.authorizationgroupid+"job";
		         var editjobuserName="td#"+$scope.authorizationgroupid+"jobuserName .usrname";
		 	   	 $(editorganisationname).text(updatedorganisationname);
		 	   	 $(editsecurityrole).text(updatedsecurityrole);
		 	     $(editjob).text(updatedjobname);
		 	     $(editjobuserName).text(updatedjobuserName);*/
		 	     
		 	    $http.post(myVars.site_prefix +"/auth/searchAuthGroup/", authGroupData, config)
	    		   .then(
	    		       function(response){
	    		    	   if(response.data!=''){
	    		    		   $scope.sucessMsg='Successfully updated Auth group info';
	    			        	 $scope.myauthData = response.data;
					          		
				        	 }
	    		         
	    		       }, 
	    		       function(response){
	    		    	   $scope.errorMsg='Unable to change the group name:: Error ';
	    		         
	    		       }
	    		    );
		 	     
		 	     
		 	     
		       }, 
		       function(response){
		    	   $scope.errorMsg='Unable to change the group name:: Error ';
		         //alert('Unable to change the group name:: Error '+ response.statusText);
		       }
		    );
  };
    /** End of Edit Auth Group Name **/
  	
    /** Start of Delete Auth  **/
    $scope.deleteAuth = function(itemId,itemName,cosdabaccontrolgroup) {
    	$scope.selectedAuthId = itemId;
    	$scope.selectedAuthName = itemName;
    	$scope.cosdabaccontrolgroup = cosdabaccontrolgroup;
    	var modalinstance = $uibModal.open({
	      scope: $scope,
	      templateUrl: 'deleteAuthModel.html'
	  	});
	    
  	};

  	$scope.delAuth = function(){
  		$('#'+$scope.selectedAuthId+'controlgroupname').closest('tr').remove();
  		
  		var respDelData = {
				"authorizationgroupid": $scope.selectedAuthId,
	            "cosdabaccontrolgroup": $scope.cosdabaccontrolgroup,
	            "organisationunit": null,
	            "securityrole": null,
	            "users": null,
	            "jobid": null,
	            "createdby": null,
	            "createdon": null,
	            "lastupdatedby": null,
	            "lastupdatedon": null

  	        };

    	respDelData = JSON.stringify(respDelData);

    	console.log("deleteGroup JSON object --- "+respDelData);

    	var config = {
            headers : {
                'Content-Type': 'application/json'
            }
        }

    	$http.post(myVars.site_prefix +"/auth/deleteAuthGroup/", respDelData, config)
		   .then(
		       function(response){
		    	   if(response.data.message){
			        	 if(response.data.message=='Constraint data or Transaction data Present So No deletion'){
			        		 $scope.sucessMsg="Constraint data or Transaction data is present for this control group. Deletion not possible.";
				          		
			        	 }else{
				        	 $scope.sucessMsg="Deleted the group " +$scope.selectedAuthId+ " successfully";
					         var editcontrolgroupname="td."+$scope.selectedId+"controlgroupname";
					 	   	 $(editcontrolgroupname).closest('tr').remove();
				         }
			         }
		         
		         
		       }, 
		       function(response){
		    	   $scope.errorMsg="Unable to delete the group :: Error";
		      
		       }
		    );
  	};
    /** End of Delete Auth **/
    
}]);