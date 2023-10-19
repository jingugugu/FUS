<%@ page import="java.text.DecimalFormat" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>main</title>
    <script src="/assets/js/product/productList.js"></script>
</head>
<body>
<style>
    nav.navbar.bootsnav.no-background.white ul.nav > li > a {
        color: white !important;
    }
    .user-name {
        color: #3b6cd7 !important
    }
</style>
  <jsp:include page="../layout/header.jsp?12234234" />
  <section id="home" class="home bg-black fix">
      <div class="overlay"></div>
      <div class="container">
          <div class="row">
              <div class="main_home text-center">
                  <div class="col-md-12">
                      <div class="hello">
                          <div class="slid_item">
                              <div class="home_text ">
                                  <h1 class="text-yellow">Football Uniform Site</h1>
                              </div>
                          </div><!-- End off slid item -->

                      </div>
                  </div>

              </div>
          </div><!--End off row-->
      </div><!--End off container -->
  </section> <!--End off Home Sections-->



<div class="wrapper2 products-wrapper">

    <h3 class="products-title">Just Dropped</h3>
    <div class="flex-test">
<%--        <% DecimalFormat formatter = new DecimalFormat("###,###.##"); %>--%>
        <c:forEach var="product" items="${productList}">
            <div class="product-list-wrap">
                <a href="/product/view?productId=${product.productId}" class="product-wrap" role="button">
                    <div class="product-img-scale-wrap">
                        <img class="product-img" src="/upload/fus/product/${product.fileName}">
                    </div>
                    <h4 class="product-name">${product.productName}</h4>
                    <h6 class="product-description">${product.description}</h6>
                    <h6 class="product-price">\<fmt:formatNumber type="number" maxFractionDigits="3" value="${product.price}" /></h6>
                </a>
            </div>
        </c:forEach>
    </div>
</div>

  <jsp:include page="../layout/footer.jsp?12" />
</body>
</html>
