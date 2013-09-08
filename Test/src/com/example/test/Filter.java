package com.example.test;

import java.util.ArrayList;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.sax.StartElementListener;
import android.telephony.SmsMessage;

public class Filter extends BroadcastReceiver {
	String text;
	String contactName;
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
                    Helper helper = Helper.getInstant();
                    helper.temp = text;
                    helper.address = number;
                    helper.processText();
                    contactName= helper.sendName();
                    helper.name = contactName;
                    Intent intent2 = new Intent(context, Sender.class);
                    intent2.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent2);
                   // System.out.println("XXXXXXXXX"+contactName+"XXXXXXXXX");
                    Intent intent3 = new Intent(context, Inbox.class);
                    intent3.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent3);
                }
            }
        }
    }
}
