
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
	    	
	    	getUserName : function(id){
	    		var urlid = url+"name/"+id;
	    		return $http.get(urlid)
	    			.then(function(response){
	    				return response.data;
	    			});
	    	},
	    	
			usernameTest : function(username){
				var urlid = url+username;
	    		return $http.get(urlid)
	    			.then(function(response){
	    				return response.data;
	    			});
	    	},
	    	
	    	
			emailTest : function(email){
				var urlid = url+email;
	    		return $http.get(urlid)
	    			.then(function(response){
	    				return response.data;
	    			});
	    	},
	    	
	    	putUser : function(user){
	    		var urlid = url+user.id;
	    		return $http.put(urlid,user)
	    			.then(function(response){
	    				return response.data;
	    			});
	    	},
	    	
	    	deleteUser : function(id){
	    		var urlid = url+id;
	    		return $http.delete(urlid)
	    			.then(function(response){
	    				return response.data;
	    			});
	    	}
	    	
	}
    return userInterface;
}])