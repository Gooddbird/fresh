<%--
  Created by IntelliJ IDEA.
  User: hhhhh
  Date: 2019/1/25
  Time: 16:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <title>form</title>
    <link rel="stylesheet" href="static/css/bootstrap.min.css">
    <style type="text/css">
        body {
            font-family: 'Microsoft YaHei';
        }

        /*.panel-body{ padding: 0; }*/
    </style>
</head>
<body>
<div class="jumbotron">
    <div class="container">

        <h3>——会议室后台管理系统</h3>

    </div>
</div>
<div class="container">
    <div class="main">
        <div class="row">
            <!-- 左侧内容 -->
            <div class="col-md-3">
                <div class="list-group">
                    <a href="${pageContext.request.contextPath}/room?method=getRoomList&currentPage=1&currentCount=10" class="list-group-item text-center ">会议室列表</a>
                    <a href="${pageContext.request.contextPath}/room-add.jsp" class="list-group-item text-center active">新增会议室</a>
                </div>
            </div>
            <!-- 右侧内容 -->
            <div class="col-md-9">
                <!-- 错误提示 -->
                <div style="display: none" class="alert alert-danger" role="alert">
                    <ul>
                        <li>会议室不能为空</li>
                        <li>容量只能为整数</li>
                        <li>请选择当前状态</li>
                    </ul>
                </div>
                <div id="sucess-info" style="display: none" class="alert alert-success alert-dismissible" role="alert">
                    <button type="button" class="close" data-dismiss="alert"><span
                            aria-hidden="false">&times;</span><span class="sr-only">Close</span></button>
                    <strong>成功！</strong> 操作成功提示
                </div>
                <!-- 失败提示框 -->
                <div id="fail-info" style="display: none" class="alert alert-danger alert-dismissible" role="alert">
                    <button type="button" class="close" data-dismiss="alert"><span
                            aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                    <strong>失败！</strong> 操作失败提示
                </div>
                <!-- 自定义内容 -->
                <div class="panel panel-default">
                    <div class="panel-heading">新增会议室</div>
                    <div class="panel-body">
                        <form action="room" method="post" class="form-horizontal" role="form">
                            <div class="form-group">
                                <input type="hidden" name="method" value="addRoom">
                                <label class="col-sm-2 control-label">会议室</label>
                                <div class="col-sm-5">
                                    <input type="text" name="r_name" class="form-control">
                                </div>
                                <div class="col-sm-5">
                                    <p class="form-control-static text-danger">会议室不能为空</p>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">容量</label>
                                <div class="col-sm-5">
                                    <input type="text" name="content" class="form-control" placeholder="容量">
                                </div>
                                <div class="col-sm-5">
                                    <p class="form-control-static text-danger">容量不能为空</p>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">当前状态</label>
                                <div class="col-sm-5">
                                    <label class="radio-inline">
                                        <input type="radio" name="station" value="0">空闲
                                    </label>
                                    <label class="radio-inline">
                                        <input type="radio" name="station" value="1">预定中
                                    </label>
                                    <label class="radio-inline">
                                        <input type="radio" name="station" value="2">使用中
                                    </label>
                                </div>
                                <div class="col-sm-5">
                                    <p class="form-control-static text-danger">请选择当前状态</p>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">预订人ID</label>
                                <div class="col-sm-5">
                                    <input type="text" name="reserver" class="form-control">
                                </div>
                                <div class="col-sm-5">
                                    <p class="form-control-static text-danger">若为空闲则填写0</p>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-offset-2 col-sm-10">
                                    <button type="submit" class="btn btn-primary">提交</button>
                                </div>
                            </div>
                        </form>

                    </div>
                </div>


            </div>
        </div>
    </div>
</div>
<!-- 尾部 -->
<div class="jumbotron" style=" margin-bottom:0;margin-top:105px;">
    <div class="container">
        <span></span>
    </div>
</div>

<script src="static/js/jquery-3.1.0.min.js"></script>
<script src="static/js/bootstrap.min.js"></script>
<%
    System.out.println(response.getStatus() + "--------------status");
    if (response.getStatus() == 201) {
        out.write("<script type=\"text/javascript\">\n" +
                "    window.onload=function(){\n" +
                "        showdiv();\n" +
                "       }</script>");
    } else if (response.getStatus() == 200) {

    } else {
        out.write("<script type=\"text/javascript\">\n" +
                "    window.onload=function(){\n" +
                "        hidediv();\n" +
                "       }</script>");
    }

%>


<script>


    function showdiv() {

        document.getElementById('sucess-info').style.display = 'block';//show的display属性设置为block（显示）
        document.getElementById('fail-info').style.display = 'none';//show的display属性设置为block（显示）


    }
    function hidediv() {


        document.getElementById('fail-info').style.display = 'block';
        document.getElementById('sucess-info').style.display = 'none';//show的display属性设置为none（隐藏）


    }
</script>

</body>
</html>
