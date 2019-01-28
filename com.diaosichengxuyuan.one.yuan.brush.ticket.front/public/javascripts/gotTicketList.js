(function (baseSearchUrl) {

    var ticketList;
    var currentPage = 1;

    function GotTicketList(root) {
        this.root = root;
    }

    GotTicketList.prototype = {
        constructor: GotTicketList,
        getSearchUrl: function (type) {
            if (type == 1) {
                currentPage++;
            }
            else if (type == -1 && currentPage > 1) {
                currentPage--;
            }

            return baseSearchUrl + "?page=" + currentPage;
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
                id: 1,
                startDate: "2019-01-19",
                week: "周六",
                trains: "G1" + currentPage,
                startStation: "北京南站",
                endStation: "上海虹桥站",
                startTime: "9:15",
                endTime: "14:49",
                status: "未支付"
            }, {
                id: 2,
                startDate: "2019-01-19",
                week: "周六",
                trains: "G2" + currentPage,
                startStation: "北京南站",
                endStation: "上海虹桥站",
                startTime: "9:15",
                endTime: "14:49",
                status: "已支付"
            }, {
                id: 3,
                startDate: "2019-01-19",
                week: "周六",
                trains: "G3" + currentPage,
                startStation: "北京南站",
                endStation: "上海虹桥站",
                startTime: "9:15",
                endTime: "14:49",
                status: "已支付"
            }, {
                id: 4,
                startDate: "2019-01-19",
                week: "周六",
                trains: "G4" + currentPage,
                startStation: "北京南站",
                endStation: "上海虹桥站",
                startTime: "9:15",
                endTime: "14:49",
                status: "已取消"
            }, {
                id: 5,
                startDate: "2019-01-19",
                week: "周六",
                trains: "G5" + currentPage,
                startStation: "北京南站",
                endStation: "上海虹桥站",
                startTime: "9:15",
                endTime: "14:49",
                status: "已取消"
            }]
        },
        parseJsonData: function (data) {

        },
        generateHtml: function (ticketList) {
            var html = "";
            html += '<table>' +
                '<tbody>';
            if (ticketList.length > 0) {
                for (var i = 0; i < ticketList.length; i++) {
                    var ticket = ticketList[i];
                    html += '<tr>' +
                        '<td><a onclick="GotTicketList.ticktDetail(' + JSON.stringify(ticket).replace(/"/g, '&quot;') + ')">' + ticket.startDate + '(' + ticket.week + ')&nbsp;&nbsp;' + ticket.trains + '</br>' + ticket.startStation + '(' + ticket.startTime + '开) → ' + ticket.endStation + '(' + ticket.endTime + '到)' + '</a></td>' +
                        '<td>' + ticket.status + '</td>' +
                        '</tr>';
                }
            }
            html += '</tbody>' +
                '</table>';
            return html;
        },
        bindToRootElement: function (ticketListHtml) {
            var rootElement = document.getElementById(this.root);
            rootElement.innerHTML = ticketListHtml;
        }
    }

    GotTicketList.search = function (root, type) {
        debugger
        if (ticketList == undefined) {
            ticketList = new GotTicketList(root);
        }
        var searchUrl = ticketList.getSearchUrl(type);
        var data = ticketList.search(searchUrl);
        var html = ticketList.generateHtml(data);
        ticketList.bindToRootElement(html);
    }

    GotTicketList.ticktDetail = function (task) {
        location.href = "http://localhost:3000/gotTicketDetail?id=" + task.id + "&status=" + task.status;
        return false;
    }

    window.GotTicketList = GotTicketList;
})("localhost:3000/homePage")