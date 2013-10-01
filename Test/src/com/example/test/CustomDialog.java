package com.example.test;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomDialog extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.dialog);
		
		Context context = this;
		final Dialog dialog = new Dialog(context);
		dialog.setContentView(R.layout.dialog);
		dialog.setTitle("New message from");
		Helper helper = Helper.getInstant();
		Bitmap bitmap = helper.senderImage;
		Button now = (Button) dialog.findViewById(R.id.button1);
		Button later = (Button) dialog.findViewById(R.id.button2);
		ImageView image = (ImageView) dialog.findViewById(R.id.imageView1);
		TextView sender = (TextView) dialog.findViewById(R.id.textView1);
		image.setImageBitmap(bitmap);
		sender.setText(helper.name);
		System.out.println("XXXXXXXX = "+helper.name);
		now.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				
			}
		});
		later.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				dialog.dismiss();
			}
		});
	}
}
