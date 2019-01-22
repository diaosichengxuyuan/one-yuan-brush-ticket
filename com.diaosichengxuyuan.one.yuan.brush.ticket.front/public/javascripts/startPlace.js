$(document).ready(
    function () {
        $("#azTitle li").click(function () {
            $(".azContentClass").hide();
            var index = $(this).attr("index");
            showCityContent(index);
        });

        $(".oneCityOfStartPlaceClass").click(function () {
            var city = $.trim($(this).text());
            $("#startPlace .searchConditonClass").val(city);
        });
    }
);

function showCityContent(index) {
    $("#azTitle li").removeClass("azliClass");
    $("#azTitle" + index).addClass("azliClass");
    $("#azContent" + index).show();
}