<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>로그인</title>
    <style>
        /* 마이페이지/인덱스와 통일된 스타일 */
        body {
            font-family: 'Pretendard', -apple-system, BlinkMacSystemFont, system-ui, Roboto, sans-serif;
            background-color: #f8f9fa;
            color: #333;
            margin: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        /* 로그인 카드 */
        .login-card {
            background: #fff;
            width: 100%;
            max-width: 400px;
            padding: 40px;
            border-radius: 16px;
            box-shadow: 0 10px 25px rgba(0, 0, 0, 0.05);
            text-align: center;
        }

        .login-card h2 {
            margin-top: 0;
            margin-bottom: 30px;
            font-size: 1.8rem;
            color: #111;
            letter-spacing: -0.5px;
        }

        /* 입력 폼 스타일 */
        form {
            display: flex;
            flex-direction: column;
            gap: 15px;
        }

        input {
            width: 100%;
            padding: 14px 16px;
            border: 1px solid #e1e1e1;
            border-radius: 10px;
            font-size: 1rem;
            box-sizing: border-box;
            transition: all 0.2s;
        }

        input:focus {
            outline: none;
            border-color: #007bff;
            box-shadow: 0 0 0 3px rgba(0, 123, 255, 0.1);
        }

        /* 버튼 스타일 */
        button {
            padding: 14px;
            font-size: 1rem;
            font-weight: 600;
            background-color: #007bff; /* 인덱스 로그인 버튼과 동일 */
            color: white;
            border: none;
            border-radius: 10px;
            cursor: pointer;
            transition: background-color 0.2s;
            margin-top: 10px;
        }

        button:hover {
            background-color: #0056b3;
        }

        /* 로그인 후 웰컴 메시지 스타일 */
        .welcome-container {
            text-align: center;
        }

        .welcome-text {
            font-size: 1.2rem;
            margin-bottom: 25px;
            color: #444;
        }

        .btn-logout {
            background-color: #333;
        }

        .btn-logout:hover {
            background-color: #555;
        }

        /* 하단 보조 링크 */
        .login-footer {
            margin-top: 25px;
            font-size: 0.85rem;
            color: #888;
        }

        .login-footer a {
            color: #007bff;
            text-decoration: none;
            margin-left: 5px;
        }
    </style>
</head>
<body>

    <c:if test="${not empty SECURITY_LAST_EXCEPTION}">
        <script>
            alert("${SECURITY_LAST_EXCEPTION.message}");
        </script>
        <c:remove var="SECURITY_LAST_EXCEPTION" scope="session" />
    </c:if>
    
    <div class="login-card">
        <c:if test="${not empty authMember}">
            <div class="welcome-container">
                <h2>Welcome!</h2>
                <p class="welcome-text"><strong>${authMember}</strong>님, 환영합니다.</p>
                <form action="/logout" method="post">
                    <button type="submit" class="btn-logout">로그아웃</button>
                    <a href="${pageContext.request.contextPath}/" style="margin-top:15px; display:block; color:#888; text-decoration:none; font-size:0.9rem;">메인으로 돌아가기</a>
                </form>
            </div>
        </c:if>

        <c:if test="${empty authMember}">
            <h2>Login</h2>
            <form action="" method="post" encType="application/x-www-form-urlencoded">
                <input type="text" name="username" placeholder="아이디를 입력하세요" required>
                <input type="password" name="password" placeholder="비밀번호를 입력하세요" required>
                <button type="submit">로그인</button>
            </form>
            
            <div class="login-footer">
                아직 회원이 아니신가요? <a href="${pageContext.request.contextPath}/member/register">회원가입</a>
            </div>
        </c:if>
    </div>

</body>
</html>