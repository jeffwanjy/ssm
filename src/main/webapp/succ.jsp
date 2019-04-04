<%--
  Created by IntelliJ IDEA.
  User: wanjiayuan
  Date: 2018/3/6
  Time: 19:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    1
</head>
<body>
<h2>登陆</h2>

username:${username }
<p>
    password:${password }

    <form action="login.html" method="post">
        username:<input type="text" name="username" />
<p>
    password:<input type="password" name="password"/>
    <input type="submit" value="submit" />
    </form>
</body>
</html>
