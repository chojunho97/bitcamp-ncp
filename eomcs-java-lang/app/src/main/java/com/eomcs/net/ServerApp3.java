package com.eomcs.net;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Stack;

	//우선순위 지정 함수
public class ServerApp3 {
	
	static int priority(char temp) {
		if(temp == '(') return 0;
		else if(temp == '+' || temp == '-') return 1;
		else return 2;
	}

  public static void main(String[] args) throws Exception {
	  System.out.println("서버실행중...");
	  ServerSocket serverSocket = new ServerSocket(8888);
	  
	  Socket socket = serverSocket.accept();
	    System.out.println("클라이언트와 연결됨!");
	  
	  BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	  BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

	    StringBuilder sb = new StringBuilder();
	    Stack<Character> stack = new Stack<>();
	    String input = br.readLine();
	    
	    for(int i=0; i<input.length(); i++) {
	    	char temp = input.charAt(i);
	    	//연산자 외에는 바로 출력
	    	if('A'<=temp && temp <= 'Z') {
	    		sb.append(temp);
	    	}
	    	//여는 괄호 나오면 스텍에 넣음
	    	else if(temp == '(') {
	    		stack.add('(');
	    	}
	    	//닫는 괄호 나오면 여는 괄호가 나올때까지 출력큐에 넣어주고 여는큐는 삭제
	    	else if(temp == ')') {
	    		while(!stack.isEmpty()) {
	    			if(stack.peek() == '(') {
	    				stack.pop();
	    				break;
	    			}
	    			sb.append(stack.pop());
	    		}
	    	}
	    	//연산자를 스텍에 넣으려 할때 이미 현재꺼보다 우선순위가 높거나 같은게 있따면 빼줌... 그리고 스텍에 현재꺼 넣음
	    	else {
	    		while(!stack.isEmpty() && priority(stack.peek()) >= priority(temp)) {
	    			sb.append(stack.pop());
	    		}
	    		stack.add(temp);
	    	}
	    }
	    //남은 스텍처리
	    while(!stack.isEmpty()) {
	    	sb.append(stack.pop());
	    }
	    bw.write(sb.toString());
	    bw.flush();
	    bw.close();
	    br.close();
	    }
  }

    /*Scanner keyScan = new Scanner(System.in);
    System.out.println("서버실행");

    ServerSocket serverSocket = new ServerSocket(8888);

    Socket socket = serverSocket.accept();
    System.out.println("클라이언트와 연결됨!");

    Scanner in = new Scanner(socket.getInputStream());
    PrintStream out = new PrintStream(socket.getOutputStream());

    while (true) {
      String message = in.nextLine();
      System.out.println(message);
     
     
      
	}
      out.println(result);
      socket.close();

      keyScan.close();
      serverSocket.close();
    }

    //System.out.println("서버종료!");
}*/
	  