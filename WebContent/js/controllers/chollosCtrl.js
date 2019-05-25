/**
 * 
 */

angular.module('WhatAtreat').controller('chollosCtrl',['chollosFactory',function(chollosFactory){
	
	
	 var chollosViewModel = this;
	 this.chollos = [];  // es lo mismo que lo de abajo.
	 
	 chollosViewModel.chollosHot = [];
	 
		 chollosViewModel.functions = {
				 readChollos : function () {
					 chollosFactory.getChollos().then(function(response){
						 console.log("Reading all the treats: ", response);
						 chollosViewModel.chollos = response;
					 },function(response){
						 console.log("Error reading treats");
					 })
				 },
		 
		 readChollosHot : function () {
			 chollosFactory.getChollosHot().then(function(response){
				 console.log("Reading three hot chollos", response);
				 chollosViewModel.chollosHot = response;
			 },function(response){
				 console.log("Error reading treats");
			 })
		 }
	 }		 
	 
	 chollosViewModel.functions.readChollosHot();
	 
	 chollosViewModel.functions.readChollos();
}])
