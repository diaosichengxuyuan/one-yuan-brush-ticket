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

    CommonUtils.getDefaultGrayColor = function () {
        return "rgb(170, 164, 164)";
    }

    CommonUtils.validateAndPrompt = function (fields) {
        var success = true;
        for (var index in fields) {
            var field = fields[index];
            if (field == undefined) {
                continue;
            }

            if (typeof (field.value) != "string") {
                continue;
            }

            if (field.value.trim() == "") {
                field.style["border-color"] = "red";
                success = false;
            }
        }
        return success;
    }

    window.CommonUtils = CommonUtils;
})()