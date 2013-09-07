package com.example.test;

import java.util.ArrayList;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;

public class Filter extends BroadcastReceiver {
	String text;
	String contactName;
	Helper helper = new Helper();
	@Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.provider.Telephony.SMS_RECEIVED")) {
            Bundle extras = intent.getExtras();
            if (extras != null) {
                Object[] pdus = (Object[])extras.get("pdus");

                if (pdus.length < 1) return; 
                StringBuilder sb = new StringBuilder();
                String sender = null;
                for (int i = 0; i < pdus.length; i++) {
                    SmsMessage message = SmsMessage.createFromPdu((byte[]) pdus[i]);
                    if (sender == null){
                    	sender = message.getOriginatingAddress(); //get the sender
                    }
                    text = message.getMessageBody(); //get the message body
                    System.out.println("show text : "+text);
                    if (text != null){
                    	sb.append(text);
                    }
                }
                String number = "+94717516043"; //add the number that you want to filter
                if (sender.equals(number)) {
                    abortBroadcast();
                    helper.temp = text;
                    helper.processText();
                  /*  char[] body = new char[text.length()];
                    body = text.toCharArray();
                    ArrayList<Character> temp = new ArrayList<Character>();
                    for(int j=1;j<body.length;j++){
                    		if(body[j]==':'){
                    			break;
                    		}
                    		temp.add(body[j]);
                    		System.out.println("charactor "+j+" = "+body[j]);
                    } 
                    StringBuilder builder = new StringBuilder(temp.size());
                        for(Character ch: temp)
                        {
                            builder.append(ch);
                        }
                  contactName = builder.toString();
                  System.out.println("contact name = "+builder.toString());
                  helper.name = contactName;*/
                }
            }
        }
    }
}
