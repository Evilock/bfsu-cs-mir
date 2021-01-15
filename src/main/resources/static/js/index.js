function uploadFile() {
    var fileObj = $("#file")[0].files[0];
    var form = new FormData();
    form.append("music", fileObj)

    $.ajax({
        url:"/getMusicFile",
        contentType:false,
        data:form,
        type:"POST",
        async: true,
        processData: false,
        success: function(data) {
            alert("转换成功！请点击最右侧按钮下载~\n");
        },
        error:function () {
            alert("ERROR!!\n");
        }
    });
}