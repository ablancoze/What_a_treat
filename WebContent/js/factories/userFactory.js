
/**
 * Modulo para gestionar los chollos
 */
angular.module('WhatAtreat').factory("userFactory", ['$http',function($http){
	
	var url = 'https://localhost:8443/What_a_treat/rest/user/';
	
	var userInterface = {
			
			getUser : function(){
	    		return $http.get(url)
	    			.then(function(response){
	    				return response.data;
	    			});
	    	} 	   
	}
    return userInterface;
}])