<%@ page contentType="text/html; charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>게시판</title>
</head>
<body>
<jsp:include page="../layout/header.jsp?12" />
<div class="container">
    <h2>게시판 목록</h2>
    <hr>
    <ul class="list-group">
        <c:forEach var="boardDTO" items="${boardDTOList}" varStatus="status">
            <li >
            <a href="/board/view?boardNum=${boardDTO.boardNum}">
                [${status.count}] ${boardDTO.title}, ${boardDTO.boardNum}</a>
                <a href="/board/remove?boardNum=${boardDTO.boardNum}">
                    <span class="badge bg-secondary"></span></a>
            </li>
        </c:forEach>
    </ul>
    <hr>

    <c:if test="${error != null}">
        <div class="alert alert-danger alert-dismissible fade show mt-3">
            에러 발생: ${error}
            <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
        </div>
    </c:if>

    <button class="btn btn-outline-info mb3" type="button" href="/board/add">뉴스 등록</button>
<%--    <div class="card card-body">--%>
<%--        <form method="post" action="./add.news?action=add" enctype="multipart/form-data">--%>
<%--            <label class="form-lable">제목</label>--%>
<%--            <input type="text" name="title" class="form-control">--%>
<%--            <label class="form-lable">이미지</label>--%>
<%--            <input type="file" name="file" class="form-control">--%>
<%--            <label class="form-lable">기사내용</label>--%>
<%--            <textarea cols="50" rows="5" name="content" class="form-control"></textarea>--%>
<%--            <button type="submit" class="btn btn-success mt-3">저장</button>--%>
<%--        </form>--%>
<%--    </div>--%>
</div>

<jsp:include page="../layout/footer.jsp?12" />
</body>
</html>