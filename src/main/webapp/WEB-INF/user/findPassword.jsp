<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<jsp:include page="../layout/header.jsp" flush="false" />
<div class="margin-block"></div>
<div class="jumbotron">
  <div class="container">
    <h1 class="display-3">비밀번호 찾기 페이지</h1>
  </div>
</div>

<div class="container">
  <form action="/user/findPassword" method="post">
    <div class="form-div"><label class="control-label flex-label"><span>아이디</span> <input type="text" name="memberId" class="form-input"></label></div>
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

    <div class="form-div"><input type="submit" id="submitBtn" value="찾기"></div>
  </form>
</div>

<jsp:include page="../layout/footer.jsp" flush="false" />
</body>
</html>
