<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>회원 상세 정보</title>

    <style>
        .detail-label { background-color: #f8f9fa; font-weight: bold; width: 20%; }
        .detail-value { width: 30%; }
    </style>

 
</head>
<body>

<c:if test="${not empty errors}"> 
    <script>
        alert("${errors}");
    </script>
    <c:remove var="errors" scope="session" />
</c:if>


<div class="container mt-5 mb-5">
    <div class="card shadow">
        <div class="card-header bg-primary text-white d-flex justify-content-between align-items-center">
            <h4 class="mb-0">회원 상세 프로필</h4>
            <span class="badge bg-light text-primary">${member.memId}</span>
        </div>
        
        <div class="card-body">
            <h5 class="border-bottom pb-2 mb-3"><i class="bi bi-person-fill"></i> 기본 정보</h5>
            <table class="table table-bordered">
                <tr>
                    <td class="detail-label">아이디</td>
                    <td class="detail-value">${member.memId}</td>
                    <td class="detail-label">성명</td>
                    <td class="detail-value">${member.memName}</td>
                </tr>
                <tr>
                    <td class="detail-label">주민번호</td>
                    <td class="detail-value">${member.memRegno1} - ${member.memRegno2}</td>
                    <td class="detail-label">생년월일</td>
                    <td class="detail-value">${member.memBir}</td>
                </tr>
                <tr>
                    <td class="detail-label">권한</td>
                    <td colspan="3">
                        <c:forEach var="role" items="${member.memRoles}">
                            <span class="badge bg-secondary">${role}</span>
                        </c:forEach>
                    </td>
                </tr>
            </table>

            <h5 class="border-bottom pb-2 mt-4 mb-3"><i class="bi bi-telephone-fill"></i> 연락처 및 주소</h5>
            <table class="table table-bordered">
                <tr>
                    <td class="detail-label">휴대폰</td>
                    <td class="detail-value">${member.memHp}</td>
                    <td class="detail-label">이메일</td>
                    <td class="detail-value">${member.memMail}</td>
                </tr>
                <tr>
                    <td class="detail-label">집 전화</td>
                    <td class="detail-value">${member.memHometel}</td>
                    <td class="detail-label">회사 전화</td>
                    <td class="detail-value">${member.memComtel}</td>
                </tr>
                <tr>
                    <td class="detail-label">우편번호</td>
                    <td colspan="3">${member.memZip}</td>
                </tr>
                <tr>
                    <td class="detail-label">주소</td>
                    <td colspan="3">
                        ${member.memAdd1} <br>
                        <small class="text-muted">${member.memAdd2}</small>
                    </td>
                </tr>
            </table>

            <h5 class="border-bottom pb-2 mt-4 mb-3"><i class="bi bi-plus-circle-fill"></i> 부가 정보</h5>
            <table class="table table-bordered">
                <tr>
                    <td class="detail-label">직업</td>
                    <td class="detail-value">${member.memJob}</td>
                    <td class="detail-label">취미</td>
                    <td class="detail-value">${member.memLike}</td>
                </tr>
                <tr>
                    <td class="detail-label">기념일</td>
                    <td class="detail-value">${member.memMemorial}</td>
                    <td class="detail-label">기념일 날짜</td>
                    <td class="detail-value">${member.memMemorialday}</td>
                </tr>
                <tr>
                    <td class="detail-label">마일리지</td>
                    <td class="detail-value">
                        <span class="text-primary fw-bold">${member.memMileage}</span> 점
                    </td>
                    <td class="detail-label">탈퇴 여부</td>
                    <td class="detail-value">
                        <c:choose>
                            <c:when test="${member.memDelete eq 'Y'}">
                                <span class="badge bg-danger">탈퇴</span>
                            </c:when>
                            <c:otherwise>
                                <span class="badge bg-success">활동중</span>
                            </c:otherwise>
                        </c:choose>
                    </td>
                </tr>
            </table>
        </div>

        <div class="card-footer text-end">
            <a href="memberList.do" class="btn btn-outline-secondary">목록으로</a>
            <a href="memberUpdate.do?id=${member.memId}" class="btn btn-warning">정보 수정</a>
            <button type="button" class="btn btn-danger btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal">
            회원 탈퇴
            </button>


        </div>
    </div>
</div>

<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">회원 탈퇴 확인</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        <form action="/member/leave-out" method="POST">
                <p>정말 탈퇴하시겠습니까?</p>
                <input type="password" name="memPass" id="memPass" placeholder="비밀번호 입력">
                <button type="submit">
                    회원 탈퇴
                </button>

                </form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary">Save changes</button>
      </div>
    </div>
  </div>
</div>
</body>
</html>