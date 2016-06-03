'use strict';

// Activity controller
angular.module('activities').controller('ReportActivityController', ['$scope', '$stateParams', '$location', 'ActivityService','$uibModal','ConfirmationModalService','AreasService', 'UserService', 'moment', '$sce',
	function($scope, $stateParams, $location, ActivityService, $uibModal, ConfirmationModalService, AreasService, UserService, moment, $sce) {
        
        $scope.activities = [];
        $scope.participantLabels = [];
        $scope.participantSeries = ['Actividades'];
        $scope.activitiesPerParticipant = [];
        $scope.areasLabels = [];
        $scope.activitiesPerArea = [];
        $scope.monthSeries = ['Actividades'];
        $scope.monthLabels = [];
        $scope.activitiesPerMonth = [];
        $scope.maxSize = 5;
        $scope.itemsPerPage = 50;
        $scope.currentPage = 1;
        $scope.searchingActivities = false;
        $scope.horizontalBarType = 'HorizontalBar';

        $scope.areas = AreasService.query();
        $scope.users = UserService.query();

        $scope.format = 'dd/MM/yyyy';

        var now = new Date();
        now.setFullYear(now.getFullYear() - 1);

        $scope.filters = {    
            startDate: now,
            endDate: new Date(),
            title: null,
            other_participant: null
        };

        $scope.popupStartDate = {
            opened: false
        };

        $scope.popupEndDate = {
            opened: false
        };


        $scope.openStartDate = function() {
            $scope.popupStartDate.opened = true;
        };

        $scope.openEndDate = function() {
            $scope.popupEndDate.opened = true;
        };

        $scope.init = function() {
            $scope.search();
        };

        var buildParams = function() {
            var params = {
                page : $scope.currentPage,
                per_page : $scope.itemsPerPage
            };
            if ($scope.filters.areas !== undefined) {
                params.areas = $scope.filters.areas.id;
            }
            if ($scope.filters.participants !== undefined) {
                params.caeme_users = $scope.filters.participants.id;
            }
            if ($scope.filters.otherParticipant !== null) {
                params.other_participant = $scope.filters.otherParticipant;
            }
            if ($scope.filters.title !== null) {
                params.title = $scope.filters.title;
            }
            if ($scope.filters.startDate !== undefined && $scope.filters.startDate) {
                params.startDate = moment($scope.filters.startDate).format('DD/MM/YYYY');
            }
            if ($scope.filters.endDate !== undefined && $scope.filters.endDate) {
                params.endDate = moment($scope.filters.endDate).format('DD/MM/YYYY');
            }
            return params;
        };

        $scope.search = function() {
            $scope.searchingActivities = true;
            $scope.currentPage = 1;

            var params = buildParams();            
            ActivityService.report(params, function(response) {
                $scope.activitiesPerParticipant = [];
                $scope.participantLabels = [];
                $scope.activitiesPerArea = [];
                $scope.activitiesPerMonth = [];
                $scope.areasLabels = [];
                $scope.activities = response.activities;
                $scope.areasLabels = Object.keys(response.activitiesPerArea);
                for (var i = 0; i < $scope.areasLabels.length; i++) {
                    $scope.activitiesPerArea.push(response.activitiesPerArea[$scope.areasLabels[i]]);
                }
                $scope.participantLabels = Object.keys(response.activitiesPerParticipant);
                var activitiesPerParticipantTemp = [];
                for (var j = 0; j < $scope.participantLabels.length; j++) {
                    activitiesPerParticipantTemp.push(response.activitiesPerParticipant[$scope.participantLabels[j]]);
                }
                $scope.activitiesPerParticipant.push(activitiesPerParticipantTemp);
                $scope.monthLabels = Object.keys(response.activitiesPerMonth);
                var activitiesPerMonthTemp = [];
                for (var k = 0; k < $scope.monthLabels.length; k++) {
                    activitiesPerMonthTemp.push(response.activitiesPerMonth[$scope.monthLabels[k]]);
                }
                $scope.activitiesPerMonth.push(activitiesPerMonthTemp);
                $scope.totalItems = response.totalActivities;
                $scope.searchingActivities = false;
            });
        };

        $scope.getOrganizer = function(activity) {
            for (var i in activity.caemeParticipants) {
                if (activity.caemeParticipants[i].organizer) {
                    return activity.caemeParticipants[i].user.name;
                }
            }
        };

        $scope.pageChanged = function() {
            $scope.searchingActivities = true;
            var params = buildParams();
            ActivityService.report(params, function(response) {
                $scope.activities = response.activities;
                $scope.searchingActivities = false;
            });
        };

        var trusted = [];
        $scope.participantsPopover = function(activity) {

            var html = '<p>Caeme:</p><ul>';
            for (var i = 0; i < activity.caemeParticipants.length; i++) {
                html = html + '<li>' + activity.caemeParticipants[i].user.name + '</li>';
            }
            html = html + '</ul>';

            if (activity.otherParticipants !== undefined && activity.otherParticipants !== null) {
                html = html + '<p>Otros:</p><ul>';
                for (var j = 0; j < activity.otherParticipants.length; j++) {
                    html = html + '<li>' + activity.otherParticipants[j].name + '</li>';
                }
                html = html + '</ul>';    
            }

            trusted[html] || (trusted[html] = $sce.trustAsHtml(html));
            return trusted[html];
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
            ActivityService.save(activity, function(response) {
                $scope.activities.splice(0, 0, response);
            });
        };

	}
]);