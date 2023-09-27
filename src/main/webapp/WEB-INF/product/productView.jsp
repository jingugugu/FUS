<%@ page import="com.example.fus.dto.ProductDTO" %>
<%@ page import="com.example.fus.dto.ProductDTO" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html lang="ko">
<head>
    <title>Product Detail</title>
</head>
<body>
<jsp:include page="../layout/header.jsp?" flush="false" />
<%
    ProductDTO productDTO = (ProductDTO) request.getAttribute("product");

%>
<div>

    <div>
        <img src="<%=productDTO.getFileName()%>">
    </div>

    <div>
        <%=productDTO.getProductName()%>
    </div>

    <div>
        <%=productDTO.getCategory()%>
    </div>

    <div>
        <%=productDTO.getDescription()%>
    </div>

    <div>
        <%=productDTO.getPrice()%>
    </div>

    <div>
        <%=productDTO.getUnitsInStock()%>
    </div>

    <div>
        <button class="btn btn-warning"><a href="#">장바구니</a></button>
        <button class="btn btn-success"><a href="#">구매</a></button>
    </div>

</div>
<jsp:include page="../layout/footer.jsp" flush="false" />
</body>
</html>
