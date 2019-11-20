package com.example.bettingapp.Views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.bettingapp.AdsManager.DataFireStore;
import com.example.bettingapp.R;
import com.example.bettingapp.util.FireStore;

public class Splash extends AppCompatActivity {

    DataFireStore dataFireStore;
    FireStore fireStore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        dataFireStore=DataFireStore.getInstance();
        fireStore=FireStore.getInstence();

        dataFireStore.loadObject();

        fireStore.LoadDataToday();
        fireStore.LoadDatayesterday();
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (!dataFireStore.isObjLoaded) {
                    handler.postDelayed(this, 1000);
                    //Toast.makeText(Splash.this, "not", Toast.LENGTH_SHORT).show();
                } else {
                    // do actions
                    if (dataFireStore.isObjLoaded){
                        Intent i = new Intent(getApplicationContext(), Main2Activity.class);
                        startActivity(i);
                        finish();
                    }
                }
            }
        }, 1000);
    }
}
