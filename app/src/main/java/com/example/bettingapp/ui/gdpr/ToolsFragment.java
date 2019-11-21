package com.example.bettingapp.ui.gdpr;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.bettingapp.R;
import com.example.bettingapp.util.ConsentSDK;

public class ToolsFragment extends Fragment {

    private ToolsViewModel toolsViewModel;
    ConsentSDK consentSDK;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        consentSDK = new ConsentSDK.Builder(getActivity().getApplicationContext())
                .addPrivacyPolicy(getResources().getString(R.string.url_privacy)) // Add your privacy policy url
                .addPublisherId(getResources().getString(R.string.publisher_id)) // Add your admob publisher id
                .build();
        consentSDK.checkConsent(new ConsentSDK.ConsentCallback() {
            @Override
            public void onResult(boolean isRequestLocationInEeaOrUnknown) {

            }
        });
        toolsViewModel =
                ViewModelProviders.of(this).get(ToolsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final TextView textView = root.findViewById(R.id.text_tools);
        toolsViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                //textView.setText(s);
            }
        });
        changegdpr(getActivity().getApplicationContext());

        return null;
    }

    public void changegdpr(final Context context) {
        consentSDK.requestConsent(new ConsentSDK.ConsentStatusCallback() {
            @Override
            public void onResult(boolean isRequestLocationInEeaOrUnknown, int isConsentPersonalized) {
                // Your code after the consent is submitted if needed
                consentSDK = new ConsentSDK.Builder(context)
                        .addTestDeviceId("your device id from logcat") // Add your test device id "Remove addTestDeviceId on production!"
                        .addCustomLogTag("CUSTOM_TAG") // Add custom tag default: ID_LOG
                        .addPrivacyPolicy(getString(R.string.url_privacy)) // Add your privacy policy url
                        .addPublisherId(getString(R.string.publisher_id)) // Add your admob publisher id
                        .build();
            }
        });
    }
}