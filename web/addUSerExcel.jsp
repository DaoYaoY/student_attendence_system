<%--
  Created by IntelliJ IDEA.
  User: D-YY
  Date: 2020/3/24
  Time: 11:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String relPath = request.getRequestURI();
    session.setAttribute("path",relPath);
%>
<html>
<head>
    <%
        String info = (String) request.getAttribute("info");
        if (info!=null){%>
    <script>
        alert("<%=info%>");
    </script>
    <%}%>
    <title>添加用户、上传Excel文件添加</title>
    <script src="http://code.jquery.com/jquery-3.3.1.min.js">

    </script>
</head>

<script type="text/javascript">
    $(function(){

        $("#submitBtn").click(function(){
            var fileName = $("#excel").val();
            var suffix = (fileName.substr(fileName.lastIndexOf("."))).toUpperCase();
            // alert(suffix);
            //验证文件后缀名
            if(".XLS" != suffix && ".XLSX" != suffix){
                alert("文件格式只能是.XLS或者.XLSX");
                return;
            }
            $("#myForm").submit();

        });



    });

</script>
<body>
<form id="myForm" action="addUser" enctype="multipart/form-data" method="post" style="text-align: left">
    <input id="excel" name="excel" type="file" multiple="multiple"/>
    <input type="button" id="submitBtn" value="提交" />
</form>
</body>
</html>
