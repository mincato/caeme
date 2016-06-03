'use strict';

angular.module('core').controller('ConfirmationModalController', ['$scope', '$uibModalInstance', 'modalData',
    function ($scope, $uibModalInstance, modalData) {

        $scope.modalTitle = modalData.title;
        $scope.modalBody = modalData.body;

        $scope.cancel = function () {
            $uibModalInstance.dismiss('cancel');
        };

        $scope.confirm = function() {
        	$uibModalInstance.close('confirm');
        };
    }
]);
