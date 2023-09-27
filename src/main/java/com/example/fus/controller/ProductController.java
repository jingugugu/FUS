package com.example.fus.controller;

import com.example.fus.dto.ProductDTO;
import com.example.fus.service.ProductService;
import lombok.extern.log4j.Log4j2;

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
@MultipartConfig(maxFileSize = 5 * 1024 * 1024, location = "c:/upload") //파일 용량 5mb
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
                List<ProductDTO> productDTOList = productService.listProduct();
                req.setAttribute("products", productDTOList);
                req.getRequestDispatcher("/WEB-INF/product/productList.jsp").forward(req, resp);
                break;
            case "/view":
                String productId = req.getParameter("productId");
                ProductDTO product = productService.getProduct(productId);
                req.setAttribute("product", product);
                log.info(product);
                req.getRequestDispatcher("/WEB-INF/product/productView.jsp").forward(req, resp);
                break;

        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


    }
}
