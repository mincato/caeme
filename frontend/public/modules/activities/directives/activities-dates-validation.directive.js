'use strict';

angular.module('activities').directive('dateHigherOrEqualThan', ['moment', function(moment) {
    
    var link = function(scope, elm, attrs, ctrl) {
        
        function validate(date) {

            var compareDate = attrs.dateHigherOrEqualThan.replace(/"/g,'');

            if(date === undefined || date === null || date === '' || compareDate === undefined || compareDate === null || compareDate === ''){
                ctrl.$setValidity('dateHigherOrEqualThan', true);
            }
            else {
                ctrl.$setValidity('dateHigherOrEqualThan', moment(date, 'DD/MM/YYYY').isSameOrAfter(moment(compareDate)));    
            }
            
            return date;

        }

        ctrl.$parsers.unshift(validate);

        attrs.$observe('dateHigherOrEqualThan', function(compareDate){
            // Whenever the comparison model changes we'll re-validate
            return validate(ctrl.$viewValue);
        });

    };

    return {
        require: 'ngModel',
        link: link
    };    
}]);

angular.module('activities').directive('dateLowerOrEqualThan',['moment', function(moment) {

     var link = function(scope, elm, attrs, ctrl) {
        
        function validate(date) {

            var compareDate = attrs.dateLowerOrEqualThan.replace(/"/g,'');

            if(date === undefined || date === null || date === '' || compareDate === undefined || compareDate === null || compareDate === ''){
                ctrl.$setValidity('dateLowerOrEqualThan', true);
            } else {
                ctrl.$setValidity('dateLowerOrEqualThan', moment(date, 'DD/MM/YYYY').isSameOrBefore(moment(compareDate)));
            }

            return date;

        }

        ctrl.$parsers.unshift(validate);

        attrs.$observe('dateLowerOrEqualThan', function(compareDate){
            // Whenever the comparison model changes we'll re-validate
            return validate(ctrl.$viewValue);
        });

    };

    return {
        require: 'ngModel',
        link: link
    };

}]);
