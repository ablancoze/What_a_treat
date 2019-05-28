angular.module('WhatAtreat', ['ngRoute']).config(function($routeProvider){
	$routeProvider
    	.when("/", {
    		controller: "cholloList",
    		controllerAs: "cholloListVM",
    		templateUrl: "ListChollosTemplate.html",
    		resolve: {
    			// produce 500 miliseconds (0,5 seconds) of delay that should be enough to allow the server
    			//does any requested update before reading the orders.
    			// Extracted from script.js used as example on https://docs.angularjs.org/api/ngRoute/service/$route
    			delay: function($q, $timeout) {
    			var delay = $q.defer();
    			$timeout(delay.resolve, 500);
    			return delay.promise;
    			}
    		}
    	})
    	
    	.when ("/chollo/:ID" , {
    		controller: "chollosCtrl",
    		controllerAs: "chollosVM",
    		templateUrl: "CholloPag.html"
    	})
    	
    	
    	.when("/user", {
    		controller: "orderHandlerCtrl",
    		controllerAs: "orderHandlerVM",
    		templateUrl: "CuentaUsuario.html"
        })
        
        .when("/userSeting", {
    		controller: "orderHandlerCtrl",
    		controllerAs: "orderHandlerVM",
    		templateUrl: "CuentaUsuario.html"
        })
        
        .when("/cholloNew", {
        	controller: "chollosCtrl",
        	controllerAs: "chollosVM",
        	templateUrl: "orderHandlerTemplate.html"
        })
        
        .when("/deleteOrder/:ID", {
        	controller: "orderHandlerCtrl",
        	controllerAs: "orderHandlerVM",
        	templateUrl: "orderHandlerTemplate.html"
        });
})