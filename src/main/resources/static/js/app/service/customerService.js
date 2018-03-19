openBankingApp.service('customerService', ['$http', function($http) {
    return {
        getCustomer: function (customerId) {
        return $http.get('customers/'+ customerId)
            .then(function(response) {
            return response.data;
        });
    },
        patchCustomer: function(customerId, customerPatch) {
            return $http.patch('customers/'+ customerId, customerPatch)
                .then(function(response) {
                    return response.data;
                })
        }
};
}]);