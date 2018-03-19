var openBankingApp = angular.module('openBankingApp',['ngRoute','ngMessages']);

openBankingApp.config(['$routeProvider', function($routeProvider){
    $routeProvider
        .when("/", {
            templateUrl: 'view/customer-search.html',
            controller: 'customerController'
        });
}]);