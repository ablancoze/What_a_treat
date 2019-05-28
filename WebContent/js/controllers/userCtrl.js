/**
 * 
 */

angular.module('WhatAtreat').controller('userCtrl',['userFactory','chollosFactory','$routeParams','$location',function(userFactory){
	
	
	 var userViewModel = this;
	 
	 /*Atributos*/

	 userViewModel.user = {};
	 userViewModel.chollos = [];
	 userViewModel.name = "";
	 
	 /*Funciones*/
	 userViewModel.functions = {
			 
		   		where : function(route){
		   			return $location.path() == route;
		   		},
				
				readUser : function() {
					userFactory.getUser(id).then(function(response) {
						userViewModel.user = response;
						console.log("Geting an user", response);
					}, function(response) {
						console.log("Error reading user");
					})
				},
				
				readUserName : function() {
					userFactory.getUserName(id).then(function(response){
						userViewModel.name = response;
						console.log("Geting name user", response);
					}, function(response) {
						console.log("Error reading user");
					})
				},
				
				
				readUserChollos : function() {
					chollosFactory.getChollosUser(id).then(function(response){
						userViewModel.chollos = response;
						console.log("Geting chollos user", response);
					}, function(response) {
						console.log("Error reading user");
					})
				},
				
				/*Elimina un chollo de la lista de chollos de un usuario*/
				deleteUser : function() {
					userViewModel.deleteUser(id).then(function(response){
						console.log("Deleting user with id:",id," Response:", response);
					},function (response){
						console.log("Error deleting user");
					})
				}
				
	 }
}])
