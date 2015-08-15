'use strict';

angular.module('terminal.module', [])
  .directive('compileData', ['$compile', function($compile) {
      return {
        restrict: 'A',
        link: function(scope, element, attrs) {
          scope.$watch('htmlSafeData', function(data) {
            if (data !== undefined) {
              element.html('');
              data = $compile(data)(scope);
              element.append(data);
            }
          });
        }
      };
    }]);

