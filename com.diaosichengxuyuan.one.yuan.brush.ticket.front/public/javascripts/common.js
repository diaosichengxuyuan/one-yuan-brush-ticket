(function () {
    function CommonUtils() {

    }

    CommonUtils.getUrlParam = function (key) {
        var reg = new RegExp("(^|&)" + key + "=([^&]*)(&|$)");
        var r = location.search.substr(1).match(reg);
        if (r == undefined || r == null) {
            return null;
        }

        return decodeURI(r[2]);
    }

    CommonUtils.removeFromArray = function (array, element) {
        var index = array.indexOf(element);
        if (index > -1) {
            array.splice(index, 1);
        }
    }

    window.CommonUtils = CommonUtils;
})()