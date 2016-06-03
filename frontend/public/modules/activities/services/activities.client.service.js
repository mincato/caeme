'use strict';

//Activity service used to communicate with Activity REST endpoints
angular.module('activities').factory('ActivityService', ['$resource', 'Configuration',
	function($resource, Configuration) {

		var url = Configuration.serviceContext + 'activities/:id/:operation';
		
		return $resource(url, { id: '@id' }, {
            update: {
                method: 'PUT'
            },
            report: {
                method: 'GET',
                params: {
                	operation: 'search'
                }
            }
        });
	}
]);


//Participation Types service used to communicate with Participation Types REST endpoints
angular.module('activities').factory('ParticipationTypesService', ['$resource', 'Configuration',
	function($resource, Configuration) {

		var url = Configuration.serviceContext + 'activities/participation_types';
		
		return $resource(url);
	}
]);

//Areas service used to communicate with Areas REST endpoints
angular.module('activities').factory('AreasService', ['$resource', 'Configuration',
	function($resource, Configuration) {

		var url = Configuration.serviceContext + 'activities/areas';
		
		return $resource(url);
	}
]);

//User service used to communicate with Users REST endpoints
angular.module('activities').factory('UserService', ['$resource', 'Configuration',
	function($resource, Configuration) {

		var url = Configuration.serviceContext + 'users/:id';
		
		return $resource(url);
	}
]);