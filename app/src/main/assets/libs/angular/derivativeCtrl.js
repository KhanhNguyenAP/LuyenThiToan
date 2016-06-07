/**
 * Created by Ariana Nhu on 07/06/2016.
 */
app.controller('directiveCtrl', function($scope) {
    $scope.res = "";
    $scope.resultText = "";
    $scope.computed = false;

    document.getElementById('frmDirective').onsubmit = function (event) {
        $scope.computeDirective();
        event.preventDefault();
    };

    $scope.input = function(){
        if (!$scope.paramFx){
            $scope.computed = false;
        }
    };

    $scope.goToLink = function(url) {
        window.open(url, "_self");
    };

    $scope.computeDirective = function () {
        $scope.computed = true;
        $scope.res = Algebrite.eval('d('+ $scope.paramFx +')').toString();
        $scope.resultText = "\\begin{align}" +
            "f'(x) & = (" + $scope.paramFx + ")' \\\\"+
                  "& = " + $scope.res +
            "\\end{align}";
    };
});