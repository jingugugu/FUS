<%@ page import="java.util.List" %>
<%@ page import="com.example.fus.dto.ProductDTO" %>
<%@ page import="com.example.fus.dto.ProductDTO" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%
    List productDTOList = (List) request.getAttribute("products");
    System.out.println("productDTOList" + productDTOList);
%>
<html lang="ko">
<head>
    <title>상품 목록</title>
    <jsp:include page="../layout/header.jsp" flush="false" />
</head>
<body>
        <div>
            <ul class="nav navbar">
                <li class="nav-item"><a class="nav-link" href="/product/list">상품 목록</a></li>
                <li class="nav-item"><a class="nav-link" href="/product/add">상품 등록</a></li>
                <li class="nav-item"><a class="nav-link" href="/product/edit">상품 편집</a></li>
            </ul>
        </div>

        <div>
        <%
            if(productDTOList != null) {
                for(int j = 0; j < productDTOList.size(); j++){
                    ProductDTO productDTO = (ProductDTO) productDTOList.get(j);
        %>
            <div>
                <img src="<%=productDTO.getFileName()%>" style="width: 200px;">
                <h3><%=productDTO.getProductName()%></h3>
                <p><%=productDTO.getDescription()%></p>
                <p><%=productDTO.getPrice()%></p>
                <a href="/product/view?productId=<%=productDTO.getProductId()%>" class="btn btn-secondary" role="button">
                    상세정보
                </a>
            </div>
        <%
                }
            }
        %>
        </div>
    </div>
    <jsp:include page="../layout/footer.jsp" flush="false"/>
</body>
</html>
