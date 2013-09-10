package com.example.test;

import java.util.ArrayList;

public class Helper {
	String temp;
	String name;
	String address,message;
	int m=0;
	private static Helper helper=null;
	private Helper(){
		
	}
	public void processText() {		//get the name of the sender from the message body
		message = temp;
		 char[] body = new char[temp.length()];
         body = temp.toCharArray();
         ArrayList<Character> temp = new ArrayList<Character>();
         for(int j=1;j<body.length;j++){
         		if(body[j]==':'){
         			m=j;
         			break;
         		}
         		temp.add(body[j]);
         } 
         StringBuilder builder = new StringBuilder(temp.size());
             for(Character ch: temp)
             {
                 builder.append(ch);
             }
       name = builder.toString();
	}
	public void processBody(){		//get the actual message from the message body
		char[] text = new char[temp.length()];
		text = temp.toCharArray();
		ArrayList<Character> temp1 = new ArrayList<Character>();
		for(int i=m+1;i<temp.length();i++){
			temp1.add(text[i]);
		}
		StringBuilder builder1 = new StringBuilder(temp1.size());
		for(Character ch1: temp1){
			builder1.append(ch1);
		}
		message = builder1.toString();
		System.out.println("messsage body = "+message);
	}
	public String sendName() {
		return this.name;
	}
	public static Helper getInstant(){		//singleton design pattern
		if(helper==null){
			helper = new Helper();
		}
		return helper;
	}
}
