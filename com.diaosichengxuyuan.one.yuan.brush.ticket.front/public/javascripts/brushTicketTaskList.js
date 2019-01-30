(function (baseSearchUrl) {

    function BrushTicketTaskList(root) {
        this.root = root;
    }

    BrushTicketTaskList.prototype = {
        constructor: BrushTicketTaskList,
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
                id: 1,
                startPlace: "北京",
                endPlace: "上海",
                startDate: "2019年5月26日",
                status: "已停止"
            }, {
                id: 2,
                startPlace: "杭州",
                endPlace: "武汉",
                startDate: "2018年7月26日",
                status: "已启动"
            }, {
                id: 3,
                startPlace: "长沙",
                endPlace: "广州",
                startDate: "2019年3月20日",
                status: "已结束"
            }, {
                id: 4,
                startPlace: "天津",
                endPlace: "南京",
                startDate: "2018年2月21日",
                status: "已结束"
            }]
        },
        parseJsonData: function (data) {

        },
        generateHtml: function (taskList) {
            var html = "";
            html += '<table>' +
                '<tbody>';
            if (taskList.length > 0) {
                for (var i = 0; i < taskList.length; i++) {
                    var task = taskList[i];
                    html += '<tr>' +
                        '<td><a href="javascript:void(0);" onclick="BrushTicketTaskList.taskDetail(' + JSON.stringify(task).replace(/"/g, '&quot;') + ')">' + task.startPlace + '→' + task.endPlace + '</br>' + task.startDate + '</a></td>' +
                        '<td>' + task.status + '</td>' +
                        '<td><a class="taskListDeleteLink" href="" onclick="BrushTicketTaskList.deleteById(' + task.id + ')">删除</a></td>' +
                        '</tr>';
                }
            }
            html += '</tbody>' +
                '</table>';
            return html;
        },
        bindToRootElement: function (taskListHtml) {
            var rootElement = document.getElementById(this.root);
            rootElement.innerHTML = taskListHtml;
        }
    }

    BrushTicketTaskList.search = function (root) {
        var taskList = new BrushTicketTaskList(root);
        var searchUrl = taskList.getSearchUrl();
        var data = taskList.search(searchUrl);
        var html = taskList.generateHtml(data);
        taskList.bindToRootElement(html);
    }

    BrushTicketTaskList.deleteById = function (id) {
        return false;
    }

    BrushTicketTaskList.taskDetail = function (task) {
        switch (task.status) {
            case "已停止":
                {
                    location.href = "http://localhost:3000/brushTicketTaskListStopped?id=" + task.id;
                    break;
                }
            case "已启动":
                {
                    location.href = "http://localhost:3000/brushTicketTaskListStarted?id=" + task.id;
                    break;
                }
            case "已结束": {
                location.href = "http://localhost:3000/brushTicketTaskListFinished?id=" + task.id;
                break;
            }
            default:
                {
                    break;
                }
        }
    }

    window.BrushTicketTaskList = BrushTicketTaskList;
})("localhost:3000/homePage")