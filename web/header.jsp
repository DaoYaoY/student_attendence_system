<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*" %>
<%@page import="bean.People" %>

<%

%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="uk/css/uikit.min.css" />
    <link rel="stylesheet" href="uk/css/components/datepicker.css"/>
    <script src="uk/js/jquery.js"></script>
    <script src="uk/js/uikit.min.js"></script>
    <script src="uk/js/components/datepicker.js"></script>

    <title>header</title>
</head>
<body >
<div>
<nav class="uk-navbar">
    <ul class="uk-navbar-nav" style="top: 40px;left: 0;">
        <%
            People user = (People) session.getAttribute("user");
            if (user==null){
                response.sendRedirect("index.jsp");
                return;
            }
            if (user.getNature()==1 || user.getNature()==0){%>
        <%--        学生模块   --%>
            <li class="uk-button-dropdown" data-uk-dropdown="" aria-haspopup="true" aria-expanded="false">

                <!-- 这是拨动下拉菜单的导航项 -->
                <a href="">出勤管理</a>

                <!-- 这是下拉菜单 -->
                <div class="uk-dropdown uk-dropdown-small">
                    <ul class="uk-nav uk-nav-dropdown">
                        <li><a href="apply_leave.jsp">请假</a></li>
                        <li><a href="stu_notice">查看审核</a></li>
                        <li><a href="attendance_notice">出勤状况</a></li>
<%--                        <li><a href="">下拉菜单</a></li>--%>
                    </ul>
                </div>
            </li>

            <li class="uk-button-dropdown" data-uk-dropdown="" aria-haspopup="true" aria-expanded="false">

                <!-- 这是拨动下拉菜单的导航项 -->
                <a href="">课程管理</a>

                <!-- 这是下拉菜单 -->
                <div class="uk-dropdown uk-dropdown-small">
                    <ul class="uk-nav uk-nav-dropdown">
                        <li><a href="select_course.jsp">选课</a></li>
                        <li><a href="show_course">查看课程</a></li>
                    </ul>
                </div>
            </li>
       <%}%>
        <%--        <li class="uk-parent"><a href="">个人信息</a></li>--%>
        <%--        <li><a href="">链接</a></li>--%>

        <%
            if ((user.getNature()>=2 && user.getNature()<=3) || user.getNature()==0){%>

        <%--     普通教师界面        --%>

        <!-- 这是启用JavaScript的容器 -->
        <li class="uk-button-dropdown" data-uk-dropdown="" aria-haspopup="true" aria-expanded="false">

            <!-- 这是拨动下拉菜单的导航项 -->
            <a href="">课程管理</a>

            <!-- 这是下拉菜单 -->
            <div class="uk-dropdown uk-dropdown-small">
                <ul class="uk-nav uk-nav-dropdown">
                    <li><a href="show_course.jsp">查看课程</a></li>
                </ul>
            </div>
        </li>

            <li class="uk-button-dropdown" data-uk-dropdown="" aria-haspopup="true" aria-expanded="false">

                <!-- 这是拨动下拉菜单的导航项 -->
                <a href="">考勤管理</a>

                <!-- 这是下拉菜单 -->
                <div class="uk-dropdown uk-dropdown-small">
                    <ul class="uk-nav uk-nav-dropdown">
                        <li><a href="read_att.jsp">学生缺勤填写</a></li>
                        <li><a href="show_att.jsp">学生出勤状况查看</a> </li>
                    </ul>
                </div>
            </li>
                <%}%>
        <%
            if (user.getNature()>=3 || user.getNature()==0 ){%>

        <li class="uk-button-dropdown" data-uk-dropdown="" aria-haspopup="true" aria-expanded="false">

                <!-- 这是拨动下拉菜单的导航项 -->
                <a href="">审核管理</a>

                <!-- 这是下拉菜单 -->
                <div class="uk-dropdown uk-dropdown-small">
                    <ul class="uk-nav uk-nav-dropdown">
                        <li><a href="show_app.jsp">查询假单</a></li>
                        <li><a href="check_app.jsp">审核假单</a></li>
                    </ul>
                </div>
            </li>
        <%}%>
    </ul>
    <div class="uk-navbar-flip">
        <ul class="uk-navbar-nav">
            <li class="uk-button-dropdown" data-uk-dropdown="" aria-haspopup="true" aria-expanded="false">

                <!-- 这是拨动下拉菜单的导航项 -->
                <a href="">信息管理</a>

                <!-- 这是下拉菜单 -->
                <div class="uk-dropdown uk-dropdown-small">
                    <ul class="uk-nav uk-nav-dropdown">
                        <li><a href="show_self_info.jsp">个人信息</a></li>
                        <li><a href="show_class_info.jsp">班级信息</a></li>
                    </ul>
                </div>
            </li>
        </ul>
    </div>
</nav>

</div>
</body>
</html>
