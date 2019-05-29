
/**
 * Modulo para gestionar los chollos
 */
angular.module('WhatAtreat').factory("chollosFactory", ['$http',function($http){
	
	var url = 'https://localhost:8443/What_a_treat/rest/chollos/';
	
	var chollosInterface = {
			
			getChollos : function(){
	    		return $http.get(url)
	    			.then(function(response){
	    				return response.data;
	    			});
	    	},
	    	
	    	getChollo : function(id){
	    		var urlid = url + id;
	            return $http.get(urlid)
	            	.then(function(response){
	            		return response.data;
	         		});
	    	},
	    	
	    	getChollosUser : function(id){
	    		var urlid = url +"user/"+id;
	            return $http.get(urlid)
	            	.then(function(response){
	            		return response.data;
	         		});
	    	},
	    	
			getChollosHot: function(){
				var urlHot = url+"hot";
	    		return $http.get(urlHot)
	    			.then(function(response){
	    				return response.data;
	    			});
	    	},
	    	
	    	postChollo:  function(chollo){
	    		return $http.post(url,chollo)
	            	.then(function(response){
	            		return response.status;
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
    return chollosInterface;
}])