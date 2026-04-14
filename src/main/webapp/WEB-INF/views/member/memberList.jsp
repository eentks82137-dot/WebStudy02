<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Member List</title>

    <style>
    table {
  width: 80%;
  border-collapse: collapse; /* Removes double borders */
  table-layout: fixed;      /* Faster rendering & fixed widths */
  margin:auto;
}

th, td {
  padding: 12px;
  text-align: center;
  border-bottom: 1px solid #ddd; /* Horizontal dividers only */
}

thead th {
  background-color: #f2f2f2;
  font-weight: bold;
}

/* Zebra Striping */
tbody tr:nth-child(even) {
  background-color: #fafafa;
}

/* Interaction */


    </style>
</head>
<body>
    <h1>Member List</h1>
    <table border="1">
        <thead>
            <tr>
                <th style="width: 80px;">ID</th>
                <th style="width: 120px;">회원명</th>
                <th style="width: 200px;">전화번호</th>
                <th style="width: 200px;">이메일</th>
                <th>거주지역</th>
                <th>역할</th>
            </tr>
        </thead>
        <tbody>
            <c:choose>
                <c:when test="${not empty memberList}">
                    <c:forEach var="member" items="${memberList}">
                        <tr>
                            <td>${member.memId}</td>
                            <td>${member.memName}</td>
                            <td>${member.memHp}</td>
                            <td>${member.memMail}</td>
                            <td style="text-align: left;">${member.memAdd1} ${member.memAdd2} ${member.memZip}</td>
                            <td>${member.memRoles}</td>
                        </tr>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <tr>
                        <td colspan="6">조건에 맞는 회원 없음</td>
                    </tr>
                </c:otherwise>
            </c:choose>
        </tbody>
    </table>
</body>
</html>