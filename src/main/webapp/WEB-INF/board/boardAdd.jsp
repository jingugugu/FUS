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

  <div class="form-group"><label class="control-label">제목<input type="text" name="title" class="form-control" minlength="5" maxlength="15"></label></div>

  <div class="form-group "><label class="control-label"><textarea name="content" rows="45" cols="100" maxlength="1000" minlength="10" placeholder="내용을 입력하세요."></textarea></label></div>

  <div><label class="control-label"><input type="file"></label></div>

    <label class="control-label"><input type="submit" value="등록"></label>

</form>
<jsp:include page="../layout/footer.jsp" flush="false" />

</body>
</html>

