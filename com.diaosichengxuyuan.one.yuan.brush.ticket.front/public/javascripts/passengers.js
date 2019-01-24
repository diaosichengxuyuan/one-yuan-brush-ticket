(
    function (baseSearchUrl) {
        var globalPassengers;

        function Passengers(passengersId, root) {
            this.passengersId = passengersId;
            this.root = root;
            this.selectedPassengers = [];
        }

        Passengers.prototype = {
            constructor: Passengers,
            getSearchUrl: function () {
                return baseSearchUrl;
            },
            search: function (searchUrl) {
                // return $.ajax({
                //     type: "GET",
                //     url: searchUrl,
                //     dataType: "json",
                //     async: false,
                //     success: function (data) {
                //         return this.parseJsonData(data);
                //     },
                //     error: function () {
                //     }
                // });
                return [{
                    name: "迈克尔杰克逊",
                    attribute: "成人",
                    idCard: "3723*********025",
                }, {
                    name: "杜甫",
                    attribute: "成人",
                    idCard: "3723*********025",
                }, {
                    name: "爱新觉罗弘历",
                    attribute: "成人",
                    idCard: "3723*********025",
                }, {
                    name: "C罗",
                    attribute: "成人",
                    idCard: "3723*********025",
                }, {
                    name: "爱因斯坦",
                    attribute: "成人",
                    idCard: "3723*********025",
                }, {
                    name: "张伯伦",
                    attribute: "成人",
                    idCard: "3723*********025",
                }, {
                    name: "乔布斯",
                    attribute: "成人",
                    idCard: "3723*********025",
                }]
            },
            parseJsonData: function (data) {

            },
            generateHtml: function (passengersList) {
                var html = "";
                html += '<table class="passengersAreaTable">' +
                    '<thead>' +
                    '<tr>' +
                    '<th class="passengersTh">姓名</th>' +
                    '<th class="passengersTh">属性</th>' +
                    '<th class="passengersTh">身份证</th>' +
                    '<th class="passengersTh">选择</th>' +
                    '</tr>' +
                    '</thead>' +
                    '<tbody>';

                if (passengersList.length > 0) {
                    for (var i = 0; i < passengersList.length; i++) {
                        var passenger = passengersList[i];
                        html += '<tr>' +
                            '<td class="passengersTd">' + passenger.name + '</td>' +
                            '<td class="passengersTd">' + passenger.attribute + '</td>' +
                            '<td class="passengersTd">' + passenger.idCard + '</td>' +
                            '<td class="passengersTd"><input class="ticketResultSelect" type="checkbox"></td>' +
                            '</tr>';
                    }
                }

                html += '</tbody>' +
                    '</table>'
                return html;
            },
            bindToRootElement: function (passengersHtml) {
                var rootElement = document.getElementById(this.root);
                rootElement.innerHTML = passengersHtml;
            },
            addSelectListener: function () {
                $(".ticketResultSelect").click(function () {
                    if (globalPassengers == undefined) {
                        return;
                    }

                    var trElement = $(this).parent().parent();
                    var passenger = trElement.find("td").eq(0).text();
                    if ($(this).prop("checked")) {
                        globalPassengers.selectedPassengers.push(passenger);
                    }
                    else {
                        CommonUtils.removeFromArray(globalPassengers.selectedPassengers, passenger);
                    }
                    document.getElementById(globalPassengers.passengersId).value = globalPassengers.selectedPassengers.join(";");
                });
            }
        }

        Passengers.searchAndCheck = function (passengersId, root) {
            var passengers = new Passengers(passengersId, root);
            globalPassengers = passengers;
            var searchUrl = passengers.getSearchUrl();
            var passengersList = passengers.search(searchUrl);
            var passengersHtml = passengers.generateHtml(passengersList);
            passengers.bindToRootElement(passengersHtml);
            passengers.addSelectListener();
        }

        window.Passengers = Passengers;
    }
)("localhost:3000/test")