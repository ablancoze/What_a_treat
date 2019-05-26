/**
 * 
 */

angular.module('WhatAtreat').controller('chollosCtrl',['chollosFactory','$routeParams','$location',function(chollosFactory){
	
	
	 var chollosViewModel = this;
	 
	 /*Atributos*/
	 chollosViewModel.listChollosHot = [];
	 chollosViewModel.chollo = {};
	 
	 
	 
	 /*Funciones*/
	 chollosViewModel.functions = {
			 
		   		where : function(route){
		   			return $location.path() == route;
		   		},
				
				readChollo : function() {
					chollosFactory.getChollos(id).then(function(response) {
						chollosViewModel.chollo = response;
						console.log("Reading all the treats: ", response);
					}, function(response) {
						console.log("Error reading treats");
					})
				},
				
				/*Lee los tres chollos mas importantes del dia*/
				readChollosHot : function () {
					chollosFactory.getChollosHot().then(function(response){
						chollosViewModel.listChollosHot = response;
						console.log("Reading three hot chollos", response);
					}, function(response){
						console.log("Error reading treats");
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
	 
	 chollosViewModel.functions.readChollo();
	 
	 chollosViewModel.functions.readChollosHot();
	 
	 chollosViewModel.functions.deleteChollo();
}])
