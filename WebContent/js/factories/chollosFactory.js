
/**
 * Modulo para gestionar los chollos
 */
angular.module('WhatAtreat').factory("chollosFactory", ['$http',function($http){
	var url = 'https://localhost:8443/whatAtreat/rest/chollos/';
	
	var chollosInterface = {
			
			getChollos: function(){
	    		return $http.get(url)
	    			.then(function(response){
	    				return response.data;
	    			});
	    	},
	    	
	    	getChollos : function(id){
	    		var urlid = url + id;
	            return $http.get(urlid)
	            	.then(function(response){
	            		return response.data;
	         		});
	    	},
	    	
	    	putChollos : function(order){
	    		var urlid = url+order.id;
	            return $http.put(urlid, order)
	            	.then(function(response){
	      				 return response.status;
	  				});                   
	    	},
	    	
	    	postChollos :  function(order){
	    		return $http.post(url,order)
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
	        },
	        
			getChollosHot: function(){
				var urlid = url+"hot";
	    		return $http.get(urlid)
	    			.then(function(response){
	    				return response.data;
	    			});
	    	}
	        
	        
	}
    return ordersInterface;
}])