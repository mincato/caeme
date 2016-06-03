'use strict';

// CategoryHotel controller
angular.module('activities').controller('AddActivityCtrl', ['$scope', 'uibDateParser', 'ActivityService','ConfirmationModalService', 'AreasService', 'ParticipationTypesService', 'UserService', '$uibModalInstance', 'moment',
	function($scope, uibDateParser, ActivityService, ConfirmationModalService, AreasService, ParticipationTypesService, UserService, $uibModalInstance, moment) {
        
		$scope.create = function() {
            if ($scope.startDate !== undefined) {
                $scope.activity.startDate = moment($scope.startDate).format('DD/MM/YYYY');    
            }
            if ($scope.endDate !== undefined) {
                $scope.activity.endDate = moment($scope.endDate).format('DD/MM/YYYY');    
            }

			$uibModalInstance.close($scope.activity);
		};

		$scope.cancel = function () {
			$uibModalInstance.dismiss();
		};

        $scope.activity = {};
        $scope.areas = AreasService.query();
        $scope.participationTypes = ParticipationTypesService.query();
        $scope.users = UserService.query();
        
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

        $scope.removeParticipationType = function(participant) {
            participant.participationType = {};
        };


        $scope.addCameParticipant = function() {
            var caemeParticipant = {};
            if ($scope.activity.caemeParticipants === undefined) {
                $scope.activity.caemeParticipants = [];       
            }
            caemeParticipant.organizer = $scope.activity.caemeParticipants.length === 0;
            $scope.activity.caemeParticipants.push(caemeParticipant);
        };
        $scope.addCameParticipant();

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
            if ($scope.activity.otherParticipants === undefined) {
                $scope.activity.otherParticipants = [];
            }
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
                $scope.goToActvities();
            });
            
        };

        $scope.cancel = function () {
            $uibModalInstance.dismiss();
        };
	}

]);