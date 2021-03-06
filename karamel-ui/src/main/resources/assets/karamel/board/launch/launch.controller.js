angular.module('main.module')
  .controller('launch.controller', ['$log', '$scope', '$modalInstance', 'info', function($log, $scope, $modalInstance, info) {

      function _initScope(scope) {
        scope.credentialsHolderMap = {};
        scope.summaryValid = true;
        _setUpHolderMap(scope.credentialsHolderMap);
      }

      function _setUpHolderMap(map) {
        if (info.cluster.hasEc2()) {
          map[info.cluster.ec2.getMapKey()] = info.cluster.ec2;
        }
        if (info.cluster.hasGce()) {
          map[info.cluster.gce.getMapKey()] = info.cluster.gce;
        }
        map[info.cluster.sshKeyPair.getMapKey()] = info.cluster.sshKeyPair;
      }

      $scope.close = function() {
        $modalInstance.close();
      };

      function _checkSummaryValid(map) {
        var result = true;
        for (var name in map) {
          var obj = map[name];
          if (obj instanceof Credentials) {
            $log.info(obj.getIsValid());
            result = result && obj.getIsValid();
          }
        }
        return result;
      }

      $scope.credentialsFormSubmit = function() {
        if (_checkSummaryValid($scope.credentialsHolderMap)) {
          $log.info('All checks passed, capturing details.');
          $modalInstance.close(info.cluster);
        }
        else {
          $scope.summaryValid = false;
        }
      };
      _initScope($scope);
    }]);