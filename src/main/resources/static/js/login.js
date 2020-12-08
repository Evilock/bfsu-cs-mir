$(document).ready(function(){
    $(function() {
        $("#submitTo").click(
            function() {
                $.ajax({
                    url : "/check/" + $("input[name=userid]").val() + "/"
                        + $("input[name=password]").val()+"/"
                    + $("input[name=verifyCode]").val(),
                    type : "POST",
                    success : function(data) {
                        if(data.body === "A00000"){
                            alert("登陆成功！");
                            window.location.href="/";
                        }else if(data.body === "E00401"){
                            alert("请输入验证码！");
                        }else if(data.body === "E00204"){
                            alert("验证码错误!");
                        }else if(data.body === "E00400"){
                            alert("用户名密码错误!");
                        }
                    },
                    error:function(){
                        alert("请正确输入！");
                    }
                });
            });
    });
});