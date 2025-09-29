<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
    <h2>로그인</h2>
    <p>테스트 계정 : test@test.com / 1234!</p>

    <form action="/user/login" method="post">
        이메일: <input type="text" name="email" value="${loginForm.email}" /><br>
        비밀번호: <input type="password" name="password" /><br>
        <button type="submit">로그인</button>
    </form>

    <c:if test="${not empty message}">
        <div> ${message} </div>
    </c:if>

    <!-- 디버깅 정보 -->
    <c:if test="${not empty loginForm.instanceId}">
        <p>디버깅: ${loginForm.instanceId}</p>
    </c:if>
</body>
</html>