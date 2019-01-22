$(document).ready(
    function () {
        $(".oneCityOfEndPlaceClass").click(function () {
            var city = $.trim($(this).text());
            $("#endPlace .searchConditonClass").val(city);
        });

        $("#azTitle li").click(function () {
            $(".azContentClass").hide();
            var index = $(this).attr("index");
            showCityContent(index);
        });
    }
);

function showCityContent(index) {
    $("#azTitle li").removeClass("azliClass");
    $("#azTitle" + index).addClass("azliClass");
    $("#azContent" + index).show();
}