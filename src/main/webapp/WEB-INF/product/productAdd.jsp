<%@ page contentType="text/html;charset=UTF-8" %>
<html lang="ko">
<head>
    <title>상품 등록</title>
</head>
<script src="/assets/js/product/productAdd.js"></script>
<body>
<style>
    .form-div{
        margin-top: 30px;
    }
</style>
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
            <span>상품명</span>
            <input type="text" name="productName" class="form-input">
        </label>
    </div>

    <div class="form-div">
        <label class="control-label flex-label">
            <span>분류</span>
            <input type="radio" name="category" value="EPL" checked>EPL
            <input type="radio" name="category" value="ESP">ESP
            <input type="radio" name="category" value="SERIA">SERIA
            <input type="radio" name="category" value="FRA">FRA
            <input type="radio" name="category" value="GER">GER
            <input type="radio" name="category" value="NAT">NAT
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

    <div class="form-div"><input id="addBtn" type="button" value="상품 등록"></div>
</form>
</body>
</html>
