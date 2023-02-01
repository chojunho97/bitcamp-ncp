package com.eomcs.net;

import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class ClientApp3 {
  public static void main(String[] args) throws Exception {
    Scanner keyScan = new Scanner(System.in);
    System.out.println("클라이언트 실행중...");

    Socket socket = new Socket("192.168.0.26", 8888);

    System.out.println("서버에 연결되었음!");

    PrintStream out = new PrintStream(socket.getOutputStream());
    Scanner in = new Scanner(socket.getInputStream());

    while (true) {
      System.out.print("값1> ");
      int message = keyScan.nextInt();
      out.println(message);
      System.out.print("값2>");
      int message2 = keyScan.nextInt();
      out.println(message2);
      System.out.print("연산자>");
      char message3 = keyScan.next().charAt(0);
      out.println(message3);
      if (message3 == 'q') {
        break;
      }
      // System.out.println("서버에 메시지를 보냈음!");

      // 서버에서 응답이 올 때까지 리턴하지 않는다.
      int response = in.nextInt();
      System.out.printf("응답: %d\n", response);
    }
    out.close();
    in.close();
    socket.close();

    System.out.println("클라이언트 종료!");
  }
}

