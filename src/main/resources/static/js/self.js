$(document).ready(function(){
    $(function() {
        $("#submitTo").click(
            function() {
                $.ajax({
                    url : "/logout",
                    type : "POST",
                    success : function(data) {
                        if(data.body === "A00001"){
                            alert("登出成功！");
                            window.location.href="/";
                        }else if(data.body === "F00002"){
                            alert("登出失败");
                        }
                    },
                    error:function(){
                        alert("ERROR！");
                    }
                });
            });
    });
});

/* 关闭弹窗 */
function fade() {
    document.getElementById("MyDiv").style.display = 'none';
    document.getElementById("fade").style.display = 'none';
}