<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>添加水印</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/user/addwater" method="post">
    当前路径：<input type="text" name="srcImgPath"/> <br><br>
    修改后路径：<input type="text" name="tarImgPath"/> <br><br>
    水印：<input type="text" name="waterMarkContent"/> <br><br>
    <input type="submit" value="提交"/>
</form>

</body>
</html>