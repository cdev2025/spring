<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
    <title>Login</title>
</head>
<body>
    <form:form modelAttribute="user">
        이메일: <form:input path="email" /><br>
        비밀번호: <form:password path="password" /><br>
        <input type="submit" value="로그인" />
    </form:form>
</body>
</html>