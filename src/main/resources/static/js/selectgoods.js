/**
 * Created by liuhj3 on 2017/8/25.
 */

app.controller('selectgoods', function ($scope, $http, $location) {
    console.log('selectgoods');
    $scope.username = "jack";
    $scope.productArray =
        [{"productName": "长城红酒", "productPrice": 100, "productNum": 0},
            {"productName": "坚果礼盒", "productPrice": 80, "productNum": 0},
            {"productName": "粗粮礼盒", "productPrice": 60, "productNum": 0},
            {"productName": "黑猪肉", "productPrice": 200, "productNum": 0}];
    $scope.totalPrice = 0;
    var priceLimited = 1000;

    for (var i = 0; i < $scope.productArray.length; i++) {
        $scope.productArray[i].indexNo = i;
    }

    $scope.minusClick = function (index) {
        if ($scope.productArray[index].productNum > 0) {
            $scope.productArray[index].productNum--;
            $scope.totalPrice = $scope.totalPrice - $scope.productArray[index].productPrice;
        }

    }
    $scope.plusClick = function (index) {
        $scope.productArray[index].productNum++;
        $scope.totalPrice = $scope.totalPrice + $scope.productArray[index].productPrice;

    }
    $scope.submit = function () {
        if ($scope.totalPrice == 0) {
            alertWithStyle("请选择商品数量");
        }
        else if (typeof($scope.pickaddress) == "undefined" || typeof($scope.pickdate) == "undefined") {
            alertWithStyle("请选择提取地点和时间段");
        }
        else if($scope.totalPrice > priceLimited){
            alertWithStyle("选择的总价大于" + priceLimited);
        }
        else {
            var confirmInfo = "您选择了:<br>";
            for (var i = 0; i < $scope.productArray.length; i++) {
                confirmInfo = confirmInfo + $scope.productArray[i].productName + "  " + $scope.productArray[i].productNum + "<br>";
            }
            confirmInfo = confirmInfo + "总价:" + $scope.totalPrice + "<br>";
            confirmInfo = confirmInfo + "提取地点:" + $scope.pickaddress + "<br>";
            confirmInfo = confirmInfo + "提取时间:" + $scope.pickdate + "<br>";

            confirm(confirmInfo);
        }
    }

    var index;

    function showLoader() {
        index = layer.open({
            type: 2,
            shadeClose: false
        });
    }

    function hideLoader() {
        layer.close(index);
    }

    function confirm(string) {
        layer.open({
            content: string
            , btn: ['确定', '返回']
            , yes: function (index) {
                //数据组合提交逻辑
                alertWithStyle("提交成功")
                $scope.$apply(function () {
                    $scope.isDisabled = true;
                });

            }
        });
    }

//重写alert样式
    function alertWithStyle(msg) {
        layer.open({
            content: msg,
            style: 'background-color:#09C1FF; color:#fff; border:none;',
            time: 2
        });
    }
})


