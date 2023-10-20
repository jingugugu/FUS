<%@ page import="com.example.fus.dto.ProductDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.fus.dto.ReviewDTO" %>
<%@ page import="com.example.fus.dto.UserDTO" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<html lang="ko">
<head>
    <title>Product Detail</title>
    <script src="/assets/js/product/productView.js?a"></script>
</head>
<body>
<jsp:include page="../layout/header.jsp?" flush="false" />
<%
    ProductDTO productDTO = (ProductDTO) request.getAttribute("product");
    List reviewList = (List) request.getAttribute("reviewList");

    String memberId = null;
    if(session.getAttribute("loginInfo") != null){
        UserDTO userDTO = (UserDTO) session.getAttribute("loginInfo");
        memberId = userDTO.getMemberId();
    }
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

                <div><span>가격</span><p><fmt:formatNumber type="number" maxFractionDigits="3" value="<%=productDTO.getPrice()%>" /></p></div>
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

    <div class="review-wrap-title">상품평</div>

    <div class="review-view-wrap">
    <%
        if(reviewList.size() > 0) {
            for(int j = 0; j < reviewList.size(); j++){
                ReviewDTO reviewDTO = (ReviewDTO) reviewList.get(j);
    %>
        <div class="user-review1">
            <div class="review-userController">

                <div class="review-userId">
                    <span><%=reviewDTO.getMemberId().substring(0, 4)+"****"%>
                    <%
                        if( memberId != null && memberId.equals(reviewDTO.getMemberId())){
                    %>
                        <a href="/review/remove?index=<%=reviewDTO.getIndex()%>&productId=<%=reviewDTO.getProductId()%>">삭제</a>
                    <%
                        }
                    %>
                    </span>
                </div>
                <div class="review-rate-date">
                    <span class="rate-date-wrap">
                        <span class="review-rate">
                        <c:forEach begin="1" end="<%=reviewDTO.getRate()%>">
                            ★
                        </c:forEach>
                        </span>
                        <%=reviewDTO.getAddDate().split(" ")[0]%>
                    </span>
                </div>
                <div class="review-title">
                    <%=reviewDTO.getTitle()%>
                </div>
                <div class="review-contents">
                    <c:if test="<%=reviewDTO.getFileName() != null && reviewDTO.getFileName().length() > 0 %>">
                        <div class="review-image">
                            <img src="/upload/fus/review/<%=reviewDTO.getFileName()%>"/>
                        </div>
                    </c:if>
                    <div class="review-content">
                        <%=reviewDTO.getContent()%>
                    </div>
                </div>
            </div>
        </div>
    <%
            }
        }
    %>
    </div>

    <% if(memberId != null) { %>
        <div class="review-addFrm-wrap">
            <form name="frmRipple" action="/review/add" method="post" enctype="multipart/form-data">
                <input type="hidden" name="productId" value="<%=productDTO.getProductId()%>"><!-- 제품키 -->
                <div class="review-add-input-wrap">
                    <div>
                        <%--                    value="${sessionMemberId}"--%>
                        <input type="hidden" name="memberId"  value="<%=memberId%>" readonly>
                    </div>
                    <div>
                        <!--value="<%=productDTO.getProductName()%>"-->
                        <input type="hidden" name="productName" value="<%=productDTO.getProductId()%>" readonly >

                    </div>
                    <div class="rate-form-wrap">
                        <input type="text" name="title" id="rateFrmTitle" placeholder="제목" maxlength="50" minlength="5">
                        <div class="rating_box">
                            <div class="rating">
                                ★★★★★
                                <span class="rating_star">★★★★★</span>
                                <input type="range" name="rate" value="1" step="1" min="1" max="5">
                            </div>
                        </div>
    <%--                    <select name="rate" class="review-form-rate">--%>
    <%--                        <option value="5">★★★★★</option>--%>
    <%--                        <option value="4">★★★★</option>--%>
    <%--                        <option value="3">★★★</option>--%>
    <%--                        <option value="2">★★</option>--%>
    <%--                        <option value="1">★</option>--%>
    <%--                    </select>--%>
                    </div>
                    <textarea name="content" style="width: 700px; height: 200px" maxlength="300" minlength="5"></textarea>
                    <div id="review-btns-wrap">
                        <input type="file" name="file">
                        <input type="submit" value="등록">
                    </div>
                </div>
            </form>
        </div>
    <% } %>
</div>

<jsp:include page="../layout/footer.jsp" flush="false" />
</body>
</html>
