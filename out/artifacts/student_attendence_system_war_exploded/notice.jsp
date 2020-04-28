<%@ page import="com.sun.org.apache.bcel.internal.generic.LDIV" %><%--
  Created by IntelliJ IDEA.
  User: D-YY
  Date: 2020/4/15
  Time: 21:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<html>
<%@include file="header.jsp"%>
<head>
    <title>出勤</title>
    <link rel="stylesheet" href="uk/css/uikit.min.css">
    <script src="uk/js/jquery.js"></script>
    <script src="uk/css/uikit.css"></script>
    <script src="uk/js/uikit.min.js"></script>
    <script src="uk/js/components/pagination.js"></script>
</head>
<style>
    tr{
       border-bottom: solid 1px #d4edda;
    }
</style>



<body class="uk-height-1-1">

<%
    String select = (String)session.getAttribute("select");
    if (select != null){%>
        <script>
            alert(<%=select%>);
        </script>
<%}
%>

<%
//    People stu = (People) session.getAttribute("user");
    String stu_info = "";
    if (request.getAttribute("info")!=null && request.getAttribute("info")!=""){
       stu_info = (String)request.getAttribute("info");
    }
%>
<div class="uk-overflow-container uk-width-1-1">
<table class="uk-table uk-table-hover uk-table-striped uk-table-condensed">
    <s:if test="page.getPage_name()=='leave'">    <caption>显示所有请假记录</caption> </s:if>
    <thead>
<s:if test="page.getPage_name()=='leave'">

    <tr>
        <th>
            学号
        </th>
        <th>
            申请日期
        </th>
        <th>
            开始时间
        </th>
        <th>
            结束时间
        </th>
        <th class="uk-text-center">
            申请原因
        </th>
        <th>
            班主任审核状态
        </th>
        <th >
            院领导审核状态
        </th>

    </tr>
</s:if>
<s:if test="page.getPage_name()=='attendance'">
    <tr>
    <th>
    学号
    </th>
    <th>
    考勤日期
    </th>
    <th>
    考勤状况
    </th>
    </tr>
</s:if>
    <s:if test="page.getPage_name()=='course'">
        <tr>
            <th>
                课程号
            </th>
            <th>
                课程名称
            </th>
            <th>
                任课老师
            </th>
            <th class="uk-text-center">
                上课时间
            </th>
            <th>
                课时
            </th>
            <th>
                学院
            </th>
        </tr>
    </s:if>
    </thead>
    <tbody>
<%--    <s:debug></s:debug>--%>
<s:if test="page.getPage_name()=='leave'">
    <s:iterator value="page.getList()" status="status">
        <tr class="uk-table-middle">
            <td>

                <s:property value="page.list.getStdid()"></s:property>
                <s:property value="#status.index"></s:property>
<%--                <s:property value="page.getList()[#status.index].getStdid()"></s:property>--%>
            </td>
            <td>
<%--                <s:property value="page.getList()[#status.index].getApply_time()"></s:property>--%>
                <s:property value="page.getList()[1].getApply_time()"></s:property>
            </td>
            <td>
                <s:property value="page.getList()[#status.index].getStart_time()"></s:property>
            </td>
            <td>
                <s:property value="page.getList()[#status.index].getEnd_time()"></s:property>
            </td>
            <td>
                <s:property value="page.getList()[#status.index].getLeave_reason()"></s:property>
            </td>
            <td>
                <s:property value="page.getList()[#status.index].getMaster_statue()"></s:property>
            </td>
            <td>
                <s:property value="page.getList()[#status.index].getCollege_statue()"></s:property>
            </td>
    </s:iterator>
</s:if>

<s:if test="page.getPage_name()=='attendance'">
    <s:iterator var="at" value="page.getList()" id="iter" status="status">
        <tr class="uk-table-middle">
            <td>
                <s:property value="page.getList()[#status.index].getStdid()"></s:property>
            </td>
            <td>
                <s:property value="page.getList()[#status.index].getAtt_time()"></s:property>
            </td>
            <td>
                <s:property value="page.getList()[#status.index].getAttendance(page.getList()[#status.index].getAttendance())"></s:property>
            </td>
        </tr>
    </s:iterator>
