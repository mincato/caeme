'use strict';

// Activity controller
angular.module('activities').controller('ActivityController', ['$scope', '$stateParams', '$location', 'ActivityService','$uibModal','ConfirmationModalService',
	function($scope, $stateParams, $location, ActivityService, $uibModal, ConfirmationModalService) {
       
        $scope.init = function() {
            $scope.activities = ActivityService.query();
        };
        
        $scope.removeActivity = function(activity) {
            var confirmation = ConfirmationModalService.open(
                'Eliminar actividad',
                'Desea eliminar la actividad?'
            );


            confirmation.result.then(function () {
                ActivityService.delete({id:activity.id}, function(response) {
                for (var i in $scope.activities) {
                        if ($scope.activities [i] === activity) {
                            $scope.activities.splice(i, 1);
                        }
                    }
                });
            });
        };


        $scope.review = function(activity) {
        	$location.path('actividades/' + activity.id);
        };
        
        $scope.goHome = function() {
            $location.path('#!/');
        };
        
        $scope.toggleAddActivity = function() {
            var modalInstance = $uibModal.open({
                    animation: true,
                    templateUrl: 'modules/activities/views/add-activity.client.view.html',
                    controller: 'AddActivityCtrl',
                    size: 'lg'
            });


            modalInstance.result.then(function (activity) {
                $scope.addActivity(activity);
            });
        };

        $scope.addActivity = function(activity) {
            ActivityService.save(activity, function() {
                $scope.init();
            });
        };

	}
]);