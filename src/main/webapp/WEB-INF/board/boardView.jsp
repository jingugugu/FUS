<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>FUS 게시판</title>
</head>
<body>
<div class="container w-75 mt-5 mx-auto">
    <h2>${boardDTO.title}</h2>
    <hr>
    <div class="container w-75 mx-auto">
        <img class="card-img-top" src="/upload/fus/board/${boardDTO.fileName}">
        <div class="card-title">
            <h4 class="card-title">Date: ${boardDTO.addDate}</h4>
            <p class="card-text">Content: ${boardDTO.content}</p>
        </div>
    </div>
    <a href="javascript:history.back()" class="btn btn-primary"><<</a>
</div>

</body>
</html>
