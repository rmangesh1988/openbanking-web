openBankingApp.controller('customerController', ['$scope' , 'customerService', function($scope, customerService) {
    $scope.customerId="";

    $scope.engagementOptions = ['DEPOSIT', 'CREDITCARD', 'MORTGAGE'];
    $scope.customerTypeOptions = ['PERSON', 'COMPANY'];

    $scope.getCustomer = function () {
        customerService.getCustomer($scope.customerId).then(function(response) {
            $scope.apiError = null;
            $scope.customer = response;

        }, function(error) {
            $scope.customer = null;
            $scope.apiError = error;
        });
    };

    $scope.patchCustomer = function() {
        var customerPatch = {email : $scope.customer.email, phone: $scope.customer.phone};
        customerService.patchCustomer($scope.customerId, customerPatch)
            .then(function(response) {
                $scope.apiError = null;
                $scope.patched = true;
            }, function(error) {
                $scope.apiError = error;
                $scope.patched = false;
            });
    };

    $scope.dismissSuccess = function () {
        $scope.patched = false;
    }
}]);