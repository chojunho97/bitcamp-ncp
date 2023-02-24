package bitcamp.myapp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import bitcamp.myapp.service.BoardService;

public class BoardFormController implements PageController {

  private BoardService boardService;

  public BoardFormController(BoardService boardService) {
    this.boardService=  boardService;
  }
  @Override
  public String execute(HttpServletRequest request, HttpServletResponse response)
  {
    return "/board/form.jsp";
  }
}