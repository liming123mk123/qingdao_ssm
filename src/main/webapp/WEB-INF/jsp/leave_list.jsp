<%--
  Created by IntelliJ IDEA.
  User: 李明
  Date: 2019/7/10
  Time: 11:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Title</title>
    <script src="/js/jquery-3.4.1.js"></script>
    <script>
        $(function () {
            $(".agreeBtn").click(function () {
                var lid = $(this).attr("lid");
                $.get("/updateLeave",{lid:lid},function (data) {
                    alert("shenhechenggong");
                    $("#tr"+lid).remove();
                },"text");
            });
        })
    </script>
</head>
<body>
<table border="1">
    <tr>
        <th>序号</th>
        <th>请假人</th>
        <th>开始时间</th>
        <th>结束时间</th>
        <th>请假理由</th>
        <th>操作</th>
    </tr >
    <c:forEach items="${leaveList}" var="leave" varStatus="i">
        <tr id="tr${leave.lid}">
            <td>${i.count}</td>

            <td>${leave.user.uname}</td>

            <td>${leave.startDate}</td>
            <td>${leave.endDate}</td>
            <td>${leave.reason}</td>
<td><button class="agreeBtn" lid="${leave.lid}">通过</button> </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
