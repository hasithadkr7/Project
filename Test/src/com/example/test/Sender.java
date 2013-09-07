package com.example.test;

import java.io.BufferedInputStream;
import java.io.InputStream;

//import com.example.contactreader.R;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Sender extends Activity {
	//private Button button;
	String check;
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.message);
		Helper helper = new Helper();
		check = helper.name;
		System.out.println("XXXXXXXXXXXXXX "+check+" XXXXXXXXXXXXXXX");
		ContentResolver cr = getContentResolver();  
		Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI,null, null, null, null);  
		if (cur.getCount() > 0) {  
			while (cur.moveToNext()) {  
				String id = cur.getString(  
						cur.getColumnIndex(ContactsContract.Contacts._ID));  
				String name = cur.getString(  
						cur.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));  
				if (Integer.parseInt(cur.getString(  
						cur.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0) {  
					Cursor pCur = cr.query(  
							ContactsContract.CommonDataKinds.Phone.CONTENT_URI,   
							null,   
							ContactsContract.CommonDataKinds.Phone.CONTACT_ID +" = ?",   
									new String[]{id}, null);  
					while (pCur.moveToNext()) {  
						if(check.equals(name)){            
							Uri my_contact_Uri = Uri.withAppendedPath(ContactsContract.Contacts.CONTENT_URI, String.valueOf(id));  
							InputStream photo_stream = ContactsContract.Contacts.openContactPhotoInputStream(getContentResolver(),my_contact_Uri);        
							BufferedInputStream buf = new BufferedInputStream(photo_stream);  
							Bitmap my_btmp = BitmapFactory.decodeStream(buf);  
							
							LayoutInflater inflater = getLayoutInflater();
			 
							View layout = inflater.inflate(R.layout.message,
							  (ViewGroup) findViewById(R.id.custom_toast_layout_id));
			 
							// set image
							ImageView image = (ImageView) layout.findViewById(R.id.image);
							image.setImageBitmap(my_btmp);
			 
							// set a message
							TextView text = (TextView) layout.findViewById(R.id.text);
							text.setText(name);
			 
							// Toast...
							Toast toast = new Toast(getApplicationContext());
							toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
							toast.setDuration(Toast.LENGTH_LONG);
							toast.setView(layout);
							toast.show();
						}  
					}   
					pCur.close();  
				}  
			}  
		}
	}
}
