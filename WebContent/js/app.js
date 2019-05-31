angular.module('WhatAtreat', ['ngRoute']).config(function($routeProvider){
	$routeProvider
    	.when("/", {
    		controller: "cholloList",
    		controllerAs: "cholloListVM",
    		templateUrl: "ListChollos.html",
    		resolve: {
    			// produce 500 miliseconds (0,5 seconds) of delay that should be enough to allow the server
    			//does any requested update before reading the orders.
    			// Extracted from script.js used as example on https://docs.angularjs.org/api/ngRoute/service/$route
    			delay: function($q, $timeout) {
    			var delay = $q.defer();
    			$timeout(delay.resolve, 200);
    			return delay.promise;
    			}
    		}
    	})
    	
    	.when ("/chollo/:ID" , {
    		controller: "cholloCtrl",
    		controllerAs: "chollosVM",
    		templateUrl: "CholloPag.html"
    	})
    	
    	.when ("/shop/:ID" , {
    		controller: "userCtrl",
    		controllerAs: "userVM",
    		templateUrl: "ChollosUsuario.html"
    	})
    	
    	.when ("/user" , {
    		controller: "userCtrl",
    		controllerAs: "userVM",
    		templateUrl: "CuentaUsuario.html"
    	})
    	.when ("/user/:ID" , {
    		controller: "userCtrl",
    		controllerAs: "userVM",
    		templateUrl: "ChollosUsuario.html"
    	})
    	
    	.when ("/user/settings/:ID" , {
    		controller: "userCtrl",
    		controllerAs: "userVM",
    		templateUrl: "SettingUser.html"
    	});
})