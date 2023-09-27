<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>로그인</title>
</head>
<body>
<jsp:include page="../layout/header.jsp" flush="false" />

    <div class="jumbotron">
        <div class="container">
            <h1 class="display-3">글 등록</h1>
        </div>
    </div>

    <div class="container">
        <form action="/user/login" method="post">
            <label class="control-label">ID : <input type="text" name="memberId" class="form-control"></label>
            <label class="control-label">PW : <input type="password" name="passwd" class="form-control"></label>
            <input class="btn btn-primary" type="submit" value="로그인">
        </form>
    </div>





<jsp:include page="../layout/footer.jsp" flush="false" />
</body>
</html>
