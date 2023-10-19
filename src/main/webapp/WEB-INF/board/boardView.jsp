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
    <div class="form-group row">
        <label class="col-sm-2 control-label">User</label>
        <div class="col-sm-3">
            <%=board.getName()%>
        </div>
    </div>

    <div class="form-group row">
        <label class="col-sm-2 control-label">Title</label>
        <div class="col-sm-5">
            <%=board.getTitle()%>
        </div>
    </div>

    <div class="form-group row">
        <label class="col-sm-2 control-label">Content</label>
        <div class="col-sm-8" style="word-break: break-all; height: 200px">
            <img class="card-img-top" src="Users/ieunseo/Desktop/dev/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/webapps/upload/board<%=board.getFileName()%>">
            <%=board.getContent()%>
        </div>
    </div>


    <form name="frmRippleView" method="post">
        <input type="hidden" name="num" value="<%=board.getBoardNum()%>">
    </form>

    <div class="form-group row">
        <div class="col-sm-offset-2 col-sm-10">
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
            </div>

            <!--리플 목록 출력 영역 -->
            <div class="user-ripple-list">
                <ul>
                    <li></li>
                </ul>
            </div>

            <!-- 리플 목록 출력 영역 -->
            <form name="frmRippleView" method="post">
                <input type="hidden" name="boardNum" value="<%=board.getBoardNum()%>">
            </form>

            <!-- 리플 시작 -->
            <c:if test="${loginInfo != null}">
                <form name="frmRipple" method="post">
                    <div class="write-ripple-area">
                        <input type="hidden" name="boardNum" value="<%=boardNum%>"> <!--게시물의 번호-->
                        <input name="name" type="text" class="ripple-form-name" value="${loginInfo.name}" placeholder="name">
                        <div class="ripple-content-box">
                            <textarea name="content" class="ripple-form-content" cols="50" rows="3"></textarea>
                            <span class="ripple-submit-btn" id="goRippleSubmit">등록</span>
                        </div>
                    </div>
                </form>
            </c:if>
            <!--//리플 끝 -->

        </div>
    </div>
</div>
<div class="margin-block"></div>
<jsp:include page="../layout/footer.jsp" flush="false" />
</body>
</html>
