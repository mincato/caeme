'use strict';

angular.module('core').service('Configuration', [ '$location', function($location) {

	var serviceContext = 'http://' + $location.host() + ':7070/activity-report/services/';
    
    return {
        serviceContext : serviceContext
    };

}]);


