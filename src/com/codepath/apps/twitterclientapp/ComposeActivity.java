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
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ListView;

public class ComposeActivity extends Activity {

	EditText twitter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_compose);
		twitter = (EditText)findViewById(R.id.etTwitter);
		
		/*twitter.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
            	if(hasFocus){
            		InputMethodManager imm = (InputMethodManager)
        	                getSystemService(Context.INPUT_METHOD_SERVICE);
        	        imm.showSoftInput(v, InputMethodManager.SHOW_IMPLICIT);
            	}
            }
        });
		twitter.requestFocus();*/
		
		twitter.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				InputMethodManager imm = (InputMethodManager)
    	                getSystemService(Context.INPUT_METHOD_SERVICE);
    	        imm.showSoftInput(v, InputMethodManager.SHOW_IMPLICIT);
    	        Log.d("click", "soft keyboaed");
			}
			
		});
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
        		Log.d("TS","JSONArray");
        		finish();
        	}
        	@Override
        	public void onSuccess(JSONObject response) {
        		Log.d("TS","JSONObject");
        		finish();
        	}
        	@Override
        	public void onFailure(Throwable e, JSONArray errorResponse) {
        		Log.d("TF",e.getMessage());
        	}
            
        	@Override
        	public void onFailure(Throwable e, JSONObject errorResponse) {
        		Log.d("TF",e.getMessage());
        	}
        	          
        	
        },twitter.getText().toString());
	}
	public void cancelUpdate(View v){
		finish();
	}

}
