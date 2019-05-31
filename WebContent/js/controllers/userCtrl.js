/**
 * 
 */

angular.module('WhatAtreat').controller('userCtrl',['userFactory','chollosFactory','$routeParams','$location',function(userFactory,chollosFactory,$routeParams,$location){
	
	
	 var userViewModel = this;
	 
	 /*Atributos*/
	 userViewModel.user = {};
	 userViewModel.chollos = [];
	 userViewModel.name = "";
	 userViewModel.oldPassword = "";
	 userViewModel.newPassword = "";
	 
	 userViewModel.wrongPassword = "Password incorrecta";
	 
	 /*Funciones*/
	 userViewModel.functions = {
			 
		   		where : function(route){
		   			return $location.path() == route;
		   		},
			 	
				readUser : function() {
					userFactory.getUser().then(function(response) {
						if (angular.isObject(response)){
							userViewModel.user = response;
							console.log("Geting an user", response);
						}
					}, function(response) {
						console.log("Error reading user");
					})
				},
				
				/* Leer todos los chollos de la base de datos */
				readChollosUser : function(id) {
					chollosFactory.getChollosUser(id)
						.then(function(response) {
							userViewModel.chollos = response;
							console.log("Reading the treats of user: ",id, response);
						}, function(response) {
							console.log("Error reading treats");
						})
				},
				
				/*Actualiza un chollo de la lischa de chollos del usuario*/
				updateUser : function() {
					userFactory.putChollo(chollosViewModel.chollo)
						.then(function(response){
							console.log("Updating chollo with id:",chollosViewModel.chollo.id," Response:", response);
		    			}, function(response){
		    				console.log("Error updating chollo");
		    			})
				},
				
				/*Elimina un chollo de la lista de chollos de un usuario*/
				deleteUser : function(id) {
					userFactory.deleteChollo(id)
						.then(function(response){
							console.log("Deleting chollo with id:",id," Response:", response);
						},function (response){
							console.log("Error deleting chollo");
						})
				},
				
				
				userHandlerSwitcher : function(){
					if (userViewModel.oldPassword == userViewModel.user.password){
						if (userViewModel.functions.where('/user/updateUser')){
							console.log($location.path());
							userViewModel.functions.updateUser();
						}else{
							if (userViewModel.functions.where('/user/deleteUser')){
								console.log($location.path());
								userViewModel.functions.deleteUser(userViewModel.user.id);
							}else{
								console.log($location.path());
							}
						}
						$window.location.href = '/What_a_treat/pages/Index.html';
					}else{
						$location.path('/');
					}
					
				}	
	 }
	 
	 userViewModel.functions.readUser();
	 if ($routeParams.ID!=undefined){
		 userViewModel.functions.readChollosUser($routeParams.ID);
	 }
}])
