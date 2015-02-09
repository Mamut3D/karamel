/**
 * Created by babbarshaer on 2014-10-29.
 */

// This module deals with the core caramel services.

/*jshint undef: false, unused: false, indent: 2*/
/*global angular: false */

'use strict';

angular.module('coreApp', [])
    
    .controller('CommandCenterController',['$log','$scope','CaramelCoreServices', function($log, $scope, CaramelCoreServices){
        
        function initScope(scope){
            
            scope.commandObj = {
                commandName: null,
                commandResult: null
            }
        }
        
        
        $scope.processCommand = function(commandName){
            
            var obj = {
                command : commandName
            };
            
            $log.info("Process Command Called with: " + angular.toJson(obj));
            CaramelCoreServices.processCommand(obj)

                .success(function(data){
                    $scope.commandObj.commandResult = data.command;
                })
                .error(function(data){
                    $log.info(data);
                    $log.info('Core -> Unable to process command: ' + commandName);
                })
        };

        initScope($scope);
    }])

    .service('CaramelCoreServices', ['$log', '$http', '$location', function($log, $http, $location) {

        // Return the promise object to the users.
        var _getPromiseObject = function(method, url, contentType, data) {

          var promiseObject = $http({
            method: method,
            url: url,
            headers: {'Content-Type': contentType},
            data: data
          });

          return promiseObject;
        };

        /* window.location.hostname for the webserver  */

        var _defaultHost = 'http://' + $location.host() + ':9090/api';
        var _defaultContentType = 'application/json';


        // Services interacting with the caramel core.
        return{
          // Based on the object passed get the complete url.
          getCompleteYaml: function(json) {

            var method = 'PUT';
            var url = _defaultHost.concat("/fetchYaml");

            return _getPromiseObject(method, url, _defaultContentType, json);

          },
          getCleanYaml: function(json) {

            var method = 'PUT';
            var url = _defaultHost.concat("/cleanYaml");

            return  _getPromiseObject(method, url, _defaultContentType, json);

          },
          getJsonFromYaml: function(ymlString) {

            var method = 'PUT';
            var url = _defaultHost.concat("/fetchJson");

            return _getPromiseObject(method, url, _defaultContentType, ymlString);


          },
          getCookBookInfo: function(requestData) {

            var method = 'PUT';
            var url = _defaultHost.concat("/fetchCookbook");
            return _getPromiseObject(method, url, _defaultContentType, requestData);
          },
          loadSshKeys: function() {
            var method = 'PUT';
            var url = _defaultHost.concat("/loadSshKeys");
            return _getPromiseObject(method, url, _defaultContentType);
          },
          generateSshKeys: function() {
            var method = 'PUT';
            var url = _defaultHost.concat("/generateSshKeys");
            return _getPromiseObject(method, url, _defaultContentType);
          },
          loadCredentials: function() {
            var method = 'PUT';
            var url = _defaultHost.concat("/loadCredentials");
            return _getPromiseObject(method, url, _defaultContentType);
          },
          validateCredentials: function(providerInfo) {
            var method = 'PUT';
            var url = _defaultHost.concat("/validateCredentials");
            return _getPromiseObject(method, url, _defaultContentType, providerInfo);
          },
          startCluster: function(clusterJson) {
            var method = 'PUT';
            var url = _defaultHost.concat("/startCluster");
            return _getPromiseObject(method, url, _defaultContentType, clusterJson);
          },
          viewCluster: function(clusterNameJson) {
            var method = 'PUT';
            var url = _defaultHost.concat("/viewCluster");
            return _getPromiseObject(method, url, _defaultContentType, clusterNameJson);
          },
          pauseCluster: function(clusterName) {
            var method = 'PUT';
            var url = _defaultHost.concat("/pauseCluster");
            return _getPromiseObject(method, url, _defaultContentType, clusterName);
          },
          stopCluster: function(clusterName) {
            var method = 'PUT';
            var url = _defaultHost.concat("/stopCluster");
            return _getPromiseObject(method, url, _defaultContentType, clusterName);
          },
          
          commandSheet: function(){
            var method = 'GET';
            var url = _defaultHost.concat("/getCommandSheet");
            return _getPromiseObject(method, url, _defaultContentType);
          },

          processCommand: function(commandName){
            var method = 'PUT';
            var url = _defaultHost.concat("/processCommand");
            return _getPromiseObject(method, url, _defaultContentType, commandName);
          }  
            
        }

      }]);

