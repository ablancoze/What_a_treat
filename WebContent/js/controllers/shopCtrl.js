
angular.module('WhatAtreat').controller('shopCtrl',['shopFactory',function(shopFactory){
	
	
	 var shopViewModel = this;
	 
	 /*Atributos*/
	 shopViewModel.shopList = [];
	 shopViewModel.shop = {};
	 
	 /*Funciones*/
	 shopViewModel.functions = {
			
				/* Leer todos los chollos de la base de datos */
				readListShops : function() {
					shopFactory.getShops()
						.then(function(response) {
							shopViewModel.shopList = response;
							console.log("Reading all the shops:");
						}, function(response) {
							console.log("Error reading treats");
						})
				}
	 }
	 
	 shopViewModel.functions.readListShops();

}])
