package com.eomcs.net;

import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ServerApp3 {

  public static void main(String[] args) throws Exception{
    Scanner keyScan = new Scanner(System.in);
    System.out.println("서버실행");

    ServerSocket serverSocket = new ServerSocket(8888);

    Socket socket = serverSocket.accept();
    System.out.println("클라이언트와 연결됨!");

    Scanner in = new Scanner(socket.getInputStream());
    PrintStream out = new PrintStream(socket.getOutputStream());

    while (true) {
      int message = in.nextInt();
      System.out.println(message);
      int message2 = in.nextInt();
      System.out.println(message2);
      char message3 = in.next().charAt(0);
      System.out.println(message3);


      int result = 0;
      switch(message3) {
        case '+' : result = message + message2; break;
        case '-' : result = message - message2; break;
        case '*' : result = message * message2; break;
        case '/' : result = message / message2; break;
        default : continue;
      }
      out.println(result);
      socket.close();

      keyScan.close();
      serverSocket.close();
    }


    //System.out.println("서버종료!");
  }

}