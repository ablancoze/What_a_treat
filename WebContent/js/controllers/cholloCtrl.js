/**
 * 
 */
angular.module('WhatAtreat').controller('cholloCtrl',['chollosFactory','userFactory','shopFactory','$routeParams','$location','$window',function(chollosFactory,userFactory,shopFactory,$routeParams,$location,$window){
	
	
	 var chollosViewModel = this;
	 
	 /*Atributos*/
	 chollosViewModel.listChollosHot = [];
	 chollosViewModel.chollo = {};
	 chollosViewModel.shopObj={};
	 chollosViewModel.user="";
	 
	 /*Funciones*/
	 chollosViewModel.functions = {
			 
		   		where : function(route){
		   			return $location.path() == route;
		   		},
				
				readChollo : function(id) {
					chollosFactory.getChollo(id).then(function(response1) {
							chollosViewModel.chollo = response1;
							console.log("Reading treats with id: ", id," Response: ", response1);
							
							shopFactory.getShopById(chollosViewModel.chollo.ids).then(function(response2){
								chollosViewModel.shopObj = response2;
								console.log("Reading shop of treats with id: ", id," Response: ", response2);
							}, function(response2) {
								console.log("Error reading shop");
							})
							
							userFactory.getUserName(chollosViewModel.chollo.idu).then(function(response3){
								chollosViewModel.user = response3;
								console.log("Reading User name from the chollo",chollosViewModel.chollo.id, response3);
							}, function(response3){
								console.log("User not conected");
							})
							
						}, function(response1) {
							console.log("Error reading chollo");
						})
				},
				
				readShopName : function(id) {
					shopFactory.getShopById(id).then(function(response){
						chollosViewModel.shopObj = response;
						console.log("Reading shop of treats with id: ", id," Response: ", response);
					},function(response) {
						console.log("Error reading shop");
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
				
				readUserId : function () {
					userFactory.getUser().then(function(response){
						chollosViewModel.chollo.idu = response.id;
							console.log("Reading User with id",response.id, "for cholloCtr");
						}, function(response){
							console.log("User not conected");
						})
				},
				
				readUserNameChollo : function(id){

				},
				
				
				
				/*Permite la creacion de un chollo*/
				createChollo : function() {
					chollosFactory.postChollo(chollosViewModel.chollo)
						.then(function(response){
							console.log("Creating chollo. Response:", response);
		    			}, function(response){
		    				console.log("Error creating the chollo");
		    			})
				},
				
				/*Actualiza un chollo de la lischa de chollos del usuario*/
				updateChollo : function() {
					if (chollosViewModel.chollo.soldout == true){
						chollosViewModel.chollo.soldout = 1;
					}
					chollosFactory.putChollo(chollosViewModel.chollo)
						.then(function(response){
							console.log("Updating chollo with id:",chollosViewModel.chollo.id," Response:", response);
		    			}, function(response){
		    				console.log("Error updating chollo");
		    			})
				},
				
				/*Elimina un chollo de la lista de chollos de un usuario*/
				deleteChollo : function(id) {
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
					else if (chollosViewModel.functions.where('/user/updateChollo')){
						console.log($location.path());
						chollosViewModel.functions.updateChollo();
					}
					else if (chollosViewModel.functions.where('/user/deleteChollo')){
						console.log($location.path());
						chollosViewModel.functions.deleteChollo(chollosViewModel.chollo.id);
					}
					else {
					console.log($location.path());
					}
					$window.location.href = '/What_a_treat/pages/Index.html';
				}				
	 }
	 
	 chollosViewModel.functions.readUserId();
	 chollosViewModel.functions.readChollosHot();
	 var cholloid=$location.hash();
	 if ($routeParams.ID!=undefined){
		 chollosViewModel.functions.readChollo($routeParams.ID);
	 }
	 
	 if (cholloid!=""){
		 chollosViewModel.functions.readChollo(cholloid);
	 }
	 
}])
