<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>INDEX JSP</title>
    <style>
        /* 기본 폰트 및 배경 설정 */
        body {
            font-family: 'Pretendard', -apple-system, BlinkMacSystemFont, system-ui, Roboto, sans-serif;
            background-color: #f8f9fa;
            color: #333;
            margin: 0;
            padding: 0;
            display: flex;
            flex-direction: column;
            align-items: center;
            min-height: 100vh;
        }

        /* 상단 네비게이션 스타일 */
        header {
            width: 100%;
            background: #fff;
            padding: 15px 0;
            box-shadow: 0 2px 10px rgba(0,0,0,0.05);
            display: flex;
            justify-content: space-around;
            align-items: center;
            margin-bottom: 40px;
        }

        header a {
            text-decoration: none;
            color: #666;
            font-size: 0.9rem;
            margin: 0 10px;
        }

        header a:hover { color: #007bff; }

        /* 메인 컨테이너 */
        .container {
            width: 100%;
            max-width: 600px;
            background: #fff;
            padding: 40px;
            border-radius: 16px;
            box-shadow: 0 10px 25px rgba(0, 0, 0, 0.05);
            text-align: center;
        }

        h4 {
            font-size: 1.8rem;
            margin-bottom: 10px;
            color: #111;
        }

        .lang-switch {
            margin-bottom: 30px;
        }

        .lang-switch a {
            text-decoration: none;
            padding: 5px 12px;
            border: 1px solid #ddd;
            border-radius: 20px;
            font-size: 0.8rem;
            color: #888;
            margin: 0 3px;
        }

        /* 로그인 정보 카드 스타일 */
        .user-info-box {
            background: #f1f3f5;
            padding: 20px;
            border-radius: 12px;
            margin-bottom: 30px;
            text-align: left;
        }

        .user-name {
            font-size: 1.1rem;
            font-weight: 700;
            color: #007bff;
        }

        .roles {
            list-style: none;
            padding: 0;
            margin: 10px 0 0 0;
            display: flex;
            gap: 5px;
            flex-wrap: wrap;
        }

        .roles li {
            background: #dee2e6;
            padding: 3px 10px;
            border-radius: 4px;
            font-size: 0.75rem;
            color: #495057;
        }

        /* 링크 리스트 스타일 */
        .menu-list {
            list-style: none;
            padding: 0;
            margin: 20px 0;
        }

        .menu-list li {
            margin-bottom: 10px;
        }

        .menu-list a {
            display: block;
            padding: 15px;
            background: #fff;
            border: 1px solid #eee;
            border-radius: 10px;
            text-decoration: none;
            color: #333;
            font-weight: 500;
            transition: all 0.2s;
        }

        .menu-list a:hover {
            background: #f8f9fa;
            border-color: #007bff;
            color: #007bff;
            transform: translateY(-2px);
        }

        /* 버튼 스타일 */
        .btn-login, .btn-logout {
            display: inline-block;
            width: 100%;
            padding: 14px;
            border-radius: 10px;
            font-size: 1rem;
            font-weight: 600;
            cursor: pointer;
            text-decoration: none;
            border: none;
            transition: opacity 0.2s;
        }

        .btn-login { background-color: #007bff; color: white; width: 75%;}
        .btn-logout { background-color: #333; color: white; margin-top: 10px; }

        hr { border: 0; border-top: 1px solid #eee; margin: 30px 0; }
    </style>
</head>
<body>

<header>
    <div><strong>Web Study 02</strong></div>
    <div>
        <a href="/">Index.html</a>
    </div>
</header>

<div class="container">
    <h4>안녕하세요! 👋</h4>
    
    <div class="lang-switch">
        <a href="?lang=ko">KO</a>
        <a href="?lang=en">EN</a>
    </div>

    <c:if test="${empty pageContext.request.userPrincipal}">
        <p style="color: #888; margin-bottom: 25px;">서비스를 이용하시려면 로그인해 주세요.</p>
        <a href="<c:url value='/login' />" class="btn-login">로그인 하기</a>
    </c:if>

    <c:if test="${not empty pageContext.request.userPrincipal}">
        <div class="user-info-box">
            <span class="user-name">${pageContext.request.userPrincipal.name}님</span> 환영합니다.
            <ul class="roles">
                <c:forEach var="role" items="${pageContext.request.userPrincipal.authorities}">
                    <li>${role}</li>
                </c:forEach>
            </ul>
        </div>

        <ul class="menu-list">
            <c:if test="${pageContext.request.isUserInRole('ROLE_ADMIN')}">
                <li>
                    <a href="<c:url value='/admin/memberList'/>">🛡️ 관리자용 회원목록</a>
                </li>
            </c:if>
            <li>
                <a href="<c:url value='/member/mypage'/>">👤 마이페이지 확인</a>
            </li>
            <li>
                <a href="<c:url value='/member/changePassword'/>">🔑 비밀번호 변경</a>
            </li>
        </ul>

        <form action="/logout" method="post">
            <button type="submit" class="btn-logout">로그아웃</button>
        </form>
    </c:if>

    <hr>
    <p style="font-size: 0.8rem; color: #ccc;">© 2026 Web Study Team</p>
</div>

</body>
</html>