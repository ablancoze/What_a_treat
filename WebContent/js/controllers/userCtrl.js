/**
 * 
 */

angular.module('WhatAtreat').controller('userCtrl',['userFactory','chollosFactory','$routeParams','$location','$route','$window',function(userFactory,chollosFactory,$routeParams,$location,$route,$window){
	
	
	 var userViewModel = this;
	 
	 /*Atributos*/
	 userViewModel.user = {};
	 userViewModel.chollos = [];
	 userViewModel.oldPassword;
	 userViewModel.newPassword = "";
	 
	 userViewModel.newUserName = "";
	 userViewModel.newEmail = "";
	 
	 userViewModel.bandera = false;
	 
	 userViewModel.wrongPassword;
	 userViewModel.wrongUserName;
	 userViewModel.wrongEmail;
	 
	 userViewModel.userNameBoolean=false;
	 userViewModel.userEmailBoolean=false;
	 
	 /*Funciones*/
	 userViewModel.functions = {
			 
			 	passwordTest : function(){
			 		if(userViewModel.oldPassword == userViewModel.user.password ){
			 			return true;
			 		}else{
			 			userViewModel.wrongPassword = "Password incorrecta";
			 			return false;
			 		}
			 	},
			 	
			 	userNameTest : function(){
					userFactory.usernameTest(userViewModel.newUserName).then(function(response){
						if (response.userName != ""){
							userViewModel.wrongEmail = "ese username ya existe";
							console.log("El usuario ya existe",response)
							userViewModel.userNameBoolean=true;
						}
					},function (response){
					})
			 	},
			 	
			 	emailTest : function(){
					userFactory.emailTest(userViewModel.newEmail).then(function(response){
						userViewModel.wrongEmail = "El email ya existe";
						return false;
					},function (response){
			 			return true;
					})
			 	},
			 
		   		where : function(route){
		   			return $location.path() == route;
		   		},
			 	
				readUser : function() {
					userFactory.getUser().then(function(response) {
						if (angular.isObject(response)){
							userViewModel.user = response;
							console.log("Geting an user with username: ", response.username,"\n id: ",response.id);
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
					
					if (userViewModel.newPassword != ""){
						if (userViewModel.functions.passwordTest()){
							userViewModel.user.password = userViewModel.newPassword;
							userViewModel.bandera = true;
						}else{
							$window.location.href = '/What_a_treat/pages/Index.html#!/user/settings/'+userViewModel.user.id;
						}
					}
					
					if (userViewModel.newUserName != ""){
						if (userViewModel.functions.userNameTest() || userViewModel.userNameBoolean==true){
							userViewModel.user.username = userViewModel.newUserName;
							userViewModel.bandera = true;
						}else{
							$window.location.href = '/What_a_treat/pages/Index.html#!/user/settings/'+userViewModel.user.id;
						}
						
					}
					
					if (userViewModel.newEmail != ""){
						userViewModel.functions.emailTest()
						if (!userViewModel.userNameBoolean){
							userViewModel.user.email = userViewModel.newEmail;
							userViewModel.bandera = true;
						}else{
							$window.location.href = '/What_a_treat/pages/Index.html#!/user/settings/'+userViewModel.user.id;
						}
					}
					
					if (userViewModel.bandera){
						userFactory.putUser(userViewModel.user).then(function(response){
							console.log("Deleting user with id:",id," Response:", response);
						},function (response){
							console.log("Error deleting chollo");
						})
						
						$window.location.href = '/What_a_treat/pages/Index.html';
						
					}else{
						$window.location.href = '/What_a_treat/pages/Index.html#!/user/settings/'+userViewModel.user.id;
					}

				},
				
				/*Elimina un chollo de la lista de chollos de un usuario*/
				deleteUser : function() {
					userFactory.deleteUser(userViewModel.user.id)
						.then(function(response){
							console.log("Deleting user with id:",id," Response:", response);
						},function (response){
							console.log("Error deleting chollo");
						})
						$window.location.href = '/What_a_treat/pages/Index.html';
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
						$window.location.href = '/What_a_treat/pages/Index.html';
					}
					
				}	
	 }
	 
	 userViewModel.functions.readUser();
	 if ($routeParams.ID!=undefined){
		 userViewModel.functions.readChollosUser($routeParams.ID);
	 }
}])
