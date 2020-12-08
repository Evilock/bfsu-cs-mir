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
                        var html = "<tr><td>"+JSON.stringify(data)+"</td><tr>";
                        $("#responseFuck").append(html);
                    },
                    error:function(){
                        alert("error");
                    }
                });
            });
    });
});