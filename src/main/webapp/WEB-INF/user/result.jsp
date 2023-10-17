<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>알림 페이지</title>
    <%
        Integer resultNum = Integer.parseInt(request.getParameter("resultNum"));
    %>
</head>
<body>
    <jsp:include page="../layout/header.jsp" flush="false" />
    <div class="margin-block"></div>
      <div class="jumbotron">
        <div class="container">
            <c:if test="<%=resultNum == 1%>">
                <h1 class="display-3">요청 결과가 성공적으로 처리되었습니다.</h1>
            </c:if>
            <c:if test="<%=resultNum == 0%>">
                <h1 class="display-3">요청 결과가 실패했습니다</h1>
            </c:if>
            <c:if test="<%=resultNum == 2%>">
                <h1 class="display-3">비밀번호 재설정</h1>
            </c:if>
        </div>
      </div>

    <c:if test="<%=resultNum == 2%>">
        <div class="main-container">
            <h3 class="display-3" style="text-align: center">비밀번호 재설정</h3>
            <div class="container">
                <form action="/user/resetPassword" method="post">
                    <label class="control-label">ID  <input type="text" name="memberId" class="form-control" value="<%=session.getAttribute("memberId")%>" readonly></label>
                    <label class="control-label">PW  <input type="password" name="passwd" class="form-control"></label>
                    <label class="control-label">PW 재확인 <input type="password" class="form-control"></label>
                    <input class="btn btn-primary" type="submit" value="재설정">
                </form>
            </div>
        </div>
    </c:if>

    <div class="container">
        <a href="/main">메인으로 돌아가기</a>
    </div>

    <jsp:include page="../layout/footer.jsp" flush="false" />
</body>
</html>
