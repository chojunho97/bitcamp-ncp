package com.eomcs.net;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

public class CalcClinet {

  public static void main(String[] args) throws Exception {

    Scanner keyScan = new Scanner(System.in);
    System.out.println("클라 실행중 ~");


    Socket socket = new Socket("192.168.0.6", 8888);
    System.out.println("server 연결중");

    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

    try {

      while (true) {
        System.out.print("계산기 >> ");
        String outputMsg = keyScan.nextLine();

        if(outputMsg.equalsIgnoreCase("q")) {
          out.write(outputMsg+"\n");
          out.flush();
          break;
        }
        out.write(outputMsg+"\n");
        out.flush();
        System.out.println("답 : "+in.readLine());
      }
    } catch (IOException e) {
      System.out.printf("채팅 중 오류가 발생\n", e);
    }

    System.out.println("클라 종료");
    keyScan.close();
    if(socket != null) socket.close();
    out.close();
    in.close();
  }
}
