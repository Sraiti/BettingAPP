package com.example.bettingapp.Views;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bettingapp.AdsManager.DataFireStore;
import com.example.bettingapp.BuildConfig;
import com.example.bettingapp.R;
import com.example.bettingapp.util.ConsentSDK;
import com.example.bettingapp.util.FireStore;

public class Splash extends AppCompatActivity {
	
	DataFireStore dataFireStore;
	FireStore fireStore;
	ConsentSDK consentSDK;
	private TextView Version;
	private int counter = 0;
	private boolean isload = false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		
		consentSDK = new ConsentSDK.Builder(this)
				.addPrivacyPolicy(getString(R.string.url_privacy)) // Add your privacy policy url
				.addPublisherId(getString(R.string.publisher_id)) // Add your admob publisher id
				.build();
		
		consentSDK.checkConsent(new ConsentSDK.ConsentCallback() {
			@Override
			public void onResult(boolean isRequestLocationInEeaOrUnknown) {
			
			}
		});
		
		Version = findViewById(R.id.versionName);
		Version.setText("Version " + BuildConfig.VERSION_NAME);
		dataFireStore = DataFireStore.getInstance();
		fireStore = FireStore.getInstence();
		
		dataFireStore.loadObject();
		fireStore.LoadDataToday(this);
		StartAvtivity();
		
		
	}
	
	private void StartAvtivity() {
		final Handler handler = new Handler();
		handler.postDelayed(new Runnable() {
			@Override
			public void run() {
				if (!dataFireStore.isObjLoaded && !fireStore.isloadData) {
					handler.postDelayed(this, 1000);
				} else {
					isload = true;
					Intent i = new Intent(Splash.this, Main2Activity.class);
					startActivity(i);
					finish();
				}
				counter++;
				if (counter % 5 == 0 && !isload)
					StartAvtivity();
			}
		}, 1000);
	}
	
}
