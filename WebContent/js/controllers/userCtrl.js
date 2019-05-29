/**
 * 
 */

angular.module('WhatAtreat').controller('userCtrl',['userFactory','$routeParams','chollosFactory',function(userFactory,chollosFactory,$routeParams){
	
	
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
				readChollosUser : function(id) {
					chollosFactory.getChollosUser(id)
						.then(function(response) {
							userViewModel.chollos = response;
							console.log("Reading the treats of user: ",id, response);
						}, function(response) {
							console.log("Error reading treats");
						})
				}
	 }
	 
	 userViewModel.functions.readUser();
	 if ($routeParams.ID!=undefined){
		 userViewModel.functions.readChollosUser($routeParams.ID);
	 }
}])
