
/**
 * Modulo para gestionar los chollos
 */
angular.module('WhatAtreat').factory("commentFactory", ['$http',function($http){
	
	var url = 'https://localhost:8443/What_a_treat/rest/comment/';
	
	var commentInterface = {
	
			getCommentChollo : function(id){
				var urlid = url+id;
				return $http.get(urlid)
					.then(function(response){
						return response.data;
					});
			},
	
	
			postComment : function(comment){
				return $http.post(url,comment)
					.then(function(response){
						return response.data;
					});
			}
	}
    return commentInterface;
}])