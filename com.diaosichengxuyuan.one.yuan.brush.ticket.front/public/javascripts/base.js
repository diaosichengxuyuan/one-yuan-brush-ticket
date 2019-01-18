//定时刷新网站实时数据
var CURRENT_DAILY_NUM = "currentDailyNum";
var CURRENT_MEMBERS = "currentMembers";
var BRUSH_TICKET_ORDER_NUM = "brushTicketOrderNum";
var refreshIntervalOfGetDailyData = 2000;

var currentDailyNum = function getCurrentDailyNum() {
    return Math.ceil(Math.random() * 1000);
}

var currentMembers = function getCurrentMembers() {
    return Math.ceil(Math.random() * 1000);
}

var brushTicketOrderNum = function getBrushTicketOrderNum() {
    return Math.ceil(Math.random() * 1000);
}

function setCurrentDailyData(id, num) {
    if (isNaN(num)) {
        return;
    }

    if (CURRENT_DAILY_NUM == id) {
        $("#" + id).text("当前日活数" + num + "人");
    }
    else if (CURRENT_MEMBERS == id) {
        $("#" + id).text("当前会员数" + num + "人");
    }
    else if (BRUSH_TICKET_ORDER_NUM == id) {
        $("#" + id).text("抢票订单数" + num + "个");
    }
}

$(document).ready(
    function () {
        setInterval(function () {
            setCurrentDailyData(CURRENT_DAILY_NUM, currentDailyNum());
        }, refreshIntervalOfGetDailyData);
        setInterval(function () {
            setCurrentDailyData(CURRENT_MEMBERS, currentMembers());
        }, refreshIntervalOfGetDailyData);
        setInterval(function () {
            setCurrentDailyData(BRUSH_TICKET_ORDER_NUM, brushTicketOrderNum());
        }, refreshIntervalOfGetDailyData);
    }
);