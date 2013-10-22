package com.codepath.apps.twitterclientapp;

import java.util.List;

import com.codepath.apps.twitterclientapp.models.Tweet;

import com.nostra13.universalimageloader.core.ImageLoader;


import android.content.Context;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.codepath.apps.com.twitterclientapp.R;

public class TweetsAdapter extends ArrayAdapter<Tweet>{

	public TweetsAdapter(Context context,  List<Tweet> objects) {
		super(context, 0, objects);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = convertView;
		if(view == null){
			LayoutInflater inflator = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = inflator.inflate(R.layout.tweet_item,null);
		}
		Tweet tweet = this.getItem(position);
		ImageView imageView  = (ImageView) view.findViewById(R.id.ivProfile);
		//Log.d("ADAPTER",tweet.getUser().getProfileBackgroundImageUrl());
		//Log.d("ADAPTER",tweet.getUser().getName());
		//Log.d("ADAPTER",tweet.getBody());
		ImageLoader.getInstance().displayImage(tweet.getUser().getProfileBackgroundImageUrl(), imageView);
		
		TextView nameView = (TextView)view.findViewById(R.id.tvName);
		String formattedName = "<b>" + tweet.getUser().getName() + "</b>" + "<small><front color='#777777'>@" + tweet.getUser().getScreenName()+"</front></small>";
		nameView.setText(Html.fromHtml(formattedName));
		
		TextView bodyView = (TextView)view.findViewById(R.id.tvBody);
		bodyView.setText(Html.fromHtml(tweet.getBody()));
		return view;
	}

}
