(function (baseSearchUrl) {

    var ticketList;
    var currentPage = 1;

    function GotTicketDetail(id, root) {
        this.id = id;
        this.root = root;
    }

    GotTicketDetail.prototype = {
        constructor: GotTicketDetail,
        getSearchUrl: function () {
            return baseSearchUrl + "?id=" + this.id;
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
            return {
                id: this.id,
                startDate: "2019-01-19",
                week: "周六",
                trains: "G11",
                startStation: "北京南站",
                endStation: "上海虹桥站",
                startTime: "9:15",
                endTime: "14:49",
                status: this.id == 1 ? "未支付" : this.id == 2 || this.id == 3 ? "已支付" : "已取消",
                passengers: [
                    {
                        name: "刘XX",
                        cardType: "中国居民身份证",
                        cardNumber: "3723*********025",
                        ticketType: "成人票",
                        seats: "二等座",
                        carriage: "04",
                        seatNumber: "11B号",
                        price: "553.0元"
                    }, {
                        name: "张XX",
                        cardType: "中国居民身份证",
                        cardNumber: "3723*********025",
                        ticketType: "成人票",
                        seats: "二等座",
                        carriage: "04",
                        seatNumber: "11B号",
                        price: "553.0元"
                    }
                ]
            }
        },
        parseJsonData: function (data) {

        },
        generateHtml: function (ticketDetail) {
            var html = "";
            html += '<div class="ticketDetail">' + ticketDetail.startDate + '(' + ticketDetail.week + ')&nbsp;&nbsp;G11&nbsp;&nbsp;' + ticketDetail.startStation + '(' + ticketDetail.startTime + '开) → ' + ticketDetail.endStation + '(' + ticketDetail.endTime + '到)</div>';
            html += '<div class="passengers';

            if (ticketDetail.status == "已支付") {
                html += ' haspay';
            }
            else if (ticketDetail.status == "已取消") {
                html += ' hascancel';
            }

            html += '">' +
                '<table>' +
                '<thead>' +
                '<tr>' +
                '<th>序号</th>' +
                '<th>姓名</th>' +
                '<th>证件类型</th>' +
                '<th>证件号码</th>' +
                '<th>票种</th>' +
                '<th>席别</th>' +
                '<th>车厢</th>' +
                '<th>席位号</th>' +
                '<th>票价(元)</th>' +
                '</tr>' +
                '</thead>' +
                '<tbody>';
            var passengers = ticketDetail.passengers;
            if (passengers != undefined && passengers.length > 0) {
                for (var i = 0; i < passengers.length; i++) {
                    var passenger = passengers[i];
                    html += '<tr>' +
                        '<td>' + (i + 1) + '</td>' +
                        '<td>' + passenger.name + '</td>' +
                        '<td>' + passenger.cardType + '</td>' +
                        '<td>' + passenger.cardNumber + '</td>' +
                        '<td>' + passenger.ticketType + '</td>' +
                        '<td>' + passenger.seats + '</td>' +
                        '<td>' + passenger.carriage + '</td>' +
                        '<td>' + passenger.seatNumber + '</td>' +
                        '<td>' + passenger.price + '</td>' +
                        '</tr>';
                }
            }
            html += '</tbody>' +
                '</table>' +
                '</div>';

            if (ticketDetail.status == "未支付") {
                html += '<div class="payDetail">剩余支付时间:29分21秒 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 已抢到票，请自行到12306后台支付！！！</div>'
            }

            return html;
        },
        bindToRootElement: function (ticketDetailHtml) {
            var rootElement = document.getElementById(this.root);
            rootElement.innerHTML = ticketDetailHtml;
        }
    }

    GotTicketDetail.search = function (root, id) {
        var ticketDetail = new GotTicketDetail(id, root);
        var searchUrl = ticketDetail.getSearchUrl();
        var data = ticketDetail.search(searchUrl);
        var html = ticketDetail.generateHtml(data);
        ticketDetail.bindToRootElement(html);
    }

    window.GotTicketDetail = GotTicketDetail;
})("localhost:3000/homePage")