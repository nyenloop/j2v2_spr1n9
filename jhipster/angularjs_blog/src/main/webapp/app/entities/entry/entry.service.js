(function() {
    'use strict';
    angular
        .module('angularjsBlogApp')
        .factory('Entry', Entry);

    Entry.$inject = ['$resource', 'DateUtils'];

    function Entry ($resource, DateUtils) {
        var resourceUrl =  'api/entries/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                        data.date = DateUtils.convertDateTimeFromServer(data.date);
                    }
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    }
})();
