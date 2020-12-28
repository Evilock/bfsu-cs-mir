function uploadFile() {
    var fileObj = $("#file")[0].files[0];
    var form = new FormData();
    form.append("file", fileObj);

    $.ajax({
        url:"/getMusicFile",
        data:form,
        type:"POST",
        async: true,
        processData: false,
        success: function(data) {
            alert("success!!\n"+data);
        },
        error:function () {
            alert("ERROR!!\n"+data);
        }
    });
}