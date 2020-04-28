<%--
  Created by IntelliJ IDEA.
  User: D-YY
  Date: 2020/4/2
  Time: 16:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>k课程添加</title>
    <%
    String info = (String) request.getAttribute("info");
    if (info!=null){%>
    <script>
        alert(<%=info%>);
    </script>

    <%}%>
</head>
<body>
<form name="Course_form" action="addCourse" method="post">
    课程ID：<input name="course_id" type="number">
    课程名称：<input name="course_name" type="text">
    上课时间：<input name="course_time" type="text"><%--//基本时间由两部分组成：例子12：表示星期一上午第二节课。通过“，“分割 --%>
    任课老师ID：<input name="teacher_iD" type="number">
    课时：<input name="datetime_course" type="number">
    <input name="submit" id="submit" type="button" value="提交">
    <input name="reset" id="reset" type="button" value="重置">
</form>

</body>
</html>
