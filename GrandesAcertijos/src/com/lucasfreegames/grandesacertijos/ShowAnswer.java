package com.lucasfreegames.grandesacertijos;


import android.app.Activity;
import android.content.Intent;
import android.net.MailTo;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import com.apptentive.android.sdk.Apptentive;
import com.google.ads.AdRequest;
import com.google.ads.AdView;
import com.google.analytics.tracking.android.EasyTracker;

public class ShowAnswer extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show_answer);
		// Look up the AdView as a resource and load a request.
		AdView adView = (AdView) this.findViewById(R.id.adView);
		adView.loadAd(new AdRequest());

		TextView t = new TextView(this);
		t = (TextView) findViewById(R.id.answer);
		t.setText(getResources().getIdentifier(
				MainActivity.CURRENT_CHALLENGE + "answer", "string",
				"com.lucasfreegames.grandesacertijos"));
		MainActivity.USERHASCONVERTED=true;
	}

	public void followUsOnFacebook(View view) {
		Intent myIntent = new Intent(
				Intent.ACTION_VIEW,
				Uri.parse("https://www.facebook.com/pages/Grandes-Acertijos-wwwgrandesacertijoscom/459433854080252"));
		startActivity(myIntent);
	}

	public void linkToApp(View view) {
		Intent myIntent = new Intent(
				Intent.ACTION_VIEW,
				Uri.parse("market://details?id=com.lucasfreegames.grandesacertijos"));
		startActivity(myIntent);
	}

	public void sendByEmail(View view) {
		Intent intent = new Intent(Intent.ACTION_SENDTO); // it's not
															// ACTION_SEND
		intent.setType("text/plain");
		intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.email_subject));
		intent.putExtra(Intent.EXTRA_TEXT,
				Html.fromHtml(getString(R.string.email_body)));
		intent.setData(Uri.parse("mailto:")); // or just "mailto:" for blank
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); // this will make such
														// that when user
														// returns to your app,
														// your app is
														// displayed, instead of
														// the email app.
		startActivity(intent);
	}

	/*
	 * @Override public void onDestroy() { if (adView != null) {
	 * adView.destroy(); } super.onDestroy(); }
	 */
    public void share(View view){
        Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
        emailIntent.setType("text/plain");
        emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, getResources().getString(R.string.app_name));
        emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, getResources().getString(R.string.share_body));
        startActivity(Intent.createChooser(emailIntent, "Share"));
        
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


}
