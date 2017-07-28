

var _get_rsc_default_methods = function(parent_url){
	var pre = "/admin/"
	return {
		'save' : {url : pre +  parent_url + '/save', method : 'POST'},
	    'update' : {url : pre + parent_url + '/update/:id', method : 'POST'},
		'delete' : {url : pre + parent_url + '/delete/:id', method : 'DELETE'},
	    'paged' : {url : pre + parent_url + '/paged/:limit/:page', method : 'GET'},
		'query' : {url : pre + parent_url , method : 'GET', headers : {'Accept' : 'application/x-spring-data-verbose+json'}, isArray :true}
	}
}	

adminAppSvcs.factory('MemberRsc', function($resource){
	var url = 'members';
	var default_methods = _get_rsc_default_methods(url);
	return $resource(url, {		
	}, default_methods);
});


adminAppSvcs.factory('ListingRsc', function($resource){
	var url = 'listings';
	var default_methods = _get_rsc_default_methods(url);
	return $resource(url, {		
	}, default_methods);
});



adminAppSvcs.factory('GenCrudView', function(){
	
	return function($scope, cb){

		$scope.viewVars = {
				list : true
		};
		
		$scope.switchView = function(val){
			angular.forEach($scope.viewVars, function(v, k){
				delete $scope.viewVars[k];
			});
			$scope.viewVars[val] = true;
			if(cb){
				cb(val);
			}
		};	
		
	}
	
});


adminAppSvcs.factory('GenCrudMethods', function(AlertMessageSvc){
	
	return function($scope, rsc, cbs){

		$scope.list = [];
		$scope.newItem = {};
		
		rsc.query(function(data){
			$scope.list = data;
		})
		
		$scope.save = function(item){
			rsc.save(item
			, function (data){
				$scope.newItem = {};
				$scope.list.push(data)
				AlertMessageSvc.alert('Saved Successfully')
			});
		}
		
		$scope.update = function(item){
			rsc.update(item
			, function (data){
				AlertMessageSvc.alert('Saved Successfully')
			});
		}
		
		
		$scope.del = function(item){
			if(item == $scope.editItem){
				delete $scope.editItem;
			}
			var def = AlertMessageSvc.confirm('Are you sure you want to delete?');
			def.then(function(){
				rsc.delete({id : item.id}
				, function (data){
					for(var i = 0; i < $scope.list.length; i++){
						if(item == $scope.list[i]){
							$scope.list.splice(i, 1);
							AlertMessageSvc.alert('Deleted Successfully');
							break;
						}
					}
				});
				
			});
		}
		
		$scope.create = function(){
			$scope.newItem = {};		
			$scope.switchView('create');
		}
		
		$scope.edit= function(item){
			$scope.editItem = item;
			$scope.switchView('edit');
		}	
	}
	
});

adminAppSvcs.factory('FormValidator', function(){
	
	var _service = function(){
		
		this.req = function(val){
			return !!val;
		}
				
		this.email = function(){
			return /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/.test(val);
		}
	}
	
	return new _service(); 
});

			
adminAppSvcs.factory('RegistrationRsc', function($resource){
	return $resource(url, {}, {
		'resendVerifyEmail' : {url : '/resend/:id', method : 'GET'},
	});
});
