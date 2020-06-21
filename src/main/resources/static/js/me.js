function post() {
    const questionId = $("#question_id").val();
    const content = $("#comment_content").val();
    $.ajax({
            type: "POST",
            url: "/comment",
            contentType: "application/json",
            data: JSON.stringify({
                    "parentId":questionId.toString(),
                    "content":content.toString(),
                    "type":"1".toString()
                }),
            success: [function (response) {
                if (response.code == 200) {
                    $("#comment_section").hide();
                } else {
                    alert(response.message)
                }
                console.log(response);
            }]
        });
}