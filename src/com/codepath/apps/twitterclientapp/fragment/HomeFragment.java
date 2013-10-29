package com.codepath.apps.twitterclientapp.fragment;

import java.util.ArrayList;

import org.json.JSONArray;

import com.codepath.apps.com.twitterclientapp.R;
import com.codepath.apps.twitterclientapp.TweetsAdapter;
import com.codepath.apps.twitterclientapp.TwitterClientApp;
import com.codepath.apps.twitterclientapp.models.Tweet;
import com.loopj.android.http.JsonHttpResponseHandler;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class HomeFragment extends Fragment{

	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {

      View view = inflater.inflate(R.layout.fragment_home, container, false);
      final ListView lvHome = (ListView)view.findViewById(R.id.lvHome);
      
      TwitterClientApp.getRestClient().getHomeTimeline(new JsonHttpResponseHandler(){
      	@Override
			public void onSuccess(JSONArray jsonTweets) {
      		ArrayList<Tweet> tweets = Tweet.fromJson(jsonTweets);
      		TweetsAdapter adapter = new TweetsAdapter(getActivity().getBaseContext(),tweets);
      		lvHome.setAdapter(adapter);
      	}
      	
      });
      return view;
    }
}
