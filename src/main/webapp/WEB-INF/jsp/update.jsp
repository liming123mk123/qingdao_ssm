<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/6/29
  Time: 14:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/updateProfile" method="post" enctype="multipart/form-data">
    <input type="hidden" name="id" value="${profiles.id}">
    &nbsp;&nbsp;姓名 : <input name="name" type="text" value="${profiles.name}"/><br>
    &nbsp;&nbsp;生日 : <input name="birthday" type="text" value="${profiles.birthday}"/><br>
    &nbsp;&nbsp;性别 : <input name="gender" type="text" value="${profiles.gender}"/><br>
    &nbsp;&nbsp;职业 ：<input name="career" type="text" value="${profiles.career}"/><br>
    &nbsp;&nbsp;住所 ：<input name="address" type="text" value="${profiles.address}"/><br>
    &nbsp;&nbsp;电话 ：<input name="mobile" type="text" value="${profiles.mobile}"/><br>
    &nbsp;&nbsp;照片 : <input name="file" type="file" value="${profiles.picture}"/><br>

    <input type="submit" value="修改"/>
</form>
</body>
</html>
