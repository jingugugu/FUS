<%@ page import="java.util.List" %>
<%@ page import="com.example.fus.dto.UserDTO" %>
<%@ page import="com.example.fus.dto.CartDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Title</title>
  <style>
    .flex-label {
      justify-content: start !important;
    }
  </style>
</head>
<script src="https://spi.maps.daum.net/imap/map_js_init/postcode.v2.js"></script>
<script src="/assets/js/shipping/shipping.js"></script>
<body>
<jsp:include page="../layout/header.jsp" flush="false" />
<%
  List cartDTOList = (List) request.getAttribute("cartDTOList"); // null, 정보가 들어있거나
  UserDTO userDTO = (UserDTO) session.getAttribute("loginInfo");
%>
<div class="margin-block"></div>

<div class="jumbotron">
  <div class="container">
    <h1 class="display-3">배송 정보</h1>
  </div>
</div>
<!-- 주문번호 생성 및 사용 -->
<%--<%--%>
<%--  // 세션 아이디 가져오기--%>
<%--  String sessionId = session.getId();--%>

<%--  // 현재 날짜와 시간 가져오기--%>
<%--  // 주문 할때 여러개가 한번에 들어오면 기존에 들어온 주문정보가 덮여쓰여저서 삭제될 수 있음--%>
<%--  // 날짜와시간을 지정해 주문정보를 나눔--%>
<%--  java.text.SimpleDateFormat simpleDatFormat = new java.text.SimpleDateFormat("yyyyMMddHHmmss");--%>
<%--  String currentDateTime = simpleDatFormat.format(new java.util.Date());--%>

<%--  String orderId = currentDateTime + "-" + sessionId;--%>

<%--%>--%>

<div class="shipping-container">
  <form id="orderFrm" name="orderFrm" action="/shipping/order" method="post">
    <div class="product-order-wrap" style="padding-top: 10px">
      <table class="table table-hover">
        <tr>
          <td>사진</td>
          <td>상품</td>
          <td>가격</td>
          <td>수량</td>
          <td>소계</td>
        </tr>
            <%
          Integer total = 0;
          if(cartDTOList != null) {
            for(int i = 0; i < cartDTOList.size(); i++){
              CartDTO cartDTO = (CartDTO) cartDTOList.get(i);

              Integer cartPrice = cartDTO.getPrice();
              Integer price = (cartPrice == 0) ? 0 : cartPrice;
              Integer cartCount = cartDTO.getCount();
              Integer count = (cartCount == 0) ? 0 : cartCount;

              Integer sum = price * count;
              total += sum;
        %>

          <tr>
            <input type="hidden" name="productId" value="<%=cartDTO.getProductId()%>">
            <td><img src="/upload/fus/product/<%=cartDTO.getFileName()%>" style="width: 100px;"></td>
            <td><%=cartDTO.getProductName()%></td>
            <td>₩<%=cartDTO.getPrice()%>원</td>
            <td>
              <input type="text" name="count" value="<%=cartDTO.getCount()%>">
<%--              <input type="hidden" name="productId" value="<%=cartDTO.getProductId()%>">--%>
              <input type="hidden" name="productName" value="<%=cartDTO.getProductName()%>">
            </td>
            <td>₩<%=sum%>원</td>
          </tr>
            <%
            }
          }
        %>
          <tr>
            <td colspan="7">합계 : ₩<%=total%>원</td>
          </tr>
      </table>
    </div>


    <div class="shipping-info-wrap">
      <div class="form-div">
        <label class="control-label flex-label">
          <span>받는사람 이름</span>
          <input type="text" name="receiverName" class="form-input" value="<%=userDTO.getUserName()%>">
        </label>
      </div>
      <div class="form-div">
        <label class="control-label flex-label">
          <span>우편번호</span>
          <input type="text" name="receiverZipCode" id="receiverZipCode" class="form-input" style="width: 34%" value="<%=userDTO.getZipCode()%>" readonly>
          <span class="btnFindZipCode btn btn-secondary" style="cursor: pointer">우편번호 검색</span>
        </label>
      </div>
      <div class="form-div">
        <label class="control-label flex-label">
          <span>주소</span>
          <input type="text" name="receiverAddress01" id="receiverAddress01" class="form-input" value="<%=userDTO.getAddress01()%>" readonly>
        </label>
      </div>
      <div class="form-div">
        <label class="control-label flex-label">
          <span>상세 주소</span>
          <input type="text" name="receiverAddress02" id="receiverAddress02" class="form-input" value="<%=userDTO.getAddress02()%>">
        </label>
      </div>
      <div class="order-button">
          <a href="/cart/list" class="btn purple-btn btn-secondary1" role="button">이전</a>
          <input type="submit" value="주문" class="btn white-btn btn-primary1">
      </div>
    </div>
  </form>
</div>
<jsp:include page="../layout/footer.jsp" flush="false" />
</body>
</html>