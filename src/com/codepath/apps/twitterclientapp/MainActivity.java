package com.codepath.apps.twitterclientapp;



import org.json.JSONObject;

import com.codepath.apps.com.twitterclientapp.R;

import com.codepath.apps.twitterclientapp.fragment.MentionsFragment;
import com.codepath.apps.twitterclientapp.fragment.HomeFragment;
import com.codepath.apps.twitterclientapp.models.User;
import com.loopj.android.http.JsonHttpResponseHandler;


import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MainActivity extends FragmentActivity implements
		ActionBar.TabListener {

	SectionsPagerAdapter mSectionsPagerAdapter;
	ViewPager mViewPager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// Set up the action bar.
		final ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		mSectionsPagerAdapter = new SectionsPagerAdapter(
				getSupportFragmentManager());

		// Set up the ViewPager with the sections adapter.
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mSectionsPagerAdapter);

		mViewPager
				.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
					@Override
					public void onPageSelected(int position) {
						actionBar.setSelectedNavigationItem(position);
					}
				});

		Tab tab_home = actionBar.newTab().setText(mSectionsPagerAdapter.getPageTitle(0))
				.setIcon(R.drawable.ic_home).setTag("HomeTag")
				.setTabListener(this);

		actionBar.addTab(tab_home);
		Tab tab_mention = actionBar.newTab().setText(mSectionsPagerAdapter.getPageTitle(1))
				.setIcon(R.drawable.ic_mention).setTag("MenstionTag")
				.setTabListener(this);

		actionBar.addTab(tab_mention);
		setupTitle();
	}

	public static String appTitle;
	private void setupTitle(){
		  TwitterClientApp.getRestClient().getVerifyCredentials(new JsonHttpResponseHandler(){
	        	@Override
				public void onSuccess(JSONObject jsonUser) {
	        		User user = User.fromJson(jsonUser);
	        		appTitle = user.getName();
	        		setTitle(appTitle);
	        	}
	        	
	        });
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle item selection
		switch (item.getItemId()) {
		case R.id.action_compose:
			composeAction();
			return true;

		case R.id.action_profile:
			profileAction();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
    private void composeAction(){
    	Intent i = new Intent(this, ComposeActivity.class);
    	startActivity(i);
    }
    
    private void profileAction(){
    	Intent i = new Intent(this, ProfileActivity.class);
    	startActivity(i);
    }
    
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onTabSelected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
		mViewPager.setCurrentItem(tab.getPosition());
	}

	@Override
	public void onTabUnselected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
	}

	@Override
	public void onTabReselected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
	}

	public class SectionsPagerAdapter extends FragmentPagerAdapter {

		public SectionsPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			Fragment fragment;
			if (position == 0) 
				fragment = new HomeFragment();
				
				
			 else 
				fragment = new MentionsFragment();
				
				
			
			return fragment;
		}

		@Override
		public int getCount() {
			// Show 3 total pages.
			return 2;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			// Locale l = Locale.getDefault();
			switch (position) {
			case 0:
				return "Home";// getString(R.string.title_section1).toUpperCase(l);
			case 1:
				return "Mentions";// getString(R.string.title_section2).toUpperCase(l);
				// case 2:
				// return getString(R.string.title_section3).toUpperCase(l);
			}
			return null;
		}
	}

	

}
