package com.example.test;

import java.util.ArrayList;

public class Helper {
	String temp;
	String name;
	public void processText() {
		 char[] body = new char[temp.length()];
         body = temp.toCharArray();
         ArrayList<Character> temp = new ArrayList<Character>();
         for(int j=1;j<body.length;j++){
         		if(body[j]==':'){
         			break;
         		}
         		temp.add(body[j]);
         		//System.out.println("charactor "+j+" = "+body[j]);
         } 
         StringBuilder builder = new StringBuilder(temp.size());
             for(Character ch: temp)
             {
                 builder.append(ch);
             }
       name = builder.toString();
       System.out.println("contact name = "+name);
	}
}
