<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.net.HttpURLConnection" %>
<%@ page import="java.net.URL" %>
<%@ page import="bean.People" %>
<%@ page import="java.util.spi.CalendarDataProvider" %>
<%--<%@ include file="header.jsp" %>--%>
<%
    People user = (People) session.getAttribute("user");
    if (user==null || user.getNature() !=1){
        response.sendRedirect("index.jsp");
    }
%>
<%
    HttpURLConnection conn = (HttpURLConnection)new URL("http://www.baidu.com").openConnection();
    conn.setUseCaches(false);
    conn.setInstanceFollowRedirects(false);
    String dateStr = conn.getHeaderField("Date");
    String now = null;
    if (dateStr != null){
        Date date = new Date(dateStr);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        now = simpleDateFormat.format(date);
    }
%>
<%
    if (session.getAttribute("info") == "success"){%>
<script>
    alert("申请成功");
</script>

<% session.setAttribute("info",null);} else if (session.getAttribute("info") == "error"){%>
<script>
    alert("申请失败，检查申请内容");
</script>
<% session.setAttribute("info",null);}%>

<html lang="en" class="uk-height-1-1">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="uk/css/uikit.min.css" />
    <link rel="stylesheet" href="uk/css/components/datepicker.css"/>
    <script src="uk/js/jquery.js"></script>
    <script src="uk/js/uikit.min.js"></script>
    <script src="uk/js/components/datepicker.js"></script>
    <title>请假</title>
</head>
<script>
    $(function(){
        $("body").on("click", ".uk-button[data-message]", function(){
            var a = /^(\d{4})-(\d{2})-(\d{2})$/;
            if(!a.test($("#test").val())){
                UIkit.notify($(this).data());
            }
        });
    })
</script>
<body class="uk-height-1-1">
<nav class="uk-navbar">
    <ul class="uk-navbar-nav uk-tab" data-uk-tab>
        <li class="uk-active"><a href="">请假</a></li>
        <li><a href="">出勤</a></li>
        <li class="uk-parent"><a href="">个人信息</a></li>
        <li><a href="">链接</a></li>

        <!-- 这是启用JavaScript的容器 -->
        <li data-uk-dropdown="{mode:'click'}">

            <!-- 这是拨动下拉菜单的导航项 -->
            <a href="">导航</a>

            <!-- 这是下拉菜单 -->
            <div class="uk-dropdown uk-dropdown-small">
                <ul class="uk-nav uk-nav-dropdown">
                    <li><a href="">下拉菜单</a></li>
                </ul>
            </div>
        </li>
    </ul>
    <div class="uk-navbar-flip">
        <ul class="uk-navbar-nav uk-tab" data-uk-tab>
            <li><a href="">信息</a></li>
        </ul>
    </div>
</nav>
<div class="uk-vertical-align uk-text-center uk-height-1-1">
    <div class="uk-vertical-align-middle" style="width: 600px;">
        <form action="" class="uk-panel uk-panel-box uk-form" style="border: solid 2px #ddd;border-radius: 2em;">
            <fieldset>
                <legend>请假单</legend>
                <table class="uk-table uk-table-hover uk-table-condensed">
                    <tr>
                        <td class="uk-text-left">
                            学号:
                        </td>
                        <td class="uk-text-left">
                            <div class="uk-form-icon">
                                <i class="uk-icon-user"></i><input type="text" placeholder="学号" class="uk-form-small" disabled>
                            </div></td>
                        <td class="uk-text-left">
                            姓名：
                        </td>
                        <td class="uk-text-left">
                            <div class="uk-form-icon">
                                <i class="uk-icon-lock"></i>
                                <input type="text" placeholder="" class="uk-form-small" disabled></div>
                        </td>
                    </tr>


                    <tr>
                        <td class="uk-text-left">
                            申请时间:
                        </td>
                        <td class="uk-text-left">
                            <div class="uk-form-icon">
                                <i class="uk-icon-calendar"></i>
                                <div class="uk-form-row"></div><input type="text" placeholder="" class="uk-form-small" data-uk-datepicker="{weekstart:6, format:'YYYY-MM-DD'}" disabled></div>
   </td>
    <td class="uk-text-left">
        开始时间
    </td>
    <td class="uk-text-left">
        <div class="uk-form-icon">
            <i class="uk-icon-calendar"></i>
            <div class="uk-form-row"><input type="text" placeholder="" class="uk-form-small" data-uk-datepicker="{weekstart:6, format:'YYYY-MM-DD'}"></div>
        </div></td>
    </tr>

    <tr>
        <td class="uk-text-left">
            结束时间:
        </td>
        <td class="uk-text-left">
            <div class="uk-form-icon">
                <i class="uk-icon-calendar"></i>
                <input type="text" placeholder="" class="uk-form-small" data-uk-datepicker="{weekstart:6, format:'YYYY-MM-DD'}">
            </div></td>
        <td class="uk-text-left">
            天数
        </td>
        <td class="uk-text-left">
            <div class="uk-form-icon">
                <i class="uk-icon-chevron-right"></i>
                <input type="number" placeholder="" class="uk-form-small"></div>
        </td>
    </tr>




    </table>
    原因: <div class="uk-form-icon"><i class="uk-icon-align-left"></i><textarea type="text" placeholder="" class="uk-form-small uk-text-break"></textarea><br>
</div><br>
    <button class="uk-button">提交</button>

    </fieldset>
    </form>
</div>
</div>

</body>
</html>