<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    String jspPath=basePath+"blogview/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="keyword" content="webDemo">
    <link rel="shortcut icon" href="<%=basePath%>assets/images/logo2.jpg">
    <title>简单的博客系统</title>

    <!-- Bootstrap core CSS -->
    <link href="<%=basePath%>assets/css/bootstrap.css" rel="stylesheet">
    <link href="<%=basePath%>assets/css/style.css" rel="stylesheet">
    <link href="<%=basePath%>assets/css/style-responsive.css" rel="stylesheet">
    <script src="<%=basePath%>assets/js/jquery.js"></script>
    <script src="<%=basePath%>assets/js/jquery.cookie.js"></script>
    <script src="<%=basePath%>assets/js/bootstrap.min.js"></script>
    <script src="<%=basePath%>assets/js/jquery-ui-1.9.2.custom.min.js"></script>
    <style type="text/css">
        body {
            font-family: STFangSong, Helvetica, Arial, Vernada, Tahoma, STXihei, "Microsoft YaHei", "Songti SC", SimSun, Heiti, sans-serif;
        !important;
        }
    </style>
    <script type="application/javascript">
        $(document).ready(function () {
            var uid = $.cookie("uid");
            var uname = $.cookie("username");
            if (uname != null) {
                $("#top_menu").html
                ('<li class="dropdown"><a data-toggle="dropdown" class="dropdown-toggle"' +
                    ' href="#" title="">' +
                    '<i class="fa fa-user" aria-hidden="true"></i>' +
                    '&nbsp;' + uname + '</a><ul class="dropdown-menu extended tasks-bar">' +
                    '<div class="notify-arrow notify-arrow-green"></div><li>' +
                    '<p class="green">' + uname + '</p> </li> <li class="external"> ' +
                    '<a href="#" id="changePassword"><i class="fa fa-key" aria-hidden="true" ></i>修改密码</a>' +
                    '<a href="logout?uid='+uid+'" class="logout-button"><i class="fa fa-sign-out" aria-hidden="true"></i> ' +
                    '注销</a></li></ul></li>');
            }

            $(document).ready(function () {
                    $("#changePassword").click(function () {
                        $("#myDiv").load("changePassword.jsp");
                    })
                })

            $("#myDiv").load("<%=jspPath%>homePage.jsp");
            $("#myDiv").load("<%=basePath%>articleList?uid=" + uid);

            $("#register").click(function () {
                $("#myDiv").load("<%=jspPath%>register.jsp");
            });
            $("#login").click(function () {
                $("#myDiv").load("<%=jspPath%>login.jsp");
            });
            $("#index").click(function () {

                $("#myDiv").load("<%=jspPath%>homePage.jsp");
            });
            $("#articleList").click(function () {
                $("#myDiv").load("<%=basePath%>articleList?uid=" + uid);
            });
            $("#source").click(function () {

                $("#myDiv").load("<%=jspPath%>articleWt.jsp");
            });
            $("#update").click(function () {
              alert(123);
            });
        });
    </script>
</head>

<body>
<section id="container">
    <!-- **********************************************************************************************************************************************************
    TOP BAR CONTENT & NOTIFICATIONS
    *********************************************************************************************************************************************************** -->
    <!--header start-->
    <header class="header black-bg">

        <!--logo start-->
        <a href="#" class="logo"><b>
            <img src="<%=basePath%>assets/images/logo2.jpg" height="30px" width="30px">
            一个博客系统</b></a>
        <!--logo end-->
        <div class="nav notify-row">
            <!--  notification start -->
            <ul class="nav top-menu" id="top_menu">
                <li><a href="#" id="register">注册</a></li>
                <li><a href="#" id="login">登录</a></li>
            </ul>
            <!--  notification end -->
        </div>
    </header>
    <!--header end-->

    <!-- **********************************************************************************************************************************************************
    MAIN SIDEBAR MENU
    *********************************************************************************************************************************************************** -->
    <aside>
        <div id="sidebar" class="nav-collapse scrollable">
            <!-- sidebar menu start-->
            <ul class="sidebar-menu" id="nav-accordion">
                <br>
                <li>
                    <img src="<%=basePath%>assets/images/touxiang3.png" height="180px" width="180px">

                </li>
                <li id="mainPage" >
                    <a href="#" id="index">
                        主页
                    </a>
                </li>
                <li id="articleList" >
                    <a href="#" id="list">
                        博文列表
                    </a>
                </li>
                <li id="articleWt">
                    <a href="#" id="source">
                        写文章
                    </a>
                </li>
            </ul>
            <!-- sidebar menu end-->
        </div>
    </aside>
    <!--sidebar end-->

    <!-- **********************************************************************************************************************************************************
    MAIN CONTENT
    *********************************************************************************************************************************************************** -->
    <!--main content start-->
    <section id="main-content">
        <section class="wrapper site-min-height">

            <div class="row mt">
                <div class="col-lg-12">

                    <div class="showback">
                        <div class="site-index">
                            <div class="jumbotron">
                                <div id="myDiv"></div>
                            </div>
                        </div>
                    </div>
                </div>

            </div>
        </section>
        <! --/wrapper -->
    </section><!-- /MAIN CONTENT -->

    <!--main content end-->
    <!--footer start-->
    <footer class="footer">
        <p class="pull-left"></p>
        <p class="pull-right">&copy; www.yisheng.xyz 2018</p>
    </footer>
    <!--footer end-->
</section>
<!--script for this page-->

<script>
</script>

</body>
</html>
