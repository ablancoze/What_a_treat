/**
 * 
 */
angular.module('WhatAtreat').controller('cholloList',['chollosFactory',function(chollosFactory){
	
	
	 var chollosListViewModel = this;
	 
	 /*Atributos*/
	 chollosListViewModel.listChollos = [];
	 
	 /*Funciones*/
	 chollosListViewModel.functions = {
			
				/* Leer todos los chollos de la base de datos */
				readListChollos : function() {
					chollosFactory.getChollos()
						.then(function(response) {
							chollosListViewModel.listChollos = response;
							console.log("Reading all the treats: ", response);
						}, function(response) {
							console.log("Error reading treats");
						})
				}
	 }
	 chollosListViewModel.functions.readListChollos();

}])
