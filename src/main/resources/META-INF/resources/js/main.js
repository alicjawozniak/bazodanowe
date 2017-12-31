angular.module('empRegApp', [])
    .controller('home', ['$http', '$scope', function ($http, $scope) {


        $scope.showSingle = function (id) {
            $scope.view = 'single';
            $http({
                method: 'GET',
                url: '/' + id
            })
                .then(function (response) {
                    console.log(response);
                    $scope.employee = response.data;

                }, function (error) {
                    console.log(error);
                });
        };


        $scope.saveEmployee = function () {

            if ($scope.employee.firstName == null
                || $scope.employee.firstName == ''
                ||$scope.employee.lastName == null
                || $scope.employee.lastName == ''
                || $scope.employee.email == null
                || $scope.employee.email == '')
            {
                var x = document.getElementsByClassName("custom-required");
                var i;
                for (i = 0; i < x.length; i++) {

                    if (x[i].value == null
                    || x[i].value == ''
                    || x[i].value === ''){
                        x[i].style.backgroundColor = "orangered";

                    }
                }
                window.scrollTo(0,0);
                alert("Please fill required fields");
            }
            else {
                $http({
                    method: 'POST',
                    url: '/api/employee',
                    data: JSON.stringify($scope.employee)
                })
                    .then(function (response) {
                        console.log(response);

                    }, function (error) {
                        console.log(error);
                    });

                var x = document.getElementsByClassName("custom-required");
                var i;
                for (i = 0; i < x.length; i++) {
                    x[i].style.backgroundColor = "white";
                }
                $scope.showList();
            }

        };

        $scope.createEmployee = function () {
            $scope.view = 'single';
            $scope.employee = {};
        };


        $scope.searchSorted = function (sort) {
            $scope.sort = sort;
            $scope.showList();
        };


        $scope.pageNumber = 0;

        $scope.sort = "id";

        $scope.employeeFilter = {
            "id": '',
            "firstName": '',
            "lastName": '',
            "email": '',
            "phoneNo": '',
            "pesel": '',
            "addressCity": '',
            "positionId": '',
            "companyCarRegistrationNo": '',
            "bankAccountIban": '',
            "companyBranchName": ''
        };

        $scope.showList = function () {
            $scope.view = 'list';
            $scope.params = {};

            $scope.size = 10;

            $scope.page = {};

            $http({
                method: 'POST',
                url: '/api/employees?page=' + $scope.pageNumber + '&size=' + $scope.size + "&sort=" + $scope.sort,
                data: JSON.stringify($scope.employeeFilter)
            })
                .then(function (response) {
                    console.log(response);
                    $scope.page = response.data;
                    $scope.allEmployees = $scope.page.content;

                }, function (error) {
                    console.log(error);
                });

        };

        $scope.getNextPage = function () {
            $scope.pageNumber++;
            $scope.showList();
        };

        $scope.getPrevPage = function () {
            $scope.pageNumber--;
            $scope.showList();
        };

        $scope.showList();
    }]);