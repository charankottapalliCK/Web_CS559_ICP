angular.module('lab2App', ['ngSanitize'])
    .controller('lab2Controller', function($scope, $http, $location) {

        $scope.getTwitterFriends = function (callback) {
            // Hiding all errors first
            $scope.errorText = "";
            var twitterName = $scope.twitterName;
            // Making Sure Input Text is Not empty
            if (twitterName == null || $.trim(twitterName) == ''){
                // Throw error
                $scope.errorText = "Input Text should not be empty !!";
                // Stop flow
                return false;
            }

                $http.get('http://127.0.0.1:8080/get/'+twitterName)
                    .success(function(data){
                        if(data == "Success"){
// Removing Previous SVG elements First
                            d3.select('svg').remove();

                            // ************** Generate the tree diagram	 *****************
                            var margin = {top: 20, right: 120, bottom: 20, left: 120},
                                width = 960 - margin.right - margin.left,
                                height = 500 - margin.top - margin.bottom;
                            var i = 0;
                            var tree = d3.layout.tree()
                                .size([height, width]);
                            var diagonal = d3.svg.diagonal()
                                .projection(function(d) { return [d.y, d.x]; });
                            var svg = d3.select("body").append("svg")
                                .attr("width", width + margin.right + margin.left)
                                .attr("height", height + margin.top + margin.bottom)
                                .append("g")
                                .attr("transform", "translate(" + margin.left + "," + margin.top + ")");
                            // load the external data
                            d3.json("DynTreeData.json", function(error, treeData) {
                                root = treeData[0];
                                update(root);
                            });
                            function update(source) {
                                // Compute the new tree layout.
                                var nodes = tree.nodes(root),
                                    links = tree.links(nodes);
                                // Normalize for fixed-depth.
                                nodes.forEach(function (d) {
                                    d.y = d.depth * 180;
                                });
                                // Declare the nodes…
                                var node = svg.selectAll("g.node")
                                    .data(nodes, function (d) {
                                        return d.name || (d.name = ++i);
                                    });
                                // Enter the nodes.
                                var nodeEnter = node.enter().append("g")
                                    .attr("class", function (d) {
                                        if (d.parent == "null") {
                                            return "node parent"
                                        } else
                                            return "node child"
                                    })
                                    .attr("transform", function (d) {
                                        return "translate(" + d.y + "," + d.x + ")";
                                    });
                                nodeEnter.append("circle")
                                    .attr("r", 10)
                                    .style("fill", "#fff")
                                nodeEnter.append("text")
                                    .attr("x", function (d) {
                                        return 13
                                    })
                                    .attr("dy", ".35em")
                                    .attr("text-anchor", function (d) {
                                        return d.parent || d._children ? "start" : "end";
                                    })
                                    .text(function (d) {
                                        return d.name;
                                    })
                                    .style("fill-opacity", 1);
                                // Declare the links…
                                var link = svg.selectAll("path.link")
                                    .data(links, function (d) {
                                        return d.target.id;
                                    });
                                // Enter the links.
                                link.enter().insert("path", "g")
                                    .attr("class", "link")
                                    .attr("d", diagonal);
                            }
                        }else{
                            $scope.errorText = "No friends Found for the user : "+twitterName;
                        }
                    });
        };
    });