<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<html>

<head>
    <meta charset="utf-8">

    <title>DateTime Picker</title>
    <link rel="stylesheet" type="text/css" href="static/css/DateTimePicker.css" />

    <script type="text/javascript" src="static/js/jquery-1.11.0.min.js"></script>
    <script type="text/javascript" src="static/js/DateTimePicker.js"></script>

    <!--[if lt IE 9]>
    <link rel="stylesheet" type="text/css" href="static/css/DateTimePicker-ltie9.css" />
    <script type="text/javascript" src="static/js/DateTimePicker-ltie9.js"></script>
    <![endif]-->

    <style type="text/css">

        p
        {
            margin-left: 20px;
        }

        input
        {
            width: 200px;
            padding: 10px;
            margin-left: 20px;
            margin-bottom: 20px;
        }

    </style>

</head>

<body>

<p>开始时间 : </p>
<input type="text" data-field="datetime" readonly>

<p>结束时间 : </p>
<input type="text" data-field="datetime" readonly>

<div id="dtBox"></div>

<script type="text/javascript">

    $(document).ready(function()
    {
        $("#dtBox").DateTimePicker();
    });

</script>

</body>

</html>
