'use strict';

angular.module('core').service('Configuration', [ '$location', function($location) {

	var serviceContext = 'http://' + $location.host() + ':8080/activity-report/services/';
    
    return {
        serviceContext : serviceContext
    };

}]);


