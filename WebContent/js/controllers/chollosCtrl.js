/**
 * 
 */
angular.module('WhatAtreat').controller('chollosCtrl',['chollosFactory','$routeParams','$location',function(chollosFactory,$routeParams,$location){
	
	
	 var chollosViewModel = this;
	 
	 /*Atributos*/
	 chollosViewModel.listChollosHot = [];
	 chollosViewModel.chollo = {};
	 
	 
	 
	 /*Funciones*/
	 chollosViewModel.functions = {
			 
		   		where : function(route){
		   			return $location.path() == route;
		   		},
				
				readChollo : function(id) {
					chollosFactory.getChollos(id).then(function(response) {
						chollosViewModel.chollo = response;
						console.log("Reading treats with id: ", id," Response: ", response);
					}, function(response) {
						console.log("Error reading chollo");
						$location.path('/');
					})
				},
				
				/*Lee los tres chollos mas importantes del dia*/
				readChollosHot : function () {
					chollosFactory.getChollosHot().then(function(response){
						chollosViewModel.listChollosHot = response;
						console.log("Reading three hot chollos", response);
					}, function(response){
						console.log("Error reading three hot chollos");
					})
				},
				
				
				/*Elimina un chollo de la lista de chollos de un usuario*/
				deleteChollo : function() {
					chollosFactory.deleteChollo(id).then(function(response){
						console.log("Deleting chollo with id:",id," Response:", response);
					},function (response){
						console.log("Error deleting chollo");
					})
				}
				
	 }
	 chollosViewModel.functions.readChollosHot();
	 if ($routeParams.ID!=undefined){
		 chollosViewModel.functions.readChollo($routeParams.ID);
	 }
}])
