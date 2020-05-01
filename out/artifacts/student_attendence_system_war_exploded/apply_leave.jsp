<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: D-YY
  Date: 2020/3/30
  Time: 20:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.net.HttpURLConnection" %>
<%@ page import="java.net.URL" %>
<%@ page import="bean.People" %>
<%@ page import="java.util.spi.CalendarDataProvider" %>

<%--<%--%>
<%--    People user = (People) session.getAttribute("user");--%>
<%--    if (user==null || user.getNature() !=1){--%>
<%--        response.sendRedirect("index.jsp");--%>
<%--    }--%>
<%--%>--%>
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
<html class="uk-height-1-1">
<%@ include file="header.jsp" %>
<head>

    <link rel="stylesheet" href="uk/css/uikit.min.css">
    <link rel="stylesheet" href="uk/css/components/datepicker.css">
    <script src="uk/js/jquery.js"></script>
    <script src="uk/js/uikit.min.js"></script>
    <script src="uk/js/components/datepicker.js"></script>
    <title>请假</title>
</head>

<script type="text/javascript">
    $(function(){
        $("#reset").click(function () {
            $("#endtime").val("");
            $("#start_time").val("");
            $("#num_leave").val("");
            $("#leaver_reason").val("");

        });

        $("#apply").click(function () {
            if ($("#num_leave").val() == null || $("#num_leave").val() <= 0){
                alert("请正确填写天数");
                return;
            }
            if ($("#leave_reason").val() == "" || $("#leave_reason").val() == " "){
                alert("请假原因不能为空");
                return;
            }
            $("#myform").submit();
        });

    });



</script>

<%--<form id="myform" action="apply_leave" method="post">--%>
<%--    <input name="stdid" readonly="readonly" type="number" value=<s:property value="#session.user.getStdid()"></s:property>>--%>
<%--    <input name="name" readonly="readonly" type="text" value=<s:property value="#session.user.getName()"></s:property>>--%>
<%--    当前时间：<input type="date" readonly="readonly" name="apply_time" value=<%=now%>>--%>
<%--    请假开始时间：<input type="date" id="start_time" name="start_time" value=<%=now%>>--%>
<%--    请假结束时间：<input type="date" id="end_time" name="end_time" value=<%=now%>><br>--%>
<%--    <span>天数</span><input id="num_leave" name="num_leave" type="number"><br>--%>
<%--    <span>请假原因</span><input id="leave_reason" name="leave_reason">--%>
<%--    <br>--%>
<%--    <input id="apply" type="button" value="提交"/>--%>
<%--    <button id="reset" type="button">重置</button>--%>
<%--</form>--%>

<body class="uk-height-1-1">

<div class="uk-vertical-align uk-text-center uk-height-1-1">
        <div class="uk-vertical-align-middle" >
        <form action="" class="uk-panel uk-panel-box uk-form" style=" border: solid 2px #ddd;border-radius: 2em;">
            <fieldset>
                <legend>请假单</legend>
                <table class="uk-table uk-table-hover uk-table-condensed">
                    <tr>
                        <td class="uk-text-left">
                            学号:
                        </td>
                        <td class="uk-text-left">
                            <div class="uk-form-icon">
                                <i class="uk-icon-user"></i><input name="stdid" type="text" value=<s:property value="#session.user.getStdid()"></s:property> class="uk-form-small" disabled>
                            </div></td>
                        <td class="uk-text-left">
                            姓名：
                        </td>
                        <td class="uk-text-left">
                            <div class="uk-form-icon">
                                <i class="uk-icon-user"></i>
                                <input type="text" name="name" class="uk-form-small" disabled value=<s:property value="#session.user.getName()"></s:property>></div>
                        </td>
                    </tr>


                    <tr>
                        <td class="uk-text-left">
                            申请时间:
                        </td>
                        <td class="uk-text-left">
                            <div class="uk-form-icon">
                                <i class="uk-icon-calendar"></i>
                                <input type="text" name="apply_time" value=<%=now%> class="uk-form-small" data-uk-datepicker="{weekstart:6, format:'YYYY-MM-DD'}" disabled>
                            </div>
    </td>
    <td class="uk-text-left">
        开始时间
    </td>
    <td class="uk-text-left">
        <div class="uk-form-icon">
            <i class="uk-icon-calendar"></i>
            <input type="text" name="start_time" required  class="uk-form-small" data-uk-datepicker="{weekstart:6, format:'YYYY-MM-DD'}">
        </div>
        </td>
    </tr>

    <tr>
        <td class="uk-text-left">
            结束时间:
        </td>
        <td class="uk-text-left">
            <div class="uk-form-icon">
                <i class="uk-icon-calendar"></i>
                <input type="text" name="end_time" required class="uk-form-small" data-uk-datepicker="{weekstart:6, format:'YYYY-MM-DD'}">
            </div></td>
        <td class="uk-text-left">
            天数
        </td>
        <td class="uk-text-left">
            <div class="uk-form-icon">
                <i class="uk-icon-chevron-right"></i>
                <input type="number" id="num_leave" name="num_leave" required class="uk-form-small"></div>
        </td>
    </tr>




    </table>
    原因: <div class="uk-form-icon">
                <i class="uk-icon-align-left"></i>
                <textarea id="leave_reason" name="leave_reason" type="text" required class="uk-form-small uk-text-break" style="width: 400px;height: 100px;"></textarea>
                <br>
        </div>
                <br>
                <button class="uk-button" id="apply" style="margin-right: 150px;">提交</button>
                <button class=" uk-button" type="reset" id="reset" >重置</button>

    </fieldset>

    </form>
</div>
</div>
</body>
</html>
