package com.eomcs.net;

import java.io.*;
import java.util.*;
public class Calculator {
	//우선순위 지정 함수
	static int priority(char temp) {
		if(temp == '(') return 0;
		else if(temp == '+' || temp == '-') return 1;
		else return 2;
	}
	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

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