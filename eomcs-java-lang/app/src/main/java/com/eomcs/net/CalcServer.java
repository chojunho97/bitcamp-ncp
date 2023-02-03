package com.eomcs.net;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.StringTokenizer;

public class CalcServer {

	static String calc(String exp) {

		StringTokenizer st = new StringTokenizer(exp, " ");

		if (st.countTokens() != 3) return "Error!!!!!";

		int op1 = Integer.parseInt(st.nextToken());
		String operator = st.nextToken();
		int op2 = Integer.parseInt(st.nextToken());

		String result = "";
		switch (operator) {
		case "+": result = Integer.toString(op1 + op2);
		break;
		case "-": result = Integer.toString(op1 - op2);
		break;
		case "*": result = Integer.toString(op1 * op2);
		break;
		case "/": result = String.valueOf(Math.floorDiv(op1, op2));
		break;
		case "^": result = String.valueOf(Math.pow(op1, op2));
		break;
		default : result = "Holly molly Operator Error";
		}
		return result;
	}

	public static void main(String[] args) throws Exception {

		Scanner keyScan = new Scanner(System.in);

		System.out.println("서버 실행중~");
		ServerSocket serverSocket = new ServerSocket(8888);

		Socket socket = serverSocket.accept();
		System.out.println("클라이언트와 연결 됨 ㅋ");

		BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

		try {
			while(true) {
				String inputMsg = in.readLine();

				if(inputMsg.equalsIgnoreCase("q")) {
					System.out.println("Client 에서 연결 종료 ㅋㅋ");
					break;
				}
				System.out.println("결과 >> "+ inputMsg);

				out.write(calc(inputMsg) +"\n");
				out.flush();
			}
		} catch (IOException e) {
			System.out.println("채팅 중 오류가 발생");
		}

		System.out.println("사바 종료요");
		keyScan.close();
		if(socket != null) socket.close();
		if(serverSocket != null) serverSocket.close();

	}
}
