package com.example.bettingapp.Views;


import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings.Secure;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.bettingapp.AdsManager.DataFireStore;
import com.example.bettingapp.AdsManager.ads_manager;
import com.example.bettingapp.R;
import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.AdIconView;
import com.facebook.ads.AdListener;
import com.facebook.ads.AdOptionsView;
import com.facebook.ads.AdSettings;
import com.facebook.ads.MediaView;
import com.facebook.ads.NativeAd;
import com.facebook.ads.NativeAdLayout;
import com.facebook.ads.NativeAdListener;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.formats.UnifiedNativeAd;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends AppCompatActivity implements TabYesterday.OnFragmentInteractionListener, Odds_5.OnFragmentInteractionListener, Odds_10.OnFragmentInteractionListener {
	
	private AppBarConfiguration mAppBarConfiguration;
	
	
	private LinearLayout mAdView;
	private ads_manager manager;
	
	private NativeAdLayout nativeAdLayout;
	
	private DataFireStore dataFireStore;
	
	private NativeAd nativeAdFb;
	private View v;
	private LinearLayout AdViewFb;
	private UnifiedNativeAd nativeAd;
	LayoutInflater inflater;
	FrameLayout frameLayout;
	
	private String android_id;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main2);
		MobileAds.initialize(this, new OnInitializationCompleteListener() {
			@Override
			public void onInitializationComplete(InitializationStatus initializationStatus) {
			}
		});
		dataFireStore = DataFireStore.getInstance();
		inflater = this.getLayoutInflater();
		v = inflater.inflate(R.layout.custum_dialog_rate_app, null);
		frameLayout = v.findViewById(R.id.fl_adplaceholder);
		mAdView = findViewById(R.id.adView);
		manager = ads_manager.getInstance();
		manager.fbLoadBanner(this).setAdListener(new AdListener() {
			@Override
			public void onError(Ad ad, AdError adError) {
			
			}
			
			@Override
			public void onAdLoaded(Ad ad) {
				mAdView.addView(manager.fbadView);
			}
			
			@Override
			public void onAdClicked(Ad ad) {
			
			}
			
			@Override
			public void onLoggingImpression(Ad ad) {
			
			}
		});
		
		
		//native ad facebook
		try {
			nativeAdFb = new NativeAd(this, dataFireStore.ObjectFirebase.native_adsFb);
			nativeAdFb.setAdListener(new NativeAdListener() {
				@Override
				public void onMediaDownloaded(Ad ad) {
					// Native ad finished downloading all assets
					
				}
				
				@Override
				public void onError(Ad ad, AdError adError) {
					// Native ad failed to load
				}
				
				@Override
				public void onAdLoaded(Ad ad) {
					// Native ad is loaded and ready to be displayed
					
					inflateAd(nativeAdFb);
				}
				
				@Override
				public void onAdClicked(Ad ad) {
					// Native ad clicked
					
				}
				
				@Override
				public void onLoggingImpression(Ad ad) {
					// Native ad impression
					
				}
			});
			// Request an ad
			AdSettings.addTestDevice("2B3D0A13A0A6EF98B4E2A18C2984F989");
			nativeAdFb.loadAd();
		} catch (Exception e) {
			//Toast.makeText(context, e.toString(), Toast.LENGTH_SHORT).show();
		}
