<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>비밀번호 변경</title>
    <style>
        /* 기본 폰트 및 배경 (마이페이지와 통일) */
        body {
            font-family: 'Pretendard', -apple-system, BlinkMacSystemFont, system-ui, Roboto, sans-serif;
            background-color: #f8f9fa;
            color: #333;
            margin: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
        }

        /* 카드 컨테이너 */
        .change-card {
            background: #fff;
            width: 100%;
            max-width: 450px;
            padding: 40px;
            border-radius: 16px;
            box-shadow: 0 10px 25px rgba(0, 0, 0, 0.05);
        }

        .change-card h2 {
            margin-top: 0;
            margin-bottom: 30px;
            font-size: 1.5rem;
            color: #111;
            text-align: center;
        }

        /* 입력 폼 스타일 */
        .form-group {
            margin-bottom: 20px;
        }

        label {
            display: block;
            font-weight: 600;
            color: #888;
            font-size: 0.9rem;
            margin-bottom: 8px;
        }

        input[type="password"] {
            width: 100%;
            padding: 12px 15px;
            border: 1px solid #e1e1e1;
            border-radius: 8px;
            font-size: 1rem;
            box-sizing: border-box; /* 패딩 포함 너비 계산 */
            transition: border-color 0.2s, box-shadow 0.2s;
        }

        input[type="password"]:focus {
            outline: none;
            border-color: #007bff;
            box-shadow: 0 0 0 3px rgba(0, 123, 255, 0.1);
        }

        /* 버튼 영역 */
        .btn-area {
            margin-top: 30px;
            display: flex;
            flex-direction: column;
            gap: 10px;
        }

        button {
            width: 100%;
            padding: 14px;
            background-color: #333; /* 마이페이지 홈 버튼과 동일한 톤 */
            color: white;
            border: none;
            border-radius: 8px;
            font-size: 1rem;
            font-weight: 600;
            cursor: pointer;
            transition: background-color 0.2s;
        }

        button:hover {
            background-color: #555;
        }

        /* 취소/홈으로 버튼 스타일 */
        .btn-secondary {
            background-color: transparent;
            color: #888;
            text-decoration: none;
            text-align: center;
            font-size: 0.9rem;
            padding: 10px;
        }

        .btn-secondary:hover {
            color: #333;
            background-color: transparent;
        }

        /* 에러 메시지 스타일 */
        .error-label {
            color: #dc3545;
            font-size: 0.85rem;
            margin-top: 5px;
        }
    </style>
</head>
<body>

<c:if test="${not empty message}">
    <script>
        alert("${message}");
    </script>
    <c:remove var="var_message" scope="session"/>
</c:if>

<div class="change-card">
    <h2>비밀번호 변경</h2>

    <form action="" method="post" autocomplete="off" id="changePasswordForm">
        <div class="form-group" id="oldPasswordDiv">
            <label for="oldPassword" id="oldPasswordLabel">현재 비밀번호</label>
            <input type="password" name="oldPassword" id="oldPassword" placeholder="현재 비밀번호를 입력하세요" required>
        </div>

        <div class="form-group" id="newPasswordDiv">
            <label for="newPassword" id="newPasswordLabel">새 비밀번호</label>
            <input type="password" name="newPassword" id="newPassword" placeholder="새 비밀번호를 입력하세요" required>
        </div>

        <div class="form-group" id="confirmPasswordDiv">   
            <label for="confirmPassword" id="confirmPasswordLabel">새 비밀번호 확인</label>
            <input type="password" name="confirmPassword" id="confirmPassword" placeholder="한 번 더 입력하세요" required>
        </div>

        <div class="btn-area">
            <button type="submit" id="changePasswordButton">비밀번호 변경하기</button>
            <a href="${pageContext.request.contextPath}/" class="btn-secondary">취소하고 돌아가기</a>
        </div>
    </form>
</div>

<script src="/resources/js/app/member/changePassword.js" type="text/javascript"></script>
</body>
</html>