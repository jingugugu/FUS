package com.example.fus.controller;

import com.example.fus.dto.RippleDTO;
import com.example.fus.service.RippleService;
import lombok.extern.log4j.Log4j2;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Log4j2
@WebServlet("/ripple/*")
public class RippleController extends HttpServlet {
    private String path;
    private RippleService rippleService = null;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String RequestURI = req.getRequestURI();
        String contextPath = req.getContextPath();
        String command = RequestURI.substring(contextPath.length());

        log.info("command : " + command); //도메인 이후의 경로를 불러옴
        resp.setContentType("text/html; charset=utf-8");
        req.setCharacterEncoding("utf-8");

        rippleService = new RippleService();
        path = req.getPathInfo();

        if(path == null){
            path = "/list";
            try{
                req.getRequestDispatcher("/WEB-INF/board/boardList.jsp").forward(req, resp);
            } catch (Exception e){
                e.printStackTrace();
            }
        }

        switch (path){
            case "/add":
                try{
                    JSONObject jsonObject = new JSONObject(); //JSON 정보를 담기 위해 객체를 생성

                    //성공, 실패의 결과를 JSON에 저장.
                    if (rippleService.addRipple(req) == 1){
                        jsonObject.put("result", "true");
                    } else {
                        jsonObject.put("result", "false");
                    }
                    resp.getWriter().println(jsonObject.toJSONString()); //JSON 결과물 도출
                } catch (Exception e){
                    throw new RuntimeException(e);
                }
                break;

            case "/get":
                try {
                    log.info("1");
                    List<RippleDTO> rippleDTOS = rippleService.getRipples(req);
                    log.info("2");
                    //collection list를 json으로 변환.
                    JSONArray jsonArray = new JSONArray(); //목록을 저장해야 하기 때문에 JSONArray()를 사용
                    log.info(jsonArray);
                    for (RippleDTO rippleDTO : rippleDTOS) {
                        JSONObject jsonObject = new JSONObject();
                        jsonObject.put("rippleId", rippleDTO.getRippleId());
                        jsonObject.put("name", rippleDTO.getName());
                        jsonObject.put("content", rippleDTO.getContent());
                        jsonObject.put("ip", rippleDTO.getIp());
                        jsonObject.put("insertDate", rippleDTO.getInsertDate());
                        jsonObject.put("isLogin", rippleDTO.isLogin());
                        jsonArray.add(jsonObject);
                    }
                    log.info("3");
                    log.info(jsonArray);
                    resp.getWriter().print(jsonArray.toJSONString());
                    log.info("4");
                } catch (Exception e){
                    throw  new RuntimeException(e);
                }
                break;

            case "/remove":
                try{
                    JSONObject jsonObject = new JSONObject(); // JSON 정보를 담기 위해 객체 생성
                    if(rippleService.removeRipple(req)){
                        jsonObject.put("result", "true");
                    } else {
                        jsonObject.put("result", "false");
                    }
                    resp.getWriter().println(jsonObject.toJSONString());
                } catch (Exception e){
                    throw new RuntimeException(e);
                }
                break;
            case "/view":
                try{
                    req.getRequestDispatcher("/WEB-INF/board/boardView.jsp").forward(req, resp);
                } catch (Exception e){
                    e.printStackTrace();
                }
                break;
        }

    }

}
