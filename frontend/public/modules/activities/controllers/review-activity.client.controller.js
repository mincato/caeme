'use strict';

// Activity controller
angular.module('activities').controller('ReviewActivityController', ['$scope', 'uibDateParser', '$stateParams', '$location', 'ActivityService','$uibModal','ConfirmationModalService', 'AreasService', 'ParticipationTypesService', 'UserService', 'moment',
	function($scope, uibDateParser, $stateParams, $location, ActivityService, $uibModal, ConfirmationModalService, AreasService, ParticipationTypesService, UserService, moment) {
        
        $scope.isNotValid = function(activity) {
            if (activity === undefined) return true;
            if (activity.title === undefined) return true;
            return activity.title.length === 0;
        };
        
        $scope.saveChanges = function() {
            var confirmation = ConfirmationModalService.open(
                'Confirmar cambios',
                'Desea guardar los cambios?'
            );


            confirmation.result.then(function () {
                $scope.update();
            });
        };        

        $scope.update = function() {
            $scope.showUpdateError = false;
            $scope.showUpdateSuccess = false;
            if ($scope.startDate !== undefined) {
                $scope.activity.startDate = moment($scope.startDate).format('DD/MM/YYYY');    
            }
            if ($scope.endDate !== undefined) {
                $scope.activity.endDate = moment($scope.endDate).format('DD/MM/YYYY');    
            } 
            $scope.activity.$update(function(response) {
                    $scope.showUpdateSuccess = true;
                    $scope.findOne();
                }, function() {
                    $scope.showUpdateError = true;
                });
        };

        $scope.remove = function() {
            $scope.showDeleteError = false;
            var confirmation = ConfirmationModalService.open(
                'Eliminar actividad',
                'Desea eliminar la actividad?'
            );


            confirmation.result.then(function () {
                $scope.activity.$delete(function(response) {
                    $scope.goToActvities();
                }, function() {
                    $scope.showDeleteError = true;
                });
            });

        };

        $scope.removeCaemeParticipant = function(participant) {
             var confirmation = ConfirmationModalService.open(
                'Quitar participante',
                'Desea quitar el participante?'
            );


            confirmation.result.then(function () {
                for (var i in $scope.activity.caemeParticipants) {
                    if ($scope.activity.caemeParticipants [i] === participant) {
                        $scope.activity.caemeParticipants.splice(i, 1);
                        if (participant.organizer && $scope.activity.caemeParticipants.length > 0) {
                           $scope.activity.caemeParticipants[0].organizer = true;
                        }   
                    }
                }
            });
            
        };

        $scope.removeParticipationType = function(participant) {
            participant.participationType = {};
        };


        $scope.removeOtherParticipant = function(participant) {
             var confirmation = ConfirmationModalService.open(
                'Quitar participante',
                'Desea quitar el participante?'
            );


            confirmation.result.then(function () {
                for (var i in $scope.activity.otherParticipants) {
                    if ($scope.activity.otherParticipants [i] === participant) {
                        $scope.activity.otherParticipants.splice(i, 1);
                    }
                }
            });
            
        };

        $scope.findOne = function() {
			$scope.activity = ActivityService.get({
				id: $stateParams.activityId
			}, function() {
                $scope.startDate = uibDateParser.parse($scope.activity.startDate, $scope.format);
                $scope.endDate = uibDateParser.parse($scope.activity.endDate, $scope.format);
                $scope.areas = AreasService.query();
                $scope.participationTypes = ParticipationTypesService.query();
                $scope.users = UserService.query();
                for (var i in $scope.activity.caemeParticipants) {
                    if ($scope.activity.caemeParticipants[i].organizer) {
                         $scope.organizer = $scope.activity.caemeParticipants[i];
                    }
                }
            });
            
		};

        $scope.addCameParticipant = function() {
            var caemeParticipant = {};
            if ($scope.activity.caemeParticipants === undefined) {
                $scope.activity.caemeParticipants = [];
            }
            caemeParticipant.organizer = $scope.activity.caemeParticipants.length === 0;
            $scope.activity.caemeParticipants.push(caemeParticipant);
        };

        $scope.updateOrganizer = function(participant) {
            for (var i in $scope.activity.caemeParticipants) {
                $scope.activity.caemeParticipants[i].organizer = $scope.activity.caemeParticipants[i] === participant;
            }
        };

        $scope.addOtherParticipant = function() {
            var otherParticipant = {
                name : '',
                participationType : { participationType : ''},
                observation : ''
            };
            $scope.activity.otherParticipants.push(otherParticipant);
        };

        $scope.format = 'dd/MM/yyyy';

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

        $scope.back = function() {
            var confirmation = ConfirmationModalService.open(
                'Volver',
                'Desea volver a la pagina de resultados? Asegurese de haber guardados los cambios'
            );

            confirmation.result.then(function () {
                $scope.goToActvitiesReport();
            });
            
        };
        
        $scope.goToActvitiesReport = function() {
            $location.path('actividades/reportes');
        };
        
	}
]);