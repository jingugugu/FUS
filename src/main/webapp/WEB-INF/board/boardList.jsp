<%@ page contentType="text/html; charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>게시판</title>
    <%
        // 페이징 세팅
        int pagePerBlock = 10; // 페이지 출력 시 나올 범위.
        int pageNum = Integer.parseInt((String) request.getAttribute("pageNum"));
        int count =  Integer.parseInt((String)request.getAttribute("count")); // 총 게시물 갯수
        int totalPage = count % 20 == 0 ? (count / 20) : (count / 20) + 1; // 총 페이지
        // 총 게시물 갯수를 pagePerBlock으로 나눴을 때 나머지 없이 딱 떨어지면 바로 몫으로 페이징, 나머지가 있으면 +1 해서 페이징
        int totalBlock = totalPage % pagePerBlock == 0 ? totalPage / pagePerBlock : totalPage / pagePerBlock + 1; // 6 % 5 0
        int thisBlock = ((pageNum - 1) / pagePerBlock) + 1; // 현재 블럭
        int firstPage = ((thisBlock - 1) * pagePerBlock) + 1; // 블럭의 첫 페이지
        int lastPage = thisBlock * pagePerBlock; // 블럭의 마지막 페이지
        lastPage = (lastPage > totalPage) ? totalPage : lastPage;
    %>
    <script src="https://kit.fontawesome.com/4ae8b5f015.js" crossorigin="anonymous"></script> <%-- 폰트어썸(아이콘 사이트) --%>
</head>
<body>
<jsp:include page="../layout/header.jsp?12" />
<div class="margin-block"></div>
<c:set var="totalPage" value="<%=totalPage%>" />
<c:set var="pagePerBlock" value="<%=pagePerBlock%>" />
<c:set var="totalBlock" value="<%=totalBlock%>" />
<c:set var="thisBlock" value="<%=thisBlock%>" />
<c:set var="firstPage" value="<%=firstPage%>" />
<c:set var="lastPage" value="<%=lastPage%>" />
<c:set var="pageNum" value="<%=pageNum%>" />
<div class="margin-block"></div>
<div class="board-container w-75 mt-5 mx-auto">
    <h2>게시판 목록</h2>
    <hr>
    <div style="padding-top: 50px">
        <table class="table table-hover">
                <tr class="board-flex-row">
                    <th class="board-num">번호</th>
                    <th class="board-title">제목</th>
                    <th class="board-count">조회수</th>
                    <th class="board-date">등록일</th>
                </tr>
            <c:forEach var="boardDTO" items="${boardDTOList}" varStatus="status">
                <c:set var="dueDate" value="${boardDTO.addDate.split(' ')[0]}" />
                <tr class="board-flex-row">
                    <td class="board-num">${status.count}</td>
                    <td class="board-title">
                        <a href="/board/view?boardNum=${boardDTO.boardNum}&pageNum=${pageNum}">${boardDTO.title}</a>
                    </td>
                    <td class="board-count">${boardDTO.count}</td>
                    <td class="board-date">${dueDate}</td>
                    <a href="/board/remove?boardNum=${boardDTO.boardNum}">
                        <span class="badge bg-secondary"></span>
                    </a>
                </tr>
            </c:forEach>
        </table>
    </div>
    <hr>

    <c:if test="${error != null}">
        <div class="alert alert-danger alert-dismissible fade show mt-3">
            에러 발생: ${error}
            <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
        </div>
    </c:if>

    <button class="btn btn-outline-info mb3" type="button" disabled><a href="/board/add">게시글 등록</a></button>
</div>

<div class="block-btn-wrap">
    <a href="<c:url value="/board/all?pageNum=1" />"><span><i class="fa-solid fa-angles-left"></i></span></a>
    <c:if test="${thisBlock > 1}">
        <a href="<c:url value="/board/all?pageNum=${firstPage - 1}" />"><span><i class="fa-solid fa-angle-left"></i></span></a>
    </c:if>

    <c:forEach var="i" begin="${firstPage}" end="${lastPage}">
        <a href="/board/all?pageNum=${i}">
            <c:choose>
                <c:when test="${pageNum==i}">
                    <span style="color: #4C5317;font-weight: bold"> [${i}] </span>
                </c:when>
                <c:otherwise>
                    <span style="color: #4C5317;"> [${i}] </span>
                </c:otherwise>
            </c:choose>
        </a>
    </c:forEach>

    <c:if test="${thisBlock < totalBlock}" >
        <a href="<c:url value="/board/all?pageNum=${lastPage + 1}" />"><span><i class="fa-solid fa-angle-right"></i></span></a>
    </c:if>
    <a href="<c:url value="/board/all?pageNum=${totalPage}" />"><span><i class="fa-solid fa-angles-right"></i></span></a>
</div>


<jsp:include page="../layout/footer.jsp?12" />
</body>
</html>