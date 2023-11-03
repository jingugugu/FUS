<%@ page contentType="text/html; charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.example.fus.dto.BoardDTO" %>
<%@ page import="com.example.fus.dto.RippleDTO" %>
<%@ page import="com.example.fus.dto.UserDTO" %>

<%
    String boardNum = request.getParameter("boardNum");
    BoardDTO board = (BoardDTO) request.getAttribute("boardDTO");
    String pageNum = request.getParameter("pageNum");
    String memberId = "";
    if(session.getAttribute("loginInfo") != null){
        UserDTO userDTO = (UserDTO) session.getAttribute("loginInfo");
        memberId = userDTO.getMemberId();
    }
    RippleDTO ripple = (RippleDTO) request.getAttribute("ripple");
%>
<html>
<head>
    <title>BoardView</title>
    <script src="/assets/js/board/ripple.js"></script>
    <script src="/assets/js/board/board.js"></script>
    <script src="https://kit.fontawesome.com/4ae8b5f015.js" crossorigin="anonymous"></script> <%-- 폰트어썸(아이콘 사이트) --%>
</head>
<body>
<jsp:include page="../layout/header.jsp" flush="false" />
<div class="margin-block"></div>
<div class="jumbotron">
    <div class="container">
        <h1 class="display-3">게시판</h1>
    </div>
</div>

<div class="container">

    <div class="flex-board">
        <span class="flex-board-span">User</span>
        <div><%=board.getName()%></div>
    </div>
    <hr>

    <div class="flex-board">
        <span class="flex-board-span">Title</span>
        <div><%=board.getTitle()%></div>
    </div>
    <hr>

    <div class="flex-board">
        <span class="flex-board-span">Content</span>
        <div>
            <% if(!(board.getFileName().equals("")) ) { %>
                <img class="card-img-top" src="/upload/fus/board/<%=board.getFileName()%>"><br>
            <% } %>
            <%=board.getContent()%>
        </div>
    </div>
    <hr>

    <form name="frmRippleView" method="post">
        <input type="hidden" name="num" value="<%=board.getBoardNum()%>">
    </form>

    <div class="form-group row">
        <div class="col-sm-offset-2 col-sm-10" style="margin-left: 0px">
            <form name="frmView" method="post">
                <input type="hidden" name="pageNum" value="<%=pageNum%>">
                <input type="hidden" name="num" value="<%=board.getBoardNum()%>">
            </form>
            <div class="btns-wrap">
                <span class="purple-btn-custom"><a href="../board/all?pageNum=<%=pageNum%>">목록</a></span>
                <%
                    if( memberId.equals(board.getMemberId())){
                %>
                <div class="btns-rm">
                    <span class="purple-btn btn-modify"> 수정 </span>
                    <span class="red-btn btn-remove"> 삭제 </span>
                </div>

                <%
                    }
                %>
                <%
                    if( ( !memberId.equals(board.getMemberId()) ) && memberId.equals("testman3")){
                %>
                    <div class="btns-rm">
                        <span class="red-btn btn-remove"> 삭제 </span>
                    </div>
                <%
                    }
                %>
            </div>

            <!--리플 목록 출력 영역 -->
            <div class="user-ripple-list">
                <ul>
                    <li></li>
                </ul>
                <!-- 리플 시작 -->
                <c:if test="${loginInfo != null}">
                    <form name="frmRipple" method="post">
                        <div class="write-ripple-area">
                            <input type="hidden" name="boardNum" value="<%=boardNum%>"> <!--게시물의 번호-->
                            <input name="name" type="text" class="ripple-form-name" value="${loginInfo.userName}" placeholder="name">
                            <div class="ripple-content-box">
                                <textarea name="content" class="ripple-form-content" cols="50" rows="3" maxlength="300" minlength="5"></textarea>
                                <span class="ripple-submit-btn" id="goRippleSubmit">등록</span>
                            </div>
                        </div>
                    </form>
                </c:if>
                <!--//리플 끝 -->
            </div>

            <!-- 리플 목록 출력 영역 -->
            <form name="frmRippleView" method="post">
                <input type="hidden" name="boardNum" value="<%=board.getBoardNum()%>">
            </form>

<%--            <!-- 리플 시작 -->--%>
<%--            <c:if test="${loginInfo != null}">--%>
<%--                <form name="frmRipple" method="post">--%>
<%--                    <div class="write-ripple-area">--%>
<%--                        <input type="hidden" name="boardNum" value="<%=boardNum%>"> <!--게시물의 번호-->--%>
<%--                        <input name="name" type="text" class="ripple-form-name" value="${loginInfo.userName}" placeholder="name">--%>
<%--                        <div class="ripple-content-box">--%>
<%--                            <textarea name="content" class="ripple-form-content" cols="50" rows="3" maxlength="300" minlength="5"></textarea>--%>
<%--                            <span class="ripple-submit-btn" id="goRippleSubmit">등록</span>--%>
<%--                        </div>--%>
<%--                    </div>--%>
<%--                </form>--%>
<%--            </c:if>--%>
<%--            <!--//리플 끝 -->--%>

        </div>
    </div>
</div>
<div class="margin-block"></div>
<jsp:include page="../layout/footer.jsp" flush="false" />
</body>
</html>
