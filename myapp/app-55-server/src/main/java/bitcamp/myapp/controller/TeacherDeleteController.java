package bitcamp.myapp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import bitcamp.myapp.service.TeacherService;
import bitcamp.util.RequestMapping;

@RequestMapping("/teacher/delete")
public class TeacherDeleteController implements PageController {

  private TeacherService teacherService;

  public TeacherDeleteController(TeacherService teacherService) {
    this.teacherService = teacherService;
  }

  @Override
  public String execute(HttpServletRequest request, HttpServletResponse response) {

    try {
      teacherService.delete(Integer.parseInt(request.getParameter("no")));
    } catch (Exception e) {
      e.printStackTrace();
      request.setAttribute("error", "other");
    }
    return "/teacher/delete.jsp";
  }
}
