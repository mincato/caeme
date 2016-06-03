'use strict';

//Setting up route
angular.module('activities').config(['$stateProvider',
	function($stateProvider) {
		// Destination Mapping state routing
		$stateProvider.
		/*state('listActivities', {
			url: '/actividades',
			templateUrl: 'modules/activities/views/activities.client.view.html'
		}).*/
		state('reportActivities', {
			url: '/actividades/reportes',
			templateUrl: 'modules/activities/views/activities-report.client.view.html'
		}).
		state('reviewActivity', {
			url: '/actividades/:activityId',
			templateUrl: 'modules/activities/views/review-activity.client.view.html'
		});
		
	}
]);