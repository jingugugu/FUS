<%@ page import="com.example.fus.dto.UserDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <%
        UserDTO userDTO = (UserDTO) session.getAttribute("loginInfo");
        String birthYear = userDTO.getBirthDate().split("-")[0];
        String birthMonth = userDTO.getBirthDate().split("-")[1];
        String birthDay = userDTO.getBirthDate().split("-")[2];
        String phone1 = userDTO.getPhone().split("-")[0];
        String phone2 = userDTO.getPhone().split("-")[1];
        String phone3 = userDTO.getPhone().split("-")[2];
        String findQuestion = userDTO.getFindQuestion();
    %>
    <script src="https://spi.maps.daum.net/imap/map_js_init/postcode.v2.js"></script>
    <script src="/assets/js/user/userEdit.js"></script>
</head>
<body>
<jsp:include page="../layout/header.jsp" flush="false" />
<div class="margin-block"></div>
<div class="jumbotron">
    <div class="container">
        <h1 class="display-3">회원 정보 수정</h1>
    </div>
</div>

<form id="editFrm" name="editFrm" action="/user/edit" method="post">

    <div class="form-div"><label class="control-label flex-label"><span>아이디</span> <input type="text" name="memberId" class="form-input" value="<%=userDTO.getMemberId()%>" readonly></label></div>

    <div class="form-div"><label class="control-label flex-label"><span>비밀번호</span> <input type="password" name="passwd" class="form-input" value="<%=userDTO.getPasswd()%>"></label></div>

    <div class="form-div"><label class="control-label flex-label"><span>비밀번호 확인</span> <input type="password" id="passwdCheck" class="form-input" value="<%=userDTO.getPasswd()%>"></label></div>

    <div class="form-div"><label class="control-label flex-label"><span>이름</span> <input type="text" name="name" class="form-input" value="<%=userDTO.getName()%>"></label></div>

    <div class="form-div">
        <label class="control-label flex-label"><span>생년월일</span>
            <input type="hidden" id="birthDate" name="birthDate" value="0">
            <label class="flex-row-betweeen">
                <input type="text" name="birthyy" class="form-inline" maxlength="4" placeholder="년(4자)" size="6" value="<%=birthYear%>" readonly>
                <input type="text" class="form-inline" name="birthmm" maxlength="2" placeholder="일" size="4" value="<%=birthMonth%>" readonly>
                <input type="text" class="form-inline" name="birthdd" maxlength="2" placeholder="일" size="4" value="<%=birthDay%>" readonly>
            </label>
        </label>
    </div>

    <div class="form-div">
        <label class="control-label flex-label"><span>우편번호</span>
            <input type="text" id="zipCode" name="zipCode" class="form-input zipCode" value="<%=userDTO.getZipCode()%>" readonly>
            <span class="btnFindZipcode btn btn-secondary" style="cursor: pointer">우편번호 검색</span>
        </label>
    </div>

    <div class="form-div"><label class="control-label flex-label"><span>주소</span> <input type="text" id="address01" name="address01" class="form-input" value="<%=userDTO.getAddress01()%>" readonly></label></div>

    <div class="form-div"><label class="control-label flex-label"><span>상세 주소</span> <input type="text" id="address02" name="address02" class="form-input" value="<%=userDTO.getAddress02()%>"></label></div>

    <div class="form-div">
        <label class="control-label flex-label"><span>연락처</span>
            <input type="hidden" id="phone" name="phone" value="0">
            <label class="flex-row-end-column-center">
                <input type="text" name="phone1" maxlength="3" class="form-inline form-input" value="<%=phone1%>">-
                <input type="text" name="phone2" maxlength="4" class="form-inline form-input" value="<%=phone2%>">-
                <input type="text" name="phone3" maxlength="4" class="form-inline form-input" value="<%=phone3%>">
            </label>
        </label>
    </div>

    <div class="form-div">
        <label class="control-label flex-label"><span>비밀번호 찾기 질문</span>
            <select name="findQuestion" style="width: 60%">
                <option value="treasure"
                        <c:if test='<%=findQuestion.equals("treasure")%>'>selected</c:if>>내 보물 1호는?</option>
                <option value="sports"
                        <c:if test='<%=findQuestion.equals("sports")%>'>selected</c:if>>내가 가장 좋아하는 스포츠는?</option>
                <option value="game"
                        <c:if test='<%=findQuestion.equals("game")%>'>selected</c:if>>내가 가장 좋아하는 게임은?</option>
                <option value="teacher"
                        <c:if test='<%=findQuestion.equals("teacher")%>'>selected</c:if>>가장 무서웠던 선생님 성함은?</option>
            </select>
        </label><br/>
        <label class="control-label flex-label"><span>비밀번호 찾기 답변</span> <input type="text" name="findAnswer" class="form-input" value="<%=userDTO.getFindAnswer()%>"></label>
    </div>

    <div class="form-div"><input type="button" class="btn btn-primary" id="submitBtn" value="수정하기"></div>
</form>

<jsp:include page="../layout/footer.jsp" flush="false" />

</body>
</html>
