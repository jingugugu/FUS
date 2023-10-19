<%@ page import="com.example.fus.dto.UserDTO" %>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%
  // 페이징 세팅
  int pagePerBlock = 10; // 페이지 출력 시 나올 범위.
  int pageNum = Integer.parseInt((String) request.getAttribute("pageNum"));
  String word = request.getParameter("word");
  int count =  Integer.parseInt((String)request.getAttribute("count")); // 총 게시물 갯수
  int totalPage = count % 12 == 0 ? (count / 12) : (count / 12) + 1; // 총 페이지
  // 총 게시물 갯수를 pagePerBlock으로 나눴을 때 나머지 없이 딱 떨어지면 바로 몫으로 페이징, 나머지가 있으면 +1 해서 페이징
  int totalBlock = totalPage % pagePerBlock == 0 ? totalPage / pagePerBlock : totalPage / pagePerBlock + 1; // 6 % 5 0
  int thisBlock = ((pageNum - 1) / pagePerBlock) + 1; // 현재 블럭
  int firstPage = ((thisBlock - 1) * pagePerBlock) + 1; // 블럭의 첫 페이지
  int lastPage = thisBlock * pagePerBlock; // 블럭의 마지막 페이지
  lastPage = (lastPage > totalPage) ? totalPage : lastPage;

%>
<html lang="ko">
<head>
  <title>상품 목록</title>
  <script src="https://kit.fontawesome.com/4ae8b5f015.js" crossorigin="anonymous"></script> <%-- 폰트어썸(아이콘 사이트) --%>
  <script src="/assets/js/product/productList.js"></script>
</head>
<body>
<jsp:include page="../layout/header.jsp" flush="false" />
<div class="margin-block"></div>
<c:set var="totalPage" value="<%=totalPage%>" />
<c:set var="pagePerBlock" value="<%=pagePerBlock%>" />
<c:set var="totalBlock" value="<%=totalBlock%>" />
<c:set var="thisBlock" value="<%=thisBlock%>" />
<c:set var="firstPage" value="<%=firstPage%>" />
<c:set var="lastPage" value="<%=lastPage%>" />
<c:set var="pageNum" value="<%=pageNum%>" />
<c:set var="word" value="<%=word%>" />

<div class="wrapper2 products-wrapper">

  <h3 class="products-title">"${word}"에 대한 검색결과</h3>
  <div class="flex-test">
    <c:forEach var="product" items="${products}">
      <div class="product-list-wrap">
        <a href="/product/view?productId=${product.productId}" class="product-wrap" role="button">
          <div class="product-img-scale-wrap">
            <img class="product-img" src="/upload/fus/product/${product.fileName}" style="width: 100px;">
          </div>
          <h4 class="product-name">${product.productName}</h4>
          <h6 class="product-description">${product.description}</h6>
          <h6 class="product-price">\<fmt:formatNumber type="number" maxFractionDigits="3" value="${product.price}" /></h6>
        </a>
      </div>
    </c:forEach>
  </div>

</div>

<%-- 상품 리스트 페이지 이동 버튼들--%>
<div class="block-btn-wrap">
  <c:if test="${totalPage > 0}">
    <a href="<c:url value="/product/search?pageNum=1&word=${word}" />"><span><i class="fa-solid fa-angles-left"></i></span></a>
    <c:if test="${thisBlock > 1}">
      <a href="<c:url value="/product/search?pageNum=${firstPage - 1}&word=${word}" />"><span><i class="fa-solid fa-angle-left"></i></span></a>
    </c:if>

    <c:forEach var="i" begin="${firstPage}" end="${lastPage}">
      <a href="/product/search?pageNum=${i}&word=${word}">
        <c:choose>
          <c:when test="${pageNum==i}">
            <span style="color: #4C5317;font-weight: bold"> [${i}] </span>
          </c:when>
          <c:otherwise>
            <span style="color: #4C5317;"> [${i}] </span>
          </c:otherwise>
        </c:choose>
      </a>
    </c:forEach>

    <c:if test="${thisBlock < totalBlock}" >
      <a href="<c:url value="/product/search?pageNum=${lastPage + 1}&word=${word}" />"><span><i class="fa-solid fa-angle-right"></i></span></a>
    </c:if>
    <a href="<c:url value="/product/search?pageNum=${totalPage}&word=${word}" />"><span><i class="fa-solid fa-angles-right"></i></span></a>
  </c:if>
</div>

<jsp:include page="../layout/footer.jsp?" flush="false"/>
</body>
</html>