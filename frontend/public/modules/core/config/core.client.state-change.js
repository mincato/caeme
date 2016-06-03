'use strict';

angular.module('core').run(function ($rootScope, $window) {
	$rootScope.$on('$stateChangeStart', function (event, next) {
		$window.scrollTo(0, 0);
	});
});