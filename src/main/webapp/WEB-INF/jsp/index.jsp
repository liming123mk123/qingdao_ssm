<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>--%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title>Title</title>
    <script src="js/jquery-3.4.1.js">
    </script>
    <script type="text/javascript">
        $(function(){
            $(".del").click(function(){
                var id = $(this).attr("id");
                $.get("/ajax",{id:id},function(d){
                    alert(d.resp)
                    $("#tr"+id).remove();
                },"json");
            });
        });
    </script>
</head>
<body>
<h2>欢迎</h2><br>
<a href="/leave">添加请假</a><br>
<a href="/leaveList">审批假条</a><br>
<a href="add.jsp">添加信息</a>
<table  class="table table-dark">
    <tr>
        <th>序号</th>
        <th>姓名</th>
        <th>生日</th>
        <th>性别</th>
        <th>职业</th>
        <th>住所</th>
        <th>电话</th>
        <th>图片</th>
        <th>管理员</th>
        <th>操作</th>
    </tr>
    <c:forEach items="${profiles}" var="profiles" varStatus="i">
        <tr id="tr${profiles.id }">
            <td>${i.count }</td>
            <td>${profiles.name}</td>
            <td>${profiles.birthday}</td>
            <td>${profiles.gender}</td>
            <td>${profiles.career}</td>
            <td>${profiles.address}</td>
            <td>${profiles.mobile}</td>
            <td><input type="image" src="${profiles.picture}" style="width: 25px;lenth:25px"></td>
            <td>${profiles.user.uname}</td>

            <td>
                <shiro:hasPermission name="user:delete">
                <a href="javascript:void(0)" class="del" id="${profiles.id }">删除</a>
                </shiro:hasPermission>
                <%--<a  href="ajax?id=${profiles.id }" >shanchu</a>--%>
                <a  href="queryId?id=${profiles.id }" >查看</a>
                <a  href="update?id=${profiles.id }" >修改</a></td>

        </tr>
    </c:forEach>



</table>
<div style="font-weight: bold;font-size: 16px;font-family:'仿宋体';" class="col-md-7">

    共<i class="blue">${pageInfo.total}</i>条记录,
    当前显示第&nbsp;<i class="blue">${pageInfo.pageNum}/${pageInfo.pages}</i>&nbsp;页

</div>


<nav aria-label="Page navigation">
    <ul class="pagination">
        <c:if test="${!pageInfo.isFirstPage}">
            <li><a href="selectRecordByUId?PageNum=1">首页</a></li>

            <li><a href="selectRecordByUId?PageNum=${pageInfo.pageNum-1 }"
                   aria-label="Previous">
                <span aria-hidden="true">&laquo;</span>
            </a>
            </li>
        </c:if>
        <c:forEach items="${pageInfo.navigatepageNums }" var="num">
            <c:if test="${num == pageInfo.pageNum }">
                <li class="active"><a href="selectRecordByUId?PageNum=${num }">${num }</a>
                </li>
            </c:if>
            <c:if test="${num != pageInfo.pageNum }">
                <li><a href="selectRecordByUId?PageNum=${num }">${num }</a></li>
            </c:if>
        </c:forEach>
        <c:if test="${!pageInfo.isLastPage}">
            <li>
                <a href="selectRecordByUId?PageNum=${pageInfo.pageNum+1 }"
                   aria-label="Next">
                    <span aria-hidden="true">&raquo;</span>
                </a>
            </li>
            <li><a href="selectRecordByUId?PageNum=${pageInfo.lastPage}">尾页</a></li>
        </c:if>
    </ul>
</nav>
</div>
</body>
</html>
