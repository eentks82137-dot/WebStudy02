<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Buyer List</title>
</head>
<body
    style="font-family: Pretendard, D2Coding;"
>
    <div 
        style="max-width: 80%; margin: auto; margin-top: 8rem; text-align: center;"
        >
        <h3>제조사 리스트</h3>
    <table 
        style="max-width: 100%;"
        class="table table-hover  table-bordered table-striped table-sm ">
        <thead >
            <tr>
                <th>제조사명</th>
                <th>제조사 분류코드</th>
                <th>제조사 소재지</th>
                <th>담당자명</th>
                <th>담당자 이메일</th>
            </tr>
        </thead>
        <tbody>
            <c:if test="${not empty buyerList}">
                <c:forEach var="buyer" items="${buyerList}">
                    <tr id="${buyer.buyerId}" style="cursor: pointer;">
                            <td> ${buyer.buyerName} </td>
                            <td> ${buyer.lprodGu} </td>
                            <td  style="text-align: left; padding-left: 2rem;"> ${buyer.buyerAdd1} ${buyer.buyerAdd2} ${buyer.buyerZip} </td>
                            <td> ${buyer.buyerCharger} </td>
                            <td style="text-align: left; padding-left: 2rem;"> ${buyer.buyerMail} </td>
                    </tr>

                </c:forEach>

            </c:if>
        
        </tbody>        
    </table>
    </div>
    <script>
        document.addEventListener("DOMContentLoaded", () => {
            document.querySelectorAll("tr").forEach(e=> {
                    e.addEventListener("click", ()=> {
                        <%-- window.open(`/buyer/detail?id=\${e.id}`) --%>
                        location.href=`/buyer/detail?id=\${e.id}`;
                })
            })
        })
    </script>
</body>
</html>