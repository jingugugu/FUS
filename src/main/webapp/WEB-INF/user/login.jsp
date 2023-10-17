<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>로그인</title>
</head>
<body>
<jsp:include page="../layout/header.jsp" flush="false" />
<div class="margin-block"></div>
    <div class="jumbotron">
        <div class="container">
            <h1 class="display-3">로그인</h1>
        </div>
    </div>
    <div class="main-container">
        <div class="container">
            <form action="/user/login" method="post">
                <c:if test="${param.error eq '1'}">
                    <h4 class="bg-danger">로그인 정보가 잘못되었습니다</h4>
                </c:if>
                <label class="control-label">ID  <input type="text" name="memberId" class="form-control"></label>
                <label class="control-label">PW  <input type="password" name="passwd" class="form-control"></label>
                <input class="btn btn-primary" type="submit" value="로그인">
            </form>
        </div>

        <div class="container">
            <div class="btn-wrap">
                <a href="/user/register" style="margin-right: 30px">회원가입</a>
                <a href="/user/findPassword">비밀번호 찾기</a>
            </div>
        </div>
    </div>





<jsp:include page="../layout/footer.jsp" flush="false" />
</body>
</html>
