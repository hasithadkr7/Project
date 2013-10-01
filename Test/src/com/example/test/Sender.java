package com.example.test;

import java.io.BufferedInputStream;
import java.io.InputStream;

//import com.example.contactreader.R;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
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
	String check;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Helper helper = Helper.getInstant();
		check = helper.name;
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
							//LayoutInflater inflater = getLayoutInflater();
							// set image
							//ImageView image = (ImageView) layout.findViewById(R.id.image);
							//image.setImageBitmap(my_btmp);
							//Helper helper = Helper.getInstant();
							helper.senderImage = my_btmp;
							// set a message
							//TextView text = (TextView) layout.findViewById(R.id.text);
							//text.setText("New Message from \n"+name);
							//Custom Toast...
							Context context = getApplicationContext();
							/*Intent intent3 = new Intent(context, Inbox.class);
			                intent3.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			                context.startActivity(intent3);*/
							Intent intent4 = new Intent(context,CustomDialog.class);
							intent4.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
							context.startActivity(intent4);
							/*Toast toast = new Toast(getApplicationContext());
							toast.setGravity(Gravity.BOTTOM, 0, 0);
							toast.setDuration(Toast.LENGTH_LONG);
							toast.setView(layout);
							toast.show();*/
							//moveTaskToBack(true);
						}  
					}   
					pCur.close();  
				}  
			}  
		}
	}
}
