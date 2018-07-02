<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h3>博文写作</h3>

<table style="width: 100%">
    <tr>
        <iv width="80%">
            <div class="row">
                <input type="hidden" id="bid" value="${blog.bid}">
                <div class="col-md-12">
                    <textarea class="form-control" id="title" rows="1" placeholder="请输入文章标题">${blog.title}</textarea>
                </div>
            </div>
            <br></iv>
        <div class="row">
            <div class="col-md-12">
                <textarea class="form-control" id="text" rows="10" placeholder="请输入文章内容">${blog.content}</textarea>
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

