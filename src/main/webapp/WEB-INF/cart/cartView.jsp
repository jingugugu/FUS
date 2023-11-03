<%@ page import="com.example.fus.dto.CartDTO" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>장바구니</title>
</head>
<body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js" integrity="sha384-b5kHyXgcpbZJO/tY9Ul7kGkf1S0CWuKcCD38l8YkeH8z8QjE0GmW1gYU5S9FOnJ0" crossorigin="anonymous"></script>
<jsp:include page="../layout/header.jsp?12" />
<%
  List cartDTOList = (List) request.getAttribute("cartDTOList"); // null, 정보가 들어있거나
%>
<div class="margin-block"></div>
<div class="jumbotron">
  <div class="container">
    <h1 class="display-3">장바구니</h1>
  </div>
</div>
<% CartDTO cartDTO = null; %>
<c:choose>

  <c:when test="${empty cartDTOList}">
    <div class="cart">
      <img src="/upload/fus/cart/cart.jpg" width="200px">
      <h3>NO ITEM IN SHOPPING CART</h3>
      <h3>------------------------------------</h3>
      <h3>장바구니가 비어 있습니다.</h3>
      <h3>선택하신 상품을 장바구니에 담아주세요.</h3>
    </div>
    <dic class="continue">
      <a href="/product/list?pageNum=1&category=ALL" class="btn btn-secondary"> &laquo; 쇼핑 하러가기</a>
    </dic>
  </c:when>

  <c:otherwise>
    <div class="cart-table">
      <div class="container">
        <div class="row">
          <table width="100%">
            <tr>
              <td align="left">
                <a href="#" class="btn purple-btn btn-removeAll" >전체삭제</a>
                <a href="#" class="btn purple-btn btn-selected" >선택삭제</a>
              </td>
            </tr>
          </table>
        </div>
      </div>
      <div style="margin: 0 auto; padding-top: 50px; width: 1000px" >
          <input type="hidden" name="productId">
          <table class="table table-hover">
            <tr>
              <td>선택</td>
              <td>사진</td>
              <td>상품</td>
              <td>가격</td>
              <td>수량</td>
              <td>소계</td>
              <td>비고</td>
            </tr>
            <form action="/cart/select" name="selectRemoveForm" method="post">
            <%
              Integer total = 0;
              if(cartDTOList != null) {
                for(int i = 0; i < cartDTOList.size(); i++){
                  cartDTO = (CartDTO) cartDTOList.get(i);

                  Integer cartPrice = cartDTO.getPrice();
                  Integer price = (cartPrice == 0) ? 0 : cartPrice;
                  Integer cartCount = cartDTO.getCount();
                  Integer count = (cartCount == 0) ? 0 : cartCount;

                  Integer sum = price * count;
                  total += sum;
            %>
            <tr>
              <td><input type="checkbox" name="checkId" value="<%=cartDTO.getProductId()%>"></td>
              <td><img src="/upload/fus/product/<%=cartDTO.getFileName()%>" style="width: 150px;"></td>
              <td><%=cartDTO.getProductName()%></td>
              <td>₩<%=cartDTO.getPrice()%>원</td>
              <td><%=cartDTO.getCount()%></td>
              <td>₩<%=sum%>원</td>
              <td><a href="#" role="<%=cartDTO.getProductId()%>" class="badge badge-danger btn-removeById">삭제</a></td>
            </tr>
            <%
                }
              }
            %>
            </form>
            <tr>
              <td align="right" colspan="6">합계 : <td class="one">₩<%=total%>원</td>
            </tr>
          </table>
          <div align="right">
            <a href="#" class="btn white-btn btn-success1">전체 주문</a>
            <a href="#" class="btn white-btn btn-success2">선택 주문</a>
          </div>
      </div>
      <a href="/product/list" class="btn btn-secondary"> &laquo; 쇼핑 계속하기</a>
    </div>
  </c:otherwise>
</c:choose>

<jsp:include page="../layout/footer.jsp?12" />
</body>
<script>
  document.addEventListener("DOMContentLoaded", function () {
    const checkIds = document.querySelectorAll("input[name=checkId]");

    const checkRemoveBtn = document.querySelector(".btn-selected");
    checkRemoveBtn.addEventListener("click", function (){
      let count = 0;
      checkIds.forEach(checkId => {
        if (checkId.checked) {
          count++;
        }
      })
      if (count > 0) {
        if (confirm('정말 삭제 하시겠습니까?')) {
          document.forms[0].action = "/cart/select" + "Remove";
          document.forms[0].submit();
        }
      } else {
        alert("삭제하실 상품을 선택해주세요!!")
      }
    })

    const allRemoveBtn = document.querySelector(".btn-removeAll");
    allRemoveBtn.addEventListener("click", function (){
      if (confirm('정말 삭제 하시겠습니까?')) {
        location.href="/cart/allRemove"
      }
    })

    const allOrderBtn = document.querySelector(".btn-success1");
    allOrderBtn.addEventListener("click", function (){
      location.href="/cart/allOrder"
    })

    const oneRemoveBtn = document.querySelectorAll(".btn-removeById");
    oneRemoveBtn.forEach(object => {
      object.addEventListener("click", function () {
        if (confirm('정말 삭제 하시겠습니까?')) {
          location.href="/cart/oneRemove?productId="+object.role;
        }
      })
    })


    const ckeckOrderBtn = document.querySelector(".btn-success2");
    ckeckOrderBtn.addEventListener("click", function (){
      let count = 0;
      checkIds.forEach(checkId => {
        if (checkId.checked) {
          count++;
        }
      })
      if (count > 0 ) {
        document.forms[0].action = "/cart/select" + "Order";
        document.forms[0].submit();
      } else {
        alert("주문하실 상품을 선택해주세요")
      }
    })

    const idInputs = document.querySelectorAll("input[name=checkId]");
  })
</script>
</html>