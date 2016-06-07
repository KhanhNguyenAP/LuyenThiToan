/**
 * Created by Ariana Nhu on 07/06/2016.
 */
app.controller('AppCtrl', function($scope) {
    $scope.algebra = [
        { name: 'Vẽ đồ thị hàm số', img: 'images/icons/algebra_graph.png', url:'views/graph.html' },
        { name: 'Phương trình số học', img: 'images/icons/algebra_equation.png',  url:'views/prepare.html' },
        { name: 'Phương trình lượng giác', img: 'images/icons/algebra_luonggiac.png', url:'views/prepare.html' },
        { name: 'Số phức', img: 'images/icons/algebra_sophuc.png', url:'views/prepare.html' },
        { name: 'Tính đạo hàm', img: 'images/icons/algebra_daoham.png', url:'views/derivative.html' },
        { name: 'Tính tích phân', img: 'images/icons/algebra_tichphan.png', url:'views/prepare.html' }
    ];
    $scope.hinhhoc = [
        { name: 'Tính độ dài', img: 'images/icons/hh_lenght.png', url:'views/length.html' },
        { name: 'Tính diện tích', img: 'images/icons/hh_square.png',  url:'views/prepare.html' },
        { name: 'Tính thể tích', img: 'images/icons/hh_cube.png', url:'views/prepare.html' }
    ];

    $scope.goToLink = function(url) {
        window.open(url, "_self");
    };
});
