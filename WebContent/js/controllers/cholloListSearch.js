/**
 * 
 */
angular.module('WhatAtreat').controller('cholloListSearch',['chollosFactory','$routeParams','$scope','$sce',function(chollosFactory,$routeParams,$scope, $sce){
	
	
	 var chollosListViewModel = this;
	 
	 /*Atributos*/
	 chollosListViewModel.listChollos = [];
	 
	 /*Funciones*/
	 chollosListViewModel.functions = {
			
				/* Leer todos los chollos de la base de datos */
				readListChollosBySearch : function(search) {
					chollosFactory.getChollosBySearch(search)
						.then(function(response) {
							chollosListViewModel.listChollos = response;
							console.log("Reading all the treats: ", response);
						}, function(response) {
							console.log("Error reading treats");
						})
				},
	 
				readListChollosByShopId : function(id) {
					chollosFactory.getChollosByShopId(id)
						.then(function(response) {
							chollosListViewModel.listChollos = response;
							console.log("Reading all the treats: ", response);
						}, function(response) {
							console.log("Error reading treats");
						})
				}
	 }
	 if ($routeParams.SEARCH!=undefined){
		 chollosListViewModel.functions.readListChollosBySearch($routeParams.SEARCH);
	 }
	 
	 if ($routeParams.ID!=undefined){
		 chollosListViewModel.functions.readListChollosByShopId($routeParams.ID);
	 }
}])
