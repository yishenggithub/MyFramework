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
	<script src="<%=basePath%>ckeditor/ckeditor.js"></script>
	<script src="<%=basePath%>assets/js/sample.js"></script>

	<link rel="stylesheet" href="<%=basePath%>assets/css/samples.css">
	<link rel="stylesheet" href="toolbarconfigurator/lib/codemirror/neo.css">

	 <script src="<%=basePath%>assets/js/jquery.min.js"></script>
    <script src="<%=basePath%>assets/js/bootstrap.min.js"></script>
    <script src="<%=basePath%>assets/js/scripts.js"></script>
     <link href="<%=basePath%>assets/css/bootstrap.min.css" rel="stylesheet">
    <link href="<%=basePath%>assets/css/style.css" rel="stylesheet">


</head>

<body id="main">
<main>
	<div class="adjoined-top">
		<div class="grid-container">
			<div class="content grid-width-100">
				<h1> ${article.title}</h1>
			</div>
		</div>
	</div>
	<div class="adjoined-bottom">
		<div class="grid-container">
			<div class="grid-width-100">
		    	<div class="background" style="background:white;border:1px solid #000;height:650px">
                   <div class="col-md-12">
                   <div class="content" style="width:100%; height:auto; word-wrap:break-word; word-break:break-all;overflow:hidden">
                   <!--解决换行问题-->
			         <h2>
			           ${article.content}
                     </h2>
                   </div>
                    </div>
			    </div>
			    </br>
			    <a href="list.action" class="btn btn-default" >列表</a>
			</div>
		</div>
	</div>

</main>
</body>
</html>