<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/6/14
  Time: 14:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Title</title>
    <script src="js/jquery-3.4.1.js">
    </script>
    <script type="text/javascript">
        $(function () {
            $("#ajaxBtn").click(function(){
                $.get("/ajax",{uname:'admin',upwd:'123'},function (data){
                    var d = eval("("+data+")");
                    alert(d.resp);
                },"json");
            });
        })
        $(function(){
            $(".del").click(function(){
                var id = $(this).attr("id");
                $.get("ajax",{id:id},function(data){
                    var d = eval("("+data+")");
                    $("#tr"+id).remove();
                },"json");
            });
        });
    </script>
</head>
<body>
<h2>欢迎</h2><br>




            姓名:${profiles.name}<br>
            生日:${profiles.birthday}<br>
            性别:${profiles.gender}<br>
            职业：${profiles.career}<br>
            住所：${profiles.address}<br>
            电话：${profiles.mobile}<br>
            照片:<input type="image" src="${profiles.picture}" style="width: 120px;lenth:120px"><br>




</body>
</html>
