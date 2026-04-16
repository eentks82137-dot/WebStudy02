<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>마이페이지</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/fonts.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/mypage.css" />
</head>
<body>

    <c:if test="${not empty member}">
        <div class="profile-card">
            <h2>내 정보 확인</h2>

            
            <div class="info-group">
                <span class="info-label">아이디</span>
                <span class="info-value">${member.memId}</span>
            </div>

            <div class="info-group">
                <span class="info-label">이름</span>
                <span class="info-value">${member.memName}</span>
            </div>

            <div class="info-group">
                <span class="info-label">주민번호</span>
                <span class="info-value">${member.memRegno1} - ${member.memRegno2}</span>
            </div>

            <div class="info-group">
                <span class="info-label">생년월일</span>
                <span class="info-value">${member.memBir}</span>
            </div>

            <div class="info-group">
                <span class="info-label">주소</span>
                <span class="info-value">
                    ${member.memAdd1} ${member.memAdd2}<br>
                    <small style="color:#aaa;">(${member.memZip})</small>
                </span>
            </div>

            <hr style="border: 0; border-top: 1px solid #eee; margin: 20px 0;">

            <div class="info-group">
                <span class="info-label">연락처(집)</span>
                <span class="info-value">${member.memHometel}</span>
            </div>

            <div class="info-group">
                <span class="info-label">연락처(직장)</span>
                <span class="info-value">${member.memComtel}</span>
            </div>

            <div class="info-group">
                <span class="info-label">휴대폰</span>
                <span class="info-value">${member.memHp}</span>
            </div>

            <div class="info-group">
                <span class="info-label">이메일</span>
                <span class="info-value">${member.memMail}</span>
            </div>

            <hr style="border: 0; border-top: 1px solid #eee; margin: 20px 0;">

            <div class="info-group">
                <span class="info-label">직업</span>
                <span class="info-value">${member.memJob}</span>
            </div>

            <div class="info-group">
                <span class="info-label">취미</span>
                <span class="info-value">${member.memLike}</span>
            </div>

            <div class="info-group">
                <span class="info-label">마일리지</span>
                <span class="info-value highlight">${member.memMileage} P</span>
            </div>

            <div class="info-group">
                <span class="info-label">권한</span>
                <span class="info-value">${member.memRoles}</span>
            </div>
    
            <div class="btn-area">
                <a href="${pageContext.request.contextPath}/member/changePassword" class="btn-change-password">비밀번호 변경</a>
                <a href="${pageContext.request.contextPath}/" class="btn-home">메인 화면으로</a>
            </div>
        </div>
    </c:if>

    <c:if test="${empty member}">
        <div class="profile-card">
            <p class="no-data">회원 정보가 존재하지 않습니다.</p>
        </div>
        <div class="btn-area">
                <a href="${pageContext.request.contextPath}/" class="btn-home">메인 화면으로</a>
        </div>
    </c:if>
    

</body>
</html>