package com.example.bettingapp.Views;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bettingapp.AdsManager.DataFireStore;
import com.example.bettingapp.R;
import com.example.bettingapp.util.ConsentSDK;
import com.example.bettingapp.util.FireStore;

public class Splash extends AppCompatActivity {

    DataFireStore dataFireStore;
    FireStore fireStore;
    ConsentSDK consentSDK;

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

        dataFireStore = DataFireStore.getInstance();
        fireStore = FireStore.getInstence();

        dataFireStore.loadObject();
        fireStore.LoadDataToday(this);
        fireStore.LoadDatayesterday(this);

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (!dataFireStore.isObjLoaded) {
                    handler.postDelayed(this, 1000);
                    //Toast.makeText(Splash.this, "not", Toast.LENGTH_SHORT).show();
                    Log.d("TAG", "STucked I think");

                } else {
                    // do actions
                    if (dataFireStore.isObjLoaded && fireStore.isloadData) {
                        Intent i = new Intent(Splash.this, Main2Activity.class);
                        startActivity(i);
                        finish();
                    }
                }
            }
        }, 1000);
    }
}
