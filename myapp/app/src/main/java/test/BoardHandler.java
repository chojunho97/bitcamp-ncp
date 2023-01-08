package test;

import java.sql.Date;

public class BoardHandler {

  final int SIZE = 100;
  int count;

  //모든 인스턴스가 공유하는 데이터를 스태틱 필드로 만든다.
  //특히 데이터를 조회하는 용으로 사용하는 final 변수는 스태틱 필드로 만드렁야 한다.
  Board[] members = new Board[SIZE];
  String title;

  //인스턴스를 만들 때 프롬프트 제목을 반드시 입력하도록 한다.
  BoardHandler(String title) {
    this.title = title;
  }
  void inputBoard() {
	Board m = new Board();
    m.no = Prompt.inputInt("번호? ");
    m.name = Prompt.inputString("제목? ");
    m.content = Prompt.inputString("내용? ");
    m.pwd = Prompt.inputString("암호? ");
    m.hits = Prompt.inputInt("조회수? ");
    m.createdDate = new Date(System.currentTimeMillis()).toString();

    this.members[count++] = m;
  }

  void printBoards() {
    System.out.println("번호\t제목\t작성일\t조회수");

    for (int i = 0; i < this.count; i++) {
      Board m = this.members[i];
      System.out.printf("%d\t%s\t%s\t%d\n",
          m.no, m.name, m.createdDate,
          m.hits);
    }
  }

  void printBoard() {
    int BoardNo = Prompt.inputInt("회원번호? ");

    Board m = this.findByNo(BoardNo);

    if (m == null) {
      System.out.println("해당 번호의 회원이 없습니다.");
      return;
    }

    System.out.printf("    번호: %s\n", m.no);
    System.out.printf("    제목: %s\n", m.name);
    System.out.printf("    내용: %s\n", m.content);
    System.out.printf("  작성일: %s\n", m.createdDate);
    System.out.printf("  조회수: %s\n", m.hits);
  }


  void modifyBoard() {
    int BoardNo = Prompt.inputInt("게시글번호? ");

    Board old = this.findByNo(BoardNo);

    if (old == null) {
      System.out.println("해당 번호의 회원이 없습니다.");
      return;
    }

    // 변경할 데이터를 저장할 인스턴스 준비
    Board m = new Board();
    m.no = old.no;
    m.createdDate = old.createdDate;
    m.name = Prompt.inputString(String.format("이름(%s)? ", old.name));
    m.content = Prompt.inputString(String.format("전화(%s)? ", old.content));
    m.hits = old.hits;

    String str = Prompt.inputString("정말 변경하시겠습니까?(y/N) ");
    if (str.equalsIgnoreCase("Y")) {
      this.members[indexOf(old)] = m;
      System.out.println("변경했습니다.");
    } else {
      System.out.println("변경 취소했습니다.");
    }

  }

  void deleteBoard() {
    int BoardNo = Prompt.inputInt("회원번호? ");

    Board m = this.findByNo(BoardNo);

    if (m == null) {
      System.out.println("해당 번호의 회원이 없습니다.");
      return;
    }

    String str = Prompt.inputString("정말 삭제하시겠습니까?(y/N) ");
    if (!str.equalsIgnoreCase("Y")) {
      System.out.println("삭제 취소했습니다.");
      return;
    }

    for (int i = this.indexOf(m) + 1; i < count; i++) {
      this.members[i - 1] = this.members[i];
    }
    this.members[--count] = null; // 레퍼런스 카운트를 줄인다.

    System.out.println("삭제했습니다.");

  }

  Board findByNo(int no) {
    for (int i = 0; i < this.count; i++) {
      if (this.members[i].no == no) {
        return this.members[i];
      }
    }
    return null;
  }

  int indexOf(Board m) {
    for (int i = 0; i < this.count; i++) {
      if (members[i].no == m.no) {
        return i;
      }
    }
    return -1;
  }

  void searchBoard() {
    String name = Prompt.inputString("제목? ");

    System.out.println("번호\t제목\t내용\t작성일\t조회수");

    for (int i = 0; i < this.count; i++) {
    	Board m = this.members[i];
      if(m.name.equalsIgnoreCase(name)) {
        System.out.printf("%d\t%s\t%s\t%d\t%d\n",
            m.no, m.name, m.content,m.createdDate, m.hits);
            }
    }
  }

  void service() {
    while (true) {
      System.out.printf("[%s]\n", this.title);
      System.out.println("1. 등록");
      System.out.println("2. 목록");
      System.out.println("3. 조회");
      System.out.println("4. 변경");
      System.out.println("5. 삭제");
      System.out.println("6. 검색");
      System.out.println("0. 이전");
      int menuNo = Prompt.inputInt(String.format("%s> ", this.title));

      switch (menuNo) {
        case 0: return;
        case 1: this.inputBoard(); break;
        case 2: this.printBoards(); break;
        case 3: this.printBoard(); break;
        case 4: this.modifyBoard(); break;
        case 5: this.deleteBoard(); break;
        case 6: this.searchBoard(); break;
        default:
          System.out.println("잘못된 메뉴 번호 입니다.");
      }
    }
  }
}
