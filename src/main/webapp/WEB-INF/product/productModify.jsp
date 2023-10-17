<%@ page import="com.example.fus.dto.ProductDTO" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html lang="ko">
<%
  ProductDTO product = (ProductDTO) request.getAttribute("product");
  String pageNum = (String) request.getAttribute("pageNum");
  String category = (String) request.getAttribute("category");
%>
<head>
  <title>상품 수정</title>
</head>
<body>
<jsp:include page="../layout/header.jsp?" flush="false" />
<div class="margin-block"></div>
<div class="jumbotron">
  <div class="container">
    <h1 class="head_title">상품 수정</h1>
  </div>
</div>

<form name="frmProduct" id="frmEditProduct" action="/product/modify" method="post" enctype="multipart/form-data">

    <div class="form-div">
      <input type="hidden" name="pageNum" value="<%=pageNum%>">
      <input type="hidden" name="pageCategory" value="<%=category%>">
      <label class="control-label flex-label">
        <span>상품 코드</span>
        <input type="text" name="productId" class="form-input" value="<%=product.getProductId()%>" readonly>
      </label>
    </div>

    <div class="form-div">
      <label class="control-label flex-label">
        <span>상품명</span>
        <input type="text" name="productName" class="form-input" value="<%=product.getProductName()%>">
      </label>
    </div>

    <div class="form-div">
      <label class="control-label flex-label">
        <span>분류</span>
        <input type="text" name="category" class="form-input" value="<%=product.getCategory()%>">
      </label>
    </div>

    <div class="form-div">
      <label class="control-label flex-label">
        <span>상품 설명</span>
        <input type="text" name="description" class="form-input" value="<%=product.getDescription()%>">
      </label>
    </div>

    <div class="form-div">
      <label class="control-label flex-label">
        <span>가격</span>
        <input type="text" name="price" class="form-input" value="<%=product.getPrice()%>">
      </label>
    </div>

    <div class="form-div">
      <label class="control-label flex-label">
        <span>재고</span>
        <input type="text" name="unitsInStock" class="form-input" value="<%=product.getUnitsInStock()%>">
      </label>
    </div>

    <div class="form-div">
      <label class="control-label flex-label">
        <span>사진</span>
        <input type="file" name="file" class="form-input">
        <input type="hidden" name="fileName" value="<%=product.getFileName()%>">
      </label>
    </div>

    <div class="form-div"><input type="submit" value="수정하기"></div>
</form>
</body>
</html>
