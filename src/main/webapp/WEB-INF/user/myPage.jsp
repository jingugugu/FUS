<%@ page import="com.example.fus.dto.UserDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<script src="/assets/js/user/myPage.js?"></script>
<script src="https://kit.fontawesome.com/4ae8b5f015.js" crossorigin="anonymous"></script> <%-- 폰트어썸(아이콘 사이트) --%>
<%
    UserDTO userDTO = (UserDTO) session.getAttribute("loginInfo");

%>
<body>
<jsp:include page="../layout/header.jsp?" flush="false" />
<div class="margin-block"></div>
    <div class="jumbotron">
        <div class="container">
            <h1 class="display-3">마이페이지</h1>
        </div>
    </div>

    <div class="user-container">

        <div class="user-info">
            <p><span>이름 </span> <%=userDTO.getUserName()%></p>
            <p><span>생성일 </span> <%=userDTO.getAddDate()%></p>
            <p>
                <a href="/user/edit">정보수정</a>
                <form name="removeFrm" action="" method="post">
                    <a style="cursor: pointer" id="remove-user-btn" data-id="<%=userDTO.getMemberId()%>">회원탈퇴</a>
                </form>
            </p>
        </div>

        <div class="user-contents">

            <div class="user-contents-category">
                <button class="contents-category" value="shipping">구매내역</button>
                <button class="contents-category" value="review">리뷰</button>
                <button class="contents-category" value="board">게시글</button>
                <button class="contents-category" value="ripple">댓글</button>
            </div>

            <div class="user-shipping">
                <h4 class="display-1">구매내역 목록</h4>
                <table class="mypage-category-table">
                    <tr>
                        <th>상품번호</th>
                        <th>개수</th>
                        <th class="content">상품명</th>
                        <th>구매일</th>
                    </tr>
                    <c:choose>
                        <c:when test="${userShippings.size() > 0}">
                            <c:forEach var="shipping" items="${userShippings}">
                                <tr>
                                    <td class="padding-left-sixteen">${shipping.productId}</td>
                                    <td>${shipping.count}</td>
                                    <td><a href="/product/view?productId=${shipping.productId}">${shipping.productName}</a></td>
                                    <td>${shipping.orderDate.split(" ")[0]}</td>
                                </tr>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <tr>
                                <td colspan="4" class="text-center"><p>최근 구매내역이 없습니다</p></td>
                            </tr>
                        </c:otherwise>
                    </c:choose>
                </table>
            </div>

            <div class="user-review" style="display: none">
                <h4 class="display-1">리뷰 목록</h4>
                <table class="mypage-category-table">
                    <tr>
                        <th>상품명</th>
                        <th>별점</th>
                        <th class="content">내용</th>
                        <th>작성일</th>
                    </tr>
                    <c:choose>
                        <c:when test="${userReviews.size() > 0}">
                            <c:forEach var="review" items="${userReviews}">
                                <tr>
                                    <td>${review.productName}</td>
                                    <td class="mypage-review-rate">
                                        <c:forEach begin="1" end="${review.rate}">
                                            ★
                                        </c:forEach>
                                    </td>
                                    <td><a href="/product/view?productId=${review.productId}">${review.content}</a></td>
                                    <td>${review.addDate.split(" ")[0]}</td>
                                </tr>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <tr>
                                <td colspan="4" class="text-center"><p>작성한 리뷰가 없습니다</p></td>
                            </tr>
                        </c:otherwise>
                    </c:choose>
                </table>
            </div>

            <div class="user-board" style="display: none">
                <h4 class="display-1">게시글 목록</h4>
                <table class="mypage-category-table">
                    <tr>
                        <th>구분</th>
                        <th>이름</th>
                        <th class="content">제목</th>
                        <th>작성일</th>
                    </tr>
                    <c:choose>
                        <c:when test="${userBoards.size() > 0}">
                            <c:forEach var="board" items="${userBoards}" varStatus="status">
                                <tr>
                                    <td class="padding-left-sixteen">${status.count}</td>
                                    <td>${board.name}</td>
                                    <td><a href="/board/view?boardNum=${board.boardNum}">${board.title}</a></td>
                                    <td>${board.addDate.split(" ")[0]}</td>
                                </tr>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <tr>
                                <td colspan="4" class="text-center"><p>작성한 게시글이 없습니다</p></td>
                            </tr>
                        </c:otherwise>
                    </c:choose>
                </table>
            </div>

            <div class="user-ripple" style="display: none">
                <h4 class="display-1">댓글 목록</h4>
                <table class="mypage-category-table">
                    <tr>
                        <th>구분</th>
                        <th>이름</th>
                        <th class="content">내용</th>
                        <th>작성일</th>
                    </tr>
                    <c:choose>
                        <c:when test="${userRipples.size() > 0}">
                            <c:forEach var="ripple" items="${userRipples}" varStatus="status">
                                <tr>
                                    <td class="padding-left-sixteen">${status.count}</td>
                                    <td>${ripple.name}</td>
                                    <td><a href="/board/view?boardNum=${ripple.boardNum}">${ripple.content}</a></td>
                                    <td>${ripple.insertDate.split(" ")[0]}</td>
                                </tr>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <tr>
                                <td colspan="4" class="text-center"><p>작성한 댓글이 없습니다</p></td>
                            </tr>
                        </c:otherwise>
                    </c:choose>
                </table>
            </div>
            <span class="mypage-category-toggle" data-state="on"><i class="fa-solid fa-arrow-down"></i> 전체 펼치기</span>
            <span class="mypage-category-toggle" data-state="off" style="display: none"><i class="fa-solid fa-arrow-up"></i> 접기 </span>
        </div>

    </div>

<jsp:include page="../layout/footer.jsp" flush="false" />
</body>
</html>
