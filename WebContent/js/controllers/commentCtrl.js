
/**
 * 
 */
angular.module('WhatAtreat').controller('commentCtrl',['commentFactory','userFactory','$routeParams','$location','$window',function(commentFactory,userFactory,$routeParams,$location,$window){
	
	
	 var commentListViewModel = this;
	 
	 /*Atributos*/
	 commentListViewModel.listComment = [];
	 commentListViewModel.comment={};
	 
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
				},
	 
	 
				/* Leer todos los chollos de la base de datos */
				createComment : function() {
					userFactory.getUser().then(function(response) {
						if (angular.isObject(response)){
							userViewModel.user = response;
							console.log("Geting an user with username: ", response.username,"\n id: ",response.id);
						}
					}, function(response) {
						console.log("Error reading user");
					})
					
					commentFactory.postComment(commentListViewModel.comment)
					.then(function(response1) {
							console.log("creating comment: ", commentListViewModel.comment.comentario , "by the user: ", commentListViewModel.comment.username,"in the chollo", commentListViewModel.comment.idc);
						}, function(response) {
							console.log("Error reading comments");
					})
					
					
				}
	 
	 			
	 }
	 
	 if ($routeParams.ID!=undefined){
		 commentListViewModel.functions.readListComment($routeParams.ID);
	 }
	 
}])