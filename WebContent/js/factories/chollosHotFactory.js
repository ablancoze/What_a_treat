/**
 * 
 */

angular.module('WhatAtreat').factory("chollosHotFactory", ['$http',function($http){
	var url = 'https://localhost:8443/whatAtreat/rest/chollosHot/';
	
	var chollosHotInterface = {
			
			getChollosHot: function(){
	    		return $http.get(url)
	    			.then(function(response){
	    				return response.data;
	    			});
	    	}
			
	}
	return chollosHotInterface;
}])