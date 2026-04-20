<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>회원정보 수정</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/fonts.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/registration.css" />
</head>
<body>



<div class="register-card">
    <h2>회원정보 수정</h2>

    <form action="" method="post" autocomplete="off">

        <div class="section-title">계정 정보</div>

        <div class="form-group">
            <label for="memName">이름 <span class="-mark">*</span></label>
            <input type="text" id="memName" name="memName" placeholder="이름을 입력하세요"  value="${member != null ? member.memName : ''}">
            <span>
                ${errors.memName}
            </span>
        </div>

        <div class="form-row">
            <div class="form-group">
                <label for="memPass">비밀번호 <span class="-mark">*</span></label>
                <input type="password" id="memPass" name="memPass" placeholder="비밀번호를 입력하세요"  >
                <span>
                    ${errors.memPass}
                </span>
            </div>
        </div>


        

        <div class="form-group">
            <label for="memBir">생년월일</label>
            <input type="date" id="memBir" name="memBir" value="${member != null ? member.memBir : ''}">
        </div>

        <div class="section-title">주소</div>

        <div class="form-group">
            <label for="memZip">우편번호</label>
            <input type="text" id="memZip" name="memZip" placeholder="우편번호" maxlength="10" value="${member != null ? member.memZip : ''}">
        </div>

        <div class="form-group">
            <label for="memAdd1">기본 주소</label>
            <input type="text" id="memAdd1" name="memAdd1" placeholder="기본 주소를 입력하세요" value="${member != null ? member.memAdd1 : ''}">
        </div>

        <div class="form-group">
            <label for="memAdd2">상세 주소</label>
            <input type="text" id="memAdd2" name="memAdd2" placeholder="상세 주소를 입력하세요" value="${member != null ? member.memAdd2 : ''}">
        </div>

        <div class="section-title">연락처</div>

        <div class="form-row">
            <div class="form-group">
                <label for="memHometel">집 전화</label>
                <input type="text" id="memHometel" name="memHometel" placeholder="예) 02-1234-5678" value="${member != null ? member.memHometel : ''}">
            </div>
            <div class="form-group">
                <label for="memComtel">회사 전화</label>
                <input type="text" id="memComtel" name="memComtel" placeholder="예) 031-1234-5678" value="${member != null ? member.memComtel : ''}">
            </div>
        </div>

        <div class="form-row">
            <div class="form-group">
                <label for="memHp">휴대폰</label>
                <input type="text" id="memHp" name="memHp" placeholder="예) 010-1234-5678" value="${member != null ? member.memHp : ''}">
            </div>
            <div class="form-group">
                <label for="memMail">이메일</label>
                <input type="email" id="memMail" name="memMail" placeholder="예) example@email.com" value="${member != null ? member.memMail : ''}">
            </div>
        </div>

        <div class="section-title">기타 정보</div>

        <div class="form-row">
            <div class="form-group">
                <label for="memJob">직업</label>
                <input type="text" id="memJob" name="memJob" placeholder="직업을 입력하세요" value="${member != null ? member.memJob : ''}">
            </div>
            <div class="form-group">
                <label for="memLike">관심사</label>
                <input type="text" id="memLike" name="memLike" placeholder="관심사를 입력하세요" value="${member != null ? member.memLike : ''}">
            </div>
        </div>

        <div class="form-row">
            <div class="form-group">
                <label for="memMemorial">기념일명</label>
                <input type="text" id="memMemorial" name="memMemorial" placeholder="예) 결혼기념일" value="${member != null ? member.memMemorial : ''}">
            </div>
            <div class="form-group">
                <label for="memMemorialday">기념일</label>
                <input type="date" id="memMemorialday" name="memMemorialday" value="${member != null ? member.memMemorialday : ''}">
            </div>
        </div>

        <div class="btn-area">
            <button type="submit">정보 수정</button>
        </div>

    </form>
</div>
    <script>
        document.addEventListener('DOMContentLoaded', function() {
            document.querySelector('form').addEventListener('submit', async function(event) {
                
            });
        });
    </script>
</body>
</html>
