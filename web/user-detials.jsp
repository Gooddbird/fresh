<%--
  Created by IntelliJ IDEA.
  User: hhhhh
  Date: 2019/3/5
  Time: 14:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <title>个人信息</title>
    <link rel="stylesheet" href="static/css/bootstrap.min.css">
    <style type="text/css">
        body{ font-family: 'Microsoft YaHei';}
        /*.panel-body{ padding: 0; }*/
    </style>
</head>
<body>
<div class="jumbotron">
    <div class="container">
        <h3>——会议室管理系统</h3>

    </div>
</div>
<div class="container">
    <div class="main">
        <div class="row">
            <!-- 左侧内容 -->
            <div class="col-md-3">
                <div class="list-group">
                    <a href="${pageContext.request.contextPath}/room?method=getReserveRoomList&currentPage=1&currentCount=10" class="list-group-item text-center ">会议室列表</a>
                    <a href="${pageContext.request.contextPath}/user?method=getUserDetials" class="list-group-item text-center active">我的信息</a>
                </div>
            </div>
            <!-- 右侧内容 -->
            <div class="col-md-9">
                <!-- 成功提示框 -->
                <div class="alert alert-success alert-dismissible" role="alert">
                    <button type="button" class="close" data-dismiss="alert"><span aria-hidden="false">&times;</span><span class="sr-only">Close</span></button>
                    <strong>成功！</strong> 操作成功提示
                </div>
                <!-- 失败提示框 -->
                <div style="display: none" class="alert alert-danger alert-dismissible" role="alert">
                    <button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                    <strong>失败！</strong> 操作失败提示
                </div>
                <!-- 自定义内容 -->
                <div class="panel panel-default">
                    <div class="panel-heading">个人信息 </div>
                    <div class="panel-body">
                        <div class="form-group">
                            <label class="col-sm-2 control-label">ID</label>
                            <div class="col-sm-5">
                                <label class="col-sm-2 control-label">${user.id}</label>
                            </div>
                            <div class="col-sm-5">
                                <p class="form-control-static text-danger"></p>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">姓名</label>
                            <div class="col-sm-5">
                                <label class="col-sm-2 control-label">${user.name}</label>
                            </div>
                            <div class="col-sm-5">
                                <p class="form-control-static text-danger"></p>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">邮箱</label>
                            <div class="col-sm-5">
                                <label class="col-sm-2 control-label">${user.email}</label>
                            </div>
                            <div class="col-sm-5">
                                <p class="form-control-static text-danger"></p>
                            </div>
                        </div>
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
</body>
</html>
