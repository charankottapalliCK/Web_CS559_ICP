angular.module('myapp', [])
    .controller('mycontroller', function($scope, $http,$log) {
        $scope.data = {
            model: null,
            availableOptions: [
                {id: 'en', name: 'English'},
                {id: 'hi', name: 'Hindi'},
                {id: 'es', name: 'Spanish'},
                {id:'ar', name: 'Arabic'},
                {id:'te', name: 'Telugu'},
                {id:'fr', name:'French'}
            ]
        };
        $scope.data1 = {
            model: null,
            availableOptions: [
                {id: 'en', name: 'English'},
                {id: 'hi', name: 'Hindi'},
                {id: 'es', name: 'Spanish'},
                {id:'ar', name: 'Arabic'},
                {id:'te', name: 'Telugu'},
                {id:'fr', name:'French'}
            ]
        };
        $scope.translate = function()  {
            console.log("kdjfnm");
            var successCallBack = function (response) {
                console.log("SUCCESS");
                console.log(response.data);
                $scope.text = response.data.text[0];
            };
            var failureCallBack = function (response) {
                console.log("FAIL");
                $scope.error = response.data;
                $log.info(response);
            };
            var sourceText = document.getElementById("SourceLanguage").value;
            console.log(sourceText);
            console.log();
            console.log();
            $http({
                method: 'GET',
                url: 'https://translate.yandex.net/api/v1.5/tr.json/translate?key=trnsl.1.1.20151023T145251Z.bf1ca7097253ff7e.c0b0a88bea31ba51f72504cc0cc42cf891ed90d2&text=' + sourceText + '&lang='+$scope.data.model+'-'+$scope.data1.model+'&[format=plain]&[options=1]&[callback=set]'
            })
                .then(successCallBack, failureCallBack);
        };
    });