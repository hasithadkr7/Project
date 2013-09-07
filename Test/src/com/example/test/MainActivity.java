package com.example.test;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		final Helper helper = new Helper();
		Button button = (Button) findViewById(R.id.button1);
		final TextView textView = (TextView) findViewById(R.id.textView1);
		button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				helper.processText();
				String name = helper.getContactName();
				System.out.println("contact name = "+name);
				textView.setText(name);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
