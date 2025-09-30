<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ko">
<head><title>Quiz</title></head>

<body>
    <h1>${question}</h1>
    <form action="/quiz" method="post">
        <input type="text" name="answer" placeholder="답을 입력하세요" required autofocus>
        <button type="submit">제출</button>
    </form>
</body>
</html>