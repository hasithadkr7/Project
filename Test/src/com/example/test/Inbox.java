package com.example.test;

import android.app.Activity;
import android.content.ContentValues;
import android.net.Uri;
import android.os.Bundle;

public class Inbox extends Activity {
	String sender,body,address;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.inbox);
		
		Helper helper = Helper.getInstant();
		this.body  = helper.message;
		this.sender = helper.contact;
		this.address = helper.address;
		//System.out.println("CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC");
		ContentValues values = new ContentValues();
		  values.put("address", sender);//sender name
		  values.put("body", body);
		  getContentResolver().insert(Uri.parse("content://sms/inbox"), values);
		  System.out.println("CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC");
	}
}
