<%@ page import="com.example.fus.dto.ProductDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.fus.dto.ReviewDTO" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html lang="ko">
<head>
    <title>Product Detail</title>
    <script src="/assets/js/product/productView.js"></script>
</head>
<body>
<jsp:include page="../layout/header.jsp?" flush="false" />
<%
    ProductDTO productDTO = (ProductDTO) request.getAttribute("product");
    List reviewList = (List) request.getAttribute("reviewList");

%>
<div class="margin-block"></div>

<div class="container">
    <div class="product-container">
        <div class="product-image-wrap">
            <div class="img-scale-wrap">
                <img id="product-image" src="/upload/fus/product/<%=productDTO.getFileName()%>">
            </div>
            <input type="hidden" name="productId" value="<%=productDTO.getProductId()%>">
        </div>

        <div class="product-info-wrap">
            <form action="/cart/add" method="post">
                <input type="hidden" name="productId" value="<%=productDTO.getProductId()%>">
                <input type="hidden" name="fileName" value="<%=productDTO.getFileName()%>">
                <div><span>상품명</span><p><%=productDTO.getProductName()%></p></div>
                <hr>

                <div><span>상품분류</span><p><%=productDTO.getCategory()%> </p></div>
                <hr>

                <div><span>상품정보</span><p><%=productDTO.getDescription()%> </p></div>
                <hr>

                <div><span>가격</span><p><%=productDTO.getPrice()%></p></div>
                <hr>

                <div><span>수량</span><p><input type="number" name="count" value="1"> </p></div>
                <hr>

                <div>
                    <button type="submit" class="purple-btn">장바구니 담기</button>
                    <button type="button" class="white-btn">구매</button>
                </div>
            </form>
        </div>
    </div>
    <hr>
    <%
        if(reviewList.size() > 0) {
            for(int j = 0; j < reviewList.size(); j++){
                ReviewDTO reviewDTO = (ReviewDTO) reviewList.get(j);
    %>
    <div class="user-review2">
        <p><%=reviewDTO.getProductName()%></p>
        <p><%=reviewDTO.getTitle()%></p>
        <p><%=reviewDTO.getContent()%></p>
        <p><%=reviewDTO.getAddDate()%></p>
        <p><a href="/review/remove?review"></a></p>
    </div>
    <%
            }
        }
    %>

    <div>
        <form name="frmRipple" action="/review/add" method="post" enctype="multipart/form-data">
            <input type="hidden" name="productId" value="<%=productDTO.getProductId()%>"><!-- 제품키 -->
            <div style="background-color: #a6e1ec; width: 700px; margin: 0 auto;">
                <div>
                    <%--                    value="${sessionMemberId}"--%>
                    <input name="memberId" type="text" placeholder="memberId">
                </div>
                <div>
                    <!--value="<%=productDTO.getProductName()%>"-->
                    <input type="text" name="productName" placeholder="productId" readonly >
                    <select name="rate">
                        <option value="5">5</option>
                        <option value="4">4</option>
                        <option value="3">3</option>
                        <option value="2">2</option>
                        <option value="1">1</option>
                    </select>
                </div>
                <div>
                    <input type="text" name="title" placeholder="제목">
                </div>
                <textarea name="content" style="width: 700px; height: 200px"></textarea>
                <input type="file" name="file">
                <input type="submit" value="등록">
            </div>
        </form>
    </div>

</div>

<jsp:include page="../layout/footer.jsp" flush="false" />
</body>
</html>
