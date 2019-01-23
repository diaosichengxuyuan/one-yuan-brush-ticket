(
    function (baseSearchUrl) {
        function TicketSearch(startPlace, endPlace, startDate, isStudent, isHighSpeed, root) {
            this.startPlace = startPlace;
            this.endPlace = endPlace;
            this.startDate = startDate;
            this.isStudent = isStudent;
            this.isHighSpeed = isHighSpeed;
            this.root = root;
        }

        TicketSearch.prototype = {
            constructor: TicketSearch,
            getAppendUrl: function () {
                return startPlace + endPlace + startDate + isStudent + isHighSpeed;
            },
            getSearchUrl: function (searchCondition) {
                return baseSearchUrl + searchCondition;
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
                    trains: "G101",
                    startTime: "06:43",
                    endTime: "12:40",
                    startStation: "北京南",
                    endStation: "上海虹桥",
                    costTime: "5小时57分",
                    secondSeatPrice: "￥553",
                    secondeSeatRemain: "21",
                    firstSeatPrice: "￥772",
                    firstSeatRemain: "18",
                    businessSeatPrice: "￥1500",
                    businessSeatRemain: "0",
                    noSeatPrice: "",
                    noSeatRemain: "",
                    hardSeatPrice: "",
                    hardSeatRemain: "",
                    hardSleeperSeatPrice: "",
                    hardSleeperSeatRemain: "",
                    softSleeperSeatPrice: "",
                    softSleeperSeatRemain: ""
                }, {
                    trains: "G101",
                    startTime: "06:43",
                    endTime: "12:40",
                    startStation: "北京南",
                    endStation: "上海虹桥",
                    costTime: "5小时57分",
                    secondSeatPrice: "￥553",
                    secondeSeatRemain: "21",
                    firstSeatPrice: "￥772",
                    firstSeatRemain: "18",
                    businessSeatPrice: "￥1500",
                    businessSeatRemain: "0",
                    noSeatPrice: "",
                    noSeatRemain: "",
                    hardSeatPrice: "",
                    hardSeatRemain: "",
                    hardSleeperSeatPrice: "",
                    hardSleeperSeatRemain: "",
                    softSleeperSeatPrice: "",
                    softSleeperSeatRemain: ""
                }, {
                    trains: "G101",
                    startTime: "06:43",
                    endTime: "12:40",
                    startStation: "北京南",
                    endStation: "上海虹桥",
                    costTime: "5小时57分",
                    secondSeatPrice: "￥553",
                    secondeSeatRemain: "21",
                    firstSeatPrice: "￥772",
                    firstSeatRemain: "18",
                    businessSeatPrice: "￥1500",
                    businessSeatRemain: "0",
                    noSeatPrice: "",
                    noSeatRemain: "",
                    hardSeatPrice: "",
                    hardSeatRemain: "",
                    hardSleeperSeatPrice: "",
                    hardSleeperSeatRemain: "",
                    softSleeperSeatPrice: "",
                    softSleeperSeatRemain: ""
                }]
            },
            parseJsonData: function (data) {

            },
            generateHtml: function (ticketList) {
                var html = "";
                html += '<table class="ticketResultAreaTable">' +
                    '<thead>' +
                    '<tr>' +
                    '<th class="ticketResultTh">车次</th>' +
                    '<th class="ticketResultTh">出发时间/到达时间</th>' +
                    '<th class="ticketResultTh">出发站/到达站</th>' +
                    '<th class="ticketResultTh">耗时</th>' +
                    '<th class="ticketResultTh">参考票价</th>' +
                    '<th class="ticketResultTh">余票</th>' +
                    '</tr>' +
                    '</thead>' +
                    '<tbody>';

                if (ticketList.length > 0) {
                    for (var i = 0; i < ticketList.length; i++) {
                        var ticket = ticketList[i];
                        html += '<tr>' +
                            '<td class="ticketResultTd">' + ticket.trains + '</td>' +
                            '<td class="ticketResultTd">' + ticket.startTime + '/' + ticket.endTime + '</td>' +
                            '<td class="ticketResultTd">' + ticket.startStation + '/' + ticket.endStation + '</td>' +
                            '<td class="ticketResultTd">' + ticket.costTime + '</td>';
                        if (ticket.secondSeatPrice != undefined && ticket.secondSeatPrice != "") {
                            html += '<td class="ticketResultTd">二等座 <a class="ticketResultReminder">' + ticket.secondSeatPrice + '</a></br>一等座 <a class="ticketResultReminder">' + ticket.firstSeatPrice + '</a></br>商务座 <a class="ticketResultReminder">' + ticket.businessSeatPrice + '</a></br></td>' +
                                '<td class="ticketResultTd">余<a class="ticketResultReminder">' + ticket.secondeSeatRemain + '</a>张</br>余<a class="ticketResultReminder">' + ticket.firstSeatRemain + '</a>张</br>余<a class="ticketResultReminder">' + ticket.businessSeatRemain + '</a>张</br></td>' +
                                '</tr>';
                        }
                        else {

                        }
                    }
                }

                html += '</tbody>' +
                    '</table>'
                return html;
            },
            bindToRootElement: function (ticketResultHtml) {
                var rootElement = document.getElementById(this.root);
                rootElement.innerHTML = ticketResultHtml;
            }
        }

        TicketSearch.search = function (startPlace, endPlace, startDate, isStudent, isHighSpeed, root) {
            var ticketSearch = new TicketSearch(startPlace, endPlace, startDate, isStudent, isHighSpeed, root);
            var appendUrl = ticketSearch.getAppendUrl();
            var searchUrl = ticketSearch.getSearchUrl(appendUrl);
            var ticketList = ticketSearch.search(searchUrl);
            var ticketResultHtml = ticketSearch.generateHtml(ticketList);
            ticketSearch.bindToRootElement(ticketResultHtml);
        }

        window.TicketSearch = TicketSearch;
    }
)("localhost:3000/test")