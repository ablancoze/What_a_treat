angular.module('WhatAtreat', ['ngRoute'])
.config(function($routeProvider){
	$routeProvider
    	.when("/", {
    		controller: "chollosCtrl",
    		controllerAs: "chollosVM",
    		templateUrl: "listOrdersTemplate.html",
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
    	.when("/insertOrder", {
    		controller: "orderHandlerCtrl",
    		controllerAs: "orderHandlerVM",
    		templateUrl: "orderHandlerTemplate.html"
        })
        .when("/editOrder/:ID", {
        	controller: "orderHandlerCtrl",
        	controllerAs: "orderHandlerVM",
        	templateUrl: "orderHandlerTemplate.html"
        })
        .when("/deleteOrder/:ID", {
        	controller: "orderHandlerCtrl",
        	controllerAs: "orderHandlerVM",
        	templateUrl: "orderHandlerTemplate.html"
        });
})