package com.lucasfreegames.grandesacertijos;

import com.apptentive.android.sdk.Apptentive;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.smaato.soma.AdType;
import com.smaato.soma.BannerView;

public class Game extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
 		BannerView mBanner2 = (BannerView)findViewById(R.id.BannerViewGame);
 		mBanner2.getAdSettings().setPublisherId(923865022);
 		mBanner2.getAdSettings().setAdspaceId(65769027);
 		mBanner2.setLocationUpdateEnabled(true);
 		mBanner2.getAdSettings().setAdType(AdType.IMAGE);

    	 TextView t=new TextView(this); 
     	 t=(TextView)findViewById(R.id.gametext);
    	 t.setText( getResources().getIdentifier(
    			 MainActivity.CURRENT_CHALLENGE+"text", 
    			 "string", 
    			 "com.lucasfreegames.grandesacertijos"));
     	 t=(TextView)findViewById(R.id.gameTitle);
    	 t.setText(getResources().getIdentifier(
    			 MainActivity.CURRENT_CHALLENGE+"title", 
    			 "string", 
    			 "com.lucasfreegames.grandesacertijos"));

    	 ImageView i=new ImageView(this);
    	 i=(ImageView)findViewById(R.id.gamePic);
    	 i.setBackgroundResource(
    			 getResources().getIdentifier(
    	    			 MainActivity.CURRENT_CHALLENGE+"pic", 
    	    			 "drawable", 
    	    			 "com.lucasfreegames.grandesacertijos")
    			 );
    	 
    }
    
    @Override
    protected void onStart() {
        super.onStart();
        Apptentive.onStart(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Apptentive.onStop(this);
    }
}
