/**
 * 
 */
angular.module('WhatAtreat').controller('cholloCtrl',['chollosFactory','$routeParams','$location',function(chollosFactory,$routeParams,$location){
	
	
	 var chollosViewModel = this;
	 
	 /*Atributos*/
	 chollosViewModel.listChollosHot = [];
	 chollosViewModel.chollo = {};
	 chollosViewModel.shopObj={};
	 
	 /*Funciones*/
	 chollosViewModel.functions = {
			 
		   		where : function(route){
		   			return $location.path() == route;
		   		},
				
				readChollo : function(id) {
					chollosFactory.getChollo(id)
						.then(function(response) {
							chollosViewModel.chollo = response;
							console.log("Reading treats with id: ", id," Response: ", response);
						}, function(response) {
							console.log("Error reading chollo");
							$location.path('/');
						})
				},
				
				readShopName : function(id) {
					shopFactory.getshop(id)
						.then(function(response) {
							chollosViewModel.shopObj = response;
							console.log("Reading treats with id: ", id," Response: ", response);
						}, function(response) {
							console.log("Error reading chollo");
							$location.path('/');
						})
				},
				
				/*Lee los tres chollos mas importantes del dia*/
				readChollosHot : function () {
					chollosFactory.getChollosHot()
						.then(function(response){
							chollosViewModel.listChollosHot = response;
							console.log("Reading three hot chollos", response);
						}, function(response){
							console.log("Error reading three hot chollos");
						})
				},
				
				/*Elimina un chollo de la lista de chollos de un usuario*/
				createChollo : function() {
					chollosFactory.postChollo(chollosViewModel.chollo)
						.then(function(response){
							console.log("Creating chollo. Response:", response);
		    			}, function(response){
		    				console.log("Error creating the chollo");
		    			})
				},
				
				/*Elimina un chollo de la lista de chollos de un usuario*/
				updateChollo : function() {
					chollosFactory.putChollo(chollosViewModel.order)
						.then(function(response){
							console.log("Updating chollo with id:",chollosViewModel.chollo.id," Response:", response);
		    			}, function(response){
		    				console.log("Error updating chollo");
		    			})
				},
				
				/*Elimina un chollo de la lista de chollos de un usuario*/
				deleteChollo : function() {
					chollosFactory.deleteChollo(id)
						.then(function(response){
							console.log("Deleting chollo with id:",id," Response:", response);
						},function (response){
							console.log("Error deleting chollo");
						})
				},
				
				cholloHandlerSwitcher : function(){
					if (chollosViewModel.functions.where('/newChollo')){
						console.log($location.path());
						chollosViewModel.functions.createChollo();
					}
					else if (chollosViewModel.functions.where('/user/updateChollo/'+chollosViewModel.chollo.id)){
						console.log($location.path());
						orderHandlerViewModel.functions.updateChollo();
					}
					else if (chollosViewModel.functions.where('/user/deleteChollo/'+chollosViewModel.chollo.id)){
						console.log($location.path());
						orderHandlerViewModel.functions.deleteChollo(chollosViewModel.chollo.id);
					}
					else {
					console.log($location.path());
					}
					$location.path('/');
				}
				
	 }
	 chollosViewModel.functions.readChollosHot();
	 if ($routeParams.ID!=undefined){
		 chollosViewModel.functions.readChollo($routeParams.ID);
	 }
}])
