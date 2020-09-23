app.controller('viewPolicySetCtrl', ['$scope', '$rootScope','$uibModal', '$http',  '$location', '$compile','myVars',function($scope, $rootScope,$uibModal, $http, $location, $compile,myVars) {
    $scope.msg = " I AM IN viewControlGroup";
    $rootScope.viewControlGroupView = 8;
    $rootScope.showNavbar = true;
    var transRow = 1;
    var existtransRow = 1;
    $scope.sucessMsg="";
	$scope.errorMsg="";
    $scope.selectedAttrId = null;
    $scope.attrRrowid = null;
	$rootScope.selectedAttrName = null;
	$rootScope.selectedAttrValue = null;
	$rootScope.selectedaccessconstrainttype = null;
	
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
    	$http.get(myVars.site_prefix +"/auth/getJobList/")
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
    	
    	
    	$http.post(myVars.site_prefix +"/auth/getListAuthGroup/", null, config)
		   .then(
		       function(response){
		    	   $rootScope.authGroupLists =  response.data;
		         
		       }, 
		       function(response){
		          var msg = response.data.message;
		          $scope.errorMsg='Unable to load auth group list :: Error  ';
		          
		       }
		    );
    	
    if($rootScope.existingGroup==false){
    	$scope.policySetID = $rootScope.addPolicy.policysetid;
    }
    	
	if($rootScope.existingGroup==true){
		$("#authTable .authBody tr").remove();
		
		$scope.policySetID = $rootScope.addPolicy.policyset.policysetid;
		
	
	for(i=0; i<$rootScope.addPolicy.authGroups.length; i++){
		//$scope.existTranDataLocal = $rootScope.addPolicy;
		//$scope.existAttrDataLocal = $rootScope.existAttrData[i];
		//$scope.currentExistingTransactionId = $rootScope.addPolicy.authGroups[i].authorizationgroupid;
		
		$scope.myauthData = $rootScope.addPolicy.authGroups;
		
		var authorizationgroupidlocal=$scope.myauthData[i].authorizationgroupid;
		var authGroupNamelocal=($scope.myauthData[i].authGroupName==null)?"":$scope.myauthData[i].authGroupName;
        var controlgroupnamelocal=($scope.myauthData[i].controlgroupname==null)?"":$scope.myauthData[i].controlgroupname;
   		var cosdabaccontrolgrouplocal=($scope.myauthData[i].cosdabaccontrolgroup==null)?"":$scope.myauthData[i].cosdabaccontrolgroup;
   		var jobidlocal=$scope.myauthData[i].jobid;
   		var organisationunitlocal=($scope.myauthData[i].organisationunit==null)?"":$scope.myauthData[i].organisationunit;
   		var organisationnamelocal=($scope.myauthData[i].organisationname==null)?"":$scope.myauthData[i].organisationname;
   		var securityrolelocal=($scope.myauthData[i].securityrole==null)?"":$scope.myauthData[i].securityrole;
   		//var joblocal=($scope.myauthData[i].job==null)?"":$scope.myauthData[i].job;
   		var jobuserNamelocal=($scope.myauthData[i].users==null)?"":$scope.myauthData[i].users;
   		var jobNamelocal=($scope.myauthData[i].jobName == null)?"":$scope.myauthData[i].jobName;
   		
   		
   		
   		
        var $el = $('<tr id="#'+authorizationgroupidlocal+'" class="'+authorizationgroupidlocal+'"><td id="'+authorizationgroupidlocal+'authGroupName" data-title="Authorization Group Name">'+authGroupNamelocal+'</td><td id="'+authorizationgroupidlocal+'controlgroupname" data-title="Control Group Name">'+controlgroupnamelocal+'</td><td id="'+authorizationgroupidlocal+'organisationname" data-title="Organisation name">'+organisationnamelocal+'</td><td id="'+authorizationgroupidlocal+'securityrole" data-title="Security role">'+securityrolelocal+'</td><td id="'+authorizationgroupidlocal+'jobName" data-title="Job Name">'+jobNamelocal+'</td><td id="'+authorizationgroupidlocal+'jobuserName" data-title="job user Name"><span class="usrname">'+jobuserNamelocal+'</span></td><td><span><i style="margin-right:10px;cursor:pointer;"  title="Edit" class="fa fa-pencil-square-o" aria-hidden="true" ng-click="editAuth(\''+authGroupNamelocal+'\','+authorizationgroupidlocal+',\''+controlgroupnamelocal+'\','+cosdabaccontrolgrouplocal+','+jobidlocal+','+organisationunitlocal+',\''+organisationnamelocal+'\',\''+securityrolelocal+'\',\''+jobNamelocal+'\',\''+jobuserNamelocal+'\')"></i> <i  style="cursor:pointer;" title="Delete" id="'+authorizationgroupidlocal+'" class="fa fa-times" aria-hidden="true" ng-click="deleteAuth('+authorizationgroupidlocal+',\''+controlgroupnamelocal+'\','+cosdabaccontrolgrouplocal+','+jobidlocal+','+organisationunitlocal+')"></i></span></td></tr>');
        $("table#authTable tbody.authBody").append($el);
        $compile($el)($scope);
	
		
	}
	    
	};
	
    /*** Start of Add Authorization **/
    $scope.addAuthorisation = function() {
    	$scope.org = '';
    	$scope.role = '';
    	$scope.job = '';
    	$scope.user = '';
    	$scope.sucessMsg='';
    	$scope.errorMsg='';
    	var modalinstance = $uibModal.open({
	      scope: $scope,
	      templateUrl: 'addAuthPolicyGroupModal.html'
	  	});
	  
  	};
  	
  	$scope.newAuthNameCheck = function() {
  		$(".existAuthNameFrm").addClass('hide');
    	$(".newAuthNameFrm").removeClass('hide');
    	
    	$("#ctrlGroupName").removeAttr("disabled");
  		$("#org").removeAttr("disabled");
  		$("#role").removeAttr("disabled");
  		$("#job").removeAttr("disabled");
  		$("#user").removeAttr("disabled");
  		
  		$scope.existAuthName = null;
  	};
  	
  	$scope.existAuthNameCheck = function() {
    	
    	$(".newAuthNameFrm").addClass('hide');
  		$(".existAuthNameFrm").removeClass('hide');
  		
  		$("#ctrlGroupName").attr("disabled", "disabled");
  		$("#org").attr("disabled", "disabled");
  		$("#role").attr("disabled", "disabled");
  		$("#job").attr("disabled", "disabled");
  		$("#user").attr("disabled", "disabled");
  		
  		ctrlGroupName,org,role,job,user
  		
  		$scope.ctrlGroupName = null;
  		$scope.org = null;
  		$scope.role = null;
  		$scope.job = null;
  		$scope.user = null;
  		$scope.newAuthName = null;
  		
  		//$scope.result=true;
  		
  	};

  //	console.log("$rootScope.existAttrData"+$rootScope.existAttrData);
 // 	console.log("$rootScope.existTranData"+$rootScope.existTranData);
  	//console.log("$rootScope.addGroup"+$rootScope.addGroup);

  	$scope.saveAuthGroup = function(newAuthName,existAuthName,ctrlGroupName,orgid,role,job,user){
  		//{{x.authorizationgroupid}},{{x.authGroupName}},{{x.controlgroupname}},{{x.cosdabaccontrolgroup}},{{x.jobid}},{{x.organisationunit}},{{x.organisationname}},{{x.securityrole}},{{x.job}},{{x.users}}
  		$scope.errorMsg='';
  		if(ctrlGroupName!=null){
	  		for (i=0;i<$rootScope.groupNames.length;i++){
	  			$scope.result = angular.equals(ctrlGroupName.controlgroupname, $rootScope.groupNames[i].controlgroupname);
	  			
	  			if($scope.result == true){
	  				break;
	  			}
	  		}
  		}
  		console.log("$scope.result = "+$scope.result);
  		
  		if(existAuthName != null){
  			var existAuthNamedata = existAuthName.split(",");
  	 		var authorizationgroupidData = existAuthNamedata[0];
  	 		
  			 var saveAuthGroupPolicyData = {
		        		"authGroupId": authorizationgroupidData,
		        		"policySetID": $scope.policySetID
			  	       
			  	    }; 
		        saveAuthGroupPolicyData = JSON.stringify(saveAuthGroupPolicyData);
			
			    	console.log("Add Authorisation saveAuthGroupPolicyData JSON object --- "+saveAuthGroupPolicyData);
			
			    	var config = {
			            headers : {
			                'Content-Type': 'application/json'
			            }
			        }
			    	
			    	$http.post(myVars.site_prefix +"/auth/saveAuthGroupPolicy/", saveAuthGroupPolicyData, config)
					   .then(
					       function(response){
					    	   if(response.data.message=="This Auth Grp is already in other PolicySet"){
					    		   $scope.errorMsg='This Auth Grp is already in other PolicySet';
					    	   }else{
					    		   $scope.errorMsg='';					    		   //$scope.myauthData = response.data;
					    		   $scope.myauthData = $rootScope.authGroupLists;
							        
					    		   //editAuth(189,'GRPCON1',275,null,1,'Org1','null','null','User4')
							       // authRow=authRow+1;
					    		 //{{x.authorizationgroupid}},{{x.authGroupName}},{{x.controlgroupname}},{{x.cosdabaccontrolgroup}},{{x.jobid}},{{x.organisationunit}},{{x.organisationname}},{{x.securityrole}},{{x.job}},{{x.users}}
					    		   var authorizationgroupidlocal = existAuthNamedata[0];
							       // var authorizationgroupidlocal=$scope.myauthData.authorizationgroupid;
					    		   var authGroupNamelocal = (existAuthNamedata[1]==null)?null:existAuthNamedata[1];
							       // var authGroupNamelocal=($scope.myauthData.authGroupName==null)?"":$scope.myauthData.authGroupName;
					    		   var controlgroupnamelocal = (existAuthNamedata[2]==null)?"":existAuthNamedata[2];
							        //var controlgroupnamelocal=($scope.myauthData.controlgroupname==null)?"":$scope.myauthData.controlgroupname;
							        var cosdabaccontrolgrouplocal = (existAuthNamedata[3]==null)?null:existAuthNamedata[3];
							   		//var cosdabaccontrolgrouplocal=$scope.myauthData.cosdabaccontrolgroup;
							        var jobidlocal = (existAuthNamedata[4]=="")?null:existAuthNamedata[4];
							   		//var jobidlocal=$scope.myauthData.jobid;
							   		var organisationunitlocal =(existAuthNamedata[5]==null)?0:existAuthNamedata[5];
							   		//var organisationunitlocal=($scope.myauthData.organisationunit==null)?"":$scope.myauthData.organisationunit;
							   		var organisationnamelocal = (existAuthNamedata[6]==null)?"":existAuthNamedata[6];
							   		//var organisationnamelocal=($scope.myauthData.organisationname==null)?"":$scope.myauthData.organisationname;
							   		var securityrolelocal = (existAuthNamedata[7]==null)?"":existAuthNamedata[7];
							   		//var securityrolelocal=($scope.myauthData.securityrole==null)?"":$scope.myauthData.securityrole;
							   		var joblocal = (existAuthNamedata[8]==null)?"":existAuthNamedata[8];
							   		//var joblocal=($scope.myauthData.job==null)?"":$scope.myauthData.job;
							   		var jobuserNamelocal = (existAuthNamedata[9]==null)?"":existAuthNamedata[9];
							   		//var jobuserNamelocal=($scope.myauthData.users==null)?"":$scope.myauthData.users;
							        var $el = $('<tr id="#'+authorizationgroupidlocal+'" class="'+authorizationgroupidlocal+'"><td id="'+authorizationgroupidlocal+'authGroupName" data-title="Authorization Group Name">'+authGroupNamelocal+'</td><td id="'+authorizationgroupidlocal+'controlgroupname" data-title="Control Group Name">'+controlgroupnamelocal+'</td><td id="'+authorizationgroupidlocal+'organisationname" data-title="Organisation name">'+organisationnamelocal+'</td><td id="'+authorizationgroupidlocal+'securityrole" data-title="Security role">'+securityrolelocal+'</td><td id="'+authorizationgroupidlocal+'job" data-title="job">'+joblocal+'</td><td id="'+authorizationgroupidlocal+'jobuserName" data-title="job user Name"><span class="usrname">'+jobuserNamelocal+'</span></td><td><span><i style="margin-right:10px;cursor:pointer;"  title="Edit" class="fa fa-pencil-square-o" aria-hidden="true" ng-click="editAuth(\''+authGroupNamelocal+'\','+authorizationgroupidlocal+',\''+controlgroupnamelocal+'\','+cosdabaccontrolgrouplocal+','+jobidlocal+','+organisationunitlocal+',\''+organisationnamelocal+'\',\''+securityrolelocal+'\',\''+joblocal+'\',\''+jobuserNamelocal+'\')"></i> <i  style="cursor:pointer;" title="Delete" id="'+authorizationgroupidlocal+'" class="fa fa-times" aria-hidden="true" ng-click="deleteAuth('+authorizationgroupidlocal+',\''+controlgroupnamelocal+'\','+cosdabaccontrolgrouplocal+','+jobidlocal+','+organisationunitlocal+')"></i></span></td></tr>');
							        $("table#authTable tbody.authBody").append($el);
							        $compile($el)($scope);
		       
							      	
							        
					    	   }
					    	
					         
					       }, 
					       function(response){
					          // :: Error';
					    	   $scope.errorMsg='Unable to create new auth group:: Error';
					       }
					    );
			    	$scope.authDone=true;
  		}
  	
  		if($scope.result == true){
	  		var authGroupData={
	  					"authGroupName": (newAuthName=="")? existAuthName:newAuthName,
	  					"authorizationgroupid": null,
			  	        "cosdabaccontrolgroup":(ctrlGroupName!=null)?ctrlGroupName.controlgroupid:null,
			  	        "organisationunit": orgid,
			  	        "securityrole": role,
			  	        "users": user,
			  	        "jobid": job,
			  	        "createdby": $rootScope.globals.currentUser.username,
			  	        "createdon":null,
			  	        "lastupdatedby": null,
			  	        "lastupdatedon": null,
			  	        "controlgroupname": null,
			  	        "organisationname": null,
			  	        "job": null,
			  	        "jobuserName": null,
			  	    }; 
	  		authGroupData = JSON.stringify(authGroupData);
	
	    	console.log("Add Authorisation JSON object --- "+authGroupData);
	
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
				        		 $scope.errorMsg='Auth Group Name is already in use or CONTROLGROUPID ,ORGANIZATIONUNITID,ROLENAME,USERNAME is same in other auth group ';
				        	 }
				         }else{
				        	 
				        	 $scope.myauthData = response.data;
				        	 
				        	 var saveAuthGroupPolicyData = {
						        		"authGroupId": response.data.authorizationgroupid,
						        		"policySetID": $scope.policySetID
							  	       
							  	    }; 
						        saveAuthGroupPolicyData = JSON.stringify(saveAuthGroupPolicyData);
							
							    	console.log("Add Authorisation saveAuthGroupPolicyData JSON object --- "+saveAuthGroupPolicyData);
							
							    	var config = {
							            headers : {
							                'Content-Type': 'application/json'
							            }
							        }
							    	
							    	$http.post(myVars.site_prefix +"/auth/saveAuthGroupPolicy/", saveAuthGroupPolicyData, config)
									   .then(
									       function(response){
									    	   if(response.data.message=="This Auth Grp is already in other PolicySet"){
									    		   $scope.errorMsg='This Auth Grp is already in other PolicySet';
									    	   }else{
									    		   $scope.errorMsg='';					    		  
									    		   //$scope.myauthData = response.data;
									    		   
									    		   $scope.sucessMsg="Successfully added new Authorization Group.";
									    		   
									    		   //$scope.myauthData = $rootScope.authGroupLists;
											        
									    		  
											       // authRow=authRow+1;
									    		   var authorizationgroupidlocal=$scope.myauthData.authorizationgroupid;
									    		   var authGroupNamelocal=($scope.myauthData.authGroupName==null)?"":$scope.myauthData.authGroupName;
											        var controlgroupnamelocal=($scope.myauthData.controlgroupname==null)?"":$scope.myauthData.controlgroupname;
											   		var cosdabaccontrolgrouplocal=$scope.myauthData.cosdabaccontrolgroup;
											   		var jobidlocal=$scope.myauthData.jobid;
											   		var organisationunitlocal=($scope.myauthData.organisationunit==null)?"":$scope.myauthData.organisationunit;
											   		var organisationnamelocal=($scope.myauthData.organisationname==null)?"":$scope.myauthData.organisationname;
											   		var securityrolelocal=($scope.myauthData.securityrole==null)?"":$scope.myauthData.securityrole;
											   		var joblocal=($scope.myauthData.job==null)?"":$scope.myauthData.job;
											   		var jobuserNamelocal=($scope.myauthData.users==null)?"":$scope.myauthData.users;
											        var $el = $('<tr id="#'+authorizationgroupidlocal+'" class="'+authorizationgroupidlocal+'"><td id="'+authorizationgroupidlocal+'authGroupName" data-title="Authorization Group Name">'+authGroupNamelocal+'</td><td id="'+authorizationgroupidlocal+'controlgroupname" data-title="Control Group Name">'+controlgroupnamelocal+'</td><td id="'+authorizationgroupidlocal+'organisationname" data-title="Organisation name">'+organisationnamelocal+'</td><td id="'+authorizationgroupidlocal+'securityrole" data-title="Security role">'+securityrolelocal+'</td><td id="'+authorizationgroupidlocal+'job" data-title="job">'+joblocal+'</td><td id="'+authorizationgroupidlocal+'jobuserName" data-title="job user Name"><span class="usrname">'+jobuserNamelocal+'</span></td><td><span><i style="margin-right:10px;cursor:pointer;"  title="Edit" class="fa fa-pencil-square-o" aria-hidden="true" ng-click="editAuth(\''+authGroupNamelocal+'\','+authorizationgroupidlocal+',\''+controlgroupnamelocal+'\','+cosdabaccontrolgrouplocal+','+jobidlocal+','+organisationunitlocal+',\''+organisationnamelocal+'\',\''+securityrolelocal+'\',\''+joblocal+'\',\''+jobuserNamelocal+'\')"></i> <i  style="cursor:pointer;" title="Delete" id="'+authorizationgroupidlocal+'" class="fa fa-times" aria-hidden="true" ng-click="deleteAuth('+authorizationgroupidlocal+',\''+controlgroupnamelocal+'\','+cosdabaccontrolgrouplocal+','+jobidlocal+','+organisationunitlocal+')"></i></span></td></tr>');
											        $("table#authTable tbody.authBody").append($el);
											        $compile($el)($scope);
											        
											        
									    	   }
									    	
									         
									       }, //end of /auth/saveAuthGroupPolicy/ success
									       function(response){
									          // :: Error';
									    	   $scope.errorMsg='Unable to create new auth group:: Error';
									       }
									    ); //end of /auth/saveAuthGroupPolicy/ http post call
				        	 
				        	
				         }// end of /auth/saveAuthGroup/ success
				         
			      
			       },  
			       function(response){
			         $scope.errorMsg='Unable to create new auth group:: Error';
			         
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
	  		  
	  		}//end of $scope.result == false
  	};

	/*** End of Add Authorisation **/
  	
	
 	 /** Start of Edit Auth Group Name **/
   $scope.editAuth = function(authGroup,authorizationgroupid,controlgroupname,cosdabaccontrolgroup,jobid,organisationunitid,organisationname,securityrole,job,jobuserName) {
	$rootScope.authGroupNameLocal = authGroup;
   	$scope.authorizationgroupid = authorizationgroupid;
   	$rootScope.controlgroupname = controlgroupname;
   	$scope.cosdabaccontrolgroup = cosdabaccontrolgroup;
   	$scope.jobid = jobid;
   	$scope.organisationunitid = organisationunitid;
   	$rootScope.orgLocal = organisationname+','+organisationunitid;
   	$rootScope.roleLocal = securityrole;
   	$rootScope.jobLocal = job+','+jobid;
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
 		var updatedjob = updatedjob.split(",");
 		var updatedjobname = updatedjob[0];
 		var updatedjobid = updatedjob[1];
 		var authGroupData = {
 	  	        "authorizationgroupid": $scope.authorizationgroupid,
 	  	        "cosdabaccontrolgroup": $scope.cosdabaccontrolgroup,
 	  	        "organisationunit": updatedorganisationunitid,
 	  	        "securityrole": updatedsecurityrole,
 	  	        "users": updatedjobuserName,
 	  	        "jobid": updatedjobid,
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
		    	 $scope.sucessMsg='Successfully updated Auth group info';
		         //alert('Successfully changed the group name to '+ updatedName);
		         var editorganisationname="td#"+$scope.authorizationgroupid+"organisationname";
		         var editsecurityrole="td#"+$scope.authorizationgroupid+"securityrole";
		         var editjob="td#"+$scope.authorizationgroupid+"job";
		         var editjobuserName="td#"+$scope.authorizationgroupid+"jobuserName .usrname";
		 	   	 $(editorganisationname).text(updatedorganisationname);
		 	   	 $(editsecurityrole).text(updatedsecurityrole);
		 	     $(editjob).text(updatedjobname);
		 	     $(editjobuserName).text(updatedjobuserName);
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
				        	 $scope.sucessMsg="Deleted the group successfully";
					         //var editcontrolgroupname="td."+$scope.selectedId+"controlgroupname";
					         $('#'+$scope.selectedAuthId+'controlgroupname').closest('tr').remove();
					 	   	// $(editcontrolgroupname).closest('tr').remove();
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
    
    