</s:if>

<s:if test="page.getPage_name()=='course'">
    <s:iterator value="page.getList()" status="status">
        <script>
            $(function () {
                $("#select").click(function () {
                    let stdid = '<%=user.getStdid()%>';
                    let course_id = '<s:property value="page.getList()[#status.index].getCourse_id()"></s:property>';
                    $.get("select_course_action?std_id=" + stdid + "&course_id=" + course_id, new function (date, status) {
                        $("#select").text("已选");
                    })
                    return false;
                })
            })
        </script>
        <tr class="uk-table-middle">
            <form action="select_course_action" method="post">
                <input type="hidden" id="stdid" name="stdid" value="<%=user.getStdid()%>">
            <td>
                <input id="course_id" name="course_id" type="hidden" value="<s:property value="page.getList()[#status.index].getCourse_id()"></s:property>">
                <s:property value="page.getList()[#status.index].getCourse_id()"></s:property>
            </td>
            <td>
                <s:property value="page.getList()[#status.index].getCourse_name()"></s:property>
            </td>
            <td>
                <s:property value="page.getList()[#status.index].getTeacher_name()"></s:property>
            </td>
            <td class="uk-text-center">
                <s:property value="page.getList()[#status.index].getTime_course()"></s:property>
            </td>
            <td>
                <s:property value="page.getList()[#status.index].getDatetime_course()"></s:property>
            </td>
            <td>
                <s:property value="page.getList()[#status.index].getCollege_name()"></s:property>
            </td>
            <td>
                <button id="select">选择</button>
            </td>

            </form>
        </tr>

    </s:iterator>
</s:if>

    </tbody>

<%--    <span>共<s:property value="page.getTotalPage()"></s:property>页</span>--%>
</table>
</div>
    <br>
<div class="uk-width-1-1 uk-text-center">
    <ul class="uk-pagination" >
<%--    <s:if test="page.page_name=='attendance'">--%>
        <s:if test="page.nowPage==1">
               <s:iterator var="i" begin="1" end="page.totalPage" step="1">
                      <li><sx:a href="?page.nowPage=%{i}" targets="page"><span>${i}</span></sx:a></li>
                </s:iterator>
            <s:if test="page.totalPage>1">
               <li ><sx:a href="?page.nowPage=%{page.nowPage+1}" targets="page">下一页</sx:a></li>
             <li ><sx:a href="?page.nowPage=%{page.totalPage}" targets="page">尾页</sx:a>
            </s:if>
        </s:if>

        <s:elseif test="page.nowPage==page.totalPage">
                <li ><sx:a href="?page.nowPage=1" targets="page">首页</sx:a></li>
                 <li><span>当前页面为:${page.nowPage}/${page.totalPage}</span></li>
                  
                <li ><sx:a href="?page.nowPage=%{pb.nowPage-1}" targets="page" >上一页</sx:a></li>
                <s:iterator var="i" begin="1" end="page.totalPage" step="1">
                      <li><sx:a href="?pb.nowPage=%{i}" targets="page">${i}</sx:a></li>
                </s:iterator>
             </s:elseif>
         <s:else>
             <li > <sx:a href="?page.nowPage=1" targets="page">首页</sx:a></li>
             <li><span>当前页面为:${page.nowPage}/${page.totalPage}</span></li>
              
            <li ><sx:a href="?page.nowPage=%{page.nowPage-1}" targets="page" >上一页</sx:a></li>
    <%--            <s:iterator var="i" begin="1" end="page.totalPage" step="1">--%>
    <%--                  <li><sx:a href="?page.nowPage=%{i}" targets="page">${i}</sx:a></li>>--%>
    <%--            </s:iterator>--%>
          <li ></li> <sx:a href="?page.nowPage=%{pb.nowPage+1}" targets="page">下一页</sx:a></li>
          <li ><sx:a href="?page.nowPage=%{pb.totalPage}" targets="page">尾页</sx:a></li>
         </s:else>
        <li><span>共<s:property value="page.totalPage"></s:property>页</span></li>
    </ul>
</div>
</body>
</html>