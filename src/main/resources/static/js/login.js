/**
 * Created by liuhj3 on 2017/8/25.
 */
app.controller('login',function ($scope, $http, $location){
    console.log('login');
    $scope.login = function () {
        $location.url("/selectgoods");
    }

})
