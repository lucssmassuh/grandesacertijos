package com.lucasfreegames.grandesacertijos;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import com.apptentive.android.sdk.Apptentive;
import com.google.analytics.tracking.android.EasyTracker;
import com.smaato.soma.AdType;
import com.smaato.soma.BannerView;

public class ShowRiddle extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show_riddle);

		TextView t = new TextView(this);
		t = (TextView) findViewById(R.id.riddletext);

		t.setText(getResources().getIdentifier(
				MainActivity.CURRENT_CHALLENGE + "text", "string",
				"com.lucasfreegames.grandesacertijos"));
		t = (TextView) findViewById(R.id.riddleTitle);
		t.setText(getResources().getIdentifier(
				MainActivity.CURRENT_CHALLENGE + "button", "string",
				"com.lucasfreegames.grandesacertijos"));
		
		BannerView mBanner2 = (BannerView)findViewById(R.id.BannerViewRiddle);
		mBanner2.getAdSettings().setPublisherId(923865022);
		mBanner2.getAdSettings().setAdspaceId(65769027);
		mBanner2.setLocationUpdateEnabled(true);
		mBanner2.getAdSettings().setAdType(AdType.IMAGE);
		mBanner2.setAutoReloadEnabled(true);
		
	}

	public void showAnswer(View view) {
		Intent intent = new Intent(this, ShowAnswer.class);
		startActivity(intent);
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
