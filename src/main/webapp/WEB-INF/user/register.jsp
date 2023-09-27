<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style>
      #regFrm {
        display: flex;
        flex-direction: column;
        align-items: center;
      }
    </style>
</head>
<script src="https://spi.maps.daum.net/imap/map_js_init/postcode.v2.js"></script>
<script src="/assets/js/userRegister/userRegister.js"></script>
<body>
  <jsp:include page="../layout/header.jsp" flush="false" />

  <form id="regFrm" name="regFrm" action="/user/register" method="post">

    <div class="form-group"><label class="control-label">아이디  <input type="text" name="memberId" class="form-control"></label></div>

    <div class="form-group"><label class="control-label">비밀번호  <input type="password" name="passwd" class="form-control"></label></div>

    <div class="form-group"><label class="control-label">비밀번호 확인  <input type="password" class="form-control"></label></div>

    <div class="form-group"><label class="control-label">이름 : <input type="text" name="name" class="form-control"></label></div>

    <div class="form-group">
      <label class="control-label">생년월일
        <input type="hidden" id="birthDate" value="0">
        <input type="text" name="birthyy" maxlength="4" placeholder="년(4자)" size="6">
        <select name="birthmm">
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
        </select> <input type="text" name="birthdd" maxlength="2" placeholder="일" size="4">
      </label>
    </div>

    <div class="form-group">
      <label class="control-label">우편번호
        <input type="text" id="zipCode" name="zipCode" class="form-control" readonly>
        <span class="btnFindZipcode btn btn-secondary" style="cursor: pointer">우편번호 검색</span>
      </label>
    </div>

    <div class="form-group"><label class="control-label">주소 <input type="text" id="address01" name="address01" class="form-control" readonly></label></div>

    <div class="form-group"><label class="control-label">상세 주소 <input type="text" id="address02" name="address02" class="form-control"></label></div>

    <div class="form-group">
      <label class="control-label">연락처
        <input type="hidden" id="phone" value="0">
        <input type="text" name="phone1" maxlength="3" class="form-control">-
        <input type="text" name="phone2" maxlength="4" class="form-control">-
        <input type="text" name="phone3" maxlength="4" class="form-control">
      </label>
    </div>

    <div class="form-group">
      <label class="control-label">비밀번호 찾기 질문
        <select name="findQuestion">
          <option value="treasure">내 보물 1호는?</option>
          <option value="sports">내가 가장 좋아하는 스포츠는?</option>
          <option value="game">내가 가장 좋아하는 게임은?</option>
          <option value="teacher">가장 무서웠던 선생님 성함은?</option>
        </select>
      </label><br/>
      <label class="control-label">비밀번호 찾기 답변 <input type="text" name="findAnswer" class="form-control"></label>
    </div>

    <div class="form-group"><input type="button" id="submitBtn" value="가입하기"></div>
  </form>

  <jsp:include page="../layout/footer.jsp" flush="false" />
</body>
</html>
