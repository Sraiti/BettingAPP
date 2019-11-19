package com.example.bettingapp.AdsManager;

public class module_firebase {
    public String admob_banner;
    public String admob_interstitial;
    public String fb_banner;
    public String fb_interstitial;
    public String native_ads;
    public String native_adsFb;

    public module_firebase(){}

    public String getAdmob_banner() {
        return admob_banner;
    }

    public void setAdmob_banner(String admob_banner) {
        this.admob_banner = admob_banner;
    }

    public String getAdmob_interstitial() {
        return admob_interstitial;
    }

    public void setAdmob_interstitial(String admob_interstitial) {
        this.admob_interstitial = admob_interstitial;
    }

    public String getFb_banner() {
        return fb_banner;
    }

    public void setFb_banner(String fb_banner) {
        this.fb_banner = fb_banner;
    }

    public String getFb_interstitial() {
        return fb_interstitial;
    }

    public void setFb_interstitial(String fb_interstitial) {
        this.fb_interstitial = fb_interstitial;
    }

    public String getNative_ads() {
        return native_ads;
    }

    public void setNative_ads(String native_ads) {
        this.native_ads = native_ads;
    }

    public String getNative_adsFb() {
        return native_adsFb;
    }

    public void setNative_adsFb(String native_adsFb) {
        this.native_adsFb = native_adsFb;
    }
}
