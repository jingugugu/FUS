<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>배송정보</title>
</head>
<body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js" integrity="sha384-b5kHyXgcpbZJO/tY9Ul7kGkf1S0CWuKcCD38l8YkeH8z8QjE0GmW1gYU5S9FOnJ0" crossorigin="anonymous"></script>
<jsp:include page="../layout/header.jsp?12" />

<div class="margin-block"></div>

<div class="jumbotron">
    <div class="container">
        <h1 class="display-3">주문 완료!!</h1>
    </div>
</div>

<div class="form-group row">
    <div class="col-sm-10">
        <a href="/main" class="btn btn-secondary" role="button">Home</a>
    </div>
</div>
<jsp:include page="../layout/footer.jsp?12" />
</body>
</html>