/**
 * Created by liuhj3 on 2017/8/25.
 */
'use strict';
var app = angular.module('myApp', ['ngRoute']);
app.config(['$routeProvider', function ($routeProvider) {
    $routeProvider
        .when('/selectgoods', {
            templateUrl: 'selectgoods.html',
        })
        .otherwise({
            templateUrl: 'login.html',
        });

}]);
