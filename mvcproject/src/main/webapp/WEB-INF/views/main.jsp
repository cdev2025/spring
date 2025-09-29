<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>메인 페이지</title>
</head>
<body>
    <h1>메인 페이지</h1>
    <h2>환영합니다, ${userName}님!</h2>

    <div>
        <h3>사용자 정보</h3>
        <p><strong>이름:</strong> ${userName}</p>
        <p><strong>이메일:</strong> ${userEmail}</p>
        <p><strong>로그인 시간:</strong> ${loginTime}</p>
        <p><small><strong>세션 ID:</strong> ${sessionId}</small></p>
    </div>

    <div>
     이 페이지는 로그인한 사용자만 접근할 수 있습니다.<br>
     세션이 유지되는 동안 사용자 정보가 보존됩니다.
    </div>

    <div><a href="/main?logout"> 로그아웃 </a></div>
</body>
</html>