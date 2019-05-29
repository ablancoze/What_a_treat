
/**
 * Modulo para gestionar los chollos
 */
angular.module('WhatAtreat').factory("shopFactory", ['$http',function($http){
	
	var url = 'https://localhost:8443/What_a_treat/rest/shop/';
	
	var shopInterface = {
			
			getShops : function(){
	    		return $http.get(url)
	    			.then(function(response){
	    				return response.data;
	    			});
	    	}, 
	
			getShop : function(id){
				var urlid = url + id;
				return $http.get(url)
					.then(function(response){
						return response.data;
					});
			} 
	}
    return shopInterface;
}])