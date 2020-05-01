<%@ taglib prefix="s" uri="/struts-tags" %>
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

    <link rel="stylesheet" href="uk/css/uikit.min.css">
    <%
        String info = (String) request.getAttribute("info");
        String tip = (String)session.getAttribute("info");
        if (tip!=null){%>
    <script>
        alert("<%=tip%>");
    </script>
    <%}%>
    <%
        if (info!=null){%>
    <script>
        alert("<%=info%>");
    </script>
    <%}%>
    <title>添加用户、上传Excel文件添加</title>
    <script src="http://code.jquery.com/jquery-3.3.1.min.js"></script>
</head>

<style type="text/css">

</style>


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
<div class="uk-vertical-align uk-text-center uk-height-1-1">
    <div class="uk-vertical-align-middle uk-panel uk-panel-box" style=" border: solid 2px #ddd;border-radius: 2em;">
    <form id="myForm" class="uk-panel uk-panel-box uk-form" action="addUser" enctype="multipart/form-data" method="post" style="text-align: left">
        <fieldset>
            <legend class="uk-text-center">批量添加人员</legend>
            <table class="uk-table uk-table-hover uk-table-condensed">
                <tr>
<%--                    <td class="uk-text-left">--%>
<%--                    选择文件:--%>
<%--                </td>--%>
                    <td class="uk-text-left">
                        <div class="uk-form-icon">
                            <i class="uk-icon-folder-open"></i><input id="excel" class="uk-button" name="excel" type="file" multiple="multiple"/>
                        </div></td>
                    <td class="uk-text-left">
                        <input class="uk-button" type="button" id="submitBtn" value="提交" />
                    </td></tr>
            </table>
            </fieldset>
        </form>
        <form action="adduser" class="uk-panel uk-panel-box uk-form"  method="post">
            <fieldset>
                <legend>单例添加人员</legend>
                <table class="uk-table uk-table-hover uk-table-condensed">
                    <tr>
                        <td>
                            学号：<input name="stdid" type="number">
                        </td>
                        <td>
                            姓名：<input name="name" type="text">
                        </td>
                    </tr>
                    <tr>
                        <td>
                            性别：<input name="sex" type="text" list="sexlist">
                            <datalist id="sexlist">
                                <option>男</option>
                                <option>女</option>
                            </datalist>
                        </td>

                        <td>
                            密码：<input name="password" type="text">
                        </td>
                    </tr>
                    <tr>
                        <td>
                            电话：<input name="phone" type="number">
                        </td>
                        <td>
                            权限：<input name="nature" type="number" list="powerlist">
                            <datalist id="powerlist">
                                <option value="0">管理员</option>
                                <option value="1">学生</option>
                                <option value="2">老师</option>
                                <option value="3">班主任</option>
                                <option value="4">院领导</option>
                                <option value="5">校领导</option>
                            </datalist>
                        </td>

                    </tr>
                    <tr>

                        <td>班级：<input name="class_name" list="classlist">
                            <datalist id="classlist">
                                <s:iterator value="list" status="status">
                                    <option value="<s:property value="list[#status.index].getClass_id()"></s:property>"><s:property value="list[#status.index].getClass_name()"></s:property> </option>
                                </s:iterator>
                            </datalist>
                        </td>
                        <td>
                            学院：<input name="college_name" list="collegelist">
                            <datalist id="collegelist">
                                <s:iterator value="list" status="status">
                                    <option value="<s:property value="list[#status.index].getCollege_id()"></s:property>"><s:property value="list[#status.index].getCollege_name()"></s:property> </option>
                                </s:iterator>
                            </datalist>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <button class="uk-button-primary uk-width-1-2" style="margin-left: 5em;" type="submit">添加</button>
                        </td>
                        <td>
                            <button class="uk-button-danger uk-width-1-2" style="margin-left: 5em;" type="reset">重置</button>
                        </td>
                    </tr>
                </table>
            </fieldset>

        </form>
    </div>
</div>
</body>
</html>
