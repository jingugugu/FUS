package com.example.fus.controller;

import com.example.fus.service.CartService;
import com.example.fus.service.ShippingService;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Log4j2
@WebServlet("/shipping/*")
public class ShippingController extends HttpServlet {
    private String path;
    private ShippingService shippingService = null;
    private CartService cartService = null;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        path = req.getPathInfo();

        if (path == null) {
            resp.sendRedirect("/main");
        }
        try {
            switch (path) {
                case "/complete":
                    req.getRequestDispatcher("/WEB-INF/cart/orderFinish.jsp").forward(req, resp);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        shippingService = new ShippingService();
        cartService = new CartService();
        path = req.getPathInfo();

        if (path == null) {
            resp.sendRedirect("/main");
        }
        try {
            switch (path) {
                case "/order":
                    log.info("shipping order!!");
                    String[] productIds = req.getParameterValues("productId");
                    cartService.orderFinish(req, productIds); // productId를 가진 배열
                    shippingService.addShipping(req);
                    resp.sendRedirect("/shipping/complete");
                    break;
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}
