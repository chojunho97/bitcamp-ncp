package test;

import test.BoardHandler;
import test.Prompt;

public class App {

  public static void main(String[] args) {
    goMainMenu();
    System.out.println("안녕히 가세요!");

    Prompt.close();
  } 

  private static void goMainMenu() {
	BoardHandler generalBoardHandler = new BoardHandler("일반게시판");
    BoardHandler adminBoardHandler = new BoardHandler("관리자게시판");


    while (true) {
      System.out.println("1. 일반게시판");
      System.out.println("2. 관리자게시판");
      System.out.println("9. 종료");
      int menuNo = Prompt.inputInt("메뉴> ");

      if (menuNo == 1) {
        generalBoardHandler.service();
      } else if (menuNo == 2) {
    	  adminBoardHandler.service();
      } else if (menuNo == 9) {
        break;
      } else {
        System.out.println("잘못된 메뉴 번호 입니다.");
      }
    }
  }

}









