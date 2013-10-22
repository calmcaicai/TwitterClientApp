package com.codepath.apps.twitterclientapp;


import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import com.codepath.apps.com.twitterclientapp.R;
import com.codepath.apps.twitterclientapp.models.Tweet;
import com.codepath.apps.twitterclientapp.models.User;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

public class TimelineActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);
        
        TwitterClientApp.getRestClient().getHomeTimeline(new JsonHttpResponseHandler(){
        	@Override
			public void onSuccess(JSONArray jsonTweets) {
        		ArrayList<Tweet> tweets = Tweet.fromJson(jsonTweets);
        		ListView lvTweets = (ListView)findViewById(R.id.lvTweets);
        		TweetsAdapter adapter = new TweetsAdapter(getBaseContext(),tweets);
        		lvTweets.setAdapter(adapter);
        	}
        	
        });
        
        TwitterClientApp.getRestClient().getVerifyCredentials(new JsonHttpResponseHandler(){
        	@Override
			public void onSuccess(JSONObject jsonUser) {
        		User user = User.fromJson(jsonUser);
        		//Log.d("USER:" , user.getName() + " " + user.getScreenName());
        		setTitle(user.getName());
        	}
        	
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.timeline, menu);
        return true;
    }
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle item selection
		switch (item.getItemId()) {
		case R.id.action_compose:
			composeAction();
			return true;

		default:
			return super.onOptionsItemSelected(item);
		}
	}
    public void composeAction(){
    	Intent i = new Intent(this, ComposeActivity.class);
    	startActivity(i);
    }
    
}
