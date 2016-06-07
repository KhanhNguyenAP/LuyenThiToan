/**
 * Created by Ariana Nhu on 26/05/2016.
 */
app.controller('GraphCtrl', function($scope) {
    $scope.types = [
        {name: "Hàm bậc nhất", id: "bac1"},
        {name: "Hàm bậc 2", id: "bac2"},
        {name: "Hàm bậc 3", id: "bac3"},
        {name: "Trùng phương", id: "bac4"}
    ];
    $scope.algebraType = "bac1";
    $scope.equation = "";
    $scope.computed = false;
    $scope.TXD = "\\mathbb{R}";
    $scope.pointOx = "";
    $scope.nghiem = [];

    $scope.input = function () {
        $scope.equation = "";
        if ($scope.algebraType == "bac1")
        {
            $scope.paramA = 0;
            $scope.paramD = 0;
            if(typeof $scope.paramB == "number"){
                $scope.equation = $scope.paramB + "x";
            }
            if(typeof $scope.paramC == "number"){
                if($scope.paramC < 0)
                    $scope.equation += $scope.paramC;
                else
                    $scope.equation += "+" +$scope.paramC;
            }
        }
        else if($scope.algebraType == "bac2"){
            $scope.paramD = 0;
            if(typeof $scope.paramA == "number"){
                $scope.equation = $scope.paramA + "x^2";
            }
            if(typeof $scope.paramB == "number"){
                if($scope.paramB < 0)
                    $scope.equation += $scope.paramB + "x";
                else
                    $scope.equation += "+" + $scope.paramB + "x";
            }
            if(typeof $scope.paramC == "number"){
                if($scope.paramC < 0)
                    $scope.equation += $scope.paramC;
                else
                    $scope.equation += "+" +$scope.paramC;
            }
        }
        else if($scope.algebraType == "bac3"){
            if(typeof $scope.paramA == "number"){
                $scope.equation = $scope.paramA + "x^3";
            }
            if(typeof $scope.paramB == "number"){
                if($scope.paramB < 0)
                    $scope.equation += $scope.paramB + "x^2";
                else
                    $scope.equation += "+" + $scope.paramB + "x^2";
            }
            if(typeof $scope.paramC == "number"){
                if($scope.paramC < 0)
                    $scope.equation += $scope.paramC + "x";
                else
                    $scope.equation += "+" + $scope.paramC + "x";
            }
            if(typeof $scope.paramD == "number"){
                if($scope.paramD < 0)
                    $scope.equation += $scope.paramD;
                else
                    $scope.equation += "+" + $scope.paramD;
            }
        }
        else if($scope.algebraType == "bac4"){
            $scope.paramD = 0;
            if(typeof $scope.paramA == "number"){
                $scope.equation = $scope.paramA + "x^4";
            }
            if(typeof $scope.paramB == "number"){
                if($scope.paramB < 0)
                    $scope.equation += $scope.paramB + "x^2";
                else
                    $scope.equation += "+" + $scope.paramB + "x^2";
            }
            if(typeof $scope.paramC == "number"){
                if ($scope.paramC < 0)
                    $scope.equation += $scope.paramC;
                else
                    $scope.equation += "+" + $scope.paramC;
            }
        }
    };

    $scope.clearInput = function () {
        $scope.paramA = "";
        $scope.paramB = "";
        $scope.paramC = "";
        $scope.paramD = "";
        $scope.equation = "";
        $scope.computed = false;
        $scope.nghiem = [];
    };

    $scope.goToLink = function(url) {
        window.open(url, "_self");
    };

    document.getElementById('form').onsubmit = function (event) {
        draw();
        $scope.compute();
        event.preventDefault();
    };

    $scope.compute = function () {
        $scope.computed = true;
        $scope.nghiem = [];
        $scope.pointOx = "";
        $scope.value = [];
        $scope.temp = "";
        $scope.special = [];
        if($scope.algebraType != 'bac1'){
            $scope.valueDx = "";
            $scope.dx = Algebrite.eval('d('+$scope.equation+')').toString();
            $scope.temp = Algebrite.eval('nroots('+$scope.dx+')').toString();
            $scope.value = tinhNghiem($scope.temp, ',');
            if($scope.value == 1) {
                $scope.valueDx = "x = "+ temp;
            }
            else {
                for(var i=0; i<$scope.value.length; i++){
                    if($scope.value[i].indexOf('i') < 0){
                        $scope.valueDx += " x_"+ (i+1) +" = "+ $scope.value[i] +"; ";
                        $scope.special.push("("+ $scope.value[i] +", "+ Algebrite.eval($scope.equation, 'x', $scope.value[i]).toString() +"); ");
                    }
                }
            }
        }
        //Tính giao điểm vs trục Ox: y=0
        $scope.points = Algebrite.eval('nroots('+$scope.equation+')').toString();
        $scope.nghiem = tinhNghiem($scope.points, ',');
        console.log($scope.nghiem);
        if($scope.nghiem.length == 1) {
            $scope.pointOx = "("+$scope.points+", 0); ";
        }
        else {
            for(var i=0; i<$scope.nghiem.length; i++){
                if($scope.nghiem[i].indexOf('i') < 0){
                    $scope.pointOx += "("+$scope.nghiem[i]+", 0); ";
                }
            }
        }
        //Tính giao điểm vs trục Oy: x=0
        if($scope.algebraType == 'bac3'){
            $scope.pointOy = "( 0, "+ $scope.paramD +")";
        }
        else{
            $scope.pointOy = "( 0, "+ $scope.paramC +")";
        }
    };

    function tinhNghiem(str, div){
        var res = str.split(div);
        for(var i = 0; i<res.length; i++){
            res[i] = res[i].replace("(-1)^(1/2)","i");
            if(i==0){
                res[i] = res[i].slice(1);
            }
            if(i==(res.length-1)){
                res[i] = res[i].slice(0,res[i].length - 1);
            }
        }
        return res;
    }
});