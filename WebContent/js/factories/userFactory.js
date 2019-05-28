
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
	    	},
	    	
	    	getUser : function(id){
	    		var urlid = url + id;
	            return $http.get(urlid)
	            	.then(function(response){
	            		return response.data;
	         		});
	    	},
	    	
	    	getUserName : function(id){
	    		var urlid = url +"name"+ id;
	            return $http.get(urlid)
	            	.then(function(response){
	            		return response.data;
	         		});
	    	},
	    	
	        deleteChollos : function(id){
	        	var urlid = url+id;
	            return $http.delete(urlid)
	            	.then(function(response){
	            		return response.status;
	            	});
	        }     
	}
    return userInterface;
}])