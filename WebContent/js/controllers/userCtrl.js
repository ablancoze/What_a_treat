/**
 * 
 */

angular.module('WhatAtreat').controller('userCtrl',['userFactory',function(userFactory){
	
	
	 var userViewModel = this;
	 
	 /*Atributos*/
	 userViewModel.user = {};
	 userViewModel.chollos = [];
	 userViewModel.name = "";
	 
	 /*Funciones*/
	 userViewModel.functions = {
			 	
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
				readChollosUser : function() {
					chollosFactory.getChollosUser()
						.then(function(response) {
							userViewModel.chollos = response;
							console.log("Reading all the treats: ", response);
						}, function(response) {
							console.log("Error reading treats");
						})
				}
				
	 }
	 userViewModel.functions.readUser();
}])
