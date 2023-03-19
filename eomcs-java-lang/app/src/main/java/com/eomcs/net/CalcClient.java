package com.eomcs.net;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

public class CalcClient {
  public static void main(String[] args) throws Exception {

    Scanner scanner = new Scanner(System.in);
    System.out.println("클라 실행중 ~");


    Socket socket = new Socket("192.168.0.4", 8888);
    System.out.println("server 연결중");

    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

    try {

      while (true) {
        System.out.print("계산기 >> ");
        String outputMessage = scanner.nextLine();
        if(outputMessage.equalsIgnoreCase("q")) {
          out.write(outputMessage+"\n");
          out.flush();
          break;
        }
        out.write(outputMessage+"\n");
        out.flush();
        String inputMessage = in.readLine();
        System.out.println("답 : "+inputMessage);
      }
    } catch(IOException e) {
      System.out.println(e.getMessage());
    } finally {
      try {
        scanner.close();
        if(socket != null) socket.close();
        out.close();
        in.close();
      } catch (IOException e) {
        System.out.printf("채팅 중 오류가 발생", e);
      }
    }

    System.out.println("클라 종료");

  }
}
