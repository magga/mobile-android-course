package id.magga.admob;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

public class InterstitialAdActivity extends AppCompatActivity {

    private InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interstitial_ad);

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");

        mInterstitialAd.loadAd(new AdRequest.Builder().build());

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                // Code to be executed when an ad finishes loading.
                Toast.makeText(InterstitialAdActivity.this, "Ad Loaded", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                // Code to be executed when an ad request fails.
                Toast.makeText(InterstitialAdActivity.this, "Ad Failed To Load", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdOpened() {
                // Code to be executed when the ad is displayed.
                Toast.makeText(InterstitialAdActivity.this, "Ad Opened", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdLeftApplication() {
                // Code to be executed when the user has left the app.
                Toast.makeText(InterstitialAdActivity.this, "Ad - User Left The App", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdClosed() {
                // Code to be executed when when the interstitial ad is closed.
                Toast.makeText(InterstitialAdActivity.this, "Ad Closed", Toast.LENGTH_SHORT).show();

                // You can reload the ad when user close the ad
                // mInterstitialAd.loadAd(new AdRequest.Builder().build());
            }
        });

    }

    public void ViewAd(View view){
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {
            Toast.makeText(this, "The interstitial wasn't loaded yet.", Toast.LENGTH_SHORT).show();
        }

    }
}
