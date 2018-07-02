<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<head>
    <title>Title</title>
    <meta charset="utf-8">
    <script src="assets/js/jquery.js"></script>
    <script src="assets/js/jquery.cookie.js"></script>
    <script type="application/javascript">

        $(document).ready(function () {
            $("#pullout").click(function () {
                var uid = $.cookie("uid");
                var title = $("#title").val();
                var text = $("#text").val();
                if (title == "" || text == "") {
                    alert("请确认是否有空缺项！");
                } else {
                    var blog={
                        "title": title,
                        "content": text,
                        "uid": uid
                    };
                    $.ajax({
                        type: 'post',
                        url: '<%=basePath%>addArticle',
                        contentType: 'application/json;charset=utf-8',
                        data: JSON.stringify(blog),
                        success: function () {
                            alert("添加成功！");
                            window.location.href="<%=basePath%>index";
                        },
                        error: function (XMLHttpRequest, textStatus, errorThrown) {
                            alert(XMLHttpRequest.status);
                            alert(XMLHttpRequest.readyState);
                            alert(textStatus);
                            alert(errorThrown);
                        }
                    });
                }
            });
        })
    </script>
</head>
<body>
<h3>博文写作</h3>

<table style="width: 100%">
    <tr>
        <iv width="80%">
            <div class="row">
                <div class="col-md-12">
                    <textarea class="form-control" id="title" rows="1" placeholder="请输入文章标题"></textarea>
                </div>
            </div>
            <br></iv>
            <div class="row">
                <div class="col-md-12">
                    <textarea class="form-control" id="text" rows="17" placeholder="请输入文章内容"></textarea>
                </div>
            </div>
            <br></iv>
            <div class="row">
                <div class="col-md-12">
                    <button id="pullout" type="button" class="btn btn-default">
                        发布
                    </button>
                </div>
            </div>
        </td>
    </tr>
</table>
</body>
</html>
