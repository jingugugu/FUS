<%@ page contentType="text/html;charset=UTF-8" %>
<html lang="ko">
<head>
    <title>상품 등록</title>
</head>
<body>
<jsp:include page="../layout/header.jsp?" flush="false" />
<div class="margin-block"></div>
<div class="jumbotron">
    <div class="container">
        <h1 class="head_title">상품 등록</h1>
    </div>
</div>
<form name="frmProduct" id="frmAddProduct" action="/product/add" method="post" enctype="multipart/form-data">

    <div class="form-div">
        <label class="control-label flex-label">
            <span>상품 코드</span>
            <input type="text" name="productId" class="form-input">
        </label>
    </div>

    <div class="form-div">
        <label class="control-label flex-label">
            <span>상품명</span>
            <input type="text" name="productName" class="form-input">
        </label>
    </div>

    <div class="form-div">
        <label class="control-label flex-label">
            <span>분류</span>
            <input type="text" name="category" class="form-input">
        </label>
    </div>

    <div class="form-div">
        <label class="control-label flex-label">
            <span>상품 설명</span>
            <input type="text" name="description" class="form-input">
        </label>
    </div>

    <div class="form-div">
        <label class="control-label flex-label">
            <span>가격</span>
            <input type="text" name="price" class="form-input">
        </label>
    </div>

    <div class="form-div">
        <label class="control-label flex-label">
            <span>재고</span>
            <input type="text" name="unitsInStock" class="form-input">
        </label>
    </div>

    <div class="form-div">
        <label class="control-label flex-label">
            <span>사진</span>
            <input type="file" name="file" class="form-input">
        </label>
    </div>

    <div class="form-div"><input type="submit" value="상품 등록"></div>
</form>
</body>
</html>
