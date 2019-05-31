/**
 * 
 */
angular.module('WhatAtreat').controller('commentCtrl',['commentFactory','$routeParams','$location','$window',function(commentFactory,$routeParams,$location,$window){
	
	
	 var commentListViewModel = this;
	 
	 /*Atributos*/
	 commentListViewModel.listComment = [];
	 
	 /*Funciones*/
	 commentListViewModel.functions = {
			
				/* Leer todos los chollos de la base de datos */
				readListComment : function(id) {
					commentFactory.getCommentChollo(id)
						.then(function(response) {
							commentListViewModel.listComment = response;
							console.log("Reading all comments of chollo: ",id, response);
						}, function(response) {
							console.log("Error reading comments");
						})
				}
	 }
	 
	 if ($routeParams.ID!=undefined){
		 commentListViewModel.functions.readListComment($routeParams.ID);
	 }
	 
}])
