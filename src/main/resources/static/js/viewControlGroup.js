app.controller('viewControlGroupCtrl', ['$scope', '$rootScope','$uibModal', '$http',  '$location', '$compile','myVars',function($scope, $rootScope,$uibModal, $http, $location, $compile,myVars) {
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
	
	if($rootScope.existingGroup==true){
		$("#transTable .tranBody tr").remove();
		
	
	for(i=0; i<$rootScope.existTranData.transaction.length; i++){
		$scope.existTranDataLocal = $rootScope.existTranData;
		//$scope.existAttrDataLocal = $rootScope.existAttrData[i];
		$scope.currentExistingTransactionId = $rootScope.existTranData.transaction[i].transactionid;
	
		transRow=transRow+1;
	    var transactionidlocal=$rootScope.existTranData.transaction[i].transactionid;
	    var transactionnamelocal=$rootScope.existTranData.transaction[i].transactionname;
		var transactiontypelocal=$rootScope.existTranData.transaction[i].transactiontype;
		var defaultaccesslocal=$rootScope.existTranData.transaction[i].defaultaccess;
	    var $el = $('<tr id="#'+transactionidlocal+'" class="'+transactionidlocal+'"> <td> <button class="btn btn-default btn-xs" data-toggle="collapse" class="accordion-toggle" data-target="#tr'+transactionidlocal+'" ><i style="" title="Open Constrains" class="fa fa-sort" aria-hidden="true" ng-click=""></i></button> </td> <td>'+transactionnamelocal+'</td> <td>'+transactiontypelocal+'</td> <td>'+defaultaccesslocal+'</td> <td> <span style="float:right"> <i style="margin-right:10px;cursor:pointer;" title="Delete" class="fa fa-times" aria-hidden="true" ng-click="delTran('+transactionidlocal+')"></i> <i style="cursor:pointer;" title="Add Attribute Constraints" class="fa fa-check-square-o" aria-hidden="true" ng-click="addAttribute('+transactionidlocal+')"></i> </span> </td> </tr> <tr> <td colspan="12" class="hiddenRow"> <div class="accordian-body collapse in" id="tr'+transactionidlocal+'"> <table class="table table-striped" style="width: 96%;margin: 10px auto;"> <thead> <tr> <th>Attribute Name</th> <th>Attribute Value</th> <th>Access Constraint Type</th> <th>Actions</th> </tr> </thead> <tbody class="tranAttr"></tbody> </table> </div> </td> </tr>');
	    $("table#transTable tbody.tranBody").append($el);
	    $compile($el)($scope);
	    
	    if($rootScope.existTranData.transaction[i].cosdabacconstraintvo!=null){
		    var transrow = '#tr' + transactionidlocal+' table tbody.tranAttr';
	        var currentTransId=transactionidlocal;
	        for(j=0; j<$rootScope.existTranData.transaction[i].cosdabacconstraintvo.length; j++){
	        var attributeconstraintidlocal=$rootScope.existTranData.transaction[i].cosdabacconstraintvo[j].attributeconstraintid;
	        var attributenamelocal=$rootScope.existTranData.transaction[i].cosdabacconstraintvo[j].attributename;
	   		var attributevaluelocal=$rootScope.existTranData.transaction[i].cosdabacconstraintvo[j].attributevalue;
	   		console.log("att value"+attributevaluelocal);
	   		var accessconstrainttypelocal=$rootScope.existTranData.transaction[i].cosdabacconstraintvo[j].accessconstrainttype;
	   		//var edit=attributeconstraintidlocal+',"'+attributenamelocal+'","'+attributevaluelocal+'","'+accessconstrainttypelocal+'",'+ $scope.currentTransId;
	   		var trid = "tr"+transactionidlocal+"att"+attributeconstraintidlocal;
	   		//console.log('<tr id='+trid +'> <td class="attributenamelocal">'+attributenamelocal +'</td> <td class="attributevaluelocal">'+attributevaluelocal+'</td> <td class="accessconstrainttypelocal">'+accessconstrainttypelocal+'</td> <td><span style="float:right"> <i style="margin-right:10px;cursor:pointer;" title="Delete" class="fa fa-times" aria-hidden="true" ng-click="delAttr('+attributeconstraintidlocal+','+ $scope.currentTransId+')"></i> <i style="cursor:pointer;" title="Edit" class="fa fa-pencil-square-o" aria-hidden="true" ng-click="openAttrEdit('+attributeconstraintidlocal+','+ currentTransId+',\''+attributenamelocal+'\',\''+attributevaluelocal+'\',\''+accessconstrainttypelocal+'\')"></i> </span></td> </tr>'); 
	   		var $el2 = $('<tr id='+trid +'> <td class="attributenamelocal">'+attributenamelocal +'</td> <td class="attributevaluelocal">'+attributevaluelocal+'</td> <td class="accessconstrainttypelocal">'+accessconstrainttypelocal+'</td> <td><span style="float:right"> <i style="margin-right:10px;cursor:pointer;" title="Delete" class="fa fa-times" aria-hidden="true" ng-click="delAttr('+attributeconstraintidlocal+','+ transactionidlocal+')"></i> <i style="cursor:pointer;" title="Edit" class="fa fa-pencil-square-o" aria-hidden="true" ng-click="openAttrEdit('+attributeconstraintidlocal+','+ currentTransId+',\''+attributenamelocal+'\',\''+attributevaluelocal+'\',\''+accessconstrainttypelocal+'\')"></i> </span></td> </tr>');
	        $(transrow).append($el2);
	        $compile($el2)($scope);
	        }
	    }
	}
	    
	};
	
	 //Load groupnames to dropdown
    var config = {
            headers : {
                'Content-Type': 'application/json'
            }
        }


    
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
	
    /*** Start of Add transaction **/
    $scope.addTransaction = function() {
    	$scope.sucessMsg="";
    	$scope.errorMsg="";
    	var modalinstance = $uibModal.open({
	      scope: $scope,
	      templateUrl: 'addTransactionModal.html'
	  	});
	  
  	};

  	/*** Start of Use Existing transaction **/
    $scope.useCategoryExistTransaction = function() {
    	$scope.sucessMsg="";
    	$scope.errorMsg="";
    	var modalinstance = $uibModal.open({
	      scope: $scope,
	      templateUrl: 'useCategoryExistTransactionModal.html'
	  	});
	  
  	};
  	
  	 $scope.useExistCategoryTransactionSave = function(ControupNames,conTranData){
     	
     	var conTranDataNew = conTranData.split(",");
 		var contransactionid = conTranDataNew[0];
 		var contransactionname = conTranDataNew[1];
 		var contransactiontype = conTranDataNew[2];
 		var condefaultaccess = conTranDataNew[3];
     	
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
     		        "controlgroupid":$rootScope.addGroup.controlgroupid
     	        };
     	

     	data=JSON.stringify(data);

         var config = {
             headers : {
                 'Content-Type': 'application/json'
             }
         }

     	console.log("add transaction for  consent group is json object ---- "+data);
     	
     	$http.post(myVars.site_prefix +"/control/existTrans/", data, config)
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
 			 		   		 var $el = $('<tr id="#'+transactionidlocal+'" class="'+transactionidlocal+'"> <td> <button class="btn btn-default btn-xs" data-toggle="collapse" class="accordion-toggle" data-target="#tr'+transactionidlocal+'" ><i style="" title="Open Constrains" class="fa fa-sort" aria-hidden="true" ng-click=""></i></button> </td> <td>'+transactionnamelocal+'</td> <td>'+transactiontypelocal+'</td> <td>'+defaultaccesslocal+'</td> <td> <span style="float:right"> <i style="margin-right:10px;cursor:pointer;" title="Delete" class="fa fa-times" aria-hidden="true" ng-click="delTran('+transactionidlocal+')"></i> <i style="cursor:pointer;" title="Add Attribute Constraints" class="fa fa-check-square-o" aria-hidden="true" ng-click="addAttribute('+transactionidlocal+')"></i> </span> </td> </tr> <tr> <td colspan="12" class="hiddenRow"> <div class="accordian-body collapse in" id="tr'+transactionidlocal+'"> <table class="table table-striped" style="width: 96%;margin: 10px auto;"> <thead> <tr> <th>Attribute Name</th> <th>Attribute Value</th> <th>Access Constraint Type</th> <th>Actions</th> </tr> </thead> <tbody class="tranAttr"></tbody> </table> </div> </td> </tr>');
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
  	
  	console.log("$rootScope.existAttrData"+$rootScope.existAttrData);
  	console.log("$rootScope.existTranData"+$rootScope.existTranData);
  	console.log("$rootScope.addGroup"+$rootScope.addGroup);

  	 $scope.addTransactionSave = function(tranName,tranType,accessType){
 		var data = {
             "transactiontype": tranType,
 	        "transactionname": tranName,
 	        "defaultaccess": accessType,
 	        "createdby": $rootScope.globals.currentUser.username,
 	        "createdon": "",
 	        "lastupdatedby": "",
 	        "lastupdatedon":"",
 	        "versionno":"",
 	        "controlgroupid":$rootScope.addGroup.controlgroupid
         };

     	data=JSON.stringify(data);

         var config = {
             headers : {
                 'Content-Type': 'application/json'
             }
         }

     	console.log("add transaction is json object ---- "+data);
     	
     	$http.post(myVars.site_prefix +"/control/saveTxn/", data, config)
 		   .then(
 		       function(response){
 		    	 if(response.data.message=="Duplicate"){
 		    		 $scope.errorMsg="Transaction with this name already exists. Please try with another name."
 		    	 }else{
 			         $scope.myTransData = response.data;
 			         
 			        transRow=transRow+1;
 			        var transactionidlocal=$scope.myTransData.transactionid;
 			        var transactionnamelocal=$scope.myTransData.transactionname;
 			   		var transactiontypelocal=$scope.myTransData.transactiontype;
 			   		var defaultaccesslocal=$scope.myTransData.defaultaccess;
 			        var $el = $('<tr id="#'+transactionidlocal+'" class="'+transactionidlocal+'"> <td> <button class="btn btn-default btn-xs" data-toggle="collapse" class="accordion-toggle" data-target="#tr'+transactionidlocal+'" ><i style="" title="Open Constrains" class="fa fa-sort" aria-hidden="true" ng-click=""></i></button> </td> <td>'+transactionnamelocal+'</td> <td>'+transactiontypelocal+'</td> <td>'+defaultaccesslocal+'</td> <td> <span style="float:right"> <i style="margin-right:10px;cursor:pointer;" title="Delete" class="fa fa-times" aria-hidden="true" ng-click="delTran('+transactionidlocal+')"></i> <i style="cursor:pointer;" title="Add Attribute Constraints" class="fa fa-check-square-o" aria-hidden="true" ng-click="addAttribute('+transactionidlocal+')"></i> </span> </td> </tr> <tr> <td colspan="12" class="hiddenRow"> <div class="accordian-body collapse in" id="tr'+transactionidlocal+'"> <table class="table table-striped" style="width: 96%;margin: 10px auto;"> <thead> <tr> <th>Attribute Name</th> <th>Attribute Value</th> <th>Access Constraint Type</th> <th>Actions</th> </tr> </thead> <tbody class="tranAttr"></tbody> </table> </div> </td> </tr>');
 			        $("table#transTable tbody.tranBody").append($el);
 			        $compile($el)($scope);
 		    	 }
 		       
 		       }, 
 		       function(response){
 		         $scope.errorMsg='Unable to create new transaction:: Error';
 		         //alert('Unable to create new transaction:: Error - '+ response.statusText);
 		       }
 		    );
 	 
 	}

	/*** End of Add transaction **/

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

    	$http.post(myVars.site_prefix +"/control/deleteTxn/", respDelData, config)
		   .then(
		       function(response){
		         $scope.msg = response.data;
		         
		         if(response.data.message){
		        	 if(response.data.message=='Error-constraint Record Present'){
		        		 $scope.errorMsg="Constraint data is present for this Transaction. Deletion not possible.";
			          		
		        	 }else if(response.data.message=='Record deleted'){
		        		
		        		 $('.'+selectedtranId).next('tr').remove();
				 		 $('.'+selectedtranId).remove();
				 		$scope.sucessMsg="Transaction deleted successfully";
				 		
		        	 }
		         }
		       }, 
		       function(response){
		    	   $scope.errorMsg="Unable to delete the transaction:: Error";
		        
		       }
		    );
  	};
    /** End of transaction delete  **/

    /*** Start of add attribute constrains **/
    $scope.addAttribute = function(transId) {
    	$scope.sucessMsg="";
    	$scope.errorMsg="";
    	$scope.currentTransId = transId;
    	var transrow = '#tr' + $scope.currentTransId+' table tbody.tranAttr';
    	var rowCount = $(transrow+' tr').length;
    	if(rowCount > 5){
    		$scope.errorMsg="Cannot add more than one Attribute Contraint to a transaction.";
    	}else{
	    	var modalinstance = $uibModal.open({
		      scope: $scope,
		      templateUrl: 'addAttributeModal.html'
		  	});
    	}
	  
  	};

    $scope.addAttributeSave = function(attrName,attrValue,attrType){
		var data = {
            "attributename": attrName,
	        "attributevalue": attrValue,
	        "accessconstrainttype": attrType,
	        "createdby": $rootScope.globals.currentUser.username,
	        "createdon": "",
	        "lastupdatedby": "",
	        "lastupdatedon":"",
	        "controlgroupid":$rootScope.addGroup.controlgroupid,
	        "transactionid":$scope.currentTransId
        };

    	data=JSON.stringify(data);

        var config = {
            headers : {
                'Content-Type': 'application/json'
            }
        }

    	console.log("addAttribute data is json object ---- "+data);
    	
    	$http.post(myVars.site_prefix +"/control/saveConstraint/", data, config)
		   .then(
		       function(response){
		         $scope.myattrData = response.data;

		        var transrow = '#tr' + $scope.currentTransId+' table tbody.tranAttr';
		        var currentTransId=$scope.currentTransId;
		        var attributeconstraintidlocal=$scope.myattrData.attributeconstraintid;
		        var attributenamelocal=$scope.myattrData.attributename;
		   		var attributevaluelocal=$scope.myattrData.attributevalue;
		   		console.log("a value"+attributevaluelocal)
		   		var accessconstrainttypelocal=$scope.myattrData.accessconstrainttype;
		   		//var edit=attributeconstraintidlocal+',"'+attributenamelocal+'","'+attributevaluelocal+'","'+accessconstrainttypelocal+'",'+ $scope.currentTransId;
		   		var trid = "tr"+$scope.currentTransId+"att"+attributeconstraintidlocal;
		   		//console.log('<tr id='+trid +'> <td class="attributenamelocal">'+attributenamelocal +'</td> <td class="attributevaluelocal">'+attributevaluelocal+'</td> <td class="accessconstrainttypelocal">'+accessconstrainttypelocal+'</td> <td><span style="float:right"> <i style="margin-right:10px;cursor:pointer;" title="Delete" class="fa fa-times" aria-hidden="true" ng-click="delAttr('+attributeconstraintidlocal+','+ $scope.currentTransId+')"></i> <i style="cursor:pointer;" title="Edit" class="fa fa-pencil-square-o" aria-hidden="true" ng-click="openAttrEdit('+attributeconstraintidlocal+','+ currentTransId+',\''+attributenamelocal+'\',\''+attributevaluelocal+'\',\''+accessconstrainttypelocal+'\')"></i> </span></td> </tr>'); 
		   		
		        var $el2 = $('<tr id='+trid +'> <td class="attributenamelocal">'+attributenamelocal +'</td> <td class="attributevaluelocal">'+attributevaluelocal+'</td> <td class="accessconstrainttypelocal">'+accessconstrainttypelocal+'</td> <td><span style="float:right"> <i style="margin-right:10px;cursor:pointer;" title="Delete" class="fa fa-times" aria-hidden="true" ng-click="delAttr('+attributeconstraintidlocal+','+ $scope.currentTransId+')"></i> <i style="cursor:pointer;" title="Edit" class="fa fa-pencil-square-o" aria-hidden="true" ng-click="openAttrEdit('+attributeconstraintidlocal+','+ currentTransId+',\''+attributenamelocal+'\',\''+attributevaluelocal+'\',\''+accessconstrainttypelocal+'\')"></i> </span></td> </tr>');
		        $(transrow).append($el2);
		        $compile($el2)($scope);

		       }, 
		       function(response){
		    	   $scope.errorMsg="Unable to add constraint to the transaction:: Error";
		         //alert('Unable to add constraint to the transaction:: Error - '+ response.statusText);
		       }
		    );
	 
	}
	/*** End of Add attribute constrains **/

	/** Start del attr **/
	$scope.delAttr = function(selectedattrId,trid){
		$scope.sucessMsg="";
    	$scope.errorMsg="";
		var trid = "tr"+trid+"att"+selectedattrId;
  		var respAttrData = {
        	"attributeconstraintid": selectedattrId,
            "attributename": "",
	        "attributevalue": "",
	        "accessconstrainttype": "",
	        "createdby": "",
	        "createdon": "",
	        "lastupdatedby": "",
	        "lastupdatedon":""
        };
  		
    	respAttrData = JSON.stringify(respAttrData);

    	console.log("deleteAttr JSON object --- "+respAttrData);

    	var config = {
            headers : {
                'Content-Type': 'application/json'
            }
        }

    	$http.post(myVars.site_prefix +"/control/delConstraint/", respAttrData, config)
		   .then(
		       function(response){
		         $scope.msg = response.data;
		         
		         var attrrow = '#' + trid;
		       	 $(attrrow).remove();
		       	 $scope.sucessMsg='Constraint for the transaction deleted successfully';
		         //alert('Constraint for the transaction deleted successfully');

		       }, 
		       function(response){
		    	   $scope.errorMsg='Unable to delete constraint for the transaction:: Error';
		         //alert('Unable to delete constraint for the transaction:: Error - '+ response.statusText);
		       }
		    );
  	};
    /** End of del attr  **/

    /** Start of Edit attr Name **/
    $scope.openAttrEdit = function(selectedattrId,trid,attrName,attrValue,accessconstrainttype) {
    	$scope.sucessMsg="";
    	$scope.errorMsg="";
    	$scope.selectedAttrId = selectedattrId;
    	$scope.attrRrowid = trid;
    	console.log("seltrid"+$rootScope.selectedAttrId);
    	console.log("trid"+$rootScope.selectedAttrId);
    	var attreditname="tr#"+trid+" td.attributenamelocal";
   		var attreditvalue="tr#"+trid+" td.attributevaluelocal";
   		var attreditaccess="tr#"+trid+" td.accessconstrainttypelocal";
   		
   		$(attreditname).text();
   		$(attreditvalue).text();
   		$(attreditaccess).text();
    	
    	$rootScope.selectedAttrName = attrName;
    	console.log($rootScope.selectedAttrName);
    	$rootScope.selectedAttrValue = attrValue;
    	console.log("selected value " +$rootScope.selectedAttrValue);
		$rootScope.selectedaccessconstrainttype = accessconstrainttype;
	    var modalinstance = $uibModal.open({
	      scope: $scope,
	      templateUrl: 'editAttrModal.html',
	      resolve: {
	        myData: function() {
	          return $scope.myData;
	        }
	      }
	    });
	  
  	};

  	
  	$scope.editSave = function(upattrName,upattrValue,upaccessconstrainttype){
  		//var trid = "tr"+trid+"att"+selectedattrId;
  		var a = upattrValue;
  		console.log("a"+a);
  		var trid = "tr"+$scope.attrRrowid+"att"+$scope.selectedAttrId;
  		var data = {
        	"attributeconstraintid": $scope.selectedAttrId,
            "attributename": upattrName,
	        "attributevalue": upattrValue,
	        "accessconstrainttype": upaccessconstrainttype,
	        "createdby": "",
	        "createdon": "",
	        "lastupdatedby": "",
	        "lastupdatedon":""
        };
  		
  		data=JSON.stringify(data);

        var config = {
            headers : {
                'Content-Type': 'application/json'
            }
        }

    	console.log("selected data is json object ---- "+data);
    	
    	$http.post(myVars.site_prefix +"/control/updateConstraint/", data, config)
		   .then(
		       function(response){
		        $scope.newattrData = response.data;
		         
		        var attreditname="tr#"+trid+" td.attributenamelocal";
		   		var attreditvalue="tr#"+trid+" td.attributevaluelocal";
		   		var attreditaccess="tr#"+trid+" td.accessconstrainttypelocal";
		   		
		   		$(attreditname).text(upattrName);
		   		$(attreditvalue).text(upattrValue);
		   		$(attreditaccess).text(upaccessconstrainttype);
		   		$rootScope.selectedAttrValue = upattrValue;
		   		$scope.sucessMsg='Successfully changed constraint details for the transaction';
		        //alert('Successfully changed constraint details for the transaction');
		       }, 
		       function(response){
		    	   $scope.errorMsg='Unable to edit constraint details for the transaction:: Error';
		         //alert('Unable to edit constraint details for the transaction:: Error - '+ response.statusText);
		       }
		    );
    };
    /** End of Edit attr Name **/

}]);

