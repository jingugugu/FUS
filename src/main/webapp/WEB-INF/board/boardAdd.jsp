<%@ page contentType="text/html; charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>글쓰기</title>
</head>
<body>
<jsp:include page="../layout/header.jsp" flush="false" />
<div class="margin-block"></div>
<form id="AddFrm" action="/board/add" method="post" enctype="multipart/form-data">


  <div class="form-group-title1" align="center">
      <label class="control-label"><input type="text" name="title" class="text-title" minlength="5" maxlength="100" placeholder="제목">
          <div align="left">
              <label class="control-label">
                  <input type="file">
              </label>
          </div>
      </label>
  </div>

    <div align="center">
      <label class="control-label-content">
          <textarea name="content" rows="45" cols="100" maxlength="1000" minlength="10" placeholder="내용을 입력하세요."></textarea>
      </label>
    </div>


    <div class="btn-Sub" align="center">
    <label class="control-label"><input type="submit" value="등록"></label>
    </div>

</form>
<jsp:include page="../layout/footer.jsp" flush="false" />

</body>
</html>

