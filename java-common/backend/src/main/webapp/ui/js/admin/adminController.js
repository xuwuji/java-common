var adminApp = angular.module('admin', ['ngRoute', 'ckeditor', 'ngSanitize']);


adminApp.config(['$routeProvider', '$locationProvider', function ($routeProvider, $locationProvider) {
    $routeProvider.
    when('/', {
        templateUrl: './template/admin/write.html',
        controller: 'writeController'
    });
}]);

adminApp.controller('writeController', ['$scope', '$routeParams', '$sce', function ($scope, $routeParams, $sce) {
    $scope.options = {
        language: 'en',
        allowedContent: true,
        entities: false
    };

    $scope.content = $sce.trustAsHtml($scope.content);


    $scope.$watch('content', function (oldV, newV, scope) {
        console.log($scope.content);
    }, true)

}]);
