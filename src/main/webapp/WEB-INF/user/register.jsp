<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<script src="https://spi.maps.daum.net/imap/map_js_init/postcode.v2.js"></script>
<script src="/assets/js/user/userRegister.js"></script>
<body>
  <jsp:include page="../layout/header.jsp" flush="false" />
  <div class="margin-block"></div>
  <div class="jumbotron">
    <div class="container">
      <h1 class="display-3">회원 가입</h1>
    </div>
  </div>

  <form id="regFrm" name="regFrm" action="/user/register" method="post">

    <div class="form-div"><label class="control-label flex-label"><span>아이디</span> <input type="text" name="memberId" class="form-input"></label></div>

    <div class="form-div"><label class="control-label flex-label"><span>비밀번호</span> <input type="password" name="passwd" class="form-input" minlength="2"></label></div>

    <div class="form-div"><label class="control-label flex-label"><span>비밀번호 확인</span> <input type="password" id="passwdCheck" class="form-input" minlength="2"></label></div>

    <div class="form-div"><label class="control-label flex-label"><span>이름</span> <input type="text" name="name" class="form-input"></label></div>

    <div class="form-div">
      <label class="control-label flex-label"><span>생년월일</span>
        <input type="hidden" id="birthDate" name="birthDate" value="0">
        <label class="flex-row-betweeen">
          <input type="text" name="birthyy" class="form-inline" maxlength="4" placeholder="년(4자)" size="6">
          <select name="birthmm" class="form-inline">
            <option value="">월</option>
            <option value="01">1</option>
            <option value="02">2</option>
            <option value="03">3</option>
            <option value="04">4</option>
            <option value="05">5</option>
            <option value="06">6</option>
            <option value="07">7</option>
            <option value="08">8</option>
            <option value="09">9</option>
            <option value="10">10</option>
            <option value="11">11</option>
            <option value="12">12</option>
          </select> <input type="text" class="form-inline" name="birthdd" maxlength="2" placeholder="일" size="4">
        </label>
      </label>
    </div>

    <div class="form-div">
      <label class="control-label flex-label"><span>우편번호</span>
        <input type="text" id="zipCode" name="zipCode" class="form-input zipCode" readonly>
        <span class="btnFindZipcode btn btn-secondary" style="cursor: pointer">우편번호 검색</span>
      </label>
    </div>

    <div class="form-div"><label class="control-label flex-label"><span>주소</span> <input type="text" id="address01" name="address01" class="form-input" readonly></label></div>

    <div class="form-div"><label class="control-label flex-label"><span>상세 주소</span> <input type="text" id="address02" name="address02" class="form-input"></label></div>

    <div class="form-div">
      <label class="control-label flex-label"><span>연락처</span>
        <input type="hidden" id="phone" name="phone" value="0">
        <label class="flex-row-end-column-center">
          <input type="text" name="phone1" maxlength="3" class="form-inline form-input">-
          <input type="text" name="phone2" maxlength="4" class="form-inline form-input">-
          <input type="text" name="phone3" maxlength="4" class="form-inline form-input">
        </label>
      </label>
    </div>

    <div class="form-div">
      <label class="control-label flex-label"><span>비밀번호 찾기 질문</span>
        <select name="findQuestion" style="width: 60%">
          <option value="treasure">내 보물 1호는?</option>
          <option value="sports">내가 가장 좋아하는 스포츠는?</option>
          <option value="game">내가 가장 좋아하는 게임은?</option>
          <option value="teacher">가장 무서웠던 선생님 성함은?</option>
        </select>
      </label><br/>
      <label class="control-label flex-label"><span>비밀번호 찾기 답변</span> <input type="text" name="findAnswer" class="form-input"></label>
    </div>

    <div class="form-div"><input type="button" class="btn btn-primary" id="submitBtn" value="가입하기"></div>
  </form>

  <jsp:include page="../layout/footer.jsp" flush="false" />
</body>
</html>
