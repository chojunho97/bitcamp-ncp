package com.eomcs.net;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class ClientApp3 {
  public static void main(String[] args) throws Exception {
    Scanner keyScan = new Scanner(System.in);
    System.out.println("클라이언트 실행중...");

    Socket socket = new Socket("192.168.0.4", 8888);

    System.out.println("서버에 연결되었음!");

    BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	  BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

    while (true) {
      System.out.print("계산식을 입력하시오> ");
      String message = keyScan.nextLine();
      bw.write(message);
      
      if (message == "quit") {
        break;
      }
      // System.out.println("서버에 메시지를 보냈음!");

      // 서버에서 응답이 올 때까지 리턴하지 않는다.
      String response = br.readLine();
      System.out.printf("응답: %s\n", response);
    }
    bw.close();
    br.close();
    socket.close();

    System.out.println("클라이언트 종료!");
  }
}

