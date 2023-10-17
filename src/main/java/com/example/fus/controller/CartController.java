package com.example.fus.controller;

import com.example.fus.dto.CartDTO;
import com.example.fus.dto.ProductDTO;
import com.example.fus.service.CartService;
import com.example.fus.service.ProductService;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Log4j2
@WebServlet("/cart/*")
public class CartController extends HttpServlet {
    private String path = null;
    private CartService cartService = null;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        cartService = new CartService();
        path = req.getPathInfo();

        if (path == null) {
            path = "/list";
        }

        switch (path) {
            case "/list":
                List<CartDTO> cartDTOList = cartService.listCart(req);
                req.setAttribute("cartDTOList", cartDTOList);
                req.getRequestDispatcher("/WEB-INF/cart/cartView.jsp").forward(req, resp);
                break;
            case "/add":
                int productId = Integer.parseInt(req.getParameter("productId")); // 아이디 값을 받아와서
                ProductService productService = new ProductService();
                ProductDTO productDTO = productService.getProduct(productId); // 해당 아이디의 상품정보를 가져옴
                String count = req.getParameter("count");
                String test1 = req.getParameter("productId");
                String test2 = req.getParameter("productName");
                log.info("cart Controller count = " + count);
                log.info("cart TEst count = " + test1);
                log.info("cart TEst coun4234t = " + test2);
                cartService.insertCart(req, productDTO, count);
                resp.sendRedirect("/product/view?productId="+productId);
                break;
            case "/oneRemove" :
                cartService.oneRemoveCart(req);
                resp.sendRedirect("/cart/list");
                break;
            case "/allRemove":
                cartService.allRemoveCart(req);
                req.getRequestDispatcher("/WEB-INF/cart/cartView.jsp").forward(req, resp);
                break;
            case "/selectRemove" :
                String[] checkIds = req.getParameterValues("checkId");
//                for(String checkId : checkIds) { log.info("테스트 : " + checkId); }
                cartService.checkedRemoveCart(req, checkIds); // productId를 가진 배열
                resp.sendRedirect("/cart/list");
                break;
            case "/order":
                req.getRequestDispatcher("/WEB-INF/cart/orderView.jsp").forward(req, resp);
                break;
            case "/allOrder":
                List<CartDTO> order = cartService.allOrderCart(req);
                log.info(order);
                req.setAttribute("cartDTOList", order);
                req.getRequestDispatcher("/WEB-INF/cart/orderView.jsp").forward(req, resp);
                break;
            case "/selectOrder":
                checkIds = req.getParameterValues("checkId");
                for (String id : checkIds) {
                    log.info(id);
                }
                log.info("controll" + checkIds);
                List<CartDTO> carts = cartService.orderCart(req, checkIds);
                req.setAttribute("cartDTOList", carts);
                req.getRequestDispatcher("/WEB-INF/cart/orderView.jsp").forward(req, resp);
                break;
            case "/orderFinish":

                req.getRequestDispatcher("/WEB-INF/cart/orderFinish.jsp").forward(req, resp);
        }
    }
}