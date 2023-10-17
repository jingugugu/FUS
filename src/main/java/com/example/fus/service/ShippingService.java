package com.example.fus.service;

import com.example.fus.dao.ShippingDAO;
import com.example.fus.dto.ProductDTO;
import com.example.fus.dto.ShippingDTO;
import com.example.fus.dto.UserDTO;
import lombok.extern.log4j.Log4j2;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class ShippingService {
    private final ShippingDAO shippingDAO;

    public ShippingService() {shippingDAO = new ShippingDAO();}

    //주문 정보 등록
    public void addShipping(HttpServletRequest request) {
        List<ShippingDTO> shippingDTOList = new ArrayList<>();
        try{
            HttpSession session = request.getSession();
            UserDTO userDTO = (UserDTO) session.getAttribute("loginInfo");
            String memberId = userDTO.getMemberId();
            String[] productIds = request.getParameterValues("productId");
            String[] productNames = request.getParameterValues("productName");
            String[] counts = request.getParameterValues("count");
            for(String productId : productIds) {
                log.info(productId);
            }
            log.info(productNames[0]);
            log.info(counts[0]);
            log.info(request.getParameter("receiverName"));
            log.info(request.getParameter("receiverZipCode"));
            log.info(request.getParameter("receiverAddress01"));
            log.info(request.getParameter("receiverAddress02"));
            for(int i = 0; i<productIds.length; i++) {
                ShippingDTO shippingDTO = ShippingDTO.builder()
                        .memberId(memberId)
                        .productId(Integer.parseInt(productIds[i]))
                        .productName(productNames[i])
                        .count(Integer.parseInt(counts[i]))
                        .phone(userDTO.getPhone())
                        .receiverName(request.getParameter("receiverName"))
                        .receiverZipCode(request.getParameter("receiverZipCode"))
                        .receiverAddress01(request.getParameter("receiverAddress01"))
                        .receiverAddress02(request.getParameter("receiverAddress02"))
                        .build();
                shippingDTOList.add(shippingDTO);
            }
            shippingDAO.insertShipping(shippingDTOList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}