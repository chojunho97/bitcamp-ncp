package bitcamp.myapp;

public class App {

  public static void main(String[] args) {

    goMainMenu();
    System.out.println("안녕히가세요");

    Prompt.close();
  } // main()

  private static void goMainMenu() {
    while(true) {
      System.out.println("1. 회원관리");
      System.out.println("9. 종료");
      int menu = Prompt.inputInt("메뉴> ");

      if(menu == 1) {
        MemberHandler.service();
      } else if(menu == 9) {
        break;
      } else {
        System.out.println("잘못된 메뉴 번호 입니다.");
      }
    }
  }
} // class App









