$(document).ready(function(){
    getUserData();
});
function getUserData()
{
    var Option =
        {
            url: "./index",
            type: "post",
            dataType:"html",
            success: function (data) {
                data = JSON.stringify(data);
                console.log(data);
                if (Object.keys(data)[0]!=='uinfo') {
                    var content2 = '<ul><li><text style="color: white">Hello~ </text></li>' +
                        '<li><text style="color: white" th:utext="${uinfo.getNickName()}"></text></li></ul>';
                    $("#melo").html(content2);
                } else {
                    var content = '<ul><li><a href="/pages/login">登录</a></li>'+
                        '<li><a href="/pages/register">注册</a></li></ul>';
                    $("#melo").html(content);
                }
            },
            error: function () {
            },
        };
    jQuery.ajax(Option);
    return false;
}
