/**
 * 
 */

angular.module('WhatAtreat').controller('loginCtrl',['userFactory',function(userFactory){
	
	
	 var loginViewModel = this;
	 
	 /*Atributos*/
	 loginViewModel.conected = false;
	 
	 /*Funciones*/
	 loginViewModel.functions = {
			 
		   		loging : function(){
		   			if (loginViewModel.conected == false)
		   				return false;
		   			else
		   				return true;
		   		},

		   		
				readUser : function() {
					if (loginViewModel.conected==false){
						userFactory.getUser().then(function(response) {
							if (angular.isObject(response)){
								loginViewModel.conected=true;
								console.log("chekin login user", response);
							}
						}, function(response) {
							console.log("error on chekin login user");
						})
					}
				}
	 }
	 loginViewModel.functions.readUser();
}])