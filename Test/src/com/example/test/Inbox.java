package com.example.test;

import android.app.Activity;
import android.content.ContentValues;
import android.net.Uri;

public class Inbox extends Activity {
	String sender,body,address;
	
	public void storeMessage(){
		ContentValues values = new ContentValues();
		  values.put("address", "+9233397365xx");//sender name
		  values.put("body", "this is my text");
		  getContentResolver().insert(Uri.parse("content://sms/inbox"), values);
	}
}
