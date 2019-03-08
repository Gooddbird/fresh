<%--
  Created by IntelliJ IDEA.
  User: hhhhh
  Date: 2019/2/25
  Time: 19:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <title>用户预订</title>
    <link rel="stylesheet" href="static/css/bootstrap.min.css">
    <style type="text/css">
        body{ font-family: 'Microsoft YaHei';}
        /*.panel-body{ padding: 0; }*/
    </style>
</head>
<body>
<div class="jumbotron">
    <div class="container">
        <h3>——会议室预订系统</h3>

    </div>
</div>
<div class="container">
    <div class="main">
        <div class="row">
            <!-- 左侧内容 -->
            <div class="col-md-3">
                <div class="list-group">
                    <a href="${pageContext.request.contextPath}/room?method=getReserveRoomList&currentPage=1&currentCount=10" class="list-group-item text-center active">会议室列表</a>
                    <a href="${pageContext.request.contextPath}/user?method=getUserDetials" class="list-group-item text-center ">我的信息</a>
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
                    <div class="panel-heading">会议室列表</div>
                    <div class="panel-body">
                        <table class="table table-striped table-responsive table-hover">
                            <thead>
                            <tr>
                                <th>会议室</th>
                                <th>容量</th>
                                <th>状态</th>
                                <th>预订人</th>
                                <th width="120">操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${page.list}" var="room">
                                <tr>
                                    <td>${room.r_name}</td>
                                    <td>${room.content}</td>
                                    <c:if test="${room.station==0}" >
                                        <td>空闲</td>
                                    </c:if>
                                    <c:if test="${room.station==1}" >
                                        <td>预定中</td>
                                    </c:if>
                                    <c:if test="${room.station==2}" >
                                        <td>使用中</td>
                                    </c:if>
                                    <td>${room.reserver}</td>
                                    <td>
                                        <c:if test="${room.reserver==0}">
                                            <a href="${pageContext.request.contextPath}/room-reserve.jsp?r_id=${room.r_id}&r_station=${room.station}&reserver=${room.reserver}">预订</a>
                                        </c:if>
                                        <a href="${pageContext.request.contextPath}/room-update.jsp?r_id=${room.r_id}&r_name=${room.r_name}&content=${room.content}&station=${room.station}&reserver=${room.reserver}">详情</a>
                                    </td>
                                </tr>
                            </c:forEach>


                            </tbody>
                        </table>
                    </div>
                </div>

                <nav>
                    <ul class="pagination pull-right">
                        <li  class="previous"><a href="${pageContext.request.contextPath}/room?method=getReserveRoomList&currentPage=1&currentCount=10">&laquo;</a></li>
                        <c:forEach begin="1" end="${page.totalPage}" var="Page">
                            <li><a href="${pageContext.request.contextPath}/room?method=getReserveRoomList&currentPage=${Page}&currentCount=10">${Page}</a></li>
                        </c:forEach>
                        <li><a href="${pageContext.request.contextPath}/room?method=getReserveRoomList&currentPage=${page.totalPage}&currentCount=10">&raquo;</a></li>
                    </ul>

                </nav>

                <!-- 分页结束 -->
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
