package com.codepath.apps.twitterclientapp;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import com.codepath.apps.com.twitterclientapp.R;
import com.codepath.apps.com.twitterclientapp.R.layout;
import com.codepath.apps.com.twitterclientapp.R.menu;
import com.codepath.apps.twitterclientapp.models.Tweet;
import com.loopj.android.http.JsonHttpResponseHandler;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class ComposeActivity extends Activity {

	EditText twitter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_compose);
		setTitle(MainActivity.appTitle);
		twitter = (EditText)findViewById(R.id.etTwitter);
		cancelUpdate();
	/*	twitter.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				InputMethodManager imm = (InputMethodManager)
    	                getSystemService(Context.INPUT_METHOD_SERVICE);
    	        imm.showSoftInput(v, InputMethodManager.SHOW_IMPLICIT);
    	        Log.d("click", "soft keyboaed");
			}
			
		});*/
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.compose, menu);
		return true;
	}
	public void tweetStatus(View v){
		TwitterClientApp.getRestClient().statusUpdate(new JsonHttpResponseHandler(){
        	@Override
			public void onSuccess(JSONArray jsonTweets) {
        		//Log.d("TS","JSONArray");
        		finish();
        	}
        	@Override
        	public void onSuccess(JSONObject response) {
        		//Log.d("TS","JSONObject");
        		finish();
        	}
        	@Override
        	public void onFailure(Throwable e, JSONArray errorResponse) {
        		//Log.d("TF",e.getMessage());
        	}
            
        	@Override
        	public void onFailure(Throwable e, JSONObject errorResponse) {
        		//Log.d("TF",e.getMessage());
        	}
        	          
        	
        },twitter.getText().toString());
	}
	final Context context = this;
	public void cancelUpdate(){
		Button button = (Button) findViewById(R.id.btCancel);
		button.setOnClickListener(new OnClickListener() {
		@Override
		public void onClick(View arg0) {
 
			AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
 
			// set title
			alertDialogBuilder.setTitle("Are You Want To Cancel?");
 
			// set dialog message
			alertDialogBuilder
				//.setMessage("Are You Want To Cancel?")
				//.setCancelable(true)
				.setPositiveButton("Quit and Save",new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog,int id) {
						//save();
						finish();
					}
				  })
				.setNegativeButton("Cancel",new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog,int id) {
						dialog.cancel();
					}
				}).setNeutralButton("Quite and No Save", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog,int id) {
						finish();
					}
	            });
 
				// create alert dialog
				AlertDialog alertDialog = alertDialogBuilder.create();
 
				// show it
				alertDialog.show();
			}
		});
	}
		

}
