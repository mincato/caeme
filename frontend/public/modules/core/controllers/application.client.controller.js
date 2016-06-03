'use strict';

angular.module('core').controller('ApplicationController', ['$scope',
	function($scope) {
		
        $scope.messages = {};
        
        $scope.$on('showMessage', function(event, message) {
            $scope.messages.info = message;
        });
        
        $scope.$on('showError', function(event, error) {
            $scope.messages.danger = error;
        });

        $scope.$on('showWarning', function(event, warning) {
            $scope.messages.warning = warning;
        });

        $scope.$on('showSuccess', function(event, success) {
            $scope.messages.success = success;
        });        
	}
]);