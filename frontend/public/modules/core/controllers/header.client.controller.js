'use strict';

angular.module('core').controller('HeaderController', ['$scope', 'Menus', '$location',
	function($scope, Menus, $location) {
		
		$scope.isCollapsed = false;
		$scope.menu = Menus.getMenu('topbar');
        
		$scope.toggleCollapsibleMenu = function() {
			$scope.isCollapsed = !$scope.isCollapsed;
		};

		// Collapsing the menu after navigation
		$scope.$on('$stateChangeSuccess', function() {
			$scope.isCollapsed = false;
		});
	}
]);