$(document).ready(function(){
    $(function() {
        $("#submitTo").click(
            function() {
                $.ajax({
                    url: "/register/submit",
                    data: {
                        "userId": $("[name='userId']").val(),
                        "password": $("[name='password']").val(),
                        "email": $("[name='email']").val(),
                        "nickName": $("[name='nickName']").val(),
                    },
                    type: "POST",
                    dataType: "json",
                    success: function (data) {
                        console.log(data);
                        var messageId = data.code;
                        alert("Code:"+messageId+','+data.message);
                        if(messageId === "R00200"){
                            window.location.href = "/index";
                        }
                    },
                    error: function () {
                        alert("error");
                    }
                });
            });
    });
});