<%@ page import="com.example.fus.dto.UserDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<script src="/assets/js/user/myPage.js"></script>
<%
    UserDTO userDTO = (UserDTO) session.getAttribute("loginInfo");

%>
<body>
<jsp:include page="../layout/header.jsp" flush="false" />
<div class="margin-block"></div>
    <div class="jumbotron">
        <div class="container">
            <h1 class="display-3">마이페이지</h1>
        </div>
    </div>

    <div class="user-container">

        <div class="user-info">
            <p><span>이름 </span> <%=userDTO.getName()%></p>
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
                <button class="contents-category" value="review">리뷰</button>
                <button class="contents-category" value="board">게시글</button>
                <button class="contents-category" value="ripple">댓글</button>
            </div>

            <div class="user-review">
                <h4 class="display-1">리뷰 목록</h4>
                <table>
                    <tr>
                        <th>상품명</th>
                        <th>가격</th>
                        <th class="content">내용</th>
                        <th>작성일</th>
                    </tr>
                    <tr>
                        <td> <a href="/product/view/productId=?">상품명11</a> </td>
                        <td> 27,900 </td>
                        <td class="content"> 너무좋아요 맘에듭니다 </td>
                        <td> 2023-10-02 </td>
                    </tr>
                    <tr>
                        <td> <a href="/product/view/productId=?">상품명22</a> </td>
                        <td> 55,900 </td>
                        <td class="content"> 너무좋아요 최고에요 </td>
                        <td> 2023-10-01 </td>
                    </tr>
                </table>
            </div>

            <div class="user-board" style="display: none">
                <h4 class="display-1">게시글 목록</h4>
                <table>
                    <tr>
                        <th>구분</th>
                        <th class="content">제목</th>
                        <th>작성일</th>
                    </tr>
                    <c:forEach var="board" items="${userBoards}" varStatus="status">
                        <tr>
                            <td>[${status.count}]</td>
                            <td><a href="/board/view?boardNum=${board.boardNum}">${board.title}</a></td>
                            <td>${board.addDate}</td>
                        </tr>
                    </c:forEach>
                </table>
            </div>

            <div class="user-ripple" style="display: none">
                <h4 class="display-1">댓글 목록</h4>
                <table>
                    <tr>
                        <th>구분</th>
                        <th class="content">내용</th>
                        <th>작성일</th>
                    </tr>
                    <c:forEach var="ripple" items="${userRipples}" varStatus="status">
                        <tr>
                            <td>[${status.count}]</td>
                            <td><a href="/board/view?boardNum=${ripple.boardNum}">${ripple.content}</a></td>
                            <td>${ripple.insertDate}</td>
                        </tr>
                    </c:forEach>
                </table>
            </div>

        </div>

    </div>

<jsp:include page="../layout/footer.jsp" flush="false" />
</body>
</html>
