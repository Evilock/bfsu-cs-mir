$(document).ready(function(){
    getUserData();
});
function getUserData()
{
    var Option =
        {
            url: "/index",
            type: "post",
            dataType:"html",
            success: function () {
                console.log("todo");
            },
            error: function () {
            },
        };
    jQuery.ajax(Option);
    return false;
}
