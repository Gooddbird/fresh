<%--
  Created by IntelliJ IDEA.
  User: hhhhh
  Date: 2019/1/26
  Time: 13:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <title>index</title>
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
                    <a href="${pageContext.request.contextPath}/room?method=getRoomList&currentPage=1&currentCount=10" class="list-group-item text-center ">会议室列表</a>
                    <a href="${pageContext.request.contextPath}/room-add.jsp" class="list-group-item text-center ">新增会议室</a>
                    <a href="${pageContext.request.contextPath}/user?method=getUserList&currentPage=1&currentCount=10" class="list-group-item text-center active">员工列表</a>
                    <a href="${pageContext.request.contextPath}/user-add.jsp" class="list-group-item text-center ">新增员工</a>
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
                    <div class="panel-heading">员工列表</div>
                    <div class="panel-body">
                        <table class="table table-striped table-responsive table-hover">
                            <thead>
                            <tr>
                                <th>编号</th>
                                <th>姓名</th>
                                <th>性别</th>
                                <th>邮箱</th>
                                <th>人脸</th>
                                <th width="120">操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${page.list}" var="user">
                                <tr>
                                    <th>${user.id}</th>
                                    <td>${user.name}</td>
                                    <c:if test="${user.sex==0}" >
                                        <td>男</td>
                                    </c:if>
                                    <c:if test="${user.sex==1}" >
                                        <td>女</td>
                                    </c:if>
                                    <td>${user.email}</td>

                                    <td>${null}</td>


                                    <td>
                                            <%--<a href="">详情</a>--%>
                                        <a href="${pageContext.request.contextPath}/user?method=deleteUser&id=${user.id}">删除</a>
                                        <a href="${pageContext.request.contextPath}/user-update.jsp?id=${user.id}&name=${user.name}&password=${user.password}&sex=${user.sex}&face=${user.face}&email=${user.email}">修改</a>
                                    </td>
                                </tr>
                            </c:forEach>


                            </tbody>
                        </table>
                    </div>
                </div>

                <nav>
                    <ul class="pagination pull-right">
                        <li  class="previous"><a href="#">&laquo;</a></li>
                        <c:forEach begin="1" end="${page.totalPage}" var="Page">
                            <li><a href="${pageContext.request.contextPath}/user?method=getUserList&userPage=${Page}&currentCount=10">${Page}</a></li>
                        </c:forEach>
                        <li><a href="#">&raquo;</a></li>
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
