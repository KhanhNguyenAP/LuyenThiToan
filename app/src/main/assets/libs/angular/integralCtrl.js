/**
 * Created by Ariana Nhu on 08/06/2016.
 */
app.controller('integralCtrl', function($scope) {
    $scope.types = [
        {name: "Tích phân xác định", id: "xacdinh"},
        {name: "Tích phân bất định", id: "batdinh"}
    ];
    $scope.integralType = 'xacdinh';
    $scope.res = "";
    $scope.resultText = "";
    $scope.finalRes = "";
    $scope.valueResult = "";
    $scope.computed = false;

    document.getElementById('frmIntegral').onsubmit = function (event) {
        $scope.computeDirective();
        event.preventDefault();
        document.getElementById('paramFx').blur();
    };

    $scope.input = function(){
        if (!$scope.paramFx){
            $scope.computed = false;
        }
        if ($scope.integralType == 'batdinh'){
            $scope.paramA = 0;
            $scope.paramB = 0;
        }
    };

    $scope.clearInput = function(){
        $scope.paramA = "";
        $scope.paramB = "";
        if ($scope.integralType == 'batdinh'){
            $scope.paramA = 0;
            $scope.paramB = 0;
        }
        $scope.res = "";
        $scope.resultText = "";
        $scope.finalRes = "";
        $scope.valueResult = "";
        $scope.computed = false;
    };

    $scope.goToLink = function(url) {
        window.open(url, "_self");
    };

    $scope.computeDirective = function () {
        $scope.computed = true;
        $scope.valueResult = "";
        if ($scope.integralType == 'batdinh'){
            $scope.res = Algebrite.eval('integral('+ $scope.paramFx +')').toString();
            $scope.resultText = "\\begin{align}" +
                "\\int{f(x)dx} & = \\int{(" + $scope.paramFx + ")dx} \\\\"+
                "& = " + $scope.res + "+ C"+
                "\\end{align}";
            $scope.finalRes = "\\text{Vậy }\\int{(" + $scope.paramFx + ")dx} = " + $scope.res + "+ C";
        }
        else {
            $scope.res = Algebrite.eval('integral('+ $scope.paramFx +')').toString();
            $scope.valueResult = Algebrite.eval('defint('+ $scope.paramFx +',x,'+ $scope.paramB +','+ $scope.paramA +')').toString();
            $scope.resultText = "\\begin{align}" +
                "\\int_{"+ $scope.paramB +"}^{"+ $scope.paramA +"}{f(x)dx}" +
                "& = \\int_{"+ $scope.paramB +"}^{"+ $scope.paramA +"}{(" + $scope.paramFx + ")dx} \\\\"+
                "& = (" + $scope.res + ")|_{"+ $scope.paramB +"}^{"+ $scope.paramA +"} \\\\"+
                "&= ("+ replaceValue($scope.res, "("+$scope.paramA+")") +")-("+ replaceValue($scope.res, "("+$scope.paramB+")") +") \\\\" +
                "&= " +$scope.valueResult + "\\\\"+
                "\\end{align}";
            $scope.finalRes = "\\text{Vậy }\\int_{"+ $scope.paramB +"}^{"+ $scope.paramA +"}{(" + $scope.paramFx + ")dx} = " + $scope.valueResult;
        }
    };

    function replaceValue(str, val) {
        return str.replace(/x/g, val);
    }
});