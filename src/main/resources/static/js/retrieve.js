$(document).ready(function(){
    $(function() {
        $("#submitTo").click(
            function() {
                $.ajax({
                    url: "/register/change",
                    data: {
                        "email": $("[id='cococo']").text(),
                        "newPassword": $("[name='newpassword']").val(),
                        "newPassword2": $("[name='newpassword2']").val(),
                        "verifyCode": $("[name='verifyCode']").val(),
                    },
                    type: "POST",
                    dataType: "json",
                    success: function (data) {
                        console.log(data);
                        var messageId = data.code;
                        alert("Code:"+messageId+','+data.message);
                        if(messageId === "A00000"){
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