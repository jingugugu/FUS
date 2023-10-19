package com.example.fus.controller;

import com.example.fus.dto.BoardDTO;
import com.example.fus.service.BoardService;
import com.example.fus.service.CartService;
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
@WebServlet("/board/*")
@MultipartConfig(maxFileSize = 2 * 1024 * 1024, location ="c:/upload/fus/board" )
public class BoardController extends HttpServlet {
    private BoardService boardService = null;
    private String path = null;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        boardService = new BoardService();

        path = req.getPathInfo();


        if (path == null) {
            path = "/all";
        }

        switch (path) {
            case "/all":
                String pageNum = "1";
                if(req.getParameter("pageNum") != null) {
                    pageNum = req.getParameter("pageNum");
                }
                String count = boardService.sizeBoards();
                List<BoardDTO> boardDTOList = boardService.listBoardDTO(pageNum);
                req.setAttribute("boardDTOList", boardDTOList);
                req.setAttribute("pageNum", pageNum);
                req.setAttribute("count", count);
                req.getRequestDispatcher("/WEB-INF/board/boardList.jsp").forward(req, resp);
                break;

            case "/add":
                req.getRequestDispatcher("/WEB-INF/board/boardAdd.jsp").forward(req, resp);
                break;

            case "/remove":
                boardService.removeBoard(req);
                resp.sendRedirect("/board/all");
                break;

            case "/view":
                boardService.boardCountUp(req);
                BoardDTO boardDTO = boardService.getBoard(req);
                log.info("확인" + boardDTO.getMemberId());
                req.setAttribute("boardDTO", boardDTO);
                req.getRequestDispatcher("/WEB-INF/board/boardView.jsp").forward(req, resp);
                break;
            case "/modify":
                boardService.getBoard(req);
                req.getRequestDispatcher("/WEB-INF/board/boardModify.jsp").forward(req, resp);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        boardService = new BoardService();

        path = req.getPathInfo();


        if (path == null) {
            path = "/all";
        }
        switch (path) {
            case "/add":
                boardService.boardAdd(req);
                resp.sendRedirect("/board/all");
                break;

            case "/remove":
                boardService.removeBoard(req);
                resp.sendRedirect("/board/all");
                break;
            case "/view":
                boardService.getBoard(req);
                req.getRequestDispatcher("/WEB-INF/board/boardView.jsp").forward(req, resp);
                break;

            case "/modify":
                BoardDTO editBoardDTO = boardService.getBoard(req);
                req.setAttribute("editBoardDTO", editBoardDTO);
                log.info(editBoardDTO);
                req.getRequestDispatcher("/WEB-INF/board/boardModify.jsp").forward(req, resp);
                break;
            case "/modify.do":
                log.info("1");
                log.info("String " + req.getParameter("boardNum"));
                boardService.modifyBoard(req); //content를 받지 못하고 있음....
                log.info("2");
                resp.sendRedirect("/board/all");
                break;
        }


    }

}
