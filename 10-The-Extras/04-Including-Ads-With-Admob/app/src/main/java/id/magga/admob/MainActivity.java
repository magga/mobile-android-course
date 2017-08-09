package id.magga.admob;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void BannerPage(View view){
        Intent intent = new Intent(getApplicationContext(), BannerAddActivity.class);
        startActivity(intent);
    }

    public void InterstitialPage(View view){
        Intent intent = new Intent(getApplicationContext(), InterstitialAdActivity.class);
        startActivity(intent);
    }

    public void RewardedVideoPage(View view){
        Intent intent = new Intent(getApplicationContext(), RewardedVideoAdActivity.class);
        startActivity(intent);
    }
}
