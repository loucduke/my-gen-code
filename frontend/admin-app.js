
var adminAppCtrl = angular.module('adminAppControllers', []);
var adminAppSvcs = angular.module('adminAppServices', []);
var adminAppDirs = angular.module('adminAppDirectives', []);
var adminAppFltr = angular.module('adminAppFilters', []);

var adminApp = angular.module('adminApp', [
    'ngResource',
    'ui.router',
    'adminAppControllers',
    'adminAppServices',
    'adminAppDirectives',
    'adminAppFilters',
    'ngMap'
    ]);

adminApp.config(function ($stateProvider){
	
	$stateProvider.state({
		name : 'members',
		url : '/members',
		templateUrl : '/partials/crud/members',
		controller : 'MembersCtrl'
	});	
	

	$stateProvider.state({
		name : 'listings',
		url : '/listings',
		templateUrl : '/partials/crud/listings',
		controller : 'ListingsCtrl'
	});	
	
});


