package com.codepath.apps.twitterclientapp;

import java.util.ArrayList;

import org.json.JSONArray;

import com.codepath.apps.com.twitterclientapp.R;
import com.codepath.apps.com.twitterclientapp.R.layout;
import com.codepath.apps.com.twitterclientapp.R.menu;
import com.codepath.apps.twitterclientapp.models.Tweet;
import com.codepath.apps.twitterclientapp.models.User;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.nostra13.universalimageloader.core.ImageLoader;

import android.os.Bundle;
import android.app.Activity;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class ProfileActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_profile);
		setTitle(MainActivity.appTitle);
		TwitterClientApp.getRestClient().getUserTimeline(
				new JsonHttpResponseHandler() {
					@Override
					public void onSuccess(JSONArray jsonTweets) {
						ArrayList<Tweet> tweets = Tweet.fromJson(jsonTweets);
						ListView lvMentions = (ListView) findViewById(R.id.lvProfile);
						TweetsAdapter adapter = new TweetsAdapter(
								getBaseContext(), tweets);
						lvMentions.setAdapter(adapter);
						
						if(tweets != null && !tweets.isEmpty()){
							setupTopView(tweets.get(0).getUser());
							
						}
					}

				});

	}
	private void setupTopView(User user){
		ImageView imageView  = (ImageView)findViewById(R.id.ivUserImage);
		Log.d("image url:",user.getProfileBackgroundImageUrl());
		ImageLoader.getInstance().displayImage(user.getProfileBackgroundImageUrl(), imageView);
		
		TextView nameView = (TextView)findViewById(R.id.tvUserName);
		String formattedName = "<b>" + user.getName() + "</b>" + "<small><front color='#777777'>@" + user.getScreenName()+"</front></small>";
		nameView.setText(Html.fromHtml(formattedName));
		
		int followerCount = user.getFollowersCount();
		int followingCount = user.getFriendsCount();
		TextView tvProfile = (TextView)findViewById(R.id.tvFollow);
		tvProfile.setText(followerCount + " Followers " + followingCount + " Following");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.profile, menu);
		return true;
	}

}
