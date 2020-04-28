<%@ page import="bean.People" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
 <%-- 
  Created by IntelliJ IDEA. 
  User: D-YY 
  Date: 2020/3/6 
   Time: 17:09 
   To change this template use File | Settings | File Templates. --%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<%@include file="apply_leave1.jsp"%>--%>
<html> 
<head>
    <title>$Title$</title>
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="uk/css/uikit.min.css">
</head>
<script type="text/javascript">
    function replace(){
    sessionStorage.removeItem("user");
    window.location.replace("index.jsp");
    }
</script>
<body>
<div class="header">
    <button><a href="www.baidu.com">请假</a></button>
    <button><a href="www.baidu.com">修改个人信息</a></button>
    <button><a href="www.baidu.com">选课</a></button>
    <button><a href="www.baidu.com">查看审批</a></button>
    <button><a href="www.baidu.com">考勤登记</a></button>
    <button><a href="www.baidu.com">审批</a></button>
    <button><a href="www.baidu.com">添加课程</a></button>
    <button><a href="www.baidu.com">添加人员</a></button>


    <input id="exit" name="exit" value="注销" type="button" onclick="replace()">
    <span id="span_stu"><s:property value="#session.user.getName()"></s:property>,欢迎您</span>
    <% People user = (People)session.getAttribute("user");%>
    <%=user.getName()%>

</div>



</body>
</html>
