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
	 <script src="<%=basePath%>assets/js/jquery.min.js"></script>
    <script src="<%=basePath%>assets/js/bootstrap.min.js"></script>
    <script src="<%=basePath%>assets/js/scripts.js"></script>
     <link href="<%=basePath%>assets/css/bootstrap.min.css" rel="stylesheet">
    <link href="<%=basePath%>assets/css/style.css" rel="stylesheet">



</head>

<body id="main">
<main>
<form id="edit" method="post" action="save.action">
	<div class="adjoined-top">
		<div class="grid-container"><br/><br/>
			<div class="content grid-width-100">
				<div class="input-group" style="width:100%">
                   <input id="title" name="title" type="text" value="${article.title}" class="form-control" placeholder="请输入文章标题" style="color:black;border: 1px solid #333; height: 31.8px;">
                   </input>
                </div>
			</div>
		</div>
	</div>
	<div class="adjoined-bottom">
		<div class="grid-container">
			<div class="grid-width-100">

                    <textarea id="content" name="content" rows="10" cols="38">${article.content}</textarea>
                    <script type="text/javascript">CKEDITOR.replace('content')</script>
                  <br/>
                    <button type="submit" class="btn btn-default" >提交</button>

			</div>
		</div>
	</div>
  </form>
</main>

 <script type="application/javascript">
            var editId=document.getElementById("edit")

            if(${param.bid}!=null){
             editId.setAttribute("action","update.action?bid=${param.bid}");
            }

</script>
</body>
</html>