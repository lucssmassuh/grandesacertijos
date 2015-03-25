package com.lucasfreegames.grandesacertijos;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

import com.apptentive.android.sdk.Apptentive;
import com.google.analytics.tracking.android.EasyTracker;
import com.google.analytics.tracking.android.Tracker;
import com.pad.android.xappad.AdController;
import com.smaato.soma.AdType;
import com.smaato.soma.BannerView;

public class MainActivity extends Activity {
	
	public final static String ACERTIJO_MESSAGE = "com.lucasfreegames.grandesacertijos.MESSAGE";
	public static String CURRENT_CHALLENGE= "";
	public static boolean  USERHASCONVERTED=false;
	private AdController myController;
	private TabHost tabHost;
	private void setupTabHost() {
		tabHost = (TabHost) findViewById(android.R.id.tabhost);
		tabHost.setup();
	}		
	
	
	private void setupTab(final View view, final String tag, int res) {
		View tabview = createTabView(tabHost.getContext(), tag);

		TabSpec setContent = tabHost.newTabSpec(tag).setIndicator(tabview).setContent(res);
		tabHost.addTab(setContent);

	}

	private static View createTabView(final Context context, final String text) {
		View view = LayoutInflater.from(context).inflate(R.layout.tabs_bg, null);
		TextView tv = (TextView) view.findViewById(R.id.tabsText);
		tv.setText(text);
		return view;
	}
	

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupTabHost();
		tabHost.getTabWidget().setDividerDrawable(R.drawable.tab_divider);
		
		setupTab(new TextView(this),getString(R.string.tab1), R.id.tab1);
		setupTab(new TextView(this),getString(R.string.tab2), R.id.tab2);
		setupTab(new TextView(this),getString(R.string.tab3), R.id.tab3);
				
		//BANNER JUEGOS
		BannerView mBanner2 = (BannerView)findViewById(R.id.BannerView2);
		mBanner2.getAdSettings().setPublisherId(923865022);
		mBanner2.getAdSettings().setAdspaceId(65769027);
		mBanner2.setLocationUpdateEnabled(true);
		mBanner2.getAdSettings().setAdType(AdType.IMAGE);
		mBanner2.setAutoReloadEnabled(true);
		
		 myController = new AdController(getApplicationContext(), 
	        		"302331825");
	  		myController.loadNotification();


     }
    
    @Override
    public void onStart(){
    	 super.onStart();
    	 EasyTracker.getInstance(this).activityStart(this);
    	 Apptentive.onStart(this);

    }

    @Override
    public void onStop() {
      super.onStop();
      EasyTracker.getInstance(this).activityStop(this);  // Add this method.
      Apptentive.onStop(this);
    }
    
    public void showRiddle(View view) {
    	CURRENT_CHALLENGE=view.getTag().toString();
    	if ("a13".equals(CURRENT_CHALLENGE) ){
	        myController = new AdController(getApplicationContext(), 
	        		"302331825");
	  		myController.loadNotification();
  		}

    	
    	
    	Intent intent = new Intent(this, ShowRiddle.class);
    	startActivity(intent);
    }
    
    public void showGame(View view) {
    	CURRENT_CHALLENGE=view.getTag().toString();
    	Intent intent = new Intent(this, Game.class);
    	startActivity(intent);
    }

    public void followUsOnFacebook(View view){
    	Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/pages/Grandes-Acertijos-wwwgrandesacertijoscom/459433854080252"));
    	startActivity(myIntent);
    }
    
    public void linkToApp(View view){
    	Apptentive.engage(this, "GA_RATING");
    	//Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.lucasfreegames.grandesacertijos"));
        //startActivity(myIntent);
    }
    
    public void sendByEmail(View view){
    	ImageView messageCenterButton = (ImageView)findViewById(R.id.messageButton);
    	messageCenterButton.setOnClickListener(new View.OnClickListener(){
    	    public void onClick(View v) {
    	        Apptentive.showMessageCenter(MainActivity.this);
    	    }
    	});
/*    	Intent intent = new Intent(Intent.ACTION_SENDTO); // it's not ACTION_SEND
    	intent.setType("text/plain");
    	intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.email_subject));
    	intent.putExtra(Intent.EXTRA_TEXT,Html.fromHtml(getString(R.string.email_body)));
    	intent.setData(Uri.parse("mailto:")); // or just "mailto:" for blank
    	intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); // this will make such that when user returns to your app, your app is displayed, instead of the email app.
    	startActivity(intent);*/
    }
    
    public void share(View view){
        Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
        emailIntent.setType("text/plain");
        emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, getResources().getString(R.string.app_name));
        emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, getResources().getString(R.string.share_body));
        startActivity(Intent.createChooser(emailIntent, "Share"));
        
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus && MainActivity.USERHASCONVERTED ) {
            // Engage a code point called "main_activity_focused".
            boolean shown = Apptentive.engage(this, "GA_RESPONSE_SHOWN");
        }
    }

    
    
}