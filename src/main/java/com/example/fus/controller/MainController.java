package com.example.fus.controller;

import com.example.fus.dto.ProductDTO;
import com.example.fus.service.ProductService;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Log4j2
@WebServlet("/main")
//@MultipartConfig(location = "c:/upload") // 최대 파일 크기와 저장 위치를 지정11
public class MainController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException {
        try {
            ProductService productService = new ProductService();
            List<ProductDTO> productList = productService.listProductLimit8();
            for (ProductDTO products : productList){
                log.info(products);
            }
            req.setAttribute("productList", productList);
            req.getRequestDispatcher("/WEB-INF/main/main.jsp").forward(req, resp);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new ServletException("index error");
        }
    }
}
