package com.example.test;

import android.app.Activity;
import android.content.ContentValues;
import android.net.Uri;
import android.os.Bundle;

public class Inbox extends Activity {
	String body,address;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Helper helper = Helper.getInstant();
		this.body  = helper.message;
		this.address = helper.address;
		ContentValues values = new ContentValues();
		values.put("address", address);
		values.put("body", body);
		System.out.println("address = "+address+" "+"body = "+body);
		getContentResolver().insert(Uri.parse("content://sms/inbox"), values);
	}
}
