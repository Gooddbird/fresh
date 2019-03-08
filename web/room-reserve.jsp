<%--
  Created by IntelliJ IDEA.
  User: hhhhh
  Date: 2019/2/28
  Time: 16:52
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
    <link rel="stylesheet" type="text/css" href="static/css/DateTimePicker.css" />
    <script type="text/javascript" src="static/js/jquery-1.11.0.min.js"></script>
    <script type="text/javascript" src="static/js/DateTimePicker.js"></script>
    <!--[if lt IE 9]>
    <link rel="stylesheet" type="text/css" href="static/css/DateTimePicker-ltie9.css" />
    <script type="text/javascript" src="static/js/DateTimePicker-ltie9.js"></script>
    <![endif]-->
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
                    <a href="${pageContext.request.contextPath}/room-list.jsp" class="list-group-item text-center ">会议室列表</a>
                </div>
            </div>
            <!-- 右侧内容 -->
            <div class="col-md-9">
                <!-- 错误提示 -->

                <div id="sucess-info" style="display: none" class="alert alert-success alert-dismissible" role="alert">
                    <button type="button" class="close" data-dismiss="alert"><span aria-hidden="false">&times;</span><span class="sr-only">Close</span></button>
                    <strong>成功！</strong> 操作成功提示
                </div>
                <!-- 失败提示框 -->
                <div id="fail-info" style="display: none" class="alert alert-danger alert-dismissible" role="alert">
                    <button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                    <strong>失败！</strong> 操作失败提示
                </div>
                <!-- 自定义内容 -->
                <div class="panel panel-default">
                    <div class="panel-heading">预订会议室</div>
                    <div class="panel-body">
                        <form action="room" method="post" class="form-horizontal" role="form">
                            <div class="form-group">
                                <label class="col-sm-2 control-label">会议室</label>
                                <input hidden name="method" value="reserveRoom">
                                <div class="col-sm-5">
                                    <input type="text" name="r_id" value="${param.r_id}"  class="form-control" readonly>
                                </div>
                                <div class="col-sm-5">
                                    <p class="form-control-static text-danger"></p>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">开始时间</label>
                                <div class="col-sm-5">
                                    <input type="text" name="startTime"  data-field="datetime" class="form-control" readonly>
                                </div>
                                <div class="col-sm-5" id="dtBox1">
                                    <p class="form-control-static text-danger"></p>
                                </div>
                            </div>
                            <div class="form-group" >
                                <label class="col-sm-2 control-label">结束时间</label>
                                <div class="col-sm-5">
                                    <input type="text" name="endTime" data-field="datetime" class="form-control" readonly>
                                </div>
                                <div class="col-sm-5" id="dtBox2">
                                    <p class="form-control-static text-danger"></p>
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
<script type="text/javascript">

    $(document).ready(function()
    {
        $("#dtBox1").DateTimePicker();
        $("#dtBox2").DateTimePicker();
    });

</script>
<!-- 尾部 -->
<div class="jumbotron" style=" margin-bottom:0;margin-top:105px;">
    <div class="container">
        <span></span>
    </div>
</div>
<script src="static/js/bootstrap.min.js"></script>
<%
    System.out.println(response.getStatus()+"--------------status");
    if (response.getStatus()==200) {

    }else if(response.getStatus()==201){
        out.write("<script type=\"text/javascript\">\n" +
                "    window.onload=function(){\n" +
                "        showdiv();\n" +
                "       }</script>");
    }
    else {
        out.write("<script type=\"text/javascript\">\n" +
                "    window.onload=function(){\n" +
                "        offdiv();\n" +
                "       }</script>");
    }

%>


<script>


    function showdiv(){

        if(showdiv_display = document.getElementById('sucess-info').style.display=='none'){//如果show是隐藏的

            document.getElementById('sucess-info').style.display='block';//show的display属性设置为block（显示）
            document.getElementById('fail-info').style.display='none';//show的display属性设置为block（显示）
        }else{//如果show是显示的
            document.getElementById('fail-info').style.display='block';
            document.getElementById('show').style.display='none';//show的display属性设置为none（隐藏）

        }

    }
    function offdiv(){

        //如果show是显示的
        document.getElementById('fail-info').style.display='block';
        document.getElementById('sucess-info').style.display='none';//show的display属性设置为none（隐藏）



    }
</script>

</body>
</html>
