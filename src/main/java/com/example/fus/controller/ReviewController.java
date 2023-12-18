package com.example.fus.controller;

import com.example.fus.service.ProductService;
import com.example.fus.service.ReviewService;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Log4j2
@WebServlet("/review/*")
@MultipartConfig(maxFileSize = 5 * 1024 * 1024, location = "/Users/imjingu/Desktop/dev/upload/fus/review")
public class ReviewController extends HttpServlet {
    private String path;
    private ReviewService reviewService = null;
    ProductService productService = null;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        reviewService = new ReviewService();
        path = req.getPathInfo();

        if(path == null){
            path = "/list";
        }
        switch (path) {
            case "/remove":
                int index = Integer.parseInt(req.getParameter("index"));
                int productId = Integer.parseInt(req.getParameter("productId"));
                try {
                    reviewService.removeReview(index);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                resp.sendRedirect("/product/view?productId="+productId);
                break;
        }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        reviewService = new ReviewService();
        productService = new ProductService();
        path = req.getPathInfo();

        if(path == null){
            path = "/list";
        }
        switch (path) {
            case "/add":
                int productId = Integer.parseInt(req.getParameter("productId"));
                log.info("aa"+req.getParameter("productId"));
                reviewService.addReview(req);
                productService.reviewCountUp(productId);
                resp.sendRedirect("/product/view?productId=" + productId);
        }
    }
}
