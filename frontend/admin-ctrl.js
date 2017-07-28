adminAppCtrl.controller('MembersCtrl', function($scope, MemberRsc, GroupsRsc, GenCrudMethods, GenCrudView){
	
	GenCrudView($scope);
	GenCrudMethods($scope, MemberRsc)
	
	
});


adminAppCtrl.controller('ListingsCtrl', function($scope, $http, ListingRsc, GenCrudMethods, GenCrudView){
	
	GenCrudView($scope);
	GenCrudMethods($scope, ListingRsc)
	
});


adminAppCtrl.controller('GroupsCtrl', function($scope, GroupsRsc, GenCrudMethods, GenCrudView){
	
	GenCrudView($scope);
	GenCrudMethods($scope, GroupsRsc)
	
});


adminAppCtrl.controller('CategoriesCtrl', function($scope, CategoriesRsc, GenCrudMethods, GenCrudView){
	
	GenCrudView($scope);
	GenCrudMethods($scope, CategoriesRsc)

	$scope.create = function(){
		$scope.newItem = { categoryInfo:{}};		
		$scope.switchView('create');
	}	

});