/////----- native ad faceb/>
		
		Toolbar toolbar = findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		FloatingActionButton fab = findViewById(R.id.fab);
		fab.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				startRate();
			}
		});
		DrawerLayout drawer = findViewById(R.id.drawer_layout);
		NavigationView navigationView = findViewById(R.id.nav_view);
		// Passing each menu ID as a set of Ids because each
		// menu should be considered as top level destinations.
		mAppBarConfiguration = new AppBarConfiguration.Builder(
				R.id.nav_home, R.id.contact_us, R.id.privacy,
				R.id.gdpr, R.id.nav_share, R.id.nav_send)
				.setDrawerLayout(drawer)
				.build();
		
		NavController navController = Navigation.findNavController(Main2Activity.this, R.id.nav_host_fragment);
		NavigationUI.setupActionBarWithNavController(Main2Activity.this, navController, mAppBarConfiguration);
		NavigationUI.setupWithNavController(navigationView, navController);
		
		
	}
	
	
	@Override
	public boolean onSupportNavigateUp() {
		NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
		return NavigationUI.navigateUp(navController, mAppBarConfiguration)
				|| super.onSupportNavigateUp();
	}
	
	@Override
	public void onFragmentInteraction(Uri uri) {
	
	}
	
	@Override
	public void onBackPressed() {
		rateApp();
		//super.onBackPressed();
	}
	
	private void inflateAd(NativeAd nativeAd) {
		
		nativeAd.unregisterView();
		
		// Add the Ad view into the ad container.
		
		nativeAdLayout = v.findViewById(R.id.native_ad_container);
		LayoutInflater inflater = LayoutInflater.from(Main2Activity.this);
		// Inflate the Ad view.  The layout referenced should be the one you created in the last step.
		AdViewFb = (LinearLayout) inflater.inflate(R.layout.native_ad_layout, nativeAdLayout, false);
		nativeAdLayout.addView(AdViewFb);
		
		// Add the AdOptionsView
		LinearLayout adChoicesContainer = AdViewFb.findViewById(R.id.ad_choices_container);
		AdOptionsView adOptionsView = new AdOptionsView(Main2Activity.this, nativeAd, nativeAdLayout);
		adChoicesContainer.removeAllViews();
		adChoicesContainer.addView(adOptionsView, 0);
		
		// Create native UI using the ad metadata.
		AdIconView nativeAdIcon = AdViewFb.findViewById(R.id.native_ad_icon);
		TextView nativeAdTitle = AdViewFb.findViewById(R.id.native_ad_title);
		MediaView nativeAdMedia = AdViewFb.findViewById(R.id.native_ad_media);
		TextView nativeAdSocialContext = AdViewFb.findViewById(R.id.native_ad_social_context);
		TextView nativeAdBody = AdViewFb.findViewById(R.id.native_ad_body);
		TextView sponsoredLabel = AdViewFb.findViewById(R.id.native_ad_sponsored_label);
		Button nativeAdCallToAction = AdViewFb.findViewById(R.id.native_ad_call_to_action);
		
		// Set the Text.
		nativeAdTitle.setText(nativeAd.getAdvertiserName());
		nativeAdBody.setText(nativeAd.getAdBodyText());
		nativeAdSocialContext.setText(nativeAd.getAdSocialContext());
		nativeAdCallToAction.setVisibility(nativeAd.hasCallToAction() ? View.VISIBLE : View.INVISIBLE);
		nativeAdCallToAction.setText(nativeAd.getAdCallToAction());
		sponsoredLabel.setText(nativeAd.getSponsoredTranslation());
		
		// Create a list of clickable views
		List<View> clickableViews = new ArrayList<>();
		clickableViews.add(nativeAdTitle);
		clickableViews.add(nativeAdCallToAction);
		
		// Register the Title and CTA button to listen for clicks.
		nativeAd.registerViewForInteraction(
				AdViewFb,
				nativeAdMedia,
				nativeAdIcon,
				clickableViews);
		
	}
	
	public void rateApp() {
		
		
		final androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(Main2Activity.this);
		
		if (v.getParent() != null)
			((ViewGroup) v.getParent()).removeAllViews();
		builder.setView(v);
		
		final Dialog dialog = builder.create();
		dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
		dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
		v.findViewById(R.id.doneBtn).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				dialog.dismiss();
				startRate();
				
			}
		});
		v.findViewById(R.id.cancelBtn).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				
				dialog.dismiss();
				
				finish();
				
				
			}
		});
		dialog.setCanceledOnTouchOutside(true);
		dialog.show();
		WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
		lp.copyFrom(dialog.getWindow().getAttributes());
		float density = getResources().getDisplayMetrics().density;
		lp.width = (int) (320 * density);
		lp.height = (int) (600 * density);
		lp.gravity = Gravity.BOTTOM;
		dialog.getWindow().setAttributes(lp);
		
		
	}
	
	private void startRate() {
		Uri uri = Uri.parse("market://details?id=" + this.getPackageName());
		Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
		// To count with Play market backstack, After pressing back button,
		// to taken back to our application, we need to add following flags to intent.
		goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
				Intent.FLAG_ACTIVITY_NEW_DOCUMENT |
				Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
		try {
			startActivity(goToMarket);
		} catch (ActivityNotFoundException e) {
			startActivity(new Intent(Intent.ACTION_VIEW,
					Uri.parse("http://play.google.com/store/apps/details?id=" + this.getPackageName())));
		}
	}
	
	
}
