<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>

<!DOCTYPE html>
<html>
<head>

	<meta charset="utf-8">
	<title>CKEditor Sample</title>

    <script src="<%=basePath%>assets/js/jquery.min.js"></script>
    <script src="<%=basePath%>assets/js/bootstrap.min.js"></script>
    <script src="<%=basePath%>assets/js/scripts.js"></script>
     <link href="<%=basePath%>assets/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="<%=basePath%>assets/css/style.css" rel="stylesheet" type="text/css">

    <script src="<%=basePath%>ckeditor/ckeditor.js"></script>
	<script src="<%=basePath%>assets/js/sample.js"></script>

	<link href="<%=basePath%>assets/css/samples.css" rel="stylesheet" >
	<link rel="stylesheet" href="toolbarconfigurator/lib/codemirror/neo.css">


</head>

<body id="main">
<main>
	<div class="adjoined-top">
		<div class="grid-container">
			<div class="content grid-width-100">
				<h1>Congratulations!</h1>
				<p>
					欢迎使用！
                    <a class="btn btn-primary btn-large " href="edit.action" >新建</a>
				</p>
			</div>
		</div>
	</div>
	<div class="adjoined-bottom">
		<div class="grid-container">
			<div class="grid-width-100">


 <div class="container-fluid">
	<div class="row">
		<div class="col-md-12">


			<c:forEach items="${articleList}" var="article">
                <div class="jumbotron" style="padding-right: 60px;padding-left: 60px; border-radius: 0px; ">
                				<h2>
                					${article.title}
                				</h2>
                				<p>
                					${article.content}
                				</p>
                				<p>
                					<a class="btn btn-primary btn-large btn-success" href="show.action?bid=${article.bid}">Learn more</a>
                					<a class="btn btn-primary btn-large btn-danger" href="delete.action?bid=${article.bid}">delete</a>
                					<a class="btn btn-primary btn-large " href="edit.action?bid=${article.bid}">edit</a>
                				</p>

                </div>
            </c:forEach>
		</div>
	</div>
</div>



			</div>
		</div>
	</div>


</main>

</body>
</html>