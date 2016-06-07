/**
 * Created by Ariana Nhu on 07/06/2016.
 */
app.controller('lengthCtrl', function($scope) {
    $scope.types = [
        {name: "Hệ Oxy", id: "Oxy"},
        {name: "Hệ Oxyz", id: "Oxyz"}
    ];
    $scope.heToaDo = "Oxy";
    $scope.distanceAB = 0;
    $scope.computed = false;
    $scope.showEquation = "";

    $scope.clearInput = function () {
        $scope.paramAx = "";
        $scope.paramAy = "";
        $scope.paramAz = "";
        $scope.paramBx = "";
        $scope.paramBy = "";
        $scope.paramBz = "";
        $scope.computed = false;
        $scope.showEquation = "";
    };

    $scope.input = function () {
        if ($scope.heToaDo == 'Oxy'){
            $scope.paramAz = 0;
            $scope.paramBz = 0;
        }
    };

    document.getElementById('frmLength').onsubmit = function (event) {
        $scope.measureLength();
        event.preventDefault();
    };

    $scope.goToLink = function(url) {
        window.open(url, "_self");
    };

    $scope.measureLength = function () {
        $scope.computed = true;
        var powDistance = Math.pow(($scope.paramAx - $scope.paramBx), 2);
        powDistance +=  Math.pow(($scope.paramAy - $scope.paramBy), 2);
        if($scope.heToaDo == 'Oxyz'){
            powDistance +=  Math.pow(($scope.paramBz - $scope.paramAz), 2);
        }
        $scope.distanceAB = customRound(Math.sqrt(powDistance)).toString();
        if($scope.heToaDo == 'Oxy'){
            $scope.showEquation = "\\begin{align} &= \\sqrt{("+ $scope.paramAx +" - "+ $scope.paramBx +")^2 " +
                "+ ("+ $scope.paramAy +" - "+ $scope.paramBy +")^2} \\\\" +
                "&= \\sqrt{("+ customRound(Number($scope.paramAx) - Number($scope.paramBx)) +")^2 + ("+ customRound(Number($scope.paramAy) - Number($scope.paramBy)) +")^2} \\\\" +
                "&= " + $scope.distanceAB +
                "\\end{align}";
        }
        else {
            $scope.showEquation = "\\begin{align} &= \\sqrt{("+ $scope.paramAx +" - "+ $scope.paramBx +")^2 " +
                "+ ("+ $scope.paramAy +" - "+ $scope.paramBy +")^2 + ("+ $scope.paramAz +" - "+ $scope.paramBz +")^2} \\\\" +
                "&= \\sqrt{("+ customRound(Number($scope.paramAx) - Number($scope.paramBx)) +")^2 + ("+ customRound(Number($scope.paramAy) - Number($scope.paramBy)) +")^2" +
                " + ("+ customRound(Number($scope.paramAz) - Number($scope.paramBz)) +")^2} \\\\" +
                "&= " + $scope.distanceAB +
                "\\end{align}";
        }
    };

    function customRound(num){
        return Math.round(num * 10000)/10000;
    }
});