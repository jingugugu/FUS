<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.example.fus.dto.BoardDTO" %>
<%@ page import="com.example.fus.dto.UserDTO" %>
<%
    BoardDTO boardDTO = (BoardDTO) request.getAttribute("editBoardDTO");
    UserDTO userDTO = (UserDTO) session.getAttribute("loginInfo");
    if(userDTO == null){
        response.sendRedirect("/user/register");
    } else if(!userDTO.getMemberId().equals(boardDTO.getMemberId())) {
        response.sendRedirect("/main");
    }
%>
<html>
<head>
    <title>modify</title>
</head>
<body>
<jsp:include page="../layout/header.jsp" flush="false" />
<div class="margin-block"></div>
<div class="jumbotron">
    <div class="container">
        <h1 class="display-3">게시판</h1>
    </div>
</div>

<form id="ModifyFrm" action="/board/modify.do" method="post" enctype="multipart/form-data">
<!--제목-->
    <div class="form-group-title1" align="center">
        <label class="control-label">
            <input type="hidden" name="boardNum" value="<%=boardDTO.getBoardNum()%>">
            <input type="text" name="title" class="text-title" minlength="5" maxlength="100" placeholder="제목" value="<%=boardDTO.getTitle()%>" readonly>
            <!--파일-->
            <div>
                <label class="control-label"><input type="file" name="file"></label>
            </div>
        </label>
    </div>

    <!--내용-->
    <div align="center">
        <label class="control-label-content">
            <textarea class="board-text" name="content" rows="45" cols="100" maxlength="1000" minlength="10" placeholder="내용을 입력하세요."><%=boardDTO.getContent()%></textarea>
        </label>
    </div>



    <div align="center">
        <label class="control-label" >
            <input type="submit" value="수정하기">
        </label>
    </div>


</form>
<jsp:include page="../layout/footer.jsp" flush="false" />
</body>
</html>
