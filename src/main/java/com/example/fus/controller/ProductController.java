package com.example.fus.controller;

import com.example.fus.dto.ProductDTO;
import com.example.fus.dto.ReviewDTO;
import com.example.fus.service.ProductService;
import com.example.fus.service.ReviewService;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Log4j2
@WebServlet("/product/*")
@MultipartConfig(maxFileSize = 5 * 1024 * 1024, location = "c:/upload/fus/product") //파일 용량 5mb, location="사진 저장되는 곳"
public class ProductController extends HttpServlet {

    private String path;
    private ProductService productService = null;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        productService = new ProductService();
        path = req.getPathInfo();

        if(path == null){
            path = "/list";
        }
        switch (path) {
            case "/list":
                String pageNum = "1";
                if(req.getParameter("pageNum") != null){
                    pageNum = req.getParameter("pageNum");
                };
                String category = "ALL";
                if(req.getParameter("category") != null) {
                    category = req.getParameter("category");
                }
                List<ProductDTO> newProductDTOList = productService.categoryProducts(pageNum, category, "addDate");
                req.setAttribute("newProducts", newProductDTOList);
                List<ProductDTO> orderProductDTOList = productService.categoryProducts(pageNum, category, "orderCount");
                req.setAttribute("orderProducts", orderProductDTOList);
                List<ProductDTO> reviewProductDTOList = productService.categoryProducts(pageNum, category, "reviewCount");
                req.setAttribute("reviewProducts", reviewProductDTOList);
                String count = category.equals("ALL") ? productService.sizeProductList() : productService.sizeCategoryProductList(category);
                req.setAttribute("count", count);
                req.setAttribute("pageNum", pageNum);
                req.setAttribute("category", category);
                req.getRequestDispatcher("/WEB-INF/product/productList.jsp").forward(req, resp);
                break;
            case "/view":
                ReviewService reviewService = new ReviewService();
                int productId = Integer.parseInt(req.getParameter("productId"));
                ProductDTO product = productService.getProduct(productId);
                List<ReviewDTO> reviewList = reviewService.listReview(productId);
                for(ReviewDTO reviewDTO : reviewList) {
                    log.info(reviewDTO);
                }
                log.info(reviewList.size());
                req.setAttribute("product", product);
                req.setAttribute("reviewList", reviewList);
                log.info(product);
                req.getRequestDispatcher("/WEB-INF/product/productView.jsp").forward(req, resp);
                break;
            case "/add":
                req.getRequestDispatcher("/WEB-INF/product/productAdd.jsp").forward(req, resp);
                break;
            case "/edit":
                pageNum = req.getParameter("pageNum");
                newProductDTOList = productService.listProduct(pageNum, "addDate");
                req.setAttribute("newProducts", newProductDTOList);
                count = productService.sizeProductList();
                req.setAttribute("count", count);
                req.setAttribute("pageNum", pageNum);
                req.getRequestDispatcher("/WEB-INF/product/productEdit.jsp").forward(req, resp);
                break;
            case "/remove" :
                productId = Integer.parseInt(req.getParameter("productId"));
                pageNum = req.getParameter("pageNum");
                category = req.getParameter("category");
                try {
                    productService.removeProduct(productId);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                resp.sendRedirect("/product/edit?pageNum="+pageNum+"&category="+category);
                break;
            case "/modify":
                productId = Integer.valueOf(req.getParameter("productId"));
                pageNum = req.getParameter("pageNum");
                category = req.getParameter("category");
                product = productService.getProduct(productId);
                req.setAttribute("product", product);
                req.setAttribute("pageNum", pageNum);
                req.setAttribute("category", category);
                req.getRequestDispatcher("/WEB-INF/product/productModify.jsp").forward(req, resp);
                break;
            case "/search":
                pageNum = req.getParameter("pageNum");
                String word = req.getParameter("word");
                List<ProductDTO> productDTOList = productService.searchProducts(pageNum, word);
                count = productService.sizeSearchProductList(word);
                req.setAttribute("products", productDTOList);
                log.info("search :");
                for(ProductDTO product1 : productDTOList) {
                    log.info(product1);
                }
                req.setAttribute("count", count);
                req.setAttribute("pageNum", pageNum);
                req.setAttribute("word", word);
                req.getRequestDispatcher("/WEB-INF/product/productSearch.jsp").forward(req, resp);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        path = req.getPathInfo();
        if(path == null) {
            resp.sendRedirect("/list");
        }
        productService = new ProductService();
        try{
            switch (path) {
                case "/add":
                    productService.addProduct(req);
                    resp.sendRedirect("/product/list?pageNum=1&category=ALL");
                    break;
                case "/remove" :
                    Integer productId = Integer.valueOf(req.getParameter("productId"));
                    productService.removeProduct(productId);
                    resp.sendRedirect("/product/list");
                    break;
                case "/modify" :
                    log.info("modify income: ");
                    ProductDTO productDTO = new ProductDTO();
                    BeanUtils.populate(productDTO, req.getParameterMap());
                    productService.modifyProduct(req, productDTO);

                    String pageNum = req.getParameter("pageNum");
                    String category = req.getParameter("pageCategory");
                    resp.sendRedirect("/product/edit?pageNum="+pageNum+"&category="+category);
                    break;
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}
