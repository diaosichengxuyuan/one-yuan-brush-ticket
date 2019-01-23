(function () {
    function CommonUtils() {

    }

    CommonUtils.getUrlParam = function (key) {
        var reg = new RegExp("(^|&)"+ key +"=([^&]*)(&|$)"); 
        var r = location.search.substr(1).match(reg);
        if(r == undefined || r == null){
            return null;
        }

        return decodeURI(r[2]);
    }

    window.CommonUtils = CommonUtils;
})()